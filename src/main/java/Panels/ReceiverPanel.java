package Panels;

import bill.Bill;

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

        transactionAccountNo = new JLabel(" Transaction account number:", JLabel.LEFT);
        transactionAccountNo.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        transactionAccountValue = new JLabel("0000000000001", JLabel.LEFT);
        transactionAccountValue.setFont(new Font("Times New Roman", Font.BOLD, 12));

        referenceNo = new JLabel("  Reference number:", JLabel.LEFT);
        referenceNo.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        referenceValue = new JLabel("--", JLabel.LEFT);
        referenceValue.setFont(new Font("Times New Roman", Font.BOLD, 12));

        amount = new JLabel("  Amount:", JLabel.LEFT);
        amount.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        amountValue = new JLabel("00,00 KM", JLabel.LEFT);
        amountValue.setFont(new Font("Times New Roman", Font.BOLD, 12));

        areaOfSupply = new JLabel("  Area of supply: ", JLabel.LEFT);
        areaOfSupply.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        areaOfSupplyValue = new JLabel("--", JLabel.LEFT);
        areaOfSupplyValue.setFont(new Font("Times New Roman", Font.BOLD, 12));

        address = new JLabel("  Address and Place: ", JLabel.LEFT);
        address.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        addressValue = new JLabel("-- + --", JLabel.LEFT);
        addressValue.setFont(new Font("Times New Roman", Font.BOLD, 12));
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
}
