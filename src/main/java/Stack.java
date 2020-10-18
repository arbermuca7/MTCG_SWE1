
import java.util.Collections;
import java.util.ArrayList;

public class Stack {
    private ArrayList<Monster> stack;
    private ArrayList<Monster> store;
    private Monster cards;

    Stack(){
        stack = new ArrayList<Monster>();
    }

    public ArrayList<Monster> getStack() {
        return stack;
    }

    public ArrayList<Monster> getStore() {
        return store;
    }

    public boolean buy_packages(){
        ArrayList<Monster> pkg = new ArrayList<Monster>(5);
        for(int j = 0; j<5; j++)
            pkg.add(j, cards);

        for(int i = 0; i<pkg.size(); i++)
            stack.addAll(i,pkg);

        return true;
    }

}
