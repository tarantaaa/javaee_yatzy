package Controllers;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import Classes.*;
import java.util.*;
import javax.el.ELContext;

/**
 * muutettu public String refreshPage() ja public String challengeThePlayer() ja
 * public void createTheGame() ja ja ja ja ja
 *
 * @author Tara
 */
@ManagedBean
@SessionScoped
public class UIController implements java.io.Serializable {

    private String name;
    private String challenged;
    private List<LobbyPlayers> uiPlayers;
    private String newUrl;

    public UIController() {
        uiPlayers = new ArrayList();
    }

    public ArrayList<LobbyPlayer> getUiPlayers() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        LobbyPlayers lp = (LobbyPlayers) facesContext.getApplication().getELResolver().getValue(elContext, null, "lobbyPlayers");
        ArrayList<LobbyPlayer> tempplayers = lp.getPlayers();
        return tempplayers;
    }

    public void setUiPlayers(List<LobbyPlayers> uiPlayers) {
        this.uiPlayers = uiPlayers;
    }

    public String getNewUrl() {
        //for javascript
        //checks out if somebody have challenged the player
        //moves player to game page
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        LobbyPlayers lp = (LobbyPlayers) facesContext.getApplication().getELResolver().getValue(elContext, null, "lobbyPlayers");
        GameController gc = (GameController) facesContext.getApplication().getELResolver().getValue(elContext, null, "gameController");
        if (lp.isWaitLobbyPlayer(lp.getIndexLobbyPlayer(name))) {
            gc.setName(name);
            return "Yatzy.xhtml";
        } else {
            return "Lobby.xhtml";
        }
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public String getChallenged() {
        return challenged;
    }

    public void setChallenged(String challenged) {
        this.challenged = challenged;
    }

    public String addPlayerToList() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        //LobbyPlayers lp = (LobbyPlayers)facesContext.getExternalContext().getSessionMap().get("lobbyPlayers");
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        LobbyPlayers lp = (LobbyPlayers) facesContext.getApplication().getELResolver().getValue(elContext, null, "lobbyPlayers");
        if (!this.name.isEmpty()) {
            if (!lp.isOnTheList(name)) {
                lp.addLobbyPlayer(this.name);
                return "Lobby";
            } else {
                return "index";
            }
        } else {
            return "index";
        }
    }

    public String challengeThePlayer() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        LobbyPlayers lp = (LobbyPlayers) facesContext.getApplication().getELResolver().getValue(elContext, null, "lobbyPlayers");
        try {//not able to challenge yourself
            if(name.compareTo(challenged) == 0){
                challenged = "";
                return "Lobby";                
            }//not able to challenge player who already is challenged
            if (lp.isWaitLobbyPlayer(lp.getIndexLobbyPlayer(challenged))) {
                challenged = "";
                return "Lobby";
            }//not able to challenge anybody if someone has already challenged you
            if (lp.isWaitLobbyPlayer(lp.getIndexLobbyPlayer(name))) {
                challenged = "";
                return "Lobby";
            } else {//challenge accepted
                lp.setWaitLobbyPlayer(lp.getIndexLobbyPlayer(challenged), true);
                lp.setChallengerLobbyPlayer(lp.getIndexLobbyPlayer(challenged), name);
                lp.setWaitLobbyPlayer(lp.getIndexLobbyPlayer(name), true);
                lp.setChallengerLobbyPlayer(lp.getIndexLobbyPlayer(name), challenged);
                this.createTheGame();
                return "Yatzy";
            }
        } catch (Exception e) {
            return "Lobby";
        }
    }

    public void createTheGame() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        GameController gc = (GameController) facesContext.getApplication().getELResolver().getValue(elContext, null, "gameController");
        gc.setName(name);
        GameList gamelist = (GameList) facesContext.getApplication().getELResolver().getValue(elContext, null, "gameList");
        gamelist.createGame(name, challenged);
    }

    public String refreshPage() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        LobbyPlayers lp = (LobbyPlayers) facesContext.getApplication().getELResolver().getValue(elContext, null, "lobbyPlayers");
        GameController gc = (GameController) facesContext.getApplication().getELResolver().getValue(elContext, null, "gameController");
        if (lp.isWaitLobbyPlayer(lp.getIndexLobbyPlayer(name))) {
            gc.setName(name);
            return "Yatzy";
        } else {
            return "Lobby";
        }
    }
}
