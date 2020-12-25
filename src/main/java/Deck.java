
import java.util.ArrayList;

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
    //take the card to fight against your opponent
    public Card pickCardFromDeck(int pos){
        Card card = deck.get(pos);
        return card;
    }
    public int deckSize(){
        return deck.size();
    }

}
