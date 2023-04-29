import java.io.IOException;

/**
 * Write a description of interface Playable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Playable
{
    //game controllers
    void newGame(String playerName);
    void loadGame();
    void saveGame();
       
    // welcoming message
    String welcome();
      
    // navigation and rooms methods
    void goRoom(String direction) throws IOException;
    void ways();
    
    
    // fight methods
    void fight();
    void monester();
    
    //item methods
    void getRoomItems();
    void takeItem(String itemName);
    void leaveItem(String itemName);
    void consume(String itemName);
    
    //inventory methods
    int getInventorryItems();
    void inventory();
}
