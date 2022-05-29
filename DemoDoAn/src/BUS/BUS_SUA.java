/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MINH TUAN
 */
public class BUS_SUA {
    DAO cn = new DAO();
    Statement ps;
    ResultSet rs;
    public void suaTongTien(String mahd)
     {
         String sql = "Update HOADON set TongTien = "+ this.TongTien(mahd)  + "where MaHD ='"+mahd+"'";
         try {
             ps = cn.conn.createStatement();
             rs = ps.executeQuery(sql);
         } catch (Exception e) {
         }
     }
    public Double TongTien(String mahd)
    {
        String sql = "Select TongTien from HOADON where MaHD ='"+mahd+"'";
        try
        {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
            while(rs.next())
            {
                return rs.getDouble("TongTien");
            }
        }
        catch(Exception e)
                {}
        return 0.0;
    }
    public boolean suaSP(String macu,String mamoi,String tensp,String maloai,int soluong,String donvitinh,Double dongia,String tennsx)
    {
      String sql = "Update SANPHAM set MaSP='"+mamoi+"',TenSP=N'"+tensp+"',MaLoai='"+maloai+"',SoLuong="+soluong+",DonViTinh=N'"+donvitinh+"',DonGia="+dongia+",TenNhaSX='"+tennsx+"' where MaSP='"+macu+"' ";
        try {
            ps =  cn.conn.createStatement();
            ps.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
        
    }
    public boolean suaKM(String macu , String mamoi , String tenct ,String ngaybd,String ngaykt , float phantram)
   {
       String sql = "Update KHUYEMMAI set MaKM = '"+mamoi+"' , TenCT = N'"+tenct+"' ,NgayBD ='"+ngaybd+",NgayKT ='"+ngaykt+"' ,PhanTram = "+phantram+" WHERE MaKM= '"+macu+"'  ";
       try {
           ps = cn.conn.createStatement();
           ps.executeUpdate(sql);
           return true;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }
    public boolean suasoluongSP(String masp,int soluongmoi)
    {
      String sql = "Update SANPHAM set SoLuong="+soluongmoi+" where MaSP='"+masp+"' ";
        try {
            ps = cn.conn.createStatement();
            ps.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
        
    }
    public boolean suaKH(String ma,String ten,String sdt)
    {
        String sql = "Update KHACHHANG set HoTen='"+ten+"',SDT='"+sdt+"' where MaKH='"+ma+"'";
        try {
            ps=cn.conn.createStatement();
            ps.executeUpdate(sql);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    public boolean suaTK(String taikhoancu , String taikhoanmoi , String matkhau , String manv ,String trangthai)
   {
       String sql = "Update NGUOIDUNG set username='"+taikhoanmoi+"',password='"+matkhau+"',MaNV='"+manv+"' , TinhTrang = '"+trangthai+"' where username = '"+taikhoancu+"'";
       try {
          
           ps = cn.conn.createStatement();
           ps.executeUpdate(sql);
           return true;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }
    public boolean capnhatTTNV(String ma ,String hoten ,String gioitinh ,String ngaysinh , String sodienthoai,String diachi ,String email)
   {
       String sql = "UPDATE NGUOIDUNG SET HoTen=N'"+hoten+"' , GioiTinh=N'"+gioitinh+"', NgaySinh='"+ngaysinh+"' , SoDienThoai=N'"+sodienthoai+"' , DiaChi=N'"+diachi+"' , Email=N'"+email+"' WHERE MaNV = '"+ma+"' ";
       try {
           ps = cn.conn.createStatement();
           ps.executeUpdate(sql);
           return true;
       } catch (Exception e) {
       e.printStackTrace();
       }
       
       return false;
   
   }
    
}
