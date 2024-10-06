package EBS.EbsGUI;

import service.Customer.Customer;
import service.ServiceFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class CustomerDetails extends JFrame  {
    JPanel  topPanel, centerPanel, bottomPanel, searchPanel;
    JLabel id, name, address, city, country, email, phone, contractNo, meterNo,
            debtBalance, titleLabel, idSearchLabel, nameSearchLabel;
    JTextField idSearchValue, nameSearchValue;
    JLabel idValue, nameValue, addressValue, cityValue, countryValue, emailValue,
            phoneValue, contractNoValue, meterNoValue, debtBalanceValue;
    JButton detailsButton, exitButton;

    public CustomerDetails() {
        setTitle("Find Customer ");
        createComponents();
        createBackPanels();
        createFrame();
        addComponents();
        addListeners();
    }

    public void createFrame(){
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

    public void createBackPanels(){
        topPanel = new JPanel();
        topPanel.setLocation(0, 0);
        topPanel.setSize(800, 70);
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new FlowLayout());

        searchPanel = new JPanel();
        searchPanel.setLocation(40, 100);
        searchPanel.setSize(700, 80);
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setLayout(new GridLayout(2, 2, 10, 10));

        centerPanel = new JPanel();
        centerPanel.setLocation(40, 200);
        centerPanel.setSize(700, 460);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new GridLayout(10, 2, 10, 2));

        bottomPanel = new JPanel();
        bottomPanel.setLocation(40, 700);
        bottomPanel.setSize(700, 50);
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setLayout(new GridLayout(1, 2, 10, 0));


    }

    public void createComponents(){
        titleLabel = new JLabel("Customer Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));

        idSearchLabel = new JLabel("Customer's Id:", JLabel.CENTER);
        idSearchValue = new JTextField();

        nameSearchLabel = new JLabel("Name:", JLabel.CENTER);
        nameSearchValue = new JTextField();

        id = new JLabel("Customer Id:");
        name = new JLabel("Name:");
        address = new JLabel("Address:");
        city = new JLabel("City:");
        country = new JLabel("Country:");
        email = new JLabel("Email:");
        phone = new JLabel("Phone:");
        contractNo = new JLabel("Contract No:");
        meterNo = new JLabel("Meter No");
        debtBalance = new JLabel("Debt Balance:");

        idValue = new JLabel();
        nameValue = new JLabel();
        addressValue = new JLabel();
        cityValue = new JLabel();
        countryValue = new JLabel();
        emailValue = new JLabel();
        phoneValue = new JLabel();
        contractNoValue = new JLabel();
        meterNoValue = new JLabel();
        debtBalanceValue = new JLabel();

        detailsButton = new JButton("Get Details");
        exitButton = new JButton("Exit");
        addFont();
        addBordersBackgroundAndHorizontalAlignmentForCenterPanel();
        addForegroundAndBackgroundForBottomPanel();

    }

    public void addComponents(){
        JLabel[] labels = { id, idValue, name, nameValue, address, addressValue,
                            city, cityValue, country, countryValue,
                            email, emailValue, phone, phoneValue, contractNo, contractNoValue,
                            meterNo, meterNoValue, debtBalance, debtBalanceValue};

        topPanel.add(titleLabel);
        searchPanel.add(idSearchLabel);
        searchPanel.add(idSearchValue);
        searchPanel.add(nameSearchLabel);
        searchPanel.add(nameSearchValue);

        for(JLabel label : labels){
            centerPanel.add(label);
        }

        bottomPanel.add(detailsButton);
        bottomPanel.add(exitButton);

        add(topPanel);
        add(searchPanel);
        add(centerPanel);
        add(bottomPanel);
    }

    public void addFont(){
        JTextField[] searchPanelTextFields= {idSearchValue, nameSearchValue};
        JLabel[] centerPanelLabels = { id, name, address, city, country, email, phone, contractNo, meterNo, debtBalance};
        JLabel[] centerPanelValueLabels= { idValue, nameValue, addressValue, cityValue, countryValue, emailValue, phoneValue,
                                            contractNoValue, meterNoValue, debtBalanceValue};
        JLabel[] topPanelLabels= {idSearchLabel, nameSearchLabel};
        JButton[] buttons= {detailsButton, exitButton};
        for(JLabel label : centerPanelLabels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        }
        for(JLabel label : centerPanelValueLabels){
            label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        }
        for(JLabel label : topPanelLabels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        }
        for(JButton button : buttons){
            button.setFont(new Font("Times New Roman", Font.BOLD, 24));
        }

        for(JTextField fields : searchPanelTextFields){
            fields.setFont(new Font("Times New Roman", Font.BOLD, 24));

        }
    }

    public void addBordersBackgroundAndHorizontalAlignmentForCenterPanel(){
        JLabel[] centerPanelLabels = { id, name, address, city, country, email, phone, contractNo, meterNo, debtBalance};
        JLabel[] centerPanelValueLabels= { idValue, nameValue, addressValue, cityValue, countryValue, emailValue, phoneValue,
                                contractNoValue, meterNoValue, debtBalanceValue};

        for(JLabel label: centerPanelLabels){
            label.setHorizontalAlignment(JLabel.LEFT);
        }
        for(JLabel label: centerPanelValueLabels){
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBorder(new LineBorder(Color.LIGHT_GRAY));
            label.setBackground(Color.WHITE);

        }

    }

    public void addForegroundAndBackgroundForBottomPanel(){
        JButton[] buttons= {detailsButton, exitButton};
        for(JButton button : buttons){
            button.setForeground(Color.BLACK);
            button.setBackground(new Color(50, 205, 50));
        }
    }

    public void addListeners(){
        idSearchValue.addMouseListener(mouseAdapter);
        nameSearchValue.addMouseListener(mouseAdapter);
        detailsButton.addActionListener(this::onClickDetailsButton);
        exitButton.addActionListener(this::onClickExitButton);
    }

    public void onClickDetailsButton(ActionEvent e) {
        String id = idSearchValue.getText();
        String name = nameSearchValue.getText();

        if (name.isEmpty() && !id.isEmpty()) {
            findByID(id);
        }
        else if(!name.isEmpty() && id.isEmpty()) {
            findByName(name);

        }
            else{
                infoMessage("Search fields are empty", "warning");
            }
    }

    public void onClickExitButton(ActionEvent e){
        this.dispose();
    }

    MouseAdapter mouseAdapter= new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            idSearchValue.setText("");
            idSearchValue.setBorder(new LineBorder(Color.gray));
            nameSearchValue.setText("");
            idSearchValue.setForeground(Color.BLACK);
            nameSearchValue.setBorder(new LineBorder(Color.gray));
            nameSearchValue.setForeground(Color.BLACK);
            idValue.setBorder(new LineBorder(Color.lightGray));
            idValue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            clearTextFields();
        }
    };

    public void findByID(String id){
        ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
        Customer customer = serviceFactory.getCustomerService().find(id);

        if (customer != null) {
            infoMessage("Customer "+customer.getName()+" found","info");
            setValues(customer);
        } else {
            infoMessage("Doesn't exists", "warning");
        }
    }

    public void findByName(String name){
        ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
        Customer customer = serviceFactory.getCustomerService().findByName(name);
        if (customer != null) {
            infoMessage("Customer "+customer.getName()+" found","info");
            setValues(customer);
        } else {
            infoMessage("Doesn't exists", "warning");
        }
    }

    public void setValues(Customer customer){
        idValue.setText(String.valueOf(customer.getId()));
        nameValue.setText(customer.getName());
        addressValue.setText(customer.getAddress());
        cityValue.setText(customer.getCity());
        countryValue.setText(customer.getCountry());
        emailValue.setText(customer.getPhone());
        phoneValue.setText(customer.getEmail());
        contractNoValue.setText(customer.getContractNo());
        meterNoValue.setText(String.valueOf(customer.getMeterNo()));
        debtBalanceValue.setText(String.valueOf(customer.getDebtBalance()));
    }

    public void clearTextFields(){
      JLabel[] fields={idValue, nameValue, addressValue, cityValue, countryValue, emailValue, phoneValue,
              contractNoValue, meterNoValue, debtBalanceValue};
      for(JLabel field:fields){
          field.setText("");
      }
    }

    public void infoMessage(String message, String type){
        int messageType;
        if(type.equals("warning")) {
            messageType = JOptionPane.WARNING_MESSAGE;
        }else{
            messageType = JOptionPane.INFORMATION_MESSAGE;
        }
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 24));
        JOptionPane.showMessageDialog(this, message+" !", "Service", messageType);
    }

    public static void main(String[] args) {
        new CustomerDetails();
    }
}








