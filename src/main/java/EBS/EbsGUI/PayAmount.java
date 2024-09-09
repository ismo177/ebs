package EBS.EbsGUI;

import EBS.EntityServices.Customer.Customer;
import EBS.EntityServices.ServiceFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;

public class PayAmount extends JFrame implements MouseListener {


    JPanel mainPanel, topPanel, centerPanel, bottomPanel;
    JLabel titleLabel, customerIdLabel, customerNamelabel, amountLabel;
    JTextField customerIdTextField, customerNameTextField, amountTextField;
    JButton payButton, exitButton;


    public PayAmount() {

       super("Pay Amount");
        titleLabel = new JLabel("Pay Amount", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setLocation(200, 10);
        titleLabel.setSize(200, 30);

        topPanel = new JPanel();
        topPanel.setLocation(20, 20);
        topPanel.setSize(540, 60);
        topPanel.setLayout(null);
        topPanel.setBackground(Color.WHITE);
        topPanel.add(titleLabel);


        customerIdLabel = new JLabel("Customer's Id", JLabel.CENTER);
        customerIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        customerIdLabel.setSize(200, 30);

        customerNamelabel = new JLabel("Name", JLabel.CENTER);
        customerNamelabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        amountLabel = new JLabel("Amount  (KM)", JLabel.CENTER);
        amountLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));


        customerIdTextField = new JTextField("", JTextField.RIGHT);
        customerIdTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        customerIdTextField.addMouseListener(this);


        customerNameTextField = new JTextField("", JTextField.RIGHT);
        customerNameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        customerNameTextField.setSize(200, 20);
        customerNameTextField.addMouseListener(this);


        amountTextField = new JTextField("00.00", JTextField.LEFT);
        amountTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        amountTextField.setSize(200, 20);

        payButton = new JButton("Pay");
        payButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        payButton.setBackground(new Color(50, 205, 50));
        payButton.addActionListener(this::onClickPay);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        exitButton.setBackground(new Color(50, 205, 50));
        exitButton.addActionListener(this::onClickExit);



        centerPanel = new JPanel();
        centerPanel.setLocation(0, 120);
        centerPanel.setSize(540, 160);
        // centerPanel.setBorder(new LineBorder(Color.red));
        centerPanel.setLayout(new GridLayout(3, 2, 0, 20));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(customerIdLabel);
        centerPanel.add(customerIdTextField);
        centerPanel.add(customerNamelabel);
        centerPanel.add(customerNameTextField);
        centerPanel.add(amountLabel);
        centerPanel.add(amountTextField);


        bottomPanel = new JPanel();
        bottomPanel.setLocation(20, 320);
        bottomPanel.setSize(540, 60);
        bottomPanel.setLayout(new GridLayout(1, 2, 20, 20));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(payButton);
        bottomPanel.add(exitButton);


        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setLocation(0, 0);
        mainPanel.setSize(660, 460);
        mainPanel.setBackground(Color.WHITE);

        //mainPanel.add(l4);
        mainPanel.add(topPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(bottomPanel);


        add(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(660, 460);
        setLayout(null);
        //  setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }




  public void onClickPay(ActionEvent e) {
      String id = customerIdTextField.getText();
      String name = customerNameTextField.getText();

      BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(amountTextField.getText()));

      if (name.isEmpty() && id.isEmpty()) {
          customerIdTextField.setBorder(new LineBorder(Color.RED,3,true));
          customerIdTextField.setText("ID here");
          customerIdTextField.setForeground(Color.lightGray);
          customerNameTextField.setBorder(new LineBorder(Color.RED,3,true));
          customerNameTextField.setText("name here");
          customerNameTextField.setForeground(Color.lightGray);
          amountTextField.setText("00.00");

      } else if (!name.isEmpty() || !id.isEmpty() && amount.compareTo(BigDecimal.ZERO) > 0) {
          updateBalance(id, name, amount);


      }

  }

  public void onClickExit(ActionEvent e) {
        this.dispose();
  }


public void updateBalance(String id, String name, BigDecimal amount){
     ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
     if(!id.isEmpty() && name.isEmpty()) {

           Customer customer= serviceFactory.getCustomerService().find(id);
            if(customer != null){
                try {
                    BigDecimal before = customer.getDebtBalance();
                    BigDecimal after = before.subtract(amount);
                    customer.setDebtBalance(after);
                    serviceFactory.getCustomerService().edit(customer);
                    customerIdTextField.setText("Successful");
                    customerIdTextField.setBorder(new LineBorder(Color.GREEN,3,true));
                }catch(ClassCastException e ){
                    System.out.println("Error: "+e.getMessage());

                }

                  }else{
                  customerIdTextField.setBorder(new LineBorder(Color.RED,3,true));
                  customerIdTextField.setText("Does not exist");
                  customerNameTextField.setText("");
                  amountTextField.setText("");
            }
     }else if(id.isEmpty() && !name.isEmpty()) {
            Customer customer = serviceFactory.getCustomerService().findByName(name);
            if (customer != null) {
             customer.setDebtBalance(customer.getDebtBalance().add(amount));
                customerIdTextField.setText("Successful");
                customerIdTextField.setBorder(new LineBorder(Color.GREEN,3,true));
                  } else {
                  customerIdTextField.setText("");
                  customerNameTextField.setText("Doesn't exist");
                  customerNameTextField.setBorder(new LineBorder(Color.RED,3,true));
                  amountTextField.setText("");
             }
     }

}
//----------------------------------------------------------------------------



    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == customerIdTextField || e.getSource() == customerNameTextField){
            customerIdTextField.setText("");
            customerIdTextField.setBorder(new LineBorder(Color.lightGray));
            customerIdTextField.setForeground(Color.BLACK);
            customerNameTextField.setText("");
            customerNameTextField.setBorder(new LineBorder(Color.lightGray));
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
        new PayAmount();
    }


}

