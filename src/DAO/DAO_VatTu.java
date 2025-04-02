package DAO;

import model.model_VatTu;
import util.JDBCHelper;
import java.sql.*;
import java.util.*;
import java.sql.ResultSet;

public class DAO_VatTu extends DAO_HeThongQuanLyVatTu<model_VatTu, String> {

public void insert(model_VatTu vt) {
    try {
        String newMaVatTu = generateNewMaVatTu(); // Tạo mã vật tư mới tự động tăng
        String sql = "INSERT INTO VATTU (MaVatTu, TenVatTu, MaLoaiVatTu) VALUES (?, ?, ?)";
        JDBCHelper.update(sql, newMaVatTu, vt.getTenVatTu(), vt.getMaLoaiVatTu());
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi ra console
    }
}


    public void update(model_VatTu vt) {
    String sql = "UPDATE VATTU SET TenVatTu = ?, MaLoaiVatTu = ? WHERE MaVatTu = ?";
    try {
        JDBCHelper.update(sql, vt.getTenVatTu(), vt.getMaLoaiVatTu(), vt.getMaVatTu());
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi ra console
    }
}

public void delete(String maVatTu) {
    String sql = "DELETE FROM VATTU WHERE MaVatTu = ?";
    try {
        JDBCHelper.update(sql, maVatTu);
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi ra console
    }
}


    private String generateNewMaVatTu() throws SQLException {
    String sql = "SELECT MAX(MaVatTu) FROM VATTU";
    
    try (ResultSet rs = JDBCHelper.query(sql)) { // Sử dụng query() trực tiếp
        if (rs.next()) {
            String maxId = rs.getString(1);
            if (maxId == null) {
                return "VT01"; // Nếu chưa có dữ liệu, bắt đầu từ VT01
            }
            int newId = Integer.parseInt(maxId.substring(2)) + 1;
            return String.format("VT%02d", newId); // Định dạng VT00000001, VT00000002, ...
        }
    }
    return "VT01"; // Trường hợp lỗi hoặc không có dữ liệu
}




    public String selectMaxId() {
        String sql = "SELECT MAX(CAST(SUBSTRING(MaVatTu, 3, LEN(MaVatTu)-2) AS INT)) FROM VATTU";
        String newMaVatTu = "VT1";// Mặc định nếu bảng rỗng.
        try {
            ResultSet rs = JDBCHelper.query(sql);
            if (rs == null) {
                System.out.println("⚠ Không thể lấy dữ liệu: ResultSet trả về null.");
                return newMaVatTu;
            }
            if (rs.next() && rs.getObject(1) != null) {
                int maxMaVatTu = rs.getInt(1);
                newMaVatTu = "VT" + (maxMaVatTu + 1); // Tạo NV tiếp theo
            }
            if (rs != null && rs.getObject(1) != null) {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newMaVatTu;
    }

    public List<model_VatTu> selectById(String maVatTu) {
        String sql = "SELECT * FROM VATTU WHERE MaVatTu = ?";
        return selectBySQL(sql, maVatTu);
    }

    public List<model_VatTu> selectByKeyWord(String keyWord, String columnVatTu) {
        String sql;
        String keyWord_2 = "%" + keyWord + "%";

        switch (columnVatTu) {
            case "Mã Vật Tư":
                sql = "SELECT MaVatTu, TenVatTu, MaLoaiVatTu FROM VATTU WHERE MaVatTu LIKE ?";
                return this.selectBySQL(sql, keyWord_2);
            case "Tên Vật Tư":
                sql = "SELECT MaVatTu, TenVatTu, MaLoaiVatTu FROM VATTU WHERE TenVatTu LIKE ?";
                return this.selectBySQL(sql, keyWord_2);
            case "Mã Loại Vật Tư":
                sql = "SELECT MaVatTu, TenVatTu, MaLoaiVatTu FROM VATTU WHERE MaLoaiVatTu LIKE ?";
                return this.selectBySQL(sql, keyWord_2);
            default:
                // Nếu không chỉ định cột cụ thể, tìm trên tất cả 3 cột
                sql = "SELECT MaVatTu, TenVatTu, MaLoaiVatTu FROM VATTU WHERE "
                        + "MaVatTu LIKE ? OR "
                        + "TenVatTu LIKE ? OR "
                        + "MaLoaiVatTu LIKE ?";
                return this.selectBySQL(sql, keyWord_2, keyWord_2, keyWord_2);
        }
    }

    public List<model_VatTu> selectAll() {
        String sql = "SELECt * FROM VATTU";
        return this.selectBySQL(sql);
    }

    protected List<model_VatTu> selectBySQL(String sql, Object... args) {
        List<model_VatTu> list_VatTu = new ArrayList<>();
        ResultSet rs = null;

        try {
            rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                model_VatTu vt = new model_VatTu();
                vt.setMaVatTu(rs.getString("MaVatTu")); // Lấy Mã Vật Tư
                vt.setTenVatTu(rs.getString("TenVatTu")); // Lấy Tên Vật Tư
                vt.setMaLoaiVatTu(rs.getString("MaLoaiVatTu")); // Lấy Mã Loại Vật Tư

                list_VatTu.add(vt);
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
        return list_VatTu;
    }

}
