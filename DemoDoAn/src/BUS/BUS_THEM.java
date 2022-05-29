/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.CTHD;
import DTO.CTPN;
import DTO.HOADON;
import DTO.KHACHHANG;
import DTO.KHUYENMAI;
import DTO.NHANVIEN;
import DTO.PHIEUNHAP;
import DTO.SANPHAM;
import DTO.TAIKHOAN;
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
 * @author MINH TUAN
 */
public class BUS_THEM {
    DAO cn=new DAO();
    Statement ps;
    ResultSet rs;
     public boolean themSP(SANPHAM s)
    {
      String sql = "INSERT INTO SANPHAM(MaSP,TenSP,MaLoai,SoLuong,DonViTinh,DonGia,TenNhaSX)"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.conn.prepareStatement(sql);
            ps.setString(1,s.getMaSP());
            ps.setString(2,s.getTenSP());
            ps.setString(3,s.getMaLoai());
            ps.setInt(4,s.getSoLuong());
            ps.setString(5,s.getDonViTinh());
            ps.setDouble(6, s.getDonGia());
            ps.setString(7, s.getMaNCC());
            return ps.executeUpdate() > 0;
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
     public boolean themHD(HOADON h)
    {
      String sql = "INSERT INTO HOADON(MaHD,MaNV,NgayXuat,TongTien)"
                + "VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = cn.conn.prepareStatement(sql);   
            ps.setString(1,h.getMahd());
            ps.setString(2, h.getManv());
            ps.setString(3,h.getNgayxuat());
            ps.setDouble(4,h.getTongtien());
            return ps.executeUpdate() > 0;
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
     public boolean themKM(KHUYENMAI km)
   {
       String sql = "INSERT INTO KHUYENMAI (MaKM,TenCT,NgayBD,NgayKT,PhanTram) VALUES(?,?,?,?,?)";
       try {
           PreparedStatement ps = cn.conn.prepareStatement(sql);
           
           ps.setString(1, km.getMaKM());
           ps.setString(2, km.getTenCT());
           ps.setString(3, km.getNgayBatDau());
           ps.setString(4, km.getNgayKetThuc());
           ps.setFloat(5,km.getPhanTram());
           
           return ps.executeUpdate() > 0;
           
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       return false;
   }
      public boolean themCTHD(CTHD ch)
    {
        String sql = "INSERT INTO CTHD(MaHD,MaSP,TenSP,SoLuong,DonGia,ThanhTien)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.conn.prepareStatement(sql);         
            ps.setString(1, ch.getMahd());
            ps.setString(2,ch.getMasp());
            ps.setString(3,ch.getTensp());
            ps.setInt(4,ch.getSoluong());
            ps.setDouble(5,ch.getDongia());
            ps.setDouble(6,ch.getThanhtien());
            return ps.executeUpdate() > 0;
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
      public boolean themTK(TAIKHOAN tk)
    {
        String sql = "INSERT INTO NGUOIDUNG(username,password,MaNV,TinhTrang) VALUES(?,?,?,?)";
        try {
            PreparedStatement sm = cn.conn.prepareStatement(sql);
            sm.setString(1,tk.getTaiKhoan());
            sm.setString(2, tk.getMatKhau());
            sm.setString(3, tk.getMaNV());
            sm.setString(4, tk.getTrangThai());
            
            return sm.executeUpdate() > 0 ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
      public boolean themKH(KHACHHANG kh)
    {
        String sql = "INSERT INTO KHACHHANG(MaKH,HoTen,SDT)"
                + "VALUES(?,?,?)";
        try {
            PreparedStatement ps = cn.conn.prepareStatement(sql);         
            ps.setString(1, kh.getMakh());
            ps.setString(2,kh.getTenkh());
            ps.setString(3,kh.getSdt());
           
            return ps.executeUpdate() > 0;
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
       public boolean ThemThongTinKhac(String table,String ma,String ten)
    {
        String sql="Insert into " + table +" values('"+ma+"','"+ten+"')";
        try {
            ps = cn.conn.createStatement();
            ps.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Da them thong tin");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Co loi xay ra");
        }
        return false;
    }
       public boolean TaoHD(CTHD h,String MaHD)
    {
        PreparedStatement ps = null;
        String sql = "INSERT INTO CTHD(MaHD,MaSP,TenSP,SoLuong,DonGia,ThanhTien)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            ps = cn.conn.prepareStatement(sql);         
            ps.setString(1,MaHD);
            ps.setString(2,h.getMasp());
            ps.setString(3,h.getTensp());
            ps.setInt(4,h.getSoluong());
            ps.setDouble(5,h.getDongia());
            ps.setDouble(6,h.getThanhtien());
            return ps.executeUpdate() > 0;
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
     
        
        
        return false;
    }
     public boolean themHD2(ArrayList<CTHD> list,String mahd,float phantram,String tenctkm,String makh,String manv)
    {
      String sql = "INSERT INTO HOADON(MaHD,MaNV,NgayXuat,TongTien,MaKM,MaKH)"
                + "VALUES(?,?,?,?,?,?)";
                double tong=0.0;
                for(CTHD h : list)
                {
                    tong+=h.getThanhtien();
                }
                Date day = new Date();
        try {
            PreparedStatement ps = cn.conn.prepareStatement(sql);   
            ps.setString(1,mahd);
            ps.setString(2,manv);
            ps.setString(3,String.valueOf(new SimpleDateFormat("yyyy/MM/dd").format(day)));
            Double dagiam = tong*(100.0-phantram)/100.0;
            ps.setDouble(4,dagiam);
            if(phantram==0)
            {
                 ps.setString(5,"Không có");
            }
            else
            {
                 ps.setString(5,tenctkm + "(-"+phantram+"%)");
            }
            ps.setString(6, makh);
            return ps.executeUpdate() > 0;
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    public boolean ThemGioHang(String masp,int soluong)
    {
        PreparedStatement ps = null;
        String sql = "INSERT INTO GIOHANG(MaSP,SoLuong)"
                + "VALUES(?,?)";
        try {
            ps = cn.conn.prepareStatement(sql);         
            ps.setString(1,masp);
            ps.setInt(2,soluong);
            return ps.executeUpdate() > 0;
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
     
        
        
        return false;
    }
    public boolean themTTNV(NHANVIEN nv)
   {
       String sql =" INSERT INTO NGUOIDUNG(HoTen,GioiTinh,NgaySinh,SoDienThoai,DiaChi,Email) VALUES(?,?,?,?,?,?)";
       try {
           PreparedStatement ps = cn.conn.prepareStatement(sql);
           
           
           ps.setString(1, nv.getHoTen());
           ps.setString(2, nv.getGioiTinh());
           ps.setString(3, nv.getNgaySinh());
           ps.setString(4, nv.getSDT());
           ps.setString(5, nv.getDiaChi());
           ps.setString(6, nv.getEmail());
           
           return ps.executeUpdate() > 0 ;
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       return false;
   }
    public boolean ThemCTPN(CTPN ctpn)
    {
        String sql = "INSERT INTO CTPN VALUES("+null+",'"+ctpn.getMaSP()+"',"+ctpn.getSoLuong()+","+ctpn.getGiaNhap()+","+ctpn.getThanhTien()+")";
        try {
            ps = cn.conn.createStatement();
             ps.executeUpdate(sql);
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(BUS_THEM.class.getName()).log(Level.SEVERE, null, ex);
        }
       return false;
    }
    public boolean ThemPN(PHIEUNHAP p)
    {
        String sql = "insert into phieunhap values('"+p.getMaPN()+"','"+p.getMaNV()+"','"+p.getNgayNhap()+"',"+p.getTongTien()+")";
        try {
            ps = cn.conn.createStatement();
             ps.executeUpdate(sql);
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(BUS_THEM.class.getName()).log(Level.SEVERE, null, ex);
        }
       return false;
    }
    public boolean ThemCTPN_CT(CTPN ctpn)
    {
        String sql = "INSERT INTO CTPN VALUES('"+ctpn.getMaPN()+"','"+ctpn.getMaSP()+"',"+ctpn.getSoLuong()+","+ctpn.getGiaNhap()+","+ctpn.getThanhTien()+")";
        try {
            ps = cn.conn.createStatement();
             ps.executeUpdate(sql);
             return true;
        } catch (SQLException ex) {
            Logger.getLogger(BUS_THEM.class.getName()).log(Level.SEVERE, null, ex);
        }
       return false;
    }
    
}
