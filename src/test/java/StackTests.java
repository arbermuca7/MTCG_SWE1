import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackTests {

    @Test
    @DisplayName("Add cards into the stack")
    void testAddCardtoStack(){
        User usr = new User("Arber");
        Card crd = new Monster(MonsterBreeds.DRAGON.getVal(),1020,Elements.FIRE);
        usr.getStack().addCardsToStack(crd);

        String actual = usr.getStack().pickCardFromStack(0).getName();
        String expected = MonsterBreeds.DRAGON.getVal();
        //assert
        assertEquals(expected, actual, "The card was successfully saved into the Stack!!");
    }


    @Test
    @DisplayName("Buy a package of cards")
    void testBuyPackage(){
        User usr = new User("Arber");

        usr.getStack().buy_packages(usr);
        ArrayList<Card> actual = usr.getStack().getStack();
        int actual_coins = usr.getCoins();
        //assert
        assertEquals(usr.getStack().getStack(), actual, "The Package was successfully bought!!");
        assertEquals(15,actual_coins);
    }


}
