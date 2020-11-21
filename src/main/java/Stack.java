
import java.util.ArrayList;
import java.util.Random;

public class Stack{

    private ArrayList<String> stack;

    Stack(){
        stack = new ArrayList<String>();
    }

    public ArrayList<String> getStack() {
        return stack;
    }

    public void addCardsToStack(String cards){
        stack.add(cards);
    }

    public void deleteCardsToStack(String cards){
        stack.remove(cards);
    }

    public void buy_packages(User usr){
        if(usr.getCoins() > 0){
            ArrayList<String> pkg = new ArrayList<>(5);

            Random rand = new Random();
            //save 5 cards to package List
            //first if it comes 1 save a monster and if its a monster save a random one from MonsterBreeds
            //second if it comes 2 save a spell and save a random one from spell
            for(int i=0; i<5; i++){
                int whichOne = rand.nextInt(2) + 1;
                if (whichOne == 1){
                    Random randMonster = new Random();
                    int whichMonster = randMonster.nextInt(7);
                    Card monster = new Monster(MonsterBreeds.getMonster(whichMonster));
                    pkg.add(monster.getName());
                }else if (whichOne == 2){
                    Random randSpell = new Random();
                    int whichSpell = randSpell.nextInt(3);
                    Card spell = new Spell(SpellBreeds.getSpell(whichSpell));
                    pkg.add(spell.getName());
                }
            }
            //add all cards from the package to the stack
            stack.addAll(pkg);
        }
    }

}
