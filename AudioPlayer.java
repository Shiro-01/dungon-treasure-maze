import java.io.File;
import javax.sound.sampled.*;
import java.util.Scanner;

/**
 * Write a description of class Audio here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AudioPlayer
{
    // instance variables - replace the example below with your own 
    private Clip clip;
    /**
     * Constructor for objects of class Audio
     */
    public AudioPlayer() 
    {        
        File file = new File(".\\res\\audio\\song.wav");
        try
        {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            try
            {
                clip = AudioSystem.getClip();
            }
            catch (LineUnavailableException lue)
            {
                lue.printStackTrace();
            }
            try
            {
                clip.open(audioStream);
            }
            catch (LineUnavailableException lue)
            {
                lue.printStackTrace();
            }
            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);
        }
        catch (UnsupportedAudioFileException uafe)
        {
            uafe.printStackTrace();
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    
    public AudioPlayer(String path)
    {
        File file = new File(path);
        try
        {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            try
            {
                clip = AudioSystem.getClip();
            }
            catch (LineUnavailableException lue)
            {
                lue.printStackTrace();
            }
            try
            {
                clip.open(audioStream);
            }
            catch (LineUnavailableException lue)
            {
                lue.printStackTrace();
            }
        }
        catch (UnsupportedAudioFileException uafe)
        {
            uafe.printStackTrace();
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }

    }
   
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void stop()
    {
        // put your code here
        clip.stop();
        
    }
    
     public void start()
    {
        // put your code here
        clip.start();
        clip.setMicrosecondPosition(0);
        
    }
    
     public void close()
    {
        // put your code here
        clip.close();
        
    }
    
    public void reset()
    {
        // put your code here
        clip.setMicrosecondPosition(0);        
    }
}
