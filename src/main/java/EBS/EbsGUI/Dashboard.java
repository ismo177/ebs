package EBS.EbsGUI;

import Panels.Bill_Panel;
import Panels.ControlPanel;
import bill.Bill;
import service.Customer.Customer;
import service.Tax.Tax;
import service.ServiceFactory;

import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Dashboard extends JFrame   {
    Bill_Panel billPanel;
    ControlPanel controlPanel;
    int tempCustID;

public Dashboard() {
        createDashboard();
        createComponents();
        addComponents();
        repaint();
    }

    public void createDashboard(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setSize(screenSize);
        setLayout(null);
        setVisible(true);
    }

    public void createComponents(){

    billPanel = new Bill_Panel();
    billPanel.setLocation(20,20);

    controlPanel = new ControlPanel();
    controlPanel.setLocation(1060,20);
    }

    public void addComponents(){
    getContentPane().add(billPanel);
    getContentPane().add(controlPanel);

    }

public String getTime(){
    LocalDateTime timeDate = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
    String formattedDate = timeDate.format(myFormatObj);
    return formattedDate;
}



    public void findCustomer(ActionEvent event){
        //validate customer id, name
        String id="";
        String name="";
      /*  if(!id.isEmpty()) {
            findCustomerByID(id, name, tempCustID, ...);
            findCustomerByName(...);
        }

       */

    }

    public void setBill(ActionEvent event) {
        //validate customer id, name
        String id="";
        String name="";
        setGUIBill(id, name);
    }

    public void onClickGenerateBill(ActionEvent event){
        new GenerateBill(tempCustID);
        }

    public void onClickPayBill(ActionEvent event){
        new PayBill(tempCustID);
    }

    public void onClickPayAmount(ActionEvent event){
        new PayAmount();
    }


    public void onClickMonthChoiceButton(ActionEvent event) {
        String message=event.getActionCommand();
        if (message.equals("<<") || message.equals(">>")) {
            //setMonth(message);
        }
    }

    public void onClickPrintBIll(ActionEvent event) {
        printBill(billPanel);

    }

    //PrintBill(https://docs.oracle.com/javase%2Ftutorial%2F/2d/printing/printable.html)
    private void printBill(JPanel panel) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName("PrintBill");
        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D graphics2D = (Graphics2D) graphics;
                graphics2D.translate(pageFormat.getImageableX() - 5, pageFormat.getImageableY());
                graphics2D.scale(0.58, 0.5);
                panel.paint(graphics2D);
                return Printable.PAGE_EXISTS;
            }
        });

        boolean returningResult = printerJob.printDialog();
        if (returningResult) {
            try {
                printerJob.print();
            } catch (PrinterException printerException) {
                JOptionPane.showMessageDialog(null, printerException.getMessage());
            }
        }
    }



    public void findCustomerByID(String id, String name, int tempCustID, JTextField nameField ) {
        if (!id.isEmpty() && name.isEmpty()) {
            ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
            Customer customer = serviceFactory.getCustomerService().find(id);
            if (customer != null) {
                tempCustID = customer.getId();
                    //setCustomerFields(customer);
            } else {
                nameField.setBorder(new LineBorder(Color.RED, 2, true));
                nameField.setForeground(Color.BLACK);
                nameField.setText("Doesn't exist");
            }
        }
    }

    public void findCustomerByName(String id, String name, int tempCustID, JTextField nameField ) {
        if (id.isEmpty() && !name.isEmpty()) {
            ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
            Customer customer = serviceFactory.getCustomerService().findByName(name);
            if (customer != null) {
                tempCustID = customer.getId();
                    //setCustomerFields(customer);
            } else {
                nameField.setBorder(new LineBorder(Color.RED, 2, true));
                nameField.setForeground(Color.BLACK);
                nameField.setText("Doesn't exist");
            }
        }
    }

    public void setGUIBill(String id, String name) {

        if (!id.isEmpty() && name.isEmpty() || (!id.isEmpty() && !name.isEmpty())) {
            setBillByID(id);
        } else if (id.isEmpty() && !name.isEmpty()) {
            setBillByName(name);
        }
    }


    public void setBillByID(String id){
        ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
        Customer customer = serviceFactory.getCustomerService().find(id);

        serviceFactory = ServiceFactory.TAX_SERVICE;
        Tax tax = serviceFactory.getTaxService().find(customer.getTax().getId());

        serviceFactory = ServiceFactory.BILL_SERVICE;
        Bill bill = serviceFactory.getBillService().findByMonthCustomer(controlPanel.getMonthChoice().getMonth(), customer);

        if (customer != null && bill != null && tax != null) {
            //call setPanel func for each panel
        } else {
            JOptionPane.showMessageDialog(this, "Can't find bill !", null, JOptionPane.WARNING_MESSAGE);

        }
    }


    public void setBillByName(String name){
        ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
        Customer customer = serviceFactory.getCustomerService().findByName(name);

        serviceFactory = ServiceFactory.TAX_SERVICE;
        Tax tax = serviceFactory.getTaxService().find(customer.getTax().getId());

        serviceFactory = ServiceFactory.BILL_SERVICE;
        Bill bill = serviceFactory.getBillService().findByMonthCustomer(controlPanel.getMonthChoice().getMonth(), customer);

        if (customer != null && bill != null && tax != null) {
            //call setPanel func for each panel
        }
         else {
            JOptionPane.showMessageDialog(this, "Can't find bill !", "Dashboard", JOptionPane.WARNING_MESSAGE);
        }
    }



    public static void main(String[] args) {
        new Dashboard().setVisible(true);
    }
}