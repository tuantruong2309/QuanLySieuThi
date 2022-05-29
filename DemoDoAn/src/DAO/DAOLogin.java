/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.NGUOIDUNG;
import DTO.CTHD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class DAOLogin {
    private Connection conn = null;
    private ResultSet rs = null;
    private Statement sm = null;
    public DAOLogin()
    {
//        try {
//            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QuanLySieuThi;"
//                    + "user=tuan;password=Kames551689");
//            System.out.println("Connected");
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
    
   public int ktraTaiKhoan(NGUOIDUNG tt)
   {
       String sql = "SELECT * FROM NGUOIDUNG WHERE username='"+tt.getUser()+"'";
       try{
          sm = conn.createStatement();
          rs = sm.executeQuery(sql);
          while(rs.next())
          {
              if(tt.getPass().equals(rs.getString("password"))&&Integer.parseInt(rs.getString("TinhTrang"))==1)
              {
                  return 1;
              }
              else if(tt.getPass().equals(rs.getString("password"))&&Integer.parseInt(rs.getString("TinhTrang"))==2)
              {
                  return 2;
              }
          }
          
       }catch(Exception e)
       { e.printStackTrace();}
       return 0;
   }
    
    
    public static void main(String[] args)
    {
       DAOLogin d = new DAOLogin();
     
    }
    
     
}
