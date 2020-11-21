
import java.util.ArrayList;
import java.util.Random;

public class Deck extends Stack{

    private ArrayList<String> deck;

    Deck(){
        deck = new ArrayList<String>();
    }

    public ArrayList<String> getDeck() {
        return deck;
    }

    public void addCardToDeck(String card){
        deck.add(card);
    }

    public void deleteCard(String breed){
        deck.remove(breed);
    }

    //save those MonsterBreeds in the Deck
    public void createDeck(){
        Random rand = new Random();
        for (int i = 0; i<4 ; i++){
            int whichMonster = rand.nextInt(getStack().size());
            addCardToDeck(getStack().get(whichMonster));
        }

    }

}
