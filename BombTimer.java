/**
   This is a class that creates the countdown timer for the bomb. 
   It places the necessary components within a JPanel using the inherited method from ModuleTemplate. 
  
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
import java.awt.event.*;
import javax.swing.*;
import java.text.*;


public class BombTimer extends JPanel implements ModuleTemplate {
    private JLabel countdownLabel; 
    private Font tickingTimeBomb; 
    private Timer countdownTimer; 

    private int minutesLeft;
    private int secondsLeft; 
    private DecimalFormat decimalFormat; 
    private String doubleMinute; 
    private String doubleSecond; 

    // The BombTimer constructor uses the extended JPanel to add the countdownLabel. It also instantiates the custom font to be used. 
    public BombTimer() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        try {
            InputStream inputStream = getClass().getResourceAsStream("TickingTimebombBB.ttf");
            tickingTimeBomb = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(200F);

        } catch(IOException | FontFormatException e) {

        }

        countdownLabel = new JLabel();
        countdownLabel.setHorizontalAlignment(JLabel.CENTER);
        countdownLabel.setFont(tickingTimeBomb);
        countdownLabel.setForeground(Color.red);

        decimalFormat = new DecimalFormat("00");
        countdownLabel.setText("2:30");
        minutesLeft = 2; 
        secondsLeft = 30; 

        setUpComponents();
        countdownTimer.start();

        add(countdownLabel, BorderLayout.CENTER);
        repaint();

    }
    
    // Method inherited from the ModuleTemplate interface that formats the countdown timer. 
        // Source: https://www.youtube.com/watch?v=zWw72j-EbqI
    @Override
    public void setUpComponents() {
        countdownTimer = new Timer(1000, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsLeft--; 
                doubleMinute = decimalFormat.format(minutesLeft); 
                doubleSecond = decimalFormat.format(secondsLeft);
                countdownLabel.setText(doubleMinute + ":" + doubleSecond);

                if(secondsLeft == -1) { 
                    secondsLeft = 59; 
                    minutesLeft--; 
                    doubleMinute = decimalFormat.format(minutesLeft);
                    doubleSecond = decimalFormat.format(secondsLeft);
                    countdownLabel.setText(doubleMinute + ":" + doubleSecond);
                }
                
                if(minutesLeft == 0 && secondsLeft == 0) { 
                    countdownTimer.stop();
                }
            }
        });
    }
    
    // Getter methods for other classes to access. 
    public int getMinutesLeft() {
        return minutesLeft;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
}
