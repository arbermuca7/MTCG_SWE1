import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpellTests {
    @Test
    @DisplayName("Water is effective against Fire")
    void testSetEffectivenessWater(){
        Spell spell_crd = new Spell();
        Card monster1 = new Monster();
        Card monster2 = new Monster();
        Elements elem = Elements.WATER;
        Elements elem1 = Elements.FIRE;
        monster1.setElement_type(elem);
        monster2.setElement_type(elem1);

        boolean expected = TRUE;
        spell_crd.attackEffective(monster1, monster2);
        boolean actual = spell_crd.getEffectiveness();

        //assert
        assertEquals(expected, actual, "Water is effective against Fire");
    }
    @Test
    @Disabled
    @DisplayName("Fire is effective against Normal")
    void testSetEffectivenessFire(){
        Spell spell_crd = new Spell();
        Card monster1 = new Monster();
        Monster monster2 = new Monster();
        Elements elem = Elements.NORMAL;
        Elements elem1 = Elements.FIRE;
        monster1.setElement_type(elem);
        monster2.setElement_type(elem1);

        boolean expected = TRUE;
        spell_crd.attackEffective(monster1, monster2);
        boolean actual = spell_crd.getEffectiveness();

        //assert
        assertEquals(actual, expected, "Fire is effective against Water");
    }
    @Test
    @DisplayName("They aren't effecting")
    void testSetEffectiveness_NotEffecting(){
        Spell spell_crd = new Spell();
        Card monster1 = new Monster();
        Card monster2 = new Monster();
        Elements elem = Elements.WATER;
        Elements elem1 = Elements.WATER;
        monster1.setElement_type(elem);
        monster2.setElement_type(elem1);

        boolean expected = false;
        spell_crd.attackEffective(monster1, monster2);
        boolean actual = spell_crd.getEffectiveness();

        //assert
        assertEquals(actual, expected, "They aren't effecting each other");
    }

}
