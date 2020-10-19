
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


}
