package DAO;


import util.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO_ChuyenDoiDonVi {
    
    public boolean kiemTraDonViTonKho(String maKho, String maVatTu, String donViMoi) {
        String sql = "SELECT DonVi FROM TONKHO WHERE MaKho = ? AND MaVatTu = ?";
        try (ResultSet rs = JDBCHelper.query(sql, maKho, maVatTu)) {
            if (rs.next()) {
                String donViHienTai = rs.getString("DonVi");
                return donViHienTai.equalsIgnoreCase(donViMoi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean chuyenDoiDonViTonKho(String maKho, String maVatTu, String donViMoi, double heSoQuyDoi) {
        if (heSoQuyDoi <= 0) {
            System.out.println("Hệ số quy đổi không hợp lệ!");
            return false;
        }
        
        String sqlLaySoLuong = "SELECT SoLuong, DonVi FROM TONKHO WHERE MaKho = ? AND MaVatTu = ?";
        try (ResultSet rs = JDBCHelper.query(sqlLaySoLuong, maKho, maVatTu)) {
            if (rs.next()) {
                int soLuongHienTai = rs.getInt("SoLuong");
                String donViHienTai = rs.getString("DonVi");
                
                if (donViHienTai.equalsIgnoreCase(donViMoi)) {
                    System.out.println("Vật tư đã có đơn vị này rồi!");
                    return false;
                }
                
                int soLuongMoi = (int) Math.round(soLuongHienTai * heSoQuyDoi);
                
                String sqlUpdate = "UPDATE TONKHO SET SoLuong = ?, DonVi = ? WHERE MaKho = ? AND MaVatTu = ?";
                JDBCHelper.update(sqlUpdate, soLuongMoi, donViMoi, maKho, maVatTu);
                return true;
            } else {
                System.out.println("Không tìm thấy vật tư trong kho!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

