
package Login;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptionDecryption {
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String ALGORITHM = "AES";

    public void prepareSecreteKey(String myKey) {
    MessageDigest sha = null; // Khai báo biến sha để sử dụng thuật toán băm (hashing)
    try {
        // Chuyển đổi chuỗi khóa đầu vào (myKey) thành mảng byte theo chuẩn UTF-8
        key = myKey.getBytes(StandardCharsets.UTF_8);
        // Khởi tạo thuật toán băm SHA-1 để tạo khóa bảo mật
        sha = MessageDigest.getInstance("SHA-1");
        // Băm dữ liệu của khóa (key), tạo ra một mã băm SHA-1 dài 20 byte
        key = sha.digest(key);
        // Cắt bỏ bớt dữ liệu, chỉ lấy 16 byte đầu tiên để làm khóa
        key = Arrays.copyOf(key, 16);
        // Tạo khóa bí mật (SecretKey) từ mảng byte đã cắt, sử dụng thuật toán mã hóa đã định nghĩa (ALGORITHM)
        secretKey = new SecretKeySpec(key, ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
        // Xử lý ngoại lệ nếu thuật toán SHA-1 không được hỗ trợ trên hệ thống
        e.printStackTrace();
    }
}


    public String encrypt(String strToEncrypt, String secret) {
    try {
        // Chuẩn bị khóa bí mật từ chuỗi "secret" (hàm này tạo khóa 16-byte dựa trên SHA-1)
        prepareSecreteKey(secret);
        // Khởi tạo một đối tượng Cipher để thực hiện mã hóa theo thuật toán đã định nghĩa (ALGORITHM)
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // Cấu hình Cipher ở chế độ mã hóa (ENCRYPT_MODE) với khóa bí mật (secretKey)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // Mã hóa chuỗi đầu vào (strToEncrypt), chuyển đổi thành dạng Base64 để dễ lưu trữ và truyền tải
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    } catch (Exception e) {
        // Nếu có lỗi xảy ra, in ra thông báo lỗi
        System.out.println("Error while encrypting: " + e.toString());
    }
    // Trả về null nếu có lỗi xảy ra
    return null;
}


    public String decrypt(String strToDecrypt, String secret) {
    try {
        // Chuẩn bị khóa bí mật từ chuỗi "secret" 
        // (Hàm này tạo khóa 16-byte dựa trên SHA-1, sau đó lưu vào biến `secretKey`)
        prepareSecreteKey(secret);
        // Khởi tạo một đối tượng Cipher để thực hiện giải mã theo thuật toán đã định nghĩa (ALGORITHM)
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // Cấu hình Cipher ở chế độ giải mã (DECRYPT_MODE) với khóa bí mật (secretKey)
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // Giải mã chuỗi đã được mã hóa:
        // 1. Giải mã Base64 để lấy lại dữ liệu dạng byte gốc
        // 2. Dùng Cipher để giải mã dữ liệu
        // 3. Chuyển dữ liệu giải mã từ byte sang chuỗi String
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    } catch (Exception e) {
        // Nếu có lỗi xảy ra trong quá trình giải mã, in thông báo lỗi ra console
        System.out.println("Error while decrypting: " + e.toString());
    }
    // Trả về null nếu quá trình giải mã thất bại
    return null;
}


    public static void main(String[] args) {
        final String secretKey = "thekeyhere";

        String originalString = "jdbc:sqlserver://localhost:1433;databaseName=QLVT;trustServerCertificate=true";

        AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
        String encryptedString = aesEncryptionDecryption.encrypt(originalString, secretKey);
        String decryptedString = aesEncryptionDecryption.decrypt(encryptedString, secretKey);

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
        System.out.println(aesEncryptionDecryption.encrypt("sa", secretKey));
        System.out.println(aesEncryptionDecryption.encrypt("123", secretKey));
        System.out.println(aesEncryptionDecryption.encrypt("jdbc:sqlserver://localhost:1433;databaseName=QLVT;trustServerCertificate=true", secretKey));
    }
}