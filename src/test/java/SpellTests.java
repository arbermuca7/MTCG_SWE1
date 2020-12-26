import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SpellTests {
    @Test
    @DisplayName("Water is effective against Fire")
    void testSetEffectivenessWater(){
        Spell spell_crd = new Spell();
        Card monster1 = new Monster();
        Card monster2 = new Monster();

        monster1.setElement_type(Elements.WATER);
        monster2.setElement_type(Elements.FIRE);

        boolean expected = true;
        spell_crd.attackEffectiveness(monster1, monster2);
        boolean actual = spell_crd.getEffectiveness();

        //assert
        assertEquals(actual, expected, "They aren't effecting each other");
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
        spell_crd.attackEffectiveness(monster1, monster2);
        boolean actual = spell_crd.getEffectiveness();

        //assert
        assertEquals(actual, expected, "They aren't effecting each other");
    }
    @Test
    @DisplayName("Damage calculator between two cards")
    void testDamageCalculate(){

        Spell b1 = new Spell();
        Card spell1 = new Spell();
        Card spell2 = new Spell();
        Elements elem = Elements.WATER;
        Elements elem1 = Elements.NORMAL;
        spell1.setElement_type(elem);
        spell2.setElement_type(elem1);

        boolean expected = true;
        b1.attackEffectiveness(spell1, spell2);
        boolean actual = b1.getEffectiveness();

        spell1.setDamage(100);
        spell2.setDamage(200);

        b1.damageCalculate(spell1,spell2);

        int actualDamage = spell1.getDamage();
        int expectedDamage = 50;
        //assert
        assertEquals(expectedDamage, actualDamage, "Damage correct calculated");
    }

    @Test
    void testSetName(){
        Card crd = new Spell("SPELL_WATER");

        String expectedName = "SPELL_WATER";
        String actualName = crd.getName();

        //assert
        assertEquals(actualName, expectedName, "The Spell should be Spell_water");
    }
    @Test
    void testSetDamage(){
        Card crd = new Spell("SPELL_FIRE");

        int expectedDamage = 101;
        crd.setDamage(101);
        int actualDamage = crd.getDamage();

        //assert
        assertEquals(actualDamage, expectedDamage, "The Damages should be 101!!!");
    }
    @Test
    void testSetElementType(){
        Card crd = new Spell("SPELL_FIRE");

        Elements expectedElement = Elements.WATER;
        crd.setElement_type(Elements.WATER);
        Elements actualElement = crd.getElement_type();

        //assert
        assertEquals(actualElement, expectedElement, "The Element should be water!!!");
    }

}
