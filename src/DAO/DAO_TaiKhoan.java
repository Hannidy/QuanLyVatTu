package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.model_TaiKhoan;
import model.model_VatTu;
import util.JDBCHelper;

public class DAO_TaiKhoan extends DAO_HeThongQuanLyVatTu<model_TaiKhoan, String>{

    @Override
    public void insert(model_TaiKhoan tk) {
        String sql = "INSERT INTO TAIKHOAN (MaNhanVien, MatKhau, ChucVu, TenNhanvien, Email, SoDienThoai, HinhAnh, TrangThai) VALUES (?, ?, ?, ?, ?)";
        String newMaNhanVien = this.selectMaxId();
        JDBCHelper.update(sql,
                newMaNhanVien,
                tk.getMatKhau(),
                tk.getChucVu(),
                tk.getTenNhanVien(),
                tk.getEmail(),
                tk.getSoDienThoai(),
                tk.getHinhAnh(),
                tk.getTrangThai());
    }

    @Override
    public void update(model_TaiKhoan tk) {
        String sql = "UPDATE TAIKHOAN SET MatKhau = ?, ChucVu = ?, TenNhanVien = ?, Email = ?, SoDienThoai = ?, HinhAnh = ?, TrangThai = ? WHERE MaNhanVien = ?";
        JDBCHelper.update(sql, tk.getMatKhau(),
                               tk.getChucVu(),
                               tk.getTenNhanVien(),
                               tk.getEmail(),
                               tk.getSoDienThoai(),
                               tk.getHinhAnh(),
                               tk.getMaNhanVien());
    }

    @Override
    public void delete(String maNhanVien) {
        String sql = "DELETE FROM TAIKHOAN WHERE MaNhanVien = ?";
        JDBCHelper.update(sql, maNhanVien);
    }

    @Override
    public String selectMaxId() {
        String sql = "SELECT MAX(CAST(SUBSTRING(MaNhanVien, 3, LEN(MaNhanVien)-2) AS INT)) FROM TAIKHOAN";
        String newMaNhanVien = "NV1";// Mặc định nếu bảng rỗng.
        try{
        java.sql.ResultSet rs = JDBCHelper.query(sql);
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

    @Override
    public List<model_TaiKhoan> selectById(String maNhanVien) {
        String sql = "SELECT * FROM TAIKHOAN WHERE MaNhanVien = ?";
        return selectBySQL(sql, maNhanVien);
    }

    @Override
    public List<model_TaiKhoan> selectByKeyWord(String keyWord, String columnTaiKhoan) {
        String sql1 = "SELECT * FROM TAIKHOAN WHERE MaNhanVien LIKE ?";
        String sql2 = "SELECT * FROM TAIKHOAN WHERE MatKhau LIKE ?";
        String sql3 = "SELECT * FROM TAIKHOAN WHERE ChucVu LIKE ?";
        String sql4 = "SELECT * FROM TAIKHOAN WHERE TenNhanVien LIKE ?";
        String sql5 = "SELECT * FROM TAIKHOAN WHERE Email LIKE ?";
        String sql6 = "SELECT * FROM TAIKHOAN WHERE"
                + " MaNhanVien LIKE ? OR "
                + " MatKhau LIKE ? OR "
                + " ChucVu LIKE ? OR "
                + " TenNhanVien LIKE ? OR "
                + " Email LIKE ? OR "
                + " SoDienThoai LIKE ? OR "
                + " HinhAnh LIKE ? OR "
                + " TrangThai"
        
        String keyWord_2 = "%" + keyWord + "%";
        if(columnTaiKhoan.equals("Mã Vật Tư")){
            return this.selectBySQL(sql1, keyWord_2);
        }else if(columnTaiKhoan.equals("Tên Vật Tư")){
            return this.selectBySQL(sql2, keyWord_2);
        }else if(columnTaiKhoan.equals("Số Lượng")){
            return this.selectBySQL(sql3, keyWord_2);
        }else if(columnTaiKhoan.equals("Đơn Vụ Tính")){
            return this.selectBySQL(sql4, keyWord_2);
        }else if(keyWord.equals("Mã Kho")){
            return this.selectBySQL(sql5, keyWord_2);
        }else{
            return this.selectBySQL(sql6, keyWord_2, keyWord_2, keyWord_2, keyWord_2, keyWord_2);
        }
    }

    @Override
    public List<model_TaiKhoan> selectAll() {
        String sql = "SELECt * FROM TAIKHOAN";
        return this.selectBySQL(sql);
    }

    @Override
    protected List<model_TaiKhoan> selectBySQL(String sql, Object... args) {
        List<model_VatTu> list_VatTu = new ArrayList<model_VatTu>();
        try{
            java.sql.ResultSet rs = null;
            
            try{
                rs = JDBCHelper.query(sql, args);
                while(rs.next()){
                model_VatTu tk = new model_VatTu();
                tk.setMaNhanVien(rs.getString("MaNhanVien"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setChucVu(rs.getInt("ChucVu"));
                tk.setTenNhanVien(rs.getString("TenNhanVien"));
                tk.setEmail(rs.getString("Email"));
                
                list_VatTu.add(tk);
                }
            }finally{
                rs.getStatement().getConnection().close();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list_VatTu;
    }
    }
    
}
