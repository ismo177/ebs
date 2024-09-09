package EBS.EbsGUI;

import EBS.EntityServices.Bill.Bill;
import EBS.EntityServices.Customer.Customer;
import EBS.EntityServices.ServiceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import java.util.List;

public class BillTable extends JFrame implements MouseListener {

    Toolkit toolKit = Toolkit.getDefaultToolkit();
    Dimension dimension = toolKit.getScreenSize();
    JTable table;
    JPanel panel;
    String[] colNames={"units_off/Peak", "units_On/Peak", "read_date", "issue_date",
                        "payment_date", "deadline_date", "month", "offPeakAmount", "onPeakAmount","amount",
                        "invoice_status", "customerName"};
    String[][] data=new String[getList().length][colNames.length];
    int rowsLength;
    JLabel l1;

    public BillTable() {

        data=getList();

        table =new JTable(data, colNames);
        table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 18));
        table.setFont(new Font("Serif", Font.PLAIN, 16));
        table.addMouseListener(this);


        //t.setSize(d.width, d.height/3);
        JScrollPane sp=new JScrollPane(table);
        sp.setSize(dimension.width-100, dimension.height-200);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setFont(new Font("Serif", Font.PLAIN, 200));
        sp.setLocation(40,40);
       // add(sp);

        panel =new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.lightGray);
                g2d.fill3DRect(35,35, dimension.width-88, dimension.height-188,false);
                g2d.setColor(new Color(220,220,220));
                g2d.fillRect(38,38, dimension.width-95, dimension.height-195);

            }
        };
        panel.setSize(dimension);
        panel.setBackground(new Color(210, 232, 210));
        panel.setLayout(null);
        panel.add(sp);

        add(panel);
        setTitle("Bill Table");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(0,0);
        setSize(dimension);
        //getContentPane().setBackground(new Color(210, 232, 210));
        setVisible(true);


    }
    
    public String[][] getList(){
       // ServiceFactory.getService("BillService").findAll();
        ServiceFactory serviceFactory = ServiceFactory.BILL_SERVICE;
        List<Bill> list= serviceFactory.getBillService().findAll();

        String[][] billListString=new String[list.size()][colNames.length];

        for(int i=0;i<list.size();i++) {

            for (int j = 0; j <colNames.length; j++) {
                String[] billToStrings = new String[colNames.length];
               //billToStrings[0] = String.valueOf(list.get(i).getId());
                billToStrings[0] = String.valueOf(list.get(i).getUnitsOffPeak());
                billToStrings[1] = String.valueOf(list.get(i).getUnitsOnPeak());
                billToStrings[2] = list.get(i).getReadDate();
                billToStrings[3] = list.get(i).getIssueDate();
                billToStrings[4] = list.get(i).getPaymentDate();
                billToStrings[5] = list.get(i).getDeadlineDate();
                billToStrings[6] = list.get(i).getMonth();
                billToStrings[7] = String.valueOf(list.get(i).getOffPeakAmount());
                billToStrings[8] = String.valueOf(list.get(i).getOnPeakAmount());
                billToStrings[9] = String.valueOf(list.get(i).getAmount());
                billToStrings[10] = String.valueOf(list.get(i).getInvoiceStatus());
                billToStrings[11] = String.valueOf(list.get(i).getCustomer().getName());
                //billToStrings[12] = String.valueOf(list.get(i).getUser().getId());

                billListString[i][j] = billToStrings[j];
            }
        }


        return billListString;
        }






    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint());
        int col = table.columnAtPoint(e.getPoint());
        if (row >= 0 && col >= 0) {
            table.getCellSelectionEnabled();
           //t.getColumn(3).getCellEditor();

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) {
        new BillTable();
    }


}
