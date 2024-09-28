package EBS.EbsGUI;

import service.ServiceFactory;
import service.User.User;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Base64;

public class CreateNewUser extends JFrame implements MouseListener {
    JPanel centerPanel, bottomPanel;
    JLabel titleLabel, usernameLabel, passwordLabel;
    JTextField usernameTextField, passwordTextField;
    JButton createButton, exitButton;


    public CreateNewUser() {

        titleLabel = new JLabel("Create New User",JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        titleLabel.setSize(600, 60);
        titleLabel.setLocation(0, 0);


        usernameLabel = new JLabel("Enter Username:", JLabel.CENTER);
        usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        passwordLabel = new JLabel("Enter Password:", JLabel.CENTER);
        passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));


        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2, 20, 40));
        centerPanel.setSize(580, 140);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLocation(0, 80);
        // p1.setBorder(new LineBorder(Color.RED));

        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        usernameTextField.addMouseListener(this);

        passwordTextField = new JTextField();
        passwordTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        passwordTextField.addMouseListener(this);

        centerPanel.add(usernameLabel);
        centerPanel.add(usernameTextField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordTextField);


        createButton = new JButton("Create");
        createButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        createButton.setBackground(new Color(50, 205, 50));
        createButton.addActionListener(this::onClickCreateButton);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exitButton.setBackground(new Color(50, 205, 50));
        exitButton.addActionListener(this::onClickExitButton);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, 20, 0));
        bottomPanel.setSize(560, 50);
        bottomPanel.setLocation(20, 290);

        bottomPanel.add(createButton);
        bottomPanel.add(exitButton);


        add(titleLabel);
        add(centerPanel);
        add(bottomPanel);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Create New User");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }


    //----------------------------------------------------------------------------------------------

    private void onClickCreateButton(ActionEvent e) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (username.isEmpty() && !password.isEmpty()) {
            usernameTextField.setText("Username here");
            usernameTextField.setForeground(Color.lightGray);
            usernameTextField.setBorder(new LineBorder(Color.RED,3,true));
            passwordTextField.setBorder(new LineBorder(Color.GREEN,3,true));

        }else if(!username.isEmpty() && password.isEmpty()){
            passwordTextField.setText("Password here");
            passwordTextField.setForeground(Color.lightGray);
            passwordTextField.setBorder(new LineBorder(Color.RED,3,true));
            usernameTextField.setBorder(new LineBorder(Color.GREEN,3,true));

        }else if(username.isEmpty() || password.isEmpty()){
            usernameTextField.setText("Username here");
            usernameTextField.setForeground(Color.lightGray);
            usernameTextField.setBorder(new LineBorder(Color.RED,3,true));

            passwordTextField.setText("Password here");
            passwordTextField.setBorder(new LineBorder(Color.RED,3,true));
            passwordTextField.setForeground(Color.lightGray);

        }else {
            if(username.length()>8 && password.length()>6) {

                ServiceFactory serviceFactory = ServiceFactory.USER_SERVICE;
                User user = serviceFactory.getUserService().findByUsername(username);


                if (user != null) {
                    usernameTextField.setText("Username already exists");
                    usernameTextField.setBorder(new LineBorder(Color.RED,2,true));
                    passwordTextField.setText("");
                } else {
                    user = new User();
                    user.setUsername(username);
                    String hashedPassword = Base64.getEncoder().encodeToString(password.getBytes());
                    user.setPassword(hashedPassword);
                    user.setActive(true);
                    serviceFactory.getUserService().create(user);
                    usernameTextField.setText("User " + "'" + user.getUsername() + "'" + " created.");
                    usernameTextField.setBorder(new LineBorder(Color.RED, 2, true));
                    passwordTextField.setText("");
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    this.dispose();
                }

            }else{
                usernameTextField.setText("At least 8 characters!!");
                usernameTextField.setForeground(Color.GRAY);
                passwordTextField.setBorder(new LineBorder(Color.RED,2,true));
                passwordTextField.setText("At least 6 characters!!");
                passwordTextField.setForeground(Color.GRAY);
                passwordTextField.setBorder(new LineBorder(Color.RED,2,true));

            }
        }
    }

    public void onClickExitButton(ActionEvent e) {
        this.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
            if(e.getSource()== usernameTextField) {
                usernameTextField.setText("");
                usernameTextField.setBorder(new LineBorder(Color.GRAY));
                usernameTextField.setForeground(Color.BLACK);
            }else if(e.getSource()== passwordTextField) {
                passwordTextField.setText("");
                passwordTextField.setBorder(new LineBorder(Color.GRAY));
                passwordTextField.setForeground(Color.BLACK);
            }
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }


}
