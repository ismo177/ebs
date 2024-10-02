package EBS.EbsGUI;

import service.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MenuFrame extends JFrame {
    User tempUser;
    JMenuItem menuItemNewCustomer,menuItemCreateNewUser, menuItemDeleteCustomer, menuItemExit;
    JMenuItem menuItemGuiBill, menuItemCustomerDetails, menuItemBillTable;
    JMenuItem menuItemNotepad, menuItemCalculator, menuItemWebBrowser, menuItemPDFBill;
    JMenuItem readMe;

    MenuFrame(User tempUser){
        this.tempUser =tempUser;

        createMainMenu();
        createUserMenu();
        createUtilityMenu();
        createAboutMenu();

        JMenu main= getMainMenu(menuItemNewCustomer, menuItemCreateNewUser, menuItemDeleteCustomer, menuItemExit);
        JMenu user = getUserMenu(menuItemGuiBill, menuItemCustomerDetails, menuItemBillTable);
        JMenu utility = getUtilityMenu(menuItemNotepad, menuItemCalculator, menuItemWebBrowser, menuItemPDFBill);
        JMenu about = getAboutMenu(readMe);

        setMenuBar(main, user, utility, about);
        setMenuFrame();

        addMainListeners();
        addUserListeners();
        addUtilityListeners();
        addAboutListeners();
    }

    public void setMenuBar(JMenu m1, JMenu m2, JMenu m3, JMenu m4) {
        JMenuBar menuBar  = new JMenuBar();
        menuBar.add(m1);
        menuBar.add(m2);
        menuBar.add(m3);
        menuBar.add(m4);
        setJMenuBar(menuBar);
    }

    public JMenu getMainMenu(JMenuItem menuItem1, JMenuItem menuItem2, JMenuItem menuItem3, JMenuItem menuItem4) {
        JMenu main = new JMenu("Main");
        main.setFont(new Font("Arial", Font.PLAIN, 20));
        main.setForeground(Color.BLACK);
        main.add(menuItem1);
        main.add(menuItem2);
        main.add(menuItem3);
        main.add(menuItem4);
        return main;
    }

    public JMenu getUserMenu(JMenuItem menuItem1, JMenuItem menuItem2, JMenuItem menuItem3) {
        JMenu user = new JMenu("User");
        user.setFont(new Font("Arial",Font.PLAIN,20));
        user.setForeground(Color.BLACK);
        user.add(menuItem1);
        user.add(menuItem2);
        user.add(menuItem3);
        return user;
    }

    public JMenu getUtilityMenu(JMenuItem menuItem1, JMenuItem menuItem2, JMenuItem menuItem3, JMenuItem menuItem4) {
        JMenu utility = new JMenu("Utility");
        utility.setFont(new Font("Arial",Font.PLAIN,20));
        utility.setForeground(Color.BLACK);
        utility.add(menuItem1);
        utility.add(menuItem2);
        utility.add(menuItem3);
        utility.add(menuItem4);
        return utility;
    }

    public JMenu getAboutMenu(JMenuItem menuItem){
        JMenu about = new JMenu("About");
        about.setFont(new Font("Arial",Font.PLAIN,20));
        about.add(menuItem);
        return about;
    }

    public void createMainMenu(){
        menuItemNewCustomer = new JMenuItem("New Customer");
        menuItemNewCustomer.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("images/icon1.jpg"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemNewCustomer.setIcon(new ImageIcon(image1));
        menuItemNewCustomer.setBackground(Color.WHITE);

         menuItemCreateNewUser = new JMenuItem("Create New User");
        menuItemCreateNewUser.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("images/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemCreateNewUser.setIcon(new ImageIcon(image2));
        menuItemCreateNewUser.setBackground(Color.WHITE);

        menuItemDeleteCustomer = new JMenuItem("Delete Customer");
        menuItemDeleteCustomer.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("images/delete-customer.png"));
        Image image3 = icon3.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemDeleteCustomer.setIcon(new ImageIcon(image3));
        menuItemDeleteCustomer.setBackground(Color.WHITE);

         menuItemExit = new JMenuItem("Exit");
        menuItemExit.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("images/green-exit.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemExit.setIcon(new ImageIcon(image4));
        menuItemExit.setBackground(Color.WHITE);
    }

    public void createUserMenu(){
        menuItemGuiBill = new JMenuItem("Gui_Bill");
        menuItemGuiBill.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("images/icon5.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemGuiBill.setIcon(new ImageIcon(image5));
        menuItemGuiBill.setBackground(Color.WHITE);

        menuItemCustomerDetails = new JMenuItem("Customer Details");
        menuItemCustomerDetails.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("images/icon6.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemCustomerDetails.setIcon(new ImageIcon(image6));
        menuItemCustomerDetails.setBackground(Color.WHITE);

        menuItemBillTable = new JMenuItem("Bill Table");
        menuItemBillTable.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("images/table-icon.png"));
        Image image7 = icon7.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemBillTable.setIcon(new ImageIcon(image7));
        menuItemBillTable.setBackground(Color.WHITE);

    }

    public void createUtilityMenu(){
        menuItemNotepad = new JMenuItem("Notepad");
        menuItemNotepad.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("images/icon12.png"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemNotepad.setIcon(new ImageIcon(image8));
        menuItemNotepad.setBackground(Color.WHITE);

        menuItemCalculator = new JMenuItem("Calculator");
        menuItemCalculator.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("images/icon9.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemCalculator.setIcon(new ImageIcon(image9));
        menuItemCalculator.setBackground(Color.WHITE);

        menuItemWebBrowser = new JMenuItem("Web Browser");
        menuItemWebBrowser.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("images/icon10.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemWebBrowser.setIcon(new ImageIcon(image10));
        menuItemWebBrowser.setBackground(Color.WHITE);

        menuItemPDFBill = new JMenuItem("PDF Bill");
        menuItemPDFBill.setFont(new Font("Arial",Font.PLAIN,16));
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("images/pdf-print.jpg"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        menuItemPDFBill.setIcon(new ImageIcon(image11));
        menuItemPDFBill.setBackground(Color.WHITE);
    }

    public void createAboutMenu(){
        readMe = new JMenuItem("Read Me");
        readMe.setFont(new Font("Arial",Font.PLAIN,16));
    }

    public void addMainListeners(){
        menuItemNewCustomer.addActionListener(this::onClickCreateNewCustomer);
        menuItemCreateNewUser.addActionListener(this::onClickCreateNewUser);
        menuItemDeleteCustomer.addActionListener(this::onClickDeleteCustomer);
        menuItemExit.addActionListener(this::onClickExit);
    }

    public void addUserListeners(){
        menuItemGuiBill.addActionListener(this::onClickGui_Bill);
        menuItemCustomerDetails.addActionListener(this::onClickCustomerDetails);
        menuItemBillTable.addActionListener(this::onClickBIllTable);
    }

    public void addUtilityListeners(){
        menuItemNotepad.addActionListener(this::onClickNotepad);
        menuItemCalculator.addActionListener(this::onClickCalculator);
        menuItemWebBrowser.addActionListener(this::onClickWebBrowser);
        menuItemPDFBill.addActionListener(this::onClickPDFBill);
    }

    public void addAboutListeners(){
        readMe.addActionListener(this::onClickReadMe);
    }



    public void setMenuFrame(){
        setTitle("EBS "+tempUser.getUsername());
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        setSize(d.width, d.height);
        setFont(new Font("Times New Roman",Font.BOLD,16));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon ic =  new ImageIcon(ClassLoader.getSystemResource("images/green-energie.jpg"));
        Image i3 = ic.getImage().getScaledInstance(d.width, d.height,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel l1 = new JLabel(icc3);
        add(l1);
        setLayout(new FlowLayout());
        setVisible(true);
    }


    public void onClickCreateNewUser(ActionEvent e) {
        new NewUser();

    }


    public void onClickCreateNewCustomer(ActionEvent e) {
        new NewCustomer(tempUser);

    }


    public void onClickDeleteCustomer(ActionEvent e) {
        new DeleteCustomer();
    }


    public void onClickExit(ActionEvent e) {
        System.exit(0);
    }


    public void onClickGui_Bill(ActionEvent e) {
        new Dashboard();
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
            Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe " +
                    "--incognito file:///C:/Users/ismet/Desktop/images");
        } catch (IOException ex) {
            System.out.println("PDF Read exception: " + ex.getMessage());
        }
    }


    public void onClickReadMe(ActionEvent e){
        try{
            Runtime.getRuntime().exec("notepad.exe C:\\Users\\ismet\\IdeaProjects\\EBS\\src\\main" +
                    "\\resources\\About.txt");
        }catch(IOException ex){
            System.out.println("Read me exception: "+ex.getMessage());
        }
    }



}




