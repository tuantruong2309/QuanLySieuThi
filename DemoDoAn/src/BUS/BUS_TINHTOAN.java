/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author MINH TUAN
 */
public class BUS_TINHTOAN {
    DAO cn = new DAO();
    Statement ps;
    ResultSet rs;
    public int tongsp(String ma)
     {
         String sql = "select sum(soluong) as tongsp from giohang where masp = '"+ma+"'";
         try {
             ps=cn.conn.createStatement();
             rs = ps.executeQuery(sql);
             while(rs.next())
             {
                 return rs.getInt("tongsp");
             }
         } catch (Exception e) {
         }
         return 0;
     }
      public int tongspkho(String ma)
     {
         String sql = "select sum(soluong) as tongsp from sanpham where masp = '"+ma+"'";
         try {
             ps=cn.conn.createStatement();
             rs = ps.executeQuery(sql);
             while(rs.next())
             {
                 return rs.getInt("tongsp");
             }
         } catch (Exception e) {
         }
         return 0;
     }
      public int demNV()
   {
       
        String sql = "SELECT COUNT(MaNV) as TongNV FROM NGUOIDUNG";
       try {
           ps = cn.conn.createStatement();
           rs = ps.executeQuery(sql);
           while(rs.next())
           {
               return Integer.parseInt(rs.getString("TongNV"))-1;
           }
           
       } catch (Exception e) {
           e.printStackTrace();
       }
       return 0;
   }
   
   public int demSP()
   {
       String sql = "SELECT COUNT(MaSP) as TongSP FROM SANPHAM";
       try {
           ps = cn.conn.createStatement();
           rs = ps.executeQuery(sql);
           while(rs.next())
           {
               return Integer.parseInt(rs.getString("TongSP"));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return 0;
   }
   
   public int demHD()
   {
       String sql = "SELECT COUNT(MaHD) as TongHD FROM HOADON";
       try {
           ps = cn.conn.createStatement();
           rs = ps.executeQuery(sql);
           while(rs.next())
           {
               return Integer.parseInt(rs.getString("TongHD"));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return 0;
   }
}
