package DAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.model_NhaCungCap;
import model.model_PhieuNhap;
import util.JDBCHelper;

public class DAO_PhieuNhap extends DAO_HeThongQuanLyVatTu<model_PhieuNhap, String>{
    private String generateMaPhieuNhap() {
        String prefix = "PN";
        String sql = "SELECT MAX(CAST(SUBSTRING(MaPhieuNhap, 3, LEN(MaPhieuNhap) - 2) AS INT)) FROM PHIEUNHAP";
        try {
            Object result = JDBCHelper.querySingleValue(sql);
            Integer maxId = (result != null) ? Integer.parseInt(result.toString()) : 0;

            if (maxId == null) {
                maxId = 0;
            }
            return prefix + (maxId + 1);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_PhieuNhap.class.getName()).log(Level.SEVERE, "Lỗi khi tạo mã phiếu nhập", ex);
            return null;
        }
    }
    
    public void insert(model_PhieuNhap pn) {
        String newMaPhieuNhap = generateMaPhieuNhap();
        if (newMaPhieuNhap == null) return;
        
        String sql = "INSERT INTO PHIEUNHAP (MaPhieuNhap, NgayNhap, MaKho, MaNhaCungCap, TrangThai) VALUES (?, ?, ?, ?, ?)";
        try {
            String ngayNhapSQL = new SimpleDateFormat("yyyy-MM-dd").format(pn.getNgayNhap());
            JDBCHelper.update(sql,
                    newMaPhieuNhap,
                    ngayNhapSQL,
                    pn.getMaKho(),
                    pn.getMaNhaCungCap(),
                    "Chờ duyệt" // Giá trị mặc định
            );
        } catch (SQLException ex) {
            Logger.getLogger(DAO_PhieuNhap.class.getName()).log(Level.SEVERE, "Lỗi khi thêm phiếu nhập", ex);
        }
    }
    
    public void update(model_PhieuNhap pn) {
        String sql = "UPDATE PHIEUNHAP SET NgayNhap = ?, MaKho = ?, MaNhaCungCap = ?, TrangThai = ? WHERE MaPhieuNhap = ?";
        try {
            String ngayNhapSQL = new SimpleDateFormat("yyyy-MM-dd").format(pn.getNgayNhap());
            JDBCHelper.update(sql,
                    ngayNhapSQL,
                    pn.getMaKho(),
                    pn.getMaNhaCungCap(),
                    pn.getTrangThai(),
                    pn.getMaPhieuNhap()
            );
        } catch (SQLException ex) {
            Logger.getLogger(DAO_PhieuNhap.class.getName()).log(Level.SEVERE, "Lỗi khi cập nhật phiếu nhập", ex);
        }
    }
    


//    public boolean delete(String maPhieuNhap) {
//           String sql = "DELETE FROM PHIEUNHAP WHERE MaPhieuNhap = ?";
//        try {
//            JDBCHelper.update(sql,maPhieuNhap);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAO_PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            int rowsAffected = JDBCHelper.update(sql, maPhieuNhap);
//            return rowsAffected > 0; // Trả về true nếu xóa thành công
//        } catch (SQLException ex) {
//            Logger.getLogger(DAO_PhieuNhap.class.getName()).log(Level.SEVERE, "Lỗi khi xóa phiếu nhập", ex);
//            return false;
//        }
//    }

    public String selectMaxId() {
        String sql = "SELECT MAX(CAST(SUBSTRING(MaPhieuNhap, 3, LEN(MaPhieuNhap)-2) AS INT)) FROM PHIEUNHAP";
        String newMaPhieuNhap = "PN1";// Mặc định nếu bảng rỗng.
        try{
        java.sql.ResultSet rs = JDBCHelper.query(sql);;
            if (rs == null) {
            System.out.println("⚠ Không thể lấy dữ liệu: ResultSet trả về null.");
            return newMaPhieuNhap;
            }
            if(rs.next() && rs.getObject(1) != null){
                int maxMaPhieuNhap = rs.getInt(1);
                newMaPhieuNhap = "VT" + (maxMaPhieuNhap + 1); // Tạo mã phiếu nhập tiếp theo
            }
            if(rs != null && rs.getObject(1) != null){
                rs.getStatement().getConnection().close();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newMaPhieuNhap;
    }

    public List<model_PhieuNhap> selectById(String maPhieuNhap) {
        String sql = "SELECT * FROM PHIEUNHAP WHERE MaPhieuNhap = ?";
        return this.selectBySQL(sql, maPhieuNhap);
    }
    
        public List<model_PhieuNhap>selectByKeyWord(String keyWord, String columnKho){
        String sql1 = "SELECT * FROM NHACUNGCAP WHERE MaNhaCungCao LIKE ?";
        String sql2 = "SELECT * FROM NHACUNGCAP WHERE TenNhaCungCap LIKE ?";
        String sql3 = "SELECT * FROM NHACUNGCAP WHERE Email LIKE ?";
        String sql4 = "SELECT * FROM NHACUNGCAP WHERE DiaChi LIKE ?";
        
        String keyWord_2 = "%" + keyWord + "%";
//        if(){
        return this.selectBySQL(sql1, keyWord, keyWord_2);
//        }
    }

    public List<model_PhieuNhap> selectAll() {
        String sql = "SELECT * FROM PHIEUNHAP";
        return this.selectBySQL(sql);
    }

    protected List<model_PhieuNhap> selectBySQL(String sql, Object... args) {
        List<model_PhieuNhap> list_PhieuNhap = new ArrayList<model_PhieuNhap>();
        try{
            java.sql.ResultSet rs = null;
            
            try{
                rs = JDBCHelper.query(sql, args);
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                
                while(rs.next()){
                model_PhieuNhap pn = new model_PhieuNhap();
                pn.setMaPhieuNhap(rs.getString("MaPhieuNhap"));
                
                Date ngayNhapSQL = rs.getDate("NgayNhap");
                if(ngayNhapSQL != null){
                    pn.setNgayNhap(sdf.parse(sdf.format(ngayNhapSQL)));
                }
                pn.setMaKho(rs.getString("MaKho"));
                pn.setMaNhaCungCap(rs.getString("MaNhaCungCap"));
                
                list_PhieuNhap.add(pn);
                }
            
            } catch (ParseException ex) {
                Logger.getLogger(DAO_PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                rs.getStatement().getConnection().close();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list_PhieuNhap;
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
