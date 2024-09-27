package EBS.EbsGUI;

import service.ServiceFactory;
import service.User.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame  {

    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel loginImageLabel;
    private JButton registrationButton;

    JTextField usernameTextField;
    JPasswordField passwordTextField;
    JButton loginButton, cancelButton;
    JPanel topPanel, buttonsPanel, imgPanel, regPanel;
    String tempUser;
    String tempPass;
    int tempUserID;
    Welcome welcome;
    Login() {
        super("Login");

        usernameLabel =new JLabel("User Name:",JLabel.CENTER);
        usernameLabel.setFont(new Font("Times New Roman",Font.BOLD,20));
        passwordLabel =new JLabel("Password:",JLabel.CENTER);
        passwordLabel.setFont(new Font("Times New Roman",Font.BOLD,20));

        usernameTextField =new JTextField(15);
        usernameTextField.setFont(new Font("Serif",Font.PLAIN,22));
        passwordTextField =new JPasswordField(15);
        passwordTextField.setFont(new Font("Serif",Font.PLAIN,22));

        registrationButton =new JButton("Registration");
        registrationButton.setFont(new Font("Times New Roman",Font.BOLD,20));
        registrationButton.setLocation(0,440);
        registrationButton.setBackground(new Color(50,205,50));
        registrationButton.addActionListener(this::onClickRegistrationButton);

        loginButton =new JButton("Login");
        loginButton.setFont(new Font("Serif",Font.BOLD,24));
        loginButton.setBackground(new Color(50,205,50));
        loginButton.setSize(420,40);


        cancelButton =new JButton("Cancel");
        cancelButton.setBackground(new Color(50,205,50));
        cancelButton.setFont(new Font("Serif",Font.BOLD,24));

        loginButton.addActionListener(this::onClickLoginButton);
        cancelButton.addActionListener(this::onClickCancelButton);


        ImageIcon ic3=new ImageIcon(ClassLoader.getSystemResource("images/green-login.jpg"));
        Image i3=ic3.getImage().getScaledInstance(360,240,Image.SCALE_DEFAULT);
        ImageIcon icc3=new ImageIcon(i3);

        loginImageLabel =new JLabel(icc3);

        topPanel =new JPanel();
        buttonsPanel =new JPanel();
        imgPanel =new JPanel();


        topPanel.setLayout(new GridLayout(2,2,10,10));
        topPanel.add(usernameLabel);
        topPanel.add(usernameTextField);
        topPanel.add(passwordLabel);
        topPanel.add(passwordTextField);

        topPanel.setSize(600,100);
        topPanel.setLocation(0,0);


        buttonsPanel.add(loginButton);
        buttonsPanel.add(cancelButton);
        buttonsPanel.setSize(620,60);
        buttonsPanel.setLocation(10,360);
        buttonsPanel.setLayout(new GridLayout(1,2,10,0));


        topPanel.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.WHITE);

        imgPanel.add(loginImageLabel);
        imgPanel.setSize(660,260);
        imgPanel.setLocation(0,100);
        imgPanel.setBackground(Color.white);

        regPanel =new JPanel();
        regPanel.setSize(620,40);
        regPanel.setLocation(10,430);
        regPanel.setLayout(new GridLayout(1,1));
        regPanel.add(registrationButton);


        add(topPanel);
        add(imgPanel);
        add(buttonsPanel);
        add(regPanel);


        welcome=new Welcome();
        getContentPane().setBackground(Color.white);
        setSize(660,520);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }


    private void onClickCancelButton(ActionEvent actionEvent) {
        welcome.dispose();
        this.dispose();
    }

    private void onClickLoginButton(ActionEvent actionEvent){
        String uName = usernameTextField.getText();
        String pass = passwordTextField.getText();
        if (!uName.isEmpty() && !pass.isEmpty()) {
            ServiceFactory serviceFactory = ServiceFactory.USER_SERVICE;
            User user = serviceFactory.getUserService().login(uName, pass);
            //serviceFactory.getUserService().emOpen();

            if (user != null) {
                tempUser=user.getUsername();
                tempUserID=user.getId();

                new MenuFrame(tempUser, tempUserID);
                welcome.setVisible(false);
                this.setVisible(false);

            } else {
                //usernameTextField.setBorder(new LineBorder(Color.RED,3,true));
                usernameTextField.setText("User not found !");

            }
        } else {
            usernameTextField.setText("username here");
            usernameTextField.setForeground(Color.lightGray);
            passwordTextField.setText("pass here");
            passwordTextField.setBorder(new LineBorder(Color.RED,3,true));
            passwordTextField.setForeground(Color.lightGray);
        }
    }

    public void onClickRegistrationButton(ActionEvent actionEvent) {
        new CreateNewUser();
    }

}
