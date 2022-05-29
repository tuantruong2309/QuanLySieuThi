/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BUS_SUA;
import BUS.BUS_THEM;
import BUS.BUS_THONGKE;
import BUS.BUS_XOA;
import BUS.LOADDULIEU;
import DTO.KHUYENMAI;
import DTO.SANPHAM;
import DTO.CTHD;
import DTO.HOADON;
import DTO.NGUOIDUNG;

import DAO.DAO;
import DAO.KiemTraLoi;
import DTO.CTPN;
import DTO.KHACHHANG;
import DTO.PHIEUNHAP;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MINH TUAN
 */
public class GIAODIENCHINH extends javax.swing.JFrame {

    public CardLayout card;
    public String pass;
    static NumberFormat vnmoney = NumberFormat.getInstance(new Locale("vi", "vn"));
    static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
    static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    public static DefaultComboBoxModel boxpn;
    public static String matruyen;
    public static DAO d = new DAO();

    //public static DAOPhieuNhap dpn = new DAOPhieuNhap();
    //public static ArrayList<PhieuNhap> listphieunhap = new ArrayList<>();
    //public static ArrayList<ChiTietPhieuNhap> listctpn = new ArrayList<>();
    public static double os;
    public static ArrayList<SANPHAM> list = new ArrayList<>();
    public static ArrayList<HOADON> listhd = new ArrayList<>();
    public static ArrayList<CTHD> listcthd = new ArrayList<>();
    public static ArrayList<CTHD> tktc = new ArrayList<>();
    public static ArrayList<NGUOIDUNG> ttdn = new ArrayList<>();
    public static ArrayList<KHACHHANG> listkh = new ArrayList<>();
    public static ArrayList<KHUYENMAI> listkm = new ArrayList<>();
    public static ArrayList<CTPN> listctpn = new ArrayList<>();
    public static ArrayList<PHIEUNHAP> listphieunhap = new ArrayList<>();
    public static DefaultTableModel modelqlsp, modeldshd, modelcthd, modelthongke, modelcttk, modeltksp, modelkm, modelkhachhang, modelctkh, modelctpn, modelphieunhap;
    int mx, my;

    public GIAODIENCHINH() {

        initComponents();
        boxpn = (DefaultComboBoxModel) combopn.getModel();
        KhoiTaoGiaoDien();
        this.setSize(1310, 727);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        hienthingay();
        hienthigio();
        setHeaderTable();
        getModelTable();

        setThongTinCaNhan();
        setAvatar(ttdn.get(0).getUser());
        LocAll();
    }

    public static void LocListKH() {
        modelkhachhang.setRowCount(0);
        listkh = new LOADDULIEU().getListKhachHang2();
        for (KHACHHANG kh : listkh) {
            modelkhachhang.addRow(new Object[]{kh.getMakh(), kh.getTenkh(), kh.getSdt(), new LOADDULIEU().TongHDcuaKH(kh.getMakh()), vnmoney.format(new LOADDULIEU().TongTienHDcuaKH(kh.getMakh()))});
        }
    }

    public static void LocAll() {
        LocListSP();
        LocListHD();
        LocThongKe();
        LocListKhuyenMai();
        LocListKH();
        LocListCTPN();
        LocListPhieuNhap();
    }

    public void KhoiTaoGiaoDien() {
        txtNgayBD.setDate(new Date());
        day3.setDate(new Date());
        bienmat.setVisible(false);
        card = (CardLayout) panel.getLayout();
        card.show(panel, "qlsp");

    }

    public void setThongTinCaNhan() {
        String userlogin = LOGIN.txtTaiKhoan.getText();
        ttdn = new LOADDULIEU().NguoiDung(userlogin);
        lbhello.setText("Xin chào : " + ttdn.get(0).getHoten());
        ttdnmanv.setText(ttdn.get(0).getManv());
        ttdnhoten.setText(ttdn.get(0).getHoten());
        try {
            ttdnngaysinh.setText(sdf2.format(sdf1.parse(String.valueOf(ttdn.get(0).getNgaysinh()))));
        } catch (ParseException ex) {
            Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
        }
        ttdndiachi.setText(ttdn.get(0).getDiachi());
        ttdnemail.setText(ttdn.get(0).getEmail());
        ttdnsdt.setText(ttdn.get(0).getSdt());
    }

    public void getModelTable() {
        modelqlsp = (DefaultTableModel) tblqlsp.getModel();
        modeldshd = (DefaultTableModel) tbldshd.getModel();
        modelthongke = (DefaultTableModel) tblthongke.getModel();
        modelcttk = (DefaultTableModel) tblcttk.getModel();
        modeltksp = (DefaultTableModel) tbltksp.getModel();
        modelkm = (DefaultTableModel) tblkm.getModel();
        modelkhachhang = (DefaultTableModel) tblkhachhang.getModel();
        modelctkh = (DefaultTableModel) tblctkh.getModel();
        modelctpn = (DefaultTableModel) tblctpn.getModel();
        modelphieunhap = (DefaultTableModel) tblphieunhap.getModel();
    }

    public void setHeaderTable() {
        setHeader(tblqlsp);
        setHeader(tbldshd);
        setHeader(tblthongke);
        setHeader(tblcttk);
        setHeader(tbltksp);
        setHeader(tblkm);
        setHeader(tblkhachhang);
        setHeader(tblctkh);
        setHeader(tblctpn);
        setHeader(tblphieunhap);
    }

