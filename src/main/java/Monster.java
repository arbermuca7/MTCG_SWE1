
public class Monster extends Card {

    private MonsterBreeds breed;

    Monster (){}
    Monster(MonsterBreeds breed){
        setBreed(breed);
    }

    public MonsterBreeds getBreed() {

        return breed;
    }

    public void setBreed(MonsterBreeds breed) {

        this.breed = breed;
    }
    public void GoblinAgainstDragon(Monster m1, Monster m2){
        if(m1.getBreed() == MonsterBreeds.GOBLIN && m2.getBreed() == MonsterBreeds.DRAGON){
            int damages = m1.getDamage();
            m1.setDamage(damages);
        }
    }
    public void WizzardAgainstOrks(Monster m1, Monster m2){
        if(m1.getBreed() == MonsterBreeds.WIZARD && m2.getBreed() == MonsterBreeds.ORK){
            int damages = m1.getDamage();
            m1.setDamage(damages);
        }
    }
    public void KnightAgainstWaterspells(Monster m1, Spell m2){
        if(m1.getBreed() == MonsterBreeds.KNIGHT && m2.getBreed() == SpellBreeds.SPELL_WATER){
            m1.setDamage(0);
        }
    }
    public void KrakenAgainstSpells(Monster m1, Spell m2){
        if(m1.getBreed() == MonsterBreeds.KRAKEN && (m2.getBreed() == SpellBreeds.SPELL_WATER || m2.getBreed() == SpellBreeds.SPELL_FIRE || m2.getBreed() == SpellBreeds.SPELL_NONE)){
            int damages = m1.getDamage();
            m1.setDamage(damages);
        }
    }
    public void FireElvesAgainstDragons(Monster m1, Monster m2){
        if(m1.getBreed() == MonsterBreeds.FIREELVES && m2.getBreed() == MonsterBreeds.DRAGON){
            int damages = m1.getDamage();
            m1.setDamage(damages);
        }
    }


}
