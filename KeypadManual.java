/**
    This is the class that generates the instructions needed for the keypad puzzle. This sets up the needed information for the keypad,
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

public class KeypadManual extends JPanel implements ModuleTemplate{
    //constructs all the necessary components which is the main panel where everything is stored and the images,fonts, and textarea
    public JPanel backpanel;
    public JTextArea manual;
    public JLabel pic;
    public ImageIcon image;
    public Font font;

    // The KeypadManual constructor creates a JPanel to add the pseudcode into. 
    public KeypadManual(){
        backpanel = new JPanel();
        backpanel.setLayout(new GridLayout(2, 1));

        manual = new JTextArea();
        
        setUpComponents();
        add(backpanel);
    }

    //sets up the face of the state
    public void setUpComponents(){

        font = new Font("Verdana", Font.BOLD, 15);
        manual.setFont(font);
        manual.setText("Int i = 1;\nIf (!Unlocked)\n    for(int x : Column i)\n        If(x.get(symbol) = currentSymbol)\n            PressButton();\n        i++;");
        manual.setEditable(false);

        image = new ImageIcon(new ImageIcon("Modules/KeypadImages/order of rows for keypad.png").getImage().getScaledInstance(450, 200, Image.SCALE_DEFAULT));

        pic = new JLabel();
        pic.setIcon(image);
        backpanel.add(pic);
        backpanel.add(manual);

    }

}