package EBS.EbsGUI;

import service.CrudServiceFactory;
import service.User.User;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Base64;
import EBS.EbsGUI.Login.*;

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
        titleLabel = new JLabel("New User",JLabel.CENTER);
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
        JLabel[] labels = { usernameLabel, passwordLabel};
        JTextField[] labelValues = { usernameTextField, passwordTextField };
        JButton[] buttons= { createButton, exitButton };
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
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
            CrudServiceFactory crudServiceFactory = CrudServiceFactory.USER_SERVICE;
            User user = crudServiceFactory.getUserService().findByUsername(username);

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
        customKeyListener(usernameTextField, createButton, KeyEvent.VK_ENTER);
        customKeyListener(passwordTextField, createButton, KeyEvent.VK_ENTER);
        customKeyListener(usernameTextField, exitButton, KeyEvent.VK_ESCAPE);
        customKeyListener(passwordTextField, exitButton, KeyEvent.VK_ESCAPE);
        createButton.addActionListener(this::onClickCreateButton);
        exitButton.addActionListener(this::onClickExitButton);
    }

    public void customKeyListener(Component target, JComponent listener, int keyCode){
        target.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==keyCode){
                    listener.requestFocusInWindow();
                    if(listener instanceof JButton) {
                        JButton button=(JButton)listener;
                        button.doClick();
                    }
                }

            }
        });
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
        CrudServiceFactory crudServiceFactory = CrudServiceFactory.USER_SERVICE;
        crudServiceFactory.getUserService().create(user);
        infoMessage("User "+ user.getUsername() +" created");
        try {
            Thread.sleep(1500);
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
