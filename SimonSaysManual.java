/**
    This is the class that generates the instructions needed for the simon says puzzle. This sets up the needed information for the simon says puzzle,
    basically acting as a manual.

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

public class SimonSaysManual extends JPanel implements ModuleTemplate{

    //constructs all the necessary components which is the main panel where everything is stored and the images,fonts, and textarea
    public JPanel backpanel;
    public JTextArea manual;
    public ImageIcon greenimage;
    public ImageIcon redimage;
    public Font font; 

    // SimonSaysManual creates a JPanel to add the pseudocode into. 
    public SimonSaysManual(){
        backpanel = new JPanel();
        backpanel.setLayout(new GridLayout(2, 1));
    
        manual = new JTextArea();
        
        setUpComponents();
        add(backpanel);
    }

    // The method is used to create the text for the manual. 
    public void setUpComponents(){

        font = new Font("Verdana", Font.BOLD, 25);
        manual.setFont(font);
        manual.setText(" redPressed = true\n    While i <= 6\n    Red flash = Yellow button\n    Green flash = Red button\n    Blue Flash = Green button\n    Yellow Flash = Blue button\n    i++;");
        manual.setEditable(false);
        
        backpanel.add(manual);
    }
    
}
