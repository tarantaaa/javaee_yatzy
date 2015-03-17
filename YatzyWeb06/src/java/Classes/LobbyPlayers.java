package Classes;

import java.util.*;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Muutoksia addTestPlayers
 *
 * @author Tara
 */
@ManagedBean
@ApplicationScoped
public class LobbyPlayers {

    private ArrayList<LobbyPlayer> players;

    public LobbyPlayers() {
        players = new ArrayList<LobbyPlayer>();
        this.addTestPlayers();
    }

    private void addTestPlayers() {
        LobbyPlayer jaana = new LobbyPlayer("Jaana");
        //jaana.setWait(false);
        this.players.add(jaana);
        LobbyPlayer toni = new LobbyPlayer("Toni");
        toni.setWait(true);
        this.players.add(toni);
        LobbyPlayer sirkka = new LobbyPlayer("Sirkka");
        sirkka.setWait(true);
        this.players.add(sirkka);
    }

    public ArrayList<LobbyPlayer> getPlayers() {
        return this.players;
    }

    public void setPlayers(ArrayList<LobbyPlayer> players) {
        this.players = players;
    }

    public boolean isOnTheList(String name) {
        if (this.getIndexLobbyPlayer(name) > -1) {
            return true;
        } else {
            return false;
        }
    }

    public void addLobbyPlayer(String name) {
        //for UIController
        players.add(new LobbyPlayer(name));
    }

    public void removeLobbyPlayer(int index) {
        players.remove(index);
    }

    public boolean isWaitLobbyPlayer(int index) {
        return players.get(index).isWait();
    }

    public void setWaitLobbyPlayer(int index, boolean wait) {
        players.get(index).setWait(wait);
    }

    public boolean isIngameLobbyPlayer(int index) {
        return players.get(index).isIngame();
    }

    public void setIngameLobbyPlayer(int index, boolean ingame) {
        players.get(index).setIngame(ingame);
    }

    public void setChallengerLobbyPlayer(int index, String challenger) {
        players.get(index).setChallenger(challenger);
    }

    public int getIndexLobbyPlayer(String name1) {
        for (int k = 0; k < this.players.size(); k++) {
            String name2 = this.players.get(k).getName();
            if (name1.equals(name2)) {
                return k;
            }
        }
        return -1;
    }
    public String getJonossaLobbyPlayer(int index){
        return this.players.get(index).getJonossa();
    }
}
