public class Spell extends Card{

    private boolean effectiveness;
    Spell(){
        getName();
    }
    Spell(String name){
       setName(name);
    }

    public void setEffectiveness(boolean effectiveness) {
        this.effectiveness = effectiveness;
    }

    public boolean getEffectiveness() {
        return effectiveness;
    }
    public void attackEffective(Monster p1, Monster p2){
        //falls water->fire true
        //falls fire->normal true
        //falls normal->water true
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
        else{
            setEffectiveness(false);
        }

    }

}
