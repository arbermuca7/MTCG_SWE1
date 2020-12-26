import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackTests {

    @Test
    void testBuyPackage(){
        User usr = new User("Arber");
        Stack stk = new Stack();

        usr.getStack().buy_packages(usr);
        ArrayList<Card> actual = usr.getStack().getStack();
        int actual_coins = usr.getCoins();
        //assert
        assertEquals(usr.getStack().getStack(), actual, "The Monster card was successfully saved into the Deck!!");
        assertEquals(15,actual_coins);
    }

}
