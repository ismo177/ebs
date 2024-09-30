package Panels;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    JButton payBillButton, payAmountButton, setBillButton, printBillButton, genBillButton, exitButton;
    public ButtonsPanel() {
    createPanel();
    createComponents();
    addFont();
    addComponents();

    }

    public void createPanel(){
        setLayout(new GridLayout(3, 2, 30, 20));
        setSize(460, 160);
        setBackground(new Color(210, 232, 210));
    }

    public void createComponents(){
        payBillButton = new JButton("PayBill");
        payBillButton.setBackground(new Color(50, 205, 50));

        payAmountButton = new JButton("PayAmount");
        payAmountButton.setBackground(new Color(50, 205, 50));

        setBillButton = new JButton("<<<SetBill");
        setBillButton.setBackground(new Color(50, 205, 50));

        printBillButton = new JButton("PrintBill");
        printBillButton.setBackground(new Color(50, 205, 50));

        genBillButton = new JButton("GenBill");
        genBillButton.setBackground(new Color(241, 213, 100));

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(255, 0, 50));

    }

    public void addComponents(){
        add(payBillButton);
        add(payAmountButton);
        add(setBillButton);
        add(printBillButton);
        add(genBillButton);
        add(exitButton);
    }

    public void addFont(){
        JButton[] buttons= { payBillButton, payAmountButton, setBillButton, printBillButton, genBillButton, exitButton };
        for(JButton button : buttons){
            button.setFont(new Font("Times New Roman", Font.BOLD, 20));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ButtonsPanel());
        frame.setSize(460,160);
        //frame.pack();
        frame.setVisible(true);

    }
}
