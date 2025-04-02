
package model;


public class model_LoaiVatTu {
    
    String MaLoaiVatTu ;
    String TenLoaiVatTu ;

    public model_LoaiVatTu() {
    }

    public model_LoaiVatTu(String MaLoaiVatTu, String TenLoaiVatTu) {
        this.MaLoaiVatTu = MaLoaiVatTu;
        this.TenLoaiVatTu = TenLoaiVatTu;
    }

    public String getMaLoaiVatTu() {
        return MaLoaiVatTu;
    }

    public void setMaLoaiVatTu(String MaLoaiVatTu) {
        this.MaLoaiVatTu = MaLoaiVatTu;
    }

    public String getTenLoaiVatTu() {
        return TenLoaiVatTu;
    }

    public void setTenLoaiVatTu(String TenLoaiVatTu) {
        this.TenLoaiVatTu = TenLoaiVatTu;
    }

    
}
