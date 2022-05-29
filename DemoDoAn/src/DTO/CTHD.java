/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;

/**
 *
 * @author MINH TUAN
 */
public class CTHD {
    public String mahd,masp,tensp;
    public Double dongia,thanhtien;
    public int soluong;
    public CTHD(){}

    public CTHD(String mahd, String masp, String tensp, Double dongia, Double thanhtien, int soluong) {
        this.mahd = mahd;
        this.masp = masp;
        this.tensp = tensp;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
        this.soluong = soluong;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public Double getDongia() {
        return dongia;
    }

    public void setDongia(Double dongia) {
        this.dongia = dongia;
    }

    public Double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(Double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    public static void main(String[] args) {
        ArrayList<String> l = new ArrayList<>();
        l.add(("number"));
        l.add("new");
        l.add("gray");
        for(String a : l)
        {
            System.out.println(a);
        }
        System.out.println("after remove 2");
        l.remove(2);
        System.out.println("0 is " + l.get(2));
    }
}
