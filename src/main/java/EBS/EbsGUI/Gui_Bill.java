package EBS.EbsGUI;

import bill.Bill;
import service.Customer.Customer;
import service.Tax.Tax;
import service.ServiceFactory;

import java.awt.event.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Gui_Bill extends JFrame implements ActionListener,MouseListener {

    LocalDateTime timeDate = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
    String formattedDate = timeDate.format(myFormatObj);
    Choice c;
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String month = months[0];

    JPanel mainPanel, billPanel, controlPanel;

    JLabel titleLabel, billPanelHeaderLabel2, billPanelHeaderLabel3, billPanelHeaderLabel4, billPanelHeaderLabel5, billPanelHeaderLabel6, billPanelHeaderLabel7, billPanelHeaderLabel8, billPanelInsetLabel1, billPanelInsetLabel2, billPanel_CenterInsetPrincipalLabel, billPanel_CenterInsetReceiverLabel, monthChoiceLabel, l13, l14;

    JPanel billPanelHeaderLabel1, billPanel_TopLeftPanel, billPanel_TopRightPanel, billPanel_CenterLeftTopPanel, billPanel_CenterLeftBottomPanel, billPanel_CenterCenterPanel, billPanel_CenterRightPanel, billPanel_BottomLeftPanel, billPanel_BottomRightPanel, billPanel_BottomRightLowerPanel, controlPanel_TopPanel, controlPanel_CenterPanel, controlPanel_BottomPanel, monthChoicePanel;
    JLabel TopLeftPanelIssueDate, TopLeftPanelIssueDateValue, TopLeftPanelForMonth, TopLeftPanelForMonthValue;

    JLabel TopRightPanelIssueDateLabel, TopRightPanelIssueDateValue, TopRightPanelForMonthLabel, TopRightPanelDateValue;

    JLabel CenterLeftTopPanelNameLabel, CenterLeftTopPanelNameValue, CenterLeftTopPanelAdressLabel, CenterLeftTopPanelAddressValue;

    JLabel CenterLeftBottomPanelBank1Label, CenterLeftBottomPanelBank1Value, CenterLeftBottomPanelBank2Label, CenterLeftBottomPanelBank2Value;

    JLabel CenterCenterPanelTransactionAccountNoLabel, CenterCenterPanelTransactionAccountNoValue, CenterCenterPanelReferenceNumberLabel, CenterCenterPanelReferenceNumberLabelValue, CenterCenterPanelAmountLabel, CenterCenterPanelAmountValue, CenterCenterPanelAreaOfSupplyLabel, CenterCenterPanelAreaOfSupplyValue, CenterCenterPanelAddressAndPlaceLabel, CenterCenterPanelAddressAndPlaceValue;
    JLabel CenterRightPanelAmountLabel, CenterRightPanelAmountValue, CenterRightPanelReferenceNoLabel, CenterRightPanelReferenceNoValue, CenterRightPanelNameLabel, CenterRightPanelNameValue, CenterRightPanelAddressLabel, CenterRightPanelAddressValue, CenterRightPanelAddressAndPlaceLabel, CenterRightPanelAddressAndPlaceValue, CenterRightPanelTopLabel;
    JLabel BottomLeftPanelCustomerDetailsLabel, p8_l2, p8_l3, p8_l4, BottomLeftPanelNameLabel, BottomLeftPanelNameValue, BottomLeftPanelAddressLabel, BottomLeftPanelAddressValue, BottomLeftPanelCityLabel, BottomLeftPanelCityValue, BottomLeftPanelCountryLabel, BottomLeftPanelCountryValue, BottomLeftPanelEmailLabel;
    JLabel BottomLeftPanelEmailValue, BottomLeftPanelPhoneLabel, BottomLeftPanelPhoneValue, BottomLeftPanelMeterNoLabel, BottomLeftPanelMeterNoValue, BottomLeftPanelTaxTypeLabel, BottomLeftPanelTaxTypeValue, p8_l21, p8_l22, BottomLeftPanelReadDateLabel, BottomLeftPanelReadDateValue, BottomLeftPanelIssueDateLabel, BottomLeftPanelIssueDateValue, BottomLeftPanelDeadLineDateDateLabel, BottomLeftPanelDeadLineDateDateValue, BottomLeftPanelPaymentDateLabel, BottomLeftPanelPaymentDateLabelValue;
    JLabel BottomRightPanelElectricityLabel, BottomRightPanelElectricityValue, p9_lTop, p9_l3, p9_l4, BottomRightPanelItemTypeLabel, BottomRightPanelQuentityLabel, BottomRightPanelPriceLabel, BottomRightPanelAmountLabel, BottomRightPanelEnergyOffPeakLabel, BottomRightPanelEnergyOffPeakQuantityValue, BottomRightPanelEnergyOffPeakPriceValue;
    JLabel BottomRightPanelEnergyOffPeakAmountValue, BottomRightPanelEnergyOnPeakLabel, BottomRightPanelEnergyOnPeakQuantityValue, BottomRightPanelEnergyOnPeakPriceValue, BottomRightPanelEnergyOnPeakAmountValue, BottomRightPanelMeterRentLabel, BottomRightPanelPriceValue, p9_l19, BottomRightPanelMeterRentAmountValue, BottomRightPanelServiceRentLabel, p9_l22, p9_l23, BottomRightPanelServiceRentAmountValue,
            BottomRightPanelTierRateLabel, p9_l26, p9_l27, BottomRightPanelTierRateAmountValue;
    JLabel BottomRightLowerPanelAmountWithoutTaxLabel, BottomRightLowerPanelAmountWithoutTaxValue, BottomRightLowerPanelTaxLabel, BottomRightLowerPanelTaxValue, BottomRightLowerPanelTotalLabel, BottomRightLowerPanelTotalValue;


    JLabel TopPanelFindCustomerLabel, controlPanel_TopPanel_CustomerIdLabel, controlPanel_TopPanel_CustomerNameLabel;
    JButton findButton;
    JTextField controlPanel_TopPanel_CustomerIdTextField, controlPanel_TopPanel_CustomerNameTextField;
    JLabel CenterPanelCustomerDetailsLabel, CenterPanelCustomerDetailsValue, CenterPanelCustomerNameLabel, CenterPanelCustomerNameValue, CenterPanelCustomerAddressLabel, CenterPanelCustomerAddressValue, CenterPanelCustomerCityLabel, CenterPanelCustomerCityValue, CenterPanelCustomerCountryLabel, CenterPanelCustomerCountryValue, CenterPanelCustomerEmailLabel, CenterPanelCustomerEmailValue,
            CenterPanelCustomerPhoneLabel, CenterPanelCustomerPhoneValue, CenterPanelCustomerContractNoLabel, CenterPanelCustomerContractNoValue, CenterPanelCustomerMeterNoLabel, CenterPanelCustomerMeterNoValue, CenterPanelCustomerDebtBalanceLabel, CenterPanelCustomerDebtBalanceValue;
    JButton payBillButton, payAmountButton, setBillButton, printBillButton, genBillButton, exitButton, b7, b8;
    JButton monthChoiceLeftBtn, monthChoiceRightBtn;
    int i = 0;

    int tempUserID;
    int tempCustID;


    public Gui_Bill(int id) {
        super("Generate Bill");
        this.tempUserID = id;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //billPanel = new JPanel();
        billPanelHeaderLabel1 = new JPanel();
        billPanel_TopLeftPanel = new JPanel();
        billPanel_TopRightPanel = new JPanel();
        billPanel_CenterLeftTopPanel = new JPanel();
        billPanel_CenterLeftBottomPanel = new JPanel();
        billPanel_CenterCenterPanel = new JPanel();
        billPanel_CenterRightPanel = new JPanel();
        billPanel_BottomLeftPanel = new JPanel();
        billPanel_BottomRightPanel = new JPanel();

//---------------------------------------------------------------------
        //____title_______
        titleLabel = new JLabel("ELECTRICITY  BILL", JLabel.CENTER);
        titleLabel.setSize(300, 20);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setLocation(320, 10);
        titleLabel.setBackground(Color.WHITE);


        //billPanel-header
        billPanelHeaderLabel1.setSize(960, 80);
        billPanelHeaderLabel1.setBorder(new RoundedBorder(10));
        billPanelHeaderLabel1.setBackground(Color.WHITE);
        billPanelHeaderLabel1.setLocation(20, 40);
        billPanelHeaderLabel1.setLayout(new GridLayout(1, 6, 10, 0));

        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("images/green-logo4.jpg"));
        Image image1 = icon1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        billPanelHeaderLabel2 = new JLabel(new ImageIcon(image1), JLabel.CENTER);
        billPanelHeaderLabel2.setBackground(new Color(160, 227, 160));

        billPanelHeaderLabel3 = new JLabel("<html>ELECTRICITY<br>COMPANY</html>", JLabel.LEFT);
        billPanelHeaderLabel3.setFont(new Font("Times New Roman", Font.BOLD, 13));
        billPanelHeaderLabel3.setHorizontalTextPosition(SwingConstants.LEFT);


        billPanelHeaderLabel4 = new JLabel("<html>------------------------<br> Date of payment</html>", JLabel.LEFT);
        billPanelHeaderLabel5 = new JLabel("<html>  </html>");
        billPanelHeaderLabel6 = new JLabel("<html>________________________<br>Signature of the principal</html>", JLabel.LEFT);


        billPanelHeaderLabel7 = new JLabel("<html>ELECTRICITY<br>COMPANY</html>", JLabel.RIGHT);
        billPanelHeaderLabel7.setFont(new Font("Times New Roman", Font.BOLD, 13));

        billPanelHeaderLabel8 = new JLabel(new ImageIcon(image1), JLabel.CENTER);

        billPanelHeaderLabel1.add(billPanelHeaderLabel2);
        billPanelHeaderLabel1.add(billPanelHeaderLabel3);
        billPanelHeaderLabel1.add(billPanelHeaderLabel4);
        billPanelHeaderLabel1.add(billPanelHeaderLabel6);
        billPanelHeaderLabel1.add(billPanelHeaderLabel7);
        billPanelHeaderLabel1.add(billPanelHeaderLabel8);

//-----------------------------------------------------------------------------------------------------
        //Panels Inset Labels
        billPanelInsetLabel1 = new JLabel("BASE");
        billPanelInsetLabel1.setFont(new Font("Times New Roman", Font.BOLD, 16));
        billPanelInsetLabel1.setLocation(50, 120);
        billPanelInsetLabel1.setSize(100, 20);

        billPanelInsetLabel2 = new JLabel("BASE");
        billPanelInsetLabel2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        billPanelInsetLabel2.setLocation(700, 120);
        billPanelInsetLabel2.setSize(100, 20);

//------------------------------------------------------------------------------------------------------
        //billPanel Top-Left
        billPanel_TopLeftPanel.setSize(640, 40);
        billPanel_TopLeftPanel.setBorder(new RoundedBorder(10));
        billPanel_TopLeftPanel.setBackground(Color.WHITE);
        billPanel_TopLeftPanel.setLocation(20, 140);
        billPanel_TopLeftPanel.setLayout(new GridLayout(1, 4, 10, 0));

        TopLeftPanelIssueDate = new JLabel("Issue Date: ", JLabel.LEFT);
        TopLeftPanelIssueDate.setFont(new Font("Times New Roman", Font.BOLD, 16));
        billPanel_TopLeftPanel.add(TopLeftPanelIssueDate);


        TopLeftPanelIssueDateValue = new JLabel(formattedDate, JLabel.CENTER);
        TopLeftPanelIssueDateValue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        TopLeftPanelIssueDateValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_TopLeftPanel.add(TopLeftPanelIssueDateValue);

        TopLeftPanelForMonth = new JLabel("For month: ", JLabel.RIGHT);
        TopLeftPanelForMonth.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        TopLeftPanelForMonth.setBorder(new LineBorder(Color.lightGray));
        billPanel_TopLeftPanel.add(TopLeftPanelForMonth);

        TopLeftPanelForMonthValue = new JLabel("", JLabel.CENTER);
        TopLeftPanelForMonthValue.setFont(new Font("Times New Roman", Font.BOLD, 16));
        TopLeftPanelForMonthValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_TopLeftPanel.add(TopLeftPanelForMonthValue);


//--------------------------------------------------------------------------------------------------------
        //billPanel Top-Right
        billPanel_TopRightPanel.setSize(300, 40);
        billPanel_TopRightPanel.setBorder(new RoundedBorder(10));
        billPanel_TopRightPanel.setBackground(Color.WHITE);
        billPanel_TopRightPanel.setLocation(680, 140);
        billPanel_TopRightPanel.setLayout(new GridLayout(1, 2, 0, 0));

        TopRightPanelIssueDateLabel = new JLabel("Issue Date: ", JLabel.LEFT);
        TopRightPanelIssueDateLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billPanel_TopRightPanel.add(TopRightPanelIssueDateLabel);

        //Date
        TopRightPanelIssueDateValue = new JLabel(formattedDate, JLabel.CENTER);
        TopRightPanelIssueDateValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        TopRightPanelIssueDateValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_TopRightPanel.add(TopRightPanelIssueDateValue);

        TopRightPanelForMonthLabel = new JLabel("For month: ", JLabel.LEFT);
        TopRightPanelForMonthLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        billPanel_TopRightPanel.add(TopRightPanelForMonthLabel);

        //Date
        TopRightPanelDateValue = new JLabel("", JLabel.CENTER);
        TopRightPanelDateValue.setFont(new Font("Times New Roman", Font.BOLD, 12));
        TopRightPanelDateValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_TopRightPanel.add(TopRightPanelDateValue);

        //----------------------------------------------------------------------------------

        //billPanel Center-Inset-Labels
        billPanel_CenterInsetPrincipalLabel = new JLabel("PRINCIPAL", JLabel.LEFT);
        billPanel_CenterInsetPrincipalLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        billPanel_CenterInsetPrincipalLabel.setLocation(50, 180);
        billPanel_CenterInsetPrincipalLabel.setSize(100, 20);

        billPanel_CenterInsetReceiverLabel = new JLabel("RECEIVER", JLabel.LEFT);
        billPanel_CenterInsetReceiverLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        billPanel_CenterInsetReceiverLabel.setLocation(380, 180);
        billPanel_CenterInsetReceiverLabel.setSize(100, 20);

        //-----------------------------------------------------------------------------------

        //billPanel Center-Left-Top
        billPanel_CenterLeftTopPanel.setSize(300, 140);
        billPanel_CenterLeftTopPanel.setBorder(new RoundedBorder(10));
        billPanel_CenterLeftTopPanel.setBackground(Color.WHITE);
        billPanel_CenterLeftTopPanel.setLocation(20, 200);
        billPanel_CenterLeftTopPanel.setLayout(new GridLayout(4, 1, 4, 0));

        CenterLeftTopPanelNameLabel = new JLabel("Name: ", JLabel.LEFT);
        CenterLeftTopPanelNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        billPanel_CenterLeftTopPanel.add(CenterLeftTopPanelNameLabel);
        //Name
        CenterLeftTopPanelNameValue = new JLabel("--", JLabel.LEFT);
        CenterLeftTopPanelNameValue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        setSize(100, 20);
        CenterLeftTopPanelNameValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_CenterLeftTopPanel.add(CenterLeftTopPanelNameValue);

        CenterLeftTopPanelAdressLabel = new JLabel("Address: ", JLabel.LEFT);
        CenterLeftTopPanelAdressLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        billPanel_CenterLeftTopPanel.add(CenterLeftTopPanelAdressLabel);
        //Address
        CenterLeftTopPanelAddressValue = new JLabel("--", JLabel.LEFT);
        CenterLeftTopPanelAddressValue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        setSize(100, 20);
        CenterLeftTopPanelAddressValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_CenterLeftTopPanel.add(CenterLeftTopPanelAddressValue);

//--------------------------------------------------------------------------------------------
        //billPanel Center-Left-Bottom
        billPanel_CenterLeftBottomPanel.setSize(300, 100);
        billPanel_CenterLeftBottomPanel.setBorder(new RoundedBorder(10));
        billPanel_CenterLeftBottomPanel.setLayout(new GridLayout(2, 2, 4, 0));
        billPanel_CenterLeftBottomPanel.setBackground(Color.WHITE);
        billPanel_CenterLeftBottomPanel.setLocation(20, 360);

        CenterLeftBottomPanelBank1Label = new JLabel("BANK1", JLabel.LEFT);
        CenterLeftBottomPanelBank1Label.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billPanel_CenterLeftBottomPanel.add(CenterLeftBottomPanelBank1Label);

        CenterLeftBottomPanelBank1Value = new JLabel("XXXXXXXXXXXX1", JLabel.LEFT);
        CenterLeftBottomPanelBank1Value.setBorder(new LineBorder(Color.lightGray));
        CenterLeftBottomPanelBank1Value.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billPanel_CenterLeftBottomPanel.add(CenterLeftBottomPanelBank1Value);

        CenterLeftBottomPanelBank2Label = new JLabel("BANK2", JLabel.LEFT);
        CenterLeftBottomPanelBank2Label.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billPanel_CenterLeftBottomPanel.add(CenterLeftBottomPanelBank2Label);

        CenterLeftBottomPanelBank2Value = new JLabel("XXXXXXXXXXXX2", JLabel.LEFT);
        CenterLeftBottomPanelBank2Value.setBorder(new LineBorder(Color.lightGray));
        CenterLeftBottomPanelBank2Value.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billPanel_CenterLeftBottomPanel.add(CenterLeftBottomPanelBank2Value);

        //------------------------------------------------------------------------------

        //billpanel Center-Center-Panel
        billPanel_CenterCenterPanel.setSize(320, 260);
        billPanel_CenterCenterPanel.setBorder(new RoundedBorder(10));
        billPanel_CenterCenterPanel.setBackground(Color.WHITE);
        billPanel_CenterCenterPanel.setLayout(new GridLayout(10, 1, 0, 0));
        billPanel_CenterCenterPanel.setLocation(340, 200);

        CenterCenterPanelTransactionAccountNoLabel = new JLabel("Transaction account number:", JLabel.LEFT);
        CenterCenterPanelTransactionAccountNoLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billPanel_CenterCenterPanel.add(CenterCenterPanelTransactionAccountNoLabel);
        //Account number
        CenterCenterPanelTransactionAccountNoValue = new JLabel("0000000000001", JLabel.LEFT);
        CenterCenterPanelTransactionAccountNoValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        CenterCenterPanelTransactionAccountNoValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_CenterCenterPanel.add(CenterCenterPanelTransactionAccountNoValue);

        CenterCenterPanelReferenceNumberLabel = new JLabel("Reference number:", JLabel.LEFT);
        CenterCenterPanelReferenceNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billPanel_CenterCenterPanel.add(CenterCenterPanelReferenceNumberLabel);

        CenterCenterPanelReferenceNumberLabelValue = new JLabel("--", JLabel.LEFT);
        CenterCenterPanelReferenceNumberLabelValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        CenterCenterPanelReferenceNumberLabelValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_CenterCenterPanel.add(CenterCenterPanelReferenceNumberLabelValue);

        CenterCenterPanelAmountLabel = new JLabel("Amount:", JLabel.LEFT);
        CenterCenterPanelReferenceNumberLabelValue.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billPanel_CenterCenterPanel.add(CenterCenterPanelAmountLabel);

        CenterCenterPanelAmountValue = new JLabel("00,00 KM", JLabel.LEFT);
        CenterCenterPanelAmountValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        CenterCenterPanelAmountValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_CenterCenterPanel.add(CenterCenterPanelAmountValue);

        CenterCenterPanelAreaOfSupplyLabel = new JLabel("Area of supply: ", JLabel.LEFT);
        CenterCenterPanelAreaOfSupplyLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billPanel_CenterCenterPanel.add(CenterCenterPanelAreaOfSupplyLabel);

        CenterCenterPanelAreaOfSupplyValue = new JLabel("--", JLabel.LEFT);
        CenterCenterPanelAreaOfSupplyValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        CenterCenterPanelAreaOfSupplyValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_CenterCenterPanel.add(CenterCenterPanelAreaOfSupplyValue);

        CenterCenterPanelAddressAndPlaceLabel = new JLabel("Address and Place: ", JLabel.LEFT);
        CenterCenterPanelAddressAndPlaceLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billPanel_CenterCenterPanel.add(CenterCenterPanelAddressAndPlaceLabel);

        CenterCenterPanelAddressAndPlaceValue = new JLabel("-- + --", JLabel.LEFT);
        CenterCenterPanelAddressAndPlaceValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        CenterCenterPanelAddressAndPlaceValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_CenterCenterPanel.add(CenterCenterPanelAddressAndPlaceValue);

        //-----------------------------------------------------------------------------------

        //billPanel Center-Right-Panel
        billPanel_CenterRightPanel.setSize(300, 260);
        billPanel_CenterRightPanel.setBorder(new RoundedBorder(15));
        billPanel_CenterRightPanel.setLayout(new GridLayout(9, 1, 0, 0));
        billPanel_CenterRightPanel.setBackground(Color.WHITE);
        billPanel_CenterRightPanel.setLocation(680, 200);

        CenterRightPanelAmountLabel = new JLabel("AMOUNT:", JLabel.LEFT);
        CenterRightPanelAmountLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        CenterRightPanelAmountValue = new JLabel("--", JLabel.CENTER);
        CenterRightPanelAmountValue.setFont(new Font("Times New Roman", Font.BOLD, 20));
        CenterRightPanelAmountValue.setBorder(new LineBorder(Color.lightGray));

        CenterRightPanelTopLabel = new JLabel();
        CenterRightPanelTopLabel.setLayout(new GridLayout(1, 2));

        CenterRightPanelTopLabel.add(CenterRightPanelAmountLabel);
        CenterRightPanelTopLabel.add(CenterRightPanelAmountValue);
        billPanel_CenterRightPanel.add(CenterRightPanelTopLabel);


        CenterRightPanelReferenceNoLabel = new JLabel("Reference number", JLabel.LEFT);
        CenterRightPanelReferenceNoLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_CenterRightPanel.add(CenterRightPanelReferenceNoLabel);

        CenterRightPanelReferenceNoValue = new JLabel("--", JLabel.LEFT);
        CenterRightPanelReferenceNoValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        CenterRightPanelReferenceNoValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_CenterRightPanel.add(CenterRightPanelReferenceNoValue);

        CenterRightPanelNameLabel = new JLabel("Name: ", JLabel.LEFT);
        CenterRightPanelNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_CenterRightPanel.add(CenterRightPanelNameLabel);

        CenterRightPanelNameValue = new JLabel("--", JLabel.LEFT);
        CenterRightPanelNameValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        CenterRightPanelNameValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_CenterRightPanel.add(CenterRightPanelNameValue);

        CenterRightPanelAddressLabel = new JLabel("Address: ", JLabel.LEFT);
        CenterRightPanelAddressLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_CenterRightPanel.add(CenterRightPanelAddressLabel);

        CenterRightPanelAddressValue = new JLabel("--", JLabel.LEFT);
        CenterRightPanelAddressValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        CenterRightPanelAddressValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_CenterRightPanel.add(CenterRightPanelAddressValue);

        CenterRightPanelAddressAndPlaceLabel = new JLabel("Address and place:", JLabel.LEFT);
        CenterRightPanelAddressAndPlaceLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_CenterRightPanel.add(CenterRightPanelAddressAndPlaceLabel);

        CenterRightPanelAddressAndPlaceValue = new JLabel("--", JLabel.LEFT);
        CenterRightPanelAddressAndPlaceValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        CenterRightPanelAddressAndPlaceValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_CenterRightPanel.add(CenterRightPanelAddressAndPlaceValue);

        //-----------------------------------------------------------------------------------------------

        //billPanel Bottom-left
        billPanel_BottomLeftPanel.setSize(470, 270);
        billPanel_BottomLeftPanel.setBorder(new RoundedBorder(15));
        billPanel_BottomLeftPanel.setLayout(new GridLayout(15, 2, 0, 0));
        billPanel_BottomLeftPanel.setBackground(Color.WHITE);
        billPanel_BottomLeftPanel.setLocation(20, 510);

        BottomLeftPanelCustomerDetailsLabel = new JLabel("Customer Details", JLabel.RIGHT);
        BottomLeftPanelCustomerDetailsLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        billPanel_BottomLeftPanel.add(BottomLeftPanelCustomerDetailsLabel);

        p8_l2 = new JLabel();//empty
        billPanel_BottomLeftPanel.add(p8_l2);//empty

        p8_l3 = new JLabel();//empty
        billPanel_BottomLeftPanel.add(p8_l3);

        p8_l4 = new JLabel();//
        billPanel_BottomLeftPanel.add(p8_l4);

        BottomLeftPanelNameLabel = new JLabel("Name:", JLabel.LEFT);
        BottomLeftPanelNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(BottomLeftPanelNameLabel);

        BottomLeftPanelNameValue = new JLabel("--");
        BottomLeftPanelNameValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomLeftPanelNameValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomLeftPanel.add(BottomLeftPanelNameValue);

        BottomLeftPanelAddressLabel = new JLabel("Address:", JLabel.LEFT);
        BottomLeftPanelAddressLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(BottomLeftPanelAddressLabel);

        BottomLeftPanelAddressValue = new JLabel("--");
        BottomLeftPanelAddressValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomLeftPanelAddressValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomLeftPanel.add(BottomLeftPanelAddressValue);

        BottomLeftPanelCityLabel = new JLabel("City:", JLabel.LEFT);
        BottomLeftPanelCityLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(BottomLeftPanelCityLabel);

        BottomLeftPanelCityValue = new JLabel("--");
        BottomLeftPanelCityValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomLeftPanelCityValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomLeftPanel.add(BottomLeftPanelCityValue);

        BottomLeftPanelCountryLabel = new JLabel("Country:", JLabel.LEFT);
        BottomLeftPanelCountryLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(BottomLeftPanelCountryLabel);

        BottomLeftPanelCountryValue = new JLabel("--");
        BottomLeftPanelCountryValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomLeftPanelCountryValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomLeftPanel.add(BottomLeftPanelCountryValue);

        BottomLeftPanelEmailLabel = new JLabel("Email:", JLabel.LEFT);
        BottomLeftPanelEmailLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(BottomLeftPanelEmailLabel);

        BottomLeftPanelEmailValue = new JLabel("--");
        BottomLeftPanelEmailValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomLeftPanelEmailValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomLeftPanel.add(BottomLeftPanelEmailValue);

        BottomLeftPanelPhoneLabel = new JLabel("Phone:", JLabel.LEFT);
        BottomLeftPanelPhoneLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(BottomLeftPanelPhoneLabel);

        BottomLeftPanelPhoneValue = new JLabel("--");
        BottomLeftPanelPhoneValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomLeftPanelPhoneValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomLeftPanel.add(BottomLeftPanelPhoneValue);

        BottomLeftPanelMeterNoLabel = new JLabel("Meter No:", JLabel.LEFT);
        BottomLeftPanelMeterNoLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(BottomLeftPanelMeterNoLabel);

        BottomLeftPanelMeterNoValue = new JLabel("--");
        BottomLeftPanelMeterNoValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomLeftPanelMeterNoValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomLeftPanel.add(BottomLeftPanelMeterNoValue);

        BottomLeftPanelTaxTypeLabel = new JLabel("Tax type:", JLabel.LEFT);
        BottomLeftPanelTaxTypeLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(BottomLeftPanelTaxTypeLabel);

        BottomLeftPanelTaxTypeValue = new JLabel("--");
        BottomLeftPanelTaxTypeValue.setFont(new Font("Arial", Font.PLAIN, 14));
        BottomLeftPanelTaxTypeValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomLeftPanel.add(BottomLeftPanelTaxTypeValue);

        p8_l21 = new JLabel("", JLabel.LEFT);//empty
        //p8_l21.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(p8_l21);

        p8_l22 = new JLabel("");//empty
        p8_l22.setFont(new Font("Arial", Font.PLAIN, 14));
        //p8_l22.setBorder(new LineBorder(Color.RED));
        billPanel_BottomLeftPanel.add(p8_l22);

        BottomLeftPanelReadDateLabel = new JLabel("Read Date:", JLabel.LEFT);
        BottomLeftPanelReadDateLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(BottomLeftPanelReadDateLabel);

        BottomLeftPanelReadDateValue = new JLabel("--");
        BottomLeftPanelReadDateValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomLeftPanelReadDateValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomLeftPanel.add(BottomLeftPanelReadDateValue);

        BottomLeftPanelIssueDateLabel = new JLabel("Issue Date:", JLabel.LEFT);
        BottomLeftPanelIssueDateLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(BottomLeftPanelIssueDateLabel);

        BottomLeftPanelIssueDateValue = new JLabel("--");
        BottomLeftPanelIssueDateValue.setFont(new Font("Arial", Font.PLAIN, 14));
        BottomLeftPanelIssueDateValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomLeftPanel.add(BottomLeftPanelIssueDateValue);

        BottomLeftPanelDeadLineDateDateLabel = new JLabel("Dedline Date:", JLabel.LEFT);
        BottomLeftPanelDeadLineDateDateLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(BottomLeftPanelDeadLineDateDateLabel);

        BottomLeftPanelDeadLineDateDateValue = new JLabel("--");
        BottomLeftPanelDeadLineDateDateValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomLeftPanelDeadLineDateDateValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomLeftPanel.add(BottomLeftPanelDeadLineDateDateValue);

        BottomLeftPanelPaymentDateLabel = new JLabel("Payment Date:", JLabel.LEFT);
        BottomLeftPanelPaymentDateLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomLeftPanel.add(BottomLeftPanelPaymentDateLabel);

        BottomLeftPanelPaymentDateLabelValue = new JLabel("--");
        BottomLeftPanelPaymentDateLabelValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomLeftPanelPaymentDateLabelValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomLeftPanel.add(BottomLeftPanelPaymentDateLabelValue);


//----------------------------------------------------------------------------------------------------

        //billPanel Bottom Right Panel
        billPanel_BottomRightPanel.setSize(470, 175);
        billPanel_BottomRightPanel.setBorder(new RoundedBorder(10));
        billPanel_BottomRightPanel.setLayout(new GridLayout(7, 4));
        billPanel_BottomRightPanel.setBackground(Color.WHITE);
        billPanel_BottomRightPanel.setLocation(510, 510);

        BottomRightPanelElectricityLabel = new JLabel("Electricity", JLabel.CENTER);
        BottomRightPanelElectricityLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        billPanel_BottomRightPanel.add(BottomRightPanelElectricityLabel);

        BottomRightPanelElectricityValue = new JLabel();
        //p9_l2.setBorder(new LineBorder(Color.GRAY));
        billPanel_BottomRightPanel.add(BottomRightPanelElectricityValue);

        p9_l3 = new JLabel();//empty
        // p9_l3.setBorder(new LineBorder(Color.GRAY));
        billPanel_BottomRightPanel.add(p9_l3);

        p9_l4 = new JLabel();//empty
        // p9_l4.setBorder(new LineBorder(Color.GRAY));
        billPanel_BottomRightPanel.add(p9_l4);
        //------------------------

        BottomRightPanelItemTypeLabel = new JLabel("Item type", JLabel.LEFT);
        BottomRightPanelItemTypeLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        billPanel_BottomRightPanel.add(BottomRightPanelItemTypeLabel);

        BottomRightPanelQuentityLabel = new JLabel("Quantity", JLabel.LEFT);
        BottomRightPanelQuentityLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        billPanel_BottomRightPanel.add(BottomRightPanelQuentityLabel);
        //------------------------
        BottomRightPanelPriceLabel = new JLabel("Price (KM):", JLabel.LEFT);
        BottomRightPanelPriceLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        billPanel_BottomRightPanel.add(BottomRightPanelPriceLabel);

        BottomRightPanelAmountLabel = new JLabel("Amount (KM)", JLabel.LEFT);
        BottomRightPanelAmountLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        billPanel_BottomRightPanel.add(BottomRightPanelAmountLabel);

        BottomRightPanelEnergyOffPeakLabel = new JLabel("Energy off-peak", JLabel.LEFT);
        BottomRightPanelEnergyOffPeakLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomRightPanel.add(BottomRightPanelEnergyOffPeakLabel);

        BottomRightPanelEnergyOffPeakQuantityValue = new JLabel("--", JLabel.LEFT);
        BottomRightPanelEnergyOffPeakQuantityValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomRightPanelEnergyOffPeakQuantityValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(BottomRightPanelEnergyOffPeakQuantityValue);

        BottomRightPanelEnergyOffPeakPriceValue = new JLabel("--", JLabel.LEFT);
        BottomRightPanelEnergyOffPeakPriceValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomRightPanelEnergyOffPeakPriceValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(BottomRightPanelEnergyOffPeakPriceValue);

        BottomRightPanelEnergyOffPeakAmountValue = new JLabel("--", JLabel.LEFT);
        BottomRightPanelEnergyOffPeakAmountValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomRightPanelEnergyOffPeakAmountValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(BottomRightPanelEnergyOffPeakAmountValue);
//-----------------------------
        BottomRightPanelEnergyOnPeakLabel = new JLabel("Energy on-peak", JLabel.LEFT);
        BottomRightPanelEnergyOnPeakLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomRightPanel.add(BottomRightPanelEnergyOnPeakLabel);

        BottomRightPanelEnergyOnPeakQuantityValue = new JLabel("--", JLabel.LEFT);
        BottomRightPanelEnergyOnPeakQuantityValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomRightPanelEnergyOnPeakQuantityValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(BottomRightPanelEnergyOnPeakQuantityValue);

        BottomRightPanelEnergyOnPeakPriceValue = new JLabel("--", JLabel.LEFT);
        BottomRightPanelEnergyOnPeakPriceValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomRightPanelEnergyOnPeakPriceValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(BottomRightPanelEnergyOnPeakPriceValue);

        BottomRightPanelEnergyOnPeakAmountValue = new JLabel("--", JLabel.LEFT);
        BottomRightPanelEnergyOnPeakAmountValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomRightPanelEnergyOnPeakAmountValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(BottomRightPanelEnergyOnPeakAmountValue);
//-------------------------
        BottomRightPanelMeterRentLabel = new JLabel("Meter rent", JLabel.LEFT);
        BottomRightPanelMeterRentLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomRightPanel.add(BottomRightPanelMeterRentLabel);

        BottomRightPanelPriceValue = new JLabel();//empty
        BottomRightPanelPriceValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(BottomRightPanelPriceValue);

        p9_l19 = new JLabel();//empty
        p9_l19.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(p9_l19);

        BottomRightPanelMeterRentAmountValue = new JLabel("--", JLabel.LEFT);
        BottomRightPanelMeterRentAmountValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomRightPanelMeterRentAmountValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(BottomRightPanelMeterRentAmountValue);
//-----------------------------
        BottomRightPanelServiceRentLabel = new JLabel("Service rent", JLabel.LEFT);
        BottomRightPanelServiceRentLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomRightPanel.add(BottomRightPanelServiceRentLabel);

        p9_l22 = new JLabel();//empty
        p9_l22.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(p9_l22);

        p9_l23 = new JLabel();//empty
        p9_l23.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(p9_l23);

        BottomRightPanelServiceRentAmountValue = new JLabel("--", JLabel.LEFT);
        BottomRightPanelServiceRentAmountValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomRightPanelServiceRentAmountValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(BottomRightPanelServiceRentAmountValue);
//---------------------------------------
        BottomRightPanelTierRateLabel = new JLabel("Tier rate", JLabel.LEFT);
        BottomRightPanelTierRateLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomRightPanel.add(BottomRightPanelTierRateLabel);

        p9_l26 = new JLabel("");//empty
        p9_l26.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(p9_l26);

        p9_l27 = new JLabel();//empty
        p9_l27.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(p9_l27);

        BottomRightPanelTierRateAmountValue = new JLabel("--", JLabel.LEFT);
        BottomRightPanelTierRateAmountValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomRightPanelTierRateAmountValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightPanel.add(BottomRightPanelTierRateAmountValue);


        //----------------------------------------------------------------------------

        //billPanel Bottom-Right (p9_br)
        billPanel_BottomRightLowerPanel = new JPanel();
        billPanel_BottomRightLowerPanel.setLayout(new GridLayout(3, 2));
        billPanel_BottomRightLowerPanel.setSize(300, 90);
        billPanel_BottomRightLowerPanel.setBorder(new RoundedBorder(10));
        billPanel_BottomRightLowerPanel.setBackground(Color.WHITE);
        billPanel_BottomRightLowerPanel.setLocation(680, 690);

        BottomRightLowerPanelAmountWithoutTaxLabel = new JLabel("Amount without tax:", JLabel.LEFT);
        BottomRightLowerPanelAmountWithoutTaxLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomRightLowerPanel.add(BottomRightLowerPanelAmountWithoutTaxLabel);

        BottomRightLowerPanelAmountWithoutTaxValue = new JLabel("--", JLabel.LEFT);
        BottomRightLowerPanelAmountWithoutTaxValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomRightLowerPanelAmountWithoutTaxValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightLowerPanel.add(BottomRightLowerPanelAmountWithoutTaxValue);
//----------------
        BottomRightLowerPanelTaxLabel = new JLabel("Tax 17.00%:", JLabel.LEFT);
        BottomRightLowerPanelTaxLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billPanel_BottomRightLowerPanel.add(BottomRightLowerPanelTaxLabel);

        BottomRightLowerPanelTaxValue = new JLabel("--", JLabel.LEFT);
        BottomRightLowerPanelTaxValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        BottomRightLowerPanelTaxValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightLowerPanel.add(BottomRightLowerPanelTaxValue);

        //------------------------
        BottomRightLowerPanelTotalLabel = new JLabel("Total :", JLabel.LEFT);
        BottomRightLowerPanelTotalLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        billPanel_BottomRightLowerPanel.add(BottomRightLowerPanelTotalLabel);

        BottomRightLowerPanelTotalValue = new JLabel("--", JLabel.LEFT);
        BottomRightLowerPanelTotalValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
        BottomRightLowerPanelTotalValue.setBorder(new LineBorder(Color.lightGray));
        billPanel_BottomRightLowerPanel.add(BottomRightLowerPanelTotalValue);
//-----------------------------
        /*p9_br_l5 = new JLabel("Amount with tax:", JLabel.LEFT);
        p9_br_l5.setFont(new Font("Times New Roman", Font.BOLD, 14));
        p9_br.add(p9_br_l5);

        p9_br_l6 = new JLabel("--", JLabel.LEFT);
        p9_br_l6.setFont(new Font("Times New Roman", Font.BOLD, 20));
        p9_br_l6.setBorder(new LineBorder(Color.lightGray));
        p9_br.add(p9_br_l6);

         */
//-----------------------------------------------------------------------------------------


        //-----------------------------------------------------------------------------------------
        billPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(160, 227, 160));
                g.fillRoundRect(0, 0, 1000, 800, 50, 50);
                g.setColor(Color.lightGray);
                g.drawLine(30, 490, 970, 490);

            }
        };

        //billPanel.setBackground(new Color(144, 238, 144));
        // billPanel.setBorder(new RoundedBorder(10));
        billPanel.setSize(1000, 800);
        billPanel.setLocation(20, 20);
        billPanel.setLayout(null);
