import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterTests {

    @Test
    void testSetName(){
        Card crd = new Monster("GOBLIN");

        String expectedName = "GOBLIN";
        String actualName = crd.getName();

        //assert
        assertEquals(actualName, expectedName, "The Monster should be Goblin");
    }
    @Test
    void testSetDamage(){
        Card crd = new Monster("GOBLIN");
        Card crd1 = new Monster("DRAGON");

        int expectedDamage = 101;
        crd.setDamage(101);
        int actualDamage = crd.getDamage();

        //assert
        assertEquals(actualDamage, expectedDamage, "The Damages should be 101!!!");
    }
    @Test
    void testSetElementType(){
        Card crd = new Monster("GOBLIN");

        Elements expectedElement = Elements.WATER;
        crd.setElement_type(Elements.WATER);
        Elements actualElement = crd.getElement_type();

        //assert
        assertEquals(actualElement, expectedElement, "The Element should be water!!!");
    }

}
