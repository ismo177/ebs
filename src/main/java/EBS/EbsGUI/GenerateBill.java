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


public class GenerateBill extends JFrame implements  MouseListener {

    JLabel titleLabel, customerIdLabel, chooseMonthLabel, imgLabel, offPeakUnitsLabel, onPeakUnitsLabel, monthLabel, offPeakUnitsValueLabel;
    JTextField customerIdTextField, offPeakUnitsTextField, onPeakUnitsTextField;
    JButton generateButton, exitButton, btnChooseMonthLeft, btnChooseMonthRight;
    JPanel mainPanel, centerPanel, bottomPanel, monthChoicePanel;
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String month = months[0];
    int i = 0;
    LocalDateTime timeDate = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
    String date = timeDate.format(myFormatObj);

    int tempCustID;

    GenerateBill(int tempCustID) {
        this.tempCustID = tempCustID;


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

        titleLabel = new JLabel("Set Bill", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setSize(200, 30);
        titleLabel.setLocation(300, 20);
//----------
        customerIdLabel = new JLabel("Customer Id");
        customerIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        chooseMonthLabel = new JLabel("Month");
        chooseMonthLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        offPeakUnitsLabel = new JLabel("Off-Peak Units Consumed");
        offPeakUnitsLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        onPeakUnitsLabel = new JLabel("On-Peak Units Consumed");
        onPeakUnitsLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
//-----------
        monthLabel = new JLabel(month, JLabel.CENTER);
        monthLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        monthLabel.setBorder(new LineBorder(Color.BLACK));
        monthLabel.setBackground(Color.WHITE);

        btnChooseMonthLeft = new JButton("<<");
        btnChooseMonthLeft.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnChooseMonthLeft.setBackground(new Color(50, 205, 50));
        btnChooseMonthLeft.addActionListener(this::onClickMonthChooseButton);

        btnChooseMonthRight = new JButton(">>");
        btnChooseMonthRight.setBackground(new Color(50, 205, 50));
        btnChooseMonthRight.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnChooseMonthRight.addActionListener(this::onClickMonthChooseButton);

        monthChoicePanel = new JPanel();
        monthChoicePanel.setLayout(new BorderLayout());
        monthChoicePanel.setSize(210, 40);
        monthChoicePanel.setLocation(20, 500);

        monthChoicePanel.add(btnChooseMonthLeft, "West");
        monthChoicePanel.add(monthLabel, "Center");
        monthChoicePanel.add(btnChooseMonthRight, "East");
        //--------------

        //offPeakUnitsValueLabel = new JLabel("Off-Peak Units Consumed");
        //offPeakUnitsValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        customerIdTextField = new JTextField(String.valueOf(tempCustID));
        customerIdTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        customerIdTextField.addMouseListener(this);


        offPeakUnitsTextField = new JTextField("0");
        offPeakUnitsTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        offPeakUnitsTextField.addMouseListener(this);

        onPeakUnitsTextField = new JTextField("0");
        onPeakUnitsTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        onPeakUnitsTextField.addMouseListener(this);


        generateButton = new JButton("Generate");
        generateButton.addActionListener(this::onClickGenerateButton);
        generateButton.setBackground(new Color(50, 205, 50));
        generateButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        generateButton.setForeground(Color.BLACK);

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(50, 205, 50));
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        exitButton.setForeground(Color.BLACK);
        exitButton.addActionListener(this::onClickExitButton);

        centerPanel.add(customerIdLabel);
        centerPanel.add(customerIdTextField);
        centerPanel.add(chooseMonthLabel);
        centerPanel.add(monthChoicePanel);
        centerPanel.add(offPeakUnitsLabel);
        centerPanel.add(offPeakUnitsTextField);
        centerPanel.add(onPeakUnitsLabel);
        centerPanel.add(onPeakUnitsTextField);


        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setSize(750, 60);
        bottomPanel.setLocation(20, 480);
        bottomPanel.setLayout(new GridLayout(1, 2, 20, 20));
        bottomPanel.add(generateButton);
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



        public void onClickGenerateButton(ActionEvent e) {
            String id=String.valueOf(tempCustID);
            int offPeak=Integer.parseInt(offPeakUnitsTextField.getText());
            int onPeak=Integer.parseInt(onPeakUnitsTextField.getText());

            if(id.isEmpty() && offPeak<=0 && onPeak<=0) {
                customerIdTextField.setText("ID here");
                customerIdTextField.setBorder(new LineBorder(Color.RED,3,true));
                customerIdTextField.setForeground(Color.lightGray);
                offPeakUnitsTextField.setText("off-peak units here");
                offPeakUnitsTextField.setBorder(new LineBorder(Color.RED,3,true));
                offPeakUnitsTextField.setForeground(Color.lightGray);
                onPeakUnitsTextField.setText("on-peak units here");
                onPeakUnitsTextField.setBorder(new LineBorder(Color.RED,3,true));
                onPeakUnitsTextField.setForeground(Color.lightGray);
            }

            else if(!id.isEmpty() && (offPeak>0  || onPeak>0)) {
                updateBillStatus(id, offPeak, onPeak);
                customerIdTextField.setText("Successful");
                customerIdTextField.setBorder(new LineBorder(Color.GREEN,3,true));
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



        BigDecimal offPeakUnitsBig= BigDecimal.valueOf(offPeak);
        BigDecimal offPeakUnitPrice=tax.getOffPeakPrice();

        BigDecimal onPeakUnitsPrice= tax.getOnPeakPrice();
        BigDecimal onPeakUnitBig= BigDecimal.valueOf(onPeak);

        BigDecimal tierRate= tax.getTierRate();
        BigDecimal offPeakAmount=(offPeakUnitsBig.multiply(offPeakUnitPrice));
        BigDecimal onPeakAmount=(onPeakUnitBig.multiply(onPeakUnitsPrice));


        BigDecimal total=offPeakAmount.add(onPeakAmount).add(tax.getMeterRent().add(tax.getServiceRent()));
        BigDecimal totTax=total.multiply(tierRate).multiply(BigDecimal.valueOf(0.17));
        BigDecimal totalWithTax=total.add(totTax);

        bill.setUnitsOffPeak(offPeak);
        bill.setUnitsOnPeak(onPeak);
        bill.setPaymentDate("--");

        bill.setOffPeakAmount(offPeakAmount);
        bill.setOnPeakAmount(onPeakAmount);

        bill.setAmount(totalWithTax);
        //bill.setUser(user);
        bill.setInvoiceStatus(false);
        serviceFactory = ServiceFactory.BILL_SERVICE;
        serviceFactory.getBillService().edit(bill);

        serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
        BigDecimal custBalance = customer.getDebtBalance();
        if (custBalance.compareTo(BigDecimal.ZERO) < 0) {
            custBalance = custBalance.multiply(new BigDecimal(-1));
        }
        customer.setDebtBalance(custBalance.add(totalWithTax));
        serviceFactory.getCustomerService().edit(customer);


    }

   public void  onClickMonthChooseButton(ActionEvent e) {
       setMonth(e);

    }

    //--------------------------------------------------------
    //setMonth
    public void setMonth(ActionEvent e) {
        if (e.getSource()==btnChooseMonthLeft) {
            if (i > 0) {
                i--;
                month = months[i];
                monthLabel.setText(month);
            } else
                i = months.length - 1;
            month = months[i];
            monthLabel.setText(month);
        }
        if (e.getSource()==btnChooseMonthRight) {
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
        if (e.getSource()== customerIdTextField) {
            customerIdTextField.setBorder(new LineBorder(Color.GRAY));
            customerIdTextField.setText("");
            customerIdTextField.setForeground(Color.BLACK);
        }else if(e.getSource()== offPeakUnitsTextField) {
            offPeakUnitsTextField.setBorder(new LineBorder(Color.GRAY));
            offPeakUnitsTextField.setText("");
            offPeakUnitsTextField.setForeground(Color.BLACK);
        }else if(e.getSource()== onPeakUnitsTextField) {
            onPeakUnitsTextField.setBorder(new LineBorder(Color.GRAY));
            onPeakUnitsTextField.setText("");
            onPeakUnitsTextField.setForeground(Color.BLACK);
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


