package Panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TitlePanel extends JPanel {
    JLabel title;
    TitlePanel() {

        setSize(960, 40);
       // setBorder(new LineBorder(Color.LIGHT_GRAY));
        setBackground(new Color(160, 227, 160));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        title = new JLabel("ELECTRICITY  BILL", JLabel.CENTER);
        title.setSize(960, 20);
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        title.setBackground(Color.WHITE);
        title.setLocation(0,0);

        add(title);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        TitlePanel titlePanel = new TitlePanel();
        frame.add(titlePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 80);
        frame.setVisible(true);

    }
}