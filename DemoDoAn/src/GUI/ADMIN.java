/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BUS_SUA;
import BUS.BUS_THEM;
import BUS.BUS_TINHTOAN;
import BUS.LOADDULIEU;
import DTO.CTHD;
import DTO.HOADON;
import DTO.KHUYENMAI;
import DTO.SANPHAM;
import DTO.TAIKHOAN;
import DTO.NHANVIEN;
import static GUI.GIAODIENCHINH.list;
import static GUI.GIAODIENCHINH.modelcthd;
import static GUI.GIAODIENCHINH.modeldshd;
import static GUI.GIAODIENCHINH.modelqlsp;
import static GUI.GIAODIENCHINH.modelthongke;
import static GUI.GIAODIENCHINH.sdf1;
import static GUI.GIAODIENCHINH.sdf2;
import static GUI.FORM_NHANVIEN.listnv;
import java.awt.CardLayout;
import java.awt.Color;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ADMIN extends javax.swing.JFrame {

    CardLayout card;
    public static ArrayList<TAIKHOAN> listtk = new ArrayList<>();
     public static ArrayList<HOADON> listhd = new ArrayList<>();
    public static ArrayList<CTHD> listcthd = new ArrayList<>();
    public static ArrayList<NHANVIEN> listnv = new ArrayList<>();
    public static DefaultTableModel modeltk , modeldsnv;
   
    
    public static LOADDULIEU load = new LOADDULIEU();
    NumberFormat vnmoney = NumberFormat.getInstance(new Locale("vi", "vn"));

    
    public ADMIN() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        card = (CardLayout) panel.getLayout();
        card.show(panel, "main");
        
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        modeltk = (DefaultTableModel) tblqltk.getModel();
        
        
        tblqltk.getTableHeader().setBackground(new Color(8,112,150));
        tblqltk.getTableHeader().setOpaque(false);
        tblqltk.getTableHeader().setForeground(Color.WHITE);
        
        tblqlsp.getTableHeader().setBackground(new Color(8,112,150));
        tblqlsp.getTableHeader().setOpaque(false);
        tblqlsp.getTableHeader().setForeground(Color.WHITE);
        
        tbldshd.getTableHeader().setBackground(new Color(8,112,150));
        tbldshd.getTableHeader().setOpaque(false);
        tbldshd.getTableHeader().setForeground(Color.WHITE);
        
        tbldsnv.getTableHeader().setBackground(new Color(8,112,150));
        tbldsnv.getTableHeader().setOpaque(false);
    tbldsnv.getTableHeader().setForeground(Color.WHITE);
        
        
        listtk = new LOADDULIEU().getListTaiKhoan();
        for(TAIKHOAN tk : listtk)
        {
            if(tk.getTrangThai().equals("1"))
            {
            modeltk.addRow(new Object[]{tk.getTaiKhoan() , tk.getMaNV() , "Mở"});
            
            }
            else
            {
                modeltk.addRow(new Object[]{tk.getTaiKhoan() , tk.getMaNV() , "Khóa"});
            }
        }
        listnv = new LOADDULIEU().getListThongTinNhanVien();
        
        modelqlsp = (DefaultTableModel) tblqlsp.getModel();
        modelqlsp.setRowCount(0);
        list = new LOADDULIEU().getList();
        for(SANPHAM s : list)
        {
            modelqlsp.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getDonGia()),s.getMaNCC()});
        }
       
        
        
        modeldshd = (DefaultTableModel) tbldshd.getModel();
        modeldshd.setRowCount(0);
        listhd = new LOADDULIEU().getListHD();
        for(HOADON h : listhd)
        {
            try {
              
             modeldshd.addRow(new Object[]{h.getMahd(),h.getManv(),sdf2.format(sdf1.parse(String.valueOf(h.getNgayxuat()))),vnmoney.format(h.getTongtien()),h.getMakm(),h.getMakh()});
             
             
            } catch (ParseException ex) {
                Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        modeldsnv = (DefaultTableModel) tbldsnv.getModel();
        LocListNV();
        
        
        ResetVongTron();
        
    }
    public static void LocListNV()
    {
        modeldsnv.setRowCount(0);
        listnv = new LOADDULIEU().getListThongTinNhanVien();
        for(NHANVIEN nv : listnv)
        {
            try {
                modeldsnv.addRow(new Object[]{nv.getMaNV(),nv.getHoTen(),nv.getGioiTinh(),GIAODIENCHINH.sdf2.format(sdf1.parse(String.valueOf(nv.getNgaySinh()))),nv.getSDT(),nv.getDiaChi(),nv.getEmail()});
            } catch (ParseException ex) {
                Logger.getLogger(ADMIN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void LocListTK()
    {
        modeltk.setRowCount(0);
        listtk = new LOADDULIEU().getListTaiKhoan();
        for(TAIKHOAN tk : listtk)
        {
            if(tk.getTrangThai().equals("1"))
            {
            modeltk.addRow(new Object[]{tk.getTaiKhoan() , tk.getMaNV() , "Mở"});
            
            }
            else
            {
                modeltk.addRow(new Object[]{tk.getTaiKhoan() , tk.getMaNV() , "Khóa"});
            }
        }
    }
    public void ResetVongTron()
    {
        lbTongNV.setText(String.valueOf(new BUS_TINHTOAN().demNV()));
        lbTongSP.setText(String.valueOf(new BUS_TINHTOAN().demSP()));
        lbTongHD.setText(String.valueOf(new BUS_TINHTOAN().demHD()));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbMini = new javax.swing.JLabel();
        lbClose = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        QLTK = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblqltk = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtMaNV = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        lbTaiKhoan = new javax.swing.JLabel();
        lbMatKhau = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnReset = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnChiTiet = new javax.swing.JLabel();
        btnThem = new javax.swing.JLabel();
        btnSua = new javax.swing.JLabel();
        MAIN = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lbTongNV = new javax.swing.JLabel();
        lbTongSP = new javax.swing.JLabel();
        lbTongHD = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnDoiPass = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JLabel();
        btnQLTK = new javax.swing.JLabel();
        btnNhanVien = new javax.swing.JLabel();
        btnSanPham = new javax.swing.JLabel();
        btnHoaDon = new javax.swing.JLabel();
        qlsp = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblqlsp = new javax.swing.JTable();
        txttimkiem = new javax.swing.JTextField();
        refresh = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dshd = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbldshd = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dsnv = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbldsnv = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(61, 145, 106));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        lbMini.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbMini.setForeground(new java.awt.Color(255, 255, 255));
        lbMini.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMini.setText("-");
        lbMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMiniMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbMiniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbMiniMouseExited(evt);
            }
        });

        lbClose.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbClose.setForeground(new java.awt.Color(255, 255, 255));
        lbClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbClose.setText("X");
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCloseMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbCloseMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(950, 950, 950)
                .addComponent(lbMini, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbClose, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbMini, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lbClose, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panel.setLayout(new java.awt.CardLayout());

        QLTK.setBackground(new java.awt.Color(47, 48, 48));
        QLTK.setPreferredSize(new java.awt.Dimension(937, 780));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/arrow.png"))); // NOI18N
        jLabel2.setToolTipText("Quay lại");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        tblqltk.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblqltk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tài khoản", "Mã nhân viên", "Tình trạng"
            }
        ));
        tblqltk.setRowHeight(35);
        tblqltk.getTableHeader().setReorderingAllowed(false);
        tblqltk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblqltkMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblqltk);

        jPanel3.setBackground(new java.awt.Color(47, 48, 48));
        jPanel3.setLayout(null);

        txtMaNV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });
        jPanel3.add(txtMaNV);
        txtMaNV.setBounds(0, 190, 158, 40);

        txtMatKhau.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauActionPerformed(evt);
            }
        });
        jPanel3.add(txtMatKhau);
        txtMatKhau.setBounds(0, 110, 159, 40);

        txtTaiKhoan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jPanel3.add(txtTaiKhoan);
        txtTaiKhoan.setBounds(0, 20, 161, 40);

        lbTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lbTaiKhoan.setText("jLabel5");
        jPanel3.add(lbTaiKhoan);
        lbTaiKhoan.setBounds(0, 20, 160, 40);

        lbMatKhau.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lbMatKhau.setText("jLabel6");
        jPanel3.add(lbMatKhau);
        lbMatKhau.setBounds(0, 110, 160, 40);

        lbMaNV.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbMaNV.setForeground(new java.awt.Color(255, 255, 255));
        lbMaNV.setText("jLabel7");
        jPanel3.add(lbMaNV);
        lbMaNV.setBounds(0, 190, 160, 40);

        txtTrangThai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mở", "Khóa" }));
        txtTrangThai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(txtTrangThai);
        txtTrangThai.setBounds(0, 280, 100, 40);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tài khoản");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Mật khẩu");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Mã nhân viên");

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnResetMousePressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Trạng thái");

        btnChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/chitiet.png"))); // NOI18N
        btnChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnChiTietMousePressed(evt);
            }
        });

        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/them.png"))); // NOI18N
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemMousePressed(evt);
            }
        });

        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/sua.png"))); // NOI18N
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSuaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout QLTKLayout = new javax.swing.GroupLayout(QLTK);
        QLTK.setLayout(QLTKLayout);
        QLTKLayout.setHorizontalGroup(
            QLTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QLTKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(QLTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(QLTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QLTKLayout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QLTKLayout.createSequentialGroup()
                        .addGroup(QLTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(QLTKLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(btnThem)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua)
                                .addGap(13, 13, 13)
                                .addComponent(btnChiTiet)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(QLTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addGroup(QLTKLayout.createSequentialGroup()
                                    .addGroup(QLTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel11))
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(25, 25, 25))))
        );
        QLTKLayout.setVerticalGroup(
            QLTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QLTKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(QLTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QLTKLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QLTKLayout.createSequentialGroup()
                        .addGroup(QLTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(QLTKLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel10)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel11)
                                .addGap(61, 61, 61)
                                .addComponent(jLabel12)
                                .addGap(58, 58, 58)
                                .addComponent(jLabel13))
                            .addGroup(QLTKLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(QLTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(QLTKLayout.createSequentialGroup()
                                .addComponent(btnReset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnChiTiet))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QLTKLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(QLTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(42, 42, 42))))
        );

        panel.add(QLTK, "qltk");

        MAIN.setBackground(new java.awt.Color(47, 48, 48));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 16));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("HÓA ĐƠN");

        lbTongNV.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTongNV.setForeground(new java.awt.Color(0, 153, 255));
        lbTongNV.setText("10");

        lbTongSP.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTongSP.setForeground(new java.awt.Color(51, 255, 0));
        lbTongSP.setText("11");

        lbTongHD.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTongHD.setForeground(new java.awt.Color(255, 0, 0));
        lbTongHD.setText("12");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(96, 176, 244));
        jLabel1.setText("NHÂN VIÊN");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(86, 228, 47));
        jLabel6.setText("SẢN PHẨM");

        btnDoiPass.setBackground(new java.awt.Color(47, 48, 48));
        btnDoiPass.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDoiPass.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDoiPass.setText("ĐỔI MẬT KHẨU");
        btnDoiPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDoiPass.setOpaque(true);
        btnDoiPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDoiPassMousePressed(evt);
            }
        });

        btnDangXuat.setBackground(new java.awt.Color(47, 48, 48));
        btnDangXuat.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setText("ĐĂNG XUẤT");
        btnDangXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDangXuat.setOpaque(true);
        btnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDangXuatMousePressed(evt);
            }
        });

        btnQLTK.setBackground(new java.awt.Color(61, 145, 106));
        btnQLTK.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnQLTK.setForeground(new java.awt.Color(255, 255, 255));
        btnQLTK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnQLTK.setText("QUẢN LÝ TÀI KHOẢN & THÔNG TIN NHÂN VIÊN");
        btnQLTK.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLTK.setOpaque(true);
        btnQLTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnQLTKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQLTKMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQLTKMousePressed(evt);
            }
        });

        btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/userlg.png"))); // NOI18N
        btnNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNhanVienMousePressed(evt);
            }
        });

        btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/splg.png"))); // NOI18N
        btnSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSanPhamMousePressed(evt);
            }
        });

        btnHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/hdlg.png"))); // NOI18N
        btnHoaDon.setText("jLabel7");
        btnHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHoaDonMousePressed(evt);
            }
        });

        javax.swing.GroupLayout MAINLayout = new javax.swing.GroupLayout(MAIN);
        MAIN.setLayout(MAINLayout);
        MAINLayout.setHorizontalGroup(
            MAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MAINLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(MAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(lbTongNV))
                    .addComponent(btnNhanVien))
                .addGap(43, 43, 43)
                .addGroup(MAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(410, 410, 410)
                        .addComponent(lbTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(lbTongSP))
                    .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(119, 119, 119))
            .addGroup(MAINLayout.createSequentialGroup()
                .addGroup(MAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnDoiPass, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btnQLTK, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        MAINLayout.setVerticalGroup(
            MAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MAINLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(MAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoiPass, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(MAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lbTongNV))
                    .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel8))
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(lbTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MAINLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(lbTongSP))
                    .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addComponent(btnQLTK, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel.add(MAIN, "main");

        qlsp.setBackground(new java.awt.Color(47, 48, 48));

        tblqlsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Mã loại", "Số lượng", "Đơn vị tính", "Đơn giá", "Nhà cung cấp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblqlsp.setOpaque(false);
        tblqlsp.setRowHeight(45);
        tblqlsp.setShowVerticalLines(false);
        tblqlsp.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblqlsp);

        txttimkiem.setToolTipText("");
        txttimkiem.setBorder(null);
        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                refreshMousePressed(evt);
            }
        });

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/search.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/arrow.png"))); // NOI18N
        jLabel3.setToolTipText("Quay lại");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout qlspLayout = new javax.swing.GroupLayout(qlsp);
        qlsp.setLayout(qlspLayout);
        qlspLayout.setHorizontalGroup(
            qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qlspLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                    .addGroup(qlspLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refresh)))
                .addContainerGap())
        );
        qlspLayout.setVerticalGroup(
            qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlspLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(qlspLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlspLayout.createSequentialGroup()
                        .addGap(0, 7, Short.MAX_VALUE)
                        .addGroup(qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(qlspLayout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(qlspLayout.createSequentialGroup()
                                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlspLayout.createSequentialGroup()
                                    .addComponent(refresh)
                                    .addGap(11, 11, 11))))))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel.add(qlsp, "qlsp");

        dshd.setBackground(new java.awt.Color(47, 48, 48));

        tbldshd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã nhân viên", "Ngày xuất", "Tổng tiền", "Khuyến mãi", "Khách hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldshd.setRowHeight(45);
        tbldshd.setShowVerticalLines(false);
        tbldshd.getTableHeader().setReorderingAllowed(false);
        tbldshd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldshdMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbldshd);

        jLabel14.setBackground(new java.awt.Color(61, 145, 106));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Xem chi tiết");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.setOpaque(true);
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel14MousePressed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/arrow.png"))); // NOI18N
        jLabel9.setToolTipText("Quay lại");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });

        javax.swing.GroupLayout dshdLayout = new javax.swing.GroupLayout(dshd);
        dshd.setLayout(dshdLayout);
        dshdLayout.setHorizontalGroup(
            dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dshdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dshdLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dshdLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))))
        );
        dshdLayout.setVerticalGroup(
            dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dshdLayout.createSequentialGroup()
                .addGroup(dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dshdLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dshdLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel.add(dshd, "dshd");

        dsnv.setBackground(new java.awt.Color(47, 48, 48));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/arrow.png"))); // NOI18N
        jLabel5.setToolTipText("Quay lại");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        tbldsnv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Họ Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Địa chỉ", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldsnv.setRowHeight(35);
        jScrollPane6.setViewportView(tbldsnv);

        javax.swing.GroupLayout dsnvLayout = new javax.swing.GroupLayout(dsnv);
        dsnv.setLayout(dsnvLayout);
        dsnvLayout.setHorizontalGroup(
            dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dsnvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dsnvLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1035, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE))
                .addContainerGap())
        );
        dsnvLayout.setVerticalGroup(
            dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dsnvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel.add(dsnv, "dsnv");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lbCloseMousePressed

    private void lbCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseExited
        lbClose.setForeground(Color.WHITE);
        lbClose.setOpaque(false);
        lbClose.setBackground(new Color(61,145,106));
    }//GEN-LAST:event_lbCloseMouseExited

    private void lbCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseEntered
        lbClose.setForeground(Color.BLACK);
        lbClose.setOpaque(true);
        lbClose.setBackground(Color.WHITE);
    }//GEN-LAST:event_lbCloseMouseEntered

    private void lbMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseExited
        lbMini.setForeground(Color.WHITE);
        lbMini.setOpaque(false);
        lbMini.setBackground(new Color(61,145,106));
    }//GEN-LAST:event_lbMiniMouseExited

    private void lbMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseEntered
        lbMini.setForeground(Color.BLACK);
        lbMini.setOpaque(true);
        lbMini.setBackground(Color.WHITE);
    }//GEN-LAST:event_lbMiniMouseEntered

    private void lbMiniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseClicked
        this.setExtendedState(ADMIN.ICONIFIED);
    }//GEN-LAST:event_lbMiniMouseClicked

    private void btnQLTKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLTKMousePressed
        card.show(panel, "qltk");
    }//GEN-LAST:event_btnQLTKMousePressed

    private void btnQLTKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLTKMouseExited
        // TODO add your handling code here:
        btnQLTK.setBackground(new Color(61,145,106));
        btnQLTK.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnQLTKMouseExited

    private void btnQLTKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLTKMouseEntered
        // TODO add your handling code here:
        btnQLTK.setBackground(Color.white);
        btnQLTK.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnQLTKMouseEntered

    private void btnDangXuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMousePressed
        int a=JOptionPane.showConfirmDialog(rootPane, "Xác nhận đăng xuất ?");
        if(a==0)
        {
            new LOGIN().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnDangXuatMousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        card.show(panel, "main");
    }//GEN-LAST:event_jLabel2MousePressed

    private void tblqltkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblqltkMousePressed
        // TODO add your handling code here:
        int i = tblqltk.getSelectedRow();
        if(i >= 0)
        {
            TAIKHOAN tk = new TAIKHOAN();
            tk = listtk.get(i);

            lbTaiKhoan.setText(tk.TaiKhoan);
            txtTaiKhoan.setVisible(false);
            lbTaiKhoan.setVisible(true);

            lbMatKhau.setText(tk.MatKhau);
            txtMatKhau.setVisible(false);
            lbMatKhau.setVisible(true);

            lbMaNV.setText(tk.MaNV);
            txtMaNV.setVisible(false);
            lbMaNV.setVisible(true);

            if(tk.getTrangThai().equals("1"))
            {
                txtTrangThai.setSelectedItem("Mở");
            }
            else if(tk.getTrangThai().equals("2"))
            {
                txtTrangThai.setSelectedItem("Khóa");
            }
        }
    }//GEN-LAST:event_tblqltkMousePressed

    private void btnResetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMousePressed
        // TODO add your handling code here:
        txtTaiKhoan.setVisible(true);
        lbTaiKhoan.setVisible(false);
        txtTaiKhoan.setText("");

        txtMatKhau.setVisible(true);
        lbMatKhau.setVisible(false);
        txtMatKhau.setText("");

        txtMaNV.setVisible(true);
        lbMaNV.setVisible(false);
        txtMaNV.setText("");
        txtTrangThai.setSelectedItem("Mở");
    }//GEN-LAST:event_btnResetMousePressed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauActionPerformed

    private void btnDoiPassMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiPassMousePressed
        // TODO add your handling code here:
        new DMK_AD().setVisible(true);
    }//GEN-LAST:event_btnDoiPassMousePressed

    private void btnNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMousePressed
        // TODO add your handling code here:
        card.show(panel, "dsnv");
    }//GEN-LAST:event_btnNhanVienMousePressed

    private void btnChiTietMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChiTietMousePressed
        // TODO add your handling code here:

        int i = tblqltk.getSelectedRow();
        if(i>= 0)
        {
            
            FORM_NHANVIEN nv = new FORM_NHANVIEN();
            nv.setVisible(true);
            
             
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            
            String date = String.valueOf(listnv.get(i).getNgaySinh());
            Date dy = f1.parse(date);
            String cu = f.format(dy);
            Date dd = f.parse(cu);
            nv.lbNgaySinh.setText(f.format(f1.parse(listnv.get(i).getNgaySinh())));
            nv.txtNgaySinh.setDate(dd);
        } catch (ParseException ex) {
            Logger.getLogger(FORM_NHANVIEN.class.getName()).log(Level.SEVERE, null, ex);
        }
            nv.txtMaNV.setText(listtk.get(i).getMaNV());
            nv.lbHoTen.setText(listnv.get(i).getHoTen());
            nv.lbGioiTinh.setText(listnv.get(i).getGioiTinh());
            //NhanVien.testthu.setText(listnv.get(i).getHoTen());
            nv.lbSDT.setText(listnv.get(i).getSDT());
            nv.lbDiaChi.setText(listnv.get(i).getDiaChi());
            nv.lbEmail.setText(listnv.get(i).getEmail());
            
            
            nv.txtHoTen.setText(listnv.get(i).getHoTen());
            nv.txtGioiTinh.setSelectedItem(listnv.get(i).getGioiTinh());
            
            //NhanVien.testthu.setText(listnv.get(i).getHoTen());
            nv.txtSDT.setText(listnv.get(i).getSDT());
            nv.txtDiaChi.setText(listnv.get(i).getDiaChi());
            nv.txtEmail.setText(listnv.get(i).getEmail());
        }
        else 
        {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần xem");
        }

    }//GEN-LAST:event_btnChiTietMousePressed

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased

        String at = txttimkiem.getText();

        at=at.trim();

        modelqlsp.setRowCount(0);

        for(SANPHAM s : list)
        {
            if(s.getTenSP().contains(at)||s.getMaNCC().contains(at)||s.getDonViTinh().contains(at)||s.getMaSP().contains(at)||s.getMaLoai().contains(at))
            {
                modelqlsp.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getDonGia()),s.getMaNCC()});

            }
        }

    }//GEN-LAST:event_txttimkiemKeyReleased

    private void refreshMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMousePressed
        list = new LOADDULIEU().getList();
        modelqlsp.setRowCount(0);
        for(SANPHAM s : list)
        {
            modelqlsp.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getDonGia()),s.getMaNCC()});
        }
    }//GEN-LAST:event_refreshMousePressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        // TODO add your handling code here:
        card.show(panel, "main");
    }//GEN-LAST:event_jLabel3MousePressed

    private void btnSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMousePressed
        // TODO add your handling code here:
        card.show(panel, "qlsp");
    }//GEN-LAST:event_btnSanPhamMousePressed

    private void btnHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMousePressed
        // TODO add your handling code here:
        card.show(panel, "dshd");
    }//GEN-LAST:event_btnHoaDonMousePressed

    private void tbldshdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldshdMouseClicked
       
    }//GEN-LAST:event_tbldshdMouseClicked

    private void jLabel14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MousePressed
        int i = tbldshd.getSelectedRow();
        if(i!=-1)
        {
            FORM_CTHDadmin chitiet = new FORM_CTHDadmin();
            chitiet.setVisible(true);
            chitiet.ma.setText(listhd.get(i).getMahd());
            modelcthd = (DefaultTableModel) chitiet.tblcthd.getModel();
            chitiet.tblcthd.getTableHeader().setBackground(new Color(8,112,150));
            chitiet.tblcthd.getTableHeader().setOpaque(false);
            chitiet.tblcthd.getTableHeader().setForeground(Color.WHITE);
            listcthd =new LOADDULIEU().getListCTHD(listhd.get(i).getMahd());
            Double sum = new BUS_SUA().TongTien(listhd.get(i).getMahd());
            if(String.valueOf(modeldshd.getValueAt(i,4)).contains("%")==false)
            {
                chitiet.lbphantramkm.setText("Không có");
            }
            else
            {
            chitiet.lbphantramkm.setText(String.valueOf(modeldshd.getValueAt(i,4)));
            }
            chitiet.tongtien.setText(vnmoney.format(sum));
            for(CTHD c : listcthd)
            {
                modelcthd.addRow(new Object[]{c.getMasp(),c.getTensp(),c.getSoluong(),vnmoney.format(c.getDongia()),vnmoney.format(c.getThanhtien())});
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"chon dong de xem");
        }
    }//GEN-LAST:event_jLabel14MousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        // TODO add your handling code here:
        card.show(panel, "main");
    }//GEN-LAST:event_jLabel9MousePressed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        // TODO add your handling code here:
        card.show(panel, "main");
    }//GEN-LAST:event_jLabel5MousePressed

    private void btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMousePressed
        // TODO add your handling code here:
        TAIKHOAN tk = new TAIKHOAN();
        tk.setTaiKhoan(txtTaiKhoan.getText());
        tk.setMatKhau(txtMatKhau.getText());
        tk.setMaNV(txtMaNV.getText().toUpperCase());

        if(txtTrangThai.getSelectedItem().equals("Mở"))
        {
            tk.setTrangThai("1");
        }
        else if(txtTrangThai.getSelectedItem().equals("Khóa"))
        {
            tk.setTrangThai("2");
        }

        if(txtTaiKhoan.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập tên tài khoản sau khi bấm nút Reset");
            txtTaiKhoan.requestFocus();
        }
        else if(txtMatKhau.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập tên mật khẩu");
            txtMatKhau.requestFocus();
        }
        else if(txtMaNV.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập tên Mã Sinh Viên");
            txtMaNV.requestFocus();
        }
        else
        {
            int conf = JOptionPane.showConfirmDialog(rootPane, "Bạn muốn thêm tài khoản này");
            if(conf==0)
            {
            if(new BUS_THEM().themTK(tk))
            {

                LocListTK();
                LocListNV();
                btnResetMousePressed(evt);
                ResetVongTron();
                JOptionPane.showMessageDialog(rootPane, "Hãy cập nhật thông tin nhân viên vừa thêm !");
            }
            }
            else if(new BUS_THEM().themTK(tk)==false&&conf==0) {
                JOptionPane.showMessageDialog(rootPane, "Thêm thất bại");
            }

        }
    }//GEN-LAST:event_btnThemMousePressed

    private void btnSuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMousePressed
        // TODO add your handling code here:
        int i = tblqltk.getSelectedRow();
        if( i >= 0)
        {
            
           TAIKHOAN tttk = new TAIKHOAN();
           if(txtTrangThai.getSelectedItem().equals("Mở"))
            {
                tttk.setTrangThai("1");
            }
            else if(txtTrangThai.getSelectedItem().equals("Khóa"))
            {
               tttk.setTrangThai("2");
            }
            
            String tkcu = String.valueOf(modeltk.getValueAt(i, 0));
            System.out.println("trang thai" + tttk.getTrangThai());
            if(new BUS_SUA().suaTK(tkcu, lbTaiKhoan.getText(), lbMatKhau.getText(), lbMaNV.getText(), tttk.getTrangThai()))
            {
                listtk = new LOADDULIEU().getListTaiKhoan();
                modeltk.setRowCount(0);
                for(TAIKHOAN tk : listtk)
                {
//                    if(tk.getTrangThai().equals("1"))
//                    {
//                        modeltk.addRow(new Object[]{tk.getTaiKhoan() , tk.getMaNV() , "Mở"});
//                    }
//                    else if(tk.getTrangThai().equals("2"))
//                    {
//                        modeltk.addRow(new Object[]{tk.getTaiKhoan() , tk.getMaNV() , "Khóa"});
//                    }
                    LocListTK();
                }
                btnResetMousePressed(evt);
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Không sửa được");
            }

        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần thực hiện");
        }
    }//GEN-LAST:event_btnSuaMousePressed
    int mx,my;
    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        mx=evt.getX();
       my=evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
         int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-mx,y-my);
    }//GEN-LAST:event_jPanel2MouseDragged

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ADMIN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MAIN;
    private javax.swing.JPanel QLTK;
    private javax.swing.JLabel btnChiTiet;
    private javax.swing.JLabel btnDangXuat;
    private javax.swing.JLabel btnDoiPass;
    private javax.swing.JLabel btnHoaDon;
    private javax.swing.JLabel btnNhanVien;
    private javax.swing.JLabel btnQLTK;
    private javax.swing.JLabel btnReset;
    private javax.swing.JLabel btnSanPham;
    private javax.swing.JLabel btnSua;
    private javax.swing.JLabel btnThem;
    private javax.swing.JPanel dshd;
    private javax.swing.JPanel dsnv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbMatKhau;
    private javax.swing.JLabel lbMini;
    private javax.swing.JLabel lbTaiKhoan;
    private javax.swing.JLabel lbTongHD;
    private javax.swing.JLabel lbTongNV;
    private javax.swing.JLabel lbTongSP;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel qlsp;
    public javax.swing.JLabel refresh;
    private javax.swing.JTable tbldshd;
    private javax.swing.JTable tbldsnv;
    public static javax.swing.JTable tblqlsp;
    private javax.swing.JTable tblqltk;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JComboBox<String> txtTrangThai;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables

    
}
