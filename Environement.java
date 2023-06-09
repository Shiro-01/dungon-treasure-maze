import java.util.HashMap;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * this class is used to provide the game environement-- Cohesion concept.
 *
 * @author (AbdelRahman Hewala, nickname Shiro)
 * @version (14,11,2022)
 */
public class Environement implements Serializable
{
    private Room startRoom;  // privqate to be read only
    private Room entrance;
    private Room mainHall;
    private Room cave1 ;
    private Room northP ;
    private Room forest ;
    private Room desert ;
    private Room cave2;
    private Room welcomingHall ;
    private Room kingHall;
    private Room vault1;
    private Room vault2;
    private Room portal1;
    private Room portal2;
    private Room portal3;
    private Room island;
    
    // specifc conditoin to make sure that environment update happens one time
    private boolean northpNotDone = true;
    private boolean mainHallNotDone = true;
    private boolean vault2NotDone = true;
    private boolean kingHallNotDone = true;
    private boolean islandNotDone = true;
    private boolean desertNotDone = true;
    private boolean welcomingHall NotDone = true;
    
    /**
     * Constructor for objects of class Map
     */
    public Environement()
    {
        // initialise instance variables
        Room entrance, mainHall, cave1 , northP , forest , desert , welcomingHall , cave2, kingHall, vault1, vault2, portal1, portal2, portal3  ;

        // create the rooms
        this.entrance = new Room("the entrance of the great dungeon","Dare to enter, there is no going back.", ".\\res\\photos\\enterance.png");
        
        /*<----------------------------------------First Floor-------------------------------------------------------------------------------------------------------------------->*/
        this.mainHall = new Room("the Great main Hall", " the wonder that can't be exist\n----------------------------------\nOooh, may be there are some Skelaton monesters, u can get the monesters status if any by 'monster' command must,%n if any fight them by command 'fight' to continue.", ".\\res\\photos\\A_Shiro_the_Great_main_Hall.png");
        this.cave1  = new Room("A stranger cave","a cave that is full of materials. may it contain something usefull", ".\\res\\photos\\enterance.png");
        this.northP  = new Room("the north pole","teleported somehow, look out there is bears, u can fight or ignore them", ".\\res\\photos\\polarBears.png");
        this.forest  = new Room("The great GURA forest", "may u find some food for the journey,%n wait is that an island, we should get some boat to get there", ".\\res\\photos\\forest.png");
        this.desert  = new Room("The Great Sahara desert","its gardian the great Kobra, Kill it to collect extra item", ".\\res\\photos\\sahara.png");
        this.island = new Room("the hidden island", "am sure there is something important somewhere, but first kill monesters to search", ".\\res\\photos\\island.png");

        /*<----------------------------------------Second Floor-------------------------------------------------------------------------------------------------------------------->*/
        this.cave2 = new Room("The cave of dwarfs", "u may get some weapons",".\\res\\photos\\enterance.png");
        this.welcomingHall  = new Room("The Welcoming Hall", "where the king used to welcome his guest. it's always gaurded by Dark Knight.\nVanish them to continue ur trip.",".\\res\\photos\\knights.png");
        
        /*<----------------------------------------third Floor-------------------------------------------------------------------------------------------------------------------->*/
        this.kingHall = new Room("the Holly king's Hall", "the king died along ago, but his great gardian is still there thinking he will return someday.\nprepare to fight him. 'The Great fire Dragon '",".\\res\\photos\\firedragon.png");
        this.vault1 = new Room("the first king's vault", "is the treasure there?, ooooooooooooh, it a trap, the back doors are closed, there is a monester there u must fight it for your life\nUrgant Mission Kill the vault Gardian 'The Soul Reaper'", ".\\res\\photos\\King.png");
        this.vault2 = new Room("the second king's vault", "the treasure must be there check the room content", "D:\\Information engineering\\IE-S1\\SO2\\Labs reports\\lab 6\\prep\\Dungon Treasure Maze v2\\Rooms Photos\\treasure.png");
        this.portal1 = new Room("Portal 1", "choose ur teleportation level lv2, lv3, or out", ".\\res\\photos\\portal.png");
        this.portal2 = new Room("Portal 2", "choose ur teleportation level lv1, lv3, or out", ".\\res\\photos\\portal.png");
        this.portal3 = new Room("Portal 3", "choose ur teleportation level, lv2, lv1, or out", ".\\res\\photos\\portal.png");

        // initialise room exits
        // outside.setExits(null, theater, lab, pub);  // indicates room class is not flixable
        this.entrance.setExit("inside", this.mainHall);

        this.cave1 .setExit("back", this.mainHall);

        this.northP .setExit("back", this.mainHall);

        this.forest .setExit("back", this.mainHall);

        this.desert .setExit("back", this.mainHall);

        this.portal1.setExit("out", this.mainHall);
        this.portal1.setExit("lv2", this.portal2);
        this.portal1.setExit("lv3", this.portal3);

        this.portal2.setExit("out", this.welcomingHall );
        this.portal2.setExit("lv1", this.portal1);
        this.portal3.setExit("lv3", this.portal3);

        this.portal3.setExit("out", this.kingHall);
        this.portal3.setExit("lv2", this.portal2);
        this.portal3.setExit("lv1", this.portal1);

        this.welcomingHall .setExit("back", this.portal2);

        this.cave2.setExit("back", this.welcomingHall );

        this.kingHall.setExit("back", this.portal3);
        

        this.vault2.setExit("back", this.kingHall);
        
        this.startRoom = this.entrance;
        /*-----------------------------------------------------------------------------------------------------*/
        // room momester setters
        //setMonester(String name, int healthPoints, int attackPoints, int defense points)
        this.mainHall.setMonester("Skelaton soliders",20, 10, 2);
        this.desert .setMonester("The great kobra",30, 20, 3);
        this.northP .setMonester("Poler bears",40, 25, 4);
        this.welcomingHall .setMonester("Dark Knight",50, 30, 5);
        this.island.setMonester("sharks",60, 30, 6);
        this.kingHall.setMonester("The great Fire dragon",80, 40, 7);
        this.vault1.setMonester("The Soul Reaper",150, 40, 8);
        
        /*-----------------------------------room items setters------------------------------------------------------------*/ 
        //setItem(String name, String description, int weight, int attackPoints, int defensePoints, int healthPoints)
        this.mainHall.setItem("swordlv1", "a normal weapon to fight with", 3, 3, 0, 0);
        this.mainHall.setItem("armorlv1", "a normal armor for defense", 3, 0, 3, 0);
        this.mainHall.setItem("apples", "a source of health points", 3, 0, 0, 3);
        this.mainHall.setItem("ladder", "may be used to get to a higher place", 10, 0, 0, 0);
        
        this.cave1 .setItem("swordlv2", "a mid tier weapon ", 8, 6, 0, 0);
        this.cave1 .setItem("pizza", "increases ur health when u consume", 3, 0, 0, 10);
        this.cave1 .setItem("vas", "decoration item", 3, 2, 0, 0);
        // this.cave1 .setItem("gems", "can be trade with healthbut high wight", 20, 0, 0, 50);

        this.forest .setItem("meat", "meduim portion of health ", 5, 0, 0, 15);
        this.forest .setItem("bananas", "a source of health points", 3, 0, 0, 3);

        this.desert.setItem("swordlv2", "a mid tier weapon ", 8, 6, 0, 0);
        this.desert.setItem("apples", "a source of health points", 3, 0, 0, 3);
        this.desert.setItem("knife", "tiny weapon", 3, 1, 0, 0);
        this.desert.setItem("Sands", "calculates time", 5, 0, 0, 0);


        
        this.cave2.setItem("boat", "may be used to cross some water", 15, 0, 0, 0);
        this.cave2.setItem("machinegun", "a deadly attack weapon", 20, 40, 0, 0);

        this.vault1.setItem("machinegun", "a deadly attack weapon", 20, 40, 0, 0);

        this.vault2.setItem("treasure", "finnaly here its, u must have the 2 keys", 15, 0, 0, 0);
        /*---------------------------------SecretKeys---------------------------------------------------*/
        this.forest.setSecretKey("boat", "may be used to cross some water", 15, 0, 0, 0);
        
        // /*---------------------------------checking wining condition---------------------------------------------------*/
        // this.cave1 .setItem("key2", "the first key to the treasure", 3, 0, 0, 0);
        // this.cave1.setItem("boat", "may be used to cross some water", 15, 0, 0, 0);
        // this.desert.setItem("treasure", "finnaly here its, u must have the 2 keys", 15, 0, 0, 0);


    }
    
