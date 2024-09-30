package Panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PrincipalPanel extends JPanel {
    JPanel title;
    JLabel principal;
    JLabel name, nameValue, address, addressValue;
    JLabel emptySection, bank1, bank2;
    int bank1Value, bank2Value;

    public PrincipalPanel() {
        createPanel();
        createComponents();
        addComponents();

    }
    public void createPanel(){
        setSize(300, 280);
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setBackground(Color.WHITE);
        setLayout(new GridLayout(8, 1, 4, 0));
    }

    public void createComponents(){
        principal = new JLabel("  PRINCIPAL", JLabel.LEFT);
        principal.setFont(new Font("Times New Roman", Font.BOLD, 16));

        title = new JPanel();
        title.setLayout(new FlowLayout(FlowLayout.LEFT));
        title.add(principal);

        name = new JLabel("  Name: ", JLabel.LEFT);
        name.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        nameValue = new JLabel("--", JLabel.LEFT);
        nameValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        nameValue.setBorder(new LineBorder(Color.lightGray));

        address = new JLabel("  Address: ", JLabel.LEFT);
        address.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        addressValue = new JLabel("--", JLabel.LEFT);
        addressValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        addressValue.setBorder(new LineBorder(Color.lightGray));

        emptySection = new JLabel();

        bank1 = new JLabel("  BANK1:  "+bank1Value, JLabel.LEFT);
        bank1.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        bank2 = new JLabel("  BANK2:  "+bank2Value, JLabel.LEFT);
        bank2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    }

    public void addComponents(){
        add(title);
        add(name);
        add(nameValue);
        add(address);
        add(addressValue);
        add(emptySection);
        add(bank1);
        add(bank2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PrincipalPanel());
       // frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);

    }
}
