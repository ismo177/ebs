package EBS.EbsGUI;

import service.Customer.Customer;
import service.ServiceFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

public class PayAmount extends JFrame  {
    JPanel centerPanel, bottomPanel;
    JLabel titleLabel, id, name, amount;
    JTextField idValue, nameValue, amountValue;
    JButton payButton, exitButton;

    public PayAmount() {
        super("Pay Amount");
        createComponents();
        createBackPanels();
        createFrame();
        addComponents();
        addListeners();
    }

    public void createFrame(){
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(null);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void createBackPanels(){
        centerPanel = new JPanel();
        centerPanel.setLocation(20, 100);
        centerPanel.setSize(550, 150);
        centerPanel.setLayout(new GridLayout(3, 2, 20, 30));
        centerPanel.setBackground(Color.WHITE);

        bottomPanel = new JPanel();
        bottomPanel.setLocation(20, 300);
        bottomPanel.setSize(550, 50);
        bottomPanel.setLayout(new GridLayout(1, 2, 20, 20));
        bottomPanel.setBackground(Color.WHITE);

    }

    public void createComponents(){
        titleLabel = new JLabel("Pay Amount", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setLocation(200, 20);
        titleLabel.setSize(200, 50);

        id = new JLabel("Customer Id:", JLabel.CENTER);
        id.setFont(new Font("Times New Roman", Font.BOLD, 20));
        id.setSize(200, 30);

        name = new JLabel("Name:", JLabel.CENTER);
        name.setFont(new Font("Times New Roman", Font.BOLD, 20));

        amount = new JLabel("Amount  (KM)", JLabel.CENTER);
        amount.setFont(new Font("Times New Roman", Font.BOLD, 20));

        idValue = new JTextField("", JTextField.RIGHT);

        nameValue = new JTextField("", JTextField.RIGHT);
        nameValue.setSize(200, 20);

        amountValue = new JTextField("00.00", JTextField.LEFT);
        amountValue.setSize(200, 20);

        payButton = new JButton("Pay");
        exitButton = new JButton("Exit");
        addFont();
    }

    public void addComponents(){
        centerPanel.add(id);
        centerPanel.add(idValue);
        centerPanel.add(name);
        centerPanel.add(nameValue);
        centerPanel.add(amount);
        centerPanel.add(amountValue);

        bottomPanel.add(payButton);
        bottomPanel.add(exitButton);
        add(titleLabel);
        add(centerPanel);
        add(bottomPanel);
    }

    public void addFont(){
        JLabel[] labels= { titleLabel, id, name, amount };
        JTextField[] tfValues = { idValue, nameValue, amountValue };
        JButton[] buttons= { payButton, exitButton };

        for(JLabel label : labels){
            label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        }
        for(JTextField tfValue : tfValues){
            tfValue.setFont(new Font("Arial", Font.BOLD, 24));
        }
        for(JButton button : buttons){
            button.setFont(new Font("Times New Roman", Font.BOLD, 24));
            button.setBackground(new Color(50, 205, 50));
        }
    }

    public void addListeners(){
        idValue.addMouseListener(mouseAdapter);
        nameValue.addMouseListener(mouseAdapter);
        payButton.addActionListener(this::onClickPay);
        exitButton.addActionListener(this::onClickExit);

    }

  public void onClickPay(ActionEvent e) {
      String id = idValue.getText();
      String name = nameValue.getText();
      BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(amountValue.getText()));
      updateBalance(id, name, amount);
  }

  public void onClickExit(ActionEvent e) {
        this.dispose();
  }


public void updateBalance(String id, String name, BigDecimal amount){
        if(!id.isEmpty() && name.isEmpty() && amount.compareTo(BigDecimal.ZERO) > 0) {
            findByIDAndUpdate(id,amount);
        }else if(id.isEmpty() && !name.isEmpty() && amount.compareTo(BigDecimal.ZERO) > 0) {
         findByNameAndUpdate(name,amount);
                  } else {
                  infoMessage("Customer doesn't exists", "warning");
             }
     }

public void findByIDAndUpdate(String id, BigDecimal amount){
    ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
    Customer customer= serviceFactory.getCustomerService().find(id);
    if(customer != null){
            BigDecimal before = customer.getDebtBalance();
            BigDecimal after = before.subtract(amount);
            customer.setDebtBalance(after);
            serviceFactory.getCustomerService().edit(customer);
            infoMessage("Succesuful","info");
    }else{
        infoMessage("Does not exist","warning");
    }
}

    public void findByNameAndUpdate(String name, BigDecimal amount){
        ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
        Customer customer= serviceFactory.getCustomerService().findByName(name);
        if(customer != null){
                BigDecimal before = customer.getDebtBalance();
                BigDecimal after = before.subtract(amount);
                customer.setDebtBalance(after);
                serviceFactory.getCustomerService().edit(customer);
                infoMessage("Succesuful","info");
        }else{
            infoMessage("Does not exist","warning");
        }
    }

MouseAdapter mouseAdapter=new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        idValue.setText("");
        idValue.setBorder(new LineBorder(Color.GRAY));
        nameValue.setText("");
        nameValue.setBorder(new LineBorder(Color.GRAY));
    }
};

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
        new PayAmount();
    }
}

