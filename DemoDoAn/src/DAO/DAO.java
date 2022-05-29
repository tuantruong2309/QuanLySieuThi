/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BUS.BUS_SUA;
import DTO.HOADON;
import DTO.MALOAI;
import DTO.NHACUNGCAP;
import DTO.SANPHAM;
import DTO.NGUOIDUNG;
import DTO.CTHD;
import DTO.PHIEUNHAP;
import GUI.GIAODIENCHINH;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class DAO {
    public Connection conn = null;
    public ResultSet rs = null;
    public Statement ps = null;
    public DAO(){
//       try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QuanLySieuThi;"
//                    + "username=tuan;password=Kames551689");
//            
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Failed");
//        }
         try {  
        Class.forName("com.mysql.jdbc.Driver");
        try {
            conn=(com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysieuthi","root","");
            System.out.println("OK");
        } catch (SQLException ex) {
           
            System.out.println("Failed");
        }
    } catch (ClassNotFoundException ex) {
       
    }
    }
    public static NumberFormat vnmoney = NumberFormat.getInstance(new Locale("vi", "vn"));
    public static void main(String[] args) {
        new DAO();

    }

    public boolean DoiPass(String user,String pass)
    {
        String sql = "Update NGUOIDUNG set password='"+pass+"' where username='"+user+"'";
        try {
            ps=conn.createStatement();
            ps.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String passhientai()
    {
        String pass = null;
        String sql = "select password from nguoidung where username = 'admin'";
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);
            while(rs.next())
            {
                return String.valueOf(rs.getString("password"));
            }
          
        } catch (Exception e) {
        }
        return null;
    }
    public boolean InHD(String ma,String path,String dex) throws IOException
    {
        String sql = "select TenSP,sum(SoLuong) as SoLuong,DonGia,sum(SoLuong)*DonGia as ThanhTien from CTHD where MaHD='"+ma+"' \n" +
"group by TenSP,DonGia";
        
       
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);
            
            Document document = new Document();
            try {
   
            PdfWriter.getInstance(document, new FileOutputStream(path));

            document.open();
            BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font f1 = new Font(bf,14,Font.NORMAL);
           
            Font fb = new Font(bf,17,Font.BOLD);
            document.add(new Paragraph("THÔNG TIN HÓA ĐƠN",fb));
            document.add(new Paragraph("Mã hóa đơn : " + ma,f1));
            document.add(new Paragraph("Ngày xuất hóa đơn: " + dex,f1));
           
            SimpleDateFormat fs = new SimpleDateFormat("dd/MM/yyyy");
            
            document.add(new Paragraph("Ngày in : " + String.valueOf(fs.format(new Date())),f1));
            PdfPTable t = new PdfPTable(4);
            PdfPCell cell = new PdfPCell(new Paragraph("Tên sản phẩm" ,f1));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Số lượng" ,f1));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Đơn giá" ,f1));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Thành tiền" ,f1));
            
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);
            t.addCell(cell);
            t.addCell(cell2);
            t.addCell(cell3);
            t.addCell(cell4);
           while(rs.next())
            {
               
                String tensp = rs.getString("TenSP");
                String soluong = rs.getString("SoLuong");
                String dongia = rs.getString("DonGia");
                String thanhtien = rs.getString("ThanhTien");
               
            PdfPCell ns = new PdfPCell(new Paragraph(tensp,f1));
           
                
                
                t.addCell(ns);
                t.addCell(soluong);
                t.addCell(vnmoney.format(Double.parseDouble(dongia)));
                t.addCell(vnmoney.format(Double.parseDouble(thanhtien)));
            }
            Double tt = new BUS_SUA().TongTien(ma);
            document.add(new Paragraph("Tổng : " + vnmoney.format(tt) +"đ",f1));
            document.add(t);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
           
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
   
   
 
}
