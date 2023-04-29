import java.util.ArrayList;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.Serializable;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 * 
 * 
 * // cohesin meams evey class perform only one specific task so that can be called coherant 
 * this can be applied also to method as in command process as it deals only with one part.
 * also coherant means to avoid copy and paste if u r in that situation then its better to make 
 * a method for this function.
 */

public class Game implements Playable, Serializable
{
    transient private UI ui;
    private Environement environement; 
    private Player player;
    
    //<---------------------------------------------main function ------------------------------------------->
    public static void main(String args []) throws LineUnavailableException,UnsupportedAudioFileException,IOException 
    {
        new Game();
    }
    
    //<---------------------------------------------constructor ------------------------------------------->
    /**
     * Game Constructor
     *
     * @param name the player name
     */
    public Game() throws LineUnavailableException,UnsupportedAudioFileException,IOException 
    {
        // Starting the GUI
        ui = UI.UiStart(this);
    }

    //<---------------------------------------------Game controllers ------------------------------------------->
    /**
     * Method newGame Starts a new game
     *
     * @param playerName A parameter
     */
    public void newGame(String playerName)
    {
        //creating the environment and the player and assigning 
        player = new Player(playerName);
        environement = new Environement();
        // seting player start location in the invironment 
        player.setCurrentLocation(environement.startRoom());
        
        // makeing the gamePlay gui
        // gamePlayWindowMaker(String playerName, int playerLives, int playerHealthPoints, int playerAttackPoints, int playerDefensePoints, boolean doesRoomHaveMonester, String roomName, String roomDiscription, String roomImagePath)
        ui.gamePlayWindowMaker(player.getName(), player.getLives(), player.getHealthPoints(), player.getAttackPoints(), player.getDefensePoints(), doesRoomHaveMonester(), player.getCurrentLocation().getRoomName(), player.getCurrentLocation().getDescription(), player.getCurrentLocation().getImagePath());
    }
    
    /**
     * Method newGame Starts a new game
     *
     * @param playerName A parameter
     */
    public void loadGame()
    {
        //deserializing the data
        GameSerializer gameDeserializer = new GameSerializer();
        Game game = gameDeserializer.deserialize();
        
        //creating the environment and the player and assigning
        this.player = game.player;
        
        this.environement = game.environement;
        // no need any more to set player location it will be automaticall
        // player.setCurrentLocation(environement.startRoom());
        
        // makeing the gamePlay gui
        ui.gamePlayWindowMaker(player.getName(), player.getLives(), player.getHealthPoints(), player.getAttackPoints(), player.getDefensePoints(), doesRoomHaveMonester(), player.getCurrentLocation().getRoomName(), player.getCurrentLocation().getDescription(), player.getCurrentLocation().getImagePath());
    }
    
    public void saveGame()
    {
        //deserializing the data
        GameSerializer gamSerializer = new GameSerializer();
        gamSerializer.serialize(this);
    }

