package EBS.EbsGUI;

import Panels.MonthChoice;
import service.bill.Bill;
import service.Customer.Customer;
import service.CrudServiceFactory;
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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocation(300, 100);
        setResizable(false);
        setVisible(true);
    }

    public void createComponents(){
        titleLabel = new JLabel("Generate Bill", JLabel.CENTER);
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
        customerIdValue = new JTextField(String.valueOf(tempCustID));
        offPeakUnits = new JLabel("Off-Peak Units ");
        offPeakUnitsValue = new JTextField("0");
        onPeakUnits = new JLabel("On-Peak Units ");
        onPeakUnitsValue = new JTextField("0");

        monthChoice = new MonthChoice();
        monthChoice.setMonthLabelValue(month);
        monthChoice.setSize(200, 50);
        monthChoice.setLocation(450,280);

        generateButton = new JButton("Generate");
        exitButton = new JButton("Exit");
        addFont();
        createBackPanels();
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

    public void createBackPanels(){
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

    public void addFont(){
        JLabel[] labels = { customerId, offPeakUnits, onPeakUnits };
        JTextField[] labelValue = { customerIdValue, offPeakUnitsValue, onPeakUnitsValue };
        JButton[] buttons= { generateButton, exitButton };

        for(JLabel label : labels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        }
        for(JTextField tf : labelValue){
            tf.setFont(new Font("Times New Roman", Font.BOLD, 24));
        }
        for(JButton button : buttons){
            button.setFont(new Font("Times New Roman", Font.BOLD, 24));
            button.setBackground(new Color(50, 205, 50));
        }
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
                infoMessage("Successfully generated service.bill");
            }
        }



        public void onClickExitButton(ActionEvent e){
            this.dispose();
        }


    public void updateBillStatus(String id, int offPeak, int onPeak){
        CrudServiceFactory crudServiceFactory = CrudServiceFactory.CUSTOMER_SERVICE;
        Customer customer= crudServiceFactory.getCustomerService().find(tempCustID);

        crudServiceFactory = CrudServiceFactory.TAX_SERVICE;
        Tax tax= crudServiceFactory.getTaxService().find(customer.getTax().getId());

        crudServiceFactory = CrudServiceFactory.BILL_SERVICE;
        Bill bill= crudServiceFactory.getBillService().findByMonthCustomer(month,customer);

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
       // service.bill.setUser(user);
        bill.setInvoiceStatus(false);
        CrudServiceFactory crudServiceFactory = CrudServiceFactory.BILL_SERVICE;
        crudServiceFactory.getBillService().edit(bill);
    }

    public void calculateNewDebtBalance(Customer customer, BigDecimal[] values){
       CrudServiceFactory crudServiceFactory = CrudServiceFactory.CUSTOMER_SERVICE;
        BigDecimal custBalance = customer.getDebtBalance();
        if (custBalance.compareTo(BigDecimal.ZERO) < 0) {
            custBalance = custBalance.multiply(new BigDecimal(-1));
        }
        customer.setDebtBalance(custBalance.add(values[2]));
        crudServiceFactory.getCustomerService().edit(customer);
    }

    public static void main(String[] args) {
        new GenerateBill(14, "January");
    }

}


