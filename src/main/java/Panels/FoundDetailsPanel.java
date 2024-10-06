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
        setSize(420, 300);
    }

    public void createComponents(){
        customerDetails = new JLabel("Customer details", JLabel.CENTER);
        customerDetails.setFont(new Font("Times New Roman", Font.BOLD, 20));
        emptySection1 = new JLabel();


        name = new JLabel(" Name: ");
        nameValue = new JLabel("");

        address = new JLabel(" Address: ");
        addressValue = new JLabel("");

        city = new JLabel(" City: ");
        cityValue = new JLabel("");

        country = new JLabel(" Country: ");
        countryValue = new JLabel("");

        email = new JLabel(" Email: ");
        emailValue = new JLabel("");

        phone = new JLabel(" Phone : ");
        phoneValue = new JLabel("");

        contractNo = new JLabel(" Contract No: ");
        contractNoValue = new JLabel("");

        meterNo = new JLabel(" Meter No: ");
        meterNoValue = new JLabel("");

        debtBalance = new JLabel(" Debt Balance: ");
        debtBalanceValue = new JLabel("");
    }

    public void addComponents(){
        JLabel[] labels= { name, nameValue, address, addressValue, city, cityValue, country, countryValue,
                            email, emailValue, phone, phoneValue, contractNo, contractNoValue, meterNo,
                            meterNoValue, debtBalance, debtBalanceValue};

        add(customerDetails);
        add(emptySection1);
        for(JLabel label: labels){
            add(label);
        }
        addFontAndHorizontalAlignment();
    }

    public void addFontAndHorizontalAlignment(){
        JLabel[] labels={name,  address,  city,  country,  email, phone, contractNo, meterNo, debtBalance};
        JLabel[] labelsValues= { nameValue, addressValue, cityValue, countryValue, emailValue,
                                phoneValue,  contractNoValue,  meterNoValue};
        customerDetails.setFont(new Font("Times New Roman", Font.BOLD, 20));
        debtBalance.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        debtBalanceValue.setFont(new Font("Times New Roman", Font.BOLD, 18));
        debtBalanceValue.setBorder(new LineBorder(Color.lightGray));
        for(JLabel label:labels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            label.setHorizontalAlignment(JLabel.LEFT);
        }
        for(JLabel label:labelsValues){
            label.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label.setBorder(new LineBorder(Color.lightGray));
            label.setHorizontalAlignment(JLabel.LEFT);
        }
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
