/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Admin
 */
public class TAIKHOAN {
    public String TaiKhoan ,MatKhau,MaNV;
    public String TrangThai;

    public TAIKHOAN(String TaiKhoan,String MatKhau, String MaNV, String TrangThai) {
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.MaNV = MaNV;
       
        this.TrangThai = TrangThai;
    }

    
    
    public TAIKHOAN()
    {
        
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
    
}
