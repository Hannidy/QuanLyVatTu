
package DAO;

import java.util.ArrayList;
import util.JDBCHelper;
import java.util.List;
import model.model_NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.model_Kho;


public class DAO_NhanVien extends DAO_HeThongQuanLyVatTu<model_NhanVien, String> {

    public void insert(model_NhanVien nv) {
        String sql = "INSERT INTO NHANVIEN (MaNhanVien, MatKhau, TenNhanVien, Email, VaiTro, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";
        String newMaNhanVien = this.selectMaxId();
        JDBCHelper.update(sql,
                newMaNhanVien,
                nv.getMatKhau(),
                nv.getTenNhanVien(),
                nv.getEmail(),
                nv.getVaiTro(),
                nv.getTrangThai());
    }

    public void update(model_NhanVien nv) {
        String sql = "UPDATE NHANVIEN SET MatKhau = ?, TenNhanVien = ?, Email = ?, VaiTro = ?, TrangThai = ? WHERE MaNhanVien = ?";
        JDBCHelper.update(sql, nv.getMatKhau(),
                nv.getTenNhanVien(),
                nv.getEmail(),
                nv.getVaiTro(),
                nv.getTrangThai(),
                nv.getMaNhanVien());

    }

    public void delete(String maNhanVien) {
        String sql = "DELETE FROM NHANVIEN WHERE MaNhanVien = ?";
        JDBCHelper.update(sql, maNhanVien);
    }

    public String selectMaxId() {
        String sql = "SELECT MAX(CAST(SUBSTRING(MaNhanVien, 3, LEN(MaNhanVien)-2) AS INT)) FROM NHANVIEN";
        String newMaNhanVien = "NV1";// Mặc định nếu bảng rỗng.
        try{
        ResultSet rs = JDBCHelper.query(sql);
            if (rs == null) {
            System.out.println("⚠ Không thể lấy dữ liệu: ResultSet trả về null.");
            return newMaNhanVien;
            }    
            if(rs.next() && rs.getObject(1) != null){
                int maxMaNhanVien = rs.getInt(1);
                newMaNhanVien = "NV" + (maxMaNhanVien + 1); // Tạo NV tiếp theo
            }
            if(rs != null && rs.getObject(1) != null){
                rs.getStatement().getConnection().close();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newMaNhanVien;
    }
    
    public List<model_NhanVien> selectById(String maNhanVien) {
        String sql = "SELECT * FROM NHANVIEN WHERE MaNhanVien = ?";
        return this.selectBySQL(sql, maNhanVien);
    }

    public List<model_NhanVien> selectAll() {
        String sql = "SELECT * FROM NHANVIEN";
        return this.selectBySQL(sql);
    }

    public List<model_NhanVien>selectByKeyWord(String keyWord, String columnNhanVien){
        String sql1 = "SELECT * FROM NHANVIEN WHERE MaNhanVien LIKE ?";
        String sql2 = "SELECT * FROM NHANVIEN WHERE MatKhau LIKE ?";
        String sql3 = "SELECT * FROM NHANVIEN WHERE TenNhanVien LIKE ?";
        String sql4 = "SELECT * FROM NHANVIEN WHERE Email LIKE ?";
        String sql5 = "SELECT * FROM NHANVIEN WHERE VaiTro LIKE ?";
        String sql6 = "SELECT * FROM NHANVIEN WHERE TrangThai LIKE ?";
        
        String keyWord_2 = "%" + keyWord + "%";
//        if(){
        return this.selectBySQL(sql1, keyWord, keyWord_2);
//        }
    }
    
    protected List<model_NhanVien> selectBySQL(String sql, Object... args) {
        List<model_NhanVien> list_NhanVien = new ArrayList<model_NhanVien>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    model_NhanVien nv = new model_NhanVien();
                    nv.setMaNhanVien(rs.getString("MaNhanVien"));
                    nv.setMatKhau(rs.getString("MatKhau"));
                    nv.setTenNhanVien(rs.getString("TenNhanVien"));
                    nv.setEmail(rs.getString("Email"));
                    nv.setVaiTro(rs.getBoolean("VaiTro"));
                    nv.setTrangThai(rs.getBoolean("TrangThai"));
                    list_NhanVien.add(nv);
                }
            } finally{
                rs.getStatement().getConnection().close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list_NhanVien;
    }
}
