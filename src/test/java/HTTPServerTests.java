import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPServerTests {
    @Test
    @DisplayName("Check if the user exists")
    void testUserCheck() throws SQLException {
        User user1 = new User("arber");
        Connection con = null;
        HTTPServer srv = new HTTPServer(con);
        srv.openConnection("jdbc:postgresql://localhost:5432/swe1db","postgres","admin");
        boolean actual = srv.userCheck(user1.getUsername());
        boolean expected = true;
        //assert
        assertEquals(expected, actual, "The user exists");
        srv.closeConnection();
    }
    @Test
    @DisplayName("Check if the user can login")
    void testUserLogin() throws SQLException {
        User user1 = new User("arber","muca11");
        Connection con = null;
        HTTPServer srv = new HTTPServer(con);
        srv.openConnection("jdbc:postgresql://localhost:5432/swe1db","postgres","admin");
        boolean actual = srv.logIn(user1.getUsername(),user1.getPassword());
        boolean expected = true;
        //assert
        assertEquals(expected, actual, "The user was logged in successfully");
        srv.closeConnection();
    }
    @Test
    @DisplayName("Check if the user can login")
    void testUserSignup() throws SQLException {
        User user1 = new User("norbert","niemand");
        Connection con = null;
        HTTPServer srv = new HTTPServer(con);
        srv.openConnection("jdbc:postgresql://localhost:5432/swe1db","postgres","admin");
        boolean actual = srv.signUp(user1);
        //boolean actual = true;
        boolean expected = true;
        //assert
        assertEquals(expected, actual, "The user was logged in successfully");
        srv.closeConnection();
    }
    @Test
    @DisplayName("Test if the method take the first line of header")
    void readFirstLineHeaderTest(){
        //Socket s = new Socket();
        //List<String>messages = new ArrayList<>();
        //HTTPServer httpSrv = new HTTPServer(s,messages);

        String request = " GET /messages HTTP/1.1 \r\n Content-Type: text/plain \r\n Accept: */* \r\n\r\n hallo";

        String methodExp = "GET";
        String pathExp = "/messages";
        String versionExp = "HTTP/1.1";

        String readLine = HTTPServer.readFirstLineHeader(request);

        String[] headerPartSplit = readLine.split(",");
        //get the method
        String methodAct = headerPartSplit[0];
        //get the path
        String pathAct = headerPartSplit[1];
        //get the version
        String versionAct = headerPartSplit[2];

        assertEquals(methodExp,methodAct,"Same method!!!");
        assertEquals(pathExp,pathAct, "same path!!!");
        assertEquals(versionExp,versionAct, "Same version!!!");

    }

    @Test
    @DisplayName("Test if the path is spliten correctly to take the id ")
    void splitPathTest(){

        String path = "/messages/1";

        String ID_expected = "1";
        String ID_actual = HTTPServer.pathSpliter(path);

        assertEquals(ID_expected,ID_actual, "the ID correctly split!!");

    }
}
