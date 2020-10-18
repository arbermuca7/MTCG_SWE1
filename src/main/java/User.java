public class User{
    private String username;
    private String password;
    private int coins = 20;
    private int playedGames;
    private int user_status = 100;
    private Deck deck;
    private Card user_card;
    private Battlefield battle;
    private Stack manageStack;

    User(){
        username = "anonym";
        password = "123456";
        coins = 20;
        user_status = 100;
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

    public Card getUser_card() {
        return user_card;
    }

    public void updateCoins(){
        manageStack = new Stack();
        if(manageStack.buy_packages()){
            coins = coins - 4;
        }
    }
    public void scoreboard(User u1){
        System.out.println("-------------------------------------");
        System.out.println(u1.getUsername()+" hat "+u1.getUser_status()+" Punkte!!");
        System.out.println("-------------------------------------");
    }

}
