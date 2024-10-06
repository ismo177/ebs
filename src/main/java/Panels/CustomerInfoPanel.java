package Panels;


import service.bill.Bill;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CustomerInfoPanel extends JPanel {
    JPanel title, emptySection;
    JLabel customerInfo, emptySection1, emptySection2, emptySection3;
    JLabel name, nameValue, address, addressValue,city, cityValue, country, countryValue, email, emailValue, phone,
            phoneValue, meterNo, meterNoValue, taxType, taxTypeValue;
    JLabel readDate, readDateValue, issueDate, issueDateValue, deadLineDate, deadLineDateValue, paymentDate, paymentDateValue;
    public CustomerInfoPanel() {
        createPanel();
        createComponents();
        addComponents();
    }

    public void createPanel(){
        setSize(500, 290);
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(new GridLayout(14, 2, 0, 0));
        setBackground(Color.WHITE);
    }

    public void createComponents(){
        customerInfo = new JLabel("  CUSTOMER INFO", JLabel.LEFT);
        customerInfo.setFont(new Font("Times New Roman", Font.BOLD, 16));
        title = new JPanel();
        title.setLayout(new FlowLayout(FlowLayout.LEFT));
        title.add(customerInfo);

        emptySection = new JPanel();

        emptySection2 = new JLabel(" ");
        emptySection3= new JLabel();


        name = new JLabel(" Name:", JLabel.LEFT);
        name.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        nameValue = new JLabel("--");
        nameValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        nameValue.setBorder(new LineBorder(Color.lightGray));

        address = new JLabel(" Address:", JLabel.LEFT);
        address.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        addressValue = new JLabel("--");
        addressValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        addressValue.setBorder(new LineBorder(Color.lightGray));

        city = new JLabel(" City:", JLabel.LEFT);
        city.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        cityValue = new JLabel("--");
        cityValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        cityValue.setBorder(new LineBorder(Color.lightGray));

        country = new JLabel(" Country:", JLabel.LEFT);
        country.setFont(new Font("Times New Roman", Font.PLAIN, 14));


        countryValue = new JLabel("--");
        countryValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        countryValue.setBorder(new LineBorder(Color.lightGray));


        email = new JLabel(" Email:", JLabel.LEFT);
        email.setFont(new Font("Times New Roman", Font.PLAIN, 14));


        emailValue = new JLabel("--");
        emailValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        emailValue.setBorder(new LineBorder(Color.lightGray));

        phone = new JLabel(" Phone:", JLabel.LEFT);
        phone.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        phoneValue = new JLabel("--");
        phoneValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        phoneValue.setBorder(new LineBorder(Color.lightGray));

        meterNo = new JLabel(" Meter No:", JLabel.LEFT);
        meterNo.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        meterNoValue = new JLabel("--");
        meterNoValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        meterNoValue.setBorder(new LineBorder(Color.lightGray));


        taxType = new JLabel(" Tax type:", JLabel.LEFT);
        taxType.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        taxTypeValue = new JLabel("--");
        taxTypeValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        taxTypeValue.setBorder(new LineBorder(Color.lightGray));

        readDate = new JLabel(" Read Date:", JLabel.LEFT);
        readDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        readDateValue = new JLabel("--");
        readDateValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        readDateValue.setBorder(new LineBorder(Color.lightGray));

        issueDate = new JLabel(" Issue Date:", JLabel.LEFT);
        issueDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        issueDateValue = new JLabel("--");
        issueDateValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        issueDateValue.setBorder(new LineBorder(Color.lightGray));

        deadLineDate = new JLabel(" Dedline Date:", JLabel.LEFT);
        deadLineDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        deadLineDateValue = new JLabel("--");
        deadLineDateValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        deadLineDateValue.setBorder(new LineBorder(Color.lightGray));

        paymentDate = new JLabel(" Payment Date:", JLabel.LEFT);
        paymentDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        paymentDateValue = new JLabel("--");
        paymentDateValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        paymentDateValue.setBorder(new LineBorder(Color.lightGray));

    }

    public void addComponents(){
        add(title);
        add(emptySection);
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
        add(taxType);
        add(taxTypeValue);
        add(emptySection2);
        add(emptySection3);

        add(readDate);
        add(readDateValue);
        add(issueDate);
        add(issueDateValue);
        add(deadLineDate);
        add(deadLineDateValue);
        add(paymentDate);
        add(paymentDateValue);

    }

    public void setValues(Bill bill){
        nameValue.setText(bill.getCustomer().getName());
        addressValue.setText(bill.getCustomer().getAddress());
        cityValue.setText(bill.getCustomer().getAddress());
        countryValue.setText(bill.getCustomer().getCountry());
        emailValue.setText(bill.getCustomer().getEmail());
        phoneValue.setText(bill.getCustomer().getPhone());
        meterNoValue.setText(String.valueOf(bill.getCustomer().getMeterNo()));
        taxTypeValue.setText(bill.getCustomer().getTax().getType());
        readDateValue.setText(bill.getReadDate());
        issueDateValue.setText(bill.getIssueDate());
        deadLineDateValue.setText(bill.getDeadlineDate());
        paymentDateValue.setText(bill.getPaymentDate());
    }

    public static void main(String[] args) {
        JFrame frame= new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CustomerInfoPanel());
        frame.setSize(500, 270);
        frame.setVisible(true);

    }
}
