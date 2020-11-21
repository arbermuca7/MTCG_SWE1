import java.util.ArrayList;
import java.util.List;

public enum SpellBreeds{
    SPELL_WATER("SPELL_WATER"),
    SPELL_FIRE("SPELL_FIRE"),
    SPELL_NONE("SPELL_NONE");

    String spell;
    private static List<String> val;
    SpellBreeds(String spell){
        this.spell = spell;
    }

    public String getVal() {
        return this.spell;
    }

    private static void initList(){
        val = new ArrayList<>();
        for (SpellBreeds spell : SpellBreeds.values()){
            val.add(spell.getVal());
        }
    }

    public static String getSpell(int number){
        if(val == null){
            initList();
        }
        return val.get(number);
    }
}
