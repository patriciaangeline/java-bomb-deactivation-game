/**
    This is a class responsible for creating the components when the game is in the titleState. 
    It implements the method from PagesTemplate to create these components. 

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
import java.io.*;

public class TitleComponents implements PagesTemplate {
    
    private Font topSecret; 
    private Font cascadia; 

    private String[] titleOptions = {"Play Game", "Quit"};
    public int currentOption = 0; 

    // The TitleComponents constructor creates the custom fonts to be used for the titleState.
    public TitleComponents() {
        try {
            InputStream secretStream = getClass().getResourceAsStream("Top Secret Stamp.ttf");
            topSecret = Font.createFont(Font.TRUETYPE_FONT, secretStream).deriveFont(122F);

            InputStream cascadiaStream = getClass().getResourceAsStream("Cascadia.ttf");
            cascadia = Font.createFont(Font.TRUETYPE_FONT, cascadiaStream).deriveFont(50F);

        } catch(IOException | FontFormatException ex) {

        }
    }
    
    // Method overriding of the paintComponent method to draw the necessary text for the titleState. 
    @Override
    public void draw(Graphics2D g2d) {

        g2d.setFont(topSecret);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Remain Speaking and No", 90, 180);
        g2d.drawString("Human Being Detonates", 100, 300);

        g2d.setFont(cascadia);
        g2d.drawString("Ateneo CS Edition", 505, 410);

        for(int i = 0; i < titleOptions.length; i++) {
            if(i == currentOption) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.WHITE);
            }

            g2d.setFont(cascadia);
            g2d.drawString(titleOptions[i], 620, 520 + i*90);
        }

    }
    
    // Getter method so that the length of the options can be used in other classes. 
    public int getOptionsLength() {
        return titleOptions.length;
    }
}
