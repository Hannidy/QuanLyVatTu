package DAO;

import model.model_PhongBan;
import util.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_PhongBan extends DAO_HeThongQuanLyVatTu<model_PhongBan, String>{

    @Override
    public void insert(model_PhongBan pb) {
        String sql = "INSERT INTO PHONGBAN (MaPhongBan, TenPhongBan, DiaChi, MaTruongPhong) VALUES (?, ?, ?, ?)";
        String newMaPhongBan = this.selectMaxId();
        JDBCHelper.update(sql,
                newMaPhongBan,
                pb.getTenPhongBan(),
                pb.getDiaChi());
                pb.getMatruongPhong();
    }

    @Override
    public void update(model_PhongBan pb) {
        String sql = "UPDATE PHONGBAN SET TenPhongBan = ?, DiaChi = ?, MaTruongPhong = ? WHERE MaPhongBan = ?";
        JDBCHelper.update(sql, 
                pb.getTenPhongBan(),
                pb.getDiaChi(),
                pb.getMatruongPhong(),
                pb.getMaPhongBan());

    }

    @Override
    public void delete(String maPhongBan) {
        String sql = "DELETE FROM PHONGBAN WHERE MaPhongBan = ?";
        JDBCHelper.update(sql, maPhongBan);
    }

    @Override
    public String selectMaxId() {
        String sql = "SELECT MAX(CAST(SUBSTRING(MaPhongBan, 3, LEN(MaPhongBan)-2) AS INT)) FROM PHONGBAN";
        String newMaPhongBan = "PB1";// Mặc định nếu bảng rỗng.
        try {
            ResultSet rs = JDBCHelper.query(sql);;
            if (rs == null) {
                System.out.println("⚠ Không thể lấy dữ liệu: ResultSet trả về null.");
                return newMaPhongBan;
            }
            if (rs.next() && rs.getObject(1) != null) {
                int maxMaPhongBan = rs.getInt(1);
                newMaPhongBan = "PB" + (maxMaPhongBan + 1); // Tạo mã phòng ban tiếp theo
            }
            if (rs != null && rs.getObject(1) != null) {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newMaPhongBan;
    }

    @Override
    public List<model_PhongBan> selectById(String maPhongBan) {
        String sql = "SELECT * FROM PHONGBAN WHERE MaPhongBan = ?";
        return this.selectBySQL(sql, maPhongBan);
    }

    @Override
        public List<model_PhongBan>selectByKeyWord(String keyWord, String columnPhongBan){
        String sql1 = "SELECT * FROM PHONGBAN WHERE MaPhongBan LIKE ?";
        String sql2 = "SELECT * FROM PHONGBAN WHERE TenPhongBan LIKE ?";
        String sql3 = "SELECT * FROM PHONGBAN WHERE DiaChi LIKE ?";
        String sql4 = "SELECT * FROM PHONGBAN WHERE MaTruongPhong LIKE ?";
        
        String keyWord_2 = "%" + keyWord + "%";
//        if(){
        return this.selectBySQL(sql1, keyWord, keyWord_2);
//        }
    }
    
    @Override
    public List<model_PhongBan> selectAll() {
        String sql = "SELECt * FROM PHONGBAN";
        return this.selectBySQL(sql);
    }

    protected List<model_PhongBan> selectBySQL(String sql, Object... args) {
        List<model_PhongBan> list_PhongBan = new ArrayList<model_PhongBan>();
        try {
            ResultSet rs = null;

            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    model_PhongBan pb = new model_PhongBan();
                    pb.setMaPhongBan(rs.getString("MaPhongBan"));
                    pb.setTenPhongBan(rs.getString("TenPhongBan"));
                    pb.setDiaChi(rs.getString("DiaChi"));
                    pb.setMatruongPhong(rs.getString("MaTruongPhong"));
                    list_PhongBan.add(pb);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list_PhongBan;
    }
}
