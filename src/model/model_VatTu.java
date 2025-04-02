package model;

public class model_VatTu {
    String maVatTu;
    String tenVatTu;
    String maLoaiVatTu;
    
    public model_VatTu(){
    
    }

    public model_VatTu(String maVatTu, String tenVatTu, String maLoaiVatTu) {
        this.maVatTu = maVatTu;
        this.tenVatTu = tenVatTu;
        this.maLoaiVatTu = maLoaiVatTu;
    }

    public String getMaVatTu() {
        return maVatTu;
    }

    public void setMaVatTu(String maVatTu) {
        this.maVatTu = maVatTu;
    }

    public String getTenVatTu() {
        return tenVatTu;
    }

    public void setTenVatTu(String tenVatTu) {
        this.tenVatTu = tenVatTu;
    }

    public String getMaLoaiVatTu() {
        return maLoaiVatTu;
    }

    public void setMaLoaiVatTu(String maLoaiVatTu) {
        this.maLoaiVatTu = maLoaiVatTu;
    }
    
    
}