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
import DTO.MALOAI;
import DTO.NGUOIDUNG;
import DTO.NHACUNGCAP;
import DTO.NHANVIEN;
import DTO.PHIEUNHAP;
import DTO.SANPHAM;
import DTO.TAIKHOAN;
import GUI.GIAODIENCHINH;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MINH TUAN
 */
public class LOADDULIEU {
    DAO cn = new DAO();
    Statement ps=null;
    ResultSet rs=null;
    public ArrayList<SANPHAM> getList(){
        ArrayList<SANPHAM> list = new ArrayList<>();
        String sql = "select masp,tensp,phanloai.TenLoai,soluong,donvitinh,DonGia,NHACUNGCAP.TenNCC\n" +
"from sanpham,nhacungcap,phanloai\n" +
"where sanpham.TenNhaSX = NHACUNGCAP.MaNCC and SANPHAM.MaLoai = PHANLOAI.MaLoai";
        
        try{
            PreparedStatement ps = cn.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                SANPHAM sp = new SANPHAM();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setMaLoai(rs.getString("TenLoai"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setDonViTinh(rs.getString("DonViTinh"));
                sp.setDonGia(rs.getDouble("DonGia"));
                sp.setMaNCC(rs.getString("TenNCC"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<KHUYENMAI> getListKhuyenMai()
   {
       ArrayList<KHUYENMAI> list = new ArrayList<>();
       String sql = "SELECT * FROM KHUYENMAI";
       try {
           PreparedStatement ps = cn.conn.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           
           while(rs.next())
           {
               KHUYENMAI km = new KHUYENMAI();
               km.setMaKM(rs.getString("MaKM").toUpperCase());
               km.setTenCT(rs.getString("TenCT"));
               km.setNgayBatDau(rs.getString("NgayBD"));
               km.setNgayKetThuc(rs.getString("NgayKT"));
               km.setPhanTram(rs.getFloat("PhanTram"));
               
              list.add(km);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return list;
   }
    public ArrayList<TAIKHOAN> getListTaiKhoan()
    {
        ArrayList<TAIKHOAN> list = new ArrayList<>();
        String sql = "SELECT * FROM NGUOIDUNG";
        
        try {
            PreparedStatement ps = cn.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                TAIKHOAN tk = new TAIKHOAN();
                tk.setTaiKhoan(rs.getString("username"));
                tk.setMatKhau(rs.getString("password"));
                tk.setMaNV(rs.getString("MaNV").toUpperCase());
                tk.setTrangThai(rs.getString("TinhTrang"));
                
                if(tk.getTaiKhoan().equalsIgnoreCase("admin") == false)
                {
                list.add(tk);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return list;
    }
     public ArrayList<NGUOIDUNG> NguoiDung(String tk)
   {
        String sql = "SELECT * FROM NGUOIDUNG WHERE username='"+tk+"'";
        ArrayList<NGUOIDUNG> list = new ArrayList<>();
         try{
          ps = cn.conn.createStatement();
          rs = ps.executeQuery(sql);
          while(rs.next())
          {
             NGUOIDUNG tt = new NGUOIDUNG();
             tt.setUser(rs.getString("username"));
             tt.setPass(rs.getString("password"));
             tt.setManv(rs.getString("MaNV"));
             tt.setHoten(rs.getString("HoTen"));
             tt.setNgaysinh(rs.getString("NgaySinh"));
             tt.setGioitinh(rs.getString("GioiTinh"));
             tt.setDiachi(rs.getString("DiaChi"));
             tt.setEmail(rs.getString("Email"));
             tt.setSdt(rs.getString("SoDienThoai"));
             tt.setTinhtrang(rs.getInt("TinhTrang"));
             list.add(tt);
          }
          
       }catch(Exception e)
       { e.printStackTrace();}
       return list;
   }
    
     public ArrayList<NGUOIDUNG> getListNguoiDung()
   {
        String sql = "SELECT * FROM NGUOIDUNG";
        ArrayList<NGUOIDUNG> list = new ArrayList<>();
         try{
          ps = cn.conn.createStatement();
          rs = ps.executeQuery(sql);
          while(rs.next())
          {
             NGUOIDUNG tt = new NGUOIDUNG();
             tt.setUser(rs.getString("username"));
             tt.setPass(rs.getString("password"));
             tt.setManv(rs.getString("MaNV"));
             tt.setHoten(rs.getString("HoTen"));
             tt.setNgaysinh(rs.getString("NgaySinh"));
             tt.setGioitinh(rs.getString("GioiTinh"));
             tt.setDiachi(rs.getString("DiaChi"));
             tt.setEmail(rs.getString("Email"));
             tt.setSdt(rs.getString("SoDienThoai"));
             tt.setTinhtrang(rs.getInt("TinhTrang"));
             list.add(tt);
          }
          
       }catch(Exception e)
       { e.printStackTrace();}
       return list;
   }
    
    public ArrayList<HOADON> getListHD(){
        ArrayList<HOADON> list = new ArrayList<>();
        String sql = "SELECT * FROM HOADON order by CAST(NgayXuat AS date)";
        
        try{
            PreparedStatement ps = cn.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                HOADON h = new HOADON();
                h.setMahd(rs.getString("MaHD"));
                h.setManv(rs.getString("MaNV"));
                h.setMakm(rs.getString("MaKM"));
                h.setNgayxuat(rs.getString("NgayXuat"));
                //Double tong = this.TongTien(rs.getString("MaHD"));
                h.setTongtien(rs.getDouble("TongTien"));
                h.setMakh(rs.getString("MaKH"));
                list.add(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     public ArrayList<CTHD> getListCTHD(String mahd){
        ArrayList<CTHD> list = new ArrayList<>();
        String sql = "select MaSP,TenSP,sum(SoLuong) as SoLuong,DonGia,sum(SoLuong)*DonGia as ThanhTien from CTHD where MaHD='"+mahd+"' \n" +
"group by MaSP,TenSP,DonGia";
        
        try{
            PreparedStatement ps = cn.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                
                CTHD ch = new CTHD();
                ch.setMasp(rs.getString("MaSP"));
                ch.setTensp(rs.getString("TenSP"));
                ch.setSoluong(rs.getInt("SoLuong"));
                ch.setDongia(rs.getDouble("DonGia"));         
                ch.setThanhtien(rs.getDouble("ThanhTien"));
                list.add(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     public ArrayList<NHACUNGCAP> getListNCC()
    {
        String sql = "Select * from NHACUNGCAP";
        ArrayList<NHACUNGCAP> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            NHACUNGCAP nsx = new NHACUNGCAP();
            nsx.setMaNSX(rs.getString(1));
            nsx.setTenNSX(rs.getString(2));
            list.add(nsx);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public ArrayList<MALOAI> getListMaLoai()
    {
        String sql = "Select * from PHANLOAI";
        ArrayList<MALOAI> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            MALOAI nsx = new MALOAI();
            nsx.setMaloai(rs.getString(1));
            nsx.setTenloai(rs.getString(2));
            list.add(nsx);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public int TongHDcuaKH(String ma)
      {
          String sql = "select count(makh) as tonghoadon from hoadon where makh='"+ma+"'";
        try {
            ps = cn.conn.createStatement();
             rs = ps.executeQuery(sql);
             while(rs.next())
             {
                 return rs.getInt("tonghoadon");
             }
        } catch (SQLException ex) {
            Logger.getLogger(LOADDULIEU.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0;
          
      }
      public double TongTienHDcuaKH(String ma)
      {
          String sql = "select sum(TongTien) as tongtien from hoadon where makh='"+ma+"'";
        try {
            ps = cn.conn.createStatement();
             rs = ps.executeQuery(sql);
             while(rs.next())
             {
                 return rs.getDouble("tongtien");
             }
        } catch (SQLException ex) {
            Logger.getLogger(LOADDULIEU.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0.0;
          
      }
     
       public ArrayList<KHACHHANG> getListKhachHang()
    {
        int tonghd;
        String sql = "select * from KHACHHANG";
        ArrayList<KHACHHANG> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            //String ma = rs.getString("MaKH");
            //int tonghd = this.TongHDcuaKH(ma);
            //double tongtien = this.TongTienHDcuaKH(ma);
            KHACHHANG kh = new KHACHHANG();
            tonghd = this.TongHDcuaKH(rs.getString(1));
            kh.setMakh(rs.getString("MaKH")); 
            kh.setTenkh(rs.getString("HoTen"));
            kh.setSdt(rs.getString("SDT"));
            kh.setTongHD(tonghd);
            kh.setTongTien(123.0);
            list.add(kh);
            tonghd = 0 ;
        }
        } catch (SQLException ex) {
           
        }
        return list;
    }
       public ArrayList<KHACHHANG> getListKhachHang2()
    {
        String sql = "select * from khachhang";
        ArrayList<KHACHHANG> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            KHACHHANG kh = new KHACHHANG();
            kh.setMakh(rs.getString(1));
            kh.setTenkh(rs.getString(2));
            kh.setSdt(rs.getString(3));
            
            list.add(kh);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        public ArrayList<PHIEUNHAP> getListPhieuNhap()
    {
        String sql = "select * from phieunhap";
        ArrayList<PHIEUNHAP> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            PHIEUNHAP kh = new PHIEUNHAP();
            kh.setMaNV(rs.getString("MaNV"));
            kh.setMaPN(rs.getString("MaPN"));
            kh.setNgayNhap(rs.getString("NgayNhap"));
            kh.setTongTien(rs.getDouble("TongTien"));
            
            list.add(kh);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public ArrayList<CTHD> TTGioHang()
    {
        ArrayList<CTHD> list = new ArrayList<>();
        String sql="select sanpham.masp,sanpham.tensp,sum(giohang.soluong) as soluong,sanpham.DonGia,sum((giohang.soluong*sanpham.DonGia)) as thanhtien\n" +
"from sanpham,giohang\n" +
"where giohang.masp = sanpham.masp\n" +
"group by sanpham.TenSP,sanpham.DonGia,sanpham.MaSP";
        try {
            ps=cn.conn.createStatement();
            rs=ps.executeQuery(sql);
            while(rs.next())
            {
                CTHD h = new CTHD();
                h.setMasp(rs.getString("masp"));
                h.setTensp(rs.getString("tensp"));
                h.setSoluong(rs.getInt("soluong"));
                h.setDongia(rs.getDouble("DonGia"));
                h.setThanhtien(rs.getDouble("thanhtien"));
                list.add(h);
            }
        } catch (SQLException ex) {
           
        }
       return list;
    }
      public ArrayList<HOADON> HD_KHACHHANG(String ma)
      {
          String sql = "Select MaHD,NgayXuat,TongTien from HOADON where MaKH='"+ma+"' order by CAST(NgayXuat AS date)";
          ArrayList<HOADON> list = new ArrayList<>();
          try {
              ps = cn.conn.createStatement();
              rs = ps.executeQuery(sql);
              while(rs.next())
              {
                  HOADON h = new HOADON();
                  h.setMahd(rs.getString("MaHD"));
                  h.setNgayxuat(rs.getString("NgayXuat"));
                  h.setTongtien(rs.getDouble("TongTien"));
                  list.add(h);
              }
          } catch (Exception e) {
          }
          return list;
      }
       public ArrayList<CTPN> getListCTPN()
    {
        String sql = "select masp,sum(soluong) as soluong,gianhap,sum(thanhtien) as thanhtien\n" +
"from ctpn\n" +
"where MaPN is null\n" +
"group by masp,gianhap";
        ArrayList<CTPN> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            CTPN kh = new CTPN();
            kh.setMaSP(rs.getString("masp"));
            kh.setGiaNhap(rs.getDouble("gianhap"));
            kh.setThanhTien(rs.getDouble("thanhtien"));
            kh.setSoLuong(rs.getInt("soluong"));
            list.add(kh);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       public ArrayList<CTPN> getListCTPN(String mapn)
    {
        String sql = "select masp,sum(soluong) as soluong,gianhap,sum(thanhtien) as thanhtien\n" +
"from ctpn\n" +
"where MaPN='"+mapn+"'\n" +
"group by masp,gianhap";
        ArrayList<CTPN> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            CTPN kh = new CTPN();
            kh.setMaSP(rs.getString("masp"));
            kh.setGiaNhap(rs.getDouble("gianhap"));
            kh.setThanhTien(rs.getDouble("thanhtien"));
            kh.setSoLuong(rs.getInt("soluong"));
            list.add(kh);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       public ArrayList<NHANVIEN> getListThongTinNhanVien()
   {
       ArrayList<NHANVIEN> list = new ArrayList<>();
       String sql = "SELECT MaNV,HoTen,GioiTinh,NgaySinh,SoDienThoai,DiaChi,Email FROM NGUOIDUNG";
       try {
           PreparedStatement ps = cn.conn.prepareStatement(sql);
           rs = ps.executeQuery();
           
           while(rs.next())
           {
              
           
             NHANVIEN nv = new NHANVIEN();
               nv.setMaNV(rs.getString("MaNV"));
               nv.setHoTen(rs.getString("HoTen"));
               nv.setGioiTinh(rs.getString("GioiTinh"));
               nv.setNgaySinh(rs.getString("NgaySinh"));
               nv.setSDT(rs.getString("SoDienThoai"));
               nv.setDiaChi(rs.getString("DiaChi"));
               nv.setEmail(rs.getString("Email"));
               
               if(nv.getMaNV().equalsIgnoreCase("admin") == false)
               {
                   list.add(nv);
               }
      
              
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return list;
   }
}
