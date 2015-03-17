/*
 *
 */
package Classes;

import static Classes.Dice.random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Markus
 */
@ManagedBean
@ApplicationScoped
public class GameList {

    List<Game> gamelist;

    public GameList(List<Game> gameList) {
        this.gamelist = gameList;
    }

    public GameList() {
        gamelist = new ArrayList();
        gamelist.add(new Game());
        gamelist.add(new Game("Otto", "Niilo"));
        gamelist.add(new Game("Gideon", "Bingo"));
    }
    public void RemoveGame(Game game){
        gamelist.remove(game);
    }

    public List<Game> getGamelist() {
        return gamelist;
    }

    public void setGamelist(List<Game> gameList) {
        this.gamelist = gameList;
    }

    public void createGame(String name1, String name2) {
        this.gamelist.add(new Game(name1, name2));
    }

    public void saveToOnes(String name) {
        yourPlayer(name).setOnes(addPointToCase("ones", yourGame(name).dices));   
    }

    public void saveToTwos(String name) {
        yourPlayer(name).setTwos(addPointToCase("twos", yourGame(name).dices));
    }

    public void saveToThrees(String name) {
        yourPlayer(name).setThrees(addPointToCase("threes", yourGame(name).dices));
    }

    public void saveToFours(String name) {
        yourPlayer(name).setFours(addPointToCase("fours", yourGame(name).dices));
    }

    public void saveToFives(String name) {
        yourPlayer(name).setFives(addPointToCase("fives", yourGame(name).dices));
    }

    public void saveToSixes(String name) {
        yourPlayer(name).setSixes(addPointToCase("sixes", yourGame(name).dices));
    }
    public void saveToOnePair(String name) {
        yourPlayer(name).setOnepair(addPointToCase("onepair", yourGame(name).dices));
    }
    public void saveToTwoPair(String name) {
        yourPlayer(name).setTwopair(addPointToCase("twopair", yourGame(name).dices));
    }
    public void saveToThreeSame(String name) {
        yourPlayer(name).setThreeOfAKind(addPointToCase("threesame", yourGame(name).dices));
    }
    public void saveToFourSame(String name) {
        yourPlayer(name).setFourOfAKind(addPointToCase("foursame", yourGame(name).dices));
    }
    public void saveToHouse(String name) {
        yourPlayer(name).setFullHouse(addPointToCase("house", yourGame(name).dices));
    }
    public void saveToSmallStraight(String name) {
        yourPlayer(name).setSmallStraight(addPointToCase("smallstraight", yourGame(name).dices));
    }
    public void saveToLargeStraight(String name) {
        yourPlayer(name).setLargeStraight(addPointToCase("largestraight", yourGame(name).dices));
    }
    public void saveToChance(String name) {
        yourPlayer(name).setChange(addPointToCase("chance", yourGame(name).dices));
    }
    public void saveToYatzy(String name) {
        yourPlayer(name).setYatzy(addPointToCase("yatzy", yourGame(name).dices));
    }

    public Game yourGame(String name) {
        for (Game onegame : gamelist) {
            List<Player> playerlist = onegame.getPlayers();
            for (Player oneplayer : playerlist) {
                if (oneplayer.getName() == null ? name == null : oneplayer.getName().equals(name)) {
                    return onegame;
                }
            }
        }
        return new Game();
    }

    public Player yourPlayer(String name) {
        for (Game onegame : gamelist) {
            List<Player> playerlist = onegame.getPlayers();
            for (Player oneplayer : playerlist) {
                if (oneplayer.getName() == null ? name == null : oneplayer.getName().equals(name)) {
                    return oneplayer;
                }
            }
        }
        return new Player();
    }
    public boolean isAlone(String name){
        if(yourGame(name).players.size() == 1){
            return true;      
        }
        else return false;
    }

    public void yourDicesRoll(String name) {
        List<Dice> dices = yourGame(name).getDices();
        for (int j = 0; j < 5; j++) {
            if (!dices.get(j).isPressed()) {
                dices.get(j).setRoll(random.nextInt(1000000000) % 6 + 1);
            }
        }
    }

    public boolean isMyTurn(String name) {
        if (yourPlayer(name).getTurn() == 1)
        return true; 
        else
        return false;
    }
    public void swapTurns(String name) {
        //works with 2 ppl lazy way out
        //sets everyone elses turn to 1
        yourPlayer(name).setTurn(0);
        for (Player oneplayer : yourGame(name).players){
           if (!(oneplayer.getName() == null ? name == null : oneplayer.getName().equals(name))) {
                   oneplayer.setTurn(1);
                } 
        }       
    }
    

