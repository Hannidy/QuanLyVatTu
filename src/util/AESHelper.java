
package util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

// Lớp hỗ trợ mã hóa và giải mã AES  
public class AESHelper {  
    // Khóa bí mật AES (16 byte - 128 bit)  
    private static final String secretKey = "thekeyhere123456"; // Đảm bảo đủ 16 byte  

    /**  
     * Phương thức mã hóa chuỗi bằng thuật toán AES  
     * @param text Chuỗi cần mã hóa  
     * @return Chuỗi đã mã hóa dưới dạng Base64  
     * @throws Exception Nếu có lỗi xảy ra trong quá trình mã hóa  
     */  
    public static String encryptAES(String text) throws Exception {  
        // Chuyển khóa bí mật thành dạng byte  
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);  
        // Tạo khóa AES từ keyBytes  
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");  

        // Khởi tạo đối tượng Cipher với thuật toán AES/ECB/PKCS5Padding  
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
        // Thiết lập chế độ mã hóa với khóa AES  
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);  

        // Mã hóa chuỗi đầu vào  
        byte[] encryptedBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));  
        // Chuyển đổi sang chuỗi Base64 để dễ lưu trữ  
        return Base64.getEncoder().encodeToString(encryptedBytes);  
    }  

    /**  
     * Phương thức giải mã chuỗi đã mã hóa bằng thuật toán AES  
     * @param encryptedText Chuỗi đã được mã hóa (Base64)  
     * @return Chuỗi gốc sau khi giải mã  
     * @throws Exception Nếu có lỗi xảy ra trong quá trình giải mã  
     */  
    public static String decryptAES(String encryptedText) throws Exception {  
        // Chuyển khóa bí mật thành dạng byte  
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);  
        // Tạo khóa AES từ keyBytes  
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");  

        // Khởi tạo đối tượng Cipher với thuật toán AES/ECB/PKCS5Padding  
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
        // Thiết lập chế độ giải mã với khóa AES  
        cipher.init(Cipher.DECRYPT_MODE, keySpec);  

        // Giải mã dữ liệu từ Base64  
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);  
        // Trả về chuỗi gốc sau khi giải mã  
        return new String(cipher.doFinal(decodedBytes), StandardCharsets.UTF_8);  
    }  

    /**  
     * Hàm main để kiểm tra mã hóa & giải mã AES  
     */  
    public static void main(String[] args) {  
        try {  
            // Dữ liệu cần mã hóa (thông tin kết nối database)  
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QLVT;trustServerCertificate=true";  
            String user = "sa";  
            String password = "123";  

            // Mã hóa dữ liệu  
            String encryptedUrl = encryptAES(url);  
            String encryptedUser = encryptAES(user);  
            String encryptedPassword = encryptAES(password);  

            // In ra chuỗi đã mã hóa  
            System.out.println("Encrypted URL: " + encryptedUrl);  
            System.out.println("Encrypted User: " + encryptedUser);  
            System.out.println("Encrypted Password: " + encryptedPassword);  

            // Giải mã dữ liệu và in ra kết quả  
            System.out.println("Decrypted URL: " + decryptAES(encryptedUrl));  
            System.out.println("Decrypted User: " + decryptAES(encryptedUser));  
            System.out.println("Decrypted Password: " + decryptAES(encryptedPassword));  

        } catch (Exception e) {  
            e.printStackTrace(); // In ra lỗi nếu có  
        }  
    }  
}  