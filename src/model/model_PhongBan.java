package model;

public class model_PhongBan {

    String maPhongBan;
    String tenPhongBan;
    String diaChi;
    String matruongPhong;

    public model_PhongBan() {
    }

    public model_PhongBan(String maPhongBan, String tenPhongBan, String diaChi, String matruongPhong) {
        this.maPhongBan = maPhongBan;
        this.tenPhongBan = tenPhongBan;
        this.diaChi = diaChi;
        this.matruongPhong = matruongPhong;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(String maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMatruongPhong() {
        return matruongPhong;
    }

    public void setMatruongPhong(String matruongPhong) {
        this.matruongPhong = matruongPhong;
    }

    
}
