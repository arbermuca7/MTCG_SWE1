import java.util.List;

public class User{
    private String username;
    private String password;
    private int coins = 20;
    private int playedGames;
    //wie viele Rounds gespielt.
    private List<Battlefield> rounds;
    private int user_status = 100;
    private Deck deck;
    private Battlefield battle;
    private Stack manageStack;

    User(){
        username = "anonym";
        password = "123456";
    }
    User(String user){
        username = user;
    }
    User(String user, String pwd){
        username = user;
        password = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCoins() {
        return coins;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {

        this.user_status = user_status;
    }

    public Deck getDeck() {
        return deck;
    }

    public void updateCoins(){
        System.out.println("****** You bought a card package!!!! ******");
        coins -= 4;
    }
    public void scoreboard(User u1){
        System.out.println("-------------------------------------");
        System.out.println(u1.getUsername()+" has "+u1.getUser_status()+" points!!");
        System.out.println("-------------------------------------");
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }
}
