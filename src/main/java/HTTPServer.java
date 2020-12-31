import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HTTPServer implements Runnable{
    Socket cli;
    List<User> users;
    List<String> stack;
    List<Stack> packages;
    List<String> deck;
    Battlefield battle = new Battlefield();
    private Connection connection = null;

    public HTTPServer(Socket s, List<User> usr, List<String> stk,List<Stack> stacks, List<String> dck, Battlefield fight){
        cli = s;
        users = usr;
        stack = stk;
        packages = stacks;
        deck =dck;
        battle = fight;
    }
    public HTTPServer(Connection conn){
        connection = conn;
    }

    public static void main(String[] args){
        List<String> messages = new ArrayList<>();
        List<User> users  = new ArrayList<>();
        List<String> stack  = new ArrayList<>();
        List<Stack> stacksList = new ArrayList<>();
        List<String> deck  = new ArrayList<>();
        Battlefield battle = new Battlefield();

        try{
            //open a tcp socket
            ServerSocket serverSocket = new ServerSocket(10001);

            while (true){
                //accept the client connection
                HTTPServer httpServer = new HTTPServer(serverSocket.accept(),users,stack,stacksList,deck, battle);
                Thread thread = new Thread(httpServer);
                thread.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void run() {
        //read the request

        BufferedReader in = null;
        PrintWriter out = null;
        BufferedOutputStream contentSend = null;
        String inputStream;
        try{
            //read the characters from the client
            in = new BufferedReader(new InputStreamReader(cli.getInputStream()));
            //we send or get the character output stream to the client -> for header
            out = new PrintWriter(cli.getOutputStream());
            //we send or get the binary output stream to the client -> for body
            contentSend = new BufferedOutputStream(cli.getOutputStream());
            //get the first line of the request
            inputStream = in.readLine();

            openConnection("jdbc:postgresql://localhost:5432/swe1db","postgres","admin");

            if (inputStream != null) {

                String headerPart = readFirstLineHeader(inputStream);

                String[] headerPartSplit = headerPart.split(",");
                //get the method
                String method = headerPartSplit[0];
                //get the path
                String path = headerPartSplit[1];
                //get the version
                String version = headerPartSplit[2];
                //save the content-type
                String contentType = null;
                if (!RequestMethods.hasMethod(method)){
                    //send a response 501 not implemented, if we haven't implement the asked Method
                    sendRespond(out,contentSend, ResponseStatusCode.getDesc(501),version,contentType,"Wrong Method chosen".getBytes());
                }
                /*-----------------------------------------------------------------------------------------------------------------------------------------------------*/
                else if (method.equals(RequestMethods.GET.getVal())) {
                    String secondPartPath = pathSpliter(path);
                    String allCards = null;
                    if (path.equals("/cards")) {
                        //take the token
                        String secureToken = takeToken(in,method);
                        //check if the token was send in the request
                        if (secureToken != null) {
                            //take the username from the token
                            String[] userSplit = secureToken.split("-");
                            String user = userSplit[0];
                            //if the user exists take the stack of cards from him and return them as a response
                            boolean userExists = userCheck(user);
                            if (userExists) {
                                //User usr = new User("arber");
                                //users.add(user);
                                //System.out.println("SIze of  user list: "+users.size());
                                for (int i = 0; i < users.size(); i++) {
                                    if (user.equals(users.get(i).getUsername())) {
                                        User player = users.get(i);
                                        //System.out.println("SIze: "+player.getStack().stackSize());
                                        if(player.getStack().stackSize()>0){
                                            for (int j = 0; j < player.getStack().stackSize(); j++) {
                                                stack.add(player.getStack().pickCardFromStack(i).getName() + ","
                                                        + player.getStack().pickCardFromStack(i).getDamage() + "," + player.getStack().pickCardFromStack(i).getElement_type());
                                            }
                                            allCards = listAllCards(stack);
                                        }else{
                                            allCards = "No Cards in Stack yet!!";
                                        }
                                    }
                                }
                                //send the respond to the client
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, allCards.getBytes());
                            } else {
                                //if user doesnt exist then send a not found message
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "No User!!".getBytes());
                            }
                        }else{
                            //if the token is null, it means you can show the client anything
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is missing".getBytes());
                        }
                    }
                    else if(path.equals("/deck")) {
                        //take the token
                        String secureToken = takeToken(in,method);
                        //check if the token was send in the request
                        if (secureToken != null) {
                            //take the username from the token
                            String[] userSplit = secureToken.split("-");
                            String user = userSplit[0];
                            //if the user exists take the deck of cards from him and return them as a response
                            boolean userExists = userCheck(user);
                            if (userExists) {
                                //User usr = new User("arber");
                                //users.add(usr);
                                //System.out.println("SIze of  user list: "+users.size());
                                for (int i = 0; i < users.size(); i++) {
                                    if (user.equals(users.get(i).getUsername())) {
                                        User player = users.get(i);
                                        //Battlefield battle = new Battlefield();
                                        //battle.createDeck(player);
                                        if(player.getDeck().deckSize()>0){
                                            for (int j = 0; j < player.getDeck().deckSize(); j++) {
                                                deck.add(player.getDeck().pickCardFromDeck(j).getName()+","
                                                        +player.getDeck().pickCardFromDeck(j).getDamage()+","+player.getDeck().pickCardFromDeck(j).getElement_type());
                                            }
                                            allCards = listAllCards(deck);
                                        }else{
                                            allCards = "The deck is not created yet!!";
                                        }
                                    }
                                }
                                //send the respond to the client
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, allCards.getBytes());
                            }else {
                                //if user doesnt exist then send a not found message
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "No User!!".getBytes());
                            }
                        }else{
                            //if the token is null, it means you can show the client anything
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is missing".getBytes());
                        }
                    }else if (path.equals("/users/"+secondPartPath)){
                        String userInfo = "";
                        //take the token
                        String secureToken = takeToken(in,method);
                        //check if the token was send in the request
                        if (secureToken != null) {
                            //take the username from the token
                            String[] userSplit = secureToken.split("-");
                            String user = userSplit[0];
                            if(user.equals(secondPartPath)){
                                //if the user exists take the deck of cards from him and return them as a response
                                boolean userExists = userCheck(user);
                                if (userExists) {
                                    //User usr = new User("arber");
                                    //users.add(usr);
                                    //System.out.println("SIze of  user list: "+users.size());
                                    for (int i = 0; i < users.size(); i++) {
                                        if (user.equals(users.get(i).getUsername())) {
                                            User player = users.get(i);
                                            userInfo = player.getUsername()+"|"
                                                    +player.getPassword()+"|"+player.getBio()+"|"+player.getImage();
                                        }
                                    }
                                    //send the respond to the client
                                    sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, userInfo.getBytes());
                                }else {
                                    //if user doesnt exist then send a not found message
                                    sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "No User!!".getBytes());
                                }
                            }else{
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is incorrect!!".getBytes());

                            }
                        }else{
                            //if the token is null, it means you can show the client anything
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is missing".getBytes());
                        }
                    }else if (path.equals("/score")){
                        String info = "";
                        //take the token
                        String secureToken = takeToken(in,method);
                        //check if the token was send in the request
                        if (secureToken != null) {
                            //take the username from the token
                            String[] userSplit = secureToken.split("-");
                            String user = userSplit[0];
                            //if the user exists take the deck of cards from him and return them as a response
                            boolean userExists = userCheck(user);
                            if (userExists) {
                                //User usr = new User("arber");
                                //users.add(usr);
                                //System.out.println("SIze of  user list: "+users.size());
                                for (int i = 0; i < users.size(); i++) {
                                    if (user.equals(users.get(i).getUsername())) {
                                        User player = users.get(i);
                                        info = "--> " + player.getUsername() + " has " + player.getUser_status()+" points!!";
                                    }
                                }
                                //send the respond to the client
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, info.getBytes());
                            }else {
                                //if user doesnt exist then send a not found message
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "No User!!".getBytes());
                            }
                        }else{
                            //if the token is null, it means you can show the client anything
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is missing".getBytes());
                        }
                    }
                    else if (path.equals("/stats")){
                        String info = null;
                        //take the token
                        String secureToken = takeToken(in,method);
                        //check if the token was send in the request
                        if (secureToken != null) {
                            //take the username from the token
                            String[] userSplit = secureToken.split("-");
                            String user = userSplit[0];
                            //if the user exists take the deck of cards from him and return them as a response
                            boolean userExists = userCheck(user);
                            if (userExists) {
                                //User usr = new User("arber");
                                //users.add(usr);
                                //System.out.println("SIze of  user list: "+users.size());
                                for (int i = 0; i < users.size(); i++) {
                                    if (user.equals(users.get(i).getUsername())) {
                                        User player = users.get(i);
                                        info = "------------------------------------------------------------\n"
                                                + "--> Winner of this game is: "+battle.getWinner().getUsername()
                                                + "\n--> In this game were "+battle.getPlayedGames()+" round(s) played!!"
                                                +"\n--> "+ player.getUsername() + " got " + player.getDeck().deckSize() + " card(s) on his deck!!"
                                                +"\n--> "+ player.getUsername() + " got " + player.getCoins() + " coins left!!"
                                                +"\n------------------------------------------------------------";
                                    }
                                }
                                //send the respond to the client
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, info.getBytes());
                            }else {
                                //if user doesnt exist then send a not found message
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "No User!!".getBytes());
                            }
                        }else{
                            //if the token is null, it means you can show the client anything
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is missing".getBytes());
                        }
                    }
                    else{
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Wrong URI".getBytes());
                    }
                /*-----------------------------------------------------------------------------------------------------------------------------------------------------*/
                }
                else if (method.equals(RequestMethods.POST.getVal())) {
                    String bodyReq = takeTheBody(in);
                    String[] bodySplit = bodyReq.split("/");
                    String bodyContent = bodySplit[0];
                    contentType = bodySplit[1]+bodySplit[2];
                    ObjectMapper objectMapper = new ObjectMapper();
                    if (path.equals("/users")) {
                        //User player = objectMapper.readValue(bodyContent, User.class);
                        JsonNode jsonNode = objectMapper.readTree(bodyContent);
                        String username = jsonNode.get("Username").asText();
                        String password = jsonNode.get("Password").asText();
                        User user = new User(username,password);
                        //add user to the List of Users
                        boolean userExists = userCheck(username);
                        //If the user exist we can't create a new one, because every user is unique
                        if(userExists){
                            //add user to the List of Users
                            users.add(user);
                            //if user  exist then send a not found message
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(400), version, contentType, "User already exists!!".getBytes());
                        }else{
                            //add user to the List of Users
                            users.add(user);
                            boolean userCreated = signUp(user);
                            if(userCreated){
                                //send the respond to the client
                                String response = "The user was successfully created";
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(201), version, contentType, response.getBytes());
                            }
                        }
                        System.out.println("User List and length: "+users.toString()+"|"+users.size());
                    }
                    else if (path.equals("/sessions")) {
                        JsonNode node = objectMapper.readTree(bodyContent);
                        String username = node.get("Username").asText();
                        String password = node.get("Password").asText();
                        boolean userExists = userCheck(username);
                        //If the user exist we can't create a new one, because every user is unique
                        if(userExists){
                            boolean userLoggedIn = logIn(username,password);
                            if(userLoggedIn){
                                String response = "The user was successfully logged in";
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, response.getBytes());
                            }else{
                                String response = "Wrong Username or Password!!";
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, response.getBytes());
                            }
                        }else{
                                //send the respond to the client
                                String response = "The user doesn't exist!";
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, response.getBytes());

                        }

                    }
                    else if (path.equals("/battles")) {
                        String info = "";
                        //take the token
                        //String secureToken = takeTokenForBattle(in);
                        String secureToken = bodySplit[2];
                        //check if the token was send in the request
                        System.out.println("Token in post: "+secureToken);
                        if (secureToken != null) {
                            //take the username from the token
                            String[] userSplit = secureToken.split("-");
                            String user = userSplit[0];
                            //if the user exists take the deck of cards from him and return them as a response
                            boolean userExists = userCheck(user);
                            /*User user1111 = new User("kienboec");
                            users.add(user1111);
                            User user1112 = new User("altenhof");
                            users.add(user1112);*/
                            if (userExists) {
                                for (int i = 0; i < users.size(); i++) {
                                    if (user.equals(users.get(i).getUsername())) {
                                        User player = users.get(i);
                                        //System.out.println("Player:"+player.getUsername());
                                        User againster = users.get(i+1);
                                        battle.startGame(player, againster);
                                    }
                                }
                                info = "Battle started";
                                //send the respond to the client
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, info.getBytes());
                            }else {
                                //if user doesnt exist then send a not found message
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "User doesn't exists!!".getBytes());
                            }
                        }else{
                            //if the token is null, it means you can show the client anything
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is missing".getBytes());
                        }
                    }
                    else if(path.equals("/packages")){
                        String secureToken = bodySplit[3];
                        if (secureToken != null) {
                            //take the username from the token
                            String[] userSplit = secureToken.split("-");
                            String user = userSplit[0];
                            System.out.println("user: "+user);
                            //if the user exists take the deck of cards from him and return them as a response
                            boolean userExists = userCheck(user);
                            System.out.println("does user exist: "+userExists);
                            if (userExists) {
                                for (int i = 0; i < users.size(); i++) {
                                    if (user.equals(users.get(i).getUsername())) {
                                        User player = users.get(i);
                                        //buy a package
                                        //admin -> saves 5 cards in his stack
                                        player.getStack().buy_packages();
                                        System.out.println("admin stack size: "+player.getStack().stackSize());
                                        //add the package to its List
                                        packages.add(player.getStack());
                                    }
                                    System.out.println("Package und length:"+packages.toString()+"|"+packages.size());
                                }
                                String info = "Package created";
                                //send the respond to the client
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, info.getBytes());
                            }else {
                                //if user doesnt exist then send a not found message
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "No User!!".getBytes());
                            }
                        }else{
                            //if the token is null, it means you can show the client anything
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is missing".getBytes());
                        }
                    }else if(path.equals("/transactions/packages")){
                        String secureToken = bodySplit[3];
                        if (secureToken != null) {
                            //take the username from the token
                            String[] userSplit = secureToken.split("-");
                            String user = userSplit[0];
                            //if the user exists take the deck of cards from him and return them as a response
                            boolean userExists = userCheck(user);
                            if (userExists) {
                                for (int i = 0; i < users.size(); i++) {
                                    if (user.equals(users.get(i).getUsername())) {
                                        User player = users.get(i);
                                        if(player.getCoins()>0){
                                            if(packages.size()>0){
                                                //get the first package
                                                //Stack pkg = packages.get(0);
                                                //save the package in the players stack
                                                player.getStack().addPkg(packages.get(0).getStack());
                                                //remove that package which was taken
                                                packages.remove(0);
                                                player.updateCoins();
                                                System.out.println("player Stack before: "+player.getStack().getStack());
                                                //System.out.println("stack size of player before: "+player.getStack().stackSize());
                                                //delete 5 cards that are more than needed in the stack
                                                if(player.getStack().stackSize() > 5){
                                                    for (int a = player.getStack().stackSize()-9;a<player.getStack().stackSize();a++){
                                                        player.getStack().deleteCardsToStack(player.getStack().pickCardFromStack(a));
                                                    }
                                                }

                                                //System.out.println("Coins: "+player.getCoins());
                                                System.out.println("player Stack: "+player.getStack().getStack());
                                                //System.out.println("stack size of player: "+player.getStack().stackSize());

                                                String info = "Package transfered to the player!!";
                                                //send the respond to the client
                                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, info.getBytes());
                                            }
                                            else{
                                                String info = "No packages to buy avaliable!!";
                                                //send the respond to the client
                                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, info.getBytes());
                                            }
                                        }
                                        else{
                                            String info = "No more coins!!";
                                            //send the respond to the client
                                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, info.getBytes());
                                        }
                                    }
                                }
                            }else {
                                //if user doesnt exist then send a not found message
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "No User!!".getBytes());
                            }
                        }else{
                            //if the token is null, it means you can show the client anything
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is missing".getBytes());
                        }
                    }
                    else if (path.equals("/battles/1")) {
                        String info = "";
                        //take the token
                        //String secureToken = takeTokenForBattle(in);
                        String secureToken = bodySplit[2];
                        //check if the token was send in the request
                        System.out.println("Token in post: "+secureToken);
                        if (secureToken != null) {
                            //take the username from the token
                            String[] userSplit = secureToken.split("-");
                            String user = userSplit[0];
                            //if the user exists take the deck of cards from him and return them as a response
                            boolean userExists = userCheck(user);
                            User user1111 = new User("kienboec");
                            users.add(user1111);
                            User user1112 = new User("altenhof");
                            users.add(user1112);
                            if (userExists) {
                                for (int i = 0; i < users.size(); i++) {
                                    if (user.equals(users.get(i).getUsername())) {
                                        User player = users.get(i);
                                        if(player.getCoins()>0){
                                                //get the first package
                                                //Stack pkg = packages.get(0);
                                                //save the package in the players stack
                                                player.getStack().buy_packages();
                                                //remove that package which was taken
                                                player.updateCoins();

                                                info = "Package transfered to the player!!";
                                                //send the respond to the client
                                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, info.getBytes());
                                        }
                                        else{
                                            info = "No more coins!!";
                                            //send the respond to the client
                                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, info.getBytes());
                                        }

                                        User againster = users.get(i+1);
                                        battle.startGame(player, againster);
                                    }
                                }
                                info = "Battle started";
                                //send the respond to the client
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, info.getBytes());
                            }else {
                                //if user doesnt exist then send a not found message
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "User doesn't exists!!".getBytes());
                            }
                        }else{
                            //if the token is null, it means you can show the client anything
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is missing".getBytes());
                        }
                    }
                }
                /*-----------------------------------------------------------------------------------------------------------------------------------------------------*/
                //update a message in the list
                else if(method.equals(RequestMethods.PUT.getVal())) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String bodyReq = takeTheBody(in);
                    String[] bodySplit = bodyReq.split("/");
                    String bodyContent = bodySplit[0];
                    contentType = bodySplit[1];
                    String secureToken = bodySplit[3];

                    String secondPartPath = pathSpliter(path);

                    if (path.equals("/users/"+secondPartPath)){
                        String userInfo = "";
                        //check if the token was send in the request
                        if (secureToken != null) {
                            //take the username from the token
                            String[] userSplit = secureToken.split("-");
                            String user = userSplit[0];
                            if(user.equals(secondPartPath)){
                                //if the user exists take the deck of cards from him and return them as a response
                                boolean userExists = userCheck(user);
                                if (userExists) {
                                    JsonNode node = objectMapper.readTree(bodyContent);
                                    String username = node.get("Name").asText();
                                    String bio = node.get("Bio").asText();
                                    String image = node.get("Image").asText();

                                    editProfile(user,username,bio,image);
                                    userInfo = "It was successfully edited!!";
                                    //save the bio and image also into the user class
                                    for (int i = 0; i < users.size(); i++) {
                                        if (user.equals(users.get(i).getUsername())) {
                                            User player = users.get(i);
                                            player.setBio(bio);
                                            player.setImage(image);
                                        }
                                    }
                                    //send the respond to the client
                                    sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, userInfo.getBytes());
                                }else {
                                    //if user doesnt exist then send a not found message
                                    sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "No User!!".getBytes());
                                }
                            }else{
                                sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is incorrect!!".getBytes());

                            }
                        }else{
                            //if the token is null, it means you can show the client anything
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is missing".getBytes());
                        }
                    }

                }
                /*-----------------------------------------------------------------------------------------------------------------------------------------------------*/
                //delete a certain user from Datenbank
                else if(method.equals(RequestMethods.DELETE.getVal())){
                    //take the token
                    String secureToken = takeToken(in,method);
                    //check if the token was send in the request
                    if (secureToken != null) {
                        //take the username from the token
                        String[] userSplit = secureToken.split("-");
                        String user = userSplit[0];
                        //if the user exists take the deck of cards from him and return them as a response
                        boolean userExists = userCheck(user);
                        if (userExists) {

                            for (int i = 0; i < users.size(); i++) {
                                if (user.equals(users.get(i).getUsername())) {
                                    User player = users.get(i);
                                    deleteUser(player);
                                }
                            }
                            //send the respond to the client
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(200), version, contentType, "Successfully deleted!!".getBytes());
                        }else {
                            //if user doesnt exist then send a not found message
                            sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "No User!!".getBytes());
                        }
                    }else{
                        //if the token is null, it means you can show the client anything
                        sendRespond(out, contentSend, ResponseStatusCode.getDesc(404), version, contentType, "Token is missing!!".getBytes());
                    }
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                in.close();
                out.close();
                contentSend.close();
                cli.close();
                closeConnection();
            } catch (IOException | SQLException e) {
                System.err.println("Error closing stream : " + e.getMessage());
            }
        }
    }
    public static String readFirstLineHeader(String input){
        //parse the request
        StringTokenizer st = new StringTokenizer(input);
        //get the method
        String method = st.nextToken().toUpperCase();
        //get the path
        String path = st.nextToken().toLowerCase();
        //get the version
        String version = st.nextToken().toUpperCase();

        String returnLine = method + "," + path + "," +version;
        return returnLine;
    }
    public static String pathSpliter(String path){
        String msgID = null;
        String[] pathSplited = path.split("/");
        if (pathSplited.length == 3){
            msgID  = pathSplited[2];
            return msgID;
        }
        return null;
    }
    public static String takeToken(BufferedReader in, String method) throws IOException {
        if(method.equals(RequestMethods.GET.getVal())){
            String secureToken = takeTheToken(in);
            if(secureToken.matches("[a-zA-Z]+[-][mtcgToken]{9}")){
                return secureToken;
            }
        }else{
            String bodyReq = takeTheBody(in);
            String[] bodySplit = bodyReq.split("/");
            String secureToken = bodySplit[3];
            if(secureToken.matches("[a-zA-Z]+[-][mtcgToken]{9}")){
                return secureToken;
            }
        }
        return null;
    }
    public static String listAllCards(List<String> crd){
        int i = 0;
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append("Stack of Cards:\n");
        while (i < crd.size() - 1) {
            msgBuilder.append(crd.get(i)+"\n");
            i++;
        }
        msgBuilder.append(crd.get(i));
        return msgBuilder.toString();
    }
    private static void sendRespond(PrintWriter out,BufferedOutputStream contentSend, String status, String version, String contentTyp, byte[] content) throws IOException {
        //send the response to the client's output stream
        out.println(version +" "+ status);
        out.println("Content-Type: "+ contentTyp);
        out.println(); // blank line between headers and content
        out.flush(); // flush character output stream buffer
        contentSend.write(content);
        contentSend.flush();
    }
    public static String takeTheToken(BufferedReader in) throws IOException {
        //take the other part of the header + the body
        StringBuilder requestBody = new StringBuilder();
        //divide every field of the header and the last part(the body) with a newline
        while (in.ready()) {
            char line = (char) in.read();
            requestBody.append(line);
        }
        String body = requestBody.toString();
        //System.out.println("HEADER full:\n"+body);
        String[] requestLines = body.split("\r\n");
        //split the line of the token where a space is
        String token = null;
        if(requestLines.length>3){
            String[] requestToken = requestLines[3].split(" ");
            //take the token
            token = requestToken[2];
        }else{
            return "";
        }
        System.out.println("token: "+token);
        return token;
    }
    public static String takeTheBody(BufferedReader in) throws IOException {
        //take the other part of the header + the body
        StringBuilder requestBody = new StringBuilder();
        //divide every field of the header and the last part(the body) with a newline
        while (in.ready()) {
            char line = (char) in.read();
            requestBody.append(line);
        }
        String body = requestBody.toString();
        System.out.println("HEADER full:\n"+body);
        String[] requestLines = body.split("\r\n");
        //split the line of the content type if you use postman where a space is
        String[] requestLine = requestLines[0].split(" ");
        String contentType = "";

        //split the line of the content type if you use curl where a space is
        String[] request = requestLines[3].split(" ");

        String[] requestToken;
        if(requestLines.length>4){
            //split the line of the token where a space is
            requestToken = requestLines[4].split(" ");
        }else{
            requestToken = requestLines[3].split(" ");
        }
        //take the token
        String token = "";

        //it comes inside the if , when we use postman
        if(requestLine[0].equals("Content-Type:")){
            //take the content type
            contentType = requestLine[1];
        }//otherwise if its curl it comes in else
        else{
            //take the content type
            contentType = request[1];
        }
        if(requestToken[0].equals("Authorization:")){
            token = requestToken[2];
        }

        //list for the header
        List<String> _headers = new ArrayList<String>();
        //save the header in the List
        for (int i = 2; i < requestLines.length; i++) {
            String header = requestLines[i];
            _headers.add(header);
        }

        System.out.println("token: "+token);
        System.out.println("content type: "+contentType);
        System.out.println("HEADER:"+_headers);
        String bodyContent = null;
        //save the body into a string
        if (!_headers.isEmpty()) {
            bodyContent = _headers.get(_headers.size() - 1);
        }
        System.out.println("body: "+bodyContent);
        return bodyContent+"/"+contentType+"/"+token;
    }

    public static String takeTokenForBattle(BufferedReader in) throws IOException {
        //take the other part of the header + the body
        StringBuilder requestBody = new StringBuilder();
        //divide every field of the header and the last part(the body) with a newline
        while (in.ready()) {
            char line = (char) in.read();
            requestBody.append(line);
        }
        String body = requestBody.toString();
        String[] requestLines = body.split("\r\n");
        System.out.println("Length2: "+requestLines.length);
        for(int i = 0 ; i < requestLines.length; i++){
            System.out.println("show2: "+requestLines[i]);
        }
        String[] requestToken = requestLines[3].split(" ");
        //take the token
        String token = requestToken[2];
        System.out.println("token2: "+token);

        return token;
    }

    public void openConnection(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public void closeConnection() throws SQLException {
        connection.close();
        connection = null;
    }

    public boolean signUp(User user) throws SQLException {
        //insert into the db
        //if the user exist then return false
        // if not the create a new user in the db with this name and password
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO fighter (username,password) VALUES (?,?); ");
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getPassword());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (userCheck(user.getUsername())){
            return true;
        }
        return false;
    }

    public boolean logIn(String username, String password){
        //select the username of the user, if it doesnt exist then create
        //if it exists select also the password from db if those are correct
        // then login
        try {
            PreparedStatement statement = connection.prepareStatement(" SELECT username, password FROM fighter WHERE username = ?; ");
            statement.setString(1,username);
            ResultSet rs = statement.executeQuery();
            String name = null;
            String pwd = null;
            while(rs.next()){
                name = rs.getString(1);
                pwd = rs.getString(2);
            }
            if(username.equals(name)){
                if (password.equals(pwd)){
                    System.out.println("login_successful");
                    return true;
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("login_denied");
        return false;
    }
    public void editProfile(String user,String name, String bio, String image){
        //update into db
        //we see if the user first of all exists
        //then update the user with the parameter we took from the entwickler
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE fighter SET firstname=?, bio=?, image=? WHERE username=?;\n");
            statement.setString(1,name);
            statement.setString(2,bio);
            statement.setString(3,image);
            statement.setString(4, user);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteUser(User usr) throws SQLException {
        //if a user exists then if u want you can delete it
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM fighter WHERE username = ?; ");
            statement.setString(1,usr.getUsername());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (!userCheck(usr.getUsername())){
            System.out.println("User \""+usr.getUsername()+"\" was successfully deleted");
        }

    }

    public boolean tokenCheck(String token, User user){
        //check if the token that came is same as the one that the user should have
        // if its true then the other operations can be fulfilled
        String checkToken = user.getUsername()+"-mctgToken";
        if(token.equals(checkToken)){
            return true;
        }
        return false;
    }

    public String createToken(String usr){
        String token = usr+"-mtcgToken";
        return token;
    }
    public boolean userCheck(String user) throws SQLException{
        //check if a user with this name in the db exists
        //if not return false
        try {
            PreparedStatement statement = connection.prepareStatement(" SELECT username FROM fighter WHERE username = ?; ");
            statement.setString(1,user);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String name = rs.getString(1);
                if(user.equals(name)){
                    return true;
                }else{
                    return false;
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }



}
