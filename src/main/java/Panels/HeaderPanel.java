package Panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {
    JLabel leftLogo, electricityCompanyLeft, dateOfPayment, signatureOfThePrincipal, electricityCompanyRight, rightLogo;
    JPanel panel;
    public HeaderPanel() {
        createComponents();
        createPanel();
        addComponents();

    }

    public void createComponents(){
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("images/green-logo4.jpg"));
        Image image1 = icon1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        leftLogo= new JLabel(new ImageIcon(image1), JLabel.CENTER);
        leftLogo.setBackground(new Color(160, 227, 160));

        electricityCompanyLeft = new JLabel("<html>ELECTRICITY<br>COMPANY</html>", JLabel.LEFT);
        electricityCompanyLeft.setFont(new Font("Times New Roman", Font.BOLD, 13));
        electricityCompanyLeft.setHorizontalTextPosition(SwingConstants.LEFT);

        dateOfPayment = new JLabel("<html>------------------------<br> Date of payment</html>", JLabel.LEFT);
        signatureOfThePrincipal = new JLabel("<html>________________________<br>Signature of the principal</html>", JLabel.LEFT);

        electricityCompanyRight= new JLabel("<html>ELECTRICITY<br>COMPANY</html>", JLabel.RIGHT);
        electricityCompanyRight.setFont(new Font("Times New Roman", Font.BOLD, 13));

        rightLogo=new JLabel(new ImageIcon(image1), JLabel.CENTER);
        rightLogo.setBackground(new Color(160, 227, 160));
    }

    public void createPanel(){
        setSize(960, 80);
       //setBorder(new LineBorder(Color.LIGHT_GRAY));
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 6, 10, 0));

    }

    public void addComponents(){
        add(leftLogo);
        add(electricityCompanyLeft);
        add(dateOfPayment);
        add(signatureOfThePrincipal);
        add(electricityCompanyRight);
        add(rightLogo);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new HeaderPanel());
        frame.pack();
        frame.setVisible(true);

    }
}
