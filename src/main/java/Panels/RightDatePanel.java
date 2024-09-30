package Panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RightDatePanel extends JPanel {
    JLabel issueDate, issueDateValue, month, monthValue;
    public RightDatePanel() {
        createPanel();
        createComponents();
        addComponents();

    }
    public void createPanel(){
        setSize(300, 40);
        //setBorder(new LineBorder(Color.LIGHT_GRAY));
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 4, 0, 0));
    }

    public void createComponents(){
        issueDate = new JLabel(" Issue Date: ", JLabel.LEFT);
        issueDate.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        issueDateValue = new JLabel("--", JLabel.CENTER);
        issueDateValue.setFont(new Font("Times New Roman", Font.BOLD, 12));
       // issueDateValue.setBorder(new LineBorder(Color.lightGray));

        month = new JLabel(" Month: ", JLabel.RIGHT);
        month.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        monthValue = new JLabel("--", JLabel.CENTER);
        monthValue.setFont(new Font("Times New Roman", Font.BOLD, 12));
       // monthValue.setBorder(new LineBorder(Color.lightGray));
    }

    public void addComponents(){
        add(issueDate);
        add(issueDateValue);
        add(month);
        add(monthValue);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new RightDatePanel());
        frame.pack();
        frame.setVisible(true);

    }
}
