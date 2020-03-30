/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment;

;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTabbedPane;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author legion
 */


public class frmAssignment extends javax.swing.JFrame {

    /**
     * Creates new form frmAssignment
     */
    public frmAssignment() {
        initComponents();
this.setLocationRelativeTo(null);
    }

    void tableProducers() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select * from NHACUNGCAP");
            ResultSet rs = st.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tableAdminSystemProducers.getModel();
            model.setRowCount(0);//Set table empty
            while (rs.next()) {
                Object list[] = {rs.getString("IDnhaCC"), rs.getString("TennhaCC"), rs.getString("DiachinhaCC"), rs.getString("SDTnhaCC"), rs.getString("EmailnhaCC")};
                model.addRow(list);
            }
        } catch (Exception ex) {
        }

    }

    void tableProducts() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("SELECT * FROM  HANGHOA AS HH JOIN CUNGCAP AS CC ON HH.IDHH=CC.IDHH join NHACUNGCAP NCC ON NCC.IDnhaCC=CC.IDnhaCC   ");
            ResultSet rs = st.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tableAdminSystemProducts.getModel();
            model.setRowCount(0);//Set table empty
            while (rs.next()) {
                Object list[] = {rs.getString("IDHH"), rs.getString("TenHH"), rs.getString("SLtoithieu"), rs.getString("DVT"), rs.getString("Dongianhap"), rs.getString("Dongiaxuat"), rs.getString("IDnhaCC"), rs.getString("TennhaCC")};
                model.addRow(list);
            }
        } catch (Exception ex) {
        }

    }

    void tableStaff() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select * from NHANVIEN");
            ResultSet rs = st.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tableStaffSystemList.getModel();
            model.setRowCount(0);//Set table empty
            while (rs.next()) {
                Object list[] = {rs.getString("IDNV"), rs.getString("TenNV"), rs.getString("Gioitinh"), rs.getString("DiachiNV"), rs.getString("SDTNV"), rs.getString("Ngaybatdaulam"), rs.getString("username")};
                model.addRow(list);
            }
        } catch (Exception ex) {
        }

    }

    void tableCheck() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("SELECT * FROM  HANGHOA AS HH JOIN CUNGCAP AS CC ON HH.IDHH=CC.IDHH join NHACUNGCAP NCC ON NCC.IDnhaCC=CC.IDnhaCC  JOIN SOLUONGHH SL ON SL.IDHH=HH.IDHH ");
            ResultSet rs = st.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tableAdminSystemCheck.getModel();
            model.setRowCount(0);//Set table empty
            while (rs.next()) {
                Object list[] = {rs.getString("IDHH"), rs.getString("TenHH"), rs.getString("SLtoithieu"), rs.getString("DVT"), rs.getString("Dongianhap"), rs.getString("SLhienco"), rs.getString("IDnhaCC"), rs.getString("TennhaCC")};
                model.addRow(list);
            }
        } catch (Exception ex) {
        }

    }

    String passwordEncryption(String password) {//set MD5 Password
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            result = no.toString(16);
            while (result.length() < 32) {
                result = "0" + result;
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error!!!");
        }
        return result;
    }

    boolean checkLoginAdmin() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select username from TKADMIN where username=? and password=?");
            st.setString(1, txtLoginUsername.getText());
            st.setString(2, passwordEncryption(txtLoginPassword.getText()));

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception ex) {

        }
        return false;
    }

    boolean checkIDProducer() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDnhaCC from NHACUNGCAP where IDnhaCC=?");
            st.setString(1, txtIdAddProducer.getText());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (Exception ex) {

        }
        return true;
    }

    boolean checkIDProduct() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDnhaCC from HANGHOA where IDHH=?");
            st.setString(1, txtIdAddProduct.getText());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (Exception ex) {

        }
        return true;
    }

    boolean checkIDStaff() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDNV from NHANVIEN where IDNV=?");
            st.setString(1, txtIdAddStaff.getText());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (Exception ex) {

        }
        return true;
    }

    boolean checkUsernameStaff() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select username from NHANVIEN where username=?");
            st.setString(1, txtUserNameAddStaff.getText());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (Exception ex) {

        }
        return true;
    }

    boolean checkIDBillR() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDphieunhap from CHITIETNHAPKHO where IDphieunhap=?");
            st.setString(1, txtReceiptIDBill.getText());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (Exception ex) {

        }
        return true;
    }

    boolean checkIDBillD() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDphieuxuat from CHITIETXUATKHO where IDphieuxuat=?");
            st.setString(1, txtIDDeliveryBill.getText());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (Exception ex) {

        }
        return true;
    }

    boolean checkIDCheck() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDphieukiemke from CHITIETKIEMKE where IDphieukiemke=?");
            st.setString(1, txtCheckIDCheck.getText());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (Exception ex) {

        }
        return true;
    }

    boolean checkInt(String a) {

        if (a.isEmpty()) {
            return true;
        }
        int number;
        try {
            number = Integer.parseInt(a.trim());
            if (number > 0) {//user just input number in menu
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }

    }

    boolean checkDouble(String price) {//check name is character in alphabet
        if (price.isEmpty()) {
            return true;
        }
        double number;

        try {
            number = Double.parseDouble(price.trim());
            if (number > 0) {//user just input number in menu
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    String formatDate(Date a) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ");

        String strDate = sdfDate.format(a);

        return strDate;
    }

    boolean checkLoginUser() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select username, IDNV from NHANVIEN where username=? and password=?");
            st.setString(1, txtLoginUsername.getText());
            st.setString(2, passwordEncryption(txtLoginPassword.getText()));

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lblSaveU.setText(rs.getString("IDNV"));
                return true;
            }
        } catch (Exception ex) {

        }
        return false;
    }

    boolean checkPhone(String a) {
        if (a.isEmpty()) {
            return true;
        }
        return a.trim().matches("\\d{10}");
    }

    boolean checkEmail(String a) {
        if (a.isEmpty()) {
            return true;
        }
        return a.trim().matches("\\w+\\@\\w+(\\.\\w+)+");
    }

    boolean checkDate(String dateS) {//check format date from user input
        if (dateS.isEmpty()) {
            return true;
        }
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        format.setLenient(false);//check date is valid or not
        try {
            format.parse(dateS);

        } catch (ParseException e) {
            return false;//if date is not true, return false

        }
        return true;
    }

    boolean checkQuantity(int number) {
        int num = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
            PreparedStatement st = null;

            st = conn.prepareStatement("select SLhienco from SOLUONGHH where IDHH=?");//insert value to database
            st.setString(1, cbIDHHDelivery.getSelectedItem().toString());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (rs.getString("SLhienco") == null) {
                    num = 0;
                } else {
                    num = Integer.parseInt(rs.getString("SLhienco").trim());
                }
            }

            conn.close();
        } catch (Exception ex) {

        }
        if (num >= number) {
            return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AdminSystem = new javax.swing.JFrame();
        lblAdminProductManagement = new javax.swing.JLabel();
        lblAdminStaffManagement = new javax.swing.JLabel();
        ProductManagement = new javax.swing.JFrame();
        tbProductManagement = new javax.swing.JTabbedPane();
        AdminSystemProducers = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAdminSystemProducers = new javax.swing.JTable();
        btnAdminSystemAddProducers = new javax.swing.JButton();
        btnAdminSystemUpdateProducers = new javax.swing.JButton();
        btnAdminSystemDeleteProducers = new javax.swing.JButton();
        AdminSystemProducts = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableAdminSystemProducts = new javax.swing.JTable();
        btnAdminSystemAddProducts = new javax.swing.JButton();
        btnAdminSystemUpdateProducts = new javax.swing.JButton();
        btnAdminSystemDeleteProducts = new javax.swing.JButton();
        AdminSystemProductMangementNote = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableAdminSystemCheck = new javax.swing.JTable();
        btnAdminSystemExcelCheck = new javax.swing.JButton();
        AddProducer = new javax.swing.JFrame();
        lblIdAddProducer = new javax.swing.JLabel();
        lblNameAddProducer = new javax.swing.JLabel();
        lblAddressAddProducer = new javax.swing.JLabel();
        lblPhoneAddProducer = new javax.swing.JLabel();
        lblEmailAddProducer = new javax.swing.JLabel();
        txtIdAddProducer = new javax.swing.JTextField();
        txtNameAddProducer = new javax.swing.JTextField();
        txtAddressAddProducer = new javax.swing.JTextField();
        txtPhoneAddProducer = new javax.swing.JTextField();
        txtEmailAddProducer = new javax.swing.JTextField();
        btnAddProducer = new javax.swing.JButton();
        btnCancelAddProducer = new javax.swing.JButton();
        UpdateProducer = new javax.swing.JFrame();
        btnUpdateProducer = new javax.swing.JButton();
        btnCancelUpdateProducer = new javax.swing.JButton();
        txtEmailUpdateProducer = new javax.swing.JTextField();
        lblEmailUpdateProducer = new javax.swing.JLabel();
        lblPhoneUpdateProducer = new javax.swing.JLabel();
        txtPhoneUpdateProducer = new javax.swing.JTextField();
        lblAddressUpdateProducer = new javax.swing.JLabel();
        txtAddressUpdateProducer = new javax.swing.JTextField();
        txtNameUpdateProducer = new javax.swing.JTextField();
        lblUpdateAddProducer = new javax.swing.JLabel();
        lblIdUpdateProducer = new javax.swing.JLabel();
        cbIdUpdateProducer = new javax.swing.JComboBox<>();
        StaffManagement = new javax.swing.JFrame();
        tbStaffManagement = new javax.swing.JTabbedPane();
        AdminSystemStaff = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableStaffSystemList = new javax.swing.JTable();
        btnStaffSystemListAddStaff = new javax.swing.JButton();
        btnStaffSystemListUpdateStaff = new javax.swing.JButton();
        btnStaffSystemListDeleteStaff = new javax.swing.JButton();
        AddProduct = new javax.swing.JFrame();
        btnCancelAddProduct = new javax.swing.JButton();
        btnAddProduct = new javax.swing.JButton();
        lblUnitAddProduct = new javax.swing.JLabel();
        txtUnitAddProduct = new javax.swing.JTextField();
        txtAtLeastAddProduct = new javax.swing.JTextField();
        lblAtLeastQuantityAddProduct = new javax.swing.JLabel();
        txtNameAddProduct = new javax.swing.JTextField();
        lblNameAddProduct = new javax.swing.JLabel();
        txtIdAddProduct = new javax.swing.JTextField();
        lblIdAddProduct = new javax.swing.JLabel();
        lblProducerAddProduct = new javax.swing.JLabel();
        cbProducerIdAddProduct = new javax.swing.JComboBox<>();
        lblProducerNameAddProduct = new javax.swing.JLabel();
        lblReceiptAddProduct = new javax.swing.JLabel();
        lblDeliveryAddProduct = new javax.swing.JLabel();
        txtReceiptAddProduct = new javax.swing.JTextField();
        txtDeliveryAddProduct = new javax.swing.JTextField();
        UpdateProduct = new javax.swing.JFrame();
        lblIdUpdateProduct = new javax.swing.JLabel();
        cbIdUpdateProduct = new javax.swing.JComboBox<>();
        txtNameUpdateProduct = new javax.swing.JTextField();
        lblNameUpdateAddProduct = new javax.swing.JLabel();
        lblAtLeastUpdateProduct = new javax.swing.JLabel();
        txtAtLeastUpdateProduct = new javax.swing.JTextField();
        txtUnitUpdateProduct = new javax.swing.JTextField();
        lblUnitUpdateProduct = new javax.swing.JLabel();
        btnUpdateProduct = new javax.swing.JButton();
        btnCancelUpdateProduct = new javax.swing.JButton();
        cbProducerIdUpdateProduct = new javax.swing.JComboBox<>();
        lblProducerNameUpdateProduct = new javax.swing.JLabel();
        lblProducerUpdateProduct = new javax.swing.JLabel();
        lblReceiptUpdateProduct = new javax.swing.JLabel();
        lblDeliveryUpdateProduct = new javax.swing.JLabel();
        txtDeliveryUpdateProduct = new javax.swing.JTextField();
        txtReceiptUpdateProduct = new javax.swing.JTextField();
        UserSystem = new javax.swing.JFrame();
        lblUserReceipt = new javax.swing.JLabel();
        lblUserDelivery = new javax.swing.JLabel();
        lblUserDelivery1 = new javax.swing.JLabel();
        ReceiptSystem = new javax.swing.JFrame();
        lblRecieptIdProduct = new javax.swing.JLabel();
        lblIDReceiptBill = new javax.swing.JLabel();
        lblReceiptQuantity = new javax.swing.JLabel();
        lblReceiptNote = new javax.swing.JLabel();
        cbReceiptIDProduct = new javax.swing.JComboBox<>();
        txtReceiptIDBill = new javax.swing.JTextField();
        txtReceiptQuantity = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtReceiptNote = new javax.swing.JTextArea();
        btnReceiptReceipt = new javax.swing.JButton();
        btnReceiptCancel = new javax.swing.JButton();
        lblReceiptNameHH = new javax.swing.JLabel();
        lblSaveU = new javax.swing.JLabel();
        DeliverySystem = new javax.swing.JFrame();
        lblIDHHDelivery = new javax.swing.JLabel();
        lblIDDeliveryBill = new javax.swing.JLabel();
        lblDeliveryQuantity = new javax.swing.JLabel();
        lblDeliveryNote = new javax.swing.JLabel();
        cbIDHHDelivery = new javax.swing.JComboBox<>();
        txtIDDeliveryBill = new javax.swing.JTextField();
        txtDeliveryQuantity = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtDeliveryNote = new javax.swing.JTextArea();
        btnDeliveryDelivery = new javax.swing.JButton();
        btnDeliveryCancel = new javax.swing.JButton();
        lblDeliveryNameHH = new javax.swing.JLabel();
        lblDeliveryRecieve = new javax.swing.JLabel();
        cbDeliveryRecieve = new javax.swing.JComboBox<>();
        jFileChooser1 = new javax.swing.JFileChooser();
        AddStaff = new javax.swing.JFrame();
        btnCancelAddStaff = new javax.swing.JButton();
        btnAddStaff = new javax.swing.JButton();
        lblAddressAddStaff = new javax.swing.JLabel();
        txtAddressAddStaff = new javax.swing.JTextField();
        lblGenderAddStaff = new javax.swing.JLabel();
        txtNameAddStaff = new javax.swing.JTextField();
        lblNameAddStaff = new javax.swing.JLabel();
        txtIdAddStaff = new javax.swing.JTextField();
        lblIdAddStaff = new javax.swing.JLabel();
        lblUsernameAddStaff = new javax.swing.JLabel();
        lblPhoneNumberAddStaff = new javax.swing.JLabel();
        lblStartDateAddStaff = new javax.swing.JLabel();
        txtPhoneAddStaff = new javax.swing.JTextField();
        txtStartDateAddStaff = new javax.swing.JTextField();
        txtUserNameAddStaff = new javax.swing.JTextField();
        lblPasswordAddStaff = new javax.swing.JLabel();
        txtPasswordAddStaff = new javax.swing.JPasswordField();
        rdoMale = new javax.swing.JRadioButton();
        rdoFemale = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        UpdateStaff = new javax.swing.JFrame();
        btnUpdateStaff = new javax.swing.JButton();
        btnCancelUpdateStaff = new javax.swing.JButton();
        txtNameUpdateStaff = new javax.swing.JTextField();
        lblNameUpdateStaff = new javax.swing.JLabel();
        lblIdUpdateStaff = new javax.swing.JLabel();
        cbIdUpdateStaff = new javax.swing.JComboBox<>();
        lblGenderAddStaff1 = new javax.swing.JLabel();
        rdoMaleUpdate = new javax.swing.JRadioButton();
        rdoFemaleUpdate = new javax.swing.JRadioButton();
        txtAddressUpdateStaff = new javax.swing.JTextField();
        lblAddressUpdateStaff = new javax.swing.JLabel();
        lblPhoneNumberUpdateStaff = new javax.swing.JLabel();
        txtPhoneUpdateStaff = new javax.swing.JTextField();
        txtStartDateUpdateStaff = new javax.swing.JTextField();
        lblStartDateUpdateStaff = new javax.swing.JLabel();
        CheckSystem = new javax.swing.JFrame();
        btnCheckSubmit = new javax.swing.JButton();
        btnCheckCancel = new javax.swing.JButton();
        txtCheckIDCheck = new javax.swing.JTextField();
        lblcheckIDCheck = new javax.swing.JLabel();
        lblcheckIDHH = new javax.swing.JLabel();
        cbCheckIDHH = new javax.swing.JComboBox<>();
        txtCheckQuantity = new javax.swing.JTextField();
        lblCheckQuantity = new javax.swing.JLabel();
        lblCheckStatus = new javax.swing.JLabel();
        txtCheckStatus = new javax.swing.JTextField();
        lblCheckNote = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtCheckNote = new javax.swing.JTextArea();
        Login = new javax.swing.JPanel();
        txtLoginUsername = new javax.swing.JTextField();
        sprLoginUsername = new javax.swing.JSeparator();
        txtLoginPassword = new javax.swing.JPasswordField();
        sprLoginPassword = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbLoginAdminOrUser = new javax.swing.JComboBox<>();
        btnLogin = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        AdminSystem.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        AdminSystem.setTitle("Admin System");

        lblAdminProductManagement.setBackground(new java.awt.Color(255, 255, 255));
        lblAdminProductManagement.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblAdminProductManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/product.png"))); // NOI18N
        lblAdminProductManagement.setText("Product Management");
        lblAdminProductManagement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblAdminProductManagement.setOpaque(true);
        lblAdminProductManagement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblAdminProductManagement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAdminProductManagementMouseClicked(evt);
            }
        });

        lblAdminStaffManagement.setBackground(new java.awt.Color(255, 255, 255));
        lblAdminStaffManagement.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblAdminStaffManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/staff.png"))); // NOI18N
        lblAdminStaffManagement.setText("Staff Management");
        lblAdminStaffManagement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblAdminStaffManagement.setOpaque(true);
        lblAdminStaffManagement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblAdminStaffManagement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAdminStaffManagementMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout AdminSystemLayout = new javax.swing.GroupLayout(AdminSystem.getContentPane());
        AdminSystem.getContentPane().setLayout(AdminSystemLayout);
        AdminSystemLayout.setHorizontalGroup(
            AdminSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminSystemLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(AdminSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAdminProductManagement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAdminStaffManagement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(487, Short.MAX_VALUE))
        );
        AdminSystemLayout.setVerticalGroup(
            AdminSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminSystemLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblAdminProductManagement)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lblAdminStaffManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        ProductManagement.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ProductManagement.setTitle("Product Management");

        tbProductManagement.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        tbProductManagement.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tbProductManagement.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbProductManagement.setPreferredSize(new java.awt.Dimension(200, 200));
        tbProductManagement.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbProductManagementStateChanged(evt);
            }
        });

        tableAdminSystemProducers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address", "Phone number", "Email"
            }
        ));
        jScrollPane1.setViewportView(tableAdminSystemProducers);

        btnAdminSystemAddProducers.setText("Add ");
        btnAdminSystemAddProducers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminSystemAddProducersActionPerformed(evt);
            }
        });

        btnAdminSystemUpdateProducers.setText("Update");
        btnAdminSystemUpdateProducers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminSystemUpdateProducersActionPerformed(evt);
            }
        });

        btnAdminSystemDeleteProducers.setText("Delete");
        btnAdminSystemDeleteProducers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminSystemDeleteProducersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AdminSystemProducersLayout = new javax.swing.GroupLayout(AdminSystemProducers);
        AdminSystemProducers.setLayout(AdminSystemProducersLayout);
        AdminSystemProducersLayout.setHorizontalGroup(
            AdminSystemProducersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(AdminSystemProducersLayout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(btnAdminSystemAddProducers)
                .addGap(64, 64, 64)
                .addComponent(btnAdminSystemUpdateProducers)
                .addGap(78, 78, 78)
                .addComponent(btnAdminSystemDeleteProducers)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AdminSystemProducersLayout.setVerticalGroup(
            AdminSystemProducersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminSystemProducersLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(AdminSystemProducersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdminSystemAddProducers)
                    .addComponent(btnAdminSystemUpdateProducers)
                    .addComponent(btnAdminSystemDeleteProducers))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tbProductManagement.addTab("Producers", AdminSystemProducers);

        tableAdminSystemProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "At least Quantity", "Unit", "Price Receipt", "Price Delivery", "ID Producer", "Name Producer"
            }
        ));
        jScrollPane2.setViewportView(tableAdminSystemProducts);

        btnAdminSystemAddProducts.setText("Add ");
        btnAdminSystemAddProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminSystemAddProductsActionPerformed(evt);
            }
        });

        btnAdminSystemUpdateProducts.setText("Update");
        btnAdminSystemUpdateProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminSystemUpdateProductsActionPerformed(evt);
            }
        });

        btnAdminSystemDeleteProducts.setText("Delete");
        btnAdminSystemDeleteProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminSystemDeleteProductsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AdminSystemProductsLayout = new javax.swing.GroupLayout(AdminSystemProducts);
        AdminSystemProducts.setLayout(AdminSystemProductsLayout);
        AdminSystemProductsLayout.setHorizontalGroup(
            AdminSystemProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
            .addGroup(AdminSystemProductsLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(btnAdminSystemAddProducts)
                .addGap(69, 69, 69)
                .addComponent(btnAdminSystemUpdateProducts)
                .addGap(57, 57, 57)
                .addComponent(btnAdminSystemDeleteProducts)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AdminSystemProductsLayout.setVerticalGroup(
            AdminSystemProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminSystemProductsLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(AdminSystemProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdminSystemAddProducts)
                    .addComponent(btnAdminSystemUpdateProducts)
                    .addComponent(btnAdminSystemDeleteProducts))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tbProductManagement.addTab("Products", AdminSystemProducts);

        tableAdminSystemCheck.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "At least Quantity", "Unit", "Unit Price", "Real Quantity", "ID Producer", "Name Producer"
            }
        ));
        jScrollPane6.setViewportView(tableAdminSystemCheck);

        btnAdminSystemExcelCheck.setText("Import Excel");
        btnAdminSystemExcelCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminSystemExcelCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AdminSystemProductMangementNoteLayout = new javax.swing.GroupLayout(AdminSystemProductMangementNote);
        AdminSystemProductMangementNote.setLayout(AdminSystemProductMangementNoteLayout);
        AdminSystemProductMangementNoteLayout.setHorizontalGroup(
            AdminSystemProductMangementNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminSystemProductMangementNoteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(AdminSystemProductMangementNoteLayout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(btnAdminSystemExcelCheck)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AdminSystemProductMangementNoteLayout.setVerticalGroup(
            AdminSystemProductMangementNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminSystemProductMangementNoteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnAdminSystemExcelCheck)
                .addContainerGap())
        );

        tbProductManagement.addTab("Check", AdminSystemProductMangementNote);

        javax.swing.GroupLayout ProductManagementLayout = new javax.swing.GroupLayout(ProductManagement.getContentPane());
        ProductManagement.getContentPane().setLayout(ProductManagementLayout);
        ProductManagementLayout.setHorizontalGroup(
            ProductManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbProductManagement, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
        );
        ProductManagementLayout.setVerticalGroup(
            ProductManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbProductManagement, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
        );

        AddProducer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        AddProducer.setTitle("Add Producer");

        lblIdAddProducer.setText("ID:");

        lblNameAddProducer.setText("Name:");

        lblAddressAddProducer.setText("Address:");

        lblPhoneAddProducer.setText("Phone number:");

        lblEmailAddProducer.setText("Email:");

        btnAddProducer.setText("Add");
        btnAddProducer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProducerActionPerformed(evt);
            }
        });

        btnCancelAddProducer.setText("Cancel");
        btnCancelAddProducer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAddProducerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddProducerLayout = new javax.swing.GroupLayout(AddProducer.getContentPane());
        AddProducer.getContentPane().setLayout(AddProducerLayout);
        AddProducerLayout.setHorizontalGroup(
            AddProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddProducerLayout.createSequentialGroup()
                .addGroup(AddProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddProducerLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(AddProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdAddProducer)
                            .addComponent(lblNameAddProducer)
                            .addComponent(lblAddressAddProducer)
                            .addComponent(lblPhoneAddProducer)
                            .addComponent(lblEmailAddProducer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNameAddProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdAddProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddressAddProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailAddProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhoneAddProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddProducerLayout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(btnAddProducer)
                        .addGap(40, 40, 40)
                        .addComponent(btnCancelAddProducer)))
                .addContainerGap(130, Short.MAX_VALUE))
        );

        AddProducerLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAddressAddProducer, txtEmailAddProducer, txtIdAddProducer, txtNameAddProducer, txtPhoneAddProducer});

        AddProducerLayout.setVerticalGroup(
            AddProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddProducerLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(AddProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdAddProducer)
                    .addComponent(txtIdAddProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNameAddProducer)
                    .addComponent(txtNameAddProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AddProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddressAddProducer)
                    .addComponent(txtAddressAddProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhoneAddProducer)
                    .addComponent(txtPhoneAddProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AddProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmailAddProducer)
                    .addComponent(txtEmailAddProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(AddProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddProducer)
                    .addComponent(btnCancelAddProducer))
                .addContainerGap(152, Short.MAX_VALUE))
        );

        UpdateProducer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        UpdateProducer.setTitle("Update Producer");

        btnUpdateProducer.setText("Update");
        btnUpdateProducer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProducerActionPerformed(evt);
            }
        });

        btnCancelUpdateProducer.setText("Cancel");
        btnCancelUpdateProducer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelUpdateProducerActionPerformed(evt);
            }
        });

        lblEmailUpdateProducer.setText("Email:");

        lblPhoneUpdateProducer.setText("Phone number:");

        lblAddressUpdateProducer.setText("Address:");

        lblUpdateAddProducer.setText("Name:");

        lblIdUpdateProducer.setText("ID:");

        cbIdUpdateProducer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbIdUpdateProducerItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout UpdateProducerLayout = new javax.swing.GroupLayout(UpdateProducer.getContentPane());
        UpdateProducer.getContentPane().setLayout(UpdateProducerLayout);
        UpdateProducerLayout.setHorizontalGroup(
            UpdateProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateProducerLayout.createSequentialGroup()
                .addGroup(UpdateProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateProducerLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(UpdateProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUpdateAddProducer)
                            .addComponent(lblAddressUpdateProducer)
                            .addComponent(lblPhoneUpdateProducer)
                            .addComponent(lblEmailUpdateProducer)
                            .addComponent(lblIdUpdateProducer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UpdateProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPhoneUpdateProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddressUpdateProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameUpdateProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailUpdateProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(UpdateProducerLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addGroup(UpdateProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UpdateProducerLayout.createSequentialGroup()
                                .addComponent(btnUpdateProducer)
                                .addGap(40, 40, 40)
                                .addComponent(btnCancelUpdateProducer))
                            .addComponent(cbIdUpdateProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(142, Short.MAX_VALUE))
        );

        UpdateProducerLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAddressUpdateProducer, txtEmailUpdateProducer, txtNameUpdateProducer, txtPhoneUpdateProducer});

        UpdateProducerLayout.setVerticalGroup(
            UpdateProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateProducerLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(UpdateProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIdUpdateProducer)
                    .addComponent(cbIdUpdateProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UpdateProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUpdateAddProducer)
                    .addComponent(txtNameUpdateProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(UpdateProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddressUpdateProducer)
                    .addComponent(txtAddressUpdateProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UpdateProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhoneUpdateProducer)
                    .addComponent(txtPhoneUpdateProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(UpdateProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmailUpdateProducer)
                    .addComponent(txtEmailUpdateProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(UpdateProducerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateProducer)
                    .addComponent(btnCancelUpdateProducer))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        StaffManagement.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        StaffManagement.setTitle("Staff Management");

        tbStaffManagement.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        tbStaffManagement.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tbStaffManagement.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbStaffManagement.setPreferredSize(new java.awt.Dimension(200, 200));

        tableStaffSystemList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Gender", "Address", "Phone number", "Start Date", "Username"
            }
        ));
        jScrollPane4.setViewportView(tableStaffSystemList);

        btnStaffSystemListAddStaff.setText("Add ");
        btnStaffSystemListAddStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffSystemListAddStaffActionPerformed(evt);
            }
        });

        btnStaffSystemListUpdateStaff.setText("Update");
        btnStaffSystemListUpdateStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffSystemListUpdateStaffActionPerformed(evt);
            }
        });

        btnStaffSystemListDeleteStaff.setText("Delete");
        btnStaffSystemListDeleteStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffSystemListDeleteStaffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AdminSystemStaffLayout = new javax.swing.GroupLayout(AdminSystemStaff);
        AdminSystemStaff.setLayout(AdminSystemStaffLayout);
        AdminSystemStaffLayout.setHorizontalGroup(
            AdminSystemStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminSystemStaffLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(btnStaffSystemListAddStaff)
                .addGap(69, 69, 69)
                .addComponent(btnStaffSystemListUpdateStaff)
                .addGap(57, 57, 57)
                .addComponent(btnStaffSystemListDeleteStaff)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(AdminSystemStaffLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        AdminSystemStaffLayout.setVerticalGroup(
            AdminSystemStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminSystemStaffLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(AdminSystemStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStaffSystemListAddStaff)
                    .addComponent(btnStaffSystemListUpdateStaff)
                    .addComponent(btnStaffSystemListDeleteStaff))
                .addGap(0, 38, Short.MAX_VALUE))
        );

        tbStaffManagement.addTab("Staff", AdminSystemStaff);

        javax.swing.GroupLayout StaffManagementLayout = new javax.swing.GroupLayout(StaffManagement.getContentPane());
        StaffManagement.getContentPane().setLayout(StaffManagementLayout);
        StaffManagementLayout.setHorizontalGroup(
            StaffManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbStaffManagement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        StaffManagementLayout.setVerticalGroup(
            StaffManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbStaffManagement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        AddProduct.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        AddProduct.setTitle("Add product");

        btnCancelAddProduct.setText("Cancel");
        btnCancelAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAddProductActionPerformed(evt);
            }
        });

        btnAddProduct.setText("Add");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        lblUnitAddProduct.setText("Unit:");

        lblAtLeastQuantityAddProduct.setText("At least quantity:");

        lblNameAddProduct.setText("Name:");

        lblIdAddProduct.setText("ID:");

        lblProducerAddProduct.setText("Producer:");

        cbProducerIdAddProduct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProducerIdAddProductItemStateChanged(evt);
            }
        });

        lblProducerNameAddProduct.setText("jLabel1");

        lblReceiptAddProduct.setText("Price Receipt:");

        lblDeliveryAddProduct.setText("Price Delivery:");

        javax.swing.GroupLayout AddProductLayout = new javax.swing.GroupLayout(AddProduct.getContentPane());
        AddProduct.getContentPane().setLayout(AddProductLayout);
        AddProductLayout.setHorizontalGroup(
            AddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddProductLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(AddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdAddProduct)
                    .addComponent(lblNameAddProduct)
                    .addComponent(lblUnitAddProduct)
                    .addComponent(lblProducerAddProduct)
                    .addComponent(lblReceiptAddProduct)
                    .addComponent(lblDeliveryAddProduct)
                    .addComponent(lblAtLeastQuantityAddProduct))
                .addGap(35, 35, 35)
                .addGroup(AddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddProductLayout.createSequentialGroup()
                        .addComponent(btnAddProduct)
                        .addGap(40, 40, 40)
                        .addComponent(btnCancelAddProduct))
                    .addComponent(txtIdAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAtLeastAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AddProductLayout.createSequentialGroup()
                        .addComponent(cbProducerIdAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblProducerNameAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtReceiptAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDeliveryAddProduct)
                    .addComponent(txtUnitAddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        AddProductLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAtLeastAddProduct, txtDeliveryAddProduct, txtIdAddProduct, txtNameAddProduct, txtReceiptAddProduct, txtUnitAddProduct});

        AddProductLayout.setVerticalGroup(
            AddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddProductLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(AddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdAddProduct)
                    .addComponent(txtIdAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNameAddProduct)
                    .addComponent(txtNameAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAtLeastAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAtLeastQuantityAddProduct))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUnitAddProduct)
                    .addComponent(txtUnitAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReceiptAddProduct)
                    .addComponent(txtReceiptAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDeliveryAddProduct)
                    .addComponent(txtDeliveryAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(AddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbProducerIdAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProducerNameAddProduct)
                    .addComponent(lblProducerAddProduct))
                .addGap(28, 28, 28)
                .addGroup(AddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddProduct)
                    .addComponent(btnCancelAddProduct))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        UpdateProduct.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        UpdateProduct.setTitle("Update");

        lblIdUpdateProduct.setText("ID:");

        cbIdUpdateProduct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbIdUpdateProductItemStateChanged(evt);
            }
        });

        lblNameUpdateAddProduct.setText("Name:");

        lblAtLeastUpdateProduct.setText("At least quantity:");

        lblUnitUpdateProduct.setText("Unit:");

        btnUpdateProduct.setText("Update");
        btnUpdateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProductActionPerformed(evt);
            }
        });

        btnCancelUpdateProduct.setText("Cancel");
        btnCancelUpdateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelUpdateProductActionPerformed(evt);
            }
        });

        cbProducerIdUpdateProduct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProducerIdUpdateProductItemStateChanged(evt);
            }
        });

        lblProducerNameUpdateProduct.setText("jLabel1");

        lblProducerUpdateProduct.setText("Producer:");

        lblReceiptUpdateProduct.setText("Price Receipt:");

        lblDeliveryUpdateProduct.setText("Price Delivery:");

        javax.swing.GroupLayout UpdateProductLayout = new javax.swing.GroupLayout(UpdateProduct.getContentPane());
        UpdateProduct.getContentPane().setLayout(UpdateProductLayout);
        UpdateProductLayout.setHorizontalGroup(
            UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateProductLayout.createSequentialGroup()
                .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateProductLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UpdateProductLayout.createSequentialGroup()
                                .addComponent(lblReceiptUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(UpdateProductLayout.createSequentialGroup()
                                .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNameUpdateAddProduct)
                                    .addComponent(lblIdUpdateProduct))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(UpdateProductLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAtLeastUpdateProduct)
                            .addComponent(lblProducerUpdateProduct))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtAtLeastUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtReceiptUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDeliveryUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUnitUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(UpdateProductLayout.createSequentialGroup()
                            .addComponent(btnUpdateProduct)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelUpdateProduct)))
                    .addComponent(cbIdUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(UpdateProductLayout.createSequentialGroup()
                        .addComponent(cbProducerIdUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblProducerNameUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(472, 472, 472))
            .addGroup(UpdateProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUnitUpdateProduct)
                    .addComponent(lblDeliveryUpdateProduct))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        UpdateProductLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAtLeastUpdateProduct, txtDeliveryUpdateProduct, txtNameUpdateProduct, txtReceiptUpdateProduct, txtUnitUpdateProduct});

        UpdateProductLayout.setVerticalGroup(
            UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateProductLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdUpdateProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbIdUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNameUpdateAddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(txtNameUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateProductLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblAtLeastUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblReceiptUpdateProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtReceiptUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(UpdateProductLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAtLeastUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDeliveryUpdateProduct)
                    .addComponent(txtDeliveryUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUnitUpdateProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUnitUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducerUpdateProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbProducerIdUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProducerNameUpdateProduct))
                .addGap(41, 41, 41)
                .addGroup(UpdateProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdateProduct)
                    .addComponent(btnCancelUpdateProduct))
                .addGap(133, 133, 133))
        );

        UserSystem.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        UserSystem.setTitle("User system");

        lblUserReceipt.setBackground(new java.awt.Color(255, 255, 255));
        lblUserReceipt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblUserReceipt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/receipt.png"))); // NOI18N
        lblUserReceipt.setText("Receipt");
        lblUserReceipt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUserReceipt.setOpaque(true);
        lblUserReceipt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblUserReceipt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserReceiptMouseClicked(evt);
            }
        });

        lblUserDelivery.setBackground(new java.awt.Color(255, 255, 255));
        lblUserDelivery.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblUserDelivery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/check.png"))); // NOI18N
        lblUserDelivery.setText("Check");
        lblUserDelivery.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUserDelivery.setOpaque(true);
        lblUserDelivery.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblUserDelivery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserDeliveryMouseClicked(evt);
            }
        });

        lblUserDelivery1.setBackground(new java.awt.Color(255, 255, 255));
        lblUserDelivery1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblUserDelivery1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delivery.png"))); // NOI18N
        lblUserDelivery1.setText("Delivery");
        lblUserDelivery1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUserDelivery1.setOpaque(true);
        lblUserDelivery1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblUserDelivery1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserDelivery1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout UserSystemLayout = new javax.swing.GroupLayout(UserSystem.getContentPane());
        UserSystem.getContentPane().setLayout(UserSystemLayout);
        UserSystemLayout.setHorizontalGroup(
            UserSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserSystemLayout.createSequentialGroup()
                .addGroup(UserSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UserSystemLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblUserReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(lblUserDelivery1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(UserSystemLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(lblUserDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        UserSystemLayout.setVerticalGroup(
            UserSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserSystemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UserSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserDelivery1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addComponent(lblUserDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        ReceiptSystem.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ReceiptSystem.setTitle("Receipt System");

        lblRecieptIdProduct.setText("IDHH:");

        lblIDReceiptBill.setText("ID Bill:");

        lblReceiptQuantity.setText("Quantity:");

        lblReceiptNote.setText("Note:");

        cbReceiptIDProduct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbReceiptIDProductItemStateChanged(evt);
            }
        });

        txtReceiptNote.setColumns(20);
        txtReceiptNote.setRows(5);
        jScrollPane3.setViewportView(txtReceiptNote);

        btnReceiptReceipt.setText("Receipt");
        btnReceiptReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiptReceiptActionPerformed(evt);
            }
        });

        btnReceiptCancel.setText("Cancel");
        btnReceiptCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiptCancelActionPerformed(evt);
            }
        });

        lblReceiptNameHH.setText("jLabel2");

        javax.swing.GroupLayout ReceiptSystemLayout = new javax.swing.GroupLayout(ReceiptSystem.getContentPane());
        ReceiptSystem.getContentPane().setLayout(ReceiptSystemLayout);
        ReceiptSystemLayout.setHorizontalGroup(
            ReceiptSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReceiptSystemLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(ReceiptSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRecieptIdProduct)
                    .addComponent(lblIDReceiptBill)
                    .addComponent(lblReceiptQuantity)
                    .addComponent(lblReceiptNote))
                .addGap(31, 31, 31)
                .addGroup(ReceiptSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReceiptSystemLayout.createSequentialGroup()
                        .addComponent(btnReceiptReceipt)
                        .addGap(18, 18, 18)
                        .addComponent(btnReceiptCancel))
                    .addGroup(ReceiptSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(ReceiptSystemLayout.createSequentialGroup()
                            .addGroup(ReceiptSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtReceiptIDBill)
                                .addComponent(txtReceiptQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addComponent(cbReceiptIDProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblReceiptNameHH))))
                .addContainerGap(327, Short.MAX_VALUE))
        );
        ReceiptSystemLayout.setVerticalGroup(
            ReceiptSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReceiptSystemLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(ReceiptSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbReceiptIDProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRecieptIdProduct)
                    .addComponent(lblReceiptNameHH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ReceiptSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDReceiptBill)
                    .addComponent(txtReceiptIDBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ReceiptSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReceiptQuantity)
                    .addComponent(txtReceiptQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ReceiptSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReceiptNote)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ReceiptSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReceiptReceipt)
                    .addComponent(btnReceiptCancel))
                .addContainerGap(231, Short.MAX_VALUE))
        );

        lblSaveU.setText("jLabel2");

        DeliverySystem.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DeliverySystem.setTitle("Delivery System");

        lblIDHHDelivery.setText("IDHH:");

        lblIDDeliveryBill.setText("ID Bill:");

        lblDeliveryQuantity.setText("Quantity:");

        lblDeliveryNote.setText("Note:");

        cbIDHHDelivery.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbIDHHDeliveryItemStateChanged(evt);
            }
        });

        txtDeliveryNote.setColumns(20);
        txtDeliveryNote.setRows(5);
        jScrollPane7.setViewportView(txtDeliveryNote);

        btnDeliveryDelivery.setText("Delivery");
        btnDeliveryDelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeliveryDeliveryActionPerformed(evt);
            }
        });

        btnDeliveryCancel.setText("Cancel");
        btnDeliveryCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeliveryCancelActionPerformed(evt);
            }
        });

        lblDeliveryNameHH.setText("jLabel2");

        lblDeliveryRecieve.setText("Recieve ID:");

        javax.swing.GroupLayout DeliverySystemLayout = new javax.swing.GroupLayout(DeliverySystem.getContentPane());
        DeliverySystem.getContentPane().setLayout(DeliverySystemLayout);
        DeliverySystemLayout.setHorizontalGroup(
            DeliverySystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeliverySystemLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(DeliverySystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DeliverySystemLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btnDeliveryDelivery)
                        .addGap(41, 41, 41)
                        .addComponent(btnDeliveryCancel))
                    .addGroup(DeliverySystemLayout.createSequentialGroup()
                        .addGroup(DeliverySystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIDHHDelivery)
                            .addComponent(lblIDDeliveryBill)
                            .addComponent(lblDeliveryQuantity)
                            .addComponent(lblDeliveryNote)
                            .addComponent(lblDeliveryRecieve))
                        .addGap(31, 31, 31)
                        .addGroup(DeliverySystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DeliverySystemLayout.createSequentialGroup()
                                .addGroup(DeliverySystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIDDeliveryBill, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDeliveryQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbIDHHDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(lblDeliveryNameHH))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbDeliveryRecieve, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(310, Short.MAX_VALUE))
        );
        DeliverySystemLayout.setVerticalGroup(
            DeliverySystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeliverySystemLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(DeliverySystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbIDHHDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIDHHDelivery)
                    .addComponent(lblDeliveryNameHH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DeliverySystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDDeliveryBill)
                    .addComponent(txtIDDeliveryBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DeliverySystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDeliveryQuantity)
                    .addComponent(txtDeliveryQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(DeliverySystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDeliveryRecieve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDeliveryRecieve))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DeliverySystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDeliveryNote))
                .addGap(97, 97, 97)
                .addGroup(DeliverySystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeliveryDelivery)
                    .addComponent(btnDeliveryCancel))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        AddStaff.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        AddStaff.setTitle("Add staff");

        btnCancelAddStaff.setText("Cancel");
        btnCancelAddStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAddStaffActionPerformed(evt);
            }
        });

        btnAddStaff.setText("Add");
        btnAddStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStaffActionPerformed(evt);
            }
        });

        lblAddressAddStaff.setText("Address");

        lblGenderAddStaff.setText("Gender:");

        lblNameAddStaff.setText("Name:");

        lblIdAddStaff.setText("ID:");

        lblUsernameAddStaff.setText("UserName:");

        lblPhoneNumberAddStaff.setText("Number Phone:");

        lblStartDateAddStaff.setText("Start Date:");

        lblPasswordAddStaff.setText("Password:");

        buttonGroup1.add(rdoMale);
        rdoMale.setSelected(true);
        rdoMale.setText("Male");

        buttonGroup1.add(rdoFemale);
        rdoFemale.setText("Female");

        javax.swing.GroupLayout AddStaffLayout = new javax.swing.GroupLayout(AddStaff.getContentPane());
        AddStaff.getContentPane().setLayout(AddStaffLayout);
        AddStaffLayout.setHorizontalGroup(
            AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddStaffLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblIdAddStaff)
                        .addComponent(lblNameAddStaff)
                        .addComponent(lblAddressAddStaff)
                        .addComponent(lblUsernameAddStaff)
                        .addComponent(lblPhoneNumberAddStaff)
                        .addComponent(lblStartDateAddStaff)
                        .addComponent(lblGenderAddStaff))
                    .addGroup(AddStaffLayout.createSequentialGroup()
                        .addComponent(lblPasswordAddStaff)
                        .addGap(30, 30, 30)))
                .addGap(35, 35, 35)
                .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhoneAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStartDateAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserNameAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPasswordAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AddStaffLayout.createSequentialGroup()
                        .addComponent(btnAddStaff)
                        .addGap(36, 36, 36)
                        .addComponent(btnCancelAddStaff))
                    .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(AddStaffLayout.createSequentialGroup()
                            .addComponent(rdoMale)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdoFemale))
                        .addComponent(txtAddressAddStaff, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        AddStaffLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAddressAddStaff, txtIdAddStaff, txtNameAddStaff, txtPasswordAddStaff, txtPhoneAddStaff, txtStartDateAddStaff, txtUserNameAddStaff});

        AddStaffLayout.setVerticalGroup(
            AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddStaffLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdAddStaff)
                    .addComponent(txtIdAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNameAddStaff)
                    .addComponent(txtNameAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGenderAddStaff)
                    .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoMale)
                        .addComponent(rdoFemale)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddressAddStaff)
                    .addComponent(txtAddressAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhoneNumberAddStaff)
                    .addComponent(txtPhoneAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStartDateAddStaff)
                    .addComponent(txtStartDateAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsernameAddStaff)
                    .addComponent(txtUserNameAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPasswordAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPasswordAddStaff))
                .addGap(31, 31, 31)
                .addGroup(AddStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddStaff)
                    .addComponent(btnCancelAddStaff))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        UpdateStaff.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        UpdateStaff.setTitle("Update Staff");

        btnUpdateStaff.setText("Update");
        btnUpdateStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateStaffActionPerformed(evt);
            }
        });

        btnCancelUpdateStaff.setText("Cancel");
        btnCancelUpdateStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelUpdateStaffActionPerformed(evt);
            }
        });

        lblNameUpdateStaff.setText("Name:");

        lblIdUpdateStaff.setText("ID:");

        cbIdUpdateStaff.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbIdUpdateStaffItemStateChanged(evt);
            }
        });

        lblGenderAddStaff1.setText("Gender:");

        buttonGroup1.add(rdoMaleUpdate);
        rdoMaleUpdate.setText("Male");

        buttonGroup1.add(rdoFemaleUpdate);
        rdoFemaleUpdate.setText("Female");

        lblAddressUpdateStaff.setText("Address");

        lblPhoneNumberUpdateStaff.setText("Number Phone:");

        lblStartDateUpdateStaff.setText("Start Date:");

        javax.swing.GroupLayout UpdateStaffLayout = new javax.swing.GroupLayout(UpdateStaff.getContentPane());
        UpdateStaff.getContentPane().setLayout(UpdateStaffLayout);
        UpdateStaffLayout.setHorizontalGroup(
            UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateStaffLayout.createSequentialGroup()
                .addGroup(UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateStaffLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNameUpdateStaff)
                            .addComponent(lblIdUpdateStaff)
                            .addComponent(lblGenderAddStaff1)
                            .addComponent(lblAddressUpdateStaff)
                            .addComponent(lblPhoneNumberUpdateStaff)
                            .addComponent(lblStartDateUpdateStaff))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPhoneUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddressUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStartDateUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(UpdateStaffLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addGroup(UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UpdateStaffLayout.createSequentialGroup()
                                .addComponent(btnUpdateStaff)
                                .addGap(40, 40, 40)
                                .addComponent(btnCancelUpdateStaff))
                            .addComponent(cbIdUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(UpdateStaffLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(rdoMaleUpdate)
                        .addGap(57, 57, 57)
                        .addComponent(rdoFemaleUpdate)))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        UpdateStaffLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAddressUpdateStaff, txtNameUpdateStaff, txtPhoneUpdateStaff, txtStartDateUpdateStaff});

        UpdateStaffLayout.setVerticalGroup(
            UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateStaffLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIdUpdateStaff)
                    .addComponent(cbIdUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNameUpdateStaff)
                    .addComponent(txtNameUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGenderAddStaff1)
                    .addComponent(rdoMaleUpdate)
                    .addComponent(rdoFemaleUpdate))
                .addGap(18, 18, 18)
                .addGroup(UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAddressUpdateStaff)
                    .addComponent(txtAddressUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhoneNumberUpdateStaff)
                    .addComponent(txtPhoneUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStartDateUpdateStaff)
                    .addComponent(txtStartDateUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(UpdateStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateStaff)
                    .addComponent(btnCancelUpdateStaff))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        CheckSystem.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        CheckSystem.setTitle("Check Staff");

        btnCheckSubmit.setText("Submit");
        btnCheckSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckSubmitActionPerformed(evt);
            }
        });

        btnCheckCancel.setText("Cancel");
        btnCheckCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckCancelActionPerformed(evt);
            }
        });

        lblcheckIDCheck.setText("ID Check:");

        lblcheckIDHH.setText("IDHH:");

        cbCheckIDHH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCheckIDHHItemStateChanged(evt);
            }
        });

        lblCheckQuantity.setText("Quantity:");

        lblCheckStatus.setText("Status:");

        lblCheckNote.setText("Note:");

        txtCheckNote.setColumns(20);
        txtCheckNote.setRows(5);
        jScrollPane8.setViewportView(txtCheckNote);

        javax.swing.GroupLayout CheckSystemLayout = new javax.swing.GroupLayout(CheckSystem.getContentPane());
        CheckSystem.getContentPane().setLayout(CheckSystemLayout);
        CheckSystemLayout.setHorizontalGroup(
            CheckSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckSystemLayout.createSequentialGroup()
                .addGroup(CheckSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CheckSystemLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(CheckSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcheckIDCheck)
                            .addComponent(lblcheckIDHH)
                            .addComponent(lblCheckQuantity)
                            .addComponent(lblCheckStatus)
                            .addComponent(lblCheckNote))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CheckSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCheckQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCheckStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCheckIDCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCheckIDHH, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(CheckSystemLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(btnCheckSubmit)
                        .addGap(61, 61, 61)
                        .addComponent(btnCheckCancel)))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        CheckSystemLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane8, txtCheckIDCheck, txtCheckQuantity, txtCheckStatus});

        CheckSystemLayout.setVerticalGroup(
            CheckSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckSystemLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(CheckSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblcheckIDHH)
                    .addComponent(cbCheckIDHH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CheckSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblcheckIDCheck)
                    .addComponent(txtCheckIDCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CheckSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCheckQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCheckQuantity))
                .addGap(18, 18, 18)
                .addGroup(CheckSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCheckStatus)
                    .addComponent(txtCheckStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(CheckSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCheckNote))
                .addGap(18, 31, Short.MAX_VALUE)
                .addGroup(CheckSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheckSubmit)
                    .addComponent(btnCheckCancel))
                .addGap(42, 42, 42))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WELCOME TO MANAGING PRODUCT SOFTWARE");

        Login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtLoginUsername.setText("Enter Username");
        txtLoginUsername.setBorder(null);
        txtLoginUsername.setOpaque(false);
        txtLoginUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLoginUsernameFocusGained(evt);
            }
        });
        Login.add(txtLoginUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 240, 30));

        sprLoginUsername.setBackground(new java.awt.Color(0, 0, 0));
        Login.add(sprLoginUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 200, 10));

        txtLoginPassword.setBorder(null);
        txtLoginPassword.setOpaque(false);
        txtLoginPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginPasswordActionPerformed(evt);
            }
        });
        Login.add(txtLoginPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 240, 30));

        sprLoginPassword.setBackground(new java.awt.Color(0, 0, 0));
        Login.add(sprLoginPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 200, 30));

        jLabel2.setText("Username:");
        Login.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        jLabel1.setText("Password:");
        Login.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, -1));

        cbLoginAdminOrUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        Login.add(cbLoginAdminOrUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, -1, -1));

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        Login.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("WELCOME TO MANAGING PRODUCT SOFTWARE");
        Login.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 46, 440, 60));

        jLabel4.setBackground(new java.awt.Color(102, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/background.png"))); // NOI18N
        jLabel4.setText("WELCOME TO MANAGING PRODUCT SOFTWARE");
        jLabel4.setOpaque(true);
        Login.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 430, 70));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/background.png"))); // NOI18N
        jLabel3.setOpaque(true);
        Login.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 380));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLoginUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLoginUsernameFocusGained
        txtLoginUsername.setText("");
    }//GEN-LAST:event_txtLoginUsernameFocusGained

    private void lblAdminProductManagementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdminProductManagementMouseClicked
        tableProducers();
        ProductManagement.setVisible(true);
        ProductManagement.setSize(1000, 500);
    }//GEN-LAST:event_lblAdminProductManagementMouseClicked

    private void lblAdminStaffManagementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdminStaffManagementMouseClicked
        tableStaff();
        StaffManagement.setVisible(true);
        StaffManagement.setSize(1000, 500);
    }//GEN-LAST:event_lblAdminStaffManagementMouseClicked

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if (checkLoginAdmin() && cbLoginAdminOrUser.getSelectedItem().toString().equalsIgnoreCase("Admin")) {
           
            AdminSystem.setVisible(true);
            AdminSystem.setSize(500, 500);
        
            this.dispose();
        } else if (checkLoginUser() && cbLoginAdminOrUser.getSelectedItem().toString().equalsIgnoreCase("User")) {
            
            UserSystem.setVisible(true);
            UserSystem.setSize(500, 500);
           
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username or password is wrong!!!");
        }

    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnAdminSystemAddProducersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminSystemAddProducersActionPerformed
        AddProducer.setVisible(true);
        AddProducer.setSize(500, 500);
    }//GEN-LAST:event_btnAdminSystemAddProducersActionPerformed

    private void btnAdminSystemUpdateProducersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminSystemUpdateProducersActionPerformed
        cbIdUpdateProducer.removeAllItems();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDnhaCC from NHACUNGCAP  ");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                cbIdUpdateProducer.addItem(rs.getString("IDnhaCC").toString());
            }

        } catch (Exception ex) {
        }
        UpdateProducer.setVisible(true);
        UpdateProducer.setSize(500, 500);
    }//GEN-LAST:event_btnAdminSystemUpdateProducersActionPerformed

    private void btnAddProducerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProducerActionPerformed
        if (txtIdAddProducer.getText().isEmpty()) {
            JOptionPane.showMessageDialog(AddProducer, "ID can not null!!!");
        } else if (!checkIDProducer()) {
            JOptionPane.showMessageDialog(AddProducer, "ID must be unique!!!");
        } else if (!checkPhone(txtPhoneAddProducer.getText())) {
            JOptionPane.showMessageDialog(AddProducer, "Phone number must be 10 digits!!!");
        } else if (!checkEmail(txtEmailAddProducer.getText())) {
            JOptionPane.showMessageDialog(AddProducer, "Email must be format!!!");
        } else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
                PreparedStatement st = null;
                st = conn.prepareStatement("Insert into NHACUNGCAP values(?,?,?,?,?)");//insert value to database
                st.setString(1, txtIdAddProducer.getText());
                st.setString(2, txtNameAddProducer.getText().isEmpty() ? null : txtNameAddProducer.getText());
                st.setString(3, txtAddressAddProducer.getText().isEmpty() ? null : txtAddressAddProducer.getText());
                st.setString(4, txtPhoneAddProducer.getText().isEmpty() ? null : txtPhoneAddProducer.getText());
                st.setString(5, txtEmailAddProducer.getText().isEmpty() ? null : txtEmailAddProducer.getText());

                int count = st.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(AddProducer, "Producer added!");
                } else {
                    JOptionPane.showMessageDialog(AddProducer, "Can not add this Producer !");
                }
                conn.close();
            } catch (Exception ex) {

            }
            txtAddressAddProducer.setText("");
            txtEmailAddProducer.setText("");
            txtIdAddProducer.setText("");
            txtPhoneAddProducer.setText("");
            txtNameAddProducer.setText("");
            tableProducers();
            AddProducer.dispose();
        }
    }//GEN-LAST:event_btnAddProducerActionPerformed

    private void btnCancelAddProducerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAddProducerActionPerformed
        AddProducer.dispose();
    }//GEN-LAST:event_btnCancelAddProducerActionPerformed

    private void btnUpdateProducerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProducerActionPerformed
        if (!checkPhone(txtPhoneUpdateProducer.getText())) {
            JOptionPane.showMessageDialog(UpdateProducer, "Phone number must be 10 digits!!!");
        } else if (!checkEmail(txtEmailUpdateProducer.getText())) {
            JOptionPane.showMessageDialog(UpdateProducer, "Email must be format!!!");
        } else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
                PreparedStatement st = null;
                st = conn.prepareStatement("update NHACUNGCAP SET TennhaCC=?,DiachinhaCC=?,SDTnhaCC=?,EmailnhaCC=? where IDnhaCC=?");//insert value to database

                st.setString(1, txtNameUpdateProducer.getText().isEmpty() ? null : txtNameUpdateProducer.getText());
                st.setString(2, txtAddressUpdateProducer.getText().isEmpty() ? null : txtAddressUpdateProducer.getText());
                st.setString(3, txtPhoneUpdateProducer.getText().isEmpty() ? null : txtPhoneUpdateProducer.getText());
                st.setString(4, txtEmailUpdateProducer.getText().isEmpty() ? null : txtEmailUpdateProducer.getText());
                st.setString(5, cbIdUpdateProducer.getSelectedItem().toString());

                int count = st.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(UpdateProducer, "Producer Update!");
                    tableProducers();
                    UpdateProducer.dispose();
                } else {
                    JOptionPane.showMessageDialog(UpdateProducer, "Can not Update this Producer !");
                }
                conn.close();
            } catch (Exception ex) {

            }

        }
    }//GEN-LAST:event_btnUpdateProducerActionPerformed

    private void btnCancelUpdateProducerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelUpdateProducerActionPerformed
        UpdateProducer.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelUpdateProducerActionPerformed

    private void cbIdUpdateProducerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbIdUpdateProducerItemStateChanged
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
            PreparedStatement st = null;
            st = conn.prepareStatement("select * from NHACUNGCAP where IDnhaCC=? ");//insert value to database
            st.setString(1, cbIdUpdateProducer.getSelectedItem().toString());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                txtAddressUpdateProducer.setText(rs.getString("DiachinhaCC"));
                txtEmailUpdateProducer.setText(rs.getString("EmailnhaCC"));
                txtNameUpdateProducer.setText(rs.getString("TennhaCC"));
                txtPhoneUpdateProducer.setText(rs.getString("SDTnhaCC"));
            }
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_cbIdUpdateProducerItemStateChanged

    private void btnAdminSystemDeleteProducersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminSystemDeleteProducersActionPerformed
        int a = tableAdminSystemProducers.getSelectedRow();
        if (a != -1) {
            DefaultTableModel model = (DefaultTableModel) tableAdminSystemProducers.getModel();
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
                PreparedStatement st = null;
                st = conn.prepareStatement("delete from NHACUNGCAP where  IDnhaCC=?");//insert value to database

                st.setString(1, model.getValueAt(a, 0).toString());
                int count = st.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(AdminSystemProducers, "Delete sucessfull!");
                } else {
                    JOptionPane.showMessageDialog(AdminSystemProducers, "Can not Delete !");
                }
                conn.close();
            } catch (Exception ex) {

            }
            model.removeRow(a);
        } else {
            JOptionPane.showMessageDialog(AdminSystemProducers, "Please choose one row");
        }
    }//GEN-LAST:event_btnAdminSystemDeleteProducersActionPerformed

    private void tbProductManagementStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbProductManagementStateChanged
        JTabbedPane tab = (JTabbedPane) evt.getSource();
        int i = tab.getSelectedIndex();
        if (i == 1) {
            tableProducts();
        }
        if (i == 2) {
            tableCheck();
        }
    }//GEN-LAST:event_tbProductManagementStateChanged

    private void btnAdminSystemAddProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminSystemAddProductsActionPerformed
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDnhaCC from NHACUNGCAP  ");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                cbProducerIdAddProduct.addItem(rs.getString("IDnhaCC").toString());

            }

        } catch (Exception ex) {
        }
        AddProduct.setVisible(true);
        AddProduct.setSize(600, 600);
    }//GEN-LAST:event_btnAdminSystemAddProductsActionPerformed

    private void btnCancelAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAddProductActionPerformed
        AddProduct.dispose();
    }//GEN-LAST:event_btnCancelAddProductActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        if (txtIdAddProduct.getText().isEmpty()) {
            JOptionPane.showMessageDialog(AddProduct, "ID can not null!!!");
        }
        if (txtReceiptAddProduct.getText().isEmpty()) {
            JOptionPane.showMessageDialog(AddProduct, "Price receipt can not null!!!");
        }
        if (txtDeliveryAddProduct.getText().isEmpty()) {
            JOptionPane.showMessageDialog(AddProduct, "Price delivery can not null!!!");
        } else if (!checkIDProduct()) {
            JOptionPane.showMessageDialog(AddProduct, "ID must be unique!!!");
        } else if (!checkInt(txtAtLeastAddProduct.getText().trim())) {
            JOptionPane.showMessageDialog(AddProduct, "At least quantity must be positive number!!!");
        } else if (!checkDouble(txtReceiptAddProduct.getText().trim())) {
            JOptionPane.showMessageDialog(AddProduct, "Price Receipt  must be positive number!!!");
        } else if (!checkDouble(txtDeliveryAddProduct.getText().trim())) {
            JOptionPane.showMessageDialog(AddProduct, "Price Delivery  must be positive number!!!");
        } else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
                PreparedStatement st = null;
                st = conn.prepareStatement("Insert into HANGHOA values(?,?,?,?,?,?)");//insert value to database
                st.setString(1, txtIdAddProduct.getText().trim());
                st.setString(2, txtNameAddProduct.getText().isEmpty() ? null : txtNameAddProduct.getText());
                st.setString(3, txtAtLeastAddProduct.getText().isEmpty() ? null : txtAtLeastAddProduct.getText());
                st.setString(4, txtUnitAddProduct.getText().isEmpty() ? null : txtUnitAddProduct.getText());
                st.setString(5, txtReceiptAddProduct.getText().trim());
                st.setString(6, txtDeliveryAddProduct.getText().trim());
                int count = st.executeUpdate();
                st = conn.prepareStatement("Insert into  CUNGCAP values(?,?)");//insert value to database
                st.setString(1, txtIdAddProduct.getText().trim());
                st.setString(2, cbProducerIdAddProduct.getSelectedItem().toString().trim());
                count = st.executeUpdate();
                st = conn.prepareStatement("Insert into  SOLUONGHH values(?,?)");//insert value to database
                st.setString(1, txtIdAddProduct.getText().trim());
                st.setString(2, "0");
                count = st.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(AddProduct, "Product added!");
                } else {
                    JOptionPane.showMessageDialog(AddProduct, "Can not add this Product !");
                }
                conn.close();
            } catch (Exception ex) {

            }
            txtNameAddProduct.setText("");
            txtAtLeastAddProduct.setText("");
            txtIdAddProduct.setText("");
            txtUnitAddProduct.setText("");
            txtReceiptAddProduct.setText("");
            txtDeliveryAddProduct.setText("");
            tableProducts();
            AddProduct.dispose();
        }
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void cbProducerIdAddProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProducerIdAddProductItemStateChanged
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select TennhaCC from NHACUNGCAP where IDnhaCC=?");
            st.setString(1, cbProducerIdAddProduct.getSelectedItem().toString());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                lblProducerNameAddProduct.setText(rs.getString("TennhaCC"));
            }

        } catch (Exception ex) {
        }

    }//GEN-LAST:event_cbProducerIdAddProductItemStateChanged

    private void btnAdminSystemUpdateProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminSystemUpdateProductsActionPerformed
        cbIdUpdateProduct.removeAllItems();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDnhaCC from NHACUNGCAP  ");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                cbProducerIdUpdateProduct.addItem(rs.getString("IDnhaCC").toString());

            }
            st = conn.prepareStatement("Select IDHH from HANGHOA  ");

            rs = st.executeQuery();

            while (rs.next()) {
                cbIdUpdateProduct.addItem(rs.getString("IDHH").toString());

            }
        } catch (Exception ex) {
        }
        UpdateProduct.setVisible(true);
        UpdateProduct.setSize(600, 600);
    }//GEN-LAST:event_btnAdminSystemUpdateProductsActionPerformed

    private void cbIdUpdateProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbIdUpdateProductItemStateChanged
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
            PreparedStatement st = null;
            st = conn.prepareStatement("select * from HANGHOA where IDHH=? ");//insert value to database
            st.setString(1, cbIdUpdateProduct.getSelectedItem().toString());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                txtNameUpdateProduct.setText(rs.getString("TenHH"));
                txtAtLeastUpdateProduct.setText(rs.getString("SLtoithieu"));
                txtUnitUpdateProduct.setText(rs.getString("DVT"));
                txtReceiptUpdateProduct.setText(rs.getString("Dongianhap"));
                txtDeliveryUpdateProduct.setText(rs.getString("Dongiaxuat"));

            }
            st = conn.prepareStatement("select IDnhaCC from   CUNGCAP where IDHH=? ");//insert value to database
            st.setString(1, cbIdUpdateProduct.getSelectedItem().toString());

            rs = st.executeQuery();
            while (rs.next()) {
                cbProducerIdUpdateProduct.setSelectedItem(rs.getString("IDnhaCC"));
            }
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_cbIdUpdateProductItemStateChanged

    private void btnUpdateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProductActionPerformed
        if (!checkInt(txtAtLeastUpdateProduct.getText().trim())) {
            JOptionPane.showMessageDialog(UpdateProduct, "At least quantity must be positive number!!!");
        } else if (txtReceiptUpdateProduct.getText().isEmpty()) {
            JOptionPane.showMessageDialog(UpdateProduct, "Price receipt can not null!!!");
        } else if (txtDeliveryUpdateProduct.getText().isEmpty()) {
            JOptionPane.showMessageDialog(UpdateProduct, "Price delivery can not null!!!");
        } else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
                PreparedStatement st = null;
                st = conn.prepareStatement("Update HANGHOA set TenHH=?, SLtoithieu=?,DVT=?,Dongianhap=?,Dongiaxuat=? where IDHH=? ");//insert value to database
                st.setString(1, txtNameUpdateProduct.getText().trim());
                st.setString(2, txtAtLeastUpdateProduct.getText().isEmpty() ? null : txtAtLeastUpdateProduct.getText());
                st.setString(3, txtUnitUpdateProduct.getText().isEmpty() ? null : txtUnitUpdateProduct.getText());
                st.setString(4, txtReceiptUpdateProduct.getText().trim());
                st.setString(5, txtDeliveryUpdateProduct.getText().trim());
                st.setString(6, cbIdUpdateProduct.getSelectedItem().toString());
                int count = st.executeUpdate();
                st = conn.prepareStatement("select IDnhaCC from   CUNGCAP where IDHH=? ");//insert value to database
                st.setString(1, cbIdUpdateProduct.getSelectedItem().toString());

                String a = null;
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    a = rs.getString("IDnhaCC");
                }

                st = conn.prepareStatement("Update CUNGCAP set IDnhaCC=? where IDHH=? and IDnhaCC=?");//insert value to database
                st.setString(1, cbProducerIdUpdateProduct.getSelectedItem().toString().trim());
                st.setString(2, cbIdUpdateProduct.getSelectedItem().toString().trim());
                st.setString(3, a);

                count = st.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(UpdateProduct, "Product Update!");
                } else {
                    JOptionPane.showMessageDialog(UpdateProduct, "Can not update this Product !");
                }
                conn.close();
            } catch (Exception ex) {

            }
            txtNameUpdateProduct.setText("");
            txtUnitUpdateProduct.setText("");
            txtAtLeastUpdateProduct.setText("");
            txtDeliveryUpdateProduct.setText("");
            txtReceiptUpdateProduct.setText("");
            tableProducts();
            UpdateProduct.dispose();
        }
    }//GEN-LAST:event_btnUpdateProductActionPerformed

    private void btnCancelUpdateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelUpdateProductActionPerformed
        UpdateProduct.dispose();
    }//GEN-LAST:event_btnCancelUpdateProductActionPerformed

    private void cbProducerIdUpdateProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProducerIdUpdateProductItemStateChanged
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select TennhaCC from NHACUNGCAP where IDnhaCC=?");
            st.setString(1, cbProducerIdUpdateProduct.getSelectedItem().toString());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                lblProducerNameUpdateProduct.setText(rs.getString("TennhaCC"));
            }

        } catch (Exception ex) {
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cbProducerIdUpdateProductItemStateChanged

    private void btnAdminSystemDeleteProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminSystemDeleteProductsActionPerformed
        int a = tableAdminSystemProducts.getSelectedRow();
        if (a != -1) {

            DefaultTableModel model = (DefaultTableModel) tableAdminSystemProducts.getModel();

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
                PreparedStatement st = null;
                st = conn.prepareStatement("delete from CUNGCAP where  IDHH=? and IDnhaCC=?");//insert value to database
                st.setString(1, model.getValueAt(a, 0).toString());
                st.setString(2, model.getValueAt(a, 6).toString());
                int count = st.executeUpdate();
                st = conn.prepareStatement("delete from HANGHOA where  IDHH=?");//insert value to database
                st.setString(1, model.getValueAt(a, 0).toString());
                count = st.executeUpdate();
                st = conn.prepareStatement("delete from SOLUONGHH where  IDHH=?");//insert value to database
                st.setString(1, model.getValueAt(a, 0).toString());
                count = st.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(AdminSystemProducts, "Delete sucessfull!");
                } else {
                    JOptionPane.showMessageDialog(AdminSystemProducts, "Can not Delete !");
                }
                conn.close();
            } catch (Exception ex) {

            }
            model.removeRow(a);
        } else {
            JOptionPane.showMessageDialog(AdminSystemProducts, "Please choose one row");
        }
    }//GEN-LAST:event_btnAdminSystemDeleteProductsActionPerformed

    private void lblUserReceiptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserReceiptMouseClicked
        cbReceiptIDProduct.removeAllItems();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDHH from HANGHOA  ");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                cbReceiptIDProduct.addItem(rs.getString("IDHH").toString());

            }

        } catch (Exception ex) {
        }
        ReceiptSystem.setVisible(true);
        ReceiptSystem.setSize(500, 500);
    }//GEN-LAST:event_lblUserReceiptMouseClicked

    private void lblUserDeliveryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserDeliveryMouseClicked
        cbCheckIDHH.removeAllItems();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDHH from HANGHOA  ");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                cbCheckIDHH.addItem(rs.getString("IDHH").toString());

            }
       
        } catch (Exception ex) {
        }
        CheckSystem.setVisible(true);
        CheckSystem.setSize(500, 500);
    }//GEN-LAST:event_lblUserDeliveryMouseClicked

    private void btnReceiptReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceiptReceiptActionPerformed

        if (txtReceiptIDBill.getText().isEmpty()) {
            JOptionPane.showMessageDialog(ReceiptSystem, "ID can not null!!!");
        } else if (!checkIDBillR()) {
            JOptionPane.showMessageDialog(ReceiptSystem, "ID must be unique!!!");
        } else if (txtReceiptQuantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(AddProduct, "Quantity can not null!!!");
        } else if (!checkInt(txtReceiptQuantity.getText().trim())) {
            JOptionPane.showMessageDialog(ReceiptSystem, "Quantity must be positive integer number!!!");
        } else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
                PreparedStatement st = null;
                st = conn.prepareStatement("Insert into CHITIETNHAPKHO values(?,?,?,?,?,?)");//insert value to database
                st.setString(1, cbReceiptIDProduct.getSelectedItem().toString());
                st.setString(2, lblSaveU.getText());
                st.setString(3, txtReceiptIDBill.getText().trim());
                st.setString(4, txtReceiptQuantity.getText());
                Date a = new Date();
                st.setString(5, formatDate(a));
                st.setString(6, txtReceiptNote.getText().isEmpty() ? null : txtReceiptNote.getText());
                int count = st.executeUpdate();
                st = conn.prepareStatement("select SLhienco from SOLUONGHH where IDHH=?");//insert value to database
                st.setString(1, cbReceiptIDProduct.getSelectedItem().toString());
                ResultSet rs = st.executeQuery();
                int num = 0;
                while (rs.next()) {
                    if (rs.getString("SLhienco") == null) {
                        num = 0;
                    } else {
                        num = Integer.parseInt(rs.getString("SLhienco").trim());
                    }
                }
                st = conn.prepareStatement("UPDATE  SOLUONGHH set SLhienco=? where IDHH=?  ");//insert value to database
                st.setString(1, Integer.toString(num + Integer.parseInt(txtReceiptQuantity.getText())));
                st.setString(2, cbReceiptIDProduct.getSelectedItem().toString());
                count = st.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(ReceiptSystem, "Receipt Successful!!!!!");
                } else {
                    JOptionPane.showMessageDialog(ReceiptSystem, "Can not Receipt !");
                }
                conn.close();
            } catch (Exception ex) {

            }
            cbReceiptIDProduct.removeAllItems();
            txtReceiptIDBill.setText("");
            txtReceiptNote.setText("");
            txtReceiptQuantity.setText("");
            ReceiptSystem.dispose();;
        }
    }//GEN-LAST:event_btnReceiptReceiptActionPerformed

    private void btnReceiptCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceiptCancelActionPerformed
        cbReceiptIDProduct.removeAllItems();
        ReceiptSystem.dispose();
    }//GEN-LAST:event_btnReceiptCancelActionPerformed

    private void cbReceiptIDProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbReceiptIDProductItemStateChanged
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select * from HANGHOA where IDHH=?  ");
            st.setString(1, cbReceiptIDProduct.getSelectedItem().toString());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lblReceiptNameHH.setText(rs.getString("TenHH"));

            }

        } catch (Exception ex) {
        }
    }//GEN-LAST:event_cbReceiptIDProductItemStateChanged

    private void cbIDHHDeliveryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbIDHHDeliveryItemStateChanged
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select * from HANGHOA where IDHH=?  ");
            st.setString(1, cbIDHHDelivery.getSelectedItem().toString());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lblDeliveryNameHH.setText(rs.getString("TenHH"));

            }

        } catch (Exception ex) {
        }
    }//GEN-LAST:event_cbIDHHDeliveryItemStateChanged

    private void btnDeliveryDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeliveryDeliveryActionPerformed
        if (txtIDDeliveryBill.getText().isEmpty()) {
            JOptionPane.showMessageDialog(DeliverySystem, "ID can not null!!!");
        } else if (!checkIDBillD()) {
            JOptionPane.showMessageDialog(DeliverySystem, "ID must be unique!!!");
        } else if (txtDeliveryQuantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(DeliverySystem, "Quantity can not null!!!");
        } else if (!checkInt(txtDeliveryQuantity.getText().trim())) {
            JOptionPane.showMessageDialog(DeliverySystem, "Quantity must be positive integer number!!!");
        } else if (!checkQuantity(Integer.parseInt(txtDeliveryQuantity.getText().trim()))) {
            JOptionPane.showMessageDialog(DeliverySystem, "Real Quantity is not enough!!!");
        } else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
                PreparedStatement st = null;
                st = conn.prepareStatement("Insert into CHITIETXUATKHO values(?,?,?,?,?,?,?)");//insert value to database

                st.setString(1, txtIDDeliveryBill.getText().trim());
                st.setString(2, cbIDHHDelivery.getSelectedItem().toString());
                st.setString(3, lblSaveU.getText());
                st.setString(4, cbDeliveryRecieve.getSelectedItem().toString());

                Date a = new Date();
                st.setString(5, formatDate(a));
                st.setString(6, txtDeliveryQuantity.getText());
                st.setString(7, txtDeliveryNote.getText().isEmpty() ? null : txtDeliveryNote.getText());
                int count = st.executeUpdate();
                st = conn.prepareStatement("select SLhienco from SOLUONGHH where IDHH=?");//insert value to database
                st.setString(1, cbIDHHDelivery.getSelectedItem().toString());
                ResultSet rs = st.executeQuery();
                int num = 0;
                while (rs.next()) {
                    if (rs.getString("SLhienco") == null) {
                        num = 0;
                    } else {
                        num = Integer.parseInt(rs.getString("SLhienco").trim());
                    }
                }
                st = conn.prepareStatement("UPDATE  SOLUONGHH set SLhienco=? where IDHH=?  ");//insert value to database
                st.setString(1, Integer.toString(num - Integer.parseInt(txtDeliveryQuantity.getText())));
                st.setString(2, cbIDHHDelivery.getSelectedItem().toString());
                count = st.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(DeliverySystem, "Delivery Successful!!!!!");
                } else {
                    JOptionPane.showMessageDialog(DeliverySystem, "Can not Delivery !");
                }
                conn.close();
            } catch (Exception ex) {

            }
            cbIDHHDelivery.removeAllItems();
            txtIDDeliveryBill.setText("");
            txtDeliveryNote.setText("");
            txtDeliveryQuantity.setText("");
            DeliverySystem.dispose();
        }
    }//GEN-LAST:event_btnDeliveryDeliveryActionPerformed

    private void btnDeliveryCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeliveryCancelActionPerformed
        DeliverySystem.dispose();
    }//GEN-LAST:event_btnDeliveryCancelActionPerformed

    private void btnAdminSystemExcelCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminSystemExcelCheckActionPerformed

        DefaultTableModel model = (DefaultTableModel) tableAdminSystemCheck.getModel();
        JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\legion\\Desktop");
        excelFileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            XSSFWorkbook excelJTableExporter = new XSSFWorkbook();
            XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");

            for (int i = 0; i < model.getRowCount(); i++) {
                XSSFRow excelRow = excelSheet.createRow(i);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    XSSFCell excelCell = excelRow.createCell(j);
                    excelCell.setCellValue(model.getValueAt(i, j).toString());
                }
            }
            try {
                FileOutputStream excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                BufferedOutputStream excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Exported successfully!!!!!");
                if (excelBOU != null) {
                    excelBOU.close();
                }
                if (excelFOU != null) {
                    excelFOU.close();
                }

            } catch (Exception ex) {

            }

        }


    }//GEN-LAST:event_btnAdminSystemExcelCheckActionPerformed

    private void btnCancelAddStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAddStaffActionPerformed
        AddStaff.dispose();
        txtIdAddStaff.setText("");
        txtNameAddStaff.setText("");
        txtAddressAddStaff.setText("");
        txtPhoneAddStaff.setText("");
        txtStartDateAddStaff.setText("");
        txtUserNameAddStaff.setText("");
        txtPasswordAddStaff.setText("");
    }//GEN-LAST:event_btnCancelAddStaffActionPerformed

    private void btnAddStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStaffActionPerformed
        if (txtIdAddStaff.getText().isEmpty()) {
            JOptionPane.showMessageDialog(AddStaff, "ID can not null!!!");
        } else if (!checkIDStaff()) {
            JOptionPane.showMessageDialog(AddStaff, "ID must be unique!!!");
        } else if (!checkPhone(txtPhoneAddStaff.getText())) {
            JOptionPane.showMessageDialog(AddStaff, "Phone number must be 10 digits!!!");
        } else if (!checkDate(txtStartDateAddStaff.getText())) {
            JOptionPane.showMessageDialog(AddStaff, "Date must be format yyyy/mm/dd!!!");
        } else if (txtUserNameAddStaff.getText().isEmpty()) {
            JOptionPane.showMessageDialog(AddStaff, "User name can not null!!!");
        } else if (!checkUsernameStaff()) {
            JOptionPane.showMessageDialog(AddStaff, "User name has already!!!");
        } else if (txtPasswordAddStaff.getText().isEmpty()) {
            JOptionPane.showMessageDialog(AddStaff, "Password can not null!!!");
        } else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
                PreparedStatement st = null;
                st = conn.prepareStatement("Insert into NHANVIEN values(?,?,?,?,?,?,?,?)");//insert value to database
                st.setString(1, txtIdAddStaff.getText());
                st.setString(2, txtNameAddStaff.getText().isEmpty() ? null : txtNameAddStaff.getText());
                st.setString(3, rdoFemale.isSelected() ? "Female" : "Male");
                st.setString(4, txtAddressAddStaff.getText().isEmpty() ? null : txtAddressAddStaff.getText());
                st.setString(5, txtPhoneAddProducer.getText().isEmpty() ? null : txtPhoneAddProducer.getText());
                st.setString(6, txtStartDateAddStaff.getText().isEmpty() ? null : txtStartDateAddStaff.getText());
                st.setString(7, txtUserNameAddStaff.getText());
                st.setString(8, passwordEncryption(String.valueOf(txtPasswordAddStaff.getPassword())));

                int count = st.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(AddProducer, "Staff added!");
                } else {
                    JOptionPane.showMessageDialog(AddProducer, "Can not add this Staff !");
                }
                conn.close();
            } catch (Exception ex) {

            }
            txtIdAddStaff.setText("");
            txtNameAddStaff.setText("");
            txtAddressAddStaff.setText("");
            txtPhoneAddStaff.setText("");
            txtStartDateAddStaff.setText("");
            txtUserNameAddStaff.setText("");
            txtPasswordAddStaff.setText("");

            tableStaff();
            AddStaff.dispose();
        }
    }//GEN-LAST:event_btnAddStaffActionPerformed

    private void btnStaffSystemListAddStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffSystemListAddStaffActionPerformed
        AddStaff.setVisible(true);
        AddStaff.setSize(1000, 500);// TODO add your handling code here:
    }//GEN-LAST:event_btnStaffSystemListAddStaffActionPerformed

    private void btnUpdateStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateStaffActionPerformed
        if (!checkPhone(txtPhoneUpdateStaff.getText())) {
            JOptionPane.showMessageDialog(UpdateStaff, "Phone number must be 10 digits!!!");
        } else if (!checkDate(txtStartDateUpdateStaff.getText())) {
            JOptionPane.showMessageDialog(UpdateStaff, "Date must be format yyyy/mm/dd!!!");
        } else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
                PreparedStatement st = null;
                st = conn.prepareStatement("update NHANVIEN set TenNV=?,Gioitinh=?,DiachiNV=?,SDTNV=?,Ngaybatdaulam=? where IDNV=?");//insert value to database

                st.setString(1, txtNameUpdateStaff.getText().isEmpty() ? null : txtNameUpdateStaff.getText());
                st.setString(2, rdoFemaleUpdate.isSelected() ? "Female" : "Male");
                st.setString(3, txtAddressUpdateStaff.getText().isEmpty() ? null : txtAddressUpdateStaff.getText());
                st.setString(4, txtPhoneUpdateStaff.getText().isEmpty() ? null : txtPhoneUpdateStaff.getText());
                st.setString(5, txtStartDateUpdateStaff.getText().isEmpty() ? null : txtStartDateUpdateStaff.getText());
                st.setString(6, cbIdUpdateStaff.getSelectedItem().toString());

                int count = st.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(AddProducer, "Staff Update!");
                } else {
                    JOptionPane.showMessageDialog(AddProducer, "Can not Update this Staff !");
                }
                conn.close();
            } catch (Exception ex) {

            }
            txtNameUpdateStaff.setText("");
            txtAddressUpdateStaff.setText("");
            txtPhoneUpdateStaff.setText("");
            txtStartDateUpdateStaff.setText("");
            cbIdUpdateStaff.removeAllItems();
            tableStaff();
            UpdateStaff.dispose();
        }
    }//GEN-LAST:event_btnUpdateStaffActionPerformed

    private void btnCancelUpdateStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelUpdateStaffActionPerformed
        txtNameUpdateStaff.setText("");
        txtAddressUpdateStaff.setText("");
        txtPhoneUpdateStaff.setText("");
        txtStartDateUpdateStaff.setText("");
        cbIdUpdateStaff.removeAllItems();
        UpdateStaff.dispose();

    }//GEN-LAST:event_btnCancelUpdateStaffActionPerformed

    private void cbIdUpdateStaffItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbIdUpdateStaffItemStateChanged
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
            PreparedStatement st = null;
            st = conn.prepareStatement("select * from NHANVIEN where IDNV=? ");//insert value to database
            st.setString(1, cbIdUpdateStaff.getSelectedItem().toString());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                txtNameUpdateStaff.setText(rs.getString("TenNV"));
                if (rs.getString("Gioitinh").trim().compareToIgnoreCase("Male") == 0) {
                    rdoMaleUpdate.setSelected(true);
                } else {
                    rdoFemaleUpdate.setSelected(true);
                }
                txtAddressUpdateStaff.setText(rs.getString("DiachiNV"));
                txtPhoneUpdateStaff.setText(rs.getString("SDTNV"));
                txtStartDateAddStaff.setText(rs.getString("Ngaybatdaulam"));

            }

        } catch (Exception ex) {

        }
    }//GEN-LAST:event_cbIdUpdateStaffItemStateChanged

    private void btnStaffSystemListUpdateStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffSystemListUpdateStaffActionPerformed
        cbIdUpdateStaff.removeAllItems();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDNV from NHANVIEN  ");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                cbIdUpdateStaff.addItem(rs.getString("IDNV").toString());

            }

        } catch (Exception ex) {
        }
        UpdateStaff.setVisible(true);
        UpdateStaff.setSize(1000, 500);
    }//GEN-LAST:event_btnStaffSystemListUpdateStaffActionPerformed

    private void btnStaffSystemListDeleteStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffSystemListDeleteStaffActionPerformed
        int a = tableStaffSystemList.getSelectedRow();
        if (a != -1) {
            DefaultTableModel model = (DefaultTableModel) tableStaffSystemList.getModel();
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
                PreparedStatement st = null;
                st = conn.prepareStatement("delete from NHANVIEN where  IDNV=?");//insert value to database

                st.setString(1, model.getValueAt(a, 0).toString());
                int count = st.executeUpdate();
                if (count > 0) {
                    JOptionPane.showMessageDialog(StaffManagement, "Delete sucessfull!");
                } else {
                    JOptionPane.showMessageDialog(StaffManagement, "Can not Delete !");
                }
                conn.close();
            } catch (Exception ex) {

            }
            model.removeRow(a);
        } else {
            JOptionPane.showMessageDialog(AdminSystemProducers, "Please choose one row");
        }
    }//GEN-LAST:event_btnStaffSystemListDeleteStaffActionPerformed

    private void lblUserDelivery1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserDelivery1MouseClicked
 cbIDHHDelivery.removeAllItems();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");////Create connection to sql server
            PreparedStatement st = conn.prepareStatement("Select IDHH from HANGHOA  ");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
               cbIDHHDelivery.addItem(rs.getString("IDHH").toString());

            }
           st = conn.prepareStatement("Select IDDD from DDNHAN  ");

            rs = st.executeQuery();

            while (rs.next()) {
               cbDeliveryRecieve.addItem(rs.getString("IDDD").toString());

            }

        } catch (Exception ex) {
        }
        
        
        DeliverySystem.setVisible(true);
    DeliverySystem.setSize(500, 1000);
    }//GEN-LAST:event_lblUserDelivery1MouseClicked

    private void btnCheckSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckSubmitActionPerformed
        if (txtCheckIDCheck.getText().isEmpty()) {
            JOptionPane.showMessageDialog(CheckSystem, "ID can not null!!!");
        } else if (!checkIDCheck()) {
            JOptionPane.showMessageDialog(CheckSystem, "ID must be unique!!!");
        } else if (txtCheckQuantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(CheckSystem, "Quantity can not null!!!");
        } else if (!checkInt(txtCheckQuantity.getText().trim())) {
            JOptionPane.showMessageDialog(CheckSystem, "Quantity must be positive integer number!!!");
        } else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3TSAMPE;databaseName=QLKHO;", "sa", "123");
                PreparedStatement st = null;
                st = conn.prepareStatement("select SLhienco from SOLUONGHH where IDHH=?");//insert value to database
                st.setString(1, cbCheckIDHH.getSelectedItem().toString());
                ResultSet rs = st.executeQuery();
                int num = 0;
                while (rs.next()) {
                    if (rs.getString("SLhienco") == null) {
                        num = 0;
                    } else {
                        num = Integer.parseInt(rs.getString("SLhienco").trim());
                    }
                }
                st = conn.prepareStatement("Insert into CHITIETKIEMKE values(?,?,?,?,?,?,?,?)");//insert value to database

                st.setString(1, cbCheckIDHH.getSelectedItem().toString());
                st.setString(2, txtCheckIDCheck.getText());
                st.setString(3, lblSaveU.getText());
                st.setString(4, txtCheckQuantity.getText());
                st.setString(5, txtCheckStatus.getText());

                Date a = new Date();
                st.setString(6, formatDate(a));

                st.setString(7, txtCheckNote.getText().isEmpty() ? null : txtCheckNote.getText());
                if (num != Integer.parseInt(txtCheckQuantity.getText())) {
                    st.setString(8, "Not good");
                } else {
                    st.setString(8, "Good");
                }
                int count = st.executeUpdate();

                if (count > 0) {
                    JOptionPane.showMessageDialog(CheckSystem, "Delivery Successful!!!!!");
                } else {
                    JOptionPane.showMessageDialog(CheckSystem, "Can not Delivery !");
                }
                conn.close();
            } catch (Exception ex) {

            }
            cbCheckIDHH.removeAllItems();
            txtCheckIDCheck.setText("");
            txtCheckQuantity.setText("");
            txtCheckStatus.setText("");
            CheckSystem.dispose();
        }
    }//GEN-LAST:event_btnCheckSubmitActionPerformed

    private void btnCheckCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckCancelActionPerformed
         cbCheckIDHH.removeAllItems();
         CheckSystem.dispose();
         
    }//GEN-LAST:event_btnCheckCancelActionPerformed

    private void cbCheckIDHHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCheckIDHHItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCheckIDHHItemStateChanged

    private void txtLoginPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginPasswordActionPerformed
   txtLoginPassword.setText("");
    }//GEN-LAST:event_txtLoginPasswordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmAssignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAssignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAssignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAssignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmAssignment().setVisible(true);

            }

        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame AddProducer;
    private javax.swing.JFrame AddProduct;
    private javax.swing.JFrame AddStaff;
    private javax.swing.JFrame AdminSystem;
    private javax.swing.JPanel AdminSystemProducers;
    private javax.swing.JPanel AdminSystemProductMangementNote;
    private javax.swing.JPanel AdminSystemProducts;
    private javax.swing.JPanel AdminSystemStaff;
    private javax.swing.JFrame CheckSystem;
    private javax.swing.JFrame DeliverySystem;
    private javax.swing.JPanel Login;
    private javax.swing.JFrame ProductManagement;
    private javax.swing.JFrame ReceiptSystem;
    private javax.swing.JFrame StaffManagement;
    private javax.swing.JFrame UpdateProducer;
    private javax.swing.JFrame UpdateProduct;
    private javax.swing.JFrame UpdateStaff;
    private javax.swing.JFrame UserSystem;
    private javax.swing.JButton btnAddProducer;
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnAddStaff;
    private javax.swing.JButton btnAdminSystemAddProducers;
    private javax.swing.JButton btnAdminSystemAddProducts;
    private javax.swing.JButton btnAdminSystemDeleteProducers;
    private javax.swing.JButton btnAdminSystemDeleteProducts;
    private javax.swing.JButton btnAdminSystemExcelCheck;
    private javax.swing.JButton btnAdminSystemUpdateProducers;
    private javax.swing.JButton btnAdminSystemUpdateProducts;
    private javax.swing.JButton btnCancelAddProducer;
    private javax.swing.JButton btnCancelAddProduct;
    private javax.swing.JButton btnCancelAddStaff;
    private javax.swing.JButton btnCancelUpdateProducer;
    private javax.swing.JButton btnCancelUpdateProduct;
    private javax.swing.JButton btnCancelUpdateStaff;
    private javax.swing.JButton btnCheckCancel;
    private javax.swing.JButton btnCheckSubmit;
    private javax.swing.JButton btnDeliveryCancel;
    private javax.swing.JButton btnDeliveryDelivery;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnReceiptCancel;
    private javax.swing.JButton btnReceiptReceipt;
    private javax.swing.JButton btnStaffSystemListAddStaff;
    private javax.swing.JButton btnStaffSystemListDeleteStaff;
    private javax.swing.JButton btnStaffSystemListUpdateStaff;
    private javax.swing.JButton btnUpdateProducer;
    private javax.swing.JButton btnUpdateProduct;
    private javax.swing.JButton btnUpdateStaff;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbCheckIDHH;
    private javax.swing.JComboBox<String> cbDeliveryRecieve;
    private javax.swing.JComboBox<String> cbIDHHDelivery;
    private javax.swing.JComboBox<String> cbIdUpdateProducer;
    private javax.swing.JComboBox<String> cbIdUpdateProduct;
    private javax.swing.JComboBox<String> cbIdUpdateStaff;
    private javax.swing.JComboBox<String> cbLoginAdminOrUser;
    private javax.swing.JComboBox<String> cbProducerIdAddProduct;
    private javax.swing.JComboBox<String> cbProducerIdUpdateProduct;
    private javax.swing.JComboBox<String> cbReceiptIDProduct;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblAddressAddProducer;
    private javax.swing.JLabel lblAddressAddStaff;
    private javax.swing.JLabel lblAddressUpdateProducer;
    private javax.swing.JLabel lblAddressUpdateStaff;
    private javax.swing.JLabel lblAdminProductManagement;
    private javax.swing.JLabel lblAdminStaffManagement;
    private javax.swing.JLabel lblAtLeastQuantityAddProduct;
    private javax.swing.JLabel lblAtLeastUpdateProduct;
    private javax.swing.JLabel lblCheckNote;
    private javax.swing.JLabel lblCheckQuantity;
    private javax.swing.JLabel lblCheckStatus;
    private javax.swing.JLabel lblDeliveryAddProduct;
    private javax.swing.JLabel lblDeliveryNameHH;
    private javax.swing.JLabel lblDeliveryNote;
    private javax.swing.JLabel lblDeliveryQuantity;
    private javax.swing.JLabel lblDeliveryRecieve;
    private javax.swing.JLabel lblDeliveryUpdateProduct;
    private javax.swing.JLabel lblEmailAddProducer;
    private javax.swing.JLabel lblEmailUpdateProducer;
    private javax.swing.JLabel lblGenderAddStaff;
    private javax.swing.JLabel lblGenderAddStaff1;
    private javax.swing.JLabel lblIDDeliveryBill;
    private javax.swing.JLabel lblIDHHDelivery;
    private javax.swing.JLabel lblIDReceiptBill;
    private javax.swing.JLabel lblIdAddProducer;
    private javax.swing.JLabel lblIdAddProduct;
    private javax.swing.JLabel lblIdAddStaff;
    private javax.swing.JLabel lblIdUpdateProducer;
    private javax.swing.JLabel lblIdUpdateProduct;
    private javax.swing.JLabel lblIdUpdateStaff;
    private javax.swing.JLabel lblNameAddProducer;
    private javax.swing.JLabel lblNameAddProduct;
    private javax.swing.JLabel lblNameAddStaff;
    private javax.swing.JLabel lblNameUpdateAddProduct;
    private javax.swing.JLabel lblNameUpdateStaff;
    private javax.swing.JLabel lblPasswordAddStaff;
    private javax.swing.JLabel lblPhoneAddProducer;
    private javax.swing.JLabel lblPhoneNumberAddStaff;
    private javax.swing.JLabel lblPhoneNumberUpdateStaff;
    private javax.swing.JLabel lblPhoneUpdateProducer;
    private javax.swing.JLabel lblProducerAddProduct;
    private javax.swing.JLabel lblProducerNameAddProduct;
    private javax.swing.JLabel lblProducerNameUpdateProduct;
    private javax.swing.JLabel lblProducerUpdateProduct;
    private javax.swing.JLabel lblReceiptAddProduct;
    private javax.swing.JLabel lblReceiptNameHH;
    private javax.swing.JLabel lblReceiptNote;
    private javax.swing.JLabel lblReceiptQuantity;
    private javax.swing.JLabel lblReceiptUpdateProduct;
    private javax.swing.JLabel lblRecieptIdProduct;
    private javax.swing.JLabel lblSaveU;
    private javax.swing.JLabel lblStartDateAddStaff;
    private javax.swing.JLabel lblStartDateUpdateStaff;
    private javax.swing.JLabel lblUnitAddProduct;
    private javax.swing.JLabel lblUnitUpdateProduct;
    private javax.swing.JLabel lblUpdateAddProducer;
    private javax.swing.JLabel lblUserDelivery;
    private javax.swing.JLabel lblUserDelivery1;
    private javax.swing.JLabel lblUserReceipt;
    private javax.swing.JLabel lblUsernameAddStaff;
    private javax.swing.JLabel lblcheckIDCheck;
    private javax.swing.JLabel lblcheckIDHH;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoFemaleUpdate;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JRadioButton rdoMaleUpdate;
    private javax.swing.JSeparator sprLoginPassword;
    private javax.swing.JSeparator sprLoginUsername;
    private javax.swing.JTable tableAdminSystemCheck;
    private javax.swing.JTable tableAdminSystemProducers;
    private javax.swing.JTable tableAdminSystemProducts;
    private javax.swing.JTable tableStaffSystemList;
    private javax.swing.JTabbedPane tbProductManagement;
    private javax.swing.JTabbedPane tbStaffManagement;
    private javax.swing.JTextField txtAddressAddProducer;
    private javax.swing.JTextField txtAddressAddStaff;
    private javax.swing.JTextField txtAddressUpdateProducer;
    private javax.swing.JTextField txtAddressUpdateStaff;
    private javax.swing.JTextField txtAtLeastAddProduct;
    private javax.swing.JTextField txtAtLeastUpdateProduct;
    private javax.swing.JTextField txtCheckIDCheck;
    private javax.swing.JTextArea txtCheckNote;
    private javax.swing.JTextField txtCheckQuantity;
    private javax.swing.JTextField txtCheckStatus;
    private javax.swing.JTextField txtDeliveryAddProduct;
    private javax.swing.JTextArea txtDeliveryNote;
    private javax.swing.JTextField txtDeliveryQuantity;
    private javax.swing.JTextField txtDeliveryUpdateProduct;
    private javax.swing.JTextField txtEmailAddProducer;
    private javax.swing.JTextField txtEmailUpdateProducer;
    private javax.swing.JTextField txtIDDeliveryBill;
    private javax.swing.JTextField txtIdAddProducer;
    private javax.swing.JTextField txtIdAddProduct;
    private javax.swing.JTextField txtIdAddStaff;
    private javax.swing.JPasswordField txtLoginPassword;
    private javax.swing.JTextField txtLoginUsername;
    private javax.swing.JTextField txtNameAddProducer;
    private javax.swing.JTextField txtNameAddProduct;
    private javax.swing.JTextField txtNameAddStaff;
    private javax.swing.JTextField txtNameUpdateProducer;
    private javax.swing.JTextField txtNameUpdateProduct;
    private javax.swing.JTextField txtNameUpdateStaff;
    private javax.swing.JPasswordField txtPasswordAddStaff;
    private javax.swing.JTextField txtPhoneAddProducer;
    private javax.swing.JTextField txtPhoneAddStaff;
    private javax.swing.JTextField txtPhoneUpdateProducer;
    private javax.swing.JTextField txtPhoneUpdateStaff;
    private javax.swing.JTextField txtReceiptAddProduct;
    private javax.swing.JTextField txtReceiptIDBill;
    private javax.swing.JTextArea txtReceiptNote;
    private javax.swing.JTextField txtReceiptQuantity;
    private javax.swing.JTextField txtReceiptUpdateProduct;
    private javax.swing.JTextField txtStartDateAddStaff;
    private javax.swing.JTextField txtStartDateUpdateStaff;
    private javax.swing.JTextField txtUnitAddProduct;
    private javax.swing.JTextField txtUnitUpdateProduct;
    private javax.swing.JTextField txtUserNameAddStaff;
    // End of variables declaration//GEN-END:variables
}
