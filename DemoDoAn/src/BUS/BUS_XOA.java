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
public class BUS_XOA {
    Statement ps;
    ResultSet rs;
    DAO cn = new DAO();
     public boolean xoaSP(String ma)
    {
      String sql = "Delete from SANPHAM where MaSP='"+ma+"'";
        try {
            ps = cn.conn.createStatement();
            ps.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
        
    }
     public boolean xoaKM(String ma)
   {
       String sql = "DELETE FROM KHUYENMAI WHERE MaKM = '"+ma+"'";
       
       try {
           ps = cn.conn.createStatement();
           ps.executeUpdate(sql);
           return true;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }
     public void resetGiohang()
    {
        String sql = "Delete from GIOHANG";
        try {
            ps=cn.conn.createStatement();
            ps.executeUpdate(sql);
          
        } catch (Exception e) {
        }
    }
     public void resetCTPN()
    {
        String sql = "Delete from CTPN where MaPN is null";
        try {
            ps=cn.conn.createStatement();
            ps.executeUpdate(sql);
          
        } catch (Exception e) {
        }
    }
     public boolean xoa1spkhoiGiohang(String ma)
    {
        String sql = "Delete from GIOHANG where MaSP='"+ma+"'";
        try {
            ps=cn.conn.createStatement();
            ps.executeUpdate(sql);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
     public boolean xoaKH(String ma)
     {
         String sql = "update hoadon set makh = NULL where makh = '"+ma+"'\n" +
"delete from khachhang where makh = '"+ma+"'";
        try {
            ps = cn.conn.createStatement();
            ps.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BUS_XOA.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
         
     }
}
