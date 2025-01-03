package Panels;

import service.bill.Bill;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ReceiverPanel extends JPanel {
    JPanel title;
    JLabel receiver;
    JLabel transactionAccountNo, transactionAccountValue, referenceNo, referenceValue, amount, amountValue;
    JLabel areaOfSupply, areaOfSupplyValue, address, addressValue;
    public ReceiverPanel() {
        createPanel();
        createComponents();
        addComponents();
    }

    public void createPanel(){
        setSize(320, 280);
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setBackground(Color.WHITE);
        setLayout(new GridLayout(11, 1, 0, 0));
    }

    public void createComponents(){
        receiver = new JLabel("  RECEIVER");
        receiver.setFont(new Font("Times New Roman", Font.BOLD, 16));
        title = new JPanel();
        title.setLayout(new FlowLayout(FlowLayout.LEFT));
        title.add(receiver);

        transactionAccountNo = new JLabel(" Transaction account number:");
        transactionAccountValue = new JLabel("0000000000001");
        referenceNo = new JLabel("  Reference number:");
        referenceValue = new JLabel("--");
        amount = new JLabel("  Amount:");
        amountValue = new JLabel("00,00 KM");
        areaOfSupply = new JLabel("  Area of supply: ");
        areaOfSupplyValue = new JLabel("--");
        address = new JLabel("  Address and Place: ");
        addressValue = new JLabel("-- + --");
        addFont();
    }

    public void addComponents(){
        add(title);
        add(transactionAccountNo);
        add(transactionAccountValue);
        add(referenceNo);
        add(referenceValue);
        add(amount);
        add(amountValue);
        add(areaOfSupply);
        add(areaOfSupplyValue);
        add(address);
        add(addressValue);
    }

    public void setValues(Bill bill){
        referenceValue.setText(bill.getCustomer().getId().toString());
        amountValue.setText(String.valueOf(bill.getAmount()));
        areaOfSupplyValue.setText(bill.getCustomer().getCity());
        addressValue.setText(bill.getCustomer().getAddress());

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ReceiverPanel());
        // frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);

    }

    public void addFont(){
        JLabel[] labels= {transactionAccountNo, referenceNo, amount, areaOfSupply, address};
        JLabel[] valueLabels={ transactionAccountValue, referenceValue, amountValue, areaOfSupplyValue, addressValue };

        for(JLabel label:labels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            label.setHorizontalAlignment(JLabel.LEFT);
        }
        for(JLabel label:valueLabels){
            label.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label.setHorizontalAlignment(JLabel.LEFT);
        }
    }
}
