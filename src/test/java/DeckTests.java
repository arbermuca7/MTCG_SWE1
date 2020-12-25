import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTests {
    @Test
    void testAddCardToDeck(){
        Deck deck = new Deck();
        Card expected_mst = new Monster(MonsterBreeds.GOBLIN.getVal());
        ArrayList<Card> expected = new ArrayList<>();

        expected.add(expected_mst);
        deck.addCardToDeck(expected_mst);
        ArrayList<Card> actual = deck.getDeck();

        //assert
        assertEquals(expected, actual, "The Monster card was successfully saved into the Deck!!");
    }

}
