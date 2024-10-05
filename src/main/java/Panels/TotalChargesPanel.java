package Panels;

import bill.Bill;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TotalChargesPanel extends JPanel {
        JLabel amountLabel, amountValue;
        JLabel tax, taxValue;
        JLabel total, totalValue;
    public TotalChargesPanel() {
        createPanel();
        createComponents();
        addComponents();
    }

    public void createPanel(){
        setLayout(new GridLayout(3, 2));
        setSize(300, 100);
        //setBorder(new LineBorder(Color.LIGHT_GRAY));
        setBackground(Color.WHITE);
    }

    public void createComponents(){
        amountLabel = new JLabel(" Amount with TierRate :", JLabel.LEFT);
        amountLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        amountValue = new JLabel("--", JLabel.LEFT);
        amountValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        amountValue.setBorder(new LineBorder(Color.lightGray));

        tax = new JLabel(" Tax 17.00%:", JLabel.LEFT);
        tax.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        taxValue = new JLabel("--", JLabel.LEFT);
        taxValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        taxValue.setBorder(new LineBorder(Color.lightGray));

        total = new JLabel(" Total :", JLabel.LEFT);
        total.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        totalValue = new JLabel("--", JLabel.LEFT);
        totalValue.setFont(new Font("Times New Roman", Font.BOLD, 18));
        totalValue.setBorder(new LineBorder(Color.lightGray));
    }

    public void addComponents(){
        add(amountLabel);
        add(amountValue);
        add(tax);
        add(taxValue);
        add(total);
        add(totalValue);
    }

    public void setValues(Bill bill){
        BigDecimal total = bill.getAmount();
        BigDecimal pdv=bill.getCustomer().getTax().getPDVTax();
        BigDecimal meterRent=bill.getCustomer().getTax().getMeterRent();
        BigDecimal serviceRent=bill.getCustomer().getTax().getServiceRent();
        BigDecimal rawAmount=bill.getOnPeakAmount().add(bill.getOffPeakAmount());
        BigDecimal amount = bill.getOnPeakAmount().add(bill.getOffPeakAmount().add(meterRent).add(serviceRent));
        if(rawAmount.compareTo(BigDecimal.ZERO)==0){
            amount=BigDecimal.ZERO;
            pdv=BigDecimal.ZERO;
            total=BigDecimal.ZERO;
        }
        amount=amount.multiply(bill.getCustomer().getTax().getTierRate());
        MathContext mc3 = new MathContext(4, RoundingMode.HALF_DOWN);
        MathContext mc4 = new MathContext(5, RoundingMode.HALF_DOWN);

        if(amount.compareTo(BigDecimal.ZERO)<10){
            amount=amount.round(mc3);
        }
        else{
            amount=amount.round(mc4);
        }
        BigDecimal tax= amount.multiply(pdv);
        if(tax.compareTo(BigDecimal.ZERO)<10){
            tax=tax.round(mc3);
        }
        else{
            tax=tax.round(mc4);
        }

        amountValue.setText(String.valueOf(amount));
        taxValue.setText(String.valueOf(tax));
       // totalValue.setText(String.valueOf(amount.add(tax)));
        totalValue.setText(String.valueOf(total));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 90);
        frame.getContentPane().add(new TotalChargesPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


}
