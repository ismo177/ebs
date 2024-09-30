package Panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EnergySummary extends JPanel {
    JPanel title, emptySection1, emptySection2, emptySection3;
    JLabel energySummary;
    JLabel itemType, quantity, price, amount;
    JLabel offPeak, ofPeakQuantityValue, ofPeakPriceValue, ofPeakAmountValue ;
    JLabel onPeak, onPeakQuantityValue, onPeakPriceValue, onPeakAmountValue;
    JLabel meterRent, meterRentEmptySection1, meterRentEmptySection2, meterRentAmountValue;
    JLabel serviceRent, serviceRentEmptySection1, serviceRentEmptySection2, serviceRentAmountValue;
    JLabel tierRate, tierRateEmptySection1, tierRateEmptySection2, tierRateAmountValue;

    public EnergySummary() {
    createPanel();
    createComponents();
    addComponents();
    }

    public void createPanel(){
        setSize(420, 170);
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(new GridLayout(7, 4));
        setBackground(Color.WHITE);
    }

    public void createComponents(){
        energySummary = new JLabel("  ENERGY", JLabel.LEFT);
        energySummary.setFont(new Font("Times New Roman", Font.BOLD, 16));
        title = new JPanel();
        title.setLayout(new FlowLayout(FlowLayout.LEFT));
        title.add(energySummary);
        emptySection1 = new JPanel();
        emptySection2 = new JPanel();
        emptySection3 = new JPanel();

        itemType = new JLabel(" Item type", JLabel.LEFT);
        itemType.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        quantity = new JLabel(" Quantity", JLabel.LEFT);
        quantity.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        price = new JLabel(" Price (KM):", JLabel.LEFT);
        price.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        amount = new JLabel(" Amount (KM)", JLabel.LEFT);
        amount.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        offPeak = new JLabel(" Energy off-peak", JLabel.LEFT);
        offPeak.setFont(new Font("Times New Roman", Font.PLAIN, 14));


        ofPeakQuantityValue = new JLabel("--", JLabel.LEFT);
        ofPeakQuantityValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        ofPeakQuantityValue.setBorder(new LineBorder(Color.lightGray));

        ofPeakPriceValue = new JLabel("--", JLabel.LEFT);
        ofPeakPriceValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        ofPeakPriceValue.setBorder(new LineBorder(Color.lightGray));

        ofPeakAmountValue = new JLabel("--", JLabel.LEFT);
        ofPeakAmountValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        ofPeakAmountValue.setBorder(new LineBorder(Color.lightGray));

        onPeak = new JLabel(" Energy on-peak", JLabel.LEFT);
        onPeak.setFont(new Font("Times New Roman", Font.BOLD, 14));

        onPeakQuantityValue = new JLabel("--", JLabel.LEFT);
        onPeakQuantityValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        onPeakQuantityValue.setBorder(new LineBorder(Color.lightGray));

        onPeakPriceValue = new JLabel("--", JLabel.LEFT);
        onPeakPriceValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        onPeakPriceValue.setBorder(new LineBorder(Color.lightGray));

        onPeakAmountValue = new JLabel("--", JLabel.LEFT);
        onPeakAmountValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        onPeakAmountValue.setBorder(new LineBorder(Color.lightGray));


        meterRent = new JLabel(" Meter rent", JLabel.LEFT);
        meterRent.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        meterRentEmptySection1 = new JLabel();//empty
        meterRentEmptySection2 = new JLabel();//empty
        meterRentAmountValue = new JLabel("--", JLabel.LEFT);
        meterRentAmountValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        meterRentAmountValue.setBorder(new LineBorder(Color.lightGray));


        serviceRent = new JLabel(" Service rent", JLabel.LEFT);
        serviceRent.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        serviceRentEmptySection1 = new JLabel();//empty
        serviceRentEmptySection2 = new JLabel();//empty
        serviceRentAmountValue = new JLabel("--", JLabel.LEFT);
        serviceRentAmountValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        serviceRentAmountValue.setBorder(new LineBorder(Color.lightGray));

        tierRate = new JLabel(" Tier rate", JLabel.LEFT);
        tierRate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tierRateEmptySection1 = new JLabel("");//empty
        tierRateEmptySection2=new JLabel();
        tierRateAmountValue = new JLabel("--", JLabel.LEFT);
        tierRateAmountValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        tierRateAmountValue.setBorder(new LineBorder(Color.lightGray));

    }

    public void addComponents(){
        add(title);
        add(emptySection1);
        add(emptySection2);
        add(emptySection3);
        add(itemType);
        add(quantity);
        add(price);
        add(amount);
        add(offPeak);
        add(ofPeakQuantityValue);
        add(ofPeakPriceValue);
        add(ofPeakAmountValue);
        add(onPeak);
        add(onPeakQuantityValue);
        add(onPeakPriceValue);
        add(onPeakAmountValue);
        add(meterRent);
        add(meterRentEmptySection1);
        add(meterRentEmptySection2);
        add(meterRentAmountValue);
        add(serviceRent);
        add(serviceRentEmptySection1);
        add(serviceRentEmptySection2);
        add(serviceRentAmountValue);
        add(tierRate);
        add(tierRateEmptySection1);
        add(tierRateEmptySection2);
        add(tierRateAmountValue);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new EnergySummary());
        frame.setSize(420, 200);
        frame.setVisible(true);

    }
}
