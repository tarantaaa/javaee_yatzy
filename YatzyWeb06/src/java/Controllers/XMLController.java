package Controllers;

import Classes.*;

import java.io.File;
import java.nio.file.*;
import java.util.*;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * MUUTETTU cechIfHighScore
 * @author Tarleena_2
 */
@ManagedBean
@SessionScoped
public class XMLController {

    private String[][] scoreplayers = new String[5][2];

    public String[][] getScoreplayers() {
        return scoreplayers;
    }

    public void setScoreplayers(String[][] scoreplayers) {
        this.scoreplayers = scoreplayers;
    }

    public XMLController() {
        this.readHighScoresFile();
    }

    public void createBackupFile() {
        //Creates a XML file about all games in gamelist
        //First creates a DOM object and then transform it to an XML file
        String xmlFilePath = "D:\\games.xml";
        Path filepath = Paths.get("D:\\games.xml");

        try {
            boolean success = Files.deleteIfExists(filepath);
        } catch (Exception e) {
        }

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("games");
            document.appendChild(root);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            GameList gamelist = (GameList) facesContext.getApplication().getELResolver().getValue(elContext, null, "gameList");
            List<Game> games = gamelist.getGamelist();
            for (int j = 0; j < games.size(); j++) {
                Element game = document.createElement("game");
                root.appendChild(game);
                List<Player> players = games.get(j).getPlayers();
                for (int i = 0; i < players.size(); i++) {
                    Element oneplayer = document.createElement("player");
                    Attr turn = document.createAttribute("turn");
                    Element nick = document.createElement("name");
                    Element ones = document.createElement("ones");
                    Element twos = document.createElement("twos");
                    Element threes = document.createElement("threes");
                    Element fours = document.createElement("fours");
                    Element fives = document.createElement("fives");
                    Element sixes = document.createElement("sixes");
                    Element threeofkind = document.createElement("threeofkind");
                    Element fourofkind = document.createElement("fourofkind");
                    Element fullhouse = document.createElement("fullhouse");
                    Element smallstraight = document.createElement("smallstraight");
                    Element largestraight = document.createElement("largestraight");
                    Element yatzy = document.createElement("yatzy");
                    Element change = document.createElement("change");

                    turn.setValue(Integer.toString(players.get(i).getTurn()));
                    nick.appendChild(document.createTextNode(players.get(i).getName()));
                    ones.appendChild(document.createTextNode(Integer.toString(players.get(i).getOnes())));
                    twos.appendChild(document.createTextNode(Integer.toString(players.get(i).getTwos())));
                    threes.appendChild(document.createTextNode(Integer.toString(players.get(i).getThrees())));
                    fours.appendChild(document.createTextNode(Integer.toString(players.get(i).getFours())));
                    fives.appendChild(document.createTextNode(Integer.toString(players.get(i).getFives())));
                    sixes.appendChild(document.createTextNode(Integer.toString(players.get(i).getSixes())));
                    threeofkind.appendChild(document.createTextNode(Integer.toString(players.get(i).getThreeOfAKind())));
                    fourofkind.appendChild(document.createTextNode(Integer.toString(players.get(i).getFourOfAKind())));
                    fullhouse.appendChild(document.createTextNode(Integer.toString(players.get(i).getFullHouse())));
                    smallstraight.appendChild(document.createTextNode(Integer.toString(players.get(i).getSmallStraight())));
                    largestraight.appendChild(document.createTextNode(Integer.toString(players.get(i).getLargeStraight())));
                    yatzy.appendChild(document.createTextNode(Integer.toString(players.get(i).getYatzy())));
                    change.appendChild(document.createTextNode(Integer.toString(players.get(i).getChange())));

                    game.appendChild(oneplayer);
                    oneplayer.setAttributeNode(turn);
                    oneplayer.appendChild(nick);
                    oneplayer.appendChild(ones);
                    oneplayer.appendChild(twos);
                    oneplayer.appendChild(threes);
                    oneplayer.appendChild(fours);
                    oneplayer.appendChild(fives);
                    oneplayer.appendChild(sixes);
                    oneplayer.appendChild(threeofkind);
                    oneplayer.appendChild(fourofkind);
                    oneplayer.appendChild(fullhouse);
                    oneplayer.appendChild(smallstraight);
                    oneplayer.appendChild(largestraight);
                    oneplayer.appendChild(yatzy);
                    oneplayer.appendChild(change);
                }
            }
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

    public void readBackupFile() {
        //Reads XML file and saves games to GameList object
        //First creates a DOM object and then transform it to Game objects
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        GameList gamelistbean = (GameList) facesContext.getApplication().getELResolver().getValue(elContext, null, "gameList");
        List<Game> tempgames = new ArrayList();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("D:\\games.xml"));
            NodeList listofgames = document.getElementsByTagName("game");
            for (int i = 0; i < listofgames.getLength(); i++) {
                //loops all game-nodes
                Node gamenode = listofgames.item(i);
                NodeList listofplayers = gamenode.getChildNodes();
                //two dimensional arrays for dumping the values from the xml file
                String[] nick = new String[listofplayers.getLength()];
                String[] turn = new String[listofplayers.getLength()];
                String[] ones = new String[listofplayers.getLength()];
                String[] twos = new String[listofplayers.getLength()];
                String[] threes = new String[listofplayers.getLength()];
                String[] fours = new String[listofplayers.getLength()];
                String[] fives = new String[listofplayers.getLength()];
                String[] sixes = new String[listofplayers.getLength()];
                String[] threeofkind = new String[listofplayers.getLength()];
                String[] fourofkind = new String[listofplayers.getLength()];
                String[] fullhouse = new String[listofplayers.getLength()];
                String[] smallstraight = new String[listofplayers.getLength()];
                String[] largestraight = new String[listofplayers.getLength()];
                String[] yatzy = new String[listofplayers.getLength()];
                String[] change = new String[listofplayers.getLength()];
                for (int j = 0; j < listofplayers.getLength(); j++) {
                    //loops the players of one game
                    Node oneplayer = listofplayers.item(j);
                    if (oneplayer.getNodeType() == Node.ELEMENT_NODE) {
                        //get the values from the xml file
                        Element playerelement = (Element) oneplayer;
                        nick[j] = playerelement.getElementsByTagName("name").item(0).getTextContent();
                        turn[j] = playerelement.getAttributes().getNamedItem("turn").getTextContent();
                        ones[j] = playerelement.getElementsByTagName("ones").item(0).getTextContent();
                        twos[j] = playerelement.getElementsByTagName("twos").item(0).getTextContent();
                        threes[j] = playerelement.getElementsByTagName("threes").item(0).getTextContent();
                        fours[j] = playerelement.getElementsByTagName("fours").item(0).getTextContent();
                        fives[j] = playerelement.getElementsByTagName("fives").item(0).getTextContent();
                        sixes[j] = playerelement.getElementsByTagName("sixes").item(0).getTextContent();
                        threeofkind[j] = playerelement.getElementsByTagName("threeofkind").item(0).getTextContent();
                        fourofkind[j] = playerelement.getElementsByTagName("fourofkind").item(0).getTextContent();
                        fullhouse[j] = playerelement.getElementsByTagName("fullhouse").item(0).getTextContent();
                        smallstraight[j] = playerelement.getElementsByTagName("smallstraight").item(0).getTextContent();
                        largestraight[j] = playerelement.getElementsByTagName("largestraight").item(0).getTextContent();
                        yatzy[j] = playerelement.getElementsByTagName("yatzy").item(0).getChildNodes().item(0).getTextContent();
                        change[j] = playerelement.getElementsByTagName("change").item(0).getChildNodes().item(0).getTextContent();
                    }
                }
                //save the values to Player objects
                Player tempplayer1 = new Player(nick[0]);
                tempplayer1.setTurn(Integer.parseInt(turn[0]));
                tempplayer1.setOnes(Integer.parseInt(ones[0]));
                tempplayer1.setTwos(Integer.parseInt(twos[0]));
                tempplayer1.setThrees(Integer.parseInt(threes[0]));
                tempplayer1.setFours(Integer.parseInt(fours[0]));
                tempplayer1.setFives(Integer.parseInt(fives[0]));
                tempplayer1.setSixes(Integer.parseInt(sixes[0]));
                tempplayer1.setThreeOfAKind(Integer.parseInt(threeofkind[0]));
                tempplayer1.setFourOfAKind(Integer.parseInt(fourofkind[0]));
                tempplayer1.setFullHouse(Integer.parseInt(fullhouse[0]));
                tempplayer1.setSmallStraight(Integer.parseInt(smallstraight[0]));
                tempplayer1.setLargeStraight(Integer.parseInt(largestraight[0]));
                tempplayer1.setYatzy(Integer.parseInt(yatzy[0]));
                tempplayer1.setChange(Integer.parseInt(change[0]));

                Player tempplayer2 = new Player(nick[1]);
                tempplayer2.setTurn(Integer.parseInt(turn[1]));
                tempplayer2.setOnes(Integer.parseInt(ones[1]));
                tempplayer2.setTwos(Integer.parseInt(twos[1]));
                tempplayer2.setThrees(Integer.parseInt(threes[1]));
                tempplayer2.setFours(Integer.parseInt(fours[1]));
                tempplayer2.setFives(Integer.parseInt(fives[1]));
                tempplayer2.setSixes(Integer.parseInt(sixes[1]));
                tempplayer2.setThreeOfAKind(Integer.parseInt(threeofkind[1]));
                tempplayer2.setFourOfAKind(Integer.parseInt(fourofkind[1]));
                tempplayer2.setFullHouse(Integer.parseInt(fullhouse[1]));
                tempplayer2.setSmallStraight(Integer.parseInt(smallstraight[1]));
                tempplayer2.setLargeStraight(Integer.parseInt(largestraight[1]));
                tempplayer2.setYatzy(Integer.parseInt(yatzy[1]));
                tempplayer2.setChange(Integer.parseInt(change[1]));

                //creates a Game object from players and add it to list of games
                Game tempgame = new Game(tempplayer1, tempplayer2);

                //Game tempgame = new Game(nick[0], nick[1]);//for debug
                tempgames.add(tempgame);
            }
            //replace the gamelist of GameList bean with new gamelist formed of values from xml file
            gamelistbean.setGamelist(tempgames);
        } catch (Exception e) {

        }
    }

    public void checkIfHighScore(String name, String score) {
        //Checks if new highscore has made and modify String[][] scoreplayers if needed
        int index = -1;
        this.readHighScoresFile();
        for (int m = 4; m > -1; m--) {
            if (Integer.parseInt(scoreplayers[m][1]) < Integer.parseInt(score)) {
                index = m;
            }
        }
        if (index > -1) {
            for (int k = 3; k + 1 > index; k--) {
                scoreplayers[k + 1][0] = scoreplayers[k][0];
                scoreplayers[k + 1][1] = scoreplayers[k][1];
            }
            scoreplayers[index][0] = name;
            scoreplayers[index][1] = score;
            this.createHighScoresFile();
        }
    }

    public void createHighScoresFile() {
        //Muuta privateksi!

        //Creates a XML file about highscores
        //First creates a DOM object and then transform it to an XML file
        String xmlFilePath = "D:\\highscores.xml";
        Path filepath = Paths.get("D:\\highscores.xml");

        try {
            boolean success = Files.deleteIfExists(filepath);
        } catch (Exception e) {
        }

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("highscores");
            document.appendChild(root);

            for (int n = 0; n < 5; n++) {
                Element oneplayer = document.createElement("player");
                Element nick = document.createElement("name");
                Element hscore = document.createElement("score");
                nick.appendChild(document.createTextNode(scoreplayers[n][0]));
                hscore.appendChild(document.createTextNode(scoreplayers[n][1]));
                root.appendChild(oneplayer);
                oneplayer.appendChild(nick);
                oneplayer.appendChild(hscore);
            }
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public void readHighScoresFile() {
        //Reads XML file and saves highscores to String[][] scoreplayers
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("D:\\highscores.xml"));

            NodeList listofplayers = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < listofplayers.getLength(); i++) {
                Node oneplayer = listofplayers.item(i);
                if (oneplayer.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) oneplayer;
                    String nick = elem.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
                    String hscore = elem.getElementsByTagName("score").item(0).getChildNodes().item(0).getNodeValue();
                    this.scoreplayers[i][0] = nick;
                    this.scoreplayers[i][1] = hscore;
                }

            }
        } catch (Exception e) {
            //if there is no highscore file
            scoreplayers[0][0] = "Jonne";
            scoreplayers[0][1] = "300";
            scoreplayers[1][0] = "Pirkko";
            scoreplayers[1][1] = "250";
            scoreplayers[2][0] = "Liisa";
            scoreplayers[2][1] = "200";
            scoreplayers[3][0] = "Jere";
            scoreplayers[3][1] = "150";
            scoreplayers[4][0] = "Jonne";
            scoreplayers[4][1] = "100";
        }
    }
}
