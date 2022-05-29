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
public class KHUYENMAI {
    String MaKM,TenCT,NoiDung,LoaiKM, NgayBatDau ,NgayKetThuc  ;
    float PhanTram;

    public KHUYENMAI(String MaKM, String TenCT, String NoiDung, String LoaiKM, String NgayBatDau, String NgayKetThuc, float PhanTram) {
        this.MaKM = MaKM;
        this.TenCT = TenCT;
        
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.PhanTram = PhanTram;
    }
    
    public KHUYENMAI()
    {
        
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String MaKM) {
        this.MaKM = MaKM;
    }

    public String getTenCT() {
        return TenCT;
    }

    public void setTenCT(String TenCT) {
        this.TenCT = TenCT;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String NoiDung) {
        this.NoiDung = NoiDung;
    }

    public String getLoaiKM() {
        return LoaiKM;
    }

    public void setLoaiKM(String LoaiKM) {
        this.LoaiKM = LoaiKM;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public float getPhanTram() {
        return PhanTram;
    }

    public void setPhanTram(float PhanTram) {
        this.PhanTram = PhanTram;
    }
    
    
    
}
