/*
*
*
 */
package Classes;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.*;
@ManagedBean
@ApplicationScoped
public class Game implements java.io.Serializable {
    List<Player> players;
    List<Dice> dices;
    
    public Game(){
        dices = new ArrayList();
        players = new ArrayList();
        dices.add(new Dice());
        dices.add(new Dice());
        dices.add(new Dice());
        dices.add(new Dice());
        dices.add(new Dice());
        players.add(new Player("Esko"));
         players.add(new Player("Matti"));
    }
    public Game(String name1, String name2){
        dices = new ArrayList();
        dices.add(new Dice());
        dices.add(new Dice());
        dices.add(new Dice());
        dices.add(new Dice());
        dices.add(new Dice());
        players = new ArrayList();
        Player player1 = new Player(name1);
        player1.setTurn(1);
        players.add(player1);
        players.add(new Player(name2));
    }
    public Game(Player one, Player two){
        dices = new ArrayList();
        dices.add(new Dice());
        dices.add(new Dice());
        dices.add(new Dice());
        dices.add(new Dice());
        dices.add(new Dice());
        players = new ArrayList();
        players.add(one);
        players.add(two);
    }
   public void ResetDices(){
       dices.clear();
       dices.add(new Dice());
        dices.add(new Dice());
        dices.add(new Dice());
        dices.add(new Dice());
        dices.add(new Dice());      
   }
   public void removePlayer(Player player){
        players.remove(player);
    }

   public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    } 

    public List<Dice> getDices() {
        return dices;
    }  

    public void setDices(List<Dice> dices) {
        this.dices = dices;
    }   
 
}
