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
        if(getUser().getUser_status() < getOpponent().getUser_status()){
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

    public void printWinner(){
        System.out.println("-------------------------------------");
        System.out.println("The winner is "+getWinner());
        System.out.println("-------------------------------------");
    }

    public void monsterAgainstEachOther(Card m1, Card m2){
        if(m1.getName().equals(MonsterBreeds.GOBLIN.getVal()) && m2.getName().equals(MonsterBreeds.DRAGON.getVal())){
            m1.setDamage(0);
        }
        if(m1.getName().equals(MonsterBreeds.WIZARD.getVal()) && m2.getName().equals(MonsterBreeds.ORK.getVal())){
            m2.setDamage(0);
        }
        if(m1.getName().equals(MonsterBreeds.KNIGHT.getVal()) && m2.getName().equals(SpellBreeds.SPELL_WATER.getVal())){
            m1.setDamage(0);
        }
        if(m1.getName().equals(MonsterBreeds.KRAKEN.getVal()) && (m2.getName().equals(SpellBreeds.SPELL_WATER.getVal())
                || m2.getName().equals(SpellBreeds.SPELL_FIRE.getVal()) || m2.getName().equals(SpellBreeds.SPELL_NONE.getVal()))){
            m2.setDamage(0);
        }
        if(m1.getName().equals(MonsterBreeds.FIREELVES.getVal()) && m2.getName().equals(MonsterBreeds.DRAGON.getVal())){
            int damages = m1.getDamage();
            m1.setDamage(damages);
        }
    }


}