    /*-----------------------------------------------------------------------------------------------------*/
    //specific condition to appear the ways
    public void update()
    {
        if(mainHall.getMonester() == null && mainHallNotDone)  // must be killed to get back to the King's Hall
        {
            mainHall.setExit("firstleft", cave1 );
            mainHall.setExit("secondleft", northP );
            mainHall.setExit("firstright", forest );
            mainHall.setExit("secondright", desert );
            mainHall.setExit("straight", portal1);
            mainHallNotDone = false;
        }

        if(vault1.getMonester() == null && vault2NotDone)  // must be killed to get back to the King's Hall
        {
            vault2.setExit("back", kingHall);
            vault2NotDone = false;
        }
            
        if(kingHall.getMonester() == null && kingHallNotDone)  // must be killed to get back to the King's Hall
        {
            this.kingHall.setExit("right", this.vault2);
            this.kingHall.setExit("left", this.vault1);
            // System.out.println("the kings droped something check the room content, also your ways");
            this.kingHall.setItem("key2", "the first key to the treasure", 3, 0, 0, 0);
            kingHallNotDone = false;
        }
           
        if(island.getMonester()==null && islandNotDone)
        {
            this.island.setItem("key1", "the first key to the treasure", 3, 0, 0, 0);
            islandNotDone = false;
        }
        
        if(northP .getMonester()==null && northpNotDone)
        {
            // System.out.println("the bears droped something check the room content, also your ways");
            this.northP .setItem("armorlv3", "full body armor but it is heavy", 15, 0, 30, 0);
            northpNotDone = false;
        }
        
        if(desert.getMonester()==null && desertNotDone)
        {
            // System.out.println("the Kobra droped something check the room content, also your ways");
            this.desert.setItem("armorlv2", "meduim vest", 8, 0, 15, 0);
            desertNotDone = false;
        }
        
        if(welcomingHall .getMonester()==null && welcomingHall NotDone)
        {
            // System.out.println("the knights droped something check the room content, also your ways");
            this.welcomingHall .setItem("swordlv3", "a high tier weapon ", 10, 15, 0, 0);
            this.welcomingHall .setExit("straight", this.cave2);
            welcomingHall NotDone = false;
        }
    }

    /**
     * Method startRoom
     *-- Sart room of tghe environment getter --
     * @return The Start room of the environment
     */
    public Room startRoom()
    {
        // put your code here
        return startRoom;
    }

     /**
      * Method check if has a key
      *
      *this method meant to build the relationships between the iland and the other room
      */
    public void checkKeyConditoin()
    {
        if(forest.holdsKey())
        {
        // System.out.println("now we can cross the sea check ur available ways");
        this.forest .setExit("cross", this.island);
        this.island.setExit("back", this.forest );
        }
    }
    
    
}
