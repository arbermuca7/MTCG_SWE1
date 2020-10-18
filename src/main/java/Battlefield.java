import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Battlefield {

    private User user;
    private User opponent;
    private User winner;

    Battlefield(){}
    public User getUser(){
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public User getOpponent() {
        return opponent;
    }

    public void setOpponent(User opponent) {
        this.opponent = opponent;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public void currWinner(){
        if(user.getUser_status() < user.getUser_status()){
            setWinner(opponent);
        }else{
            setWinner(user);
        }
    }

    public void elo_points(User u1, User u2) {
        u1 = getUser();
        u2 = getOpponent();
        User win = getWinner();
        if (u1 == win) {
            u1.setUser_status(u1.getUser_status() + 3);
        } else if (u1 != win){
            u1.setUser_status(u1.getUser_status() - 5);
        }

        if (u2 == win){
            u2.setUser_status(u2.getUser_status() + 3);
        }else if (u2 != win){
            u2.setUser_status(u2.getUser_status() - 5);
        }
    }
    //take some random MonsterBreeds
    private MonsterBreeds randomMonster() {
        int pick = new Random().nextInt(MonsterBreeds.values().length);
        return MonsterBreeds.values()[pick];
    }
    //save those MonsterBreeds in the Deck
    public void createDeck(User usr){
        Monster monsterCard = new Monster(randomMonster());
        usr.getDeck().addCardToDeck(monsterCard);
    }
}