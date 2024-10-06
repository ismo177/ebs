package Panels;

import service.bill.Bill;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

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
        amountValue = new JLabel("--", JLabel.CENTER);
        amountValue.setFont(new Font("Times New Roman", Font.BOLD, 20));
        amountValue.setBorder(new LineBorder(Color.lightGray));

        top = new JPanel();
        top.setLayout(new GridLayout(1, 2));

        top.add(amount);
        top.add(amountValue);

        reference = new JLabel("  Reference number", JLabel.LEFT);
        referenceValue = new JLabel("--", JLabel.LEFT);
        name = new JLabel(" Name: ", JLabel.LEFT);
        nameValue = new JLabel("--", JLabel.LEFT);
        address = new JLabel(" Address: ", JLabel.LEFT);
        addressValue = new JLabel("--", JLabel.LEFT);
        area = new JLabel(" Area:", JLabel.LEFT);
        areaValue = new JLabel("--", JLabel.LEFT);
        addFont();
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

    public void addFont(){
        JLabel[] labels={ amount, reference, referenceValue, name, nameValue,
                            address,addressValue, area, areaValue};
        for(JLabel label:labels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        }
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
