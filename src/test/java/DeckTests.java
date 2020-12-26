import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTests {
    @Test
    @DisplayName("Test if we can add cards to deck")
    void testAddCardToDeck(){
        Deck deck = new Deck();
        Card expected_mst = new Monster(MonsterBreeds.GOBLIN.getVal());
        ArrayList<Card> expected = new ArrayList<>();

        expected.add(expected_mst);
        deck.addCardToDeck(expected_mst);
        ArrayList<Card> actual = deck.getDeck();

        //assert
        assertEquals(expected, actual, "The card was successfully saved into the Deck!!");
    }

    @Test
    @DisplayName("Test if we can draw a card from deck")
    void testDrawCardFromDeck(){
        Deck deck = new Deck();
        Card expected_mst = new Monster(MonsterBreeds.GOBLIN.getVal());

        deck.addCardToDeck(expected_mst);
        String expected = deck.pickCardFromDeck(0).getName();

        Card crd = deck.drawCardFromDeck();
        String actual = crd.getName();
        //assert
        assertEquals(expected, actual, "The card was removed from deck and took to hand successfully");
    }

}
