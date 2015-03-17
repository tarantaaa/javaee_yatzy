/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Classes.*;
import static Classes.Dice.random;
import java.util.List;
/**
 *
 * @author Markus
 */
import javax.el.ELContext;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class GameController {
    private String name;
    private int roll;

    public int getRoll() {
        return roll;
    }
    public void GameController() {
    this.roll = 0;
}
    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void roll() {     
       FacesContext facesContext = FacesContext.getCurrentInstance(); 
       ELContext elContext = FacesContext.getCurrentInstance().getELContext();   
       GameList CurrentGameList = (GameList) facesContext.getApplication().getELResolver().getValue(elContext, null, "gameList");
       if (CurrentGameList.isMyTurn(name) & roll < 3)
       {    
       this.roll++;    
       CurrentGameList.yourDicesRoll(name);
       }        
    
    }
    public Game getYourGame() {
       GameList CurrentGameList = GetGameList();
       return CurrentGameList.yourGame(name);      
    }
                  
    public String yourOnes(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToOnes(name);
       return EndTurn();
       }
       return "Yatzy";
    }
   public String yourTwos(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToTwos(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourThrees(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToThrees(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourFours(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToFours(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourFives(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToFives(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourSixes(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToSixes(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourOnePair(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToOnePair(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourTwoPair(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToTwoPair(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourThreeSame(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToThreeSame(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourFourSame(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToFourSame(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourHouse(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToHouse(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourSmallSTraight(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToSmallStraight(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourLargeStraight(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToLargeStraight(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourChance(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToChance(name);
       return EndTurn();
       }
       return "Yatzy";
    }
    public String yourYatzy(){
       GameList CurrentGameList = GetGameList();
       if (CurrentGameList.isMyTurn(name) & roll > 0) {
       CurrentGameList.saveToYatzy(name);      
       return EndTurn();
       }
       return "Yatzy";
    }
    public int turnNow(){
       GameList CurrentGameList = GetGameList();
       return CurrentGameList.yourPlayer(name).getRound();
    }
    
    public GameList GetGameList(){
       FacesContext facesContext = FacesContext.getCurrentInstance(); 
       ELContext elContext = FacesContext.getCurrentInstance().getELContext();   
       GameList CurrentGameList = (GameList) facesContext.getApplication().getELResolver().getValue(elContext, null, "gameList");
       return CurrentGameList;
    }
    public String EndTurn(){
       this.roll = 0; 
       GameList CurrentGameList = GetGameList();
       CurrentGameList.yourGame(name).ResetDices();
       CurrentGameList.swapTurns(name); 
       CurrentGameList.yourPlayer(name).setRound();
       if (CurrentGameList.yourPlayer(name).getRound() > 12) {      //12     
           this.EndGame(CurrentGameList);
           return "index";
       }
       return "Yatzy";
    }
    public void EndGame(GameList CurrentGameList){
        FacesContext facesContext = FacesContext.getCurrentInstance(); 
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();   
        XMLController xmlc = (XMLController) facesContext.getApplication().getELResolver().getValue(elContext, null, "xMLController");
        xmlc.checkIfHighScore(name, Integer.toString(CurrentGameList.yourPlayer(name).getTotal()));
        //xmlc.createHighScoresFile();
        if (CurrentGameList.isAlone(name)){
            CurrentGameList.RemoveGame(CurrentGameList.yourGame(name));          
        }
        else{
            CurrentGameList.yourGame(name).removePlayer(CurrentGameList.yourPlayer(name));
        }
    }
    public String getInTurn(){
        GameList CurrentGameList = GetGameList();
        if(CurrentGameList.isMyTurn(name)){
            return "On sinun vuorosi.";
        }else{
            return "On vastustajan vuoro.";
        }
    }
  
    }
    

