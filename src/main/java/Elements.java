import java.util.Random;

public enum Elements {
    WATER,
    FIRE,
    NORMAL;

    public static Elements getRandomElement() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
