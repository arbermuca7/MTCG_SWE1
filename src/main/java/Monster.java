
public class Monster extends Card {

    Monster(){
        getName();
        getDamage();
        getElement_type();
    }

    Monster (String name){
        setName(name);
    }

    Monster(String name, int dmg, Elements elem){
        setName(name);
        setDamage(dmg);
        setElement_type(elem);
    }
}
