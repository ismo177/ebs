package EBS.EbsGUI;

import bill.Bill;
import service.Customer.Customer;
import service.Tax.Tax;
import service.User.User;
import service.ServiceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.border.LineBorder;

public class CreateNewCustomer extends JFrame implements  MouseListener {
    JLabel createNewCustomerTitle, emptyLabel1, name, address, city, country, email, phone, contractNo, meterNo, debtBalance, tax;
    JTextField nameTextField, addressTextField, cityTextField, countryTextField, emailTextField, phoneTextField, contractNumberTextField, meterNumberTextField, debtBalanceTextField, taxTextField;
    JButton createButton, exitButton;
    JPanel mainPanel;
    String[] months={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    LocalDateTime timeDate = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
    String date = timeDate.format(myFormatObj);
    String year=date.substring(date.length()-5, date.length()-1);
    int tempUserID;

    CreateNewCustomer(int tempUserID) {
        this.tempUserID = tempUserID;
        setTitle("New Customer");
        setBackground(Color.WHITE);
        setLocation(300, 100);
        setSize(800, 700);
        setResizable(false);
        setVisible(true);
        mainPanel = new JPanel();
       // p.setSize(760, 700);
        mainPanel.setLayout(new GridLayout(13, 2, 10, 10));
        mainPanel.setBackground(Color.WHITE);


        createNewCustomerTitle = new JLabel("New Customer", JLabel.CENTER);
        createNewCustomerTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));

        emptyLabel1 = new JLabel();

        name = new JLabel("Name");
        name.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        nameTextField.addMouseListener(this);

        address = new JLabel("Address:");
        address.setFont(new Font("Times New Roman", Font.BOLD, 20));
        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addressTextField.addMouseListener(this);

        city = new JLabel("City:");
        city.setFont(new Font("Times New Roman", Font.BOLD, 20));
        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cityTextField.addMouseListener(this);


        country = new JLabel("Country");
        country.setFont(new Font("Times New Roman", Font.BOLD, 20));
        countryTextField = new JTextField();
        countryTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        countryTextField.addMouseListener(this);

        email = new JLabel("Email:");
        email.setFont(new Font("Times New Roman", Font.BOLD, 20));
        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        emailTextField.addMouseListener(this);

        phone = new JLabel("Phone:");
        phone.setFont(new Font("Times New Roman", Font.BOLD, 20));
        phoneTextField = new JTextField();
        phoneTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        phoneTextField.addMouseListener(this);

        contractNo = new JLabel("Contract No");
        contractNo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        contractNumberTextField = new JTextField();
        contractNumberTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        contractNumberTextField.addMouseListener(this);

        meterNo = new JLabel("Meter No", JLabel.LEFT);
        meterNo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        meterNumberTextField = new JTextField();
        meterNumberTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        meterNumberTextField.addMouseListener(this);


        debtBalance = new JLabel("Debt Balance");
        debtBalance.setFont(new Font("Times New Roman", Font.BOLD, 20));
        debtBalanceTextField = new JTextField();
        debtBalanceTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        debtBalanceTextField.addMouseListener(this);

        tax = new JLabel("Tax Id", JLabel.LEFT);
        tax.setFont(new Font("Times New Roman", Font.BOLD, 20));
        taxTextField = new JTextField();
        taxTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        taxTextField.addMouseListener(this);


        mainPanel.add(createNewCustomerTitle);
        mainPanel.add(emptyLabel1);

        mainPanel.add(name);
        mainPanel.add(nameTextField);

        mainPanel.add(address);
        mainPanel.add(addressTextField);

        mainPanel.add(city);
        mainPanel.add(cityTextField);

        mainPanel.add(country);
        mainPanel.add(countryTextField);

        mainPanel.add(email);
        mainPanel.add(emailTextField);

        mainPanel.add(phone);
        mainPanel.add(phoneTextField);

        mainPanel.add(contractNo);
        mainPanel.add(contractNumberTextField);

        mainPanel.add(meterNo);
        mainPanel.add(meterNumberTextField);

        mainPanel.add(debtBalance);
        mainPanel.add(debtBalanceTextField);

        mainPanel.add(tax);
        mainPanel.add(taxTextField);