//-----------------------------------------------------------------------------------
        //controlPanel-TopPanel
        controlPanel_TopPanel = new JPanel();
        controlPanel_TopPanel.setLayout(new GridLayout(3, 2, 0, 10));
        controlPanel_TopPanel.setSize(460, 120);
        controlPanel_TopPanel.setLocation(20, 20);
        // p10.setBackground(new Color(210, 232, 210));

        TopPanelFindCustomerLabel = new JLabel("Find Customer", JLabel.CENTER);
        TopPanelFindCustomerLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        TopPanelFindCustomerLabel.setSize(150, 20);
        controlPanel_TopPanel.add(TopPanelFindCustomerLabel);


        findButton = new JButton("Find");
        findButton.setBackground(new Color(50, 205, 50));
        findButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        findButton.setSize(100, 20);
        findButton.addActionListener(this);
        controlPanel_TopPanel.add(findButton);

        controlPanel_TopPanel_CustomerIdLabel = new JLabel("Customer Id: ", JLabel.CENTER);
        controlPanel_TopPanel_CustomerIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        controlPanel_TopPanel_CustomerIdLabel.setSize(150, 20);
        controlPanel_TopPanel.add(controlPanel_TopPanel_CustomerIdLabel);

        controlPanel_TopPanel_CustomerIdTextField = new JTextField();
        controlPanel_TopPanel_CustomerIdTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        controlPanel_TopPanel_CustomerIdTextField.setSize(150, 20);
        controlPanel_TopPanel_CustomerIdTextField.addMouseListener(this);
        controlPanel_TopPanel.add(controlPanel_TopPanel_CustomerIdTextField);

        controlPanel_TopPanel_CustomerNameLabel = new JLabel("Customer name: ", JLabel.CENTER);
        controlPanel_TopPanel_CustomerNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        controlPanel_TopPanel_CustomerNameLabel.setSize(150, 20);
        controlPanel_TopPanel.add(controlPanel_TopPanel_CustomerNameLabel);

        controlPanel_TopPanel_CustomerNameTextField = new JTextField();
        controlPanel_TopPanel_CustomerNameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        controlPanel_TopPanel_CustomerNameTextField.addMouseListener(this);

        controlPanel_TopPanel.add(controlPanel_TopPanel_CustomerNameTextField);

