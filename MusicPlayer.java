/**
    This is a class responsible for playing the music. 
    It will be called when both players are in the gameplayState. 

    @author Sophia Avielle Gregorio (223019) & Patricia Angeline Tan (226189)
    @version May 15, 2023
**/

/*
    I have not discussed the Java language code in my program
    with anyone other than my instructor or the teaching assistants
    assigned to this course.

    I have not used Java language code obtained from another student,
    or any other unauthorized source, either modified or unmodified.

    If any Java language code or documentation used in my program
    was obtained from another source, such as a textbook or website,
    that has been clearly noted with a proper citation in the comments
    of my program.
*/

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class MusicPlayer {
    private File musicPath; 
    private Clip musicClip; 

    // THe playMusic() method takes in a String argument (name of the .wav file) and incorporates it into the java program. 
        // Source: https://www.youtube.com/watch?v=TErboGLHZGA&t=0s
    void playMusic(String musicLocation) {
        try {

            musicPath = new File(musicLocation);
            
            if(musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                musicClip = AudioSystem.getClip();
                musicClip.open(audioInput);
                musicClip.start();

            } else {
                System.out.println("Cannot find file.");
            }

        } catch (Exception e) {
            System.out.println("Exception from playMusic() musicPlayer");
        }

    }
}
