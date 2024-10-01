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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PayBill extends JFrame  {
    MonthChoice monthChoice;
    JPanel centerPanel, buttonsPanel;
    JLabel titleLabel, customerId, imgLabel, billDebt;
    JTextField customerIdValue, debtValue, tf3;
    JButton payButton, exitButton;
    int tempCustID;
    String month;

    PayBill(int tempCustID, String month) {
        this.tempCustID = tempCustID;
        this.month = month;
        createComponents();
        createFrame();
        addListeners();
        addComponents();

    }

    public void createFrame(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(700, 500);
        setLocation(300, 100);
        setResizable(false);
        setVisible(true);
    }

    public void createComponents(){

        titleLabel = new JLabel("Pay Bill", JLabel.CENTER);
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

        billDebt = new JLabel("Bill Debt");
        billDebt.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerIdValue = new JTextField(String.valueOf(tempCustID));
        customerIdValue.setFont(new Font("Times New Roman", Font.PLAIN, 24));

        debtValue = new JTextField("00.00");
        debtValue.setFont(new Font("Times New Roman", Font.PLAIN, 24));

        monthChoice = new MonthChoice();
        monthChoice.setMonthLabelValue(month);
        monthChoice.setSize(200, 50);
        monthChoice.setLocation(450,250);

        payButton = new JButton("Pay");
        payButton.setBackground(new Color(50, 205, 50));
        payButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        payButton.setForeground(Color.BLACK);

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(50, 205, 50));
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        exitButton.setForeground(Color.BLACK);

        centerPanel = new JPanel();
        centerPanel.setSize(400, 100);
        centerPanel.setLocation(250, 100);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new GridLayout(2,2,10,10));

        buttonsPanel = new JPanel();
       // buttonsPanel.setBackground(new Color(50, 205, 50));
        buttonsPanel.setSize(440, 60);
        buttonsPanel.setLocation(210, 380);
        buttonsPanel.setLayout(new GridLayout(1, 2, 20,40));

    }

    public void addComponents(){
        centerPanel.add(customerId);
        centerPanel.add(customerIdValue);
        centerPanel.add(billDebt);
        centerPanel.add(debtValue);
        buttonsPanel.add(payButton);
        buttonsPanel.add(exitButton);
        add(titleLabel);
        add(centerPanel);
        add(monthChoice);
        add(imgLabel);
        add(buttonsPanel);

    }

    public void addListeners(){
        payButton.addActionListener(this::onClickPay);
        exitButton.addActionListener(this::onClickExit);
    }

    public String getDate(){
        LocalDateTime timeDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
        String date = timeDate.format(myFormatObj);
        return date;
    }


    public void onClickPay(ActionEvent e) {
        String id = String.valueOf(tempCustID);
        if (id.isEmpty()) {
            infoMessage("ID field is empty");

        } else {
            try {
                updateBillStatus(id);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void onClickExit(ActionEvent e) {
        this.dispose();
    }

    public void updateBillStatus(String id) throws InterruptedException {
        ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
        Customer customer = serviceFactory.getCustomerService().find(id);

        serviceFactory = ServiceFactory.TAX_SERVICE;
        Tax tax = serviceFactory.getTaxService().find(customer.getTax().getId());

        serviceFactory = ServiceFactory.BILL_SERVICE;
        Bill bill = serviceFactory.getBillService().findByMonthCustomer(month, customer);

        if (!bill.getInvoiceStatus() && (bill.getAmount().compareTo(BigDecimal.ZERO) > 0)) {
            pay(customer, bill);
        } else if ((!bill.getInvoiceStatus() && !(bill.getAmount().compareTo(new BigDecimal(0)) > 0))) {
            infoMessage("Bill not ready");

        } else if (bill.getInvoiceStatus()) {
            infoMessage("Bill already Payed on: "+bill.getPaymentDate());
        }
    }

    public void pay(Customer customer, Bill bill) {
        bill.setPaymentDate(getDate());
        //bill.setUser(user);
        BigDecimal total = BigDecimal.valueOf(Double.parseDouble(debtValue.getText()));

        ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
        BigDecimal custBalance = customer.getDebtBalance();
        if (custBalance.compareTo(new BigDecimal(0)) < 0) {
            custBalance = custBalance.multiply(new BigDecimal(-1));
        }
        customer.setDebtBalance(custBalance.subtract(total));
        serviceFactory.getCustomerService().edit(customer);

        serviceFactory = ServiceFactory.BILL_SERVICE;
        bill.setInvoiceStatus(true);
        serviceFactory.getBillService().edit(bill);
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 24));
        JOptionPane.showMessageDialog(this, "Succesuful !",
                "Service", JOptionPane.WARNING_MESSAGE);
    }

    public void infoMessage(String message){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 24));
        JOptionPane.showMessageDialog(this, message+" !",
                "Service", JOptionPane.WARNING_MESSAGE);
    }

}






