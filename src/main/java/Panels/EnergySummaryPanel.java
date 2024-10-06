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
        setSize(420, 160);
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(new GridLayout(6, 4));
        setBackground(Color.WHITE);
    }

    public void createComponents(){
        energySummary = new JLabel("  ENERGY");
        title = new JPanel();
        title.setLayout(new FlowLayout(FlowLayout.LEFT));
        title.add(energySummary);
        emptySection1 = new JPanel();
        emptySection2 = new JPanel();
        emptySection3 = new JPanel();

        itemType = new JLabel(" Item type");
        quantity = new JLabel(" Quantity");
        price = new JLabel(" Price (KM):");
        amount = new JLabel(" Amount (KM)");

        offPeak = new JLabel(" Energy off-peak");
        offPeakQuantityValue = new JLabel("--");
        offPeakPriceValue = new JLabel("--");
        offPeakAmountValue = new JLabel("--");

        onPeak = new JLabel(" Energy on-peak");
        onPeakQuantityValue = new JLabel("--");
        onPeakPriceValue = new JLabel("--");
        onPeakAmountValue = new JLabel("--");

        meterRent = new JLabel(" Meter rent");
        meterRentEmptySection1 = new JLabel();//empty
        meterRentEmptySection2 = new JLabel();//empty
        meterRentAmountValue = new JLabel("--");

        serviceRent = new JLabel(" Service rent");
        serviceRentEmptySection1 = new JLabel();//empty
        serviceRentEmptySection2 = new JLabel();//empty
        serviceRentAmountValue = new JLabel("--");

        tierRate = new JLabel(" Tier rate");
        tierRateEmptySection1 = new JLabel("");//empty
        tierRateEmptySection2=new JLabel();
        tierRateAmountValue = new JLabel("--");
        addFontAndHorizontalAlignment();
    }

    public void addComponents(){
        JLabel[] labels= {itemType, quantity, price, amount,
                            offPeak, offPeakQuantityValue, offPeakPriceValue, offPeakAmountValue,
                            onPeak, onPeakQuantityValue, onPeakPriceValue, onPeakAmountValue,
                            meterRent, meterRentEmptySection1, meterRentEmptySection2, meterRentAmountValue,
                            tierRate, tierRateEmptySection1, tierRateEmptySection2, tierRateAmountValue };
        add(title);
        add(emptySection1);
        add(emptySection2);
        add(emptySection3);
        for(JLabel label:labels){
            add(label);
        }

    }

    public void addFontAndHorizontalAlignment(){
        JLabel[] headerLabels= { itemType, quantity, price, amount};
        JLabel[] labels= { offPeak, onPeak, meterRent, serviceRent, tierRate};
        JLabel[] labelValues= { offPeakQuantityValue, offPeakPriceValue, offPeakAmountValue,
                                onPeakQuantityValue, onPeakPriceValue, onPeakAmountValue,
                                 meterRentAmountValue, serviceRentAmountValue, tierRateAmountValue};
        energySummary.setFont(new Font("Times New Roman", Font.BOLD, 16));
        for(JLabel label : headerLabels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            label.setHorizontalAlignment(JLabel.LEFT);

        }
        for(JLabel label:labels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            label.setHorizontalAlignment(JLabel.LEFT);
        }
        for(JLabel label:labelValues){
            label.setFont(new Font("Times New Roman", Font.BOLD, 14));
            label.setBorder(new LineBorder(Color.lightGray));
            label.setHorizontalAlignment(JLabel.LEFT);
        }
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
