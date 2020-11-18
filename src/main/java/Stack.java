
import java.util.Collections;
import java.util.ArrayList;

public class Stack {
    private ArrayList<Card> stack;
    private Card cards;

    Stack(){
        stack = new ArrayList<Card>();
    }

    public ArrayList<Card> getStack() {
        return stack;
    }

    public boolean buy_packages(){
        ArrayList<Card> pkg = new ArrayList<>(5);
        for(int j = 0; j<5; j++)
            pkg.add(j, cards);

        for(int i = 0; i<pkg.size(); i++){
            stack.addAll(i,pkg);
            return true;
        }
        return false;
    }

}
