package org.uts;

import javax.swing.*;
import java.awt.*;

public class EnrolmentList extends JFrame {
    
    public EnrolmentList() {
        setTitle("Enrolment List");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        // Set font
        Font arial24 = new Font("Arial", Font.PLAIN, 24);

        // Example text
        JLabel label = new JLabel("This is the Enrolment List page", SwingConstants.CENTER);
        label.setFont(arial24);  // Apply the font
        add(label);

        setVisible(true);
    }
    
    /**********Running**********/
    public static void main(String[] args) {
        new EnrolmentList();
    }
}
