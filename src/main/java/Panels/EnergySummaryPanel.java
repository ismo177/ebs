package Panels;

import service.bill.Bill;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class EnergySummaryPanel extends JPanel {
    JPanel title, emptySection1, emptySection2, emptySection3;
    JLabel energySummary;
    JLabel itemType, quantity, price, amount;
    JLabel offPeak, offPeakQuantityValue, offPeakPriceValue, offPeakAmountValue;
    JLabel onPeak, onPeakQuantityValue, onPeakPriceValue, onPeakAmountValue;
    JLabel meterRent, meterRentEmptySection1, meterRentEmptySection2, meterRentAmountValue;
    JLabel serviceRent, serviceRentEmptySection1, serviceRentEmptySection2, serviceRentAmountValue;
    JLabel tierRate, tierRateEmptySection1, tierRateEmptySection2, tierRateAmountValue;

    public EnergySummaryPanel() {
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


        offPeakQuantityValue = new JLabel("--", JLabel.LEFT);
        offPeakQuantityValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        offPeakQuantityValue.setBorder(new LineBorder(Color.lightGray));

        offPeakPriceValue = new JLabel("--", JLabel.LEFT);
        offPeakPriceValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        offPeakPriceValue.setBorder(new LineBorder(Color.lightGray));

        offPeakAmountValue = new JLabel("--", JLabel.LEFT);
        offPeakAmountValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        offPeakAmountValue.setBorder(new LineBorder(Color.lightGray));

        onPeak = new JLabel(" Energy on-peak", JLabel.LEFT);
        onPeak.setFont(new Font("Times New Roman", Font.PLAIN, 14));

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
        add(offPeakQuantityValue);
        add(offPeakPriceValue);
        add(offPeakAmountValue);

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

    public void setValues(Bill bill){
        offPeakQuantityValue.setText(String.valueOf(bill.getUnitsOffPeak()));
        offPeakPriceValue.setText(String.valueOf(bill.getCustomer().getTax().getOffPeakPrice()));
        offPeakAmountValue.setText(String.valueOf(bill.getOffPeakAmount()));

        onPeakQuantityValue.setText(String.valueOf(bill.getUnitsOnPeak()));
        onPeakPriceValue.setText(String.valueOf(bill.getCustomer().getTax().getOnPeakPrice()));
        onPeakAmountValue.setText(String.valueOf(bill.getOnPeakAmount()));

        meterRentAmountValue.setText(String.valueOf(bill.getCustomer().getTax().getMeterRent()));
        serviceRentAmountValue.setText(String.valueOf(bill.getCustomer().getTax().getServiceRent()));

        BigDecimal tierRateAmount = getTierRate(bill);
        tierRateAmountValue.setText(String.valueOf(tierRateAmount));

    }

    private  BigDecimal getTierRate(Bill bill) {
        BigDecimal offPeakAmount = bill.getOffPeakAmount();
        BigDecimal onPeakAmount = bill.getOnPeakAmount();
        BigDecimal meterRentAmount = bill.getCustomer().getTax().getMeterRent();
        BigDecimal serviceRentAmount = bill.getCustomer().getTax().getServiceRent();
        BigDecimal withoutTierRate = offPeakAmount.add(onPeakAmount).add(meterRentAmount).add(serviceRentAmount);
        BigDecimal withTierRate = withoutTierRate.multiply(bill.getCustomer().getTax().getTierRate());
        BigDecimal tierRateAmount = withTierRate.subtract(withoutTierRate);
        MathContext mathContext1=new MathContext(3, RoundingMode.HALF_DOWN);
        MathContext mathContext2=new MathContext(4, RoundingMode.HALF_DOWN);
        MathContext mathContext;
        if(tierRateAmount.compareTo(BigDecimal.ZERO)<10){
            mathContext=mathContext1;
        }else{mathContext=mathContext2;}
        return tierRateAmount.round(mathContext);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new EnergySummaryPanel());
        frame.setSize(420, 200);
        frame.setVisible(true);

    }
}
