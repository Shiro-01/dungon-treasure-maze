import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 * Write a description of interface UI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface UI 
{
    /**
     * An example of a method header - replace this comment with your own
     *
     * @param  y a sample parameter for a method
     * @return   the result produced by sampleMethod
     */
    
    public static UI UiStart(Playable game) throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        return new GUI(game);
    }
    void updateButtons(boolean doesRoomHaveMonester);
    void updateBackground(String roomName, String roomDiscription, String roomImagePath);
    void updatePlayerStatus(int playerLives, int playerHealthPoints, int playerAttackPoints, int playerDefensePoints);
    
    
    JPanel messageWindow(String text);
    void waysButtonsMaker(JPanel buttonsPanel, String Text);
    void losingWindow();
    void winningWindow();
    void createMonesterWindow(String name,int monesterHealthPoints, int monesterFullHealth, int monesterAttackPoints, int monesterDefensePoints);
    void contentButtonMaker(String itemName, String itemDiscription, int itemWeight, int itemAttackPoints, int itemDefensePoints, int itemHealthPoints);
    void inventoryButtonMaker(String itemName, String itemDiscription, int itemWeight, int itemAttackPoints, int itemDefensePoints, int itemHealthPoints);
    void gamePlayWindowMaker(String playerName, int playerLives, int playerHealthPoints, int playerAttackPoints, int playerDefensePoints, boolean doesRoomHaveMonester, String roomName, String roomDiscription, String roomImagePath);
    void makeInventoryWindow(String playerName, int playerLives, int playerHealthPoints, int playerAttackPoints, int playerDefensePoints, int playerCarriedWeight);
    // public void updateBackground()throws IOException; 
}