    public static void LocListHD() {
        modeldshd.setRowCount(0);
        modelthongke.setRowCount(0);
        listhd = new LOADDULIEU().getListHD();
        for (HOADON h : listhd) {
            try {

                modeldshd.addRow(new Object[]{h.getMahd(), h.getManv(), sdf2.format(sdf1.parse(String.valueOf(h.getNgayxuat()))), vnmoney.format(h.getTongtien()), h.getMakm(), h.getMakh()});
                modelthongke.addRow(new Object[]{h.getMahd(), h.getManv(), sdf2.format(sdf1.parse(String.valueOf(h.getNgayxuat()))), vnmoney.format(h.getTongtien()),});

            } catch (ParseException ex) {
                Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void LocListSP() {
        modelqlsp.setRowCount(0);
        boxpn.removeAllElements();
        list = new LOADDULIEU().getList();
        for (SANPHAM s : list) {
            boxpn.addElement(s.getMaSP() + "   " + s.getTenSP());
            modelqlsp.addRow(new Object[]{s.getMaSP(), s.getTenSP(), s.getMaLoai(), s.getSoLuong(), s.getDonViTinh(), vnmoney.format(s.getDonGia()), s.getMaNCC()});
        }
    }

    public static void LocListPhieuNhap() {
        modelphieunhap.setRowCount(0);
        listphieunhap = new LOADDULIEU().getListPhieuNhap();
        for (PHIEUNHAP pn : listphieunhap) {
            try {
                modelphieunhap.addRow(new Object[]{pn.getMaPN(), sdf2.format(sdf1.parse(String.valueOf(pn.getNgayNhap()))), pn.getMaNV(), vnmoney.format(pn.getTongTien())});
            } catch (ParseException ex) {
                Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void LocThongKe() {
        modeltksp.setRowCount(0);
        tktc = new BUS_THONGKE().ThongKeTatCa();
        for (CTHD h : tktc) {
            modeltksp.addRow(new Object[]{h.getTensp(), h.getSoluong(), vnmoney.format(h.getDongia()), vnmoney.format(h.getThanhtien())});
        }
        lbtksp.setText("Tổng lượng sản phẩm đã bán ra được");
        os = new BUS_THONGKE().TongTatCa();
        lbtongdoanhthu.setText(vnmoney.format(os));
    }

    public static void LocListKhuyenMai() {
        modelkm.setRowCount(0);
        listkm = new LOADDULIEU().getListKhuyenMai();
        for (KHUYENMAI km : listkm) {
            modelkm.addRow(new Object[]{km.getMaKM(), km.getTenCT(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getPhanTram()});
        }
    }

    public static void LocListCTPN() {
        modelctpn.setRowCount(0);
        listctpn = new LOADDULIEU().getListCTPN();
        for (CTPN km : listctpn) {
            modelctpn.addRow(new Object[]{km.getMaSP(), vnmoney.format(km.getGiaNhap()), km.getSoLuong(), vnmoney.format(km.getThanhTien())});
        }
    }

    public static void setHeader(JTable tbl) {
        tbl.getTableHeader().setBackground(new Color(8, 112, 150));
        tbl.getTableHeader().setOpaque(false);
        tbl.getTableHeader().setForeground(Color.WHITE);
    }

    private void hienthingay() {
        Date tod = new Date();
        SimpleDateFormat formatcc = new SimpleDateFormat("dd-M-yyyy");
        lbtoday.setText(formatcc.format(tod));
    }

    private void hienthigio() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date tod = new Date();
                SimpleDateFormat formatcc = new SimpleDateFormat("hh:mm:ss");
                lbtime.setText(formatcc.format(tod));
            }
        }).start();

    }

    public static String taochuoi(int dodai) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < dodai; i++) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    .length())));
        }

        return sb.toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        groupthongke = new javax.swing.ButtonGroup();
        menu = new javax.swing.JPanel();
        lbdshd = new javax.swing.JLabel();
        lbqlsp = new javax.swing.JLabel();
        lbttcn = new javax.swing.JLabel();
        lbdspn = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        lbmenu = new javax.swing.JLabel();
        lbthongke = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        lbkm = new javax.swing.JLabel();
        lbkhachhang = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        dshd = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldshd = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dspn = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblphieunhap = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblctpn = new javax.swing.JTable();
        combopn = new javax.swing.JComboBox<>();
        slnhap = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        dvtpn = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        ttcn = new javax.swing.JPanel();
        pnttcn = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        ttdnmanv = new javax.swing.JTextField();
        ttdnhoten = new javax.swing.JTextField();
        ttdnngaysinh = new javax.swing.JTextField();
        ttdndiachi = new javax.swing.JTextField();
        ttdnsdt = new javax.swing.JTextField();
        ttdnemail = new javax.swing.JTextField();
        bienmat = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        me = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        qlsp = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblqlsp = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        refresh = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        thongke = new javax.swing.JPanel();
        rdo1 = new javax.swing.JRadioButton();
        rdo2 = new javax.swing.JRadioButton();
        rdo3 = new javax.swing.JRadioButton();
        cbthang = new javax.swing.JComboBox<>();
        cbnam = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblthongke = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbtongdoanhthu = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblcttk = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbltksp = new javax.swing.JTable();
        lbtksp = new javax.swing.JLabel();
        day1 = new com.toedter.calendar.JDateChooser();
        day2 = new com.toedter.calendar.JDateChooser();
        day3 = new com.toedter.calendar.JDateChooser();
        ctkm = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblkm = new javax.swing.JTable();
        txtTenCT = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        btnThemKM = new javax.swing.JLabel();
        btnXoaKM = new javax.swing.JLabel();
        btnReset1 = new javax.swing.JLabel();
        txtPhanTram = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtNgayBD = new com.toedter.calendar.JDateChooser();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        khachhang = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblkhachhang = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblctkh = new javax.swing.JTable();
        txtsdtkh = new javax.swing.JTextField();
        txttenkh = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        task = new javax.swing.JPanel();
        lbMini = new javax.swing.JLabel();
        lbClose = new javax.swing.JLabel();
        lbtoday = new javax.swing.JLabel();
        lbtime = new javax.swing.JLabel();
        lbhello = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        menu.setBackground(new java.awt.Color(61, 145, 106));
        menu.setLayout(null);

        lbdshd.setBackground(new java.awt.Color(255, 255, 255));
        lbdshd.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbdshd.setForeground(new java.awt.Color(255, 255, 255));
        lbdshd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbdshd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconhd.png"))); // NOI18N
        lbdshd.setText("DANH SÁCH HÓA ĐƠN");
        lbdshd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbdshdMousePressed(evt);
            }
        });
        menu.add(lbdshd);
        lbdshd.setBounds(0, 260, 240, 50);

        lbqlsp.setBackground(new java.awt.Color(255, 255, 255));
        lbqlsp.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbqlsp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbqlsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconsanpham.png"))); // NOI18N
        lbqlsp.setText("QUẢN LÝ SẢN PHẨM");
        lbqlsp.setOpaque(true);
        lbqlsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbqlspMousePressed(evt);
            }
        });
        menu.add(lbqlsp);
        lbqlsp.setBounds(0, 207, 240, 50);

        lbttcn.setBackground(new java.awt.Color(255, 255, 255));
        lbttcn.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbttcn.setForeground(new java.awt.Color(255, 255, 255));
        lbttcn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbttcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconnv.png"))); // NOI18N
        lbttcn.setText("THÔNG TIN CÁ NHÂN");
        lbttcn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbttcnMousePressed(evt);
            }
        });
        menu.add(lbttcn);
        lbttcn.setBounds(0, 510, 240, 50);

        lbdspn.setBackground(new java.awt.Color(255, 255, 255));
        lbdspn.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbdspn.setForeground(new java.awt.Color(255, 255, 255));
        lbdspn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbdspn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconphieunhap.png"))); // NOI18N
        lbdspn.setText("PHIẾU NHẬP HÀNG");
        lbdspn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbdspnMousePressed(evt);
            }
        });
        menu.add(lbdspn);
        lbdspn.setBounds(0, 310, 240, 50);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/logo.png"))); // NOI18N
        menu.add(logo);
        logo.setBounds(26, 29, 190, 160);

        lbmenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/menu.png"))); // NOI18N
        lbmenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbmenuMousePressed(evt);
            }
        });
        menu.add(lbmenu);
        lbmenu.setBounds(180, 670, 50, 50);

        lbthongke.setBackground(new java.awt.Color(255, 255, 255));
        lbthongke.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbthongke.setForeground(new java.awt.Color(255, 255, 255));
        lbthongke.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbthongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconthongke.png"))); // NOI18N
        lbthongke.setText("THỐNG KÊ DOANH THU");
        lbthongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbthongkeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbthongkeMouseReleased(evt);
            }
        });
        menu.add(lbthongke);
        lbthongke.setBounds(0, 360, 240, 50);

        logout.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/logout.png"))); // NOI18N
        logout.setText("ĐĂNG XUẤT");
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoutMousePressed(evt);
            }
        });
        menu.add(logout);
        logout.setBounds(10, 670, 140, 50);

        lbkm.setBackground(new java.awt.Color(255, 255, 255));
        lbkm.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbkm.setForeground(new java.awt.Color(255, 255, 255));
        lbkm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbkm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/coupon.png"))); // NOI18N
        lbkm.setText("KHUYẾN MÃI");
        lbkm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbkmMousePressed(evt);
            }
        });
        menu.add(lbkm);
        lbkm.setBounds(0, 410, 240, 50);

        lbkhachhang.setBackground(new java.awt.Color(255, 255, 255));
        lbkhachhang.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbkhachhang.setForeground(new java.awt.Color(255, 255, 255));
        lbkhachhang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbkhachhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/customer.png"))); // NOI18N
        lbkhachhang.setText("KHÁCH HÀNG");
        lbkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbkhachhangMousePressed(evt);
            }
        });
        menu.add(lbkhachhang);
        lbkhachhang.setBounds(0, 460, 240, 50);

        getContentPane().add(menu);
        menu.setBounds(0, 0, 240, 730);

        panel.setBackground(new java.awt.Color(0, 0, 0));
        panel.setLayout(new java.awt.CardLayout());

        dshd.setBackground(new java.awt.Color(47, 48, 48));

        tbldshd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã nhân viên", "Ngày xuất", "Tổng tiền", "Khuyến mãi", "Khách hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
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
        jScrollPane2.setViewportView(tbldshd);

        jLabel1.setBackground(new java.awt.Color(61, 145, 106));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/plus.png"))); // NOI18N
        jLabel1.setText("Tạo hóa đơn");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(61, 145, 106));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Xem chi tiết");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.setOpaque(true);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(61, 145, 106));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/pdf.png"))); // NOI18N
        jLabel8.setText("Xuất PDF");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.setOpaque(true);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });

        javax.swing.GroupLayout dshdLayout = new javax.swing.GroupLayout(dshd);
        dshd.setLayout(dshdLayout);
        dshdLayout.setHorizontalGroup(
            dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(dshdLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(dshdLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dshdLayout.setVerticalGroup(
            dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dshdLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel.add(dshd, "dshd");

        dspn.setBackground(new java.awt.Color(47, 48, 48));

        tblphieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu nhập", "Ngày nhập", "Nhân viên nhập", "Tổng tiền"
            }
        ));
        tblphieunhap.setRowHeight(35);
        jScrollPane10.setViewportView(tblphieunhap);

        tblctpn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Giá nhập", "Số lượng", "Thành tiền"
            }
        ));
        tblctpn.setRowHeight(35);
        jScrollPane9.setViewportView(tblctpn);

        combopn.setMaximumRowCount(100);
        combopn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combopnItemStateChanged(evt);
            }
        });
        combopn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                combopnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                combopnFocusLost(evt);
            }
        });
        combopn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combopnActionPerformed(evt);
            }
        });

        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Số lượng nhập");

        dvtpn.setBackground(new java.awt.Color(255, 255, 255));
        dvtpn.setForeground(new java.awt.Color(255, 255, 255));
        dvtpn.setText("jLabel45");

        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Đơn vị tính :");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Chọn sản phẩm");

        jLabel47.setBackground(new java.awt.Color(61, 145, 106));
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/plus.png"))); // NOI18N
        jLabel47.setText("Thêm");
        jLabel47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel47.setOpaque(true);
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel47MousePressed(evt);
            }
        });

        jLabel48.setBackground(new java.awt.Color(61, 145, 106));
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/add.png"))); // NOI18N
        jLabel48.setText("Tạo phiếu nhập");
        jLabel48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel48.setOpaque(true);
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel48MousePressed(evt);
            }
        });

        jLabel49.setBackground(new java.awt.Color(61, 145, 106));
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/xls.png"))); // NOI18N
        jLabel49.setText("Xuất Excel");
        jLabel49.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel49.setOpaque(true);
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel49MousePressed(evt);
            }
        });

        jLabel50.setBackground(new java.awt.Color(61, 145, 106));
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Reset phiếu nhập");
        jLabel50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel50.setOpaque(true);
        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel50MousePressed(evt);
            }
        });

        javax.swing.GroupLayout dspnLayout = new javax.swing.GroupLayout(dspn);
        dspn.setLayout(dspnLayout);
        dspnLayout.setHorizontalGroup(
            dspnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dspnLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(dspnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dspnLayout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(dspnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dspnLayout.createSequentialGroup()
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dvtpn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dspnLayout.createSequentialGroup()
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(combopn, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(slnhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dspnLayout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane10))
                .addContainerGap())
        );
        dspnLayout.setVerticalGroup(
            dspnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dspnLayout.createSequentialGroup()
                .addGroup(dspnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dspnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel46)
                        .addGap(11, 11, 11)
                        .addComponent(combopn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(dspnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addComponent(dvtpn))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(dspnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dspnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel.add(dspn, "dspn");

        ttcn.setBackground(new java.awt.Color(47, 48, 48));

        pnttcn.setBackground(new java.awt.Color(55, 56, 55));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("THÔNG TIN CÁ NHÂN");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("_________________________________");

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("_________________________________");

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("_________________________________");

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("_________________________________");

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("_________________________________");

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("_________________________________");

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("EMAIL");

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("MÃ NHÂN VIÊN");

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("HỌ TÊN");

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("NGÀY SINH");

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("ĐỊA CHỈ");

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("SỐ ĐIỆN THOẠI");

        ttdnmanv.setEditable(false);
        ttdnmanv.setForeground(new java.awt.Color(255, 255, 255));
        ttdnmanv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttdnmanv.setBorder(null);
        ttdnmanv.setOpaque(false);

        ttdnhoten.setEditable(false);
        ttdnhoten.setForeground(new java.awt.Color(255, 255, 255));
        ttdnhoten.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttdnhoten.setBorder(null);
        ttdnhoten.setOpaque(false);

        ttdnngaysinh.setEditable(false);
        ttdnngaysinh.setForeground(new java.awt.Color(255, 255, 255));
        ttdnngaysinh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttdnngaysinh.setBorder(null);
        ttdnngaysinh.setOpaque(false);

        ttdndiachi.setEditable(false);
        ttdndiachi.setForeground(new java.awt.Color(255, 255, 255));
        ttdndiachi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttdndiachi.setBorder(null);
        ttdndiachi.setOpaque(false);

        ttdnsdt.setEditable(false);
        ttdnsdt.setForeground(new java.awt.Color(255, 255, 255));
        ttdnsdt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttdnsdt.setBorder(null);
        ttdnsdt.setOpaque(false);

        ttdnemail.setEditable(false);
        ttdnemail.setForeground(new java.awt.Color(255, 255, 255));
        ttdnemail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttdnemail.setBorder(null);
        ttdnemail.setOpaque(false);

        bienmat.setText("jButton2");
        bienmat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bienmatActionPerformed(evt);
            }
        });

        jLabel30.setBackground(new java.awt.Color(61, 145, 106));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Đổi mật khẩu");
        jLabel30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel30.setOpaque(true);
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel30MousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnttcnLayout = new javax.swing.GroupLayout(pnttcn);
        pnttcn.setLayout(pnttcnLayout);
        pnttcnLayout.setHorizontalGroup(
            pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnttcnLayout.createSequentialGroup()
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnttcnLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ttdnmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnttcnLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ttdnhoten, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnttcnLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ttdnngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnttcnLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ttdndiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnttcnLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ttdnsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnttcnLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ttdnemail, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bienmat)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        pnttcnLayout.setVerticalGroup(
            pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnttcnLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttdnmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel19)))
                .addGap(30, 30, 30)
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel23))
                    .addComponent(ttdnhoten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttdnngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)))
                .addGap(30, 30, 30)
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttdndiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel21)))
                .addGap(30, 30, 30)
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttdnsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel22)))
                .addGap(30, 30, 30)
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttdnemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel20)))
                .addGap(32, 32, 32)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(bienmat))
        );

        me.setBackground(new java.awt.Color(153, 153, 153));
        me.setOpaque(true);

        jLabel32.setBackground(new java.awt.Color(61, 145, 106));
        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Cập nhật");
        jLabel32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel32.setOpaque(true);
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel32MousePressed(evt);
            }
        });

        jLabel38.setBackground(new java.awt.Color(61, 145, 106));
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Chọn ảnh đại diện");
        jLabel38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel38.setOpaque(true);
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel38MousePressed(evt);
            }
        });

        javax.swing.GroupLayout ttcnLayout = new javax.swing.GroupLayout(ttcn);
        ttcn.setLayout(ttcnLayout);
        ttcnLayout.setHorizontalGroup(
            ttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ttcnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnttcn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(me, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ttcnLayout.setVerticalGroup(
            ttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ttcnLayout.createSequentialGroup()
                .addComponent(pnttcn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(ttcnLayout.createSequentialGroup()
                .addComponent(me, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224))
        );

        panel.add(ttcn, "ttcn");

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
        jScrollPane1.setViewportView(tblqlsp);

        jLabel3.setBackground(new java.awt.Color(61, 145, 106));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/add.png"))); // NOI18N
        jLabel3.setText("Thêm");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.setOpaque(true);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(61, 145, 106));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/bin.png"))); // NOI18N
        jLabel4.setText(" Xóa");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.setOpaque(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(61, 145, 106));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/edit.png"))); // NOI18N
        jLabel5.setText(" Sửa");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

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

        jLabel37.setBackground(new java.awt.Color(61, 145, 106));
        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Thông tin khác");
        jLabel37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel37.setOpaque(true);
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel37MousePressed(evt);
            }
        });

        javax.swing.GroupLayout qlspLayout = new javax.swing.GroupLayout(qlsp);
        qlsp.setLayout(qlspLayout);
        qlspLayout.setHorizontalGroup(
            qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qlspLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlspLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(refresh)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addContainerGap())
        );
        qlspLayout.setVerticalGroup(
            qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlspLayout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel31)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(refresh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel.add(qlsp, "qlsp");

        thongke.setBackground(new java.awt.Color(47, 48, 48));

        groupthongke.add(rdo1);
        rdo1.setForeground(new java.awt.Color(255, 255, 255));
        rdo1.setSelected(true);
        rdo1.setText("Theo khoảng thời gian");
        rdo1.setOpaque(false);

        groupthongke.add(rdo2);
        rdo2.setForeground(new java.awt.Color(255, 255, 255));
        rdo2.setText("Theo tháng");
        rdo2.setOpaque(false);

        groupthongke.add(rdo3);
        rdo3.setForeground(new java.awt.Color(255, 255, 255));
        rdo3.setText("Theo ngày");
        rdo3.setOpaque(false);

        cbthang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cbnam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022", "2023" }));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Từ");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Đến");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tháng");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Năm");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Ngày");

        jLabel16.setBackground(new java.awt.Color(61, 145, 106));
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Lọc");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.setOpaque(true);
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel16MousePressed(evt);
            }
        });

        tblthongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã NV", "Ngày xuất", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblthongke.setRowHeight(40);
        tblthongke.setShowVerticalLines(false);
        tblthongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblthongkeMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblthongke);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel17MousePressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Tổng");

        lbtongdoanhthu.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbtongdoanhthu.setForeground(new java.awt.Color(255, 255, 255));
        lbtongdoanhthu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbtongdoanhthu.setText("0");

        tblcttk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblcttk.setRowHeight(40);
        jScrollPane4.setViewportView(tblcttk);

        tbltksp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Tổng số lượng bán ra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbltksp.setRowHeight(40);
        jScrollPane5.setViewportView(tbltksp);

        lbtksp.setForeground(new java.awt.Color(255, 255, 255));
        lbtksp.setText("jLabel2");

        javax.swing.GroupLayout thongkeLayout = new javax.swing.GroupLayout(thongke);
        thongke.setLayout(thongkeLayout);
        thongkeLayout.setHorizontalGroup(
            thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongkeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdo1)
                    .addComponent(rdo2)
                    .addComponent(rdo3)
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel17))
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel15))
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(thongkeLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(thongkeLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))))
                        .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(thongkeLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbthang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbnam, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(thongkeLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(day1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(day2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(day3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(46, 46, 46)
                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbtongdoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbtksp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        thongkeLayout.setVerticalGroup(
            thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongkeLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbtksp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(thongkeLayout.createSequentialGroup()
                                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(thongkeLayout.createSequentialGroup()
                                        .addComponent(rdo1)
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabel11))
                                    .addComponent(day1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(jLabel12))
                            .addComponent(day2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(rdo2)
                        .addGap(11, 11, 11)
                        .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbthang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbnam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addComponent(rdo3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(day3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, Short.MAX_VALUE)
                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtongdoanhthu)
                    .addComponent(jLabel18))
                .addGap(28, 28, 28))
        );

        panel.add(thongke, "tk");

        ctkm.setBackground(new java.awt.Color(47, 48, 48));

        tblkm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khuyến Mãi", "Tên CT Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giá trị % "
            }
        ));
        tblkm.setRowHeight(30);
        tblkm.getTableHeader().setReorderingAllowed(false);
        tblkm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblkmMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblkm);

        txtTenCT.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Tên CTKM ");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Ngày bắt đầu");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Ngày kết thúc");

        btnThemKM.setBackground(new java.awt.Color(61, 145, 106));
        btnThemKM.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThemKM.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/plus.png"))); // NOI18N
        btnThemKM.setText(" Thêm");
        btnThemKM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemKM.setOpaque(true);
        btnThemKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemKMMousePressed(evt);
            }
        });

        btnXoaKM.setBackground(new java.awt.Color(61, 145, 106));
        btnXoaKM.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnXoaKM.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaKM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnXoaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/bin.png"))); // NOI18N
        btnXoaKM.setText(" Xóa");
        btnXoaKM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaKM.setOpaque(true);
        btnXoaKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnXoaKMMousePressed(evt);
            }
        });

        btnReset1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        btnReset1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReset1MousePressed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Giá trị (%)");

        javax.swing.GroupLayout ctkmLayout = new javax.swing.GroupLayout(ctkm);
        ctkm.setLayout(ctkmLayout);
        ctkmLayout.setHorizontalGroup(
            ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ctkmLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ctkmLayout.createSequentialGroup()
                        .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(btnThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenCT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhanTram, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ctkmLayout.createSequentialGroup()
                                .addComponent(btnXoaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset1))))
                    .addGroup(ctkmLayout.createSequentialGroup()
                        .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        ctkmLayout.setVerticalGroup(
            ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ctkmLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ctkmLayout.createSequentialGroup()
                        .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenCT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))
                        .addGap(45, 45, 45)
                        .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ctkmLayout.createSequentialGroup()
                                .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(txtPhanTram, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnXoaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnReset1)))
                            .addGroup(ctkmLayout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel36)))))
                .addGap(33, 33, 33))
        );

        panel.add(ctkm, "ctkm");

        khachhang.setBackground(new java.awt.Color(47, 48, 48));

        tblkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Họ tên", "Số điện thoại", "Tổng hóa đơn", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblkhachhang.setRowHeight(40);
        tblkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblkhachhangMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(tblkhachhang);

        tblctkh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày mua", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblctkh.setRowHeight(40);
        tblctkh.setShowVerticalLines(false);
        jScrollPane8.setViewportView(tblctkh);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Số điện thoại");

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Họ tên khách hàng");

        jLabel40.setBackground(new java.awt.Color(61, 145, 106));
        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Thêm");
        jLabel40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel40.setOpaque(true);
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel40MousePressed(evt);
            }
        });

        jLabel41.setBackground(new java.awt.Color(61, 145, 106));
        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Xóa");
        jLabel41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel41.setOpaque(true);
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel41MousePressed(evt);
            }
        });

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        jLabel42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel42MousePressed(evt);
            }
        });

        jLabel43.setBackground(new java.awt.Color(61, 145, 106));
        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Sửa");
        jLabel43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel43.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel43.setOpaque(true);
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel43MousePressed(evt);
            }
        });

        javax.swing.GroupLayout khachhangLayout = new javax.swing.GroupLayout(khachhang);
        khachhang.setLayout(khachhangLayout);
        khachhangLayout.setHorizontalGroup(
            khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachhangLayout.createSequentialGroup()
                .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(khachhangLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel39))
                    .addGroup(khachhangLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(khachhangLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(khachhangLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))
                            .addGroup(khachhangLayout.createSequentialGroup()
                                .addComponent(txtsdtkh, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel42))
                            .addGroup(khachhangLayout.createSequentialGroup()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        khachhangLayout.setVerticalGroup(
            khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachhangLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel39)
                .addGap(9, 9, 9)
                .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(khachhangLayout.createSequentialGroup()
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsdtkh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panel.add(khachhang, "nsx");

        getContentPane().add(panel);
        panel.setBounds(240, 60, 1070, 670);

        task.setBackground(new java.awt.Color(47, 48, 48));
        task.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                taskMouseDragged(evt);
            }
        });
        task.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taskMousePressed(evt);
            }
        });
        task.setLayout(null);

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
        task.add(lbMini);
        lbMini.setBounds(1150, 0, 80, 60);

        lbClose.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbClose.setForeground(new java.awt.Color(255, 255, 255));
        lbClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbClose.setText("X");
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCloseMouseExited(evt);
            }
        });
        task.add(lbClose);
        lbClose.setBounds(1230, 0, 80, 60);

        lbtoday.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lbtoday.setForeground(new java.awt.Color(255, 255, 255));
        lbtoday.setText("date");
        task.add(lbtoday);
        lbtoday.setBounds(890, 0, 120, 50);

        lbtime.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lbtime.setForeground(new java.awt.Color(255, 255, 255));
        lbtime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtime.setText("time");
        task.add(lbtime);
        lbtime.setBounds(1020, 0, 120, 50);

        lbhello.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lbhello.setForeground(new java.awt.Color(255, 255, 255));
        lbhello.setText("Xin chào : ");
        task.add(lbhello);
        lbhello.setBounds(260, 5, 510, 40);

        getContentPane().add(task);
        task.setBounds(0, 0, 1310, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

    }//GEN-LAST:event_jLabel3MouseClicked

    private void lbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseClicked
        int a = JOptionPane.showConfirmDialog(rootPane, "Xác nhận thoát chương trình ?");
        if (a == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_lbCloseMouseClicked

    private void lbMiniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseClicked
        this.setExtendedState(GIAODIENCHINH.ICONIFIED);
    }//GEN-LAST:event_lbMiniMouseClicked

    private void lbCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseEntered
        lbClose.setForeground(Color.BLACK);
        lbClose.setOpaque(true);
        lbClose.setBackground(Color.WHITE);
    }//GEN-LAST:event_lbCloseMouseEntered

    private void lbCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseExited
        lbClose.setForeground(Color.WHITE);
        lbClose.setOpaque(false);
        lbClose.setBackground(new Color(61, 145, 106));
    }//GEN-LAST:event_lbCloseMouseExited

    private void lbMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseEntered
        lbMini.setForeground(Color.BLACK);
        lbMini.setOpaque(true);
        lbMini.setBackground(Color.WHITE);
    }//GEN-LAST:event_lbMiniMouseEntered

    private void lbMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseExited
        lbMini.setForeground(Color.WHITE);
        lbMini.setOpaque(false);
        lbMini.setBackground(new Color(61, 145, 106));
    }//GEN-LAST:event_lbMiniMouseExited

    private void taskMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - mx, y - my);
    }//GEN-LAST:event_taskMouseDragged

    private void taskMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskMousePressed
        mx = evt.getX();
        my = evt.getY();
    }//GEN-LAST:event_taskMousePressed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked

    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked

    }//GEN-LAST:event_jLabel5MouseClicked

    private void lbqlspMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqlspMousePressed
        card.show(panel, "qlsp");

        lbqlsp.setBackground(Color.WHITE);
        lbqlsp.setOpaque(true);
        lbqlsp.setForeground(Color.BLACK);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);

        lbttcn.setForeground(Color.WHITE);
        lbttcn.setOpaque(false);

        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);

        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);

        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
    }//GEN-LAST:event_lbqlspMousePressed

    private void lbdshdMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdshdMousePressed
        card.show(panel, "dshd");
        lbdshd.setBackground(Color.WHITE);
        lbdshd.setOpaque(true);
        lbdshd.setForeground(Color.BLACK);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);

        lbttcn.setForeground(Color.WHITE);
        lbttcn.setOpaque(false);

        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);

        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);

        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
    }//GEN-LAST:event_lbdshdMousePressed

    private void lbdspnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdspnMousePressed
        card.show(panel, "dspn");
        lbdspn.setBackground(Color.WHITE);
        lbdspn.setOpaque(true);
        lbdspn.setForeground(Color.BLACK);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbttcn.setForeground(Color.WHITE);
        lbttcn.setOpaque(false);

        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);

        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);

        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
    }//GEN-LAST:event_lbdspnMousePressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        FORM_THEM t = new FORM_THEM();
        t.setVisible(true);
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        int i = tblqlsp.getSelectedRow();

        if (i != -1) {
            String masua = String.valueOf(modelqlsp.getValueAt(i, 0));
            int vitri = 0;
            String mp = "", ml = "";
            for (SANPHAM s : list) {
                if (masua.equals(s.getMaSP())) {
                    vitri = list.indexOf(s);
                    mp = s.getMaNCC();
                    ml = s.getMaLoai();
                    System.out.println("Ma nsx la " + mp);
                    break;
                }
            }
            FORM_SUA r = new FORM_SUA();
            r.txtmasp.setText(list.get(vitri).getMaSP());
            r.txttensp.setText(list.get(vitri).getTenSP());

            r.txtsoluong.setText(String.valueOf(list.get(vitri).getSoLuong()));
            r.txtdvt.setText(list.get(vitri).getDonViTinh());
            r.txtdongia.setText(String.valueOf(list.get(vitri).getDonGia()).replace(".0", ""));
            for (int j = 0; j < FORM_SUA.boxncc.getSize(); j++) {
                if (String.valueOf(FORM_SUA.boxncc.getElementAt(j)).contains(mp)) {
                    r.comboncc.setSelectedIndex(j);
                    break;
                }
            }
            for (int j = 0; j < FORM_SUA.boxml.getSize(); j++) {
                if (String.valueOf(FORM_SUA.boxml.getElementAt(j)).contains(ml)) {
                    r.combomaloai.setSelectedIndex(j);
                    break;
                }
            }
            r.dongcansua = vitri;
            r.macansua = masua;
            r.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn thông tin cần sửa");
        }

    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        int ma = tblqlsp.getSelectedRow();

        /*for(int i  = 0;i<list.size();i++)
       {
         System.out.println("model ma sp o vi tri "+i+" la " + modelqlsp.getValueAt(i, 0));
         System.out.println("list ma sp o vi tri "+i+" la " + list.get(i).getMaSP());
       }*/
        if (ma != -1) {
            String maxoa = String.valueOf(modelqlsp.getValueAt(ma, 0));
            int j = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa ?");
            if (j == 0) {
                if (new BUS_XOA().xoaSP(maxoa)) {

                    modelqlsp.setRowCount(0);
                    list = new LOADDULIEU().getList();
                    for (SANPHAM s : list) {
                        modelqlsp.addRow(new Object[]{s.getMaSP(), s.getTenSP(), s.getMaLoai(), s.getSoLuong(), s.getDonViTinh(), vnmoney.format(s.getDonGia()), s.getMaNCC()});
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xóa !");
        }
    }//GEN-LAST:event_jLabel4MousePressed

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased

        String at = txttimkiem.getText();

        at = at.trim();

        modelqlsp.setRowCount(0);

        for (SANPHAM s : list) {
            if (s.getTenSP().contains(at) || s.getMaNCC().contains(at) || s.getDonViTinh().contains(at) || s.getMaSP().contains(at) || s.getMaLoai().contains(at)) {
                modelqlsp.addRow(new Object[]{s.getMaSP(), s.getTenSP(), s.getMaLoai(), s.getSoLuong(), s.getDonViTinh(), vnmoney.format(s.getDonGia()), s.getMaNCC()});

            }
        }


    }//GEN-LAST:event_txttimkiemKeyReleased

    private void refreshMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMousePressed
        list = new LOADDULIEU().getList();
        modelqlsp.setRowCount(0);
        for (SANPHAM s : list) {
            modelqlsp.addRow(new Object[]{s.getMaSP(), s.getTenSP(), s.getMaLoai(), s.getSoLuong(), s.getDonViTinh(), vnmoney.format(s.getDonGia()), s.getMaNCC()});
        }
    }//GEN-LAST:event_refreshMousePressed

    private void lbmenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbmenuMousePressed
        if (lbmenu.getX() == 180) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        logo.setVisible(false);
                        lbqlsp.setVisible(false);
                        lbdshd.setVisible(false);
                        lbdspn.setVisible(false);
                        lbttcn.setVisible(false);
                        lbkm.setVisible(false);
                        logout.setVisible(false);
                        lbthongke.setVisible(false);
                        lbkhachhang.setVisible(false);

                        panel.setBounds(75, 60, 1235, 670);
                        for (int i = 240; i >= 75; i--) {
                            sleep(1);
                            menu.setSize(i, 727);

                        }
                        lbhello.setBounds(95, 5, 510, 40);
                        lbmenu.setBounds(10, 670, 50, 50);

                    } catch (Exception e) {

                    }
                }

            };
            t.start();

        }

        //mo
        if (lbmenu.getX() == 10) {
            Thread tx = new Thread() {
                public void run() {
                    try {

                        for (int i = 75; i <= 240; i++) {
                            sleep(1);
                            menu.setSize(i, 727);
                        }
                        panel.setBounds(240, 60, 1070, 670);
                        lbhello.setBounds(260, 5, 510, 40);
                        logo.setVisible(true);
                        lbqlsp.setVisible(true);
                        lbdshd.setVisible(true);
                        lbdspn.setVisible(true);
                        lbttcn.setVisible(true);
                        lbkm.setVisible(true);
                        logout.setVisible(true);
                        lbthongke.setVisible(true);
                        lbkhachhang.setVisible(true);
                        lbmenu.setBounds(180, 670, 50, 50);
                    } catch (Exception e) {

                    }
                }

            };
            tx.start();

        }
    }//GEN-LAST:event_lbmenuMousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        /* int rd = (int) (Math.random() * ((999999 - 100000) + 1)) + 100000;
         String rd2= String.valueOf(rd);
           
        String a = taochuoi(3) + rd2;
        HOADON h = new HOADON();
        h.setMahd(a);
        h.setManv(ttdn.get(0).getManv());
        Date da = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        String now = f.format(da);
        h.setNgayxuat(now);
        h.setTongtien(0.0);
        if(new BUS_THEM().themHD(h))
        {
            JOptionPane.showMessageDialog(rootPane,"Đã tạo hóa đơn ! ","Thông báo",JOptionPane.DEFAULT_OPTION);
            listhd.add(h);
            listhd = new LOADDULIEU().getListHD();
            modeldshd.setRowCount(0);
        for(HOADON hc : listhd)
        {
            try {
                String dayhd = sdf2.format(sdf1.parse(String.valueOf(hc.getNgayxuat())));
                  modeldshd.addRow(new Object[]{hc.getMahd(),hc.getManv(),dayhd,vnmoney.format(hc.getTongtien()),hc.getMakm()});
            
            } catch (ParseException ex) {
                Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"Them that bai");
        }*/
        new GDBH().setVisible(true);
    }//GEN-LAST:event_jLabel1MousePressed

    private void lbthongkeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbthongkeMousePressed
        card.show(panel, "tk");

        lbthongke.setBackground(Color.WHITE);
        lbthongke.setOpaque(true);
        lbthongke.setForeground(Color.BLACK);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);

        lbttcn.setForeground(Color.WHITE);
        lbttcn.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);

        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
    }//GEN-LAST:event_lbthongkeMousePressed

    private void lbttcnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbttcnMousePressed
        card.show(panel, "ttcn");
        lbttcn.setBackground(Color.WHITE);
        lbttcn.setOpaque(true);
        lbttcn.setForeground(Color.BLACK);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);

        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);

        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
    }//GEN-LAST:event_lbttcnMousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        int i = tbldshd.getSelectedRow();
        if (i != -1) {
            String ma = String.valueOf(modeldshd.getValueAt(i, 0));
            int a = JOptionPane.showConfirmDialog(rootPane, "Xác nhận in hóa đơn mã " + ma + " ? ");
            if (a == 0) {
                String dex = "";
                for (HOADON hh : listhd) {
                    if (hh.getMahd().equalsIgnoreCase(ma)) {
                        try {
                            dex = sdf2.format(sdf1.parse(String.valueOf(hh.getNgayxuat())));
                        } catch (ParseException ex) {
                            Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
                File file = new File("C:\\HoaDonPDF");
                if (!file.exists()) {
                    if (file.mkdir()) {

                    }
                }
                String path = "C:\\HoaDonPDF\\" + "MaHD_" + ma + ".pdf";

                try {
                    if (d.InHD(ma, path, dex)) {
                        JOptionPane.showMessageDialog(rootPane, "In thành công");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "In thất bại");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Chọn mã cần in !");
        }
    }//GEN-LAST:event_jLabel8MousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        LocListHD();

    }//GEN-LAST:event_jLabel9MousePressed

    private void jLabel16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MousePressed

        if (rdo1.isSelected()) {
            SimpleDateFormat ngay = new SimpleDateFormat("yyyy/MM/dd");
            String tu = ngay.format(day1.getDate());
            String den = ngay.format(day2.getDate());

            try {
                Date truoc = ngay.parse(tu);
                Date sau = ngay.parse(den);
                System.out.println(sau.after(truoc));
                if (sau.after(truoc)) {

                    System.out.println("ngay truoc la " + tu);
                    System.out.println("ngay sau la " + den);
                    ArrayList<HOADON> listtk = new ArrayList<>();
                    listtk = new BUS_THONGKE().LocHDTheoKhoangThoiGian(tu, den);
                    modelthongke.setRowCount(0);
                    for (HOADON h : listtk) {

                        try {
                            modelthongke.addRow(new Object[]{h.getMahd(), sdf2.format(sdf3.parse(String.valueOf(h.getNgayxuat()))), vnmoney.format(h.getTongtien())});

                        } catch (ParseException ex) {
                            Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    ArrayList<CTHD> thongkesp = new ArrayList<>();
                    thongkesp = new BUS_THONGKE().ThongKeSPTheoKhoangThoiGian(tu, den);
                    modeltksp.setRowCount(0);
                    for (CTHD h : thongkesp) {
                        modeltksp.addRow(new Object[]{h.getTensp(), h.getSoluong(), vnmoney.format(h.getDongia()), vnmoney.format(h.getThanhtien())});
                    }
                    lbtksp.setText("Lượng sản phẩm bán ra từ " + sdf2.format(sdf1.parse(tu)) + " đến " + sdf2.format(sdf1.parse(den)));
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Vui long nhap khoang thoi gian hop le");
                }
            } catch (ParseException ex) {
                Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (rdo2.isSelected()) {
            String a = (String) cbthang.getSelectedItem();
            String b = (String) cbnam.getSelectedItem();
            //System.out.println("Thang " + a +" Nam " + b);
            String chuoi = "/" + a + "/" + b;
            ArrayList<HOADON> listtk = new ArrayList<>();
            listtk = new BUS_THONGKE().LocHDTheoThangNam(a, b);
            modelcttk.setRowCount(0);
            modelthongke.setRowCount(0);
            for (HOADON h : listtk) {

                try {
                    modelthongke.addRow(new Object[]{h.getMahd(), sdf2.format(sdf1.parse(String.valueOf(h.getNgayxuat()))), vnmoney.format(h.getTongtien())});
                } catch (ParseException ex) {
                    Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            ArrayList<CTHD> thongkesp = new ArrayList<>();
            thongkesp = new BUS_THONGKE().ThongKeSPTheoThangNam(a, b);
            modeltksp.setRowCount(0);
            for (CTHD h : thongkesp) {
                modeltksp.addRow(new Object[]{h.getTensp(), h.getSoluong(), vnmoney.format(h.getDongia()), vnmoney.format(h.getThanhtien())});
            }
            lbtksp.setText("Lượng sản phẩm bán ra trong tháng " + a + " năm " + b);
        } else if (rdo3.isSelected()) {
            SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
            String chonngay = fm.format(day3.getDate());

            //System.out.println(fmngay.format(day3.getDate()));
            //System.out.println(fmthang.format(day3.getDate()));
            //System.out.println(fmnam.format(day3.getDate()));
            //System.out.println("ngay la " + chonngay);
            ArrayList<HOADON> listtk = new ArrayList<>();
            listtk = new BUS_THONGKE().LocHDTheoNgay(chonngay);
            modelthongke.setRowCount(0);
            for (HOADON h : listtk) {

                try {
                    modelthongke.addRow(new Object[]{h.getMahd(), sdf2.format(sdf1.parse(String.valueOf(h.getNgayxuat()))), vnmoney.format(h.getTongtien())});
                } catch (ParseException ex) {
                    Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            ArrayList<CTHD> thongkesp = new ArrayList<>();
            thongkesp = new BUS_THONGKE().ThongKeSPTheoNgay(chonngay);
            modeltksp.setRowCount(0);
            for (CTHD h : thongkesp) {
                modeltksp.addRow(new Object[]{h.getTensp(), h.getSoluong(), vnmoney.format(h.getDongia()), vnmoney.format(h.getThanhtien())});
            }
            try {
                lbtksp.setText("Lượng sản phẩm bán ra trong ngày " + sdf2.format(sdf1.parse(chonngay)));
            } catch (ParseException ex) {
                Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLabel16MousePressed

    private void jLabel17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MousePressed

        ArrayList<HOADON> listtk = new ArrayList<>();
        listtk = new LOADDULIEU().getListHD();
        modelthongke.setRowCount(0);
        modelcttk.setRowCount(0);
        for (HOADON h : listtk) {

            try {
                modelthongke.addRow(new Object[]{h.getMahd(), sdf2.format(sdf1.parse(String.valueOf(h.getNgayxuat()))), vnmoney.format(h.getTongtien())});
            } catch (ParseException ex) {
                Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        modeltksp.setRowCount(0);
        tktc = new BUS_THONGKE().ThongKeTatCa();
        for (CTHD h : tktc) {
            modeltksp.addRow(new Object[]{h.getTensp(), h.getSoluong(), vnmoney.format(h.getDongia()), vnmoney.format(h.getThanhtien())});
        }
        lbtksp.setText("Tổng lượng sản phẩm đã bán ra được");
        os = new BUS_THONGKE().TongTatCa();
        lbtongdoanhthu.setText(vnmoney.format(os));
    }//GEN-LAST:event_jLabel17MousePressed

    private void tblthongkeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblthongkeMousePressed
        int i = tblthongke.getSelectedRow();
        String ma = (String) modelthongke.getValueAt(i, 0);

        ArrayList<CTHD> tklist = new ArrayList<>();
        tklist = new LOADDULIEU().getListCTHD(ma);
        Double sum = new BUS_SUA().TongTien(listhd.get(i).getMahd());
        modelcttk.setRowCount(0);
        for (CTHD c : tklist) {
            modelcttk.addRow(new Object[]{c.getTensp(), c.getSoluong(), vnmoney.format(c.getDongia()), vnmoney.format(c.getThanhtien())});
        }
    }//GEN-LAST:event_tblthongkeMousePressed

    private void logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMousePressed
        int a = JOptionPane.showConfirmDialog(rootPane, "Xác nhận đăng xuất", "Đăng xuất", JOptionPane.YES_NO_CANCEL_OPTION);

        if (a == 0) {
            this.dispose();

            new LOGIN().setVisible(true);
        }
    }//GEN-LAST:event_logoutMousePressed

    private void tbldshdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldshdMouseClicked
        if (evt.getClickCount() == 2) {
            int i = tbldshd.getSelectedRow();
            if (i != -1) {
                FORM_CTHD chitiet = new FORM_CTHD();
                chitiet.setVisible(true);
                chitiet.ma.setText(listhd.get(i).getMahd());
                modelcthd = (DefaultTableModel) chitiet.tblcthd.getModel();
                chitiet.tblcthd.getTableHeader().setBackground(new Color(8, 112, 150));
                chitiet.tblcthd.getTableHeader().setOpaque(false);
                chitiet.tblcthd.getTableHeader().setForeground(Color.WHITE);
                listcthd = new LOADDULIEU().getListCTHD(listhd.get(i).getMahd());
                Double sum = new BUS_SUA().TongTien(listhd.get(i).getMahd());
                chitiet.tongtien.setText(vnmoney.format(sum));
                chitiet.lbngayxuat.setText(String.valueOf(modeldshd.getValueAt(i, 2)));
                if (String.valueOf(modeldshd.getValueAt(i, 4)).contains("%") == false) {
                    chitiet.lbphantramkm.setText("Không có");
                } else {
                    chitiet.lbphantramkm.setText(String.valueOf(modeldshd.getValueAt(i, 4)));
                }
                String tenkh = String.valueOf(modeldshd.getValueAt(i, 5));
                for (KHACHHANG kh : listkh) {
                    if (kh.getMakh().equalsIgnoreCase(tenkh)) {
                        chitiet.lbtenkh.setText(kh.getTenkh());
                        break;
                    }
                }
                ArrayList<NGUOIDUNG> listnd = new ArrayList<>();
                listnd = new LOADDULIEU().getListNguoiDung();
                for (NGUOIDUNG ng : listnd) {
                    if (ng.getManv().equalsIgnoreCase(String.valueOf(modeldshd.getValueAt(i, 1)))) {
                        chitiet.lbtennv.setText(ng.getHoten());
                        break;
                    }
                }
                for (CTHD c : listcthd) {
                    modelcthd.addRow(new Object[]{c.getMasp(), c.getTensp(), c.getSoluong(), vnmoney.format(c.getDongia()), vnmoney.format(c.getThanhtien())});
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xem !");
            }
        }
    }//GEN-LAST:event_tbldshdMouseClicked

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        int i = tbldshd.getSelectedRow();
        if (i != -1) {
            FORM_CTHD chitiet = new FORM_CTHD();
            chitiet.setVisible(true);
            chitiet.ma.setText(listhd.get(i).getMahd());
            modelcthd = (DefaultTableModel) chitiet.tblcthd.getModel();
            chitiet.tblcthd.getTableHeader().setBackground(new Color(8, 112, 150));
            chitiet.tblcthd.getTableHeader().setOpaque(false);
            chitiet.tblcthd.getTableHeader().setForeground(Color.WHITE);
            listcthd = new LOADDULIEU().getListCTHD(listhd.get(i).getMahd());
            Double sum = new BUS_SUA().TongTien(listhd.get(i).getMahd());
            chitiet.tongtien.setText(vnmoney.format(sum));
            chitiet.lbngayxuat.setText(String.valueOf(modeldshd.getValueAt(i, 2)));
            if (String.valueOf(modeldshd.getValueAt(i, 4)).contains("%") == false) {
                chitiet.lbphantramkm.setText("Không có");
            } else {
                chitiet.lbphantramkm.setText(String.valueOf(modeldshd.getValueAt(i, 4)));
            }
            String tenkh = String.valueOf(modeldshd.getValueAt(i, 5));
            for (KHACHHANG kh : listkh) {
                if (kh.getMakh().equalsIgnoreCase(tenkh)) {
                    chitiet.lbtenkh.setText(kh.getTenkh());
                    break;
                }
            }
            ArrayList<NGUOIDUNG> listnd = new ArrayList<>();
            listnd = new LOADDULIEU().getListNguoiDung();
            for (NGUOIDUNG ng : listnd) {
                if (ng.getManv().equalsIgnoreCase(String.valueOf(modeldshd.getValueAt(i, 1)))) {
                    chitiet.lbtennv.setText(ng.getHoten());
                    break;
                }
            }
            for (CTHD c : listcthd) {
                modelcthd.addRow(new Object[]{c.getMasp(), c.getTensp(), c.getSoluong(), vnmoney.format(c.getDongia()), vnmoney.format(c.getThanhtien())});
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xem !");
        }
    }//GEN-LAST:event_jLabel6MousePressed

    public static void closeform() {
        bienmat.doClick();
    }
    private void jLabel30MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MousePressed
        new DMK_NV().setVisible(true);
    }//GEN-LAST:event_jLabel30MousePressed

    private void bienmatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bienmatActionPerformed
        dispose();
    }//GEN-LAST:event_bienmatActionPerformed

    private void tblkmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkmMousePressed
        // TODO add your handling code here:

        int i = tblkm.getSelectedRow();

        try {
            Date bd = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(modelkm.getValueAt(i, 2)));
            Date kt = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(modelkm.getValueAt(i, 3)));
            if (i >= 0) {
                for (KHUYENMAI km : listkm) {
                    if (km.getMaKM().equalsIgnoreCase(String.valueOf(modelkm.getValueAt(i, 0)))) {

                        txtTenCT.setText(km.getTenCT());
                        txtNgayBD.setDate(bd);
                        day2.setDate(kt);
                        txtPhanTram.setText(String.valueOf(km.getPhanTram()));
                    }
                }

            }
        } catch (ParseException ex) {

        }

    }//GEN-LAST:event_tblkmMousePressed

    private void btnThemKMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemKMMousePressed
        // TODO add your handling code here:
        if (txtTenCT.getText().isEmpty() || txtNgayBD.getDate() == null || txtNgayKT.getDate() == null || txtPhanTram.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập đầy đủ thông tin");
        } else {

            int rd = (int) (Math.random() * ((999999 - 100000) + 1)) + 100000;
            String rd2 = String.valueOf(rd);

            String a = taochuoi(5) + rd2;
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            String bd = f.format(txtNgayBD.getDate());
            String kt = f.format(txtNgayKT.getDate());

            Date now = new Date();
            now.setDate(now.getDate() - 1);

            String hq = f.format(now);
            try {
                Date truoc = f.parse(bd);
                Date sau = f.parse(kt);
                Date homqua = f.parse(hq);

                if ((truoc.after(homqua) && (sau.after(truoc) || bd.equals(kt)))) {
                    KHUYENMAI km = new KHUYENMAI();
                    km.setMaKM(a);
                    km.setTenCT(txtTenCT.getText());
                    km.setNgayBatDau(bd);
                    km.setNgayKetThuc(kt);
                    km.setPhanTram(Float.parseFloat(txtPhanTram.getText()));
                    int conf = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn thêm Chương Trình Khuyến Mãi này");
                    if (conf == 0) {
                        if (new BUS_THEM().themKM(km)) {
                            listkm.add(km);
                            modelkm.addRow(new Object[]{km.getMaKM(), km.getTenCT(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getPhanTram()});
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Thêm thất bại");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập ngày hợp lệ.");
                }
            } catch (ParseException ex) {

            }

        }
    }//GEN-LAST:event_btnThemKMMousePressed

    private void btnXoaKMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaKMMousePressed
        // TODO add your handling code here:
        int i = tblkm.getSelectedRow();
        if (i >= 0) {
            KHUYENMAI km = new KHUYENMAI();
            String ma = listkm.get(i).getMaKM();
            System.out.println(ma);
            int conf = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa");
            if (new BUS_XOA().xoaKM(ma) && conf == 0) {
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
                modelkm.setRowCount(0);
                listkm = new LOADDULIEU().getListKhuyenMai();

                for (KHUYENMAI ttkm : listkm) {
                    modelkm.addRow(new Object[]{ttkm.getMaKM(), ttkm.getTenCT(), ttkm.getNgayBatDau(), ttkm.getNgayKetThuc(), ttkm.getPhanTram()});
                }
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần xóa");
        }
    }//GEN-LAST:event_btnXoaKMMousePressed

    private void btnReset1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReset1MousePressed
        // TODO add your handling code here:

        txtTenCT.setText("");
        txtNgayBD.setDate(null);
        day2.setDate(null);
        txtPhanTram.setText("");
    }//GEN-LAST:event_btnReset1MousePressed

    private void lbkmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbkmMousePressed
        card.show(panel, "ctkm");
        lbkm.setBackground(Color.WHITE);
        lbkm.setOpaque(true);
        lbkm.setForeground(Color.BLACK);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);

        lbttcn.setForeground(Color.WHITE);
        lbttcn.setOpaque(false);

        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
    }//GEN-LAST:event_lbkmMousePressed

    private void lbthongkeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbthongkeMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbthongkeMouseReleased

    private void lbkhachhangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbkhachhangMousePressed
        card.show(panel, "nsx");
        lbkm.setBackground(Color.WHITE);
        lbkhachhang.setOpaque(true);
        lbkhachhang.setForeground(Color.BLACK);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);

        lbttcn.setForeground(Color.WHITE);
        lbttcn.setOpaque(false);

        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
    }//GEN-LAST:event_lbkhachhangMousePressed

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel37MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MousePressed
        new TTKHAC().setVisible(true);
    }//GEN-LAST:event_jLabel37MousePressed
    String path = null;

    public void setAvatar(String user) {
        me.setIcon(Resize("..\\DemoDoAn\\src\\Image\\AVT" + user + ".jpg", me.getWidth(), me.getHeight()));
    }
    private void jLabel32MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MousePressed
        if (path == null) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn ảnh trước !");
        } else {
            String ten = "AVT" + ttdn.get(0).getUser();
            if (ten == null) {
            } else {
                ThemAnh(ten);
            }

        }
    }//GEN-LAST:event_jLabel32MousePressed

    private void jLabel38MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MousePressed
        JFileChooser filechooser = new JFileChooser();
        filechooser.setDialogTitle("Choose Your File");
        filechooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "gif", "png");
        filechooser.addChoosableFileFilter(filter);
        int result = filechooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File select = filechooser.getSelectedFile();
            path = select.getAbsolutePath();
            me.setIcon(Resize(path, me.getWidth(), me.getHeight()));

        }
    }//GEN-LAST:event_jLabel38MousePressed

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel40MouseClicked

    private void jLabel40MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MousePressed
        String a = txttenkh.getText();
        String b = txtsdtkh.getText();
        if (a.isEmpty() || b.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đầy đủ thông tin !");
        } else if (new KiemTraLoi().KT_SDT(b) == false) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không hợp lệ !");
        } else {
            int xn = JOptionPane.showConfirmDialog(rootPane, "Xác nhận thêm khách hàng này ?");

            if (xn == 0) {
                KHACHHANG kh = new KHACHHANG();
                int rd = (int) (Math.random() * ((999999 - 100000) + 1)) + 100000;
                String ma = "KH" + String.valueOf(rd);
                kh.setTenkh(a);
                kh.setSdt(b);
                kh.setMakh(ma);
                if (new BUS_THEM().themKH(kh)) {
                    JOptionPane.showMessageDialog(rootPane, "Đã thêm khách hàng");
                    LocListKH();
                }
            }
        }
    }//GEN-LAST:event_jLabel40MousePressed

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel41MouseClicked

    private void jLabel41MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MousePressed
        int i = tblkhachhang.getSelectedRow();
        if (i >= 0) {
            int xn = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa khách hàng này ?");
            if (xn == 0) {

                if (new BUS_XOA().xoaKH(String.valueOf(modelkhachhang.getValueAt(i, 0)))) {
                    JOptionPane.showMessageDialog(rootPane, "Đã xóa");
                    LocListKH();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xóa");
        }

    }//GEN-LAST:event_jLabel41MousePressed

    private void tblkhachhangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkhachhangMousePressed
        int i = tblkhachhang.getSelectedRow();
        if (i >= 0) {
            txttenkh.setText(String.valueOf(modelkhachhang.getValueAt(i, 1)));
            txtsdtkh.setText(String.valueOf(modelkhachhang.getValueAt(i, 2)));
            modelctkh.setRowCount(0);
            ArrayList<HOADON> listctkh = new ArrayList<>();
            listctkh = new LOADDULIEU().HD_KHACHHANG(String.valueOf(modelkhachhang.getValueAt(i, 0)));
            for (HOADON h : listctkh) {
                try {
                    modelctkh.addRow(new Object[]{h.getMahd(), sdf2.format(sdf1.parse(String.valueOf(h.getNgayxuat()))), vnmoney.format(h.getTongtien())});
                } catch (ParseException ex) {
                    Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_tblkhachhangMousePressed

    private void jLabel42MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MousePressed
        LocListKH();
        txttenkh.setText("");
        txtsdtkh.setText("");
    }//GEN-LAST:event_jLabel42MousePressed

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel43MouseClicked

    private void jLabel43MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MousePressed
        int i = tblkhachhang.getSelectedRow();
        if (i >= 0) {
            String ma = String.valueOf(modelkhachhang.getValueAt(i, 0));
            String ten = txttenkh.getText();
            String sdt = txtsdtkh.getText();

            if (ten.isEmpty() || sdt.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Hãy nhập đầy đủ thông tin !");
            } else if (new KiemTraLoi().KT_SDT(sdt) == false) {
                JOptionPane.showMessageDialog(rootPane, "Số điện thoại không hợp lệ !");
            } else {
                if (ten.isEmpty() || sdt.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Hãy nhập đầy đủ thông tin trước !");
                } else {
                    if (new BUS_SUA().suaKH(ma, ten, sdt)) {
                        JOptionPane.showMessageDialog(rootPane, "Đã sửa");
                        LocListKH();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Chọn dòng cần sửa !");
        }

    }//GEN-LAST:event_jLabel43MousePressed

    private void combopnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combopnFocusLost

    }//GEN-LAST:event_combopnFocusLost

    private void combopnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combopnActionPerformed

    }//GEN-LAST:event_combopnActionPerformed

    private void combopnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combopnItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();

            String a = String.valueOf(item);
            String masp = FORM_THEM.tachphai(a);
            list = new LOADDULIEU().getList();
            for (SANPHAM s : list) {
                if (a.contains(s.getMaSP())) {
                    dvtpn.setText(s.getDonViTinh());
                    break;
                }
            }
        }
    }//GEN-LAST:event_combopnItemStateChanged

    private void combopnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combopnFocusGained

    }//GEN-LAST:event_combopnFocusGained

    private void jLabel47MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MousePressed
        if (new KiemTraLoi().KT_SoLuong(String.valueOf(slnhap.getText())) == false || slnhap.getText() == null) {
            JOptionPane.showMessageDialog(rootPane, "Số lượng chứa ký tự hoặc rỗng ");
        } else if (Integer.parseInt(slnhap.getText()) <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Số lượng không hợp lệ ");
        } else {
            CTPN pn = new CTPN();
            String masp = FORM_THEM.tachtrai(String.valueOf(boxpn.getSelectedItem()));
            pn.setMaSP(masp);
            pn.setSoLuong(Integer.parseInt(slnhap.getText()));
            list = new LOADDULIEU().getList();
            for (SANPHAM s : list) {
                if (masp.equals(s.getMaSP())) {
                    pn.setGiaNhap(s.getDonGia() * 60 / 100);
                    pn.setThanhTien(pn.getGiaNhap() * pn.getSoLuong());
                    break;
                }
            }
            if (new BUS_THEM().ThemCTPN(pn)) {
                JOptionPane.showMessageDialog(rootPane, "Đã thêm sản phẩm ");
                LocAll();
            } else {
                System.out.println("error");
            }
        }
    }//GEN-LAST:event_jLabel47MousePressed

    private void jLabel48MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MousePressed
        int rd = (int) (Math.random() * ((999999 - 100000) + 1)) + 100000;
        String rd2 = String.valueOf(rd);

        String a = "PNSP" + rd2;
        double tong = 0;
        for (CTPN pn : listctpn) {
            tong += pn.getThanhTien();
        }

        PHIEUNHAP p = new PHIEUNHAP();
        Date g = new Date();
        String nay = sdf1.format(g);
        p.setMaNV(ttdn.get(0).getManv());
        p.setTongTien(tong);
        p.setNgayNhap(nay);
        p.setMaPN(a);
        int xn = JOptionPane.showConfirmDialog(rootPane, "Hãy xác nhận tạo phiếu nhập !");
        if (xn == 0) {
            if (new BUS_THEM().ThemPN(p)) {

                for (CTPN pa : listctpn) {
                    int hientai = 0;
                    for (SANPHAM s : list) {
                        if (s.getMaSP().equals(pa.getMaSP())) {
                            hientai = s.getSoLuong() + pa.getSoLuong();
                            break;
                        }
                    }

                    pa.setMaPN(a);
                    if (new BUS_THEM().ThemCTPN_CT(pa)) {
                        if (new BUS_SUA().suasoluongSP(pa.getMaSP(), hientai)) {
                        }
                    }
                }
                new BUS_XOA().resetCTPN();
            }
            JOptionPane.showMessageDialog(rootPane, "Đã tạo phiếu nhập mã " + a);
            LocAll();
        }
    }//GEN-LAST:event_jLabel48MousePressed
    public static String stripAccents(String s) 
{
    s = Normalizer.normalize(s, Normalizer.Form.NFD);
    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    return s;
}
    private void jLabel49MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MousePressed
        FileWriter out = null;
        File theDir = new File("C:\\HoaDonPDF");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        int i = tblphieunhap.getSelectedRow();
        if (i >= 0) {
            String mapn = String.valueOf(modelphieunhap.getValueAt(i, 0));
            
            int xn = JOptionPane.showConfirmDialog(rootPane, "Xác nhận in phiếu nhập mã " + mapn + " ? ");
            if (xn == 0) {
                try {
                    LocListPhieuNhap();
                    out = new FileWriter("C:\\HoaDonPDF\\PhieuNhap" + mapn + ".xls");
                    BufferedWriter bw = new BufferedWriter(out);

                    bw.write("Ma phieu nhap : " + "\t");
                    bw.write(mapn + "\t");
                    bw.write("\n");
                    bw.write("Ngay nhap : " + "\t");
                    bw.write(String.valueOf(modelphieunhap.getValueAt(i, 1)) + "\t");
                    bw.write("\n");
                    bw.write("Ma san pham" + "\t");
                    bw.write("So luong" + "\t");
                    bw.write("Gia nhap" + "\t");
                    bw.write("Thanh tien" + "\t");

                    bw.write("\n");
                    listctpn = new LOADDULIEU().getListCTPN(mapn);
                    Double tong = 0.0;
                    for (CTPN pn : listctpn) {
                        bw.write(stripAccents(pn.getMaSP()) + "\t");
                        bw.write(pn.getSoLuong() + "\t");
                        bw.write(pn.getGiaNhap() + "\t");
                        bw.write(pn.getThanhTien() + "\t");
                        bw.write("\n");
                        tong += pn.getThanhTien();
                    }
                    bw.write("Tong" + "\t");
                    bw.write(" " + "\t");
                    bw.write(" " + "\t");
                    bw.write(tong + "\t");
                    bw.close();
                    JOptionPane.showMessageDialog(rootPane, "Đã xuất phiếu nhập");
                } catch (IOException ex) {
                    Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, "Lỗi khi xuất");
                } finally {
                    try {
                        out.close();
                    } catch (IOException ex) {
                        Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Chọn mã phiếu nhập cần xuất !");
        }
    }//GEN-LAST:event_jLabel49MousePressed

    private void jLabel50MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MousePressed
        new BUS_XOA().resetCTPN();
        LocListCTPN();
    }//GEN-LAST:event_jLabel50MousePressed

    public void ThemAnh(String name) {
        int conf = JOptionPane.showConfirmDialog(rootPane, "Xác nhận thêm ảnh ?");
        if (conf == 0) {
            File destination = new File("..\\DemoDoAn\\src\\Image\\" + name + ".jpg");
            File browser = new File(path);
            try {
                Files.copy(browser.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(rootPane, "Đã thêm ảnh");
                setAvatar(ttdn.get(0).getUser());
                path = null;

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(rootPane, "Lỗi");
            }
        } else {

        }
    }

    public ImageIcon Resize(String name, int rong, int cao) {
        ImageIcon myimg = new ImageIcon(name);
        Image img = myimg.getImage();
        Image newimg = img.getScaledInstance(rong, cao, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(newimg);
        return image;
    }

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
            java.util.logging.Logger.getLogger(GIAODIENCHINH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GIAODIENCHINH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GIAODIENCHINH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GIAODIENCHINH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GIAODIENCHINH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton bienmat;
    private javax.swing.JLabel btnReset1;
    private javax.swing.JLabel btnThemKM;
    private javax.swing.JLabel btnXoaKM;
    private javax.swing.JComboBox<String> cbnam;
    private javax.swing.JComboBox<String> cbthang;
    private javax.swing.JComboBox<String> combopn;
    private javax.swing.JPanel ctkm;
    private com.toedter.calendar.JDateChooser day1;
    private com.toedter.calendar.JDateChooser day2;
    private com.toedter.calendar.JDateChooser day3;
    private javax.swing.JPanel dshd;
    private javax.swing.JPanel dspn;
    private javax.swing.JLabel dvtpn;
    private javax.swing.ButtonGroup groupthongke;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    public javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel khachhang;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbMini;
    private javax.swing.JLabel lbdshd;
    private javax.swing.JLabel lbdspn;
    public static javax.swing.JLabel lbhello;
    private javax.swing.JLabel lbkhachhang;
    private javax.swing.JLabel lbkm;
    private javax.swing.JLabel lbmenu;
    private javax.swing.JLabel lbqlsp;
    private javax.swing.JLabel lbthongke;
    private javax.swing.JLabel lbtime;
    public static javax.swing.JLabel lbtksp;
    private javax.swing.JLabel lbtoday;
    public static javax.swing.JLabel lbtongdoanhthu;
    private javax.swing.JLabel lbttcn;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel me;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel pnttcn;
    private javax.swing.JPanel qlsp;
    private javax.swing.JRadioButton rdo1;
    private javax.swing.JRadioButton rdo2;
    private javax.swing.JRadioButton rdo3;
    public javax.swing.JLabel refresh;
    private javax.swing.JTextField slnhap;
    private javax.swing.JPanel task;
    private javax.swing.JTable tblctkh;
    private javax.swing.JTable tblctpn;
    private javax.swing.JTable tblcttk;
    private javax.swing.JTable tbldshd;
    private javax.swing.JTable tblkhachhang;
    private javax.swing.JTable tblkm;
    private javax.swing.JTable tblphieunhap;
    public static javax.swing.JTable tblqlsp;
    private javax.swing.JTable tblthongke;
    private javax.swing.JTable tbltksp;
    private javax.swing.JPanel thongke;
    private javax.swing.JPanel ttcn;
    private javax.swing.JTextField ttdndiachi;
    private javax.swing.JTextField ttdnemail;
    private javax.swing.JTextField ttdnhoten;
    private javax.swing.JTextField ttdnmanv;
    private javax.swing.JTextField ttdnngaysinh;
    private javax.swing.JTextField ttdnsdt;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private javax.swing.JTextField txtPhanTram;
    private javax.swing.JTextField txtTenCT;
    private javax.swing.JTextField txtsdtkh;
    private javax.swing.JTextField txttenkh;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
