/*
*
 */
package Classes;

/**
 *
 * @author Markus
 */

public class Player {

    private String name;
    private int turn;
    private int ones;
    private int twos;
    private int threes;
    private int fours;
    private int fives;
    private int sixes;
    private int bonus;
    private int onepair;
    private int twopair;
    private int threeOfAKind;
    private int fourOfAKind;
    private int fullHouse;
    private int smallStraight;
    private int largeStraight;
    private int yatzy;
    private int change;
    private int round;

    public Player(String name) {
        this.name = name;
        this.turn = 0;
        this.ones = 0;
        this.twos = 0;
        this.threes = 0;
        this.fours = 0;
        this.fives = 0;
        this.sixes = 0;
        this.bonus = 0;
        this.threeOfAKind = 0;
        this.fourOfAKind = 0;
        this.fullHouse = 0;
        this.smallStraight = 0;
        this.largeStraight = 0;
        this.yatzy = 0;
        this.change = 0;      
        this.onepair = 0;
        this.twopair = 0;
        this.round = 0;
    }
    public Player(){
        
    }
    

    public int getRound() {
        return round;
    }

    public void setRound() {
        this.round+=1;
    }
    

    public int getOnepair() {
        return onepair;
    }

    public void setOnepair(int onepair) {
        this.onepair = onepair;
    }

    public int getTwopair() {
        return twopair;
    }

    public void setTwopair(int twopair) {
        this.twopair = twopair;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public int getTurn() {
        return turn;
    }

    
    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getOnes() {
        return ones;
    }

    
    public void setOnes(int ones) {
        this.ones = ones;
    }

    public int getTwos() {
        return twos;
    }

    public void setTwos(int twos) {
        this.twos = twos;
    }

    public int getThrees() {
        return threes;
    }

    public void setThrees(int threes) {
        this.threes = threes;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getFives() {
        return fives;
    }

    public void setFives(int fives) {
        this.fives = fives;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getBonus() {
        if (ones+twos+threes+fours+fives+sixes > 62)
        return 50;
        else
        return 0;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getThreeOfAKind() {
        return threeOfAKind;
    }

    public void setThreeOfAKind(int threeOfAKind) {
        this.threeOfAKind = threeOfAKind;
    }

    public int getFourOfAKind() {
        return fourOfAKind;
    }

    public void setFourOfAKind(int fourOfAKind) {
        this.fourOfAKind = fourOfAKind;
    }

    public int getFullHouse() {
        return fullHouse;
    }

    public void setFullHouse(int fullHouse) {
        this.fullHouse = fullHouse;
    }

    public int getSmallStraight() {
        return smallStraight;
    }

    public void setSmallStraight(int smallStraight) {
        this.smallStraight = smallStraight;
    }

    public int getLargeStraight() {
        return largeStraight;
    }

    public void setLargeStraight(int largeStraight) {
        this.largeStraight = largeStraight;
    }

    public int getYatzy() {
        return yatzy;
    }

    public void setYatzy(int yatzy) {
        this.yatzy = yatzy;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public int getTotal() {
        return (ones+twos+threes+fours+fives+sixes+this.getBonus()+change+yatzy+largeStraight+smallStraight+fullHouse+fourOfAKind+threeOfAKind);   
    }
    public int getSum(){
        return (ones+twos+threes+fours+fives+sixes);
    }
}
