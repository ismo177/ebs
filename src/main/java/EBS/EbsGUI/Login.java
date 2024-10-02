package EBS.EbsGUI;

import service.ServiceFactory;
import service.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {

    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton registrationButton;
    JTextField usernameTextField;
    JPasswordField passwordTextField;
    JButton loginButton, cancelButton;
    Welcome loginBackground;

    Login() {
        super("Login");
        createTopPanelComponents();
        createBottomPanelComponents();
        JPanel topPanel = setTopPanel(usernameLabel, usernameTextField, passwordLabel, passwordTextField);
        JPanel buttonsPanel = setBottomPanel(loginButton, cancelButton, registrationButton);
        JPanel imgPanel = setCenterPanel();
        addListeners();
        loginBackground= new Welcome();
        setLoginFrame(topPanel, imgPanel,buttonsPanel );
    }

    public void createTopPanelComponents(){
        usernameLabel = new JLabel("User Name:", JLabel.CENTER);
        usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        passwordLabel = new JLabel("Password:", JLabel.CENTER);
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        usernameTextField = new JTextField(15);
        usernameTextField.setFont(new Font("Serif", Font.PLAIN, 22));
        passwordTextField = new JPasswordField(15);
        passwordTextField.setFont(new Font("Serif", Font.PLAIN, 22));
    }

    public void createBottomPanelComponents(){
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Serif", Font.BOLD, 24));
        loginButton.setBackground(new Color(50, 205, 50));
        loginButton.setSize(270, 50);
        loginButton.setLocation(20,20);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(50, 205, 50));
        cancelButton.setFont(new Font("Serif", Font.BOLD, 24));
        cancelButton.setSize(270, 50);
        cancelButton.setLocation(320,20);

        registrationButton = new JButton("Registration");
        registrationButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        registrationButton.setBackground(new Color(50, 205, 50));
        registrationButton.setLocation(20, 80);
        registrationButton.setSize(570,35);
    }

    public JPanel setTopPanel(JLabel label1, JTextField textField1, JLabel label2, JTextField textField2) {
        JPanel topPanel = new JPanel();
        topPanel.setSize(600, 100);
        topPanel.setLocation(0, 0);
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new GridLayout(2, 2, 10, 10));
        topPanel.add(label1);
        topPanel.add(textField1);
        topPanel.add(label2);
        topPanel.add(textField2);
        return topPanel;
    }

    public JPanel setCenterPanel(){
        //CenterPanel component
        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("images/green-login.jpg"));
        Image i3 = ic3.getImage().getScaledInstance(360, 240, Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        //
        JLabel loginImageLabel = new JLabel(icc3);
        JPanel imgPanel = new JPanel();
        imgPanel.add(loginImageLabel);
        imgPanel.setSize(660, 260);
        imgPanel.setLocation(0, 100);
        imgPanel.setBackground(Color.white);
        return imgPanel;
    }

    public JPanel setBottomPanel(JButton button1, JButton button2, JButton button3 ) {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setSize(620, 120);
        buttonsPanel.setLocation(10, 360);
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        buttonsPanel.setLayout(null);
        return buttonsPanel;

    }

    public void setLoginFrame(JPanel top, JPanel center, JPanel bottom) {
        add(top);
        add(center);
        add(bottom);
        getContentPane().setBackground(Color.white);
        setSize(660, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }


    private void onClickLoginButton(ActionEvent actionEvent) {
        String uName = usernameTextField.getText();
        String pass = passwordTextField.getText();
            if (!uName.isEmpty() && !pass.isEmpty()) {
                ServiceFactory serviceFactory = ServiceFactory.USER_SERVICE;
                User user = serviceFactory.getUserService().login(uName, pass);
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
            } else if (e.getSource() == passwordTextField) {
                passwordTextField.setText("");
            }
        }
    };

    public static void main(String[] args) {
        new Login();
    }
}