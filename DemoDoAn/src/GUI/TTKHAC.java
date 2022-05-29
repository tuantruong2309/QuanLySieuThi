/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BUS_THEM;
import BUS.LOADDULIEU;
import DTO.SANPHAM;
import DTO.NHACUNGCAP;
import DTO.MALOAI;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author MINH TUAN
 */
public class TTKHAC extends javax.swing.JFrame {

    public CardLayout card;
    DefaultTableModel modelml,modelncc,modelsp;
    public static ArrayList<NHACUNGCAP> listncc = new ArrayList<>();
    public static ArrayList<MALOAI> listml = new ArrayList<>();
     public static ArrayList<SANPHAM> list = new ArrayList<>();
    public TTKHAC() {
        initComponents();
        
        card = (CardLayout) panel.getLayout();
         card.show(panel, "ncc");
         modelml = (DefaultTableModel) tblloai.getModel();
         modelncc = (DefaultTableModel) tblncc.getModel();
         GIAODIENCHINH.setHeader(tblloai);
         GIAODIENCHINH.setHeader(tblncc);
         listncc = new LOADDULIEU().getListNCC();
         listml = new LOADDULIEU().getListMaLoai();
         for(MALOAI ml : listml)
         {
             modelml.addRow(new Object[]{ml.getMaloai(),ml.getTenloai()});
         }
         for(NHACUNGCAP ncc : listncc)
         {
             modelncc.addRow(new Object[]{ncc.getMaNSX(),ncc.getTenNSX()});
         }
         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        list = new LOADDULIEU().getList();
        /*jScrollPane2.getColumnHeader().setVisible(false);
        jScrollPane2.revalidate();*/
        
        modelsp =(DefaultTableModel) tbldssp.getModel();
        tbldssp.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbldssp.getColumn("Ảnh").setCellRenderer(new LabelRenderer());
        JTableHeader header = tbldssp.getTableHeader();
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        for(SANPHAM s : list)
        {
            JLabel lb = new JLabel();
            lb.setIcon(Resize("..\\DemoDoAn\\src\\Image\\IMG"+s.getMaSP()+".jpg",120, 120));
            modelsp.addRow(new Object[]{s.getTenSP(),lb});
        }
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void LocList()
    {
        modelml.setRowCount(0);
        modelncc.setRowCount(0);
        listncc = new LOADDULIEU().getListNCC();
         listml = new LOADDULIEU().getListMaLoai();
         for(MALOAI ml : listml)
         {
             modelml.addRow(new Object[]{ml.getMaloai(),ml.getTenloai()});
         }
         for(NHACUNGCAP ncc : listncc)
         {
             modelncc.addRow(new Object[]{ncc.getMaNSX(),ncc.getTenNSX()});
         }
    }
    private void LoadAnh()
    {
        modelsp.setRowCount(0);
        list = new LOADDULIEU().getList();
        for(SANPHAM s : list)
        {
            JLabel lb = new JLabel();
            lb.setIcon(Resize("..\\DemoDoAn\\src\\Image\\IMG"+s.getMaSP()+".jpg",120, 120));
            modelsp.addRow(new Object[]{s.getTenSP(),lb});
        }
    }
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        PL = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblloai = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtmaloai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttenloai = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        NCC = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblncc = new javax.swing.JTable();
        txttenncc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtmancc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        HA = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbldssp = new javax.swing.JTable();
        me = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbncc = new javax.swing.JLabel();
        lbpn = new javax.swing.JLabel();
        lbClose = new javax.swing.JLabel();
        lbMini = new javax.swing.JLabel();
        lbanh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panel.setBackground(new java.awt.Color(255, 102, 204));
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelMousePressed(evt);
            }
        });
        panel.setLayout(new java.awt.CardLayout());

        PL.setBackground(new java.awt.Color(47, 48, 48));
        PL.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        tblloai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã loại", "Tên loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblloai.setRowHeight(30);
        jScrollPane2.setViewportView(tblloai);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mã loại");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tên loại");

        jLabel8.setBackground(new java.awt.Color(61, 145, 106));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Thêm");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.setOpaque(true);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        javax.swing.GroupLayout PLLayout = new javax.swing.GroupLayout(PL);
        PL.setLayout(PLLayout);
        PLLayout.setHorizontalGroup(
            PLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                    .addGroup(PLLayout.createSequentialGroup()
                        .addGroup(PLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6)
                                .addComponent(txtmaloai)
                                .addComponent(txttenloai, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PLLayout.setVerticalGroup(
            PLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PLLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmaloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttenloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel.add(PL, "pl");

        NCC.setBackground(new java.awt.Color(47, 48, 48));
        NCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        tblncc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NCC", "Tên NCC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblncc.setRowHeight(30);
        jScrollPane1.setViewportView(tblncc);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tên nhà cung cấp");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mã nhà cung cấp");

        jLabel5.setBackground(new java.awt.Color(61, 145, 106));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Thêm");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout NCCLayout = new javax.swing.GroupLayout(NCC);
        NCC.setLayout(NCCLayout);
        NCCLayout.setHorizontalGroup(
            NCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NCCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NCCLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(NCCLayout.createSequentialGroup()
                        .addGroup(NCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                            .addGroup(NCCLayout.createSequentialGroup()
                                .addGroup(NCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(txtmancc, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                    .addComponent(txttenncc))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        NCCLayout.setVerticalGroup(
            NCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NCCLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmancc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttenncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel.add(NCC, "ncc");

        HA.setBackground(new java.awt.Color(47, 48, 48));
        HA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        tbldssp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên SP", "Ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldssp.setRowHeight(120);
        tbldssp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbldsspMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbldssp);

        me.setBackground(new java.awt.Color(204, 204, 204));
        me.setOpaque(true);

        jLabel1.setBackground(new java.awt.Color(61, 145, 106));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chọn ảnh");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(61, 145, 106));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Xác nhận");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.setOpaque(true);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(61, 145, 106));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Xóa ảnh");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.setOpaque(true);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });

        javax.swing.GroupLayout HALayout = new javax.swing.GroupLayout(HA);
        HA.setLayout(HALayout);
        HALayout.setHorizontalGroup(
            HALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(HALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(me, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(HALayout.createSequentialGroup()
                        .addGroup(HALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addContainerGap())
        );
        HALayout.setVerticalGroup(
            HALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HALayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(HALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HALayout.createSequentialGroup()
                        .addComponent(me, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panel.add(HA, "hinh");

        getContentPane().add(panel, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(47, 48, 48));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel2.setFocusCycleRoot(true);
        jPanel2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel2AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
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

        lbncc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbncc.setText("Nhà cung cấp");
        lbncc.setOpaque(true);
        lbncc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbnccMousePressed(evt);
            }
        });

        lbpn.setForeground(new java.awt.Color(255, 255, 255));
        lbpn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbpn.setText("Phân loại");
        lbpn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbpnMousePressed(evt);
            }
        });

        lbClose.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
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

        lbMini.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
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

        lbanh.setForeground(new java.awt.Color(255, 255, 255));
        lbanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbanh.setText("Hình ảnh");
        lbanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbanhMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(lbMini, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(lbClose, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbncc, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbpn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbanh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMini, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbClose, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbpn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbncc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbanh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbnccMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbnccMousePressed
        card.show(panel, "ncc");
        lbncc.setBackground(Color.WHITE);
        lbncc.setForeground(Color.BLACK);
        lbncc.setOpaque(true);
        
        lbpn.setBackground(new Color(47,48,48));
        lbpn.setForeground(Color.WHITE);
        lbpn.setOpaque(true);
        
        lbanh.setBackground(new Color(47,48,48));
        lbanh.setForeground(Color.WHITE);
        lbanh.setOpaque(true);
    }//GEN-LAST:event_lbnccMousePressed

    private void lbpnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbpnMousePressed
         card.show(panel, "pl");
         lbpn.setBackground(Color.WHITE);
        lbpn.setForeground(Color.BLACK);
        lbpn.setOpaque(true);
        
        lbncc.setBackground(new Color(47,48,48));
        lbncc.setForeground(Color.WHITE);
        lbncc.setOpaque(true);
        
        lbanh.setBackground(new Color(47,48,48));
        lbanh.setForeground(Color.WHITE);
        lbanh.setOpaque(true);
    }//GEN-LAST:event_lbpnMousePressed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
         String ma = txtmancc.getText();
        String ten = txttenncc.getText();
        if(ma.isEmpty()||ten.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Hãy nhập đầy đủ thông tin !");
        }
        else
        {
        if(new BUS_THEM().ThemThongTinKhac("NHACUNGCAP",ma,ten))
                {
                    txtmancc.setText("");
                    txttenncc.setText("");
                    LocList();
                }
        }
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        String ma = txtmaloai.getText();
        String ten = txttenloai.getText();
         if(ma.isEmpty()||ten.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Hãy nhập đầy đủ thông tin !");
        }
        else
        {
        if(new BUS_THEM().ThemThongTinKhac("PHANLOAI",ma,ten))
        {
             txtmaloai.setText("");
             txttenloai.setText("");
             LocList();
        }
        }
    }//GEN-LAST:event_jLabel8MousePressed

    private void lbMiniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseClicked
        this.setExtendedState(GIAODIENCHINH.ICONIFIED);
    }//GEN-LAST:event_lbMiniMouseClicked

    private void lbMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseEntered
        lbMini.setForeground(Color.BLACK);
        lbMini.setOpaque(true);
        lbMini.setBackground(Color.WHITE);
    }//GEN-LAST:event_lbMiniMouseEntered

    private void lbMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseExited
        lbMini.setForeground(Color.WHITE);
        lbMini.setOpaque(false);
        lbMini.setBackground(new Color(61,145,106));
    }//GEN-LAST:event_lbMiniMouseExited

    private void lbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseClicked
        dispose();
    }//GEN-LAST:event_lbCloseMouseClicked

    private void lbCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseEntered
        lbClose.setForeground(Color.BLACK);
        lbClose.setOpaque(true);
        lbClose.setBackground(Color.WHITE);
    }//GEN-LAST:event_lbCloseMouseEntered

    private void lbCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseExited
        lbClose.setForeground(Color.WHITE);
        lbClose.setOpaque(false);
        lbClose.setBackground(new Color(61,145,106));
    }//GEN-LAST:event_lbCloseMouseExited