    public int addPointToCase(String choose, List<Dice> dices) {
        //calculation logic here
        Collections.sort(dices);
        
        
        int points = 0;
        switch (choose) {
            case "ones":
                for (int i = 0; i < 5; i++) {
                    if (dices.get(i).getRoll() == 1) {
                        points++;
                    }
                }
                break;
            case "twos":
                for (int i = 0; i < 5; i++) {
                    if (dices.get(i).getRoll() == 2) {
                        points += 2;
                    }
                }
                break;
            case "threes":
                for (int i = 0; i < 5; i++) {
                    if (dices.get(i).getRoll() == 3) {
                        points += 3;
                    }
                }
                break;
            case "fours":
                for (int i = 0; i < 5; i++) {
                    if (dices.get(i).getRoll() == 4) {
                        points += 4;
                    }
                }
                break;
            case "fives":
                for (int i = 0; i < 5; i++) {
                    if (dices.get(i).getRoll() == 5) {
                        points += 5;
                    }
                }
                break;
            case "sixes":
                for (int i = 0; i < 5; i++) {
                    if (dices.get(i).getRoll() == 6) {
                        points += 6;
                    }
                }
            break;
            case "onepair": 
                if (dices.get(0).getRoll() == dices.get(1).getRoll()){
                points = (dices.get(0).getRoll() * 2);
                }
                if (dices.get(1).getRoll() == dices.get(2).getRoll()){
                points = (dices.get(1).getRoll() * 2);
                }
                if (dices.get(2).getRoll() == dices.get(3).getRoll()){
                points = (dices.get(2).getRoll() * 2);
                }
                if (dices.get(3).getRoll() == dices.get(4).getRoll()){
                points = (dices.get(3).getRoll() * 2);
                }
                break;
            case "twopair":
                if (dices.get(0).getRoll() == dices.get(1).getRoll() & dices.get(2).getRoll() == dices.get(3).getRoll() & dices.get(1).getRoll() != dices.get(2).getRoll() ){
                points = ((dices.get(0).getRoll()*2)+(dices.get(0).getRoll()*2));
                }
                if (dices.get(1).getRoll() == dices.get(2).getRoll() & dices.get(3).getRoll() == dices.get(4).getRoll() & dices.get(1).getRoll() != dices.get(3).getRoll() ){
                points = ((dices.get(1).getRoll()*2)+(dices.get(3).getRoll()*2));
                }
                if (dices.get(0).getRoll() == dices.get(1).getRoll() & dices.get(3).getRoll() == dices.get(4).getRoll() & dices.get(0).getRoll() != dices.get(3).getRoll() ){
                points = ((dices.get(1).getRoll()*2)+(dices.get(3).getRoll()*2));
                }
                break;
            case "threesame":
                if (dices.get(0).getRoll() == dices.get(1).getRoll() & dices.get(1).getRoll() == dices.get(2).getRoll() ){
                points = (dices.get(0).getRoll()*3);
                }
                if (dices.get(1).getRoll() == dices.get(2).getRoll() & dices.get(2).getRoll() == dices.get(3).getRoll() ){
                points = (dices.get(1).getRoll()*3);
                }
                if (dices.get(2).getRoll() == dices.get(3).getRoll() & dices.get(3).getRoll() == dices.get(4).getRoll() ){
                points = (dices.get(3).getRoll()*3);
                }
            break;
            case "foursame":
                if (dices.get(0).getRoll() == dices.get(1).getRoll() & dices.get(1).getRoll() == dices.get(2).getRoll() & dices.get(2).getRoll() == dices.get(3).getRoll() ){
                points = (dices.get(0).getRoll()*4);
                }
                if (dices.get(1).getRoll() == dices.get(2).getRoll() & dices.get(2).getRoll() == dices.get(3).getRoll() & dices.get(3).getRoll() == dices.get(4).getRoll() ){
                points = (dices.get(0).getRoll()*4);
                }               
            break;
            case "house":
                if (dices.get(0).getRoll() == dices.get(1).getRoll() & dices.get(1).getRoll() == dices.get(2).getRoll() & dices.get(3).getRoll() == dices.get(4).getRoll() & dices.get(1).getRoll() != dices.get(4).getRoll() ){
                points = (dices.get(0).getRoll()*3+dices.get(3).getRoll()*2);
                }
                if (dices.get(0).getRoll() == dices.get(1).getRoll() & dices.get(2).getRoll() == dices.get(3).getRoll() & dices.get(3).getRoll() == dices.get(4).getRoll() & dices.get(1).getRoll() != dices.get(4).getRoll() ){
                points = (dices.get(0).getRoll()*2+dices.get(3).getRoll()*3);
                }
            break;
            case "smallstraight" : 
                if (dices.get(0).getRoll() == 1 & dices.get(1).getRoll() == 2 & dices.get(2).getRoll() == 3 & dices.get(3).getRoll() == 4 & dices.get(4).getRoll() == 5 ){
                    points = 15;
                }
            break;
            case "largestraight" : 
                if (dices.get(0).getRoll() == 2 & dices.get(1).getRoll() == 3 & dices.get(2).getRoll() == 4 & dices.get(3).getRoll() == 5 & dices.get(4).getRoll() == 6 ){
                    points = 20;
                }               
            break;
            case "chance" :              
                    points = dices.get(0).getRoll()+dices.get(1).getRoll()+dices.get(2).getRoll()+dices.get(3).getRoll()+dices.get(4).getRoll() ;              
            break; 
            case "yatzy" :
                if (dices.get(0).getRoll() == dices.get(4).getRoll()){
                    points = 50;
                }          
            break;
            default:
                points = 0;
        }
        return points;
    }

}
