package Panels;

import service.bill.Bill;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PrincipalPanel extends JPanel {
    JPanel title;
    JLabel principal;
    JLabel name, nameValue, address, addressValue;
    JLabel emptySection, bank1, bank2, bank3;
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
        setLayout(new GridLayout(9, 1, 4, 0));
    }

    public void createComponents(){
        principal = new JLabel("  PRINCIPAL", JLabel.LEFT);
        principal.setFont(new Font("Times New Roman", Font.BOLD, 16));

        title = new JPanel();
        title.setLayout(new FlowLayout(FlowLayout.LEFT));
        title.add(principal);

        name = new JLabel("  Name: ", JLabel.LEFT);
        nameValue = new JLabel("--", JLabel.LEFT);
        nameValue.setBorder(new LineBorder(Color.lightGray));
        address = new JLabel("  Address: ", JLabel.LEFT);
        addressValue = new JLabel("--", JLabel.LEFT);
        addressValue.setBorder(new LineBorder(Color.lightGray));
        emptySection = new JLabel();

        bank1 = new JLabel("  BANK1:  "+bank1Value, JLabel.LEFT);
        bank1.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        bank2 = new JLabel("  BANK2:  "+bank2Value, JLabel.LEFT);
        bank2.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        bank3 = new JLabel("  BANK3:  " + bank2Value, JLabel.LEFT);
        bank3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        addFont();
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
        add(bank3);
    }

    public void setValues(Bill bill){
        nameValue.setText(bill.getCustomer().getName());
        addressValue.setText(bill.getCustomer().getAddress());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PrincipalPanel());
       // frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);

    }

    public void addFont(){
        JLabel[] labels={name, address};
        JLabel[] valueLabels= {nameValue,addressValue};
        for(JLabel label:labels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        }
        for(JLabel label:valueLabels){
            label.setFont(new Font("Times New Roman", Font.BOLD, 18));
        }
    }
}
