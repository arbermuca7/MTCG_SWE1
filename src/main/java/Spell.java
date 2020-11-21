import java.util.Random;

public class Spell extends Card{

    private boolean effectiveness;

    Spell(){}
    Spell(String name){

        setName(name);
    }

    public void setEffectiveness(boolean effectiveness) {
        this.effectiveness = effectiveness;
    }

    public boolean getEffectiveness() {
        return effectiveness;
    }

    public void attackEffectiveness(Card p1, Card p2){
        //falls water->fire true
        //falls fire->normal true
        //falls normal->water true
        //and vise versa also

        if(p1.getElement_type() == Elements.WATER && p2.getElement_type() == Elements.FIRE){
            setEffectiveness(true);
        }
        else if(p1.getElement_type() == Elements.FIRE && p2.getElement_type() == Elements.NORMAL){
            setEffectiveness(true);
        }
        else if(p1.getElement_type() == Elements.NORMAL && p2.getElement_type() == Elements.WATER){
            setEffectiveness(true);
        }
        else if(p2.getElement_type() == Elements.WATER && p1.getElement_type() == Elements.FIRE){
            setEffectiveness(true);
        }
        else if(p2.getElement_type() == Elements.FIRE && p1.getElement_type() == Elements.NORMAL){
            setEffectiveness(true);
        }
        else if(p2.getElement_type() == Elements.NORMAL && p1.getElement_type() == Elements.WATER){
            setEffectiveness(true);
        }else {
            setEffectiveness(false);
        }
    }
    public void damageCalculate(Card c1, Card c2){
        if (getEffectiveness()){
            if (c1.getElement_type() == Elements.WATER){
                if (c2.getElement_type() == Elements.FIRE){
                    c1.setDamage(c1.getDamage()*2);
                }
                else if (c2.getElement_type() == Elements.NORMAL){
                    c1.setDamage(c1.getDamage()/2);
                }
            }
            else if (c1.getElement_type() == Elements.FIRE){
                if (c2.getElement_type() == Elements.WATER){
                    c1.setDamage(c1.getDamage()/2);
                }
                else if (c2.getElement_type() == Elements.NORMAL){
                    c1.setDamage(c1.getDamage()*2);
                }
            }
            else if (c1.getElement_type() == Elements.NORMAL){
                if (c2.getElement_type() == Elements.WATER){
                    c1.setDamage(c1.getDamage()*2);
                }
                else if (c2.getElement_type() == Elements.FIRE){
                    c1.setDamage(c1.getDamage()/2);
                }
            }
        }else{
            //compare between damage value
            if (c1.getDamage() > c2.getDamage()){
                //card c1 goes to the user 1
            }else{
                //card c2 goes to the user 2
            }
        }

    }

}
