package EBS.EbsGUI;

import service.Customer.Customer;
import service.ServiceFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements  MouseListener {

    JPanel mainPanel, topPanel, centerPanel, bottomPanel, findPanel;
    JLabel customerIdLabel, customerNameLabel, customerAddressLabel, customerCityLabel, customerCountryLabel, customerEmailLabel, customerPhoneLabel, customerContractNoLabel, customerMeterNoLAbel, customerDebtBalanceLabel, titleLabel, customerIdFindLabel, customerNameFindLabel;
    JTextField customerIdFindTextField, customerNameFindTextFIeld;
    JLabel customerIdValueLabel, customerNameValueLabel, customerAddressValueLabel, customerCityValueLabel, customerCountryValueLabel, customerEmailValueLabel, customerPhoneValueLabel, customerContractNoValueLabel, customerMeterNoValueLabel, customerDebtBalanceValueLabel;
    JButton getDetailsdButton, exitButton;

    public CustomerDetails() {


        setTitle("Find Customer ");
        mainPanel = new JPanel();


        topPanel = new JPanel();
        topPanel.setLocation(0, 100);
        topPanel.setSize(960, 70);
        topPanel.setLayout(new FlowLayout());

        centerPanel = new JPanel();
        centerPanel.setLocation(0, 180);
        centerPanel.setSize(960, 460);
        centerPanel.setLayout(new GridLayout(10, 2, 10, 2));

        bottomPanel = new JPanel();
        bottomPanel.setLocation(0, 700);
        bottomPanel.setSize(960, 50);
        bottomPanel.setLayout(new GridLayout(1, 2, 10, 0));

        mainPanel.setLayout(null);
        mainPanel.setBorder(new Gui_Bill.RoundedBorder(10));
        mainPanel.setSize(1000, 900);
        mainPanel.setLocation(0, 0);

        customerIdLabel = new JLabel("Customer's Id:", JLabel.CENTER);
        customerIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerNameLabel = new JLabel("Name:", JLabel.CENTER);
        customerNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerAddressLabel = new JLabel("Address:", JLabel.CENTER);
        customerAddressLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerCityLabel = new JLabel("City:", JLabel.CENTER);
        customerCityLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerCountryLabel = new JLabel("Country:", JLabel.CENTER);
        customerCountryLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerEmailLabel = new JLabel("Email:", JLabel.CENTER);
        customerEmailLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerPhoneLabel = new JLabel("Phone:", JLabel.CENTER);
        customerPhoneLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerContractNoLabel = new JLabel("Contract No:", JLabel.CENTER);
        customerContractNoLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerMeterNoLAbel = new JLabel("Meter No", JLabel.CENTER);
        customerMeterNoLAbel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerDebtBalanceLabel = new JLabel("Debt Balance:", JLabel.CENTER);
        customerDebtBalanceLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
//------------------------------------------------------------------------------------------------------

        titleLabel = new JLabel("Customer Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));

        customerIdFindLabel = new JLabel("Customer's Id:", JLabel.CENTER);
        customerIdFindLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerNameFindLabel = new JLabel("Name:", JLabel.CENTER);
        customerNameFindLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        customerIdFindTextField = new JTextField();
        customerIdFindTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        customerIdFindTextField.setSize(100, 20);
        customerIdFindTextField.addMouseListener(this);


        customerNameFindTextFIeld = new JTextField();
        customerNameFindTextFIeld.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        customerNameFindTextFIeld.addMouseListener(this);

        customerIdValueLabel = new JLabel("", JLabel.CENTER);
        customerIdValueLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        customerIdValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        customerIdValueLabel.setBackground(Color.WHITE);

        customerNameValueLabel = new JLabel("", JLabel.CENTER);
        customerNameValueLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        customerNameValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        customerNameValueLabel.setBackground(Color.WHITE);

        customerAddressValueLabel = new JLabel("", JLabel.CENTER);
        customerAddressValueLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        customerAddressValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        customerAddressValueLabel.setBackground(Color.WHITE);

        customerCityValueLabel = new JLabel("", JLabel.CENTER);
        customerCityValueLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        customerCityValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        customerCityValueLabel.setBackground(Color.WHITE);

        customerCountryValueLabel = new JLabel("", JLabel.CENTER);
        customerCountryValueLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        customerCountryValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        customerCountryValueLabel.setBackground(Color.WHITE);

        customerEmailValueLabel = new JLabel("", JLabel.CENTER);
        customerEmailValueLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        customerEmailValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        customerEmailValueLabel.setBackground(Color.WHITE);

        customerPhoneValueLabel = new JLabel("", JLabel.CENTER);
        customerPhoneValueLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        customerPhoneValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        customerPhoneValueLabel.setBackground(Color.WHITE);

        customerContractNoValueLabel = new JLabel("", JLabel.CENTER);
        customerContractNoValueLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        customerContractNoValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        customerContractNoValueLabel.setBackground(Color.WHITE);

        customerMeterNoValueLabel = new JLabel("", JLabel.CENTER);
        customerMeterNoValueLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        customerMeterNoValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        customerMeterNoValueLabel.setBackground(Color.WHITE);

        customerDebtBalanceValueLabel = new JLabel("", JLabel.CENTER);
        customerDebtBalanceValueLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        customerDebtBalanceValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        customerDebtBalanceValueLabel.setBackground(Color.WHITE);


        getDetailsdButton = new JButton("Get Details");
        getDetailsdButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        getDetailsdButton.setBackground(new Color(50, 205, 50));
        getDetailsdButton.setForeground(Color.BLACK);
        getDetailsdButton.addActionListener(this::onClickGetDetailsButton);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        exitButton.setBackground(new Color(50, 205, 50));
        exitButton.setForeground(Color.BLACK);
        exitButton.addActionListener(this::onClickExitButton);


        findPanel = new JPanel();
        findPanel.setLocation(0, 10);
        findPanel.setSize(960, 80);
        findPanel.setLayout(new GridLayout(2, 2, 10, 10));
        findPanel.add(customerIdFindLabel);
        findPanel.add(customerIdFindTextField);
        findPanel.add(customerNameFindLabel);
        findPanel.add(customerNameFindTextFIeld);

        topPanel.add(titleLabel);

        centerPanel.add(customerIdLabel);
        centerPanel.add(customerIdValueLabel);
        centerPanel.add(customerNameLabel);
        centerPanel.add(customerNameValueLabel);
        centerPanel.add(customerAddressLabel);
        centerPanel.add(customerAddressValueLabel);
        centerPanel.add(customerCityLabel);
        centerPanel.add(customerCityValueLabel);
        centerPanel.add(customerCountryLabel);
        centerPanel.add(customerCountryValueLabel);
        centerPanel.add(customerEmailLabel);
        centerPanel.add(customerEmailValueLabel);
        centerPanel.add(customerPhoneLabel);
        centerPanel.add(customerPhoneValueLabel);
        centerPanel.add(customerContractNoLabel);
        centerPanel.add(customerContractNoValueLabel);
        centerPanel.add(customerMeterNoLAbel);
        centerPanel.add(customerMeterNoValueLabel);
        centerPanel.add(customerDebtBalanceLabel);
        centerPanel.add(customerDebtBalanceValueLabel);

        bottomPanel.add(getDetailsdButton);
        bottomPanel.add(exitButton);

        mainPanel.add(findPanel);
        mainPanel.add(topPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(bottomPanel);

        add(mainPanel);

        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocation(200, 20);
        setLayout(null);
        setVisible(true);


    }


    public void onClickGetDetailsButton(ActionEvent e) {
        String id = customerIdFindTextField.getText();
        String name = customerNameFindTextFIeld.getText();

        ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
        serviceFactory.getCustomerService().emOpen();
        if ((name.isEmpty() && !id.isEmpty()) || (!name.isEmpty() && !id.isEmpty())) {
            //serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
            Customer customer = serviceFactory.getCustomerService().find(id);

            if (customer != null) {
                customerIdFindTextField.setBorder(new LineBorder(Color.GREEN, 3, true));
                setValues(customer);

            } else {
                customerIdValueLabel.setBorder(new LineBorder(Color.RED, 3, true));
                customerIdValueLabel.setText("Doesn't Exists");
                customerIdValueLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
            }
        } else if (!name.isEmpty() && id.isEmpty()) {
            //serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
            Customer customer = serviceFactory.getCustomerService().findByName(name);

            if (customer != null) {
                customerNameFindTextFIeld.setBorder(new LineBorder(Color.GREEN, 3, true));
                setValues(customer);


            } else {
                customerIdValueLabel.setBorder(new LineBorder(Color.RED, 3, true));
                customerIdValueLabel.setText("Doesn't Exist");
                customerIdValueLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
            }
        }


        if (name.isBlank() && id.isBlank()) {
            customerIdFindTextField.setText("Customers Id here");
            customerIdFindTextField.setForeground(Color.lightGray);
            customerIdFindTextField.setBorder(new LineBorder(Color.RED, 3, true));
            customerIdFindTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            customerNameFindTextFIeld.setText("Customers name here");
            customerNameFindTextFIeld.setForeground(Color.lightGray);
            customerNameFindTextFIeld.setBorder(new LineBorder(Color.RED, 3, true));
            customerNameFindTextFIeld.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        }

    }

    public void onClickExitButton(ActionEvent e){
        this.dispose();
    }

//------------------------------------------------------------------------------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {
        customerIdFindTextField.setText("");
        customerIdFindTextField.setBorder(new LineBorder(Color.gray));
        customerNameFindTextFIeld.setText("");
        customerIdFindTextField.setForeground(Color.BLACK);
        customerNameFindTextFIeld.setBorder(new LineBorder(Color.gray));
        customerNameFindTextFIeld.setForeground(Color.BLACK);
        customerIdValueLabel.setBorder(new LineBorder(Color.lightGray));
        customerIdValueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        setBlankFields();
       //FactoryService factoryService = FactoryService.CUSTOMER_SERVICE;
        //factoryService.getCustomerService().emOpen();

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
    //-------------------------------------------------------------
    public void setValues(Customer customer){
        customerIdValueLabel.setText(String.valueOf(customer.getId()));
        customerNameValueLabel.setText(customer.getName());
        customerAddressValueLabel.setText(customer.getAddress());
        customerCityValueLabel.setText(customer.getCity());
        customerCountryValueLabel.setText(customer.getCountry());
        customerEmailValueLabel.setText(customer.getPhone());
        customerPhoneValueLabel.setText(customer.getEmail());
        customerContractNoValueLabel.setText(customer.getContractNo());
        customerMeterNoValueLabel.setText(String.valueOf(customer.getMeterNo()));
        customerDebtBalanceValueLabel.setText(String.valueOf(customer.getDebtBalance()));
    }

    public void setBlankFields(){
      JLabel[] fields={customerIdValueLabel, customerNameValueLabel, customerAddressValueLabel, customerCityValueLabel, customerCountryValueLabel, customerEmailValueLabel, customerPhoneValueLabel, customerContractNoValueLabel, customerMeterNoValueLabel, customerDebtBalanceValueLabel};
      for(JLabel field:fields){
          field.setText("");
      }



    }


}








