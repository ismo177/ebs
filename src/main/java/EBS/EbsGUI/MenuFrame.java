package EBS.EbsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MenuFrame extends JFrame {
    Gui_Bill panel ;
    String tempUser;
    int tempUserID;
    MenuFrame(String tempUser, int tempUserID){
        this.tempUser =tempUser;
        this.tempUserID = tempUserID;
        setTitle("EBS "+tempUser);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        setSize(d.width, d.height);

        //background image
        ImageIcon ic =  new ImageIcon(ClassLoader.getSystemResource("images/green-energie.jpg"));
        Image i3 = ic.getImage().getScaledInstance(d.width, d.height,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel l1 = new JLabel(icc3);
        add(l1);

        //menuBar
        JMenuBar mb  = new JMenuBar();

        /* FIRST COLUMN */
        JMenu main = new JMenu("Main");
        main.setFont(new Font("Arial", Font.PLAIN, 20));
        //.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        main.setForeground(Color.BLACK);

        JMenuItem menuItemNewCustomer = new JMenuItem("New Customer");
        JMenuItem menuItemCreateNewUser = new JMenuItem("Create New User");
        JMenuItem menuItemDeleteCustomer = new JMenuItem("Delete Customer");
        JMenuItem menuItemExit = new JMenuItem("Exit");

        // Customer details
        menuItemNewCustomer.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("images/icon1.jpg"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemNewCustomer.setIcon(new ImageIcon(image1));
        menuItemNewCustomer.setBackground(Color.WHITE);

        /* ---- Meter Details ---- */
        menuItemCreateNewUser.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("images/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemCreateNewUser.setIcon(new ImageIcon(image2));
        menuItemCreateNewUser.setBackground(Color.WHITE);

        /* ----- Delete Customer-----*/
        menuItemDeleteCustomer.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("images/delete-customer.png"));
        Image image3 = icon3.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemDeleteCustomer.setIcon(new ImageIcon(image3));
        menuItemDeleteCustomer.setBackground(Color.WHITE);

        /* ---- Exit  ----- */
        menuItemExit.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("images/green-exit.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemExit.setIcon(new ImageIcon(image4));
        menuItemExit.setBackground(Color.WHITE);

        menuItemNewCustomer.addActionListener(this::onClickCreateNewCustomer);
        menuItemCreateNewUser.addActionListener(this::onClickCreateNewUser);
        menuItemDeleteCustomer.addActionListener(this::onClickDeleteCustomer);
        menuItemExit.addActionListener(this::onClickExit);



        /* SECOND COLUMN */
        JMenu user = new JMenu("User");
        user.setFont(new Font("Arial",Font.PLAIN,20));

        JMenuItem menuItemGuiBill = new JMenuItem("Gui_Bill");
        JMenuItem menuItemCustomerDetails = new JMenuItem("Customer Details");
        JMenuItem menuItemBillTable = new JMenuItem("Bill Table");
        user.setForeground(Color.BLACK);


        /* ---- Generate Bill ---- */
        menuItemGuiBill.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("images/icon5.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemGuiBill.setIcon(new ImageIcon(image5));
        menuItemGuiBill.setBackground(Color.WHITE);

        /* ---- Customer Details ----*/
        menuItemCustomerDetails.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("images/icon6.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemCustomerDetails.setIcon(new ImageIcon(image6));
        menuItemCustomerDetails.setBackground(Color.WHITE);

        menuItemBillTable.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("images/table-icon.png"));
        Image image7 = icon7.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemBillTable.setIcon(new ImageIcon(image7));
        menuItemBillTable.setBackground(Color.WHITE);


        menuItemGuiBill.addActionListener(this::onClickGui_Bill);
        menuItemCustomerDetails.addActionListener(this::onClickCustomerDetails);
        menuItemBillTable.addActionListener(this::onClickBIllTable);

        // --------------------------------------------------------------------------------------------- 


        /* THIRD COLUMN */
        JMenu utility = new JMenu("Utility");
        utility.setFont(new Font("Arial",Font.PLAIN,20));
        JMenuItem menuItemNotepad = new JMenuItem("Notepad");
        JMenuItem menuItemCalculator = new JMenuItem("Calculator");
        JMenuItem menuItemWebBrowser = new JMenuItem("Web Browser");
        JMenuItem menuItemPDFBill = new JMenuItem("PDF Bill");
        utility.setForeground(Color.BLACK);

        /* ---- Notepad ---- */
        menuItemNotepad.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("images/icon12.png"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemNotepad.setIcon(new ImageIcon(image8));
        menuItemNotepad.setBackground(Color.WHITE);

        /* ---- Calculator ---- */
        menuItemCalculator.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("images/icon9.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemCalculator.setIcon(new ImageIcon(image9));
        menuItemCalculator.setBackground(Color.WHITE);

        /* ---- Web Browser ---- */
        menuItemWebBrowser.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("images/icon10.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemWebBrowser.setIcon(new ImageIcon(image10));
        menuItemWebBrowser.setBackground(Color.WHITE);

        menuItemPDFBill.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("images/pdf-print.jpg"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemPDFBill.setIcon(new ImageIcon(image11));
        menuItemPDFBill.setBackground(Color.WHITE);

        /*----- About --- */
        JMenu about = new JMenu("About");
        about.setFont(new Font("Arial",Font.PLAIN,20));

        JMenuItem readMe = new JMenuItem("Read Me");
        readMe.addActionListener(this::onClickReadMe);
        readMe.setFont(new Font("Arial",Font.PLAIN,16));
        about.add(readMe);

        menuItemNotepad.addActionListener(this::onClickNotepad);
        menuItemCalculator.addActionListener(this::onClickCalculator);
        menuItemWebBrowser.addActionListener(this::onClickWebBrowser);
        menuItemPDFBill.addActionListener(this::onClickPDFBill);

        main.add(menuItemNewCustomer);
        main.add(menuItemCreateNewUser);
        main.add(menuItemDeleteCustomer);
        main.add(menuItemExit);

        //user.add(u1);
        user.add(menuItemGuiBill);
        user.add(menuItemCustomerDetails);
        user.add(menuItemBillTable);

        utility.add(menuItemNotepad);
        utility.add(menuItemCalculator);
        utility.add(menuItemWebBrowser);
        utility.add(menuItemPDFBill);

        mb.add(main);
        mb.add(user);
        mb.add(utility);
        mb.add(about);

        setJMenuBar(mb);
        setFont(new Font("Senserif",Font.BOLD,16));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setVisible(true);
    }


    public void onClickCreateNewUser(ActionEvent e) {
        new CreateNewUser();

    }


    public void onClickCreateNewCustomer(ActionEvent e) {
        new CreateNewCustomer(tempUserID);

    }


    public void onClickDeleteCustomer(ActionEvent e) {
        new DeleteCustomer();
    }


    public void onClickExit(ActionEvent e) {
        System.exit(0);
    }


    public void onClickGui_Bill(ActionEvent e) {
        new Gui_Bill(tempUserID);
    }


    public void onClickCustomerDetails(ActionEvent e) {
        new CustomerDetails();
    }


    public void onClickBIllTable(ActionEvent e){
        new BillTable();
    }


    public void onClickNotepad(ActionEvent e){
        try{
            Runtime.getRuntime().exec("notepad.exe");
        }catch(IOException ex){
            System.out.println("Notepad exception"+ ex.getMessage());
        }
    }


    public void onClickCalculator(ActionEvent e){
        try{
            Runtime.getRuntime().exec("calc.exe");
        }catch(IOException ex){
            System.out.println("Calculator exception: "+ex.getMessage()); }
    }


    public void onClickWebBrowser(ActionEvent e){
        try {
            Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe --incognito www.google.com");

        } catch (IOException ex) {
            System.out.println("Web Browser exception: " + ex.getMessage());
        }
    }


    public void onClickPDFBill(ActionEvent e){
        try {
            Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe --incognito file:///C:/Users/ismet/Desktop/images");
        } catch (IOException ex) {
            System.out.println("PDF Read exception: " + ex.getMessage());
        }
    }


    public void onClickReadMe(ActionEvent e){
        try{
            Runtime.getRuntime().exec("notepad.exe C:\\Users\\ismet\\IdeaProjects\\EBS\\src\\main\\resources\\About.txt");
        }catch(IOException ex){
            System.out.println("Read me exception: "+ex.getMessage());
        }
    }

}




