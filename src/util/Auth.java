package util;

import model.model_TaiKhoan;

public class Auth {
    /**
     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */
    public static model_TaiKhoan user = null;
    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void logOut() {
        Auth.user = null;
    }
    /**
     * Kiểm tra xem đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return Auth.user != null;
    }
    
     /**
     * Kiểm tra xem có phải là trưởng phòng hay không
     */
    public static String chucVu() {
        return Auth.isLogin() && user.getChucVu();
    }
    
    public static String maNhanVien = null; // Lưu mã nhân viên đang đăng nhập
    public static String chucVu = null; // Lưu chức vụ của nhân viên

    public static boolean isAdmin() {
        return "Admin".equalsIgnoreCase(chucVu);
    }

    public static boolean isManager() {
        return "Trưởng Phòng".equalsIgnoreCase(chucVu);
    }

    public static boolean isEmployee() {
        return "Nhân Viên".equalsIgnoreCase(chucVu);
    }
    
}
