import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class is responsible for saving and loading the game state using serilization concept.
 *  
 *
 * @author (Abdelrahman Hewala) with some help from bro code youtube channel
 * @version (v1, 11/1/2023 v)
 */
public class GameSerializer
{
    // No - instance variables 
    /**
     * Constructor for objects of class Serializer
     */
    public GameSerializer()
    {
    }
    
    /**
     * Method serialize, saves the game state as a binnary stream in specific file
     *
     * @param game the game object that we want to save its state.
     */
    public void serialize(Game game)
    {
        try{
        FileOutputStream fileOut = new FileOutputStream(".\\data\\SavedGame.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(game);
        out.close();
        fileOut.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    
    /**
     * Method deserializer 
     * deserialize (reads) the saved previous game state that has been stored in a specific file as a binnary stream.
     *
     * @return The return value is a game object but with the previous game state
     */
    public Game deserialize()
    {
        Game game = null;
        
        try{
        FileInputStream fileIn = new FileInputStream(".\\data\\SavedGame.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try
        {
            game = (Game) in.readObject(); // u need to cast it manually as the class tzpe is not saved in binnarz stream.
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        in.close();
        fileIn.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        
        return game;
    }
    
    //Summery about the serialiyation -- just for reminding --
    // -------------------------------------------------------------------------------------
        // Serialization = the process of converting an object state into a byte stream.
        // Persists (saves the state) the object after program exits
        // This byte stream can be saved as a file or sent over a network
        // Can be sent to a different machine
        // Byte stream can be saved as a file (.ser) which is platform independent
        // (Think of this as if you're saving a file with the object's information)
        //----------------------------------------------------------------------------------
        // deserialization = The reverse process of converting a byte stream into an object.
        // (Think of this as if you're loading a saved file)    
    // -------------------------------------------------------------------------------------
        // Steps to Deserialize
        // ----------------------------------------------------------
            // 1- Declare your object (don't instantiate)
            // 2- your class should implement Serializable interface
            // 3- add import java. 10. Serializable;
            // 4- FileInputStream fileln = new FileInputStream(file path);
            // 5- Object Inputstream = new Object Inputstream(fileln);
            // 6- objectname = (Class) in.readObject(); // as the class type doesn't converted into bit stream and need to be casted manually
            // 7- in.close(); fileln.close();
        // ----------------------------------------------------------
    // -------------------------------------------------------------------------------------
        // Steps to Serialize
        // ----------------------------------------------------------
            // Your object class should implement Serializable interface
            // add import java. 10. Serializable;
            // FileOutputStream fileOut • new FileOutputStream(file path)
            // ObjectOutputStream out = new ObjectOutputStream(fileOut);
            // out. writeObject(objectName)
            // out. close(); fileout.close();
        // ----------------------------------------------------------
}
