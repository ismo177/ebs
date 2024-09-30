package Panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ControlPanel extends JPanel {
    FindCustomerPanel findCustomerPanel;
    FoundDetailsPanel foundDetailsPanel;
    MonthChoice monthChoice;
    ButtonsPanel buttonsPanel;

    public ControlPanel() {
        createPanel();
        createComponents();
        addComponents();
    }

protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(new Color(210, 232, 210));
    g.fillRoundRect(0, 0, 500, 800, 50, 50);
}

public void createPanel(){
    setSize(500, 800);
    setLayout(null);

}

public void createComponents(){
        findCustomerPanel = new FindCustomerPanel();
        findCustomerPanel.setLocation(20,20);

        foundDetailsPanel = new FoundDetailsPanel();
        foundDetailsPanel.setLocation(20,160);

        monthChoice = new MonthChoice();
        monthChoice.setLocation(20, 500);

        buttonsPanel = new ButtonsPanel();
        buttonsPanel.setLocation(20, 600);
}

public MonthChoice getMonthChoice(){
        return this.monthChoice;
}

public void addComponents(){
        add(findCustomerPanel);
        add(foundDetailsPanel);
        add(monthChoice);
        add(buttonsPanel);
}

public ButtonsPanel getButtonsPanel(){
        return this.buttonsPanel;
}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Control Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ControlPanel panel = new ControlPanel();
        frame.getContentPane().add(panel);
        frame.setSize(550, 850);
        //frame.pack();
        frame.setVisible(true);
    }


}
