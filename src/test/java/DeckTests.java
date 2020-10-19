import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTests {
    @Test
    void testAddCardToDeck(){
        Monster monster_type = new Monster();
        Deck deck = new Deck();

        MonsterBreeds expected_mst = MonsterBreeds.GOBLIN;
        ArrayList<MonsterBreeds> expected = new ArrayList<>();
        expected.add(expected_mst);

        monster_type.setBreed(MonsterBreeds.GOBLIN);
        deck.addCardToDeck(monster_type.getBreed());
        ArrayList<MonsterBreeds> actual = deck.getDeck();

        //assert
        assertEquals(expected, actual, "The Monstercard was successfully saved into the Deck!!");
    }
    @Test
    void testDeleteCard_fromDeck(){
        Monster monster_type = new Monster();
        Deck deck = new Deck();

        ArrayList<MonsterBreeds> expected = new ArrayList<>();

        monster_type.setBreed(MonsterBreeds.GOBLIN);
        deck.addCardToDeck(monster_type.getBreed());
        deck.deleteCard(monster_type.getBreed());
        ArrayList<MonsterBreeds> actual = deck.getDeck();

        //assert
        assertEquals(expected, actual, "The card was deleted successfully from the deck!!");
    }
}
