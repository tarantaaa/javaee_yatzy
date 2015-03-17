/*
 *
 */
package Classes;


/**
 *
 * @author Tarleena_2
 */
public class LobbyPlayer {
    private String name;
    private boolean wait;
    private boolean ingame;
    private String challenger;
    public String getChallenger() {
        return challenger;
    }

    public void setChallenger(String challenger) {
        this.challenger = challenger;
    }
    
    public LobbyPlayer(){
        
    }
    public LobbyPlayer(String name) {
        this.name = name;
        this.wait = false;
        this.ingame = false;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isWait() {
        return wait;
    }
    public void setWait(boolean wait) {
        this.wait = wait;
    }
    public boolean isIngame() {
        return ingame;
    }
    public void setIngame(boolean ingame) {
        this.ingame = ingame;
    } 
    
    //debug methods
    public String getJonossa(){
        if(this.wait)
            return "Pelissä";
        else
            return "Vapaa";
    }
}
