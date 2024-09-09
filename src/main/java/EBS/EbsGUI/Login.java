package EBS.EbsGUI;

import EBS.EntityServices.ServiceFactory;
import EBS.EntityServices.User.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements KeyListener, MouseListener{

    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel l3;
    JTextField usernameTextField;
    JPasswordField passwordTextField;
    JButton loginButton, cancelButton;
    JPanel topPanel, buttonsPanel, imgPanel,p4;
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
        usernameTextField.addMouseListener(this);

        passwordTextField =new JPasswordField(15);
        passwordTextField.setFont(new Font("Serif",Font.PLAIN,22));
        passwordTextField.addMouseListener(this);

        loginButton =new JButton("Login");
        loginButton.setFont(new Font("Serif",Font.BOLD,24));
        loginButton.setBackground(new Color(50,205,50));
        // loginButton.setFocusable(true);
        //loginButton.requestFocus();
        loginButton.addKeyListener(this);


        cancelButton =new JButton("Cancel");
        cancelButton.setBackground(new Color(50,205,50));
        cancelButton.setFont(new Font("Serif",Font.BOLD,24));
        cancelButton.addKeyListener(this);

        loginButton.addActionListener(this::onLoginButtonClick);
        cancelButton.addActionListener(this::onCancelButtonClick);


        ImageIcon ic3=new ImageIcon(ClassLoader.getSystemResource("images/green-login.jpg"));
        Image i3=ic3.getImage().getScaledInstance(360,240,Image.SCALE_DEFAULT);
        ImageIcon icc3=new ImageIcon(i3);

        l3=new JLabel(icc3);

        topPanel =new JPanel();
        buttonsPanel =new JPanel();
        imgPanel =new JPanel();


        topPanel.setLayout(new GridLayout(2,1,10,10));
        topPanel.add(usernameLabel);
        topPanel.add(usernameTextField);
        topPanel.add(passwordLabel);
        topPanel.add(passwordTextField);
        topPanel.setSize(600,100);
        topPanel.setLocation(0,0);


        buttonsPanel.add(loginButton);
        buttonsPanel.add(cancelButton);
        buttonsPanel.setSize(660,80);
        buttonsPanel.setLocation(0,360);
        buttonsPanel.setLayout(new GridLayout(1,2,10,0));


        topPanel.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.WHITE);

        imgPanel.add(l3);
        imgPanel.setSize(660,260);
        imgPanel.setLocation(0,100);
        imgPanel.setBackground(Color.white);

        add(topPanel);
        add(imgPanel);
        add(buttonsPanel);

        welcome=new Welcome();
        getContentPane().setBackground(Color.white);
        setSize(660,480);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        //setFocusable(true);
        addKeyListener(this);

    }

    private void onCancelButtonClick(ActionEvent actionEvent) {
        welcome.dispose();
        this.dispose();
    }

    private void onLoginButtonClick(ActionEvent actionEvent){
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
                usernameTextField.setBorder(new LineBorder(Color.RED,3,true));
                usernameTextField.setText("User not found !");

            }
        } else {
            usernameTextField.setText("username here");
            usernameTextField.setBorder(new LineBorder(Color.RED,3,true));
            usernameTextField.setForeground(Color.lightGray);
            passwordTextField.setText("pass here");
            passwordTextField.setBorder(new LineBorder(Color.RED,3,true));
            passwordTextField.setForeground(Color.lightGray);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
          if(e.getSource()== usernameTextField) {
              usernameTextField.setText("");
              usernameTextField.setForeground(Color.BLACK);
              usernameTextField.setBorder(new LineBorder(Color.gray));
              usernameTextField.setFont(new Font("Times New Roman",Font.PLAIN,22));
          }
              else if(e.getSource()== passwordTextField){
                  passwordTextField.setText("");
                  passwordTextField.setForeground(Color.BLACK);
                  passwordTextField.setBorder(new LineBorder(Color.gray));

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



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            if(e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                loginButton.doClick();
            }
            else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                cancelButton.doClick();

            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You released key : " +e.getKeyChar() );
    }

}
