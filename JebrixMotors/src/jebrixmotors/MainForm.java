/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jebrixmotors;


import java.awt.Color;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ibnucorp
 */
public class MainForm extends javax.swing.JFrame {
    
    Connection con;
    Statement stat;
    ResultSet res;
    String kode;
    
    
    int xMouse;
    int yMouse;
    int clicked = 1; // clicked tbl
    
    
    
    private void koneksi(){
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
    }
    
    
    public MainForm() {
        initComponents();
        //
        MenuStock.setVisible(false);
        MenuKredit.setVisible(false);
        Pengaturan.setVisible(false);
        MenuNota.setVisible(false);
        // running
        dataFromDataBaseToComboBox();
        dataFromDataBaseToComboBox2();
        dataFromDataBaseToComboBox3();
        
        tambah.setVisible(false);
        btnHapus.setVisible(false);
        btnUbah.setVisible(false);
        txtKode.setText("0");
        
        AutoCompletion.enable(comboBoxJenisMobil);
        AutoCompletion.enable(comboBoxTipeMobil);
        
        PanelKredit.setVisible(false);
        
        
        /////Memanggil Method
        tampilkan_data();
        ////sampe sini
    }
    
    public void refresh(){
        dataFromDataBaseToComboBox();
        dataFromDataBaseToComboBox2();
        dataFromDataBaseToComboBox3();
        
        AutoCompletion.enable(comboBoxJenisMobil);
        AutoCompletion.enable(comboBoxTipeMobil);
    }
    
    public void dataFromDataBaseToComboBox(){
        koneksi();
        try {
            String query = "SELECT * FROM jenis_mobil";
            ResultSet rs = stat.executeQuery(query);
             
            while (rs.next()) {                
                comboBoxJenisMobil.addItem(rs.getString("nama_jenis_mobil"));
            }
             
            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();
            dataFromDataBaseToComboBox2();
             
        } catch (SQLException e) {
        }
        Idjenismobil.setText(""+comboBoxJenisMobil.getSelectedIndex());
    }
    
    public void dataFromDataBaseToComboBox2(){
        koneksi();
        String id_jenis_mobil = Idjenismobil.getText();
        try {
            String query = "SELECT * FROM tipe_mobil";
//            if(jComboBox1.getSelectedIndex()+2 == Integer.parseInt(Idjenismobil.getText())){
//                query = "SELECT * FROM tipe_mobil WHERE id_jenis_mobil='"+id_jenis_mobil+"'";
//            }
            
            ResultSet rs = stat.executeQuery(query);
             
            while (rs.next()) {                
                comboBoxTipeMobil.addItem(rs.getString("nama_tipe_mobil"));
            }
             
            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();
             
        } catch (SQLException e) {
        }
    }
    
    public void dataFromDataBaseToComboBox3(){
        koneksi();
        String id_jenis_mobil = Idjenismobil.getText();
        
        try {
            String query = "SELECT * FROM tipe_mobil";
//            if(jComboBox1.getSelectedIndex()+2 == Integer.parseInt(Idjenismobil.getText())){
//                query = "SELECT * FROM tipe_mobil WHERE id_jenis_mobil='"+id_jenis_mobil+"'";
//            }
            
            ResultSet rs = stat.executeQuery(query);
             
            while (rs.next()) {                
                comboBoxTipeMobil.addItem(rs.getString("nama_tipe_mobil"));
            }
             
            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();
             
        } catch (SQLException e) {
        }
    }
    
    //Membuat Method Untuk Menghubungkan Project Dengan Database
    
