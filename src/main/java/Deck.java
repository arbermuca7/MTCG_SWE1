
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<MonsterBreeds> deck;

    Deck(){

        deck = new ArrayList<MonsterBreeds>(4);
    }

    public ArrayList<MonsterBreeds> getDeck() {

        return deck;
    }

    public void addCardToDeck(MonsterBreeds card_monster){
        deck.add(card_monster);
    }

    public void deleteCard(MonsterBreeds breed){
        deck.remove(breed);
    }
    //take some random MonsterBreeds
    private MonsterBreeds randomMonster() {
        int pick = new Random().nextInt(MonsterBreeds.values().length);
        return MonsterBreeds.values()[pick];
    }

    //save those MonsterBreeds in the Deck
    /*public void createDeck(User usr, MonsterBreeds b_breed){
        Monster monsterCard = new Monster(b_breed);
        addCardToDeck(monsterCard.getBreed());
    }*/

}
