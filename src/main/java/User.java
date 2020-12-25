
public class User{
    private String username;
    private String password;
    private int coins = 20;
    private int user_status = 100;
    private Deck deck;
    private Stack stack;

    User(){
        username = "anonym";
        password = "123456";
        deck = new Deck();
        stack = new Stack();
    }

    User(String user){
        username = user;
        deck = new Deck();
        stack = new Stack();
    }

    User(String user, String pwd){
        username = user;
        password = pwd;
        deck = new Deck();
        stack = new Stack();
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

    public Stack getStack() {
        return stack;
    }

    public void updateCoins(){
        System.out.println("****** You bought a card package!!!! ******");
        coins -= 5;
    }
    public void scoreboard(){
        System.out.println("------------------------------------------------------------");
        System.out.println("-->" + getUsername() + " has " + getUser_status()+" points!!");
        System.out.println("------------------------------------------------------------");
    }

}
