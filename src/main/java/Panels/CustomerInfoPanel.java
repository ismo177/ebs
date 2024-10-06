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
        nameValue = new JLabel("--");

        address = new JLabel(" Address:", JLabel.LEFT);
        addressValue = new JLabel("--");

        city = new JLabel(" City:", JLabel.LEFT);
        cityValue = new JLabel("--");

        country = new JLabel(" Country:", JLabel.LEFT);
        countryValue = new JLabel("--");

        email = new JLabel(" Email:", JLabel.LEFT);
        emailValue = new JLabel("--");

        phone = new JLabel(" Phone:", JLabel.LEFT);
        phoneValue = new JLabel("--");

        meterNo = new JLabel(" Meter No:", JLabel.LEFT);
        meterNoValue = new JLabel("--");

        taxType = new JLabel(" Tax type:", JLabel.LEFT);
        taxTypeValue = new JLabel("--");

        readDate = new JLabel(" Read Date:", JLabel.LEFT);
        readDateValue = new JLabel("--");

        issueDate = new JLabel(" Issue Date:", JLabel.LEFT);
        issueDateValue = new JLabel("--");

        deadLineDate = new JLabel(" Dedline Date:", JLabel.LEFT);
        deadLineDateValue = new JLabel("--");

        paymentDate = new JLabel(" Payment Date:", JLabel.LEFT);
        paymentDateValue = new JLabel("--");
        addFont();

    }

    public void addComponents(){
        JLabel[] labels= {name, nameValue, address, addressValue, city, cityValue, country, countryValue,
        email, emailValue, phone, phoneValue, meterNo, meterNoValue, taxType, taxTypeValue, emptySection2, emptySection3,
        readDate, readDateValue, issueDate, issueDateValue, deadLineDate, deadLineDateValue, paymentDate, paymentDateValue};
        add(title);
        add(emptySection);
        for(JLabel label: labels){
            add(label);
        }

    }

    public void addFont(){
        JLabel[] labels={ name, address,city, country, email, phone, meterNo, taxType};
        JLabel[] labelsValues={nameValue, addressValue, cityValue, countryValue, emailValue, phoneValue, meterNoValue, taxTypeValue};
        JLabel[] dates={ readDate, issueDate, deadLineDate, paymentDate};
        JLabel[] dateValues={readDateValue, issueDateValue, deadLineDateValue, paymentDateValue};

        for(JLabel label:labels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        }
        for(JLabel label:labelsValues){
            label.setFont(new Font("Times New Roman", Font.BOLD, 14));
            label.setBorder(new LineBorder(Color.lightGray));
        }
        for(JLabel label:dates){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        }
        for(JLabel label:dateValues){
            label.setFont(new Font("Times New Roman", Font.BOLD, 14));
            label.setBorder(new LineBorder(Color.lightGray));
        }
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
