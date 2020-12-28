import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) throws SQLException {
        /*
        User opponent = new User("Norbert Niemand");
        Battlefield battle = new Battlefield();
        battle.startGame(usr,opponent);*/
       Scanner sc = new Scanner(System.in);
       User usr = new User("arberM","hello");
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
               srv.logIn(usr);
           }
           System.out.println("------------------------------------------------------------");

       }else{
           System.out.println("it doesnt exist");
           boolean userCreated = srv.signUp(usr);
           if(userCreated){
               System.out.println("the user created: "+usr.getUsername());

               System.out.println("DO u wanna edit the user:(y/n)");
               String answer = sc.nextLine();
               if(answer.equals("y") || answer.equals("Y")){
                   srv.editProfile(usr,"lets go..",":->");
                   System.out.println("the content edited!!");
               }
           }else{
               System.out.println("could not create it!!");
           }
       }

       srv.closeConnection();

    }
}
