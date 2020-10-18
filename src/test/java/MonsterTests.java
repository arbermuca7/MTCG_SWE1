import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterTests {

    @Test
    void testGetBreed(){
        Monster monster_crd = new Monster();
        MonsterBreeds monster_type = MonsterBreeds.GOBLIN;

        monster_crd.setBreed(monster_type);
        MonsterBreeds actual = monster_crd.getBreed();

        //assert
        assertEquals(actual, monster_type, "The Monster should be Goblin");
    }


}