        createButton = new JButton("Create");
        createButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));

        createButton.setBackground(new Color(50, 205, 50));
        createButton.setForeground(Color.BLACK);

        exitButton.setBackground(new Color(50, 205, 50));
        exitButton.setForeground(Color.BLACK);

        mainPanel.add(createButton);
        mainPanel.add(exitButton);
        setLayout(new BorderLayout());

        add(mainPanel, "Center");

        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("images/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(150, 280, Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        phone = new JLabel(ic2);


        add(phone, "West");

        getContentPane().setBackground(Color.WHITE);

        createButton.addActionListener(this::onClickCreateButton);
        exitButton.addActionListener(this::onCLickExitButton);


    }

    public void badInput() {

        JTextField[] fields = {nameTextField, addressTextField, cityTextField, countryTextField, emailTextField, phoneTextField, contractNumberTextField, meterNumberTextField, debtBalanceTextField, taxTextField};
        for (JTextField field : fields) {
            if (field.getText().isEmpty()) {
                field.setBorder(new LineBorder(Color.red,3,true));
                field.setText(" Field should not be empty !!");
                field.setForeground(Color.lightGray);
            } else {
                field.setBorder(new LineBorder(Color.green,3,true));
            }

        }
    }
    //-------------------------------------------------------

public void onClickCreateButton(ActionEvent e) {
    String name = nameTextField.getText();
    String address = addressTextField.getText();
    String city = cityTextField.getText();
    String country = countryTextField.getText();
    String email = emailTextField.getText();
    String phone = phoneTextField.getText();
    String contract = contractNumberTextField.getText();
    String meter = meterNumberTextField.getText();
    String debt = debtBalanceTextField.getText();
    String taxx = taxTextField.getText();

    if (!name.isEmpty() && !address.isEmpty() && !city.isEmpty() && !country.isEmpty() &&
            !email.isEmpty() && !phone.isEmpty() && !contract.isEmpty() && !meter.isEmpty() && !debt.isEmpty() && !taxx.isEmpty()) {

        ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
        serviceFactory.getCustomerService().emOpen();
        Customer customer = serviceFactory.getCustomerService().findByName(name);

        if (customer != null) {
            createNewCustomerTitle.setText("Already Exists");
            createNewCustomerTitle.setBorder(new LineBorder(Color.red,3,true));

        } else {

            customer = new Customer();
            customer.setName(nameTextField.getText());
            customer.setAddress(addressTextField.getText());
            customer.setCity(cityTextField.getText());
            customer.setCountry(countryTextField.getText());
            customer.setEmail(emailTextField.getText());
            customer.setPhone(phoneTextField.getText());
            customer.setContractNo(contractNumberTextField.getText());
            customer.setMeterNo(Integer.parseInt(meterNumberTextField.getText()));
            customer.setDebtBalance(BigDecimal.valueOf(Double.parseDouble(debtBalanceTextField.getText())));
            serviceFactory = ServiceFactory.TAX_SERVICE;
            Tax tax = serviceFactory.getTaxService().find(taxx);
            customer.setTax(tax);
            serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
            serviceFactory.getCustomerService().create(customer);



            for(int i=0; i<months.length; i++) {

                String m="";
                if(i+1<10){m="0"+String.valueOf(i+1);}
                else {m=String.valueOf(i+1);}
                Bill bill = new Bill();
                bill.setUnitsOffPeak(0);
                bill.setUnitsOnPeak(0);
                bill.setReadDate("05."+m+"."+year);
                bill.setIssueDate("15."+m+"."+year);
                bill.setDeadlineDate("25."+m+"."+year);
                bill.setPaymentDate("--");
                bill.setMonth(months[i]);
                bill.setOffPeakAmount(BigDecimal.valueOf(00.00));
                bill.setOnPeakAmount(BigDecimal.valueOf(00.00));
                bill.setAmount(BigDecimal.valueOf(00.00));
                bill.setInvoiceStatus(false);
                bill.setCustomer(customer);

                serviceFactory = ServiceFactory.USER_SERVICE;
                User user = serviceFactory.getUserService().find(tempUserID);
                bill.setUser(user);


                serviceFactory = ServiceFactory.BILL_SERVICE;
                serviceFactory.getBillService().create(bill);
            }

            nameTextField.setBorder(new LineBorder(Color.green,3,true));
            nameTextField.setText("Customer "+"'"+customer.getName()+"'"+" created");

        }
    } else {
        badInput();

    }
}

public void onCLickExitButton(ActionEvent e){
        this.dispose();

}


    @Override
    public void mouseClicked(MouseEvent e) {
        JTextField[] fields = {nameTextField, addressTextField, cityTextField, countryTextField, emailTextField, phoneTextField, contractNumberTextField, meterNumberTextField, debtBalanceTextField, taxTextField};
        for (JTextField field : fields) {
            if(e.getSource()==field) {
                field.setBorder(new LineBorder(Color.gray));
            }
        }
        if (e.getSource() == nameTextField) {
            for (JTextField field : fields) {
                field.setText("");
            }
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