package EBS.EbsGUI;

import service.ServiceFactory;
import service.User.User;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Base64;

public class NewUser extends JFrame  {
    JPanel centerPanel, bottomPanel;
    JLabel titleLabel, usernameLabel, passwordLabel;
    JTextField usernameTextField, passwordTextField;
    JButton createButton, exitButton;
    public NewUser() {
        createComponents();
        addComponents();
        createFrame();
        addListeners();
    }


   public void createFrame(){
        setTitle("New User");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

    public void createComponents(){
        titleLabel = new JLabel("Create New User",JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setSize(600, 60);
        titleLabel.setLocation(0, 0);

        usernameLabel = new JLabel("Enter Username:", JLabel.CENTER);
        passwordLabel = new JLabel("Enter Password:", JLabel.CENTER);
        usernameTextField = new JTextField();
        passwordTextField = new JTextField();
        createButton = new JButton("Create");
        exitButton = new JButton("Exit");
        addFont();
        createBackPanels();

    }

    public void addComponents(){
        centerPanel.add(usernameLabel);
        centerPanel.add(usernameTextField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordTextField);

        bottomPanel.add(createButton);
        bottomPanel.add(exitButton);

        add(titleLabel);
        add(centerPanel);
        add(bottomPanel);
    }

    public void createBackPanels(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2, 20, 40));
        centerPanel.setSize(580, 140);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLocation(0, 80);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, 20, 0));
        bottomPanel.setSize(560, 50);
        bottomPanel.setLocation(20, 290);
    }

    public void addFont(){
        JLabel[] labels = { titleLabel, usernameLabel, passwordLabel};
        JTextField[] labelValues = { usernameTextField, passwordTextField };
        JButton[] buttons= { createButton, exitButton };

        for(JLabel label : labels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        }
        for(JTextField labelValue : labelValues){
            labelValue.setFont(new Font("Times New Roman", Font.BOLD, 24));
        }
        for(JButton button : buttons){
            button.setFont(new Font("Times New Roman", Font.BOLD, 24));
            button.setBackground(new Color(50, 205, 50));
        }
    }

    private void onClickCreateButton(ActionEvent e) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        boolean correctInput=!username.isEmpty() && !password.isEmpty() && username.length()>8 && password.length()>6;

        if(correctInput) {
            ServiceFactory serviceFactory = ServiceFactory.USER_SERVICE;
            User user = serviceFactory.getUserService().findByUsername(username);

            if (user != null) {
                    infoMessage("Username already exists");
                } else {
                createUser(username, password);
            }
            }else{
                infoMessage("Username or password  too short, \nor empty");
        }
    }


    public void addListeners(){
        usernameTextField.addMouseListener(mouseAdapter);
        passwordTextField.addMouseListener(mouseAdapter);
        createButton.addActionListener(this::onClickCreateButton);
        exitButton.addActionListener(this::onClickExitButton);
    }

    public void onClickExitButton(ActionEvent e) {
        this.dispose();
    }

    MouseAdapter mouseAdapter=new MouseAdapter() {
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
    };

    public void createUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        String hashedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        user.setPassword(hashedPassword);
        user.setActive(true);
        ServiceFactory serviceFactory = ServiceFactory.USER_SERVICE;
        serviceFactory.getUserService().create(user);
        infoMessage("User "+ user.getUsername() +" created");
        try {
            Thread.sleep(2000);
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.dispose();
    }

    public void infoMessage(String message){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 24));
        JOptionPane.showMessageDialog(this, message+" !",
                "Service", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        new NewUser();
    }
}
