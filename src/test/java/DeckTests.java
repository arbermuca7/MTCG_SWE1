import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTests {
    @Test
    void testAddCardToDeck(){
        Monster monster_type = new Monster("GOBLIN");
        Deck deck = new Deck();
        String expected_mst = MonsterBreeds.GOBLIN.getVal();
        ArrayList<String> expected = new ArrayList<>();

        expected.add(expected_mst);
        deck.addCardToDeck(monster_type.getName());
        ArrayList<String> actual = deck.getDeck();

        //assert
        assertEquals(expected, actual, "The Monster card was successfully saved into the Deck!!");
    }
    @Test
    void testDeleteCard_fromDeck(){
        Monster monster_type = new Monster("GOBLIN");
        Deck deck = new Deck();

        ArrayList<String> expected = new ArrayList<>();

        deck.addCardToDeck(monster_type.getName());
        deck.deleteCard(monster_type.getName());

        ArrayList<String> actual = deck.getDeck();

        //assert
        assertEquals(expected, actual, "The card was deleted successfully from the deck!!");
    }
}
