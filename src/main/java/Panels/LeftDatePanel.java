package Panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LeftDatePanel extends JPanel {
        JLabel issueDate, issueDateValue, month, monthValue;
    public LeftDatePanel() {
        createPanel();
        createComponents();
        addComponents();

    }
    public void createComponents(){
        issueDate = new JLabel(" Issue Date: ", JLabel.LEFT);
        issueDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        issueDateValue = new JLabel("--", JLabel.CENTER);
        issueDateValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        //issueDateValue.setBorder(new LineBorder(Color.lightGray));

        month = new JLabel(" Month: ", JLabel.RIGHT);
        month.setFont(new Font("Times New Roman", Font.PLAIN, 16));
       // month.setBorder(new LineBorder(Color.lightGray));

        monthValue = new JLabel("--", JLabel.CENTER);
        monthValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        //monthValue.setBorder(new LineBorder(Color.lightGray));
    }

    public void createPanel(){
        setSize(640, 40);
        //setBorder(new LineBorder(Color.LIGHT_GRAY));
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 4, 10, 0));
    }

    public void addComponents(){
        add(issueDate);
        add(issueDateValue);
        add(month);
        add(monthValue);
    }

    public JPanel getPanel(){
        return this;
    }

    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new LeftDatePanel());
        frame.setSize(640, 80);
        frame.setVisible(true);

    }
}
