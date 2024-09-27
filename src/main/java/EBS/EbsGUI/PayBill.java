package EBS.EbsGUI;

import bill.Bill;
import service.Customer.Customer;
import service.ServiceFactory;
import service.Tax.Tax;
import service.User.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PayBill extends JFrame implements  MouseListener {

    JLabel titleLabel, customerIdLabel, monthChoiceLabel, imgLabel, billDebt, l5, monthLabel, l6;
    JTextField customerIdValue, debtValue, tf3;
    Choice c1, c2;
    JButton payButton, exitButton, btnLeft, btnRight;
    JPanel mainPanel, centerPanel, bottomPanel, monthChoicePanel;
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String month = months[0];
    int i = 0;
    LocalDateTime timeDate = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
    String date = timeDate.format(myFormatObj);
    int tempUserID;
    int tempCustID;

    PayBill(int tempCustID,int tempUserID) {
        this.tempCustID = tempCustID;
        this.tempUserID = tempUserID;

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2, 30, 30));
        centerPanel.setSize(500, 300);
        centerPanel.setLocation(260, 80);
        centerPanel.setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/green-pay.jpg"));
        Image i2 = i1.getImage().getScaledInstance(180, 270, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        imgLabel = new JLabel(i3);
        imgLabel.setSize(140, 280);
        imgLabel.setLocation(60, 100);

        titleLabel = new JLabel("Pay Bill", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setSize(200, 30);
        titleLabel.setLocation(300, 20);
//----------
        customerIdLabel = new JLabel("Customer Id");
        customerIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        monthChoiceLabel = new JLabel("Month");
        monthChoiceLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        billDebt = new JLabel("Bill Debt");
        billDebt.setFont(new Font("Times New Roman", Font.BOLD, 20));

       // l5 = new JLabel("On-Peak Units Consumed");
        //l5.setFont(new Font("Times New Roman", Font.BOLD, 20));
//-----------
        monthLabel = new JLabel(month, JLabel.CENTER);
        monthLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        monthLabel.setBorder(new LineBorder(Color.BLACK));
        monthLabel.setBackground(Color.WHITE);

        btnLeft = new JButton("<<");
        btnLeft.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnLeft.setBackground(new Color(50, 205, 50));
        btnLeft.addActionListener(this::setMonth);

        btnRight = new JButton(">>");
        btnRight.setBackground(new Color(50, 205, 50));
        btnRight.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnRight.addActionListener(this::setMonth);

        monthChoicePanel = new JPanel();
        monthChoicePanel.setLayout(new BorderLayout());
        monthChoicePanel.setSize(210, 40);
        monthChoicePanel.setLocation(20, 500);

        monthChoicePanel.add(btnLeft, "West");
        monthChoicePanel.add(monthLabel, "Center");
        monthChoicePanel.add(btnRight, "East");
        //--------------



        customerIdValue = new JTextField(String.valueOf(tempCustID));
        customerIdValue.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        customerIdValue.addMouseListener(this);

        debtValue = new JTextField("00.00");
        debtValue.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        debtValue.addMouseListener(this);


        payButton = new JButton("Pay");
        payButton.setBackground(new Color(50, 205, 50));
        payButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        payButton.setForeground(Color.BLACK);
        payButton.addActionListener(this::onClickPay);

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(50, 205, 50));
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        exitButton.setForeground(Color.BLACK);
        exitButton.addActionListener(this::onClickExit);

        centerPanel.add(customerIdLabel);
        centerPanel.add(customerIdValue);
        centerPanel.add(monthChoiceLabel);
        centerPanel.add(monthChoicePanel);
        centerPanel.add(billDebt);
        centerPanel.add(debtValue);



        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setSize(750, 60);
        bottomPanel.setLocation(20, 480);
        bottomPanel.setLayout(new GridLayout(1, 2, 20, 20));
        bottomPanel.add(payButton);
        bottomPanel.add(exitButton);


        mainPanel = new JPanel();
        mainPanel.setSize(800, 600);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLocation(0, 0);


        mainPanel.add(titleLabel);
        mainPanel.add(centerPanel);
        mainPanel.add(imgLabel);
        mainPanel.add(bottomPanel);


        add(mainPanel);
        setLayout(null);
        setSize(800, 600);
        setLocation(300, 100);
        setResizable(false);
        setVisible(true);

    }



    public void onClickPay(ActionEvent e) {
        String id=String.valueOf(tempCustID);

        if(id.isEmpty()) {
            customerIdValue.setText("ID here");
            customerIdValue.setBorder(new LineBorder(Color.RED,3,true));
            customerIdValue.setForeground(Color.lightGray);

        }

        else  {
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
        Customer customer=serviceFactory.getCustomerService().find(id);

        serviceFactory=ServiceFactory.TAX_SERVICE;
        Tax tax=serviceFactory.getTaxService().find(customer.getTax().getId());


        serviceFactory = ServiceFactory.BILL_SERVICE;
        Bill bill= serviceFactory.getBillService().findByMonthCustomer(month,customer);


        serviceFactory = ServiceFactory.USER_SERVICE;
        User user=serviceFactory.getUserService().find(tempUserID);

        if(!bill.getInvoiceStatus() && (bill.getAmount().compareTo(BigDecimal.ZERO)>0)) {
            bill.setPaymentDate(date);
            bill.setUser(user);
            BigDecimal total = BigDecimal.valueOf(Double.parseDouble(debtValue.getText()));


            serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
            BigDecimal custBalance = customer.getDebtBalance();
            if (custBalance.compareTo(new BigDecimal(0)) < 0) {
                custBalance = custBalance.multiply(new BigDecimal(-1));
            }
            customer.setDebtBalance(custBalance.subtract(total));
            serviceFactory.getCustomerService().edit(customer);

            serviceFactory = ServiceFactory.BILL_SERVICE;
            bill.setInvoiceStatus(true);
            serviceFactory.getBillService().edit(bill);

            customerIdValue.setText("Successful");
            customerIdValue.setBorder(new LineBorder(Color.GREEN,3,true));
        }
        else  if((!bill.getInvoiceStatus()  && !(bill.getAmount().compareTo(new BigDecimal(0))>0))) {
            customerIdValue.setText("Bill not set !");
            customerIdValue.setBorder(new LineBorder(Color.RED,3,true));

        } else  if(bill.getInvoiceStatus()) {
            customerIdValue.setText("Payed !");
            customerIdValue.setBorder(new LineBorder(Color.RED,3,true));

        }



    }

    //--------------------------------------------------------
    //setMonth
    public void setMonth(ActionEvent e) {
        if (e.getSource() == btnLeft) {
            if (i > 0) {
                i--;
                month = months[i];
                monthLabel.setText(month);
            } else
                i = months.length - 1;
                month = months[i];
                monthLabel.setText(month);
        }
        if (e.getSource() == btnRight) {
            if (i < months.length - 1) {
                i++;
                month = months[i];
                monthLabel.setText(month);
            } else {
                i = 0;
                month = months[i];
                monthLabel.setText(month);
            }
        }
    }



//--------------------------------------------------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {
            if (e.getSource()== customerIdValue) {
                customerIdValue.setBorder(new LineBorder(Color.GRAY));
                customerIdValue.setText("");
                customerIdValue.setForeground(Color.BLACK);
            }else if(e.getSource()== debtValue) {
                debtValue.setBorder(new LineBorder(Color.GRAY));
                debtValue.setText("");
                debtValue.setForeground(Color.BLACK);
            }else if(e.getSource()==tf3) {
                tf3.setBorder(new LineBorder(Color.GRAY));
                tf3.setText("");
                tf3.setForeground(Color.BLACK);
            }




    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }



}