//-----------------------------------------------------------------------------------
        //controlPanel-CenterPanel
        controlPanel_CenterPanel = new JPanel();
        controlPanel_CenterPanel.setLayout(new GridLayout(10, 2));
        controlPanel_CenterPanel.setSize(460, 300);
        controlPanel_CenterPanel.setLocation(20, 160);
        // p11.setBackground(new Color(210, 232, 210));

        CenterPanelCustomerDetailsLabel = new JLabel("Customer details", JLabel.CENTER);
        CenterPanelCustomerDetailsLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        // p11_l1.setSize(230,20);
        controlPanel_CenterPanel.add(CenterPanelCustomerDetailsLabel);

        CenterPanelCustomerDetailsValue = new JLabel("", JLabel.RIGHT);
        controlPanel_CenterPanel.add(CenterPanelCustomerDetailsValue);
        //------------------------------

        CenterPanelCustomerNameLabel = new JLabel("Name: ", JLabel.CENTER);
        CenterPanelCustomerNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel_CenterPanel.add(CenterPanelCustomerNameLabel);

        CenterPanelCustomerNameValue = new JLabel("", JLabel.LEFT);
        CenterPanelCustomerNameValue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        CenterPanelCustomerNameValue.setBorder(new LineBorder(Color.lightGray));
        controlPanel_CenterPanel.add(CenterPanelCustomerNameValue);
        //--------------------------------
        CenterPanelCustomerAddressLabel = new JLabel("Address: ", JLabel.CENTER);
        CenterPanelCustomerAddressLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel_CenterPanel.add(CenterPanelCustomerAddressLabel);

        CenterPanelCustomerAddressValue = new JLabel("", JLabel.LEFT);
        CenterPanelCustomerAddressValue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        CenterPanelCustomerAddressValue.setBorder(new LineBorder(Color.lightGray));
        controlPanel_CenterPanel.add(CenterPanelCustomerAddressValue);
        //-------------------------------

        CenterPanelCustomerCityLabel = new JLabel("City: ", JLabel.CENTER);
        CenterPanelCustomerCityLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel_CenterPanel.add(CenterPanelCustomerCityLabel);

        CenterPanelCustomerCityValue = new JLabel("", JLabel.LEFT);
        CenterPanelCustomerCityValue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        CenterPanelCustomerCityValue.setBorder(new LineBorder(Color.lightGray));
        controlPanel_CenterPanel.add(CenterPanelCustomerCityValue);
        //--------------------------------

        CenterPanelCustomerCountryLabel = new JLabel("Country: ", JLabel.CENTER);
        CenterPanelCustomerCountryLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel_CenterPanel.add(CenterPanelCustomerCountryLabel);

        CenterPanelCustomerCountryValue = new JLabel("", JLabel.LEFT);
        CenterPanelCustomerCountryValue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        CenterPanelCustomerCountryValue.setBorder(new LineBorder(Color.lightGray));
        controlPanel_CenterPanel.add(CenterPanelCustomerCountryValue);
        //------------------------------

        CenterPanelCustomerEmailLabel = new JLabel("Email: ", JLabel.CENTER);
        CenterPanelCustomerEmailLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel_CenterPanel.add(CenterPanelCustomerEmailLabel);

        CenterPanelCustomerEmailValue = new JLabel("", JLabel.LEFT);
        CenterPanelCustomerEmailValue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        CenterPanelCustomerEmailValue.setBorder(new LineBorder(Color.lightGray));
        controlPanel_CenterPanel.add(CenterPanelCustomerEmailValue);
        //------------------------------

        CenterPanelCustomerPhoneLabel = new JLabel("Phone : ", JLabel.CENTER);
        CenterPanelCustomerPhoneLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel_CenterPanel.add(CenterPanelCustomerPhoneLabel);

        CenterPanelCustomerPhoneValue = new JLabel("", JLabel.LEFT);
        CenterPanelCustomerPhoneValue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        CenterPanelCustomerPhoneValue.setBorder(new LineBorder(Color.lightGray));
        controlPanel_CenterPanel.add(CenterPanelCustomerPhoneValue);
        //--------------------------------

        CenterPanelCustomerContractNoLabel = new JLabel("Contract No: ", JLabel.CENTER);
        CenterPanelCustomerContractNoLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel_CenterPanel.add(CenterPanelCustomerContractNoLabel);

        CenterPanelCustomerContractNoValue = new JLabel("", JLabel.LEFT);
        CenterPanelCustomerContractNoValue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        CenterPanelCustomerContractNoValue.setBorder(new LineBorder(Color.lightGray));
        controlPanel_CenterPanel.add(CenterPanelCustomerContractNoValue);
        //---------------------------------

        CenterPanelCustomerMeterNoLabel = new JLabel("Meter No: ", JLabel.CENTER);
        CenterPanelCustomerMeterNoLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        controlPanel_CenterPanel.add(CenterPanelCustomerMeterNoLabel);

        CenterPanelCustomerMeterNoValue = new JLabel("", JLabel.LEFT);
        CenterPanelCustomerMeterNoValue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        CenterPanelCustomerMeterNoValue.setBorder(new LineBorder(Color.lightGray));
        controlPanel_CenterPanel.add(CenterPanelCustomerMeterNoValue);
