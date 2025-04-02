
package DAO;

import model.model_LoaiVatTu;
import util.JDBCHelper;
import java.sql.*;
import java.util.*;

public class DAO_LoaiVatTu {
    
    public void insert(model_LoaiVatTu lvt) throws SQLException {
        String newId = generateMaLoaiVatTu();
        String sql = "INSERT INTO LOAIVATTU (MaLoaiVatTu, TenLoaiVatTu) VALUES (?, ?)";
        JDBCHelper.update(sql, newId, lvt.getTenLoaiVatTu());

        try {
            JDBCHelper.update(sql, lvt.getMaLoaiVatTu(), lvt.getTenLoaiVatTu());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private String generateMaLoaiVatTu() {
    String sql = "SELECT TOP 1 MaLoaiVatTu FROM LOAIVATTU ORDER BY MaLoaiVatTu DESC";
    try (ResultSet rs = JDBCHelper.query(sql)) {
        if (rs.next()) {
            String lastId = rs.getString(1);
            int num = Integer.parseInt(lastId.substring(3)) + 1;
            return "LVT" + String.format("%03d", num);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return "LVT001"; // Mã mặc định nếu chưa có dữ liệu
}


    public void update(model_LoaiVatTu lvt) {
        String sql = "UPDATE LOAIVATTU SET TenLoaiVatTu = ? WHERE MaLoaiVatTu = ?";
        try {
            JDBCHelper.update(sql, lvt.getTenLoaiVatTu(), lvt.getMaLoaiVatTu());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String maLoaiVatTu) {
        String sql = "DELETE FROM LOAIVATTU WHERE MaLoaiVatTu = ?";
        try {
            JDBCHelper.update(sql, maLoaiVatTu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<model_LoaiVatTu> selectAll() {
        String sql = "SELECT * FROM LOAIVATTU";
        return selectBySQL(sql);
    }

    public List<model_LoaiVatTu> selectById(String maLoaiVatTu) {
        String sql = "SELECT * FROM LOAIVATTU WHERE MaLoaiVatTu = ?";
        return selectBySQL(sql, maLoaiVatTu);
    }

    public List<model_LoaiVatTu> selectByKeyWord(String keyword) {
        String sql = "SELECT * FROM LOAIVATTU WHERE TenLoaiVatTu LIKE ?";
        return selectBySQL(sql, "%" + keyword + "%");
    }

    protected List<model_LoaiVatTu> selectBySQL(String sql, Object... args) {
        List<model_LoaiVatTu> list = new ArrayList<>();
        try (ResultSet rs = JDBCHelper.query(sql, args)) {
            while (rs.next()) {
                model_LoaiVatTu lvt = new model_LoaiVatTu();
                lvt.setMaLoaiVatTu(rs.getString("MaLoaiVatTu"));
                lvt.setTenLoaiVatTu(rs.getString("TenLoaiVatTu"));
                list.add(lvt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

