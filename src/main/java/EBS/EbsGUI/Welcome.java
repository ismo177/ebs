package EBS.EbsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Welcome extends JFrame{


    Toolkit t = Toolkit.getDefaultToolkit();
    Dimension d = t.getScreenSize();

    public Welcome() {
        super("Electricity Billing System");
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/green-energie.jpg"));
        Image image = icon.getImage().getScaledInstance(d.width, d.height, Image.SCALE_DEFAULT);
        ImageIcon iconn = new ImageIcon(image);
        JLabel label = new JLabel(iconn);
        add(label);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(d.width, d.height);
        setLocationRelativeTo(null);
        setVisible(true);


    }

}




