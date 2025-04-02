package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class model_PhieuNhap {
    String maPhieuNhap;
    Date ngayNhap;
    String maKho;
    String maNhaCungCap;
    String trangThai;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    
    public model_PhieuNhap() {
    }

    public model_PhieuNhap(String maPhieuNhap, Date ngayNhap, String maKho, String maNhaCungCap, String trangThai) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.maKho = maKho;
        this.maNhaCungCap = maNhaCungCap;
        this.trangThai = trangThai;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getNgayNhapFormatted() {
        return sdf.format(ngayNhap);
    }

    public void setNgayNhapFormatted(String ngayNhapStr) throws ParseException {
        this.ngayNhap = sdf.parse(ngayNhapStr);
    }
    
    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }
    
    public String getTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
