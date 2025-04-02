package util;

import java.sql.*;
import Login.AESEncryptionDecryption;

public class JDBCHelper {
    private static final String url = "trNiqDu94jZXBQhVpKVo203KTTAwP6HDzKmEGGyWvkwnxRrbfkWGhn7gZzwDax5UYEosbYMhIm3mH719b0L8CtHLOebybeVAY5jd58uAdB0=";
    private static final String user = "Li1fiqKPnyDyawrodO+Hlg==";
    private static final String password = "virZuIfAWfsrUC2/hRC2PA==";
    private static final String secretKey = "thekeyhere";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không thể tải driver SQL Server", e);
        }
    }

    // ✅ Lấy kết nối (Giải mã chuỗi kết nối)
    public static Connection connection() throws SQLException {
        AESEncryptionDecryption aes = new AESEncryptionDecryption();
        String decryptedJdbcUrl = aes.decrypt(url, secretKey);
        String decryptedUser = aes.decrypt(user, secretKey);
        String decryptedPassword = aes.decrypt(password.trim(), secretKey);
        return DriverManager.getConnection(decryptedJdbcUrl, decryptedUser, decryptedPassword);
    }

    // ✅ Tạo PreparedStatement (Sửa lỗi để dùng Connection từ bên ngoài)
    private static PreparedStatement getStmt(Connection conn, String sql, Object... args) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }

    // ✅ Thực hiện INSERT, UPDATE, DELETE (Đóng kết nối đúng cách)
    public static int update(String sql, Object... args)throws SQLException {
        try (Connection conn = connection();
             PreparedStatement stmt = getStmt(conn, sql, args)) {
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật dữ liệu", e);
        }
    }

    // ✅ Thực hiện SELECT (Người gọi phải tự đóng Connection)
    public static ResultSet query(String sql, Object... args) throws SQLException {
        Connection conn = connection();
        PreparedStatement stmt = getStmt(conn, sql, args);
        return stmt.executeQuery(); // Người gọi phải đóng `ResultSet` & `Connection`
    }
    
    public static Object querySingleValue(String sql, Object... args) throws SQLException {
    try (ResultSet rs = query(sql, args)) { // Gọi query() có sẵn
        if (rs.next()) {
            return rs.getObject(1); // Lấy giá trị cột đầu tiên
        }
    }
    return null; // Trả về null nếu không có kết quả
}


    // ✅ Lấy một giá trị duy nhất (Ví dụ: COUNT(*))
    public static Object value(String sql, Object... args) {
        try (Connection conn = connection();
             PreparedStatement stmt = getStmt(conn, sql, args);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getObject(1) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
