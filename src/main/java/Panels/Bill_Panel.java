package Panels;

import javax.swing.*;
import java.awt.*;

public class Bill_Panel extends JPanel {
    TitlePanel titlePanel;
    HeaderPanel headerPanel;
    LeftDatePanel leftDatePanel;
    RightDatePanel rightDatePanel;
    PrincipalPanel principalPanel;
    ReceiverPanel receiverPanel;
    AmountChargesPanel amountChargesPanel;
    CustomerInfoPanel customerInfoPanel;
    EnergySummaryPanel energySummaryPanel;
    TotalChargesPanel totalChargesPanel;

    public Bill_Panel() {
       createPanel();
       createComponents();
       addComponents();

    }

    public void createPanel() {
        setSize(1000, 790);
        setLayout(null);

    }

    public void createComponents(){
        titlePanel = new TitlePanel();
        titlePanel.setLocation(20, 5);

        headerPanel = new HeaderPanel();
        headerPanel.setLocation(20, 40);

        leftDatePanel = new LeftDatePanel();
        leftDatePanel.setLocation(20, 130);

        rightDatePanel = new RightDatePanel();
        rightDatePanel.setLocation(680, 130);

        principalPanel = new PrincipalPanel();
        principalPanel.setLocation(20, 180);

        receiverPanel = new ReceiverPanel();
        receiverPanel.setLocation(340, 180);

        amountChargesPanel = new AmountChargesPanel();
        amountChargesPanel.setLocation(680, 180);

        customerInfoPanel = new CustomerInfoPanel();
        customerInfoPanel.setLocation(20, 490);

        energySummaryPanel = new EnergySummaryPanel();
        energySummaryPanel.setLocation(550, 490);

        totalChargesPanel = new TotalChargesPanel();
        totalChargesPanel.setLocation(670, 675);


    }

    public void addComponents(){
        add(titlePanel);
        add(headerPanel);
        add(leftDatePanel);
        add(rightDatePanel);
        add(principalPanel);
        add(receiverPanel);
        add(amountChargesPanel);
        add(customerInfoPanel);
        add(energySummaryPanel);
        add(totalChargesPanel);
    }

        protected void paintComponent (Graphics g){
            super.paintComponent(g);
            g.setColor(new Color(160, 227, 160));
            g.fillRoundRect(0, 0, 1000, 790, 50, 50);
            g.setColor(Color.GRAY);
            g.drawLine(30, 475, 970, 475);

        }

        public LeftDatePanel getLeftDatePanel() {
        return leftDatePanel;
        }

        public RightDatePanel getRightDatePanel(){
        return rightDatePanel;
        }

        public PrincipalPanel getPrincipalPanel(){
        return principalPanel;
        }

        public ReceiverPanel getReceiverPanel(){
        return receiverPanel;
        }

        public AmountChargesPanel getAmountChargesPanel(){
        return amountChargesPanel;
        }

        public CustomerInfoPanel getCustomerInfoPanel(){
        return customerInfoPanel;
        }

        public EnergySummaryPanel getEnergySummaryPanel(){
        return energySummaryPanel;
        }

        public TotalChargesPanel getTotalChargesPanel(){
        return totalChargesPanel;
        }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bill Panel");
        Bill_Panel panel = new Bill_Panel();
        frame.getContentPane().add(panel);
        frame.setSize(1050,850);
        frame.setVisible(true);

    }
    }

