import java.util.ArrayList;
import java.util.List;

public enum MonsterBreeds {
    GOBLIN("GOBLIN"),
    DRAGON("DRAGON"),
    WIZARD("WIZZARD"),
    ORK("ORK"),
    KNIGHT("KNIGHT"),
    KRAKEN("KRAKEN"),
    FIREELVES("FIREELVES");

    String monster;
    private static List<String> val;
    MonsterBreeds(String monster){
        this.monster = monster;
    }

    public String getVal() {
        return this.monster;
    }

    private static void initList(){
        val = new ArrayList<>();
        for (MonsterBreeds monster : MonsterBreeds.values()){
            val.add(monster.getVal());
        }
    }

    public static String getMonster(int number){
        if(val == null){
            initList();
        }
        return val.get(number);
    }
}
