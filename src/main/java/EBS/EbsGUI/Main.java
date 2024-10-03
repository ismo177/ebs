package EBS.EbsGUI;

import javax.swing.*;

public class Main implements Runnable {
    @Override
    public void run() {
        new Login();
    }

    public static void main(String[] args) throws InterruptedException {

        SwingUtilities.invokeLater(new Main());

    }
}