
import java.util.Collections;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Monster> deck;

    Deck(){
        deck = new ArrayList<Monster>(4);
    }

    public ArrayList<Monster> getDeck() {
        return deck;
    }
    public void addCardToDeck(Monster card_monster){
        deck.add(card_monster);
    }

    public Monster deleteCard(){
        Monster card_monster = deck.get(0);
        deck.remove(0);
        return card_monster;
    }

}
