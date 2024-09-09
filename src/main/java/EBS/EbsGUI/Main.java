package EBS.EbsGUI;

import javax.swing.*;

public class Main implements Runnable {
    public static void main(String[] args) throws InterruptedException {

        SwingUtilities.invokeLater(new Main());
        //System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void run() {
       new Login();
       // System.out.println(Thread.currentThread().getName());
    }


}