//----------------------------------------
        CenterPanelCustomerDebtBalanceLabel = new JLabel("Debt Balance: ", JLabel.CENTER);
        CenterPanelCustomerDebtBalanceLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        controlPanel_CenterPanel.add(CenterPanelCustomerDebtBalanceLabel);

        CenterPanelCustomerDebtBalanceValue = new JLabel("", JLabel.LEFT);
        CenterPanelCustomerDebtBalanceValue.setFont(new Font("Times New Roman", Font.BOLD, 20));
        CenterPanelCustomerDebtBalanceValue.setBorder(new LineBorder(Color.lightGray));
        controlPanel_CenterPanel.add(CenterPanelCustomerDebtBalanceValue);

//-----------------------------------------------------------------------------------
        //monthChoice
        monthChoiceLabel = new JLabel(month, JLabel.CENTER);
        monthChoiceLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        monthChoiceLabel.setBorder(new LineBorder(Color.BLACK));


        monthChoiceLeftBtn = new JButton("<<");
        monthChoiceLeftBtn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        monthChoiceLeftBtn.setBackground(new Color(50, 205, 50));
        monthChoiceLeftBtn.addActionListener(this);

        monthChoiceRightBtn = new JButton(">>");
        monthChoiceRightBtn.setBackground(new Color(50, 205, 50));
        monthChoiceRightBtn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        monthChoiceRightBtn.addActionListener(this);

        monthChoicePanel = new JPanel();
        monthChoicePanel.setLayout(new BorderLayout());
        monthChoicePanel.setSize(210, 40);
        monthChoicePanel.setLocation(20, 500);
        // p13.setBackground(new Color(210, 232, 210));
        //p13.setBorder(new LineBorder(Color.BLACK));

        monthChoicePanel.add(monthChoiceLeftBtn, "West");
        monthChoicePanel.add(monthChoiceLabel, "Center");
        monthChoicePanel.add(monthChoiceRightBtn, "East");



        //-------------------------------------------------------------------------------
        //controlPanel-BottomPanel
        controlPanel_BottomPanel = new JPanel();
        controlPanel_BottomPanel.setLayout(new GridLayout(3, 2, 30, 20));
        controlPanel_BottomPanel.setSize(460, 160);
        controlPanel_BottomPanel.setLocation(20, 580);
        controlPanel_BottomPanel.setBackground(new Color(210, 232, 210));

        payBillButton = new JButton("PayBill");
        payBillButton.setBackground(new Color(50, 205, 50));
        payBillButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        payBillButton.addActionListener(this);

        payAmountButton = new JButton("PayAmount");
        payAmountButton.setBackground(new Color(50, 205, 50));
        payAmountButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        payAmountButton.addActionListener(this);

        setBillButton = new JButton("<<<SetBill");
        setBillButton.setBackground(new Color(50, 205, 50));
        setBillButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        setBillButton.addActionListener(this);

        printBillButton = new JButton("PrintBill");
        printBillButton.setBackground(new Color(50, 205, 50));
        printBillButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        printBillButton.addActionListener(this);

        genBillButton = new JButton("GenBill");
        genBillButton.setBackground(new Color(241, 213, 100));
        genBillButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        genBillButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(255, 0, 50));
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exitButton.addActionListener(this);


        controlPanel_BottomPanel.add(payBillButton);
        controlPanel_BottomPanel.add(payAmountButton);
        controlPanel_BottomPanel.add(setBillButton);
        controlPanel_BottomPanel.add(printBillButton);
        controlPanel_BottomPanel.add(genBillButton);
        controlPanel_BottomPanel.add(exitButton);


        controlPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(210, 232, 210));
                g.fillRoundRect(0, 0, 500, 800, 50, 50);
            }

        };
        //controlPanel.setBackground(new Color(206, 237, 206));
        //controlPanel.setBorder(new RoundedBorder(10));
        controlPanel.setSize(500, 800);
        controlPanel.setLocation(1060, 20);
        controlPanel.setLayout(null);
        controlPanel.add(controlPanel_TopPanel);
        controlPanel.add(controlPanel_CenterPanel);
        controlPanel.add(monthChoicePanel);
        controlPanel.add(controlPanel_BottomPanel);

        //----------------------------------------------------
        billPanel.add(titleLabel);
        billPanel.add(billPanelHeaderLabel1);
        billPanel.add(billPanelInsetLabel1);
        billPanel.add(billPanelInsetLabel2);
        billPanel.add(billPanel_CenterInsetPrincipalLabel);
        billPanel.add(billPanel_CenterInsetReceiverLabel);
        billPanel.add(billPanel_TopLeftPanel);
        billPanel.add(billPanel_TopRightPanel);
        billPanel.add(billPanel_CenterLeftTopPanel);
        billPanel.add(billPanel_CenterLeftBottomPanel);
        billPanel.add(billPanel_CenterCenterPanel);
        billPanel.add(billPanel_CenterRightPanel);
        billPanel.add(billPanel_BottomLeftPanel);
        billPanel.add(billPanel_BottomRightPanel);
        billPanel.add(billPanel_BottomRightLowerPanel);


        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(new Dimension(screenSize));
        mainPanel.add(billPanel);
        mainPanel.add(controlPanel);
        add(mainPanel);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = e.getActionCommand();
        String id = controlPanel_TopPanel_CustomerIdTextField.getText();
        String name = controlPanel_TopPanel_CustomerNameTextField.getText();

        if (message.equals("Find")) {
            // String id=tf1.getText();
            // String name=tf2.getText();

            if (id.isEmpty() && name.isEmpty()) {
                controlPanel_TopPanel_CustomerIdTextField.setBorder(new LineBorder(Color.RED, 3, true));
                controlPanel_TopPanel_CustomerIdTextField.setForeground(Color.lightGray);
                controlPanel_TopPanel_CustomerIdTextField.setText("id here");
                controlPanel_TopPanel_CustomerNameTextField.setBorder(new LineBorder(Color.RED, 3, true));
                controlPanel_TopPanel_CustomerNameTextField.setForeground(Color.lightGray);
                controlPanel_TopPanel_CustomerNameTextField.setText("name here");

            } else if (!id.isEmpty() || !name.isEmpty()) {

                findSetCustomer(id, name);
            }


        }


        if (message.equals("<<<SetBill")) {
            if (!id.isEmpty() || !name.isEmpty()) {
                setGUIBill(id, name);
            }

        }

        if(message.equals("GenBill")) {
            new GenerateBill(tempCustID, tempUserID);
        }


        if (message.equals("Exit")) {
            this.dispose();
        }
        if (message.equals("PayBill")) {

            new PayBill(tempCustID, tempUserID);

        }
        if (message.equals("PayAmount")) {
            new PayAmount();
        }
        if (message.equals("PrintBill")) {
            printRecord(mainPanel);

        }

        if (message.equals("<<") || message.equals(">>")) {
            setMonth(message);
        }


    }

    //--------------------------------------------------------------------------------
    //PrintBill(https://docs.oracle.com/javase%2Ftutorial%2F/2d/printing/printable.html)
    private void printRecord(JPanel panel) {

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
    //--------------------------------------------------------------------------

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == controlPanel_TopPanel_CustomerIdTextField || e.getSource() == controlPanel_TopPanel_CustomerNameTextField) {
            controlPanel_TopPanel_CustomerIdTextField.setText("");
            controlPanel_TopPanel_CustomerIdTextField.setBorder(new LineBorder(Color.GRAY));
            controlPanel_TopPanel_CustomerIdTextField.setForeground(Color.BLACK);
            controlPanel_TopPanel_CustomerNameTextField.setText("");
            controlPanel_TopPanel_CustomerNameTextField.setBorder(new LineBorder(Color.GRAY));
            controlPanel_TopPanel_CustomerNameTextField.setForeground(Color.BLACK);
            CenterPanelCustomerNameValue.setBorder(new LineBorder(Color.GRAY));
            onClickClearControlCenterPanel();
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
//---------------------------------------------------------------------------------------------------


    //RoundBorder
    public static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 2, this.radius + 2, this.radius + 2, this.radius + 2);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.lightGray);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    //--------------------------------------------------------
    //setMonth
    public void setMonth(String message) {
        if (message.equals("<<")) {
            if (i > 0) {
                i--;
                month = months[i];
                monthChoiceLabel.setText(month);
            } else
                i = months.length - 1;
            month = months[i];
            monthChoiceLabel.setText(month);
        }
        if (message.equals(">>")) {
            if (i < months.length - 1) {
                i++;
                month = months[i];
                monthChoiceLabel.setText(month);
            } else {
                i = 0;
                month = months[i];
                monthChoiceLabel.setText(month);
            }
        }
    }
