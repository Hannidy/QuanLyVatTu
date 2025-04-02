/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import util.JDBCHelper;
import java.sql.*;
import java.util.*;
import java.sql.ResultSet;
import model.model_Kho;

/**
 *
 * @author RubyNgoc
 */
public class DAO_Kho {

//    public void insert(model_Kho kho) {
//        String sql = "INSERT INTO KHO (MaKho, TenKho, MaLoaiVatTu, DiaChi) VALUES (?, ?, ?, ?)";
//        String newMaKho = this.selectMaxId(); // Lấy mã vật tư mới
//
//        JDBCHelper.update(sql,
//                newMaKho,
//                kho.getTenKho(),
//                kho.getMaLoaiVatTu(),
//                kho.getDiachi()); // Sửa dấu chấm phẩy thành dấu đóng ngoặc
//    }
//
//    public void update(model_Kho kho) {
//        String sql = "UPDATE KHO SET TenKho = ?, MaLoaiVatTu = ?, DiaChi = ? WHERE MaKho = ?";
//        JDBCHelper.update(sql,
//                kho.getTenKho(),
//                kho.getMaLoaiVatTu(),
//                kho.getDiachi(),
//                kho.getMaKho());
//    }
//
//    public void delete(String maKHO) {
//        String sql = "DELETE FROM KHO WHERE MaKho = ?";
//        JDBCHelper.update(sql, maKHO);
//    }
    
    public static List<Object[]> kiemKeHangHoa() {
    List<Object[]> danhSachHangHoa = new ArrayList<>();
    String sql = """
        SELECT 
            tk.MaKho, 
            tk.MaVatTu, 
            pn.NgayNhap AS NgayNhap, -- Chỉ lấy ngày nhập từ PHIEUNHAP
            tk.SoLuong AS SoLuongTonDauThang, 
            COALESCE(SUM(pnct.SoLuong), 0) AS SoLuongNhap, 
            COALESCE(SUM(pxct.SoLuong), 0) AS SoLuongXuat, 
            (tk.SoLuong + COALESCE(SUM(pnct.SoLuong), 0) - COALESCE(SUM(pxct.SoLuong), 0)) AS SoLuongTonCuoiThang
        FROM TONKHO tk
        LEFT JOIN CHITIETPHIEUNHAP pnct ON tk.MaVatTu = pnct.MaVatTu
        LEFT JOIN PHIEUNHAP pn ON pnct.MaPhieuNhap = pn.MaPhieuNhap -- Chỉ JOIN với PHIEUNHAP để lấy ngày nhập
        LEFT JOIN CHITIETPHIEUXUAT pxct ON tk.MaVatTu = pxct.MaVatTu
        GROUP BY 
            tk.MaKho, 
            tk.MaVatTu, 
            tk.SoLuong, 
            pn.NgayNhap -- Chỉ giữ lại Ngày nhập trong GROUP BY
        ORDER BY tk.MaKho, tk.MaVatTu;
    """; 

    try (ResultSet rs = JDBCHelber.query(sql)) {
        while (rs.next()) {
            danhSachHangHoa.add(new Object[]{
                rs.getString("MaKho"),
                rs.getString("MaVatTu"),
                rs.getDate("NgayNhap"),  // ✅ Chỉ lấy ngày nhập
                rs.getInt("SoLuongTonDauThang"),
                rs.getInt("SoLuongNhap"),
                rs.getInt("SoLuongXuat"),
                rs.getInt("SoLuongTonCuoiThang")
            });
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi kiểm kê hàng hóa: " + e.getMessage());
        e.printStackTrace();
    }
    return danhSachHangHoa;
}

    public String selectMaxId() {
        String sql = "SELECT MAX(CAST(SUBSTRING(MaKho, 3, LEN(MaKho)-2) AS INT)) FROM KHO";
        String newMaKho = "K01";// Mặc định nếu bảng rỗng.
        try {
            ResultSet rs = JDBCHelper.query(sql);
            if (rs == null) {
                System.out.println("⚠ Không thể lấy dữ liệu: ResultSet trả về null.");
                return newMaKho;
            }
            if (rs.next() && rs.getObject(1) != null) {
                int maxMaKho = rs.getInt(1);
                newMaKho = "K" + (maxMaKho + 1); // Tạo NV tiếp theo
            }
            if (rs != null && rs.getObject(1) != null) {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newMaKho;
    }

    public List<model_Kho> selectById(String maKho) {
        String sql = "SELECT * FROM Kho WHERE MaKho = ?";
        return selectBySQL(sql, maKho);
    }

    
    public List<model_Kho> selectAll() {
        String sql = "SELECT * FROM KHO";
        return this.selectBySQL(sql);
    }
    
    protected List<model_Kho> selectBySQL(String sql, Object... args) {
        List<model_Kho> list_Kho = new ArrayList<>();
        ResultSet rs = null;

        try {
            rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                model_Kho kho = new model_Kho();
                kho.setMaKho(rs.getString("MaKho")); // Lấy Mã Vật Tư
                kho.setTenKho(rs.getString("TenKho")); // Lấy Tên Vật Tư
                kho.setMaLoaiVatTu(rs.getString("MaLoaiVatTu")); // Lấy Mã Loại Vật Tư
                kho.setDiachi(rs.getString("DiaChi")); // Lấy Mã Loại Vật Tư

                list_Kho.add(kho);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.getStatement().close(); // Đóng Statement trước
                    rs.close(); // Đóng ResultSet sau
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list_Kho;
    }
}
