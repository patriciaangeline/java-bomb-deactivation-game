/**
    This is the class that calculates the strikes from both the simon and keypad modules to see if the player wins or loses. This class is 
    the one that gets the combination of errors to see if the player loses.

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

import javax.swing.*;
import java.awt.*;

public class Strikes extends JPanel {
    //The panel to implement all for organization <3

    private JPanel backpanel;

    //label where the picture of the strikes are gonna be
    private JLabel pic;

    //pictures of the strikes!
    public ImageIcon emptystrike;
    public ImageIcon onestrike;
    public ImageIcon twostrikes;

    //for the combination of strikes from both simon and keypad
    public boolean pairer;
    public boolean pairer2;
    public boolean pairer3;
    public boolean pairer4;
    public boolean pairer5;
    public boolean playerloses;

    // The Strikes constructor creates a new JPanel to create and update the strikes. 
    public Strikes() {
        playerloses = false;

        backpanel = new JPanel();
        backpanel.setLayout(new GridLayout(1,1));
        
        emptystrike = new ImageIcon(new ImageIcon("Modules/Strikes/emptystrikes.png").getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT));
        onestrike = new ImageIcon(new ImageIcon("Modules/Strikes/onestrike.png").getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT));
        twostrikes = new ImageIcon(new ImageIcon("Modules/Strikes/twostrikes.png").getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT));
        
        pic = new JLabel();
        pic.setIcon(emptystrike);
        backpanel.add(pic);
        add(backpanel);
    }

    //checks and calculates your losing ability to the strikes
    public void strikechecker(boolean simon, boolean keypad, int simonnum, int keypadnum){
        pairer = (simonnum == 2 && keypadnum == 1); //simon 1, keypad 1
        pairer2 = (simonnum == 2 && keypadnum == 2); //simon 1, keypad 2
        pairer3 = (simonnum == 3 && keypadnum == 1); //simon 2, keypad 1

        if(simon || keypad){
            if(simonnum == 2 || keypadnum == 1){
                pic.setIcon(onestrike);
            }
            
            if(simonnum == 3 || keypadnum == 2 || pairer){
                pic.setIcon(twostrikes);
            }

            if(simonnum == 4 || keypadnum == 3 || pairer2 || pairer3){
                playerloses = true;
            }

        }
  
    }
}