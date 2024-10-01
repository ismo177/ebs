package EBS.EbsGUI;

import Panels.MonthChoice;
import bill.Bill;
import service.Customer.Customer;
import service.ServiceFactory;
import service.Tax.Tax;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class GenerateBill extends JFrame  {

    JLabel titleLabel, customerId, imgLabel, offPeakUnits, onPeakUnits;
    JTextField customerIdValue, offPeakUnitsValue, onPeakUnitsValue;
    JButton generateButton, exitButton;
    JPanel  centerPanel, buttonsPanel;
    MonthChoice monthChoice;
    int tempCustID;
    String month;
    GenerateBill(int tempCustID, String month) {
        this.tempCustID = tempCustID;
        this.month = month;
           createComponents();
           createFrame();
           addComponents();
           addListeners();


    }

    public void createFrame(){
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(700, 500);
        setLocation(300, 100);
        setResizable(false);
        setVisible(true);
    }

    public void createComponents(){
        titleLabel = new JLabel("Set Bill", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setSize(260, 30);
        titleLabel.setLocation(200, 20);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/green-pay.jpg"));
        Image i2 = i1.getImage().getScaledInstance(180, 270, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        imgLabel = new JLabel(i3);
        imgLabel.setSize(140, 280);
        imgLabel.setLocation(60, 100);

        customerId = new JLabel("Customer Id");
        customerId.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerIdValue = new JTextField(String.valueOf(tempCustID));
        customerIdValue.setFont(new Font("Times New Roman", Font.PLAIN, 24));

        offPeakUnits = new JLabel("Off-Peak Units ");
        offPeakUnits.setFont(new Font("Times New Roman", Font.BOLD, 20));

        offPeakUnitsValue = new JTextField("0");
        offPeakUnitsValue.setFont(new Font("Times New Roman", Font.PLAIN, 24));

        onPeakUnits = new JLabel("On-Peak Units ");
        onPeakUnits.setFont(new Font("Times New Roman", Font.BOLD, 20));

        onPeakUnitsValue = new JTextField("0");
        onPeakUnitsValue.setFont(new Font("Times New Roman", Font.PLAIN, 24));

        monthChoice = new MonthChoice();
        monthChoice.setMonthLabelValue(month);
        monthChoice.setSize(200, 50);
        monthChoice.setLocation(450,280);

        generateButton = new JButton("Generate");
        generateButton.setBackground(new Color(50, 205, 50));
        generateButton.setFont(new Font("Times New Roman", Font.BOLD, 24));

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(50, 205, 50));
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 24));

        centerPanel = new JPanel();
        centerPanel.setSize(400, 160);
        centerPanel.setLocation(250, 100);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new GridLayout(3,2,10,10));

        buttonsPanel = new JPanel();
        buttonsPanel.setSize(440, 60);
        buttonsPanel.setLocation(210, 380);
        buttonsPanel.setLayout(new GridLayout(1, 2, 20,20));
    }


    public void addComponents(){
        centerPanel.add(customerId);
        centerPanel.add(customerIdValue);
        centerPanel.add(offPeakUnits);
        centerPanel.add(offPeakUnitsValue);
        centerPanel.add(onPeakUnits);
        centerPanel.add(onPeakUnitsValue);

        buttonsPanel.add(generateButton);
        buttonsPanel.add(exitButton);

        add(titleLabel);
        add(centerPanel);
        add(monthChoice);
        add(imgLabel);
        add(buttonsPanel);
    }

    public void addListeners(){
        generateButton.addActionListener(this::onClickGenerateButton);
        exitButton.addActionListener(this::onClickExitButton);
    }

    public String getDate(){
        LocalDateTime timeDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
        String date = timeDate.format(myFormatObj);
        return date;
    }

        public void onClickGenerateButton(ActionEvent e) {
            String id=String.valueOf(tempCustID);
            int offPeak=Integer.parseInt(offPeakUnitsValue.getText());
            int onPeak=Integer.parseInt(onPeakUnitsValue.getText());

            if(id.isEmpty() && offPeak<=0 && onPeak<=0) {
              infoMessage("Bad input");
            }

            else if(!id.isEmpty() && (offPeak>0  || onPeak>0)) {
                updateBillStatus(id, offPeak, onPeak);
                infoMessage("Successfully generated bill");
            }
        }



        public void onClickExitButton(ActionEvent e){
            this.dispose();
        }


    public void updateBillStatus(String id, int offPeak, int onPeak){
        ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
        Customer customer=serviceFactory.getCustomerService().find(tempCustID);

        serviceFactory=ServiceFactory.TAX_SERVICE;
        Tax tax=serviceFactory.getTaxService().find(customer.getTax().getId());

        serviceFactory = ServiceFactory.BILL_SERVICE;
        Bill bill= serviceFactory.getBillService().findByMonthCustomer(month,customer);

        BigDecimal[] values= calculateValues(offPeak, onPeak, tax);
        setNewBillValues(offPeak, onPeak, bill, values);
        calculateNewDebtBalance(customer, values);

    }

    public void infoMessage(String message){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 24));
        JOptionPane.showMessageDialog(this, message+" !",
                "Service", JOptionPane.WARNING_MESSAGE);
    }

    public  BigDecimal[] calculateValues(int offPeak, int onPeak, Tax tax){
        BigDecimal offPeakUnitsQuantity= BigDecimal.valueOf(offPeak);
        BigDecimal onPeakUnitsQuantity= BigDecimal.valueOf(onPeak);

        BigDecimal offPeakUnitPrice=tax.getOffPeakPrice();
        BigDecimal onPeakUnitsPrice= tax.getOnPeakPrice();
        BigDecimal tierRate= tax.getTierRate();
        BigDecimal pdvTax=tax.getPDVTax();

        BigDecimal offPeakAmount=(offPeakUnitsQuantity.multiply(offPeakUnitPrice));
        BigDecimal onPeakAmount=(onPeakUnitsQuantity.multiply(onPeakUnitsPrice));

        BigDecimal withoutTierRate = offPeakAmount.add(onPeakAmount).add(tax.getMeterRent().add(tax.getServiceRent()));
        BigDecimal withTierRate = withoutTierRate.multiply(tierRate);
        BigDecimal taxBH= withTierRate.multiply(pdvTax);

        BigDecimal total=withTierRate.add(taxBH).round(new MathContext(5, RoundingMode.HALF_DOWN));
        BigDecimal[] values={offPeakAmount, onPeakAmount, total};
        return values;
    }

    public void setNewBillValues(int offPeak, int onPeak, Bill bill, BigDecimal[] values){
        bill.setUnitsOffPeak(offPeak);
        bill.setUnitsOnPeak(onPeak);
        bill.setPaymentDate("--");

        bill.setOffPeakAmount(values[0]);
        bill.setOnPeakAmount(values[1]);
        bill.setAmount(values[2]);
       // bill.setUser(user);
        bill.setInvoiceStatus(false);
        ServiceFactory serviceFactory = ServiceFactory.BILL_SERVICE;
        serviceFactory.getBillService().edit(bill);
    }

    public void calculateNewDebtBalance(Customer customer, BigDecimal[] values){
       ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
        BigDecimal custBalance = customer.getDebtBalance();
        if (custBalance.compareTo(BigDecimal.ZERO) < 0) {
            custBalance = custBalance.multiply(new BigDecimal(-1));
        }
        customer.setDebtBalance(custBalance.add(values[2]));
        serviceFactory.getCustomerService().edit(customer);
    }


}


