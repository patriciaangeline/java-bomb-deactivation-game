/**
    This is the class that generates the winning canvas when the player wins. This class is called when the player wins
    and sets up the winning stage.

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


public class WinCanvas extends JComponent {
    //constructs the fonts and the size of the canvas
    private int width; 
    private int height; 

    private Font topSecret; 
    private Font cascadia; 

    // The WinCanvas constructor is necessary for creating the custom fonts to be used. 
    public WinCanvas(int w, int h) {
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

    //makes the face of the canvas when you win
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; 

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Rectangle2D.Double defaultBackground = new Rectangle2D.Double(0, 0, width, height);
        g2d.setPaint(Color.GREEN);
        g2d.fill(defaultBackground);

        g2d.setFont(topSecret);
        g2d.setColor(Color.WHITE);
        g2d.drawString("You Won!", 370, 330);

        g2d.setFont(cascadia);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Congratulations on", 430, 450);
        g2d.drawString("living another day", 410, 550);
    }

}