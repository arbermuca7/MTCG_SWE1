public abstract class Card {
    private String name;
    private int damage;
    private Elements element_type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Elements getElement_type() {
        return element_type;
    }

    public void setElement_type(Elements element_type) {
        this.element_type = element_type;
    }
}
