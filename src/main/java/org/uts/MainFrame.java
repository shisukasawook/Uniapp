package org.uts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainFrame extends JFrame {
    /**********Font Size **********/
    final private Font mainFont = new Font("Arial", Font.BOLD, 24);
    final private Font labelFont = new Font("Arial", Font.PLAIN, 16);
    JTextField tfEmail, tfPassword;
    JLabel lbTextidentify;
    Dimension textFieldSize = new Dimension(200, 30);

    public void initialize() {
        /**********Welcome Label at the top of the page **********/
        JLabel lbUTSWelcome = new JLabel("Welcome to University of Technology Sydney");
        lbUTSWelcome.setFont(mainFont);
        lbUTSWelcome.setHorizontalAlignment(SwingConstants.CENTER);

        /**********Form Panel**********/
      
        /**********email label**********/
        JLabel lbEmail = new JLabel("Email:    ");  // Added spaces for alignment
        lbEmail.setFont(labelFont);

        /**********email textfield **********/
        tfEmail = new JTextField();
        tfEmail.setFont(labelFont);
        tfEmail.setPreferredSize(textFieldSize);
        tfEmail.setMaximumSize(textFieldSize);
        tfEmail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY)); 

        /**********email Panel**********/
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        emailPanel.add(lbEmail);
        emailPanel.add(tfEmail);
        emailPanel.setBackground(Color.WHITE); //bg color

        /**********password label **********/
        JLabel lbPassword = new JLabel("Password:");
        lbPassword.setFont(labelFont);
   
        /**********password textfield **********/
        tfPassword = new JTextField();
        tfPassword.setFont(labelFont);
        tfPassword.setPreferredSize(textFieldSize);
        tfPassword.setMaximumSize(textFieldSize);
        tfPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY)); 

        /**********Password Panel**********/
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passwordPanel.add(lbPassword);
        passwordPanel.add(tfPassword);
        passwordPanel.setBackground(Color.WHITE); //bg color

        /**********Email and password Layout **********/
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(Box.createVerticalStrut(100));  // top
        formPanel.add(emailPanel);
        formPanel.add(Box.createVerticalStrut(0));  // space between the email and password panels
        formPanel.add(passwordPanel);
        formPanel.add(Box.createVerticalStrut(100));  // bottom
        formPanel.setBackground(Color.WHITE);

        
        /********** This happens after clicking Login **********/
        lbTextidentify = new JLabel();
        lbTextidentify.setFont(labelFont);

        /**********Login button **********/
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(labelFont);
        Dimension buttonSize = new Dimension(350, 37);  // button size
        btnLogin.setPreferredSize(buttonSize);

        btnLogin.setFont(labelFont);
        btnLogin.setBackground(new Color(0x03AD00)); // background color
        btnLogin.setForeground(Color.WHITE); // text color 
        btnLogin.setOpaque(true); // transparent
        btnLogin.setBorderPainted(false); // Remove the border 
        
        /**********Login Script **********/
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = tfPassword.getText();

                if (!isValidEmail(email)) {
                    lbTextidentify.setText("Please enter a valid @university.com email address.");
                    return; 
                }

                if (!isValidPassword(password)) {
                    lbTextidentify.setText("Invalid password format.");
                    return; 
                }

                // Check the student's data
                Map<String, String> studentData = StudentDataHelper.readStudentData();

                if (studentData.containsKey(email)) {
                    if (studentData.get(email).equals(password)) {
                        lbTextidentify.setText("Login Successful: " + email);
                        // Navigate to MainMenu
                        new MainMenu();
                        MainFrame.this.setVisible(false);  // hide the MainFrame
                    } else {
                        lbTextidentify.setText("Incorrect password.");
                    }
                } else {
                    lbTextidentify.setText("Email not found.");
                }
            }
        });
        
        /**********Button Panel **********/

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(btnLogin);
        buttonsPanel.setBackground(Color.WHITE); //bg color       
       
        /**********Center Panel **********/

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(formPanel, BorderLayout.CENTER);
        centerPanel.add(buttonsPanel, BorderLayout.PAGE_END);

        /**********Main Panel **********/
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));

        /**********Setting padding for the mainPanel **********/
        int topPadding = 100, leftPadding = 100, bottomPadding = 100, rightPadding = 100;
        mainPanel.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));

        /**********Add components**********/
        mainPanel.add(lbUTSWelcome, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(lbTextidentify, BorderLayout.SOUTH);

        /**********Window and Dimension**********/
        add(mainPanel);
        setTitle("Welcome to University of Technology Sydney");
        setSize(800, 600);
        setMinimumSize(new Dimension(500, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
        /**********Email Validation**********/
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@university\\.com$"; // Email Format
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
        /**********Password Validation**********/
    public static boolean isValidPassword(String password) {
        String regex = "^[A-Z][A-Za-z]{5,}\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    

        /**********Running**********/
        public static void main(String[] args) {
            MainFrame myFrame = new MainFrame();
            myFrame.initialize();
        }
    }

