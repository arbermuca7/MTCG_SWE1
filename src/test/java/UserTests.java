import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class UserTests {
    @Test
    @DisplayName("Update Coins of the user")
    void testUpdateCoins(){
        User user1 = new User("Norbert");

        int expected = 15;
        user1.updateCoins();
        int actual = user1.getCoins();
        //assert
        assertEquals(expected, actual, "Norbert has bought a pakage of cards!!");
    }


}
