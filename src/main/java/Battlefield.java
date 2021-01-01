import java.util.Random;

public class Battlefield {
    private User user;
    private User opponent;
    private User winner;
    private User roundWinner;

    private int playedGames;
    private boolean pureMonster;

    Battlefield(){

    }
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

    public void setRoundWinner(User win){
        this.roundWinner = win;
    }
    public User getRoundWinner() {
        return roundWinner;
    }

    public int getPlayedGames() {
        return playedGames;
    }
    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public boolean getPureMonsters(){
        return pureMonster;
    }
    public void setPureMonster(boolean answer){
        this.pureMonster = answer;
    }

    public void currWinner(){
        if(getUser().getUser_status() < getOpponent().getUser_status()){
            setWinner(opponent);
        }else{
            setWinner(user);
        }
    }
    public void elo_points(User usr1, User usr2) {
        setUser(usr1);
        User u1 = getUser();
        setOpponent(usr2);
        User u2 = getOpponent();
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

    public void printRoundWinner(){
        System.out.println("--> Round: " + getPlayedGames());
        System.out.println("--> The winner is " + getRoundWinner().getUsername());
        System.out.println("------------------------------------------------------------");
    }
    //save those MonsterBreeds in the Deck
    public void createDeck(User user){
        Random rand = new Random();
        for (int i = 0; i<4 ; i++){
            int whichMonster = rand.nextInt(user.getStack().stackSize());
            user.getDeck().addCardToDeck(user.getStack().pickCardFromStack(whichMonster));
        }

    }

    public void monsterAgainstEachOther(Card m1, Card m2){
        if(m1.getName().equals(MonsterBreeds.GOBLIN.getVal()) && m2.getName().equals(MonsterBreeds.DRAGON.getVal())){
            m1.setDamage(0);
            setPureMonster(true);
        }
        else if(m1.getName().equals(MonsterBreeds.WIZARD.getVal()) && m2.getName().equals(MonsterBreeds.ORK.getVal())){
            m2.setDamage(0);
            setPureMonster(true);
        }
        else if(m1.getName().equals(MonsterBreeds.KNIGHT.getVal()) && m2.getName().equals(SpellBreeds.SPELL_WATER.getVal())){
            m1.setDamage(0);
            setPureMonster(true);
        }
        else if(m1.getName().equals(MonsterBreeds.KRAKEN.getVal()) && (m2.getName().equals(SpellBreeds.SPELL_WATER.getVal())
                || m2.getName().equals(SpellBreeds.SPELL_FIRE.getVal()) || m2.getName().equals(SpellBreeds.SPELL_NONE.getVal()))){
            m2.setDamage(0);
            setPureMonster(true);
        }
        else if(m1.getName().equals(MonsterBreeds.FIREELVES.getVal()) && m2.getName().equals(MonsterBreeds.DRAGON.getVal())){
            int damages = m1.getDamage();
            m1.setDamage(damages);
            setPureMonster(true);
        }else if(m1.getName().equals(MonsterBreeds.EAGLE.getVal()) && (m2.getName().equals(SpellBreeds.SPELL_WATER.getVal())
                || m2.getName().equals(SpellBreeds.SPELL_FIRE.getVal()) || m2.getName().equals(SpellBreeds.SPELL_NONE.getVal())
                || m2.getName().equals(MonsterBreeds.DRAGON.getVal()) || m2.getName().equals(MonsterBreeds.GOBLIN.getVal())
                || m2.getName().equals(MonsterBreeds.WIZARD.getVal()) || m2.getName().equals(MonsterBreeds.ORK.getVal())
                || m2.getName().equals(MonsterBreeds.KNIGHT.getVal()) || m2.getName().equals(MonsterBreeds.KRAKEN.getVal())
                || m2.getName().equals(MonsterBreeds.FIREELVES.getVal()))){
            m2.setDamage(0);
            setPureMonster(true);
        }
        else if(m2.getName().equals(MonsterBreeds.GOBLIN.getVal()) && m1.getName().equals(MonsterBreeds.DRAGON.getVal())){
            m2.setDamage(0);
            setPureMonster(true);
        }
        else if(m2.getName().equals(MonsterBreeds.WIZARD.getVal()) && m1.getName().equals(MonsterBreeds.ORK.getVal())){
            m1.setDamage(0);
            setPureMonster(true);
        }
        else if(m2.getName().equals(MonsterBreeds.KNIGHT.getVal()) && m1.getName().equals(SpellBreeds.SPELL_WATER.getVal())){
            m2.setDamage(0);
            setPureMonster(true);
        }
        else if(m2.getName().equals(MonsterBreeds.KRAKEN.getVal()) && (m1.getName().equals(SpellBreeds.SPELL_WATER.getVal())
                || m1.getName().equals(SpellBreeds.SPELL_FIRE.getVal()) || m1.getName().equals(SpellBreeds.SPELL_NONE.getVal()))){
            m1.setDamage(0);
            setPureMonster(true);
        }
        else if(m2.getName().equals(MonsterBreeds.FIREELVES.getVal()) && m1.getName().equals(MonsterBreeds.DRAGON.getVal())){
            int damages = m1.getDamage();
            m2.setDamage(damages);
            setPureMonster(true);
        }else if(m2.getName().equals(MonsterBreeds.EAGLE.getVal()) && (m1.getName().equals(SpellBreeds.SPELL_WATER.getVal())
                || m1.getName().equals(SpellBreeds.SPELL_FIRE.getVal()) || m1.getName().equals(SpellBreeds.SPELL_NONE.getVal())
                || m1.getName().equals(MonsterBreeds.DRAGON.getVal()) || m1.getName().equals(MonsterBreeds.GOBLIN.getVal())
                || m1.getName().equals(MonsterBreeds.WIZARD.getVal()) || m1.getName().equals(MonsterBreeds.ORK.getVal())
                || m1.getName().equals(MonsterBreeds.KNIGHT.getVal()) || m1.getName().equals(MonsterBreeds.KRAKEN.getVal())
                || m1.getName().equals(MonsterBreeds.FIREELVES.getVal()))){
            m1.setDamage(0);
            setPureMonster(true);
        }
        else {
            setPureMonster(false);
        }
    }
    //compare damages knowing if there are two pure monsters fighting or not?
    public void compareDamage(Card c1, Card c2){
        //save the default value first here then when the round ends, set it as it was.
        int damageCard1 = c1.getDamage();
        int damageCard2 = c2.getDamage();
        //we see if there is a fight between pure monsters
        monsterAgainstEachOther(c1, c2);
        //if we have a fight between pure monsters then the element type doesn't play a role
        if(getPureMonsters()){
            //compare between damage value only
            if (c1.getDamage() > c2.getDamage()){
                setRoundWinner(user);
            }else{
                setRoundWinner(opponent);
            }
        }else{
            //look if the attack is effective(it means: element types are different for the different cards)
            Spell sp = new Spell();
            sp.attackEffectiveness(c1,c2);
            //calculate the damages (when we fight with different types of elements)
            sp.damageCalculate(c1,c2);
            //compare between the damages to define who wins
            if (c1.getDamage() > c2.getDamage()){
                //set the round winner
                setRoundWinner(user);
            }else{
                setRoundWinner(opponent);
            }
        }
        //reset the damages as they were in the beginning
        c1.setDamage(damageCard1);
        c2.setDamage(damageCard2);
    }
    //play Method which defines who wins which round and the game
    public void startGame(User player, User againster){
        //define Users
        setUser(player);
        setOpponent(againster);
        //buy a package with cards
        player.getStack().buy_packages(player);
        createDeck(player);
        System.out.println("------------------------------------------------------------");
        System.out.println("--> "+player.getUsername()+" bought a card package!!");
        System.out.println("--> His Cards are : "+player.getStack().getStack());
        System.out.println("--> His Deck  is : "+player.getDeck().getDeck());

        for (int i = 0; i< player.getDeck().deckSize();i++){
            System.out.println("--> His Deck  is : "+player.getDeck().pickCardFromDeck(i).getName()+","
                    +player.getDeck().pickCardFromDeck(i).getDamage()+","+player.getDeck().pickCardFromDeck(i).getElement_type());
        }

        againster.getStack().buy_packages(againster);
        createDeck(againster);
        System.out.println("--> "+againster.getUsername()+" bought a card package!!");
        System.out.println("--> His Cards are : "+againster.getStack().getStack().toString());
        System.out.println("--> His Deck  is : "+againster.getDeck().getDeck().toString());
        for (int i = 0; i< againster.getDeck().deckSize();i++){
            System.out.println("--> His Deck  is : "+againster.getDeck().pickCardFromDeck(i).getName()+","
                    +againster.getDeck().pickCardFromDeck(i).getDamage()+","+againster.getDeck().pickCardFromDeck(i).getElement_type());
        }
        System.out.println("------------------------------------------------------------");
        //as long that no more card are in the deck or the rounds are > 100 then stop the game
        int counter = 0;
        while(player.getDeck().deckSize()>0 && againster.getDeck().deckSize()>0 && getPlayedGames()<100){
            //pick the cards for the respective User in their deck
            Card card1 = player.getDeck().drawCardFromDeck();
            cardPicked(player,card1);

            Card card2 = againster.getDeck().drawCardFromDeck();
            cardPicked(againster,card2);

            //compares the Damage and set the round winner
            compareDamage(card1,card2);
            printRoundWinner();
            // and send the card to the other user deck
            if(getRoundWinner().equals(player)){
                player.getDeck().addCardToDeck(card1);
                player.getDeck().addCardToDeck(card2);
            }
            else if(getRoundWinner().equals(againster)){
                againster.getDeck().addCardToDeck(card2);
                againster.getDeck().addCardToDeck(card1);
            }
            // increment the rounds played
            counter++;
            setPlayedGames(getPlayedGames()+1);
        }
        if (player.getDeck().deckSize() > againster.getDeck().deckSize()){
            setWinner(player);
        }else {
            setWinner(againster);
        }
        //calculate the elo points for the Fighter
        elo_points(player, againster);
        battleLog(player, againster);

    }
    public void battleLog(User player, User againster){
        //a detailed Log about the individual matches
        player.scoreboard();
        againster.scoreboard();
        currWinner();
        System.out.println("------------------------------------------------------------");
        System.out.println("--> Winner of this game is: "+getWinner().getUsername());
        System.out.println("--> In this game were "+getPlayedGames()+" round(s) played!!");
        System.out.println("--> "+ player.getUsername() + " got " + player.getDeck().deckSize() + " card(s) on his deck!!");
        System.out.println("--> "+ againster.getUsername() + " got " + againster.getDeck().deckSize() + " card(s) on his deck!!");
        System.out.println("--> "+ player.getUsername() + " got " + player.getCoins() + " coins left!!");
        System.out.println("--> "+ againster.getUsername() + " got " + againster.getCoins() + " coins left!!");
        System.out.println("------------------------------------------------------------");
    }

    public void cardPicked(User usr, Card card){
        System.out.println("--> "+ usr.getUsername() + " picked: ");
        System.out.println("   >> Name: " + card.getName() + "\n   >> Element: " + card.getElement_type() +"\n   >> Damage: " +card.getDamage());
        System.out.println("------------------------------------------------------------");
    }
}
