import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

           User usr = new User("Arber Muca");
           User opponent = new User("Norbert Niemand");
           Battlefield battle = new Battlefield();
           // battle.startGame(usr,opponent);
        Card crd = new Monster("hello",1222,Elements.FIRE);
        Card user = new Monster("hello",1222,Elements.FIRE);;
        if (!crd.getElement_type().equals(user.getElement_type())){
            System.out.println("yes");
        }else
            System.out.println("no");

       /*Scanner sc = new Scanner(System.in);
       User usr = new User("kienboeck","daniel");
       Connection conn = null;
       HTTPServer srv = new HTTPServer(conn);
       srv.openConnection("jdbc:postgresql://localhost:5432/swe1db","postgres","admin");
       boolean userexists = srv.userCheck(usr.getUsername());

       if (userexists){
           System.out.println("the user exists");
           System.out.println("DO u wanna delete the user:(y/n)");
           String answer = sc.nextLine();
           if(answer.equals("y") || answer.equals("Y")){
               srv.deleteUser(usr);
           }
           System.out.println("------------------------------------------------------------");
           System.out.println("DO u wanna login with this user:(y/n)");
           String aws = sc.nextLine();
           if(aws.equals("y") || aws.equals("Y")){
               srv.logIn(usr.getUsername(),usr.getPassword());
           }
           System.out.println("------------------------------------------------------------");
           System.out.println("DO u wanna edit the user:(y/n)");
           String answer1 = sc.nextLine();
           if(answer1.equals("y") || answer1.equals("Y")){
               srv.editProfile(usr.getUsername(),"lets go..",":->");
               System.out.println("the content edited!!");
           }

       }else{
           System.out.println("it doesnt exist");
           boolean userCreated = srv.signUp(usr);
           if(userCreated){
               System.out.println("the user created: "+usr.getUsername());

               System.out.println("DO u wanna edit the user:(y/n)");
               String answer = sc.nextLine();
               if(answer.equals("y") || answer.equals("Y")){
                   srv.editProfile(usr.getUsername(),"lets go..",":->");
                   System.out.println("the content edited!!");
               }
           }else{
               System.out.println("could not create it!!");
           }
       }

       srv.closeConnection();*/
    }
}