    //Membuat Method Untuk Menyimpan Data
    private void simpan_data(){
        koneksi();
        try{
            stat.executeUpdate("INSERT INTO jenis_mobil values("
                    + "'" + txtKode.getText() + "',"
                    + "'" + txtJenisMobil.getText()+"')");
         stat.close();
         reset();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //Membuat Method Untuk Merubah Data Berdasarkan ID
    private void ubah_data() {
        koneksi();
        try {
            stat.executeUpdate("UPDATE jenis_mobil SET "
                    + "nama_jenis_mobil ='" + txtJenisMobil.getText()+"' "
                    + "Where "
                    + "id ='" + txtKode.getText() + "'");
            stat.close();
            reset();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //Membuat Method Untuk Menghapus Data Berdasarkan ID
    private void hapus_data(){
        koneksi();
        try{
            stat.executeUpdate("DELETE FROM jenis_mobil WHERE id ='"+txtKode.getText()+"'");
            stat.close();
            reset();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //Membuat Method Untuk Menampilkan Seluruh Data Pada Tabel
    private void tampilkan_data(){
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("id");
        dtm.addColumn("nama_jenis_mobil");
        try{
            koneksi();
            ResultSet res =  stat.executeQuery("SELECT * from jenis_mobil");
                while(res.next()){
                dtm.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2)
                });
           }
           tabelJenisMobil.setModel(dtm);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //Membuat Method Untuk Mengosongkan Form
    private void reset(){
        try{
            txtKode.setText("0");
            txtJenisMobil.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        MenuKasir = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        comboBoxJenisMobil = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        comboBoxTipeMobil = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        radioCash = new javax.swing.JRadioButton();
        radioTransfer = new javax.swing.JRadioButton();
        radioKredit = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        Idjenismobil = new javax.swing.JLabel();
        PanelKredit = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        comboBoxLama = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        hargaAwalKredit1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        uangMuka = new javax.swing.JFormattedTextField();
        txtAngsuran = new javax.swing.JFormattedTextField();
        txtHargaAKhir = new javax.swing.JFormattedTextField();
        jButton3 = new javax.swing.JButton();
        MenuStock = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtJenisMobil = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelJenisMobil = new javax.swing.JTable();
        btnTambahJenisMobil = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        txtKode = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtJenisMobil1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelJenisMobil1 = new javax.swing.JTable();
        btnTambahTipeMobil = new javax.swing.JButton();
        btnUbah1 = new javax.swing.JButton();
        btnHapus1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        tambah1 = new javax.swing.JButton();
        txtKode1 = new javax.swing.JLabel();
        MenuKredit = new javax.swing.JPanel();
        Pengaturan = new javax.swing.JPanel();
        MenuNota = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete_20px.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 40, 31));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Minimize Window_20px.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.setOpaque(true);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 0, 32, 31));

        jLabel3.setFont(new java.awt.Font("Proxima Nova Lt", 1, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon.png"))); // NOI18N
        jLabel3.setText("  Jebrix Motors");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 30));

        jPanel1.setBackground(new java.awt.Color(99, 110, 114));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(99, 110, 114));
        jLabel4.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Settings_30px.png"))); // NOI18N
        jLabel4.setText("Pengaturan");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.setOpaque(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 240, 50));

        jLabel5.setBackground(new java.awt.Color(99, 110, 114));
        jLabel5.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/POS Terminal_30px.png"))); // NOI18N
        jLabel5.setText("Kasir");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 240, 50));

        jLabel7.setBackground(new java.awt.Color(99, 110, 114));
        jLabel7.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bank Cards_30px.png"))); // NOI18N
        jLabel7.setText("Daftar Kredit");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setOpaque(true);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 240, 50));

        jLabel8.setFont(new java.awt.Font("Proxima Nova Th", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Menu");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 240, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 220, -1));

        jPanel2.setBackground(new java.awt.Color(99, 110, 114));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel2MouseExited(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Logout Rounded Left_30px.png"))); // NOI18N
        jLabel9.setText("Logout");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 140, 40));

        jLabel16.setBackground(new java.awt.Color(99, 110, 114));
        jLabel16.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Box_30px.png"))); // NOI18N
        jLabel16.setText("Kelola Stock");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.setOpaque(true);
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 240, 50));

        btnRefresh.setBackground(new java.awt.Color(99, 110, 114));
        btnRefresh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRefreshMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRefreshMouseExited(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Synchronize_30px.png"))); // NOI18N
        jLabel22.setText("Refresh");

        javax.swing.GroupLayout btnRefreshLayout = new javax.swing.GroupLayout(btnRefresh);
        btnRefresh.setLayout(btnRefreshLayout);
        btnRefreshLayout.setHorizontalGroup(
            btnRefreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRefreshLayout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnRefreshLayout.setVerticalGroup(
            btnRefreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 140, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 240, 440));

        MenuKasir.setBackground(new java.awt.Color(45, 52, 54));

        jLabel10.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jenis Mobil");

        comboBoxJenisMobil.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxJenisMobilItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tipe Mobil");

        jLabel12.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Harga");

        txtHarga.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));

        jLabel13.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Metode Pembayaran");

        buttonGroup1.add(radioCash);
        radioCash.setFont(new java.awt.Font("Proxima Nova Th", 0, 18)); // NOI18N
        radioCash.setForeground(new java.awt.Color(255, 255, 255));
        radioCash.setText("Cash");
        radioCash.setName("cash"); // NOI18N
        radioCash.setOpaque(false);
        radioCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCashActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioTransfer);
        radioTransfer.setFont(new java.awt.Font("Proxima Nova Th", 0, 18)); // NOI18N
        radioTransfer.setForeground(new java.awt.Color(255, 255, 255));
        radioTransfer.setText("Transfer");
        radioTransfer.setName("transfer"); // NOI18N
        radioTransfer.setOpaque(false);
        radioTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTransferActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioKredit);
        radioKredit.setFont(new java.awt.Font("Proxima Nova Th", 0, 18)); // NOI18N
        radioKredit.setForeground(new java.awt.Color(255, 255, 255));
        radioKredit.setText("Kredit");
        radioKredit.setName("kredit"); // NOI18N
        radioKredit.setOpaque(false);
        radioKredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioKreditActionPerformed(evt);
            }
        });

        jButton2.setText("Selesaikan Transaksi");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Idjenismobil.setForeground(new java.awt.Color(45, 52, 54));
        Idjenismobil.setText("jLabel6");

        PanelKredit.setBackground(new java.awt.Color(0, 0, 153));
        PanelKredit.setOpaque(false);
        PanelKredit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Perhitungan Kredit");
        PanelKredit.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel15.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Uang Muka");
        PanelKredit.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        comboBoxLama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jangka Waktu", "3 Bulan", "6 Bulan", "12 Bulan" }));
        comboBoxLama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxLamaActionPerformed(evt);
            }
        });
        PanelKredit.add(comboBoxLama, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 140, 30));

        jLabel18.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Jangka Waktu");
        PanelKredit.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel19.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Angsuran");
        PanelKredit.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel20.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Harga Akhir");
        PanelKredit.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        jButton1.setText("Cek Harga Akhir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        PanelKredit.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 140, 30));

        hargaAwalKredit1.setEditable(false);
        hargaAwalKredit1.setText("-");
        PanelKredit.add(hargaAwalKredit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 140, 30));

        jLabel21.setFont(new java.awt.Font("Proxima Nova Th", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Harga Awal ");
        PanelKredit.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        uangMuka.setEditable(false);
        uangMuka.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        uangMuka.setText("-");
        PanelKredit.add(uangMuka, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 140, 30));

        txtAngsuran.setEditable(false);
        txtAngsuran.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0"))));
        txtAngsuran.setText("-");
        PanelKredit.add(txtAngsuran, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 140, 30));

        txtHargaAKhir.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtHargaAKhir.setText("-");
        PanelKredit.add(txtHargaAKhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 140, 30));

        jButton3.setText("Cek Harga");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuKasirLayout = new javax.swing.GroupLayout(MenuKasir);
        MenuKasir.setLayout(MenuKasirLayout);
        MenuKasirLayout.setHorizontalGroup(
            MenuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuKasirLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(MenuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuKasirLayout.createSequentialGroup()
                        .addComponent(radioCash, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radioKredit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuKasirLayout.createSequentialGroup()
                        .addGroup(MenuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(62, 62, 62)
                        .addGroup(MenuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxTipeMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)))
                    .addGroup(MenuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(radioTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addGroup(MenuKasirLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(53, 53, 53)
                        .addGroup(MenuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Idjenismobil)
                            .addComponent(comboBoxJenisMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(PanelKredit, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuKasirLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(212, 212, 212))
        );
        MenuKasirLayout.setVerticalGroup(
            MenuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuKasirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Idjenismobil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MenuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuKasirLayout.createSequentialGroup()
                        .addGroup(MenuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(comboBoxJenisMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(MenuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(comboBoxTipeMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(MenuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MenuKasirLayout.createSequentialGroup()
                                .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(29, 29, 29)
                                .addGroup(MenuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioCash)
                                    .addComponent(radioTransfer)
                                    .addComponent(radioKredit))
                                .addGap(73, 73, 73)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(MenuKasirLayout.createSequentialGroup()
                        .addComponent(PanelKredit, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        getContentPane().add(MenuKasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 740, 440));

        MenuStock.setBackground(new java.awt.Color(45, 52, 54));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Proxima Nova Th", 0, 18)); // NOI18N
        jLabel17.setText("Nama Jenis Mobil");

        txtJenisMobil.setBackground(new java.awt.Color(220, 220, 220));
        txtJenisMobil.setFont(new java.awt.Font("Proxima Nova Th", 0, 18)); // NOI18N
        txtJenisMobil.setBorder(BorderFactory.createCompoundBorder(
            txtJenisMobil.getBorder(),BorderFactory.createEmptyBorder(5, 5, 5, 5)));

    tabelJenisMobil.setAutoCreateRowSorter(true);
    tabelJenisMobil.setFont(new java.awt.Font("Proxima Nova Th", 0, 12)); // NOI18N
    tabelJenisMobil.setForeground(new java.awt.Color(51, 51, 51));
    tabelJenisMobil.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null},
            {null, null},
            {null, null},
            {null, null},
            {null, null},
            {null, null}
        },
        new String [] {
            "ID", "Nama Jenis Mobil"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.Integer.class, java.lang.Object.class
        };
        boolean[] canEdit = new boolean [] {
            false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    tabelJenisMobil.setRowHeight(25);
    tabelJenisMobil.setShowVerticalLines(false);
    tabelJenisMobil.getTableHeader().setReorderingAllowed(false);
    tabelJenisMobil.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tabelJenisMobilMouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(tabelJenisMobil);
    if (tabelJenisMobil.getColumnModel().getColumnCount() > 0) {
        tabelJenisMobil.getColumnModel().getColumn(0).setMinWidth(20);
        tabelJenisMobil.getColumnModel().getColumn(1).setResizable(false);
    }

    btnTambahJenisMobil.setText("Tambah Jenis Mobil");
    btnTambahJenisMobil.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnTambahJenisMobilActionPerformed(evt);
        }
    });

    btnUbah.setText("Ubah");
    btnUbah.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnUbahActionPerformed(evt);
        }
    });

    btnHapus.setText("Hapus");
    btnHapus.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnHapusActionPerformed(evt);
        }
    });

    jButton4.setText("Batal");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
        }
    });

    tambah.setText("Tambah");
    tambah.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            tambahActionPerformed(evt);
        }
    });

    txtKode.setText("jLabel6");

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtJenisMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addComponent(jLabel17))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(btnTambahJenisMobil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(10, 10, 10))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addComponent(txtKode)
                    .addGap(18, 18, 18)))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGap(32, 32, 32)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addGap(6, 6, 6))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabel17)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtJenisMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTambahJenisMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKode)
                    .addContainerGap())))
    );

    jTabbedPane1.addTab("Jenis Mobil", jPanel4);

    jLabel23.setFont(new java.awt.Font("Proxima Nova Th", 0, 18)); // NOI18N
    jLabel23.setText("Nama Tipe Mobil");

    txtJenisMobil1.setBackground(new java.awt.Color(220, 220, 220));
    txtJenisMobil1.setFont(new java.awt.Font("Proxima Nova Th", 0, 18)); // NOI18N
    txtJenisMobil1.setBorder(BorderFactory.createCompoundBorder(
        txtJenisMobil.getBorder(),BorderFactory.createEmptyBorder(5, 5, 5, 5)));

