package Panels;

import org.hibernate.annotations.DialectOverride;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FindCustomerPanel extends JPanel {
    JLabel findCustomer, customerID,  customerName;
    JTextField customerIDValue, customerNameValue;
    JButton findButton;

    public FindCustomerPanel() {
    createPanel();
    createComponents();
    addComponents();
    addListeners();

    }

    public void createPanel(){
        setLayout(new GridLayout(3, 2, 0, 10));
        setSize(460, 120);
    }

    public void createComponents(){
        findCustomer = new JLabel("Find Customer", JLabel.CENTER);
        findCustomer.setFont(new Font("Times New Roman", Font.BOLD, 20));
        findCustomer.setSize(150, 20);

        findButton = new JButton("Find");
        findButton.setBackground(new Color(50, 205, 50));
        findButton.setFont(new Font("Times New Roman", Font.BOLD, 18));

        customerID = new JLabel("Customer Id: ", JLabel.CENTER);
        customerID.setFont(new Font("Times New Roman", Font.BOLD, 14));

        customerIDValue = new JTextField();
        customerIDValue.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        customerName = new JLabel("Customer name: ", JLabel.CENTER);
        customerName.setFont(new Font("Times New Roman", Font.BOLD, 14));

        customerNameValue = new JTextField();
        customerNameValue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    }

    public void addComponents(){
    add(findCustomer);
    add(findButton);
    add(customerID);
    add(customerIDValue);
    add(customerName);
    add(customerNameValue);

    }

    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == customerIDValue || e.getSource() == customerNameValue) {
                customerIDValue.setText("");
                customerNameValue.setText("");
            }
        }
    };

    public void addListeners(){
        customerIDValue.addMouseListener(mouseAdapter);
        customerNameValue.addMouseListener(mouseAdapter);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Find Customer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FindCustomerPanel panel = new FindCustomerPanel();
        frame.getContentPane().add(panel);
        frame.setSize(460,120);
        //frame.pack();
        frame.setVisible(true);

    }

}