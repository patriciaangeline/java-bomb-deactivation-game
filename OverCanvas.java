/**
    This is the class that generates the game over canvas when the player loses or runs out of time. This class is called when the player loses
    and sets up the necessary components for the losing canvas. 

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
import java.awt.geom.*;
import java.io.*;
import javax.swing.*;


public class OverCanvas extends JComponent {
    private int width; 
    private int height; 

    private Font topSecret; 
    private Font cascadia; 

    // The OverCanvas constructor that sets up the custom fonts to be used and sets the preferred size for the canvas. 
    public OverCanvas(int w, int h) {
        width = w; 
        height = h; 
        

        try {
            InputStream secretStream = getClass().getResourceAsStream("Top Secret Stamp.ttf");
            topSecret = Font.createFont(Font.TRUETYPE_FONT, secretStream).deriveFont(170F);

            InputStream luckStream = getClass().getResourceAsStream("Cascadia.ttf");
            cascadia = Font.createFont(Font.TRUETYPE_FONT, luckStream).deriveFont(55F);

        } catch(IOException | FontFormatException ex) {

        }

        setPreferredSize(new Dimension(width, height));
    }

    // Method overriding the paintComponent method to draw the necessary text when the player loses. 
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; 

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Rectangle2D.Double defaultBackground = new Rectangle2D.Double(0, 0, width, height);
        g2d.setPaint(Color.red);
        g2d.fill(defaultBackground);

        g2d.setFont(topSecret);
        g2d.setColor(Color.white);
        g2d.drawString("Game Over!", 330, 330);

        g2d.setFont(cascadia);
        g2d.setColor(Color.white);
        g2d.drawString("Better luck next time.", 410, 450);
    }

}
