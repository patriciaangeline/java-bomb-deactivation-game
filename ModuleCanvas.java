/**
   This is a class that instantiates the bomb's modules and manuals.
   It extends JPanel instead of JComponent as majority of its components are Swing objects. 
  
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
 
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModuleCanvas extends JPanel {
    private int width; 
    private int height; 

    public SimonSays simonModule; 
    public Keypad keypadModule; 
    private SimonSaysManual simonManual;
    private BombTimer bombTimer;
    private KeypadManual keypadManual;
    public Strikes strikesModule;
    public Timer timer;

    // The ModuleCanvas constructor that separates the JPanel into a GridLayout to portray the bomb's modules, manuals, timer, and strike counter.
    public ModuleCanvas(int w, int h) {
        width = w; 
        height = h; 

        bombTimer = new BombTimer();
        setLayout(new GridLayout(2, 3, 10, 10));
        setPreferredSize(new Dimension(width, height));

    }

    // A method that adds the modules and manuals to the panel. 
    public void setUpModules() {

        simonModule = new SimonSays();
        add(simonModule);

        strikesModule = new Strikes();
        add(strikesModule);

        keypadModule = new Keypad();
        add(keypadModule);

        simonManual = new SimonSaysManual();
        add(simonManual);

        bombTimer = new BombTimer(); 
        add(bombTimer);  

        keypadManual = new KeypadManual();
        add(keypadManual);   
        
        
        timer = new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent e){
                // Constantly checks both modules if the player has made a mistake
                strikesModule.strikechecker(simonModule.struck, keypadModule.struck, simonModule.u, keypadModule.u);
			}
		});
        timer.start();

    }

    // Getter methods so that the components can be accessed by other classes. 
    public BombTimer getBombTimer() {
        return bombTimer; 
    }

    public SimonSays getSimonSays() {
        return simonModule; 
    }

    public Keypad getKeypad() {
        return keypadModule; 
    }

}
