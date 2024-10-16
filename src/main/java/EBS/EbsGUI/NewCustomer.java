package EBS.EbsGUI;

import service.bill.Bill;
import service.Customer.Customer;
import service.Tax.Tax;
import service.CrudServiceFactory;
import service.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.border.LineBorder;

public class NewCustomer extends JFrame  {
    JLabel createNewCustomerTitle, emptyLabel1, name, address, city, country, email, phone, contractNo, meterNo, debtBalance, tax;
    JLabel emptyLabel2, emptyLabel3;
    JTextField nameValue, addressValue, cityValue, countryValue, emailValue, phoneValue, contractNoValue, meterNoValue, debtBalanceValue, taxValue;
    JButton createButton, exitButton;
    JPanel mainPanel, imgPanel;
    JLabel imgLabel;
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    User tempUser;

    NewCustomer(User tempUser) {
        this.tempUser = tempUser;
        createComponents();
        createFrame();
        addComponents();
        addListeners();

    }

    public void createFrame(){
        setTitle("New Customer");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void createComponents(){
        mainPanel = new JPanel();
        mainPanel.setSize(550, 700);
        mainPanel.setLocation(200,10);
        mainPanel.setLayout(new GridLayout(14, 2, 10, 15));
        mainPanel.setBackground(Color.WHITE);

        imgPanel = new JPanel();
        imgPanel.setBackground(Color.WHITE);
        //imgPanel.setLayout(null);
        imgPanel.setSize(150,280);
        imgPanel.setLocation(20,200);


        createNewCustomerTitle = new JLabel("   New Customer", JLabel.CENTER);
        createNewCustomerTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));

        emptyLabel1 = new JLabel();
        emptyLabel2 = new JLabel();
        emptyLabel3 = new JLabel();

        name = new JLabel("Name");
        nameValue = new JTextField();

        address = new JLabel("Address:");
        addressValue = new JTextField();

        city = new JLabel("City:");
        cityValue = new JTextField();

        country = new JLabel("Country");
        countryValue = new JTextField();

        email = new JLabel("Email:");
        emailValue = new JTextField();

        phone = new JLabel("Phone:");
        phoneValue = new JTextField();

        contractNo = new JLabel("Contract No");
        contractNoValue = new JTextField();

        meterNo = new JLabel("Meter No", JLabel.LEFT);
        meterNoValue = new JTextField();

        debtBalance = new JLabel("Debt Balance");
        debtBalanceValue = new JTextField();

