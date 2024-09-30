package Panels;


import service.Customer.Customer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FoundDetailsPanel extends JPanel {
    JLabel customerDetails, emptySection1;
    JLabel name, nameValue, address, addressValue, city, cityValue, country, countryValue, email, emailValue;
    JLabel phone, phoneValue, contractNo, contractNoValue, meterNo, meterNoValue, debtBalance, debtBalanceValue;
    public FoundDetailsPanel() {
        cretePanel();
        createComponents();
        addComponents();

    }

    public void cretePanel(){
        setLayout(new GridLayout(10, 2));
        setSize(460, 300);
    }

    public void createComponents(){
        customerDetails = new JLabel("Customer details", JLabel.CENTER);
        customerDetails.setFont(new Font("Times New Roman", Font.BOLD, 20));
        emptySection1 = new JLabel();


        name = new JLabel(" Name: ", JLabel.LEFT);
        name.setFont(new Font("Times New Roman", Font.PLAIN, 16));


        nameValue = new JLabel("", JLabel.LEFT);
        nameValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        nameValue.setBorder(new LineBorder(Color.lightGray));


        address = new JLabel(" Address: ", JLabel.LEFT);
        address.setFont(new Font("Times New Roman", Font.PLAIN, 16));


        addressValue = new JLabel("", JLabel.LEFT);
        addressValue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        addressValue.setBorder(new LineBorder(Color.lightGray));

        city = new JLabel(" City: ", JLabel.LEFT);
        city.setFont(new Font("Times New Roman", Font.PLAIN, 16));


        cityValue = new JLabel("", JLabel.LEFT);
        cityValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        cityValue.setBorder(new LineBorder(Color.lightGray));


        country = new JLabel(" Country: ", JLabel.LEFT);
        country.setFont(new Font("Times New Roman", Font.PLAIN, 16));


        countryValue = new JLabel("", JLabel.LEFT);
        countryValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        countryValue.setBorder(new LineBorder(Color.lightGray));

        email = new JLabel(" Email: ", JLabel.LEFT);
        email.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        emailValue = new JLabel("", JLabel.LEFT);
        emailValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        emailValue.setBorder(new LineBorder(Color.lightGray));


        phone = new JLabel(" Phone : ", JLabel.LEFT);
        phone.setFont(new Font("Times New Roman", Font.PLAIN, 16));


        phoneValue = new JLabel("", JLabel.LEFT);
        phoneValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        phoneValue.setBorder(new LineBorder(Color.lightGray));


        contractNo = new JLabel(" Contract No: ", JLabel.LEFT);
        contractNo.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        contractNoValue = new JLabel("", JLabel.LEFT);
        contractNoValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        contractNoValue.setBorder(new LineBorder(Color.lightGray));


        meterNo = new JLabel(" Meter No: ", JLabel.LEFT);
        meterNo.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        meterNoValue = new JLabel("", JLabel.CENTER);
        meterNoValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        meterNoValue.setBorder(new LineBorder(Color.lightGray));

        debtBalance = new JLabel(" Debt Balance: ", JLabel.LEFT);
        debtBalance.setFont(new Font("Times New Roman", Font.BOLD, 18));

        debtBalanceValue = new JLabel("", JLabel.LEFT);
        debtBalanceValue.setFont(new Font("Times New Roman", Font.BOLD, 20));
        debtBalanceValue.setBorder(new LineBorder(Color.lightGray));

    }

    public void addComponents(){
        add(customerDetails);
        add(emptySection1);
        add(name);
        add(nameValue);
        add(address);
        add(addressValue);
        add(city);
        add(cityValue);
        add(country);
        add(countryValue);
        add(email);
        add(emailValue);
        add(phone);
        add(phoneValue);
        add(meterNo);
        add(meterNoValue);
        add(contractNo);
       add(contractNoValue);
       add(debtBalance);
       add(debtBalanceValue);

    }

    public void setFields(Customer customer){
       nameValue.setText(customer.getName());
       addressValue.setText(customer.getAddress());
       cityValue.setText(customer.getCity());
       countryValue.setText(customer.getCountry());
       emailValue.setText(customer.getEmail());
       phoneValue.setText(customer.getPhone());
       meterNoValue.setText(String.valueOf(customer.getMeterNo()));
       contractNoValue.setText(customer.getContractNo());
       debtBalanceValue.setText(String.valueOf(customer.getDebtBalance()));


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FoundDetailsPanel());
        frame.setSize(460,300);
        //frame.pack();
        frame.setVisible(true);

    }
}