//-------------------------------------------------------------------------------------


    public void findSetCustomer(String id, String name) {
        if (!id.isEmpty() && name.isEmpty()) {

            ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
            Customer customer = serviceFactory.getCustomerService().find(id);
            if (customer != null) {
                tempCustID = customer.getId();
                setCustomerFields(customer);
            } else {
                CenterPanelCustomerNameValue.setBorder(new LineBorder(Color.RED, 3, true));
                CenterPanelCustomerNameValue.setForeground(Color.BLACK);
                CenterPanelCustomerNameValue.setText("Doesn't exist");
            }
        } else if (id.isEmpty() && !name.isEmpty()) {
            ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
            Customer customer = serviceFactory.getCustomerService().findByName(name);
            if (customer != null) {
                tempCustID = customer.getId();
                controlPanel_TopPanel_CustomerIdTextField.setText(String.valueOf(customer.getId()));
                setCustomerFields(customer);
            }
        } else {
            CenterPanelCustomerNameValue.setBorder(new LineBorder(Color.RED, 3, true));
            CenterPanelCustomerNameValue.setForeground(Color.BLACK);
            CenterPanelCustomerNameValue.setText("Doesn't exist");

        }


    }

    public void setCustomerFields(Customer customer) {
        //tf1.setText(String.valueOf(customer.getId()));
        CenterPanelCustomerNameValue.setText(customer.getName());
        CenterPanelCustomerAddressValue.setText(customer.getAddress());
        CenterPanelCustomerCityValue.setText(customer.getCity());
        CenterPanelCustomerCountryValue.setText(customer.getCountry());
        CenterPanelCustomerEmailValue.setText(customer.getEmail());
        CenterPanelCustomerPhoneValue.setText(customer.getPhone());
        CenterPanelCustomerContractNoValue.setText(customer.getContractNo());
        CenterPanelCustomerMeterNoValue.setText(String.valueOf(customer.getMeterNo()));
        CenterPanelCustomerDebtBalanceValue.setText(String.valueOf(customer.getDebtBalance()));

    }

    //-------------------------------------------------------------------------------
    public void setGUIBill(String id, String name) {

        if (!id.isEmpty() && name.isEmpty() || (!id.isEmpty() && !name.isEmpty())) {

            ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
            Customer customer = serviceFactory.getCustomerService().find(id);

            serviceFactory = ServiceFactory.TAX_SERVICE;
            Tax tax = serviceFactory.getTaxService().find(customer.getTax().getId());

            serviceFactory = ServiceFactory.BILL_SERVICE;
            Bill bill = serviceFactory.getBillService().findByMonthCustomer(month, customer);

            if (customer != null && bill != null && tax != null) {
                setBillPanel_TopLeftPanelValues();
                setBillPanel_TopRightPanelValues();
                setBillPanel_CenterLeftTopPanelValues(customer);
                setBillPanel_CenterCenterPanelValues(customer, bill);
                setBillPanel_CenterRightPanelValues(customer, bill);
                setBillPanel_BottomLeftPanelValues(customer, bill, tax);
                setBillPanel_BottomRightPanelValues(bill, tax);
                setBillPanel_BottomRightLowerPanelValues(bill, tax);
            } else {
                CenterLeftTopPanelNameValue.setText("Can't find bill !");
                CenterLeftTopPanelNameValue.setFont(new Font("Times New Roman", Font.BOLD, 18));
                CenterLeftTopPanelNameValue.setBorder(new LineBorder(Color.RED));
            }
        } else if (id.isEmpty() && !name.isEmpty()) {
            ServiceFactory serviceFactory = ServiceFactory.CUSTOMER_SERVICE;
            Customer customer = serviceFactory.getCustomerService().findByName(name);

            serviceFactory = ServiceFactory.TAX_SERVICE;
            Tax tax = serviceFactory.getTaxService().find(customer.getTax().getId());

            serviceFactory = ServiceFactory.BILL_SERVICE;
            Bill bill = serviceFactory.getBillService().findByMonthCustomer(month, customer);

            if (customer != null && bill != null && tax != null) {

                setBillPanel_TopLeftPanelValues();
                setBillPanel_TopRightPanelValues();
                setBillPanel_CenterLeftTopPanelValues(customer);
                setBillPanel_CenterCenterPanelValues(customer, bill);
                setBillPanel_CenterRightPanelValues(customer, bill);
                setBillPanel_BottomLeftPanelValues(customer, bill, tax);
                setBillPanel_BottomRightPanelValues(bill, tax);
                setBillPanel_BottomRightLowerPanelValues(bill, tax);
            }
        } else {
            CenterLeftTopPanelNameValue.setText("Can't find bill !");
            CenterLeftTopPanelNameValue.setFont(new Font("Times New Roman", Font.BOLD, 18));
            CenterLeftTopPanelNameValue.setBorder(new LineBorder(Color.RED));
        }

    }

    //--------------------------------------------------------------------------------------------------
    public void setBillPanel_TopLeftPanelValues() {
        if (monthChoiceLabel.getText().equals(month)) {
            TopLeftPanelForMonthValue.setText(month);

        }

    }

    public void setBillPanel_TopRightPanelValues() {
        TopRightPanelDateValue.setText(month);
    }

    public void setBillPanel_CenterLeftTopPanelValues(Customer customer) {
        CenterLeftTopPanelNameValue.setText(customer.getName());
        CenterLeftTopPanelAddressValue.setText(customer.getAddress());
    }

    public void setBillPanel_CenterCenterPanelValues(Customer customer, Bill bill) {
        CenterCenterPanelReferenceNumberLabelValue.setText(String.valueOf(bill.getId()));
        CenterCenterPanelAmountValue.setText(String.valueOf(bill.getAmount()));
        CenterCenterPanelAreaOfSupplyValue.setText(customer.getCity());
        CenterCenterPanelAddressAndPlaceValue.setText(customer.getAddress() + ", " + customer.getCity());
    }

    public void setBillPanel_CenterRightPanelValues(Customer customer, Bill bill) {
        CenterRightPanelAmountValue.setText(String.valueOf(bill.getAmount()));
        CenterRightPanelReferenceNoValue.setText(String.valueOf(bill.getId()));
        CenterRightPanelNameValue.setText(customer.getName());
        CenterRightPanelAddressValue.setText(customer.getAddress());
        CenterRightPanelAddressAndPlaceValue.setText(customer.getAddress() + ", " + customer.getCity());
    }

    public void setBillPanel_BottomLeftPanelValues(Customer customer, Bill bill, Tax tax) {
        BottomLeftPanelNameValue.setText(customer.getName());
        BottomLeftPanelAddressValue.setText(customer.getAddress());
        BottomLeftPanelCityValue.setText(customer.getCity());
        BottomLeftPanelCountryValue.setText(customer.getCountry());
        BottomLeftPanelEmailValue.setText(customer.getEmail());
        BottomLeftPanelPhoneValue.setText(customer.getPhone());
        BottomLeftPanelMeterNoValue.setText(String.valueOf(customer.getMeterNo()));
        BottomLeftPanelTaxTypeValue.setText(tax.getType());
        BottomLeftPanelReadDateValue.setText(bill.getReadDate());
        BottomLeftPanelIssueDateValue.setText(bill.getIssueDate());
        BottomLeftPanelDeadLineDateDateValue.setText(bill.getDeadlineDate());
        BottomLeftPanelPaymentDateLabelValue.setText(bill.getPaymentDate());
    }

    public void setBillPanel_BottomRightPanelValues(Bill bill, Tax tax) {
        BottomRightPanelEnergyOffPeakQuantityValue.setText(String.valueOf(bill.getUnitsOffPeak()));
        BottomRightPanelEnergyOffPeakPriceValue.setText(String.valueOf(tax.getOffPeakPrice()));
        BottomRightPanelEnergyOffPeakAmountValue.setText(String.valueOf(bill.getOffPeakAmount()));

        BottomRightPanelEnergyOnPeakQuantityValue.setText(String.valueOf(bill.getUnitsOnPeak()));
        BottomRightPanelEnergyOnPeakPriceValue.setText(String.valueOf(tax.getOnPeakPrice()));
        BottomRightPanelEnergyOnPeakAmountValue.setText(String.valueOf(bill.getOnPeakAmount()));

        BottomRightPanelMeterRentAmountValue.setText(String.valueOf(tax.getMeterRent()));
        BottomRightPanelServiceRentAmountValue.setText(String.valueOf(tax.getServiceRent()));
        BigDecimal totalWithoutTax = bill.getOffPeakAmount().add(bill.getOnPeakAmount());
        BottomRightPanelTierRateAmountValue.setText(String.valueOf((tax.getTierRate().multiply(totalWithoutTax)).subtract(totalWithoutTax).round(new MathContext(2, RoundingMode.HALF_UP))));


    }

    public void setBillPanel_BottomRightLowerPanelValues(Bill bill, Tax tax) {
        BigDecimal totalWithoutTax = bill.getOffPeakAmount().add(bill.getOnPeakAmount());
        BigDecimal pdvTax = tax.getPDVTax();

        BottomRightLowerPanelAmountWithoutTaxValue.setText(String.valueOf(totalWithoutTax));
        BottomRightLowerPanelTaxValue.setText(String.valueOf(bill.getAmount().multiply(pdvTax)));
        BottomRightLowerPanelTotalValue.setText(String.valueOf(bill.getAmount()));

    }

    public void onClickClearControlCenterPanel() {
        JLabel[] fields = {CenterPanelCustomerNameValue, CenterPanelCustomerAddressValue, CenterPanelCustomerCityValue, CenterPanelCustomerCountryValue, CenterPanelCustomerEmailValue, CenterPanelCustomerPhoneValue, CenterPanelCustomerContractNoValue,
                CenterPanelCustomerMeterNoValue, CenterPanelCustomerDebtBalanceValue};

        for (JLabel field : fields) {
            field.setText("");
        }

    }


}