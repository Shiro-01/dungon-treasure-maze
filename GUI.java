import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.sound.sampled.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Image;
import java.awt.Dimension;

import java.io.IOException;

/**
 * Write a description of class G here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GUI implements UI
{
    // instance variables - 
    private JFrame window, inventoryFrame;
    private Container con;
    private AudioPlayer musicPlayer, soundEffect;
    private JPanel titlePanel, backgroundPanel, buttonsPanel, playerStatusPanel, textAreaPanel, contentButtonsPanel, InventoryPanel;
    private JLabel background, title, lives, attackPoints, defensePoints, HPLabel;
    private JButton play, inventory, contentButton, wayButton, fightButton, monsterButton, continueButton;
    private JTextArea textArea;
    private JProgressBar HPBar;
    private ImageIcon image;
    
    private static final Font TITLE = new Font("Times",Font.ITALIC,50);
    private static final Font MEDUIM = new Font("Times",Font.ITALIC,30);
    private static final Font NORMAL = new Font("MV Boli",Font.PLAIN,20);
    
    private Playable game;

    /**
     * Constructor for objects of class GUI2
     */
    public GUI(Playable game) throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        // initialise instance variables
        musicPlayer = new AudioPlayer();
        
        this.makeMainWindow();
        this.game = game;
        soundEffect = new AudioPlayer(".\\res\\audio\\button.wav");        
    }
    
    //<-------------------------------------------------------------------------------------- Window makers ---------------------------------------------------------------------------------------------------------->
    //<------------------------------------------------------------------------------------------------main Window maker - ------------------------------------------------------------------------------------->
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    private void makeMainWindow()
    {
        // Creating the game frame and menu bar
        this.makeFrame();
        this.makeMenuBar();
         
        
        //ceating the game title
        titlePanel = new JPanel();
        titlePanel.setBounds(200, 200, 600, 90);
        titlePanel.setBackground(Color.black);
        titlePanel.setOpaque(false);
        window.add(titlePanel);

        title = new JLabel();
        title.setForeground(Color.white);
        title.setFont(TITLE);
        titlePanel.add(title);
        
        // creating textArea panal 
        textAreaPanel = new JPanel();
        textAreaPanel.setBounds(100, 200, 700, 250);
        textAreaPanel.setOpaque(false);
        con.add(textAreaPanel);
                
        //creating the textarea
        textArea = new JTextArea();
        textArea.setBounds(100, 200, 700, 250);
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(this.NORMAL);
        textArea.setLineWrap(true);
        textAreaPanel.add(textArea);
        
        //creating status panel
        playerStatusPanel = new JPanel();
        playerStatusPanel.setBounds(10, 10, 980, 30);
        playerStatusPanel.setVisible(false);
        playerStatusPanel.setOpaque(false);   
        playerStatusPanel.setLayout(new GridLayout());
        con.add(playerStatusPanel);
        
        //creating content Panel
        contentButtonsPanel = new JPanel();
        contentButtonsPanel.setBounds(750, 550, 200, 250);
        contentButtonsPanel.setVisible(true);
        contentButtonsPanel.setOpaque(false);   
        contentButtonsPanel.setLayout(new GridLayout(5,1));
        contentButtonsPanel.setVisible(false);
        con.add(contentButtonsPanel);
        
        //creating buttons pannel
        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(350, 600, 300, 500);
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new FlowLayout());
        con.add(buttonsPanel);
        
        //creating start button
        play = new JButton("New Game");
        play.setFont(TITLE);
        play.setBackground(Color.BLACK);
        play.setForeground(Color.WHITE);
        play.addActionListener(e ->
            { 
                game.newGame("player");
                // reposition of the title
                titlePanel.setBounds(100, 100, 800, 90);
            }
            );
        buttonsPanel.add(play);
        
        continueButton = new JButton("Continue");
        continueButton.setFont(TITLE);
        continueButton.setBackground(Color.BLACK);
        continueButton.setForeground(Color.WHITE);
        continueButton.addActionListener(e ->
            { 
                game.loadGame();
                titlePanel.setBounds(100, 100, 800, 90);
            }
            );
            
        buttonsPanel.add(continueButton);
        
        this.makeBackground();
        DiplaytestAnimation("The Great Danguon", title);
    }
    
    private void makeFrame()
    {
        window = new JFrame();
        window.setLayout(null);
        window.setTitle("The Great Danguon");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1000, 860);
        window.setVisible(true);
        window.setResizable(false);
        
        //putting a game icon
        ImageIcon image = new ImageIcon(".\\res\\photos\\enterance.png");
        window.setIconImage(image.getImage());
        
        //creating the containner
        con = window.getContentPane();
        con.setBackground(Color.BLACK);
        con.setVisible(true);
    }
    
    private void makeMenuBar()
    {
        // menu bar -------------------------------------------------------------------->
        JMenuBar menubar = new JMenuBar();
        window.setJMenuBar(menubar);
    
        // File Menu ----------------------------------------------------------------->        
        JMenu file = new JMenu("File");
        menubar.add(file);
        
            // file memu items
            JMenuItem newGame = new JMenuItem("New Game");
                newGame.addActionListener(e -> {playerStatusPanel.removeAll(); game.newGame("player");});
                file.add(newGame);
                
            JMenuItem quit = new JMenuItem("Quit");
                quit.addActionListener(e -> {System.exit(0);});
                file.add(quit);
            
            
        // file memu items
            JMenuItem save = new JMenuItem("Save");
                save.addActionListener(e -> {game.saveGame();});                
                file.add(save);
        
        // Setting menu--------------------------------------------------------------->
        JMenu settings = new JMenu("Settings");
        menubar.add(settings);

            // Settings memu items
            JMenuItem muteAudio = new JMenuItem("Stop Audio");
                muteAudio.addActionListener(e -> musicPlayer.stop());
                
            JMenuItem unmuteAudio = new JMenuItem("Play Audio");
                muteAudio.addActionListener(e -> musicPlayer.start());

            settings.add(muteAudio);
            settings.add(unmuteAudio);
    }  
    
    private void makeBackground()
    {
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, 1000, 800);
        backgroundPanel.setBackground(Color.black);
        con.add(backgroundPanel);
        
        image = new ImageIcon(".\\res\\photos\\enterance.png");
        background = new JLabel(image);
        backgroundPanel.add(background);
    }
    
    //<-------------------------------------------------------------------------------game play window maker---------------------------------------------------------------------------------------->
    public void gamePlayWindowMaker(String playerName, int playerLives, int playerHealthPoints, int playerAttackPoints, int playerDefensePoints, boolean doesRoomHaveMonester, String roomName, String roomDiscription, String roomImagePath)
    {
        //creating the status bar        
        this.makePlayerStatusPanel(playerName, playerLives, playerHealthPoints, playerAttackPoints, playerDefensePoints); // ask
        
        this.makeGamePlayButtons(doesRoomHaveMonester);
        //updating the background, title and text according to the current room
        this.updateBackground(roomName, roomDiscription, roomImagePath);
    }
    
    /**
     * Method makePlayerStatusPanel makes the player status panel
     *
     * @param player A parameter passed by the game 
     */
    private void makePlayerStatusPanel(String playerName, int playerLives, int playerHealthPoints, int playerAttackPoints, int playerDefensePoints)
    {
        playerStatusPanel.setVisible(true);
        //creating hp pannel that holds both label and bar
        JPanel HPPanel = new JPanel();
        HPPanel.setLayout(new FlowLayout());
        HPPanel.getSize(new Dimension(350, 50));
        HPPanel.setOpaque(false);
        HPPanel.setBackground(Color.WHITE);
                
        // creating hp label
        HPLabel = new JLabel(playerName);
        HPLabel.setForeground(Color.WHITE);
        HPLabel.setFont(this.NORMAL);
        HPPanel.add(HPLabel);
        
        // creating hp bar
        HPBar = new JProgressBar(0, 100);
        HPBar.setPreferredSize(new Dimension(150,30));
        HPBar.setStringPainted(true);
        HPBar.setBackground(Color.red);
        HPBar.setForeground(Color.green);
        HPBar.setValue(playerHealthPoints);
        HPPanel.add(HPBar);
        
        //adding the label and the bar to the hp pannel
        playerStatusPanel.add(HPPanel);
        
        //creating no of lives label and panel only if a player
        JPanel livesPanel = new JPanel();
        livesPanel.setSize(40, 30);
        livesPanel.setOpaque(false);
        
        lives = new JLabel("Lives: "+ playerLives);
        lives.setForeground(Color.WHITE);
        lives.setFont(this.NORMAL);
        livesPanel.add(lives);
        playerStatusPanel.add(livesPanel);
        
        
        //creating attack points
        JPanel attackPointsPanel = new JPanel();
        attackPointsPanel.setSize(40, 30);
        attackPointsPanel.setOpaque(false);
        playerStatusPanel.add(attackPointsPanel);

        
        attackPoints = new JLabel("attack Points: "+ playerAttackPoints);
        attackPoints.setForeground(Color.WHITE);
        attackPoints.setFont(this.NORMAL);
        attackPointsPanel.add(attackPoints);
        
        //creating defense points
        JPanel defensePointsPanel = new JPanel();
        defensePointsPanel.setSize(40, 30);
        defensePointsPanel.setOpaque(false);
        playerStatusPanel.add(defensePointsPanel);
        
        defensePoints = new JLabel("defense Points: "+ playerDefensePoints);
        defensePoints.setForeground(Color.WHITE);
        defensePoints.setFont(this.NORMAL);
        defensePointsPanel.add(defensePoints);
    }
    
    private void makeGamePlayButtons(boolean doesRoomHaveMonester)
    {
        buttonsPanel.removeAll();
        buttonsPanel.setLayout(new FlowLayout());  //new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setBounds(150, 750, 700, 100);
        
        monsterButton = new JButton("Monster");
            monsterButton.setBackground(Color.BLACK);
            monsterButton.setForeground(Color.WHITE);
            monsterButton.setFont(this.NORMAL);
            monsterButton.addActionListener(e -> 
            {
                game.monester();
                soundEffect.start();
            }
            );
        
        fightButton = new JButton("Fight");
            fightButton.setBackground(Color.BLACK);
            fightButton.setForeground(Color.WHITE);
            fightButton.addActionListener(e -> {game.fight(); soundEffect.start();});
            fightButton.setFont(this.NORMAL);
        
        if(!doesRoomHaveMonester)
        {
            monsterButton.setEnabled(false);
            fightButton.setEnabled(false);
        }
            
        wayButton = new JButton("ways");
            wayButton.setBackground(Color.BLACK);
            wayButton.setForeground(Color.WHITE);
            wayButton.addActionListener(e -> {game.ways(); soundEffect.start();});
            wayButton.setFont(this.NORMAL);
        
        // monsterButton.addActionListener(e -> player.getCurrentRoom().ge)   
        contentButton = new JButton("Content");
            contentButton.setBackground(Color.BLACK);
            contentButton.setForeground(Color.WHITE);
            contentButton.addActionListener(e -> {soundEffect.start(); contentButton.setVisible(false); content();});
            contentButton.setFont(this.NORMAL);
            
        inventory = new JButton("Inventory");
            inventory.setBackground(Color.BLACK);
            inventory.setForeground(Color.WHITE);
            inventory.setFont(this.NORMAL);
            inventory.addActionListener(e -> {soundEffect.start(); game.inventory();});
            
        buttonsPanel.add(monsterButton);
        buttonsPanel.add(fightButton);
        buttonsPanel.add(wayButton);
        buttonsPanel.add(inventory);
        buttonsPanel.add(contentButton);
    }   
    
    //<--------------------------------------------------------------------------- Inventory Window maker ----------------------------------------------------------------------------------------------->
    public void makeInventoryWindow(String playerName, int playerLives, int playerHealthPoints, int playerAttackPoints, int playerDefensePoints, int playerCarriedWeight)
    {
        this.inventoryFrame(playerName, playerLives, playerHealthPoints, playerAttackPoints, playerDefensePoints, playerCarriedWeight);
        
        //adding buttons for each iteam, returning number of assigned slots.
        int n = game.getInventorryItems();
        
        //adding the empty slots
        while (n < 8)
        {
            JButton temp = new JButton();
                temp.setBackground(Color.black);
                temp.setForeground(Color.WHITE);
                temp.setFont(NORMAL);
                
            InventoryPanel.add(temp);
            n++;
        }
        
        //adding cancel button
        JButton returnButton = new JButton(" < ");
                returnButton.setBackground(Color.BLACK);
                returnButton.setForeground(Color.WHITE);
                returnButton.setFont(NORMAL);
                returnButton.addActionListener(e ->
                {
                 inventoryFrame.dispose();
                 soundEffect.start();
                }
                );
        InventoryPanel.add(returnButton);
        
        //adding background pic
        JPanel inventoryBackgroundPanel = new JPanel();
        inventoryBackgroundPanel.setBounds(0, 0, 800, 650);
        inventoryBackgroundPanel.setBackground(Color.black);
        inventoryFrame.getContentPane().add(inventoryBackgroundPanel);
        
        image = new ImageIcon(".\\res\\photos\\inventory.png");
        JLabel inventoryBackground = new JLabel(image);
        inventoryBackgroundPanel.add(inventoryBackground);
    }
    
    private void inventoryFrame(String playerName, int playerLives, int playerHealthPoints, int playerAttackPoints, int playerDefensePoints, int playerCarriedWeight)
    {
        //creating inventory jframe
        inventoryFrame = new JFrame();
        inventoryFrame.setLayout(null);
        inventoryFrame.setTitle("Inventory");
        inventoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inventoryFrame.setLocation(new Point(100, 175));
        inventoryFrame.setSize(800, 650);
        inventoryFrame.setVisible(true);
        inventoryFrame.setResizable(false);
        
        //creating the containner
        Container con = inventoryFrame.getContentPane();
        con.setBackground(Color.BLACK);
        con.setVisible(true);
        
        // Adding inventory word as a title
        JPanel inventoryNamePanel = new JPanel();
        inventoryNamePanel.setBounds(175, 10, 450, 100);
        inventoryNamePanel.setBackground(Color.WHITE);
        inventoryNamePanel.setOpaque(false);
        inventoryNamePanel.setVisible(true);
        con.add(inventoryNamePanel);

        JLabel inventoryName = new JLabel("Inventory");
        inventoryName.setForeground(Color.white);
        inventoryName.setFont(TITLE);
        inventoryName.setVisible(true);
        inventoryNamePanel.add(inventoryName);
        
        //adding plater name
        JPanel playerNamePanel = new JPanel();
        playerNamePanel.setBounds(50, 75, 100, 30);
        playerNamePanel.setLayout(new BorderLayout());
        playerNamePanel.setOpaque(false);
        playerNamePanel.add(this.HPLabel, BorderLayout.WEST);
        con.add(playerNamePanel);

        
        //player info panel
        JPanel generalInfoPanel = new JPanel();
        generalInfoPanel.setBounds(50, 100, 400, 200);
        generalInfoPanel.setBackground(Color.WHITE);
        generalInfoPanel.setOpaque(false);
        generalInfoPanel.setVisible(true);
        generalInfoPanel.setLayout(new GridLayout(3,2));
        con.add(generalInfoPanel);
        
        //adding player lives number
        JLabel playerLivesLabel = new JLabel("Lives: "+ playerLives);
        playerLivesLabel.setForeground(Color.WHITE);
        playerLivesLabel.setFont(this.NORMAL);
        generalInfoPanel.add(playerLivesLabel);
        
        //adding player hp
        JLabel playerHP = new JLabel("HP: " + playerHealthPoints);
        playerHP.setForeground(Color.white);
        playerHP.setFont(NORMAL);
        playerHP.setVisible(true);
        generalInfoPanel.add(playerHP);
        
        //adding player ATK points
        JLabel playerAtK = new JLabel("Attack points: "+ playerAttackPoints);
        playerAtK.setForeground(Color.WHITE);
        playerAtK.setFont(this.NORMAL);
        generalInfoPanel.add(playerAtK);
        
        //adding player def points
        JLabel playerDef = new JLabel("Defense points: "+ playerDefensePoints);
        playerDef.setForeground(Color.WHITE);
        playerDef.setFont(this.NORMAL);
        generalInfoPanel.add(playerDef);

        //adding player carried weight
        JLabel carriedWeight = new JLabel("Weight: " + playerCarriedWeight + "/ 30");
        carriedWeight.setForeground(Color.white);
        carriedWeight.setFont(NORMAL);
        carriedWeight.setVisible(true);
        generalInfoPanel.add(carriedWeight);
        
        InventoryPanel = new JPanel();
        InventoryPanel.setBounds(50, 350, 700, 200);
        InventoryPanel.setBackground(Color.WHITE);
        InventoryPanel.setOpaque(false);
        InventoryPanel.setVisible(true);
        InventoryPanel.setLayout(new GridLayout(3,3));
        con.add(InventoryPanel);
    }
    
    public void inventoryButtonMaker(String itemName, String itemDiscription, int itemWeight, int itemAttackPoints, int itemDefensePoints, int itemHealthPoints)
    {
        JButton temp = new JButton(itemName);
                temp.setBackground(Color.black);
                temp.setForeground(Color.WHITE);
                temp.setFont(NORMAL);
                temp.addActionListener(e ->
                {
                 
                    JFrame itemWindow = createItemWindow(itemName, itemDiscription, itemWeight, itemAttackPoints, itemDefensePoints, itemHealthPoints);
                    Container content = itemWindow.getContentPane();
                    
                     //creating take button
                    JPanel itemButtonsPanel = new JPanel();
                    itemButtonsPanel.setBounds(175, 250, 500, 50);
                    itemButtonsPanel.setOpaque(false);
                    itemButtonsPanel.setLayout(new FlowLayout());
                    itemButtonsPanel.setBackground(Color.WHITE);
                    itemButtonsPanel.setForeground(Color.WHITE);
                    
                    if (itemHealthPoints == 0) 
                    {
                        JButton leaveItemButton = new JButton("Leave");
                            leaveItemButton.setBackground(Color.BLACK);
                            leaveItemButton.setForeground(Color.WHITE);
                            leaveItemButton.setFont(this.NORMAL);
                            leaveItemButton.addActionListener(x ->
                            {
                                soundEffect.start();
                                itemWindow.dispose();
                                game.leaveItem(itemName);
                                inventoryFrame.dispose();
                            }
                            );
                        itemButtonsPanel.add(leaveItemButton); 
                    }
                    else
                    {
                        JButton consumeItemButton = new JButton("Consume");
                            consumeItemButton.setBackground(Color.BLACK);
                            consumeItemButton.setForeground(Color.WHITE);
                            consumeItemButton.setFont(this.NORMAL);
                            consumeItemButton.addActionListener(x ->
                            {
                                itemWindow.dispose();
                                soundEffect.start();
                                game.consume(itemName);
                                inventoryFrame.dispose();
                            }
                            );
                        itemButtonsPanel.add(consumeItemButton);                         
                    }
                    JButton closeButton = new JButton("Close");
                        closeButton.setBackground(Color.black);
                        closeButton.setForeground(Color.WHITE);
                        closeButton.setFont(this.NORMAL);
                        closeButton.addActionListener(y -> {soundEffect.start(); itemWindow.dispose();});
             
                         
                    itemButtonsPanel.add(closeButton);
                    
                    content.add(itemButtonsPanel);

                }
                );
            InventoryPanel.add(temp);
    } 
    
    //<---------------------------------------------------------------------------  Item Window Maker----------------------------------------------------------------------------------------------->
    private JFrame createItemWindow(String name, String itemDiscription, int itemWeight, int itemAttackPoints, int itemDefensePoints, int itemHealthPoints)
    {
        JFrame itemWindow = new JFrame();
        itemWindow.setTitle("Item");
        itemWindow.setVisible(true);
        itemWindow.setDefaultCloseOperation(itemWindow.DISPOSE_ON_CLOSE);
        itemWindow.setSize(new Dimension(900, 350));
        itemWindow.setLocation(new Point(50, 370));;
        itemWindow.setResizable(false);
        
        Container container = itemWindow.getContentPane(); 
        container.setBackground(Color.BLACK);
        container.setForeground(Color.WHITE);
        container.setLayout(null);
        
        
        // Adding Item name as a title
        JPanel itemNamePanel = new JPanel();
        itemNamePanel.setBounds(200, 10, 450, 100);
        itemNamePanel.setBackground(Color.WHITE);
        itemNamePanel.setOpaque(false);
        itemNamePanel.setVisible(true);
        container.add(itemNamePanel);

        JLabel itemName = new JLabel(name);
        itemName.setForeground(Color.white);
        itemName.setFont(TITLE);
        itemName.setVisible(true);
        itemNamePanel.add(itemName);
        
        
        
        //creating item status panel
        JPanel itemStatusBar = new JPanel();
        itemStatusBar.setBounds(10, 80, 880, 50);
        itemStatusBar.setOpaque(false);
        itemStatusBar.setLayout(new BoxLayout(itemStatusBar, BoxLayout.X_AXIS));
        container.add(itemStatusBar);
        makeItemStatusPanel(itemStatusBar, itemWeight, itemAttackPoints, itemDefensePoints, itemHealthPoints);
        
        
        // creating textArea panal 
        JPanel itemDescriptionPanel = new JPanel();
        itemDescriptionPanel.setBounds(25, 130, 850, 100);
        itemDescriptionPanel.setOpaque(false);
        container.add(itemDescriptionPanel);
                
        //creating the textarea
        JTextArea itemDescription = new JTextArea(itemDiscription);
        itemDescription.setForeground(Color.WHITE);
        itemDescription.setBackground(Color.black);
        itemDescription.setOpaque(false);
        itemDescription.setLineWrap(true);
        itemDescription.setFont(NORMAL);
        itemDescription.setBounds(25, 130, 850, 100);
        itemDescriptionPanel.add(itemDescription);
        
        return itemWindow;
    }
    
    /**
     * Method makeItemStatusPanel makes Item status panel
     *
     * @param statusPanel the status panel that will hold the status info
     * @param item the item that we want to represent his status
     */
    private void makeItemStatusPanel(JPanel statusPanel, int itemWeight, int itemAttackPoints, int itemDefensePoints, int itemHealthPoints)
    {
        // adding Item weight
        JPanel weightPanel = new JPanel();
        weightPanel.setSize(40, 30);
        weightPanel.setOpaque(false);
        
        JLabel weight = new JLabel("Weight: "+ itemWeight);
        weight.setForeground(Color.WHITE);
        weight.setFont(this.NORMAL);
        weightPanel.add(weight);
        statusPanel.add(weightPanel);
        
        addingAttackAndDefenseToStatus(statusPanel, itemAttackPoints, itemDefensePoints);
        
        // adding healing points label
        JPanel healingPointsPanel = new JPanel();
        healingPointsPanel.setSize(250, 50);
        healingPointsPanel.setOpaque(false);
        
        JLabel healingPoints = new JLabel("Healing points: "+ itemHealthPoints);
        healingPoints.setForeground(Color.WHITE);
        healingPoints.setFont(this.NORMAL);
        healingPointsPanel.add(healingPoints);
        statusPanel.add(healingPointsPanel);
                        
    }
    
    //<---------------------------------------------------------------------------  Monester Window Maker----------------------------------------------------------------------------------------------->
    public void createMonesterWindow(String name,int monesterHealthPoints, int monesterFullHealth, int monesterAttackPoints, int monesterDefensePoints)
    {
        //creating a new JFrame
        JFrame monester = new JFrame();
        monester.setTitle("Monster Status");
        monester.setVisible(true);
        monester.setDefaultCloseOperation(monester.DISPOSE_ON_CLOSE);
        monester.setSize(new Dimension(900, 200));
        monester.setLocation(new Point(50, 370));
        monester.setResizable(false);

        //creating a container
        Container content = monester.getContentPane(); 
        content.setBackground(Color.BLACK);
        content.setForeground(Color.WHITE);
        content.setLayout(null);
        
        // Adding monester name as a title
        JPanel monesterNamePanel = new JPanel();
        monesterNamePanel.setBounds(200, 10, 450, 100);
        monesterNamePanel.setBackground(Color.WHITE);
        monesterNamePanel.setOpaque(false);
        monesterNamePanel.setVisible(true);
        content.add(monesterNamePanel);

        JLabel monesterName = new JLabel(name);
        monesterName.setForeground(Color.white);
        monesterName.setFont(TITLE);
        monesterName.setVisible(true);
        monesterNamePanel.add(monesterName);
        
        //creating a status Pannel
        JPanel monesterStatusPanel = new JPanel();
        monesterStatusPanel.setBounds(10, 80, 900, 100);
        monesterStatusPanel.setVisible(true);
        monesterStatusPanel.setOpaque(false);   
        monesterStatusPanel.setLayout(new BoxLayout(monesterStatusPanel, BoxLayout.X_AXIS));
        content.add(monesterStatusPanel);
        
        
        //creating the monester status bar
        JPanel MHPPanel = new JPanel();
        MHPPanel.setLayout(new FlowLayout());
        MHPPanel.getSize(new Dimension(300, 50));
        MHPPanel.setOpaque(false);
        MHPPanel.setBackground(Color.WHITE);
                
        // creating hp label
        JLabel MHPLabel = new JLabel("HP: " + monesterHealthPoints + " ");
        MHPLabel.setForeground(Color.WHITE);
        MHPLabel.setFont(this.NORMAL);
        MHPPanel.add(MHPLabel);
        
        // creating hp bar
        JProgressBar MHPBar = new JProgressBar(0, monesterFullHealth);
        MHPBar.setPreferredSize(new Dimension(150,30));
        MHPBar.setStringPainted(true);
        MHPBar.setBackground(Color.red);
        MHPBar.setForeground(Color.green);
        MHPBar.setValue(monesterHealthPoints);
        MHPPanel.add(MHPBar);
        
        //adding the label and the bar to the hp pannel
        monesterStatusPanel.add(MHPPanel);
        
        addingAttackAndDefenseToStatus(monesterStatusPanel, monesterAttackPoints, monesterDefensePoints); //u can find it in the helper section
    }
    
    //<---------------------------------------------------------------------------------- wining window maker ---------------------------------------------------------------------------------------->
    public void winningWindow()
    {
        this.con.removeAll();
        
        // creating textArea panal 
        JPanel winingPanel = new JPanel();
        winingPanel.setBounds(50, 350, 900, 300);
        winingPanel.setOpaque(false);
        this.con.add(winingPanel);
                
        //creating the textarea
        JTextArea winingMessage = new JTextArea("Huuuuurayyyyyy, u have found the treasure and its 2 keys. u won the game.");
        winingMessage.setForeground(Color.WHITE);
        winingMessage.setBackground(Color.black);
        winingMessage.setBounds(0, 0, 900, 300);
        winingMessage.setOpaque(false);
        winingMessage.setLineWrap(true);
        winingMessage.setFont(TITLE);
        winingPanel.add(winingMessage);
        
        //adding cleberation window
         //adding background pic
        JPanel winingBackgroundPanel = new JPanel();
        winingBackgroundPanel.setBounds(0, 0, 1000, 900);
        winingBackgroundPanel.setBackground(Color.black);
        this.con.add(winingBackgroundPanel);
        
        image = new ImageIcon(".\\res\\photos\\cel.png");
        background = new JLabel(image);
        winingBackgroundPanel.add(background);
    }
    
    //<---------------------------------------------------------------------------------- losing window maker ---------------------------------------------------------------------------------------->
    public void losingWindow()
    {
        this.con.removeAll();
        con.setLayout(null);
        // creating textArea panal 
        JPanel winingPanel = new JPanel();
        winingPanel.setBounds(50, 350, 900, 300);
        winingPanel.setBackground(Color.BLACK);
        winingPanel.setOpaque(false);
        this.con.add(winingPanel);
                
        //creating the textarea
        JTextArea losingMessage = new JTextArea("for sorry u don`t have any more lives. Close the game and start again. good luck next time");
        losingMessage.setForeground(Color.WHITE);
        losingMessage.setBackground(Color.black);
        losingMessage.setBounds(0, 0, 900, 300);
        losingMessage.setOpaque(false);
        losingMessage.setLineWrap(true);
        losingMessage.setFont(TITLE);
        winingPanel.add(losingMessage);
        
        //adding cleberation window
         //adding background pic
        JPanel winingBackgroundPanel = new JPanel();
        winingBackgroundPanel.setBounds(0, 0, 1000, 900);
        winingBackgroundPanel.setBackground(Color.black);
        this.con.add(winingBackgroundPanel);
        
        image = new ImageIcon(".\\res\\photos\\los.png");
        background = new JLabel(image);
        winingBackgroundPanel.add(background);
    }
    
    //<-------------------------------------------------------------------------------------------------- buttons makers -- called by the game ---------------------------------------------------------------------------------------->
    public void contentButtonMaker(String itemName, String itemDiscription, int itemWeight, int itemAttackPoints, int itemDefensePoints, int itemHealthPoints)
    {
        JButton temp = new JButton(itemName);
                temp.setBackground(Color.BLACK);
                temp.setForeground(Color.WHITE);
                temp.setFont(NORMAL);
                temp.addActionListener(e ->
                {
                    soundEffect.start();
                    // createItemWindow(String name, String itemDiscription, int itemWeight, int itemAttackPoints, int itemDefensePoints, int itemHealthPoints)
                    JFrame itemWindow = createItemWindow(itemName, itemDiscription, itemWeight, itemAttackPoints, itemDefensePoints, itemHealthPoints);
                    Container container = itemWindow.getContentPane();
                    
                     //creating take button
                    JPanel itemButtonsPanel = new JPanel();
                    itemButtonsPanel.setBounds(175, 250, 500, 50);
                    itemButtonsPanel.setOpaque(false);
                    itemButtonsPanel.setLayout(new FlowLayout());
                    itemButtonsPanel.setBackground(Color.WHITE);
                    itemButtonsPanel.setForeground(Color.WHITE);
                    
                        
                    JButton takeItemButton = new JButton("take");
                        takeItemButton.setBackground(Color.BLACK);
                        takeItemButton.setForeground(Color.WHITE);
                        takeItemButton.setFont(this.NORMAL);
                        takeItemButton.addActionListener(x ->
                        {
                            itemWindow.dispose();
                            soundEffect.start();
                            game.takeItem(itemName);
                            contentButtonsPanel.setVisible(false);
                            contentButton.setVisible(true);
                        }
                        );
                    
                    JButton closeButton = new JButton("Close");
                        closeButton.setBackground(Color.BLACK);
                        closeButton.setForeground(Color.WHITE);
                        closeButton.setFont(this.NORMAL);
                        closeButton.addActionListener(y -> {soundEffect.start(); itemWindow.dispose();});
             
                    itemButtonsPanel.add(takeItemButton);      
                    itemButtonsPanel.add(closeButton);
                    
                    container.add(itemButtonsPanel);
                }
                );
            contentButtonsPanel.add(temp);
    }
    
    public void waysButtonsMaker(JPanel buttonsPanel, String Text)
    {
        JButton temp = new JButton(Text);
            temp.setBackground(Color.BLACK);
            temp.setForeground(Color.WHITE);
            temp.setFont(NORMAL);
            temp.addActionListener(e ->
            {
                try
                {
                    soundEffect.start();
                    game.goRoom(temp.getText());
                    ((JFrame) buttonsPanel.getRootPane().getParent()).dispose();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            });
        buttonsPanel.add(temp);   
    }
    
    //<-------------------------------------------------------------------------------------------Special Buttons methods ----------------------------------------------------------------------------->
    private void content()
    {
        contentButtonsPanel.removeAll();
        contentButtonsPanel.setVisible(true);
        
        //getting room items from the game
        game.getRoomItems();
        
        //adding return button
        JButton returnButton = new JButton(" < ");
                returnButton.setBackground(Color.BLACK);
                returnButton.setForeground(Color.WHITE);
                returnButton.setFont(NORMAL);
                returnButton.addActionListener(e ->
                {
                    soundEffect.start();
                    contentButtonsPanel.setVisible(false);
                    contentButton.setVisible(true);
                }
                );
        contentButtonsPanel.add(returnButton);
    }
    
    //<--------------------------------------------------------------------------------ui methods -- GamePlay update ---------------------------------------------------------------------------->
    public void updateBackground(String roomName, String roomDiscription, String roomImagePath)
    {
        // con.remove(background);
        image = new ImageIcon(roomImagePath);
        background.setIcon(image);
        // con.add(background);
        
        
        
        DiplaytestAnimation(roomName, title);
        DiplaytestAnimation(roomDiscription, textArea);
        window.repaint();
    }
    
    public void updatePlayerStatus(int playerLives, int playerHealthPoints, int playerAttackPoints, int playerDefensePoints)
    {
        HPBar.setValue(playerHealthPoints);
        defensePoints.setText("defense Points: "+ playerDefensePoints);
        attackPoints.setText("attack Points: "+ playerAttackPoints);
        lives.setText("Lives: "+ playerLives);

    }
    
    public void updateButtons(boolean doesRoomHaveMonester)
    {
        if(doesRoomHaveMonester)
        {
            monsterButton.setEnabled(true);
            fightButton.setEnabled(true);
        }  
        else
        {
            monsterButton.setEnabled(false);
            fightButton.setEnabled(false);
        }
    }
    
    //<---------------------------------------------------------------------------------- Helper methods---------------------------------------------------------------------------------------->
    private void addingAttackAndDefenseToStatus(JPanel statusPanel, int attackPoints, int defensePoints)
    {
        // adding attack points label
        JPanel attackPointsPanel = new JPanel();
        attackPointsPanel.setSize(40, 30);
        attackPointsPanel.setOpaque(false);
        
        JLabel attackPoint = new JLabel("Atk Points: "+ attackPoints);
        attackPoint.setForeground(Color.WHITE);
        attackPoint.setFont(this.NORMAL);
        attackPointsPanel.add(attackPoint);
        statusPanel.add(attackPointsPanel);
                
        // adding defense points label
        JPanel defensePointsPanel = new JPanel();
        defensePointsPanel.setSize(40, 30);
        defensePointsPanel.setOpaque(false);
        
        JLabel defensePoint = new JLabel("Def points: "+ defensePoints);
        defensePoint.setForeground(Color.WHITE);
        defensePoint.setFont(this.NORMAL);
        defensePointsPanel.add(defensePoint);
        statusPanel.add(defensePointsPanel);

    }
    
    public JPanel messageWindow(String text)
    {
        JFrame messageFrame = new JFrame();
        messageFrame.setTitle("Message");
        messageFrame.setVisible(true);
        messageFrame.setDefaultCloseOperation(messageFrame.DISPOSE_ON_CLOSE);
        messageFrame.setSize(new Dimension(400, 250));
        messageFrame.setLocation(new Point(300, 350));
        messageFrame.setResizable(false);

        Container container = messageFrame.getContentPane(); 
        container.setBackground(Color.BLACK);
        container.setForeground(Color.WHITE);
        container.setLayout(null);        
        
        // creating textArea panal 
        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(25, 10, 350, 100);
        messagePanel.setOpaque(false);
        container.add(messagePanel);
                
        //creating the textarea
        JTextArea message = new JTextArea(text);
        message.setForeground(Color.WHITE);
        message.setBackground(Color.black);
        message.setOpaque(false);
        message.setLineWrap(true);
        message.setFont(NORMAL);
        message.setBounds(25, 10, 350, 100);
        messagePanel.add(message);
        
        Container content = messageFrame.getContentPane();
            //creating buttons pannel
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setBounds(50, 65, 300, 250);
            buttonsPanel.setOpaque(false);
            buttonsPanel.setLayout(new FlowLayout());  //buttonsPanel,BoxLayout.Y_AXIS)
            content.add(buttonsPanel);
            
        return buttonsPanel;
    }
    
    
    private void  DiplaytestAnimation(String content, Object host)
    {
        int pos = 0;
        String print = "";
        while (pos < content.length()) {
            char test = content.charAt(pos);
            
            if(test == '\n')
            {
                try {
                    Thread.sleep(0); //500
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }   
            }
            print = print + String.valueOf(test); // add to the print
            if(host instanceof JLabel)
            {
                title.setText(print);
            }
            else if(host instanceof JTextArea)
            {
                textArea.setText(print);
            }
            
            try {
                Thread.sleep(0); //35
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            
            pos++;
        }
    }
}
