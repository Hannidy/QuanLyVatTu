/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class model_ChiTietPhieuXuat {
    String maPhieuXuat_CTPX;
    String maVatTu;
    Integer soLuong;

    public model_ChiTietPhieuXuat(String maPhieuXuat_CTPX, String maVatTu, Integer soLuong) {
        this.maPhieuXuat_CTPX = maPhieuXuat_CTPX;
        this.maVatTu = maVatTu;
        this.soLuong = soLuong;
    }
    
    public model_ChiTietPhieuXuat(){
        
    }

    public String getMaPhieuXuat_CTPX() {
        return maPhieuXuat_CTPX;
    }

    public void setMaPhieuXuat_CTPX(String maPhieuXuat_CTPX) {
        this.maPhieuXuat_CTPX = maPhieuXuat_CTPX;
    }

    public String getMaVatTu() {
        return maVatTu;
    }

    public void setMaVatTu(String maVatTu) {
        this.maVatTu = maVatTu;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    
    
}
