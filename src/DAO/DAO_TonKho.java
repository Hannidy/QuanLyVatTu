package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.TonKho;
import util.JDBCHelper;
import DAO.DAO_ChuyenDoiDonVi;

public class DAO_TonKho {
    
    // 1. Lấy danh sách tồn kho
   public List<TonKho> getAllTonKho() {
    List<TonKho> list = new ArrayList<>();
    String sql = "SELECT * FROM TONKHO";
    try {
        ResultSet rs = JDBCHelper.query(sql);
        while (rs.next()) {
            list.add(new TonKho(
                rs.getString("MaKho"),
                rs.getString("MaVatTu"),
                rs.getInt("SoLuong"),
                rs.getString("DonVi"),
                rs.getInt("TonToiThieu"),
                rs.getInt("TonToiDa"),
                rs.getString("ViTri")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

    
    // 2. Chuyển đổi đơn vị sử dụng ChuyenDoiDonViDAO
    public boolean chuyenDoiDonVi(String maKho, String maVatTu, String donViMoi, double heSoQuyDoi) {
        DAO_ChuyenDoiDonVi dao = new DAO_ChuyenDoiDonVi();
        return dao.chuyenDoiDonVi(maKho, maVatTu, donViMoi, heSoQuyDoi);
    }
}
