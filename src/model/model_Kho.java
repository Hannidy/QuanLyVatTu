package model;

public class model_Kho {
    String maKho;
    String tenKho;
    String maLoaiVatTu;
    String diachi;

    public model_Kho(String maKho, String tenKho, String maLoaiVatTu, String diachi) {
        this.maKho = maKho;
        this.tenKho = tenKho;
        this.maLoaiVatTu = maLoaiVatTu;
        this.diachi = diachi;
    }

    public model_Kho() {
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    public String getMaLoaiVatTu() {
        return maLoaiVatTu;
    }

    public void setMaLoaiVatTu(String maLoaiVatTu) {
        this.maLoaiVatTu = maLoaiVatTu;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    
    
}
