package Panels;

import bill.Bill;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class AmountChargesPanel extends JPanel {
    JPanel top;
    JLabel amount, amountValue, reference, referenceValue, name, nameValue, address,addressValue, area, areaValue;
    public AmountChargesPanel() {
        createPanel();
        createComponents();
        addComponents();
    }

    public void createPanel(){
        setSize(300, 280);
        //setBorder(new LineBorder(Color.black));
        setLayout(new GridLayout(9, 1, 0, 0));
        setBackground(Color.WHITE);
    }

    public void createComponents(){
        amount = new JLabel("   TOTAL:", JLabel.LEFT);
        amount.setFont(new Font("Times New Roman", Font.BOLD, 14));

        amountValue = new JLabel("--", JLabel.CENTER);
        amountValue.setFont(new Font("Times New Roman", Font.BOLD, 20));
        amountValue.setBorder(new LineBorder(Color.lightGray));

        top = new JPanel();
        top.setLayout(new GridLayout(1, 2));

        top.add(amount);
        top.add(amountValue);

        reference = new JLabel("  Reference number", JLabel.LEFT);
        reference.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        referenceValue = new JLabel("--", JLabel.LEFT);
        referenceValue.setFont(new Font("Times New Roman", Font.BOLD, 14));


        name = new JLabel(" Name: ", JLabel.LEFT);
        name.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        nameValue = new JLabel("--", JLabel.LEFT);
        nameValue.setFont(new Font("Times New Roman", Font.BOLD, 14));

        address = new JLabel(" Address: ", JLabel.LEFT);
        address.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        addressValue = new JLabel("--", JLabel.LEFT);
        addressValue.setFont(new Font("Times New Roman", Font.BOLD, 14));

        area = new JLabel(" Area:", JLabel.LEFT);
        area.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        areaValue = new JLabel("--", JLabel.LEFT);
        areaValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
    }

    public void addComponents(){
        add(top);
        add(reference);
        add(referenceValue);
        add(name);
        add(nameValue);
        add(address);
        add(addressValue);
        add(area);
        add(areaValue);
    }

    public void setValues(Bill bill){
        amountValue.setText(String.valueOf(bill.getAmount()));
        referenceValue.setText(bill.getCustomer().getId().toString());
        nameValue.setText(bill.getCustomer().getName());
        addressValue.setText(bill.getCustomer().getAddress());
        areaValue.setText(bill.getCustomer().getCity());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AmountChargesPanel panel = new AmountChargesPanel();
        frame.getContentPane().add(panel);
        //frame.pack();
        frame.setVisible(true);

    }
}
