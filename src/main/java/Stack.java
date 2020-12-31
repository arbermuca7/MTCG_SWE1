
import java.util.ArrayList;
import java.util.Random;

public class Stack{

    private ArrayList<Card> stack;

    Stack(){

        stack = new ArrayList<Card>();
    }

    public ArrayList<Card> getStack() {
        return stack;
    }

    public void addCardsToStack(Card cards){
        stack.add(cards);
    }
    public void addPkg(ArrayList<Card> pkg){
        stack.addAll(pkg);
    }

    public void deleteCardsToStack(Card cards){
        stack.remove(cards);
    }

    public Card pickCardFromStack(int pos){
        Card card = stack.get(pos);
        return card;
    }

    public int stackSize(){
        return stack.size();
    }

    public void buy_packages(){
        //if(usr.getCoins() > 0){
            ArrayList<Card> pkg = new ArrayList<>(5);
            Random rand = new Random();
            //save 5 cards to package List
            for(int i=0; i<5; i++){
                int whichOne = rand.nextInt(2) + 1;
                //first if it comes 1 save a monster and if its a monster save a random one from MonsterBreeds
                if (whichOne == 1){
                    Random randMonster = new Random();
                    int whichMonster = randMonster.nextInt(7);
                    //a variable that takes a random damage of the card
                    //range [60,2000] --> rand((2000-60)+1)+60
                    int damageMonster = randMonster.nextInt(1941)+60;
                    // and another variable that takes a random Element type for the card
                    Elements elem = Elements.getRandomElement();
                    //if the monster Fire elves is then we can define the element before and it is fire
                    if (MonsterBreeds.getMonster(whichMonster).equals(MonsterBreeds.FIREELVES.getVal())){
                        Card monster = new Monster(MonsterBreeds.FIREELVES.getVal(),damageMonster,Elements.FIRE);
                        pkg.add(monster);
                    }else{
                        //if the monster is not a fire elves, then we can define by random what element type should he get
                        Card monster = new Monster(MonsterBreeds.getMonster(whichMonster),damageMonster,elem);
                        pkg.add(monster);
                    }
                //second if it comes 2 save a spell and save a random one from spell
                }else if (whichOne == 2){
                    Random randSpell = new Random();
                    int whichSpell = randSpell.nextInt(3);
                    //a variable that takes a random damage of the card
                    int damageSpell = randSpell.nextInt(1951)+50;

                    if (SpellBreeds.getSpell(whichSpell).equals(SpellBreeds.SPELL_FIRE.getVal())){
                        Card spell = new Spell(SpellBreeds.SPELL_FIRE.getVal(),damageSpell,Elements.FIRE);
                        pkg.add(spell);
                    }
                    else if (SpellBreeds.getSpell(whichSpell).equals(SpellBreeds.SPELL_NONE.getVal())){
                        Card spell = new Spell(SpellBreeds.SPELL_NONE.getVal(),damageSpell,Elements.NORMAL);
                        pkg.add(spell);
                    }
                    else if (SpellBreeds.getSpell(whichSpell).equals(SpellBreeds.SPELL_WATER.getVal())){
                        Card spell = new Spell(SpellBreeds.SPELL_WATER.getVal(),damageSpell,Elements.WATER);
                        pkg.add(spell);
                    }
                }
            }
            //add all cards from the package to the stack
            stack.addAll(pkg);
            //update the coins subtracting 4 coins, when you buy a package of cards
            //usr.updateCoins();
       // }
    }

}
