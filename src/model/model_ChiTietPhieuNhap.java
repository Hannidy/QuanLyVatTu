package model;

/**
 *
 * @author ADMIN
 */
public class model_ChiTietPhieuNhap {
    String MaPhieuNhap;
    String MaVatTu;
    Integer SoLuong;

    public model_ChiTietPhieuNhap(String MaPhieuNhap, String MaVatTu, Integer SoLuong) {
        this.MaPhieuNhap = MaPhieuNhap;
        this.MaVatTu = MaVatTu;
        this.SoLuong = SoLuong;
    }
    
    public model_ChiTietPhieuNhap(){
        
    }

    public String getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(String MaPhieuNhap) {
        this.MaPhieuNhap = MaPhieuNhap;
    }

    public String getMaVatTu() {
        return MaVatTu;
    }

    public void setMaVatTu(String MaVatTu) {
        this.MaVatTu = MaVatTu;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }
    
    
}
