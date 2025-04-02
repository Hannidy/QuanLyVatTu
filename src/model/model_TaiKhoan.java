package model;

public class model_TaiKhoan {
    private String maNhanVien;
    private String matKhau;
    private String chucVu;
    private String tenNhanVien;
    private String email;
    private String soDienThoai;
    private String hinhAnh;
    private boolean trangThai;

    public model_TaiKhoan() {
    }

    public model_TaiKhoan(String maNhanVien, String matKhau, String chucVu, String tenNhanVien, String email, String soDienThoai, String hinhAnh, boolean trangThai) {
        this.maNhanVien = maNhanVien;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
        this.tenNhanVien = tenNhanVien;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    

    

    
    
    
    
}