        tax = new JLabel("Tax Id", JLabel.LEFT);
        taxValue = new JTextField();

        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("images/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(150, 280, Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        imgLabel = new JLabel(ic2);


        createButton = new JButton("Create");
        createButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));

        createButton.setBackground(new Color(50, 205, 50));
        createButton.setForeground(Color.BLACK);

        exitButton.setBackground(new Color(50, 205, 50));
        exitButton.setForeground(Color.BLACK);
        addFont();
    }

    public void addComponents(){
        mainPanel.add(createNewCustomerTitle);
        mainPanel.add(emptyLabel1);

        mainPanel.add(name);
        mainPanel.add(nameValue);

        mainPanel.add(address);
        mainPanel.add(addressValue);

        mainPanel.add(city);
        mainPanel.add(cityValue);

        mainPanel.add(country);
        mainPanel.add(countryValue);

        mainPanel.add(email);
        mainPanel.add(emailValue);

        mainPanel.add(phone);
        mainPanel.add(phoneValue);

        mainPanel.add(contractNo);
        mainPanel.add(contractNoValue);

        mainPanel.add(meterNo);
        mainPanel.add(meterNoValue);

        mainPanel.add(debtBalance);
        mainPanel.add(debtBalanceValue);

        mainPanel.add(tax);
        mainPanel.add(taxValue);

        mainPanel.add(emptyLabel2);
        mainPanel.add(emptyLabel3);
        mainPanel.add(createButton);
        mainPanel.add(exitButton);

        add(mainPanel);
        imgPanel.add(imgLabel);
        add(imgPanel);

    }


    public String getDateTime(){
        LocalDateTime timeDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
        String date = timeDate.format(myFormatObj);
        return date;
    }

    public void badInput() {

        JTextField[] fields = {nameValue, addressValue, cityValue, countryValue,
                emailValue, phoneValue, contractNoValue, meterNoValue, debtBalanceValue, taxValue};
        for (JTextField field : fields) {
            if (field.getText().isEmpty()) {
                field.setBorder(new LineBorder(Color.red, 1, true));
                field.setForeground(Color.LIGHT_GRAY);
                field.setText(" Field should not be empty !!");
            }

        }
    }


    public void onClickCreateButton(ActionEvent e) {
        String name = nameValue.getText();
        String address = addressValue.getText();
        String city = cityValue.getText();
        String country = countryValue.getText();
        String email = emailValue.getText();
        String phone = phoneValue.getText();
        String contract = contractNoValue.getText();
        String meter = meterNoValue.getText();
        String debt = debtBalanceValue.getText();
        String taxInput = taxValue.getText();

        if (!name.isEmpty() && !address.isEmpty() && !city.isEmpty() && !country.isEmpty() &&
                !email.isEmpty() && !phone.isEmpty() && !contract.isEmpty() && !meter.isEmpty() && !debt.isEmpty() && !taxInput.isEmpty()) {

                CrudServiceFactory crudServiceFactory = CrudServiceFactory.CUSTOMER_SERVICE;
                crudServiceFactory.getCustomerService().emOpen();
                Customer customer = crudServiceFactory.getCustomerService().findByName(name);

                if (customer != null) {
                    infoMessage("Customer already Exists");

                } else {

                    customer = new Customer();
                    setCustomerValues(customer, taxInput);
                    crudServiceFactory = CrudServiceFactory.CUSTOMER_SERVICE;
                    crudServiceFactory.getCustomerService().create(customer);

                    setBillValues(customer);
                    infoMessage("Customer " + customer.getName() + " created");
            }
        } else {
            badInput();

        }
    }

    public void onCLickExitButton(ActionEvent e) {
        this.dispose();

    }


    public void infoMessage(String message){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 24));
        JOptionPane.showMessageDialog(this, message+" !",
                "Service", JOptionPane.WARNING_MESSAGE);
    }

    public String getDateReformated(int day, int month){
        String date = getDateTime();
        String year = date.substring(date.length() - 5, date.length() - 1);
        String monthFormated;
        String dateFormated;
        String dayFormated;
        if (month< 10) {
            monthFormated = "0" + month;
        } else {
            monthFormated = String.valueOf(month);
        }
        if(day< 10){
            dayFormated = "0" + day;
        }
        else{
            dayFormated = String.valueOf(day);
        }
        dateFormated= dayFormated+"."+monthFormated+"."+year;
        return dateFormated;
    }


    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JTextField[] fields = {nameValue, addressValue, cityValue, countryValue, emailValue,
                    phoneValue, contractNoValue, meterNoValue, debtBalanceValue, taxValue};
            for (JTextField field : fields) {
                if(e.getSource()==field) {
                    field.setBorder(new LineBorder(Color.gray));
                    field.setText("");
                }
            }
            if (e.getSource() == nameValue) {
                for (JTextField field : fields) {
                    field.setText("");
                }
                }
            }
    };

    public void addListeners(){
        JTextField[] fields = {nameValue, addressValue, cityValue, countryValue,
                emailValue, phoneValue, contractNoValue, meterNoValue, debtBalanceValue, taxValue};
        for (JTextField field : fields) {
            field.addMouseListener(mouseAdapter);
        }
        createButton.addActionListener(this::onClickCreateButton);
        exitButton.addActionListener(this::onCLickExitButton);

    }

    public void addFont(){
        JLabel[] labels= {  name, address, city, country, email, phone, contractNo, meterNo, debtBalance, tax};
        JTextField[] textFields= {nameValue, addressValue, cityValue, countryValue, emailValue, phoneValue, contractNoValue, meterNoValue, debtBalanceValue, taxValue};
        for(JLabel label : labels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        }
        for(JTextField textField : textFields){
            textField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        }
    }

    public void setCustomerValues(Customer customer,String taxx){
        customer.setName(nameValue.getText());
        customer.setAddress(addressValue.getText());
        customer.setCity(cityValue.getText());
        customer.setCountry(countryValue.getText());
        customer.setEmail(emailValue.getText());
        customer.setPhone(phoneValue.getText());
        customer.setContractNo(contractNoValue.getText());
        customer.setMeterNo(Integer.parseInt(meterNoValue.getText()));
        customer.setDebtBalance(BigDecimal.valueOf(Double.parseDouble(debtBalanceValue.getText())));
        CrudServiceFactory crudServiceFactory = CrudServiceFactory.TAX_SERVICE;
        Tax tax = crudServiceFactory.getTaxService().find(taxx);
        customer.setTax(tax);
    }

    public void setBillValues(Customer customer) {
        for (int month = 1; month <= months.length; month++) {
            Bill bill = new Bill();
            bill.setUnitsOffPeak(0);
            bill.setUnitsOnPeak(0);
            bill.setReadDate(getDateReformated(5, month));
            bill.setIssueDate(getDateReformated(15, month));
            bill.setDeadlineDate(getDateReformated(25, month));
            bill.setPaymentDate("--");
            bill.setMonth(months[month - 1]);
            bill.setOffPeakAmount(BigDecimal.valueOf(00.00));
            bill.setOnPeakAmount(BigDecimal.valueOf(00.00));
            bill.setAmount(BigDecimal.valueOf(00.00));
            bill.setInvoiceStatus(false);
            bill.setCustomer(customer);

            CrudServiceFactory crudServiceFactory = CrudServiceFactory.USER_SERVICE;
            User user = crudServiceFactory.getUserService().find(tempUser.getId());
            bill.setUser(user);

            crudServiceFactory = CrudServiceFactory.BILL_SERVICE;
            crudServiceFactory.getBillService().create(bill);
        }
    }



}