package EBS.EbsGUI;

import EBS.EntityServices.Customer.Customer;
import EBS.EntityServices.ServiceFactory;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeleteCustomer extends JFrame implements  MouseListener {
    JPanel centerPanel, bottomPanel;
    JLabel titleLabel, customerNameLabel, customerIdLabel, emptyLabel, l5;
    JTextField customerIdTextField, customerNameTextField;
    JButton deleteButton, exitButton, b3;


    public DeleteCustomer() {

        titleLabel = new JLabel("Delete Customer");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        titleLabel.setSize(600, 60);
        titleLabel.setLocation(220, 0);


        customerNameLabel = new JLabel("Customer Name:", JLabel.CENTER);
        customerNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        customerIdLabel = new JLabel("Customer Id:", JLabel.CENTER);
        customerIdLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        emptyLabel = new JLabel("", JLabel.CENTER);
        emptyLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        emptyLabel.setLocation(0, 250);
        emptyLabel.setSize(600, 40);


        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2, 20, 40));
        centerPanel.setSize(580, 140);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLocation(0, 80);
        // p1.setBorder(new LineBorder(Color.RED));

        customerIdTextField = new JTextField();
        customerIdTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        customerIdTextField.addMouseListener(this);

        customerNameTextField = new JTextField();
        customerNameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        customerNameTextField.addMouseListener(this);

        centerPanel.add(customerNameLabel);
        centerPanel.add(customerIdTextField);
        centerPanel.add(customerIdLabel);
        centerPanel.add(customerNameTextField);
        // p1.add(l4);
        //p1.add(l5);


        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        deleteButton.setBackground(new Color(50, 205, 50));
        deleteButton.addActionListener(this::onClickdeleteCustomer);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exitButton.setBackground(new Color(50, 205, 50));
        exitButton.addActionListener(this::onClickExitButton);


        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, 20, 0));
        bottomPanel.setSize(560, 50);
        bottomPanel.setLocation(20, 290);

        bottomPanel.add(deleteButton);
        bottomPanel.add(exitButton);

        add(titleLabel);
        add(centerPanel);
        add(emptyLabel);
        add(bottomPanel);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Delete Customer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }


    //---------------------------------------------------------------------------
    private void onClickdeleteCustomer(ActionEvent e) {
        String cust = customerIdTextField.getText();
        String id = customerNameTextField.getText();

        if (cust.isEmpty() && !id.isEmpty()) {
            ServiceFactory customerFactory = ServiceFactory.CUSTOMER_SERVICE;
            Customer customer = customerFactory.getCustomerService().find(id);

            if (customer != null) {
              ServiceFactory billFactory=ServiceFactory.BILL_SERVICE;
              billFactory.getBillService().deleteBills(customer);

                customerFactory.getCustomerService().delete(customer);

                Customer customerAfter=customerFactory.getCustomerService().findByName(cust);

                if (customerAfter != null) {
                    customerIdTextField.setText("Delete failed !");
                    customerIdTextField.setBorder(new LineBorder(Color.RED, 3, true));
                }else{
                    customerIdTextField.setText("Successfully deleted !");
                    customerIdTextField.setBorder(new LineBorder(Color.GREEN, 3, true));
                }

            }else{
                customerIdTextField.setText("Doesn't exist");
                customerIdTextField.setBorder(new LineBorder(Color.RED, 3, true));
                customerNameTextField.setText("");
            }


        } else if (!cust.isEmpty() && id.isEmpty()) {
            ServiceFactory customerFactory = ServiceFactory.CUSTOMER_SERVICE;
            Customer customer = customerFactory.getCustomerService().findByName(cust);

            if (customer != null) {
                ServiceFactory billFactory=ServiceFactory.BILL_SERVICE;
                billFactory.getBillService().deleteBills(customer);

                customerFactory.getCustomerService().delete(customer);

                Customer customerAfter=customerFactory.getCustomerService().findByName(cust);

                if (customerAfter != null) {
                    customerIdTextField.setText("Delete failed !");
                    customerIdTextField.setBorder(new LineBorder(Color.RED, 3, true));
                }else{
                    customerIdTextField.setText("Successfully deleted !");
                    customerIdTextField.setBorder(new LineBorder(Color.GREEN, 3, true));
                }

            }else{
                customerIdTextField.setText("Doesn't exist");
                customerIdTextField.setBorder(new LineBorder(Color.RED, 3, true));
                customerNameTextField.setText("");
            }

        }

        else if (cust.isEmpty() || id.isEmpty()) {
            customerIdTextField.setText("Customer name");
            customerIdTextField.setForeground(Color.lightGray);
            customerIdTextField.setBorder(new LineBorder(Color.RED, 3, true));

            customerNameTextField.setText("Id here");
            customerNameTextField.setBorder(new LineBorder(Color.RED, 3, true));
            customerNameTextField.setForeground(Color.lightGray);

        }

        }

        public void onClickExitButton(ActionEvent e) {
        this.dispose();
        }

    //------------------------------------------------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()== customerIdTextField || e.getSource()== customerNameTextField){
            customerIdTextField.setText("");
            customerIdTextField.setBorder(new LineBorder(Color.GRAY));
            customerIdTextField.setForeground(Color.BLACK);
            customerNameTextField.setText("");
            customerNameTextField.setBorder(new LineBorder(Color.GRAY));
            customerNameTextField.setForeground(Color.BLACK);
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

    public static void main(String[] args) {
        new DeleteCustomer();
    }


}
