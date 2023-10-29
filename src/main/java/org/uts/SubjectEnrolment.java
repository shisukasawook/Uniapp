package org.uts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SubjectEnrolment extends JFrame {
    private JTextArea txtSubjects;
    private JButton btnEnrol;
    private JButton btnGotoList;
    final private Font labelFont = new Font("Arial", Font.PLAIN, 16);
    private String[] subjects = {"Subject 1", "Subject 2", "Subject 3", "Subject 4"}; ///Subject Name
    private int currentIndex = 0;

    /************Page Setup ***********/
    public SubjectEnrolment() {
        setTitle("Subject Enrolment");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(); ///Layout

        /************Subjects TextArea***********/
        txtSubjects = new JTextArea(10, 40);
        txtSubjects.setFont(new Font("Arial", Font.BOLD, 16));
        txtSubjects.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtSubjects);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 20, 0); // This will add a bottom padding of 20
        add(scrollPane, constraints);

        /************Enrol Button***********/
        btnEnrol = new JButton("Enrol");
        btnEnrol.setFont(labelFont);
        btnEnrol.setBackground(new Color(0x03AD00)); //background color
        btnEnrol.setForeground(Color.WHITE); //text color
        btnEnrol.setOpaque(true);
        btnEnrol.setBorderPainted(false);
        btnEnrol.setPreferredSize(new Dimension(600, 30)); //dimensions
        btnEnrol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < subjects.length) {
                    txtSubjects.append(subjects[currentIndex] + "\n");
                    
                    // Check if the current subject is "Subject 4"
                    if (subjects[currentIndex].equals("Subject 4")) {
                        JOptionPane.showMessageDialog(SubjectEnrolment.this, "You can't added more than 4 subject");
                    }
                    
                    currentIndex++;
                }
                if (currentIndex == subjects.length) {
                    btnEnrol.setEnabled(false);
                    btnGotoList.setEnabled(true);
                }
            }
        });
        constraints.gridy = 1;
        add(btnEnrol, constraints);

        /************Go to Enrolment List Button***********/
        btnGotoList = new JButton("See My Enrolment List");
        btnGotoList.setFont(labelFont);
        btnGotoList.setBackground(new Color(0x03AD00)); //background color
        btnGotoList.setForeground(Color.WHITE); //text color
        btnGotoList.setOpaque(true);
        btnGotoList.setBorderPainted(false);
        btnGotoList.setPreferredSize(new Dimension(600, 30)); //dimensions
        btnGotoList.setEnabled(false); //inactive
        btnGotoList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to EnrolmentList
                new EnrolmentList();
            }
        });
        constraints.gridy = 2;
        add(btnGotoList, constraints);

        setVisible(true);


    }

    /************Running***********/
    public static void main(String[] args) {
        new SubjectEnrolment();
    }
}
