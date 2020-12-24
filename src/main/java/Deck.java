
import java.util.ArrayList;
import java.util.Random;

public class Deck extends Stack{

    private ArrayList<Card> deck;

    Deck(){
        deck = new ArrayList<>();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void addCardToDeck(Card card){
        deck.add(card);
    }

    //take a card from the deck if you loose the round
    public Card drawCardFromDeck(){
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }
    /*
    //save those MonsterBreeds in the Deck
    //in class battlefield???
    public void createDeck(){
        Random rand = new Random();
        for (int i = 0; i<4 ; i++){
            int whichMonster = rand.nextInt(getStack().size());
            addCardToDeck(getStack().get(whichMonster));
        }

    }*/

}