int mx,my;

    private void panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMousePressed
     
    }//GEN-LAST:event_panelMousePressed

    private void jPanel2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel2AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2AncestorAdded

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
       int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-mx,y-my);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        mx=evt.getX();
       my=evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void lbanhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbanhMousePressed
          card.show(panel, "hinh");
         lbanh.setBackground(Color.WHITE);
        lbanh.setForeground(Color.BLACK);
        lbanh.setOpaque(true);
        
        lbncc.setBackground(new Color(47,48,48));
        lbncc.setForeground(Color.WHITE);
        lbncc.setOpaque(true);
        
        lbpn.setBackground(new Color(47,48,48));
        lbpn.setForeground(Color.WHITE);
        lbpn.setOpaque(true);
    }//GEN-LAST:event_lbanhMousePressed
    static String path;    
    class LabelRenderer implements TableCellRenderer{
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column)
            {
                
                return (Component)value;
            }
    }
    private void tbldsspMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldsspMousePressed
     
    }//GEN-LAST:event_tbldsspMousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
            JFileChooser filechooser = new JFileChooser();
    filechooser.setDialogTitle("Chọn ảnh minh họa");
    filechooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","jpg","gif","png");
        filechooser.addChoosableFileFilter(filter);
        int result = filechooser.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File select = filechooser.getSelectedFile();
            path = select.getAbsolutePath();
            me.setIcon(Resize(path,me.getWidth(),me.getHeight()));
           
        }
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        if(path==null)
       {
           JOptionPane.showMessageDialog(rootPane, "Hãy chọn ảnh trước !");
       }
       else
       {
           int i = tbldssp.getSelectedRow();
          
           if(i>=0)
           {
               System.out.println("ten " + String.valueOf(modelsp.getValueAt(i,0)));
           String ma = "IMG";
            for(SANPHAM s : list)
           {
               if(s.getTenSP().equals(String.valueOf(modelsp.getValueAt(i,0))))
               {
                   ma+=s.getMaSP();
                   break;
               }
           }
           
           if(ma==null)
           {}
           else
           {
           ThemAnh(ma);
           }
           }
           else
           {
                   
           }
       
           
       }
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
       int i = tbldssp.getSelectedRow();
          
           if(i>=0)
           {
              
           String ma = "IMG";
            for(SANPHAM s : list)
           {
               if(s.getTenSP().equals(String.valueOf(modelsp.getValueAt(i,0))))
               {
                   ma+=s.getMaSP();
                   break;
               }
           }
            File del = new File("..\\DemoDoAn\\src\\Image\\"+ma+".jpg");
            int xn = JOptionPane.showConfirmDialog(rootPane, "Xóa ảnh này ? ");
            if(xn==0)
            {
                if(del.delete())
                {
                    JOptionPane.showMessageDialog(rootPane, "Đã xóa");
                    LoadAnh();
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
                }
            }
           }
           else
           {
                JOptionPane.showMessageDialog(rootPane, "Chọn ảnh cần xóa !");
           }
    }//GEN-LAST:event_jLabel9MousePressed
    public void ThemAnh(String name)
    {
         int conf = JOptionPane.showConfirmDialog(rootPane, "Xác nhận thêm ảnh ?");
        if(conf==0)
        {
        File destination = new File("..\\DemoDoAn\\src\\Image\\"+name+".jpg");
        File browser = new File(path);
         try {
            Files.copy(browser.toPath(),destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            JOptionPane.showMessageDialog(rootPane, "Đã thêm ảnh");
             path = null;
             me.setText("");
             LoadAnh();
        me.setIcon(null);
        } catch (IOException ex) {
           
            JOptionPane.showMessageDialog(rootPane, "Lỗi");
        }
        }
        else
        {
            
        }
    }
    public ImageIcon Resize(String name,int rong,int cao)
    {
        ImageIcon myimg = new ImageIcon(name);
        Image img = myimg.getImage();
        Image newimg = img.getScaledInstance(rong,cao, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(newimg);
        return image;
    }
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
            java.util.logging.Logger.getLogger(TTKHAC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TTKHAC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TTKHAC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TTKHAC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TTKHAC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HA;
    private javax.swing.JPanel NCC;
    private javax.swing.JPanel PL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbMini;
    private javax.swing.JLabel lbanh;
    private javax.swing.JLabel lbncc;
    private javax.swing.JLabel lbpn;
    private javax.swing.JLabel me;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tbldssp;
    private javax.swing.JTable tblloai;
    private javax.swing.JTable tblncc;
    private javax.swing.JTextField txtmaloai;
    private javax.swing.JTextField txtmancc;
    private javax.swing.JTextField txttenloai;
    private javax.swing.JTextField txttenncc;
    // End of variables declaration//GEN-END:variables
}
