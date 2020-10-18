import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattlefieldTests {
    @Test
    @DisplayName("Norbert is winner")
    void testEloPoints_ifNorbertWINS(){
        Battlefield battle = new Battlefield();
        User user1 = new User("Norbert");
        User user2 = new User("Michael");
        battle.setUser(user1);
        battle.setOpponent(user2);
        battle.setWinner(user1);
        battle.elo_points(user1, user2);

        int expected = 103;
        int actual = user1.getUser_status();

        //assert
        assertEquals(expected, actual, "Norbert won, and took +3 Points!!");
    }
    @Test
    @DisplayName("Michael is loser")
    void testEloPoints_ifMichaelLOSES(){
        Battlefield battle = new Battlefield();
        User user1 = new User("Norbert");
        User user2 = new User("Michael");
        battle.setUser(user1);
        battle.setOpponent(user2);
        battle.setWinner(user1);
        battle.elo_points(user1, user2);

        int expected = 95;
        int actual = user2.getUser_status();

        //assert
        assertEquals(expected, actual, "Michael lost, and took -5 Points!!");
    }
}