tabelJenisMobil1.setAutoCreateRowSorter(true);
tabelJenisMobil1.setFont(new java.awt.Font("Proxima Nova Th", 0, 12)); // NOI18N
tabelJenisMobil1.setForeground(new java.awt.Color(51, 51, 51));
tabelJenisMobil1.setModel(new javax.swing.table.DefaultTableModel(
    new Object [][] {
        {null, null},
        {null, null},
        {null, null},
        {null, null},
        {null, null},
        {null, null}
    },
    new String [] {
        "ID", "Nama Tipe Mobil"
    }
    ) {
        Class[] types = new Class [] {
            java.lang.Integer.class, java.lang.Object.class
        };
        boolean[] canEdit = new boolean [] {
            false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    tabelJenisMobil1.setRowHeight(25);
    tabelJenisMobil1.setShowVerticalLines(false);
    tabelJenisMobil1.getTableHeader().setReorderingAllowed(false);
    tabelJenisMobil1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tabelJenisMobil1MouseClicked(evt);
        }
    });
    jScrollPane3.setViewportView(tabelJenisMobil1);
    if (tabelJenisMobil1.getColumnModel().getColumnCount() > 0) {
        tabelJenisMobil1.getColumnModel().getColumn(0).setMinWidth(20);
        tabelJenisMobil1.getColumnModel().getColumn(1).setResizable(false);
    }

    btnTambahTipeMobil.setText("Tambah Tipe Mobil");
    btnTambahTipeMobil.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnTambahTipeMobilActionPerformed(evt);
        }
    });

    btnUbah1.setText("Ubah");
    btnUbah1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnUbah1ActionPerformed(evt);
        }
    });

    btnHapus1.setText("Hapus");
    btnHapus1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnHapus1ActionPerformed(evt);
        }
    });

    jButton5.setText("Batal");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton5ActionPerformed(evt);
        }
    });

    tambah1.setText("Tambah");
    tambah1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            tambah1ActionPerformed(evt);
        }
    });

    txtKode1.setText("jLabel6");

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtJenisMobil1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addComponent(jLabel23))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUbah1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnHapus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(btnTambahTipeMobil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(10, 10, 10))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addComponent(txtKode1)
                    .addGap(18, 18, 18)))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGap(32, 32, 32)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addGap(6, 6, 6))
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addComponent(jLabel23)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtJenisMobil1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTambahTipeMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnUbah1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(btnHapus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKode1)
                    .addContainerGap())))
    );

    jTabbedPane1.addTab("Tipe Mobil", jPanel5);

    javax.swing.GroupLayout MenuStockLayout = new javax.swing.GroupLayout(MenuStock);
    MenuStock.setLayout(MenuStockLayout);
    MenuStockLayout.setHorizontalGroup(
        MenuStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuStockLayout.createSequentialGroup()
            .addContainerGap(37, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );
    MenuStockLayout.setVerticalGroup(
        MenuStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(MenuStockLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPane1)
            .addContainerGap())
    );

    getContentPane().add(MenuStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 740, 440));

    MenuKredit.setBackground(new java.awt.Color(45, 52, 54));

    javax.swing.GroupLayout MenuKreditLayout = new javax.swing.GroupLayout(MenuKredit);
    MenuKredit.setLayout(MenuKreditLayout);
    MenuKreditLayout.setHorizontalGroup(
        MenuKreditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 740, Short.MAX_VALUE)
    );
    MenuKreditLayout.setVerticalGroup(
        MenuKreditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 440, Short.MAX_VALUE)
    );

    getContentPane().add(MenuKredit, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 740, 440));

    Pengaturan.setBackground(new java.awt.Color(45, 52, 54));

    javax.swing.GroupLayout PengaturanLayout = new javax.swing.GroupLayout(Pengaturan);
    Pengaturan.setLayout(PengaturanLayout);
    PengaturanLayout.setHorizontalGroup(
        PengaturanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 740, Short.MAX_VALUE)
    );
    PengaturanLayout.setVerticalGroup(
        PengaturanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 440, Short.MAX_VALUE)
    );

    getContentPane().add(Pengaturan, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 740, 440));

    MenuNota.setBackground(new java.awt.Color(45, 52, 54));

    javax.swing.GroupLayout MenuNotaLayout = new javax.swing.GroupLayout(MenuNota);
    MenuNota.setLayout(MenuNotaLayout);
    MenuNotaLayout.setHorizontalGroup(
        MenuNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 740, Short.MAX_VALUE)
    );
    MenuNotaLayout.setVerticalGroup(
        MenuNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 440, Short.MAX_VALUE)
    );

    getContentPane().add(MenuNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 740, 440));

    setSize(new java.awt.Dimension(958, 469));
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahJenisMobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahJenisMobilActionPerformed
        simpan_data(); //Menginputkan Data
        tampilkan_data(); //Menampilkan Data
        reset();    //Mengosongkan Form
    }//GEN-LAST:event_btnTambahJenisMobilActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        ubah_data(); //Mengubah Data
        tampilkan_data(); //Menampilkan Data
        reset();    //Mengosongkan Form
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        hapus_data(); //Menghapus Data
        tampilkan_data(); //Menampilkan Data
        reset();    //Mengosongkan Form
    }//GEN-LAST:event_btnHapusActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        reset(); //Mengosongkan Form
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabelJenisMobilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelJenisMobilMouseClicked
       
       btnTambahJenisMobil.setVisible(false);
       tambah.setVisible(true);
       btnUbah.setVisible(true);
       btnHapus.setVisible(true);
       
       //Mengambil Data Dari Tabel Lalu Menampilkan Nya Ke Form
       try{
            int row = tabelJenisMobil.getSelectedRow();
            int column = tabelJenisMobil.getSelectedColumn();
            txtKode.setText(tabelJenisMobil.getValueAt(row, 0).toString());
            txtJenisMobil.setText(tabelJenisMobil.getValueAt(row, 1).toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
       
        // Menghilangkan Editable TABEL
        if (!evt.isConsumed()) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Silahkan Edit di Kolom yang Tersedia");    
        }

       
    }//GEN-LAST:event_tabelJenisMobilMouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        MenuKasir.setVisible(false);
        MenuStock.setVisible(true);
        MenuKredit.setVisible(false);
        Pengaturan.setVisible(false);
        MenuNota.setVisible(false);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        MenuKasir.setVisible(true);
        MenuStock.setVisible(false);
        MenuKredit.setVisible(false);
        Pengaturan.setVisible(false);
        MenuNota.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        setVisible(false);
        Login lg = new Login();
        lg.setVisible(true);
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        setState(ICONIFIED);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        this.setLocation(getLocation().x+evt.getX()-xMouse,getLocation().y+evt.getY()-yMouse);
    }//GEN-LAST:event_jPanel3MouseDragged

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        jPanel2.setBackground(new Color(244, 67, 54));
    }//GEN-LAST:event_jPanel2MouseEntered

    private void jPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseExited
        jPanel2.setBackground(new Color(99,110,114));
    }//GEN-LAST:event_jPanel2MouseExited

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jLabel1.setBackground(new Color(251, 85, 72));
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        jLabel2.setBackground(new Color(220, 220, 220));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        jLabel1.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        jLabel2.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jLabel2MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(comboBoxLama.getSelectedItem() == "3 Bulan"){
            float uangMukanya = (float) (Float.parseFloat(hargaAwalKredit1.getText())*0.05);
            float hargaAwal = Float.parseFloat(hargaAwalKredit1.getText());
            float cicilan = (float) (hargaAwal/3);
            
            
            double hargaAkhir = (cicilan*3)+uangMukanya;
            txtHargaAKhir.setText("Rp. "+hargaAkhir+"");
            
        }else if(comboBoxLama.getSelectedItem() == "6 Bulan"){
            
            float uangMukanya = (float) (Float.parseFloat(hargaAwalKredit1.getText())*0.10);
            float hargaAwal = Float.parseFloat(hargaAwalKredit1.getText());
            float cicilan = (float) (hargaAwal/6);
            
            
            double hargaAkhir = (cicilan*6)+uangMukanya;
            txtHargaAKhir.setText("Rp. "+hargaAkhir+"");
            
        }else if(comboBoxLama.getSelectedItem() == "12 Bulan"){
            
            float uangMukanya = (float) (Float.parseFloat(hargaAwalKredit1.getText())*0.15);
            float hargaAwal = Float.parseFloat(hargaAwalKredit1.getText());
            float cicilan = (float) (hargaAwal/12);
            
            
            double hargaAkhir = (cicilan*12)+uangMukanya;
            txtHargaAKhir.setText("Rp. "+hargaAkhir+"");
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        jLabel5.setBackground(new Color(80,88,90));
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        jLabel5.setBackground(new Color(99,110,114));
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        jLabel16.setBackground(new Color(80,88,90));
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        jLabel16.setBackground(new Color(99,110,114));
    }//GEN-LAST:event_jLabel16MouseExited

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        jLabel17.setBackground(new Color(80,88,90));
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        jLabel17.setBackground(new Color(99,110,114));
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        jLabel14.setBackground(new Color(80,88,90));
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        jLabel14.setBackground(new Color(99,110,114));
    }//GEN-LAST:event_jLabel4MouseExited

    private void comboBoxJenisMobilItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxJenisMobilItemStateChanged
        
    }//GEN-LAST:event_comboBoxJenisMobilItemStateChanged

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        txtJenisMobil.setText("");
        btnUbah.setVisible(false);
        btnHapus.setVisible(false);
        btnTambahJenisMobil.setVisible(true);
        tambah.setVisible(false);
        txtKode.setText("0");
    }//GEN-LAST:event_tambahActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String jMobil = comboBoxJenisMobil.getSelectedItem().toString();
        String tMobil = comboBoxTipeMobil.getSelectedItem().toString();
        try {
            koneksi();
            ResultSet res = stat.executeQuery("SELECT * from stock WHERE jenis_mobil='"+jMobil+"' AND tipe_mobil='"+tMobil+"'");
            while (res.next()) {
                String harganya = res.getString("harga");
                txtHarga.setText(harganya);
                hargaAwalKredit1.setText(harganya);
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void radioKreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioKreditActionPerformed
        PanelKredit.setVisible(true);
    }//GEN-LAST:event_radioKreditActionPerformed

    private void radioTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTransferActionPerformed
        PanelKredit.setVisible(false);
        comboBoxLama.setSelectedIndex(0);
        txtAngsuran.setText("");
        uangMuka.setText("");
    }//GEN-LAST:event_radioTransferActionPerformed

    private void radioCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCashActionPerformed
        PanelKredit.setVisible(false);
        comboBoxLama.setSelectedIndex(0);
        txtAngsuran.setText("");
        uangMuka.setText("");
    }//GEN-LAST:event_radioCashActionPerformed

    private void comboBoxLamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxLamaActionPerformed
        if(comboBoxLama.getSelectedItem() == "3 Bulan"){
            float hargaAkhirnya = (float) (Float.parseFloat(hargaAwalKredit1.getText())*0.05);
            uangMuka.setText("Rp. "+hargaAkhirnya+"");
            float hargaAwal = Float.parseFloat(hargaAwalKredit1.getText());
            float cicilan = (float) (hargaAwal/3);
            txtAngsuran.setText("Rp. "+cicilan+"");
            
            float Akhir = cicilan*3+hargaAkhirnya;
            
        
        }else if(comboBoxLama.getSelectedItem() == "6 Bulan"){
            float hargaAkhirnya = (float) (Float.parseFloat(hargaAwalKredit1.getText())*0.10);
            uangMuka.setText("Rp. "+hargaAkhirnya+"");
            float hargaAwal = Float.parseFloat(hargaAwalKredit1.getText());
            float cicilan = (float) (hargaAwal/6);
            txtAngsuran.setText("Rp. "+cicilan+"");
        
        }else if(comboBoxLama.getSelectedItem() == "12 Bulan"){
            float hargaAkhirnya = (float) (Float.parseFloat(hargaAwalKredit1.getText())*0.14);
            uangMuka.setText("Rp. "+hargaAkhirnya+"");
            float hargaAwal = Float.parseFloat(hargaAwalKredit1.getText());
            float cicilan = (float) (hargaAwal/12);
            txtAngsuran.setText("Rp. "+cicilan+"");
            
            float Akhir = cicilan*3+hargaAkhirnya;
        
        }
    }//GEN-LAST:event_comboBoxLamaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int respon = JOptionPane.showConfirmDialog(null, "Ingin Melanjutkan Transaksi ?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        String Harga;
        float uangMukanya=0;
        float cicilan=0;
        
        if (comboBoxLama.getSelectedItem() == "3 Bulan") {
            uangMukanya = (float) (Float.parseFloat(hargaAwalKredit1.getText()) * 0.05);
            float hargaAwal = Float.parseFloat(hargaAwalKredit1.getText());
            cicilan = (float) (hargaAwal / 3);

            float Akhir = cicilan * 3 + uangMukanya;

        } else if (comboBoxLama.getSelectedItem() == "6 Bulan") {
            uangMukanya = (float) (Float.parseFloat(hargaAwalKredit1.getText()) * 0.10);
            float hargaAwal = Float.parseFloat(hargaAwalKredit1.getText());
            cicilan = (float) (hargaAwal / 6);

        } else if (comboBoxLama.getSelectedItem() == "12 Bulan") {
            uangMukanya = (float) (Float.parseFloat(hargaAwalKredit1.getText()) * 0.14);
            float hargaAwal = Float.parseFloat(hargaAwalKredit1.getText());
            cicilan = (float) (hargaAwal / 12);

            float Akhir = cicilan * 3 + uangMukanya;

        }
        
        
        
        if(radioCash.isSelected() || radioTransfer.isSelected() ){
            String radio="";
            if(radioCash.isSelected()){
                radio = radioCash.getText();
            }else if(radioTransfer.isSelected()){
                radio = radioTransfer.getText();
            }
            
            Harga = txtHarga.getText();
            
            if (respon == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Gagal Melanjutkan Transaksi");
            } else if (respon == JOptionPane.YES_OPTION) {
                koneksi();
                try {
                    stat.executeUpdate("INSERT INTO pembelian values("
                            + "'0',"
                            + "'" + comboBoxJenisMobil.getSelectedItem() + "',"
                            + "'" + comboBoxTipeMobil.getSelectedItem() + "',"
                            + "'" + txtHarga.getText() + "',"
                            + "'" + radio + "',"
                            + "'" + comboBoxLama.getSelectedItem() + "',"
                            + "'" + uangMukanya + "',"
                            + "'" + cicilan + "',"
                            + "'" + Harga+ "')");
                    JOptionPane.showMessageDialog(null, "Berhasil Melanjutkan Transaksi");
                    stat.close();
                    reset();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else if (respon == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Gagal Melanjutkan Transaksi");
            }
            
        }else if(radioKredit.isSelected()){
            Harga = txtHargaAKhir.getText();
            
            
            if (respon == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Gagal Melanjutkan Transaksi");
                
            } else if (respon == JOptionPane.YES_OPTION) {
                koneksi();
                try {
                    stat.executeUpdate("INSERT INTO pembelian values("
                            + "'0',"
                            + "'" + comboBoxJenisMobil.getSelectedItem() + "',"
                            + "'" + comboBoxTipeMobil.getSelectedItem() + "',"
                            + "'" + txtHarga.getText() + "',"
                            + "'" + radioKredit.getText()+ "',"
                            + "'" + comboBoxLama.getSelectedItem() + "',"
                            + "'" + uangMukanya + "',"
                            + "'" + cicilan + "',"
                            + "'" + Harga + "')");
                    JOptionPane.showMessageDialog(null, "Berhasil Melanjutkan Transaksi");
                    stat.close();
                    reset();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                
            } else if (respon == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Gagal Melanjutkan Transaksi");
            }
            
        
        }
        
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseClicked
        dispose();
        new MainForm().setVisible(true);
    }//GEN-LAST:event_btnRefreshMouseClicked

    private void btnRefreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseEntered
        btnRefresh.setBackground(new Color(76, 175, 80));
    }//GEN-LAST:event_btnRefreshMouseEntered

    private void btnRefreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseExited
        btnRefresh.setBackground(new Color(99,110,114));
    }//GEN-LAST:event_btnRefreshMouseExited

    private void tabelJenisMobil1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelJenisMobil1MouseClicked

        btnTambahTipeMobil.setVisible(false);
        tambah1.setVisible(true);
        btnUbah1.setVisible(true);
        btnHapus1.setVisible(true);

        //Mengambil Data Dari Tabel Lalu Menampilkan Nya Ke Form
        try {
            int row = tabelJenisMobil1.getSelectedRow();
            int column = tabelJenisMobil1.getSelectedColumn();
            txtKode1.setText(tabelJenisMobil1.getValueAt(row, 0).toString());
            txtJenisMobil1.setText(tabelJenisMobil1.getValueAt(row, 1).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        // Menghilangkan Editable TABEL
        if (!evt.isConsumed()) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Silahkan Edit di Kolom yang Tersedia");
        }


    }//GEN-LAST:event_tabelJenisMobil1MouseClicked

    private void btnTambahTipeMobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahTipeMobilActionPerformed
        simpan_data(); //Menginputkan Data
        tampilkan_data(); //Menampilkan Data
        reset();    //Mengosongkan Form
    }//GEN-LAST:event_btnTambahTipeMobilActionPerformed

    private void btnUbah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbah1ActionPerformed
        ubah_data(); //Mengubah Data
        tampilkan_data(); //Menampilkan Data
        reset();    //Mengosongkan Form
    }//GEN-LAST:event_btnUbah1ActionPerformed

    private void btnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapus1ActionPerformed
        hapus_data(); //Menghapus Data
        tampilkan_data(); //Menampilkan Data
        reset();    //Mengosongkan Form
    }//GEN-LAST:event_btnHapus1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        reset(); //Mengosongkan Form
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah1ActionPerformed
        txtJenisMobil1.setText("");
        btnUbah1.setVisible(false);
        btnHapus1.setVisible(false);
        btnTambahTipeMobil.setVisible(true);
        tambah1.setVisible(false);
        txtKode1.setText("0");
    }//GEN-LAST:event_tambah1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Idjenismobil;
    private javax.swing.JPanel MenuKasir;
    private javax.swing.JPanel MenuKredit;
    private javax.swing.JPanel MenuNota;
    private javax.swing.JPanel MenuStock;
    private javax.swing.JPanel PanelKredit;
    private javax.swing.JPanel Pengaturan;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHapus1;
    private javax.swing.JPanel btnRefresh;
    private javax.swing.JButton btnTambahJenisMobil;
    private javax.swing.JButton btnTambahTipeMobil;
    private javax.swing.JButton btnUbah;
    private javax.swing.JButton btnUbah1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboBoxJenisMobil;
    private javax.swing.JComboBox<String> comboBoxLama;
    private javax.swing.JComboBox<String> comboBoxTipeMobil;
    private javax.swing.JTextField hargaAwalKredit1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton radioCash;
    private javax.swing.JRadioButton radioKredit;
    private javax.swing.JRadioButton radioTransfer;
    private javax.swing.JTable tabelJenisMobil;
    private javax.swing.JTable tabelJenisMobil1;
    private javax.swing.JButton tambah;
    private javax.swing.JButton tambah1;
    private javax.swing.JFormattedTextField txtAngsuran;
    private javax.swing.JFormattedTextField txtHarga;
    private javax.swing.JFormattedTextField txtHargaAKhir;
    private javax.swing.JTextField txtJenisMobil;
    private javax.swing.JTextField txtJenisMobil1;
    private javax.swing.JLabel txtKode;
    private javax.swing.JLabel txtKode1;
    private javax.swing.JFormattedTextField uangMuka;
    // End of variables declaration//GEN-END:variables
}
