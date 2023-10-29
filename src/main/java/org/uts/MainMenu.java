package org.uts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Main Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        /*********** Subject Enrolment Button ***********/
        JButton btnSubjectEnrolment = new JButton("Subject Enrolment");
        btnSubjectEnrolment.setPreferredSize(new Dimension(150, 40));
        btnSubjectEnrolment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open SubjectEnrolment Frame
                new SubjectEnrolment();
            }
        });

        /*********** Enrolment List Button ***********/
        JButton btnEnrolmentList = new JButton("Enrolment List");
        btnEnrolmentList.setPreferredSize(new Dimension(130, 40));
        btnEnrolmentList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to EnrolmentList
                new EnrolmentList();
            }
        });

        // placing the buttons
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(btnSubjectEnrolment, gbc);

        gbc.gridx = 1;
        add(btnEnrolmentList, gbc);

        setVisible(true);
    }

    /**********Running**********/
    public static void main(String[] args) {
        new MainMenu();
    }
}
