package Panels;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

        month = new JLabel(" Month: ", JLabel.RIGHT);
        month.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        monthValue = new JLabel("--", JLabel.CENTER);
        monthValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
    }

    public void createPanel(){
        setSize(640, 40);
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

    public void setDateMonth(String month){
        String date= getDate();
        issueDateValue.setText(date);
        monthValue.setText(month);


    }

    public String getDate(){
        LocalDateTime timeDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
        String formattedDate = timeDate.format(myFormatObj);
        return formattedDate;
    }

    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new LeftDatePanel());
        frame.setSize(640, 80);
        frame.setVisible(true);

    }
}
