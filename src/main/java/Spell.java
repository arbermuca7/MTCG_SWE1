
public class Spell extends Card{
    private boolean effectiveness;

    Spell(){
        getName();
        getDamage();
        getElement_type();
    }

    Spell(String name){
        setName(name);
    }

    Spell(String name, int dmg, Elements elem){
        setName(name);
        setDamage(dmg);
        setElement_type(elem);
    }

    public void setEffectiveness(boolean effectiveness) {
        this.effectiveness = effectiveness;
    }

    public boolean getEffectiveness() {
        return effectiveness;
    }
    public void attackEffectiveness(Card p1, Card p2){
        //if water->fire true
        //if fire->normal true
        //if normal->water true
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
        }
        else {
            setEffectiveness(false);
        }
    }
    public void damageCalculate(Card c1, Card c2){
        if (getEffectiveness()){
            if (c1.getElement_type() == Elements.WATER){
                if (c2.getElement_type() == Elements.FIRE){
                    c1.setDamage(c1.getDamage()*2);
                    c2.setDamage(c2.getDamage()/2);
                    System.out.println();
                }
                else if (c2.getElement_type() == Elements.NORMAL){
                    c1.setDamage(c1.getDamage()/2);
                    c2.setDamage(c2.getDamage()*2);
                }
            }
            else if (c1.getElement_type() == Elements.FIRE){
                if (c2.getElement_type() == Elements.WATER){
                    c1.setDamage(c1.getDamage()/2);
                    c2.setDamage(c2.getDamage()*2);
                }
                else if (c2.getElement_type() == Elements.NORMAL){
                    c1.setDamage(c1.getDamage()*2);
                    c2.setDamage(c2.getDamage()/2);
                }
            }
            else if (c1.getElement_type() == Elements.NORMAL){
                if (c2.getElement_type() == Elements.WATER){
                    c1.setDamage(c1.getDamage()*2);
                    c2.setDamage(c2.getDamage()/2);
                }
                else if (c2.getElement_type() == Elements.FIRE){
                    c1.setDamage(c1.getDamage()/2);
                    c2.setDamage(c2.getDamage()*2);
                }
            }
        }else{
            c1.setDamage(c1.getDamage());
            c2.setDamage(c2.getDamage());
        }
    }

}
