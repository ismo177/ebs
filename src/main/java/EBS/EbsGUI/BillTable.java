package EBS.EbsGUI;

import service.bill.Bill;
import service.CrudServiceFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import java.util.List;

public class BillTable extends JFrame {
    Toolkit toolKit = Toolkit.getDefaultToolkit();
    Dimension dimension = toolKit.getScreenSize();
    JTable table;
    JPanel panel;
    String[] tableHeaderColumnNames = {"units_off/Peak", "units_On/Peak", "read_date", "issue_date",
            "payment_date", "deadline_date", "month", "offPeakAmount", "onPeakAmount", "amount",
            "invoice_status", "customerName"};
    String[][] data = new String[getList().length][tableHeaderColumnNames.length];

    public BillTable() {
        data = getList();

        createTable();
        createScrollBar();
        createBackPanel();
        createFrame();
        addComponents();
        addListeners();
    }

    public void createFrame() {

        setTitle("Bill Table");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(0, 0);
        setSize(dimension);
        //getContentPane().setBackground(new Color(210, 232, 210));
        setVisible(true);
    }

    public void createBackPanel() {
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.lightGray);
                g2d.fill3DRect(35, 35, dimension.width - 88, dimension.height - 188, false);
                g2d.setColor(new Color(220, 220, 220));
                g2d.fillRect(38, 38, dimension.width - 95, dimension.height - 195);
            }
        };
        panel.setSize(dimension);
        panel.setBackground(new Color(210, 232, 210));
        panel.setLayout(null);
        //panel.add(sp);
    }

    public void createTable() {
        table = new JTable(data, tableHeaderColumnNames);
        table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 18));
        table.setFont(new Font("Serif", Font.PLAIN, 16));
        table.getColumnModel().getColumn(10).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null && value.equals("false")) {
                    c.setForeground(Color.RED);
                } else {
                    c.setForeground(Color.BLACK);
                }
                    return c;
                }
        });
    }

    public JScrollPane createScrollBar() {
        JScrollPane sp = new JScrollPane(table);
        sp.setSize(dimension.width - 100, dimension.height - 200);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setFont(new Font("Serif", Font.PLAIN, 200));
        sp.setLocation(40, 40);
        return sp;
    }

    public void addListeners() {
        table.addMouseListener(mouseAdapter);
    }

    public void addComponents(){
        panel.add(createScrollBar());
        add(panel);
    }


    public String[][] getList() {
        CrudServiceFactory crudServiceFactory = CrudServiceFactory.BILL_SERVICE;
        List<Bill> list = crudServiceFactory.getBillService().findAll();
        String[][] billListString = new String[list.size()][tableHeaderColumnNames.length];

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < tableHeaderColumnNames.length; j++) {
                String[] billToStrings = new String[tableHeaderColumnNames.length];
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

    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = table.rowAtPoint(e.getPoint());
            int col = table.columnAtPoint(e.getPoint());
            System.out.println("Row: "+(row+1)+","+" Column: "+(col+1));
            if (row >= 0 && col >= 0) {
                table.getCellSelectionEnabled();
            }
        }
    };

    public static void main(String[] args) {
        new BillTable();
    }


}

