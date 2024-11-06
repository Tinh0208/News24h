package edu.abcd.doancnpm.Model;

import java.io.Serializable;

public class GopY implements Serializable {
    String ten,noidung;

    public GopY(){

    }

    public GopY(String ten, String noidung) {
        this.ten = ten;
        this.noidung = noidung;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
