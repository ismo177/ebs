package EBS.EbsGUI;

import service.Customer.Customer;
import service.CrudServiceFactory;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DeleteCustomer extends JFrame  {
    JPanel centerPanel, bottomPanel;
    JLabel titleLabel;
    JLabel id,name;
    JTextField idValue, nameValue;
    JButton deleteButton, exitButton;

    public DeleteCustomer() {
        createComponents();
        createFrame();
        addComponents();
        addListeners();
    }

    public void createFrame(){
        setTitle("Delete Customer");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

    public void createBackPanels(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2, 20, 40));
        centerPanel.setSize(580, 140);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLocation(0, 80);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, 20, 0));
        bottomPanel.setSize(560, 50);
        bottomPanel.setLocation(20, 290);
    }

    public void createComponents() {
        createBackPanels();
        titleLabel=new JLabel("Delete Customer");
        titleLabel.setSize(600, 60);
        titleLabel.setLocation(220, 0);

        id=new JLabel("ID:");
        name=new JLabel("Name:");
        idValue=new JTextField();
        nameValue=new JTextField();
        deleteButton=new JButton("Delete");
        exitButton=new JButton("Exit");
        addFontAndHorizontalAlignment();
    }

    public void addComponents(){
        centerPanel.add(id);
        centerPanel.add(idValue);
        centerPanel.add(name);
        centerPanel.add(nameValue);

        bottomPanel.add(deleteButton);
        bottomPanel.add(exitButton);
        add(titleLabel);
        add(centerPanel);
        add(bottomPanel);
    }

        public void addFontAndHorizontalAlignment(){
            JLabel[] labels= {id,name};
            JTextField[] tfValues = {idValue, nameValue};
            JButton[] buttons = {deleteButton, exitButton};

            titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
            for(JLabel label : labels){
                label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                label.setHorizontalAlignment(JLabel.CENTER);
            }
            for(JTextField tf : tfValues){
                tf.setFont(new Font("Arial", Font.BOLD, 24));
            }
            for(JButton button : buttons){
                button.setFont(new Font("Times New Roman", Font.BOLD, 24));
                button.setBackground(new Color(50, 205, 50));
            }
        }

    private void onClickDeleteCustomer(ActionEvent e) {
        String id = idValue.getText();
        String name = nameValue.getText();

        if (!id.isEmpty() && name.isEmpty()) {
            findByID(id);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        }
        else if (id.isEmpty() && !name.isEmpty()) {
            findByName(name);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        }
        else  {
          infoMessage("Fields are empty", "warning");
        }
    }

    public void findByID(String id){
            CrudServiceFactory customerFactory = CrudServiceFactory.CUSTOMER_SERVICE;
            Customer customer = customerFactory.getCustomerService().find(id);

            if (customer != null) {
                CrudServiceFactory billFactory= CrudServiceFactory.BILL_SERVICE;
                billFactory.getBillService().deleteBills(customer);
                customerFactory.getCustomerService().delete(customer);
                Customer customerAfter=customerFactory.getCustomerService().findByName(id);
                    if (customerAfter != null) {
                        infoMessage("Delete failed", "warning");
                    }else{
                        infoMessage("Delete successful", "info");
                    }
            }else{
              infoMessage("Doesn't exists", "warning");
            }

    }

    public void findByName(String name){
            CrudServiceFactory customerFactory = CrudServiceFactory.CUSTOMER_SERVICE;
            Customer customer = customerFactory.getCustomerService().findByName(name);

            if (customer != null) {
                CrudServiceFactory billFactory= CrudServiceFactory.BILL_SERVICE;
                billFactory.getBillService().deleteBills(customer);
                customerFactory.getCustomerService().delete(customer);
                Customer customerAfter=customerFactory.getCustomerService().findByName(name);
                if (customerAfter != null) {
                    infoMessage("Delete failed", "warning");
                }else{
                    infoMessage("Delete successful", "info");
                }
            }else{
                infoMessage("Doesn't exists", "warning");
            }
    }

    public void addListeners(){
            idValue.addMouseListener(mouseAdapter);
            nameValue.addMouseListener(mouseAdapter);
            deleteButton.addActionListener(this::onClickDeleteCustomer);
            exitButton.addActionListener(this::onClickExitButton);
        }

        public void onClickExitButton(ActionEvent e) {
        this.dispose();
        }

        MouseAdapter mouseAdapter= new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()== idValue || e.getSource()== nameValue){
                    idValue.setText("");
                    idValue.setBorder(new LineBorder(Color.GRAY));
                    idValue.setForeground(Color.BLACK);
                    nameValue.setText("");
                    nameValue.setBorder(new LineBorder(Color.GRAY));
                    nameValue.setForeground(Color.BLACK);
                }
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
        new DeleteCustomer();
    }


}
