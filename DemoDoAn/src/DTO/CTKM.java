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
public class CTKM {
    String MaKM ,NoiDung;
    double PhanTram;

    public CTKM(String MaKM, String NoiDung, double PhanTram) {
        this.MaKM = MaKM;
        this.NoiDung = NoiDung;
        this.PhanTram = PhanTram;
    }
    
    public CTKM()   {
        
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String MaKM) {
        this.MaKM = MaKM;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String NoiDung) {
        this.NoiDung = NoiDung;
    }

    public double getPhanTram() {
        return PhanTram;
    }

    public void setPhanTram(double PhanTram) {
        this.PhanTram = PhanTram;
    }
    
    
    
}
