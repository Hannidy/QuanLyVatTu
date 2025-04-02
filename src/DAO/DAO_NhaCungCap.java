package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.model_NhaCungCap;
import util.JDBCHelper;

public class DAO_NhaCungCap extends DAO_HeThongQuanLyVatTu<model_NhaCungCap, String> {

    public void insert(model_NhaCungCap ncc) {
        String sql = "INSERT INTO NHACUNGCAP (MaNhaCungCap, TenNhaCungCap, SoDienThoai, Email, DiaChi) VALUES (?, ?, ?, ?, ?)";
        String newmaNCC = this.selectMaxId();

        JDBCHelper.update(sql,
                newmaNCC,
                ncc.getTenNhaCungCap(),
                ncc.getSoDienThoai(),
                ncc.getEmail(),
                ncc.getDiaChi());
    }

    public void update(model_NhaCungCap ncc) {
        String sql = "UPDATE NHACUNGCAP SET TenNhaCungCap = ?, SoDienThoai = ?, Email = ?, DiaChi = ? WHERE MaNhaCungCap = ?";
        JDBCHelper.update(sql, ncc.getTenNhaCungCap(),
                ncc.getSoDienThoai(),
                ncc.getEmail(),
                ncc.getDiaChi(),
                ncc.getMaNhaCungCap());

    }

    public void delete(String maNhaCungCap) {
        String sql = "DELETE FROM NHACUNGCAP WHERE MaNhaCungCap = ?";
        JDBCHelper.update(sql, maNhaCungCap);
    }

    public String selectMaxId() {
        String sql = "SELECT MAX(CAST(SUBSTRING(MaNhaCungCap, 3, LEN(MaNhaCungCap)-2) AS INT)) FROM NHACUNGCAP";
        String newMaNhaCungCap = "NCC1";// Mặc định nếu bảng rỗng.
        try {
            ResultSet rs = JDBCHelper.query(sql);;
            if (rs == null) {
                System.out.println("⚠ Không thể lấy dữ liệu: ResultSet trả về null.");
                return newMaNhaCungCap;
            }
            if (rs.next() && rs.getObject(1) != null) {
                int maxMaNhaCungCap = rs.getInt(1);
                newMaNhaCungCap = "NCC" + (maxMaNhaCungCap + 1); // Tạo NCC tiếp theo
            }
            if (rs != null && rs.getObject(1) != null) {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newMaNhaCungCap;
    }

    public List<model_NhaCungCap> selectById(String keyWord) {
        String sql = "SELECT * FROM NHACUNGCAP WHERE MaNhaCungCap LIKE ? OR TenNhaCungCap LIKE ?";
        String keyWord_2 = "%" + keyWord + "%";
        return this.selectBySQL(sql, keyWord_2, keyWord_2);
    }

    public List<model_NhaCungCap> selectByKeyWord(String keyWord, String columnKho) {
        String sql1 = "SELECT * FROM NHACUNGCAP WHERE MaNhaCungCao LIKE ?";
        String sql2 = "SELECT * FROM NHACUNGCAP WHERE TenNhaCungCap LIKE ?";
        String sql3 = "SELECT * FROM NHACUNGCAP WHERE Email LIKE ?";
        String sql4 = "SELECT * FROM NHACUNGCAP WHERE DiaChi LIKE ?";

        String keyWord_2 = "%" + keyWord + "%";
//        if(){
        return this.selectBySQL(sql1, keyWord, keyWord_2);
//        }
    }

    public List<model_NhaCungCap> selectAll() {
        String sql = "SELECt * FROM NHACUNGCAP";
        return this.selectBySQL(sql);
    }

    protected List<model_NhaCungCap> selectBySQL(String sql, Object... args) {
        List<model_NhaCungCap> list_NhaCungCap = new ArrayList<model_NhaCungCap>();
        try {
            ResultSet rs = null;

            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    model_NhaCungCap ncc = new model_NhaCungCap();
                    ncc.setMaNhaCungCap(rs.getString("MaNhaCungCap"));
                    ncc.setTenNhaCungCap(rs.getString("TenNhaCungCap"));
                    ncc.setSoDienThoai(rs.getString("SoDienThoai"));
                    ncc.setEmail(rs.getString("Email"));
                    ncc.setDiaChi(rs.getString("DiaChi"));

                    list_NhaCungCap.add(ncc);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list_NhaCungCap;
    }
}