    //<---------------------------------------------Game Navigation------------------------------------------->
    /** 
     * 
     * this method uses the second cokmmand word for the go command
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(String direction) throws IOException
    {
        // Try to leave current room.
        player.getCurrentLocation().getExit(direction);

            player.setPreviousLocation(player.getCurrentLocation());
            player.setCurrentLocation(player.getCurrentLocation().getExit(direction));
            ui.updateBackground(player.getCurrentLocation().getRoomName(), player.getCurrentLocation().getDescription(), player.getCurrentLocation().getImagePath());
            
            ui.updateButtons(doesRoomHaveMonester());
    }
    
    public void ways()
    {
         if(player.getCurrentLocation().getDirections().isEmpty())
        {
            JPanel buttonsPanel = ui.messageWindow("There is no ways, you trapped here U must kill the monesters to open new ways");
            
        }
        else
        {
            JPanel buttonsPanel = ui.messageWindow("these are the posiple ways to go");
            for(String direction : player.getCurrentLocation().getDirections())
            {
                ui.waysButtonsMaker(buttonsPanel, direction);
            }
        }
    }
    
    /// <----------------------------------------------------------------------- monester interaction methods --------------------------------------------------------------------------------------->
    public void fight()
    {
        // appling the fight and store its result, true if wins and false if lose
        boolean win = player.fightMonester();
         if(win)
         {
            // updating the environment, the buttons and the player status and sending the winning message to the player
            environement.update();
            
            //updateButtons(boolean doesRoomHaveMonester)
            ui.updateButtons(false); // as its already dead
            // updatePlayerStatus(int playerLives, int playerHealthPoints, int playerAttackPoints, int playerDefensePoints);
            ui.updatePlayerStatus(player.getLives(), player.getHealthPoints(), player.getAttackPoints(), player.getDefensePoints());
            ui.messageWindow("Congratulations U have won the fight");
        }
        else if(checkLosingCondition()) // checking if u dont have any more lives so, then u died and if that happen open the lossing window
        {
            ui.losingWindow();
        }
        else // u died but not losed
        {
            // update the background, buttons, status. and notify the player with the result
            // updateBackground(String roomName, String roomDiscription, String roomImagePath);
            ui.updateBackground(player.getCurrentLocation().getRoomName(), player.getCurrentLocation().getDescription(), player.getCurrentLocation().getImagePath());
            ui.updateButtons(doesRoomHaveMonester());
            ui.updatePlayerStatus(player.getLives(), player.getHealthPoints(), player.getAttackPoints(), player.getDefensePoints());
            ui.messageWindow("Sorry U died and teleported to the previous Room");
        }
    }
    
    public void monester()
    {
        // createMonesterWindow(String name,int monesterHealthPoints, int monesterFullHealth, int monesterAttackPoints, int monesterDefensePoints)
        ui.createMonesterWindow(player.getCurrentLocation().getMonester().getName(), player.getCurrentLocation().getMonester().getHealthPoints(), player.getCurrentLocation().getMonester().getFullHealth(), player.getCurrentLocation().getMonester().getAttackPoints(), player.getCurrentLocation().getMonester().getDefensePoints());
    }
    
    //<---------------------------------------------item methods ------------------------------------------->
        public void getRoomItems()
    {
        for(Item item : player.getCurrentLocation().getItems())
        {
            // contentButtonMaker(String itemName, String itemDiscription, int itemWeight, int itemAttackPoints, int itemDefensePoints, int itemHealthPoints
            ui.contentButtonMaker(item.getName(), item.getDescription(), item.getWeight(), item.getAttackPoints(), item.getDefensePoints(), item.getHealthPoints());
        }
    }
    
    public void takeItem(String itemName)
    {   
        Item desiredItem = searchItems(itemName ,player.getCurrentLocation().getItems());
            
        // checking if the item exist or not
        if (desiredItem == null)
        {
            ui.messageWindow("this item doesn't exist in this room");
    
        }
        else
        {
            if(player.takeItem(desiredItem))
            {
                if(checkWinningCondition())
                {
                    ui.winningWindow();
                }
                else
                {
                    ui.updatePlayerStatus(player.getLives(), player.getHealthPoints(), player.getAttackPoints(), player.getDefensePoints());
                    ui.messageWindow("you took " + desiredItem.getName() + " check ur `inventory' and 'status' for update");
                }
            }
            else
            {
                ui.messageWindow("You carry too much weight, u need to create more space by removing items.");
            }
        }
    }
    
    public void consume(String itemName)
    {
        //validating the user input if he has this item already
        Item desiredItem = searchItems(itemName, player.getInventory());
        // null point exception handelling
        if(desiredItem == null)
        {
            ui.messageWindow("this item is not in your inventory");
        }
        else
        {
            player.consumeItem(desiredItem);
            ui.updatePlayerStatus(player.getLives(), player.getHealthPoints(), player.getAttackPoints(), player.getDefensePoints());
            ui.messageWindow("Done, ur health point had gone up, u can check ur status");
        }
    }

    public void leaveItem(String itemName)
    {
        // validating the user input if he has this item already, 
        Item desiredItem = searchItems(itemName ,player.getInventory());
        
        //illegalArgumentexception
        if(desiredItem == null)
        {
            ui.messageWindow("this item doesn't exist in your inventory");
        }
        else
        {
            if(player.putItem(desiredItem))
            {
                ui.updatePlayerStatus(player.getLives(), player.getHealthPoints(), player.getAttackPoints(), player.getDefensePoints());
                environement.checkKeyConditoin();
                ui.messageWindow("done, ur status changed.the ways may be changed too");
            }
            else
            {
                ui.messageWindow("the room is already full with 4 items");
            }
        }
    }

    //<---------------------------------------------inventory methods ------------------------------------------->
    public int getInventorryItems()
    {
        for(Item item : player.getInventory())
        {
            // inventoryButtonMaker(String itemName, String itemDiscription, int itemWeight, int itemAttackPoints, int itemDefensePoints, int itemHealthPoints)
            ui.inventoryButtonMaker(item.getName(), item.getDescription(), item.getWeight(), item.getAttackPoints(), item.getDefensePoints(), item.getHealthPoints());
        } 
        return player.getInventory().size();
    }
    
    
    public void inventory()
    {
        // inventory(String playerName, int playerLives, int playerHealthPoints, int playerAttackPoints, int playerDefensePoints, int playerCarriedWeight);
        ui.makeInventoryWindow(player.getName(), player.getLives(), player.getHealthPoints(), player.getAttackPoints(), player.getDefensePoints(), player.getCarriedWeight());
    }
    
    
    
    
    //<---------------------------------------------Hellpers methods ------------------------------------------->
    private boolean doesRoomHaveMonester()
    {
        if(player.getCurrentLocation().getMonester() == null)
        {
            return false;
        }
        return true;
    }
    
    private Item searchItems(String name, ArrayList<Item>items)
    {
        Item desiredItem = null;
        for (Item item : items)
        {
            if(item.getName().equals(name))
            {    desiredItem = item;
                 break;
            }
        }
        return desiredItem;
    }
    
    public String welcome()
    {
        
        return String.format("Welcome " + player.getName() + " to The Great Gungeon \n" +
        "The Great Dungeon is an incredibly adventure game where u will face monesters\nfight them, collect items and weapons for the fight.\n\nyour main goal is to reach the kings vaults and take the treasure.\nthe teasere has 2 keys hidden in the dangeon u must find them too! \n" + 
        "Type 'help' if you need help. \n" +
        "your Commands word are 'quit', 'go','fight','take item', 'put item', 'consume item', 'status', 'ways, 'inventory', 'content', 'monester'"+
        "\nYou are now in " + player.getCurrentLocation().getDescription() + " your status is up there\n" + 
        "press 'ways button' to know the possible ways to go \n" +
        "-----------------------------------------------------------------------");
    }
    
    private boolean checkWinningCondition()
    {
        if(this.searchItems("treasure", player.getInventory()) != null && this.searchItems("key1", player.getInventory()) != null && this.searchItems("key2", player.getInventory()) != null)
        {
               return true;
               // return "Huuuuurayyyyyy, u have found the treasure and its 2 keys. u won the game.";
        } 
        return false;
    }
    
    private boolean checkLosingCondition()
    {
        if(player.getLives() == 0)
        {
            return true;
            // System.out.println("for sorry u don`t have any more lives. The game will be closed automatically. good luck next time");
        }
        return false;  
    }
}