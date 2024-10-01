package Panels;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MonthChoice extends JPanel {
    int i=0;
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String month=months[0];
    JButton leftButton, rightButton;
    JLabel monthLabel;
    public MonthChoice() {
        createPanel();
        createComponents();
        addComponents();
        addListeners();

    }

    public void createPanel(){
        setLayout(new BorderLayout());
        setSize(210, 40);
    }

    public void createComponents(){
        monthLabel = new JLabel(month, JLabel.CENTER);
        monthLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        monthLabel.setBorder(new LineBorder(Color.BLACK));

        leftButton = new JButton("<<");
        leftButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        leftButton.setBackground(new Color(50, 205, 50));

        rightButton = new JButton(">>");
        rightButton.setBackground(new Color(50, 205, 50));
        rightButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
    }

    public void addComponents(){
        add(leftButton, BorderLayout.WEST);
        add(rightButton, BorderLayout.EAST);
        add(monthLabel, BorderLayout.CENTER);
    }

    public void addListeners(){
        leftButton.addActionListener(this::onClickSetMonth);
        rightButton.addActionListener(this::onClickSetMonth);
    }

    public String getMonth(){
        return this.month;
    }

    public void setMonthLabelValue(String month){
        this.monthLabel.setText(month);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(210, 80);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(new MonthChoice());
        frame.setVisible(true);

    }

    public void onClickSetMonth(ActionEvent e){
        String message=e.getActionCommand();
        if (message.equals("<<") || message.equals(">>")) {
            setMonth(message);
        }
        }



    public void setMonth(String message) {
        if (message.equals("<<")) {
            if (i > 0) {
                i--;
                month = months[i];
                monthLabel.setText(month);
            } else
                i = months.length - 1;
            month = months[i];
            monthLabel.setText(month);
        }
        if (message.equals(">>")) {
            if (i < months.length - 1) {
                i++;
                month = months[i];
                monthLabel.setText(month);
            } else {
                i = 0;
                month = months[i];
                monthLabel.setText(month);
            }
        }
    }
}