package EBS.EbsGUI;

import service.CrudServiceFactory;
import service.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    JPanel topPanel, imgPanel, bottomPanel;
    JLabel username, password;
    JLabel loginImageLabel;
    JTextField usernameTextField;
    JPasswordField passwordTextField;
    JButton loginButton, cancelButton, registrationButton;
    Welcome loginBackground;

    Login() {
        super("Login");
        createComponents();
        loginBackground= new Welcome();
        createFrame();
        addComponents();
        addListeners();
    }

    public void createFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setSize(660, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void createComponents(){
        username = new JLabel("User Name:", JLabel.CENTER);
        password = new JLabel("Password:", JLabel.CENTER);

        usernameTextField = new JTextField(15);
        passwordTextField = new JPasswordField(15);

        loginButton = new JButton("Login");
        loginButton.setSize(270, 50);
        loginButton.setLocation(20,20);

        cancelButton = new JButton("Cancel");
        cancelButton.setSize(270, 50);
        cancelButton.setLocation(320,20);

        registrationButton = new JButton("Registration");
        registrationButton.setLocation(20, 80);
        registrationButton.setSize(570,35);

        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("images/green-login.jpg"));
        Image i3 = ic3.getImage().getScaledInstance(360, 240, Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        loginImageLabel = new JLabel(icc3);
        createBackPanels();
        addFont();
    }

    public void createBackPanels(){
        topPanel = new JPanel();
        topPanel.setSize(600, 100);
        topPanel.setLocation(0, 20);
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new GridLayout(2, 2, 10, 10));

        imgPanel = new JPanel();
        imgPanel.setSize(660, 260);
        imgPanel.setLocation(0, 120);
        imgPanel.setBackground(Color.white);

        bottomPanel = new JPanel();
        bottomPanel.setSize(620, 120);
        bottomPanel .setLocation(10, 360);
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setLayout(null);
    }

    public void addComponents(){
        topPanel.add(username);
        topPanel.add(usernameTextField);
        topPanel.add(password);
        topPanel.add(passwordTextField);

        imgPanel.add(loginImageLabel);
        bottomPanel.add(loginButton);
        bottomPanel.add(cancelButton);
        bottomPanel.add(registrationButton);

        add(topPanel);
        add(imgPanel);
        add(bottomPanel);
    }

    public void addFont(){
        JLabel[] labels= { username, password };
        JTextField[] labelValues={ usernameTextField};
        JButton[] buttons= {loginButton, cancelButton, registrationButton};

        for(JLabel label: labels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        }
        usernameTextField.setFont(new Font("Times New Roman", Font.BOLD, 24));
        passwordTextField.setFont(new Font("Times New Roman", Font.BOLD, 24));
        for(JButton button: buttons){
            button.setFont(new Font("Times New Roman", Font.BOLD, 24));
            button.setBackground(new Color(50, 205, 50));
        }
    }

    private void onClickLoginButton(ActionEvent actionEvent) {
        String uName = usernameTextField.getText();
        String pass = passwordTextField.getText();
            if (!uName.isEmpty() && !pass.isEmpty()) {
                CrudServiceFactory crudServiceFactory = CrudServiceFactory.USER_SERVICE;
                User user = crudServiceFactory.getUserService().login(uName, pass);
                validateUser(user);
             } else {
                badInput();
             }
    }

    private void onClickCancelButton(ActionEvent actionEvent) {
        loginBackground.dispose();
        this.dispose();
    }

    public void onClickRegistrationButton(ActionEvent actionEvent) {
        new NewUser();
    }

    public void badInput() {
        usernameTextField.setText("username here");
        usernameTextField.setForeground(Color.lightGray);
        passwordTextField.setText("pass here");
        passwordTextField.setForeground(Color.lightGray);

    }

    public void validateUser(User user) {
        if (user != null) {
            new MenuFrame(user);
            loginBackground.setVisible(false);
            this.setVisible(false);

        } else {
            usernameTextField.setText("User not found !");
        }

    }

    public void addListeners() {
        loginButton.addActionListener(this::onClickLoginButton);
        cancelButton.addActionListener(this::onClickCancelButton);
        registrationButton.addActionListener(this::onClickRegistrationButton);
        usernameTextField.addMouseListener(mouseAdapter);
        passwordTextField.addMouseListener(mouseAdapter);

    }
    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == usernameTextField) {
                usernameTextField.setText("");
                usernameTextField.setForeground(Color.BLACK);
            } else if (e.getSource() == passwordTextField) {
                passwordTextField.setText("");
                passwordTextField.setForeground(Color.BLACK);
            }
        }
    };



    public static void main(String[] args) {
        new Login();
    }
}