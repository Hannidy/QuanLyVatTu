package UI;

import DAO.DAO_TaiKhoan;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.model_TaiKhoan;
import util.Message;

public class pnTaiKhoan extends javax.swing.JPanel {

    public DefaultTableModel tbl_ModelTaiKhoan;
    private DAO_TaiKhoan tkdao = new DAO_TaiKhoan();
    private DefaultComboBoxModel cbo_ModelTimKiem;
    private DefaultComboBoxModel cbo_ModelChucVu;
    private List<model_TaiKhoan> list_TaiKhoan = new ArrayList<model_TaiKhoan>();
    
    private int row = -1;
    private boolean suDungTimKiem = false;
    
    public pnTaiKhoan() {
        initComponents();
        tbl_ModelTaiKhoan = (DefaultTableModel) tbl_TaiKhoan.getModel();
        
        cbo_TimKiem.setModel(new DefaultComboBoxModel<>(new String[]{
            "Mã Nhân Viên",
            "Mật Khẩu",
            "Tên Nhân Viên",
            "Email",
            "Mã Kho",
            "Tất Cả"
        }));
        
        cbo_ChucVu.setModel(new DefaultComboBoxModel<>(new String[]{
            "Nhân Viên",
            "Trưởng Phòng"
        }));
        
        iniSetEnabledButton();
        fillToTableTaiKhoan();
        styleUI();
    }

    public void styleUI() {
        //STYLE JTable:
        tbl_TaiKhoan.putClientProperty(FlatClientProperties.STYLE, ""
            + "showVerticalLines:true;"
            + "showHorizontalLines:true;"
            + "intercellSpacing:3,3;"
            + "selectionBackground:#A5D6A7;"
            + "selectionForeground:#000000;"
            + "gridColor:#BDBDBD;"
            + "border:1,1,1,1,#9E9E9E;"
            + "rowHeight:30;"
        );
        
        tbl_TaiKhoan.setSelectionBackground(new Color(165, 214, 167));
        tbl_TaiKhoan.setSelectionForeground(Color.BLACK);
        
        //STYLE JPanel:
        pn_chucnang.putClientProperty(FlatClientProperties.STYLE, ""
        + "background: #E0ECDE;"   // Màu nền xám nhạt (giống giao diện hiện đại)
        + "arc: 15;"); // Bo tròn góc 15px

        //STYLE JTextField:
        txt_TimKiem.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "innerFocusWidth:0;");
        
        //STYLE JButton:
        btn_Them.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
        btn_Sua.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
        btn_Xoa.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
        btn_TimKiem.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
        btn_LamMoi.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)


    }

    
    // Hiển thị danh sách vật tư lên bảng
    private void fillToTableTaiKhoan() {
        tbl_ModelTaiKhoan.setRowCount(0);
        try{
        list_TaiKhoan = tkdao.selectAll();
        for (model_TaiKhoan tk : list_TaiKhoan) {
            tbl_ModelTaiKhoan.addRow(new Object[]{
                tk.getMaNhanVien(),
                tk.getMatKhau(),
                tk.getSoLuong(),
                tk.getDonViTinh(),
                tk.getMaKho()
            });
        }
        }catch(Exception e){
            Message.alert("Lỗi truy vấn dữ liệu" + e.getMessage());
        }
    }

        public void setForm(model_TaiKhoan tk) {
            
        txt_TenTaiKhoan.setText(tk.getTenTaiKhoan());
        txt_SoLuong.setText(String.valueOf(tk.getSoLuong()));
        txt_DonViTinh.setText(tk.getDonViTinh());
        txt_MaKho.setText(tk.getMaKho());
        
    }
    
    // Click vào bảng để lấy dữ liệu
    public void clickHereTaiKhoan() {
        row = tbl_TaiKhoan.getSelectedRow();
        if (row > -1 && !suDungTimKiem) {
            String maTaiKhoan = (String) tbl_TaiKhoan.getValueAt(this.row, 0);
            list_TaiKhoan = tkdao.selectById(maTaiKhoan);
            this.setForm(list_TaiKhoan.get(0)); // Lấy phần tử đầu tiên thay vì ép kiểu
            lbl_DanhSach.setText(layTatCaThongTin());
            this.capNhatTrangThai();
        } else if(row > -1 && suDungTimKiem){
            String maTaiKhoan = (String) tbl_TaiKhoan.getValueAt(this.row, 0);
            list_TaiKhoan = tkdao.selectById(maTaiKhoan);
            this.setForm(list_TaiKhoan.get(0)); // Lấy phần tử đầu tiên thay vì ép kiểu
            lbl_DanhSach.setText(layThongTinTimKiem(list_TaiKhoan));
            this.capNhatTrangThai();
        }else{
            Message.alert("Vui lòng chọn một dòng trong bảng!");
        }
    }
    

    // Thêm vật tư mới
    private void addTaiKhoan() {
        if (txt_TenTaiKhoan.getText().isEmpty() ||
            txt_SoLuong.getText().isEmpty() ||
            txt_DonViTinh.getText().isEmpty() ||
            txt_MaKho.getText().isEmpty()) {
            Message.alert("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

            model_TaiKhoan tk = new model_TaiKhoan();
            tk.setTenTaiKhoan(txt_TenTaiKhoan.getText().trim());
            tk.setSoLuong(Integer.parseInt(txt_SoLuong.getText().trim())); // Sửa lỗi convert
            tk.setDonViTinh(txt_DonViTinh.getText().trim()); // Đã sửa lỗi gán sai
            tk.setMaKho(txt_MaKho.getText().trim());

            
            try {
                tkdao.insert(tk);
                fillToTableTaiKhoan();
                Message.alert("Thêm vật tư thành công!");
            } catch(Exception e) {
                Message.error("Lỗi: " + e.getMessage());
                Message.alert("Thêm vật tư thất bại!");
            }
    }

    // Xóa vật tư
    private void deleteTaiKhoan() {
        row = tbl_TaiKhoan.getSelectedRow();
        if (row < 0) {
            Message.alert("Bạn phải chọn dòng thông tin muốn xóa trên danh sách!");
            return;
        }

        String maTaiKhoan = tbl_TaiKhoan.getValueAt(row, 0).toString();
        boolean confirm = Message.confirm("Bạn có chắc chắn muốn xóa?");
        if (confirm) {
            try{
                tkdao.delete(maTaiKhoan);
                fillToTableTaiKhoan();
                Message.alert("Xóa vật tư thành công!");
            } catch(Exception e) {
                Message.error("Lỗi" + e.getMessage());
                Message.alert("Xóa thất bại!");
            }
        }
    }

    // Cập nhật vật tư
    private void updateTaiKhoan() {
        row = tbl_TaiKhoan.getSelectedRow();
        if (row < 0) {
            Message.alert("Bạn phải chọn một dòng thông tin muốn cập nhật trên danh sách!");
            return;
        }

        if (txt_TenTaiKhoan.getText().isEmpty() ||
            txt_SoLuong.getText().isEmpty() ||
            txt_DonViTinh.getText().isEmpty() ||
            txt_MaKho.getText().isEmpty()) {
            Message.alert("Vui lòng nhập đầy đủ thông tin!");
            return;
        }
            model_TaiKhoan tk = new model_TaiKhoan();
            tk.setMaTaiKhoan(tbl_TaiKhoan.getValueAt(row, 0).toString());
            tk.setTenTaiKhoan(txt_TenTaiKhoan.getText().trim());
            tk.setSoLuong(Integer.parseInt(txt_SoLuong.getText().trim()));
            tk.setDonViTinh(txt_DonViTinh.getText().trim());
            tk.setMaKho(txt_MaKho.getText().trim());

                boolean confirm = Message.confirm("Bạn có chắc chắn muốn cập nhật vật tư có mã '" + tk.getMaTaiKhoan() + "' ?");
                if (confirm) {
                    try{
                    tkdao.update(tk);
                    fillToTableTaiKhoan();
                    Message.alert("Cập nhật vật tư thành công!");
                } catch(Exception e) {
                    Message.error("Lỗi: " + e.getMessage());
                    Message.alert("Cập nhật vật tư thất bại!");
                }
                }else {
                    return;
                }
    }

    
    private void lamMoi(){
        fillToTableTaiKhoan();
        txt_TenTaiKhoan.setText("");
        txt_MaKho.setText("");
        txt_SoLuong.setText("");
        txt_TimKiem.setText("");
        txt_DonViTinh.setText("");
        row = -1;
        
        setEnabledButton();
        
    }
    

    private void setEnabledButton(){
        btn_Them.setEnabled(true);
        btn_Sua.setEnabled(true);
        btn_Xoa.setEnabled(true);
        btn_DauTien.setEnabled(true);
        btn_TruocDo.setEnabled(true);
        btn_TiepTheo.setEnabled(true);
        btn_CuoiCung.setEnabled(true);
        
    }

    private void iniSetEnabledButton(){
        btn_TruocDo.setEnabled(false);
    }
    
    
    void dauTien(){
        if (tbl_TaiKhoan.getRowCount() == 0) {
        Message.alert("Không có dữ liệu để hiển thị!");
        return;
        }

        row = 0; // Đặt chỉ mục dòng đầu tiên
        edit(); // Hiển thị thông tin lên form
        
    }
    
    void truocDo(){
        if (tbl_TaiKhoan.getRowCount() == 0) {
            Message.alert("Không có dữ liệu để hiển thị!");
            return;
        }else if(this.row == -1){
            Message.alert("Không xác định được thông tin để hiển thị!");
            return;   
        }
        
        
//        else if(this.row == 0){
//            Message.alert("Đây là thông tin đầu tiên trong danh sách, không thể lấy thông tin trước đó!");
//            return;
//        }
        else{
            this.row --;
            this.edit();
        }
    }
    
    void tiepTheo(){
        if (tbl_TaiKhoan.getRowCount() == 0) {
        Message.alert("Không có dữ liệu để hiển thị!");
        return;
        }
        else if(this.row == -1){
            dauTien();
            return;
//        }else if(this.row == tbl_TaiKhoan.getRowCount()-1){
//            Message.alert("Bạn đang ở cuối danh sách, không thể lấy thông tin tiếp theo !");
//            return;
//        }
        }else{
            this.row++;
            this.edit();
        }
       
    }
    
    void cuoiCung(){
        if (tbl_TaiKhoan.getRowCount() == 0) {
        Message.alert("Không có dữ liệu để hiển thị!");
        return;
        }
//        else if(this.row == tbl_TaiKhoan.getRowCount()-1){
//            Message.alert("Bạn đang ở cuối danh sách.");
//            return;
//        }
        else{
            this.row = tbl_TaiKhoan.getRowCount()-1;
            this.edit();
        }
        
    }

    void edit() {

        String maTaiKhoan = (String) tbl_TaiKhoan.getValueAt(this.row, 0);
        list_TaiKhoan = tkdao.selectById(maTaiKhoan);
        tbl_TaiKhoan.setRowSelectionInterval(row, row);
        this.setForm(list_TaiKhoan.get(0)); // Lấy phần tử đầu tiên thay vì ép kiểu
        lbl_DanhSach.setText(layTatCaThongTin());
        this.capNhatTrangThai();
    }
    
    public String layTatCaThongTin(){
           List<model_TaiKhoan> list_TaiKhoan = tkdao.selectAll();
            return "Thông Tin: " + (row + 1)+ " trên tổng số " + list_TaiKhoan.size();
    }
    
    public String layThongTinTimKiem(List<model_TaiKhoan> list_TaiKhoan){
            return "Thông Tin: " + (row + 1)+ " trên tổng số " + list_TaiKhoan.size();
    }
    
    private void capNhatTrangThai(){
        boolean chinhSua = (this.row >= 0);
        boolean dau = (this.row == 0);
        boolean cuoi = (this.row == tbl_TaiKhoan.getRowCount() - 1);
        // Trạng thái form
        btn_Them.setEnabled(!chinhSua);
        btn_Sua.setEnabled(chinhSua);
        btn_Xoa.setEnabled(chinhSua);
        
        // Trạng thái điều hướng
        btn_DauTien.setEnabled(chinhSua && !dau);
        btn_TruocDo.setEnabled(chinhSua && !dau);
        btn_TiepTheo.setEnabled(chinhSua && !cuoi);
        btn_CuoiCung.setEnabled(chinhSua && !cuoi);
    }
    
    private void timKiemTaiKhoan(){
        String cbo_Columns = String.valueOf(cbo_TimKiem.getSelectedItem());
        String txt_keyWord = txt_TimKiem.getText();
        
        if (txt_keyWord.isEmpty()) {
            Message.alert("Vui lòng nhập từ khóa để tìm kiếm!");
            return;
        }

        List<model_TaiKhoan> list_TaiKhoan = tkdao.selectByKeyWord(txt_keyWord, cbo_Columns);
        tbl_ModelTaiKhoan.setRowCount(0); // Xóa dữ liệu cũ
        if (list_TaiKhoan.isEmpty()) {
            Message.alert("Không tìm thấy vật tư!");
        } else {
            for (model_TaiKhoan tk : list_TaiKhoan) {
                tbl_ModelTaiKhoan.addRow(new Object[]{tk.getMaTaiKhoan(),
                                                tk.getTenTaiKhoan(),
                                                tk.getSoLuong(),
                                                tk.getDonViTinh(),
                                                tk.getMaKho()});
            }
            lbl_DanhSach.setText(layThongTinTimKiem(list_TaiKhoan));
        }
        suDungTimKiem = true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbl_TenVatTu = new javax.swing.JLabel();
        txt_MaNhanVien = new javax.swing.JTextField();
        btn_CuoiCung = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_TaiKhoan = new javax.swing.JTable();
        btn_TiepTheo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_TruocDo = new javax.swing.JButton();
        btn_DauTien = new javax.swing.JButton();
        lbl_DanhSach = new javax.swing.JLabel();
        lbl_MaKho = new javax.swing.JLabel();
        txt_TenNhanVien = new javax.swing.JTextField();
        pn_chucnang = new javax.swing.JPanel();
        btn_Them = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_LamMoi = new javax.swing.JButton();
        btn_TimKiem = new javax.swing.JButton();
        txt_TimKiem = new javax.swing.JTextField();
        cbo_TimKiem = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lbl_SoLuong = new javax.swing.JLabel();
        cbo_ChucVu = new javax.swing.JComboBox<>();
        txt_Email = new javax.swing.JTextField();
        lbl_MaKho1 = new javax.swing.JLabel();
        txt_SoDienThoai = new javax.swing.JTextField();
        lbl_MaKho2 = new javax.swing.JLabel();
        lbl_MaKho3 = new javax.swing.JLabel();
        rdo_HoatDong = new javax.swing.JRadioButton();
        rdo_KhongHoatDong = new javax.swing.JRadioButton();
        lbl_MaKho4 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(255, 255, 255));

        lbl_TenVatTu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_TenVatTu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_TenVatTu.setText("Mã Nhân Viên:");

        txt_MaNhanVien.setEditable(false);

        btn_CuoiCung.setText(">>|");
        btn_CuoiCung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CuoiCungActionPerformed(evt);
            }
        });

        tbl_TaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Chức Vụ", "Tên Nhân Viên", "Email", "Số Điện Thoại", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_TaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_TaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_TaiKhoan);

        btn_TiepTheo.setText(">");
        btn_TiepTheo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TiepTheoActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(191, 239, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tài Khoản");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btn_TruocDo.setText("<");
        btn_TruocDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TruocDoActionPerformed(evt);
            }
        });

        btn_DauTien.setText("|<<");
        btn_DauTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DauTienActionPerformed(evt);
            }
        });

        lbl_DanhSach.setForeground(new java.awt.Color(255, 51, 51));
        lbl_DanhSach.setText("Thông Tin: 0 trên tổng số 0");

        lbl_MaKho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_MaKho.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_MaKho.setText("Tên Nhân Viên:");

        pn_chucnang.setBackground(new java.awt.Color(255, 255, 255));

        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_LamMoi.setText("Làm mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_TimKiem.setText("Tìm Kiếm");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        cbo_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_TimKiemActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Lựa Chọn Tìm Kiếm Theo:");

        javax.swing.GroupLayout pn_chucnangLayout = new javax.swing.GroupLayout(pn_chucnang);
        pn_chucnang.setLayout(pn_chucnangLayout);
        pn_chucnangLayout.setHorizontalGroup(
            pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_chucnangLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_chucnangLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbo_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_chucnangLayout.setVerticalGroup(
            pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_chucnangLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(2, 2, 2)
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        lbl_SoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_SoLuong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_SoLuong.setText("Chức Vụ:");

        cbo_ChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbl_MaKho1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_MaKho1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_MaKho1.setText("Email:");

        lbl_MaKho2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_MaKho2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_MaKho2.setText("Số Điện Thoại:");

        lbl_MaKho3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_MaKho3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_MaKho3.setText("Trạng Thái:");

        buttonGroup1.add(rdo_HoatDong);
        rdo_HoatDong.setText("Hoạt Động");
        rdo_HoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_HoatDongActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_KhongHoatDong);
        rdo_KhongHoatDong.setText("Không Hoạt Động");
        rdo_KhongHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_KhongHoatDongActionPerformed(evt);
            }
        });

        lbl_MaKho4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_MaKho4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_MaKho4.setText("Mật Khẩu:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pn_chucnang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(28, 34, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_DauTien, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_TruocDo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(btn_TiepTheo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_CuoiCung))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbl_DanhSach)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(rdo_HoatDong)
                                            .addGap(31, 31, 31)
                                            .addComponent(rdo_KhongHoatDong)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_MaKho3)
                                    .addComponent(lbl_MaKho2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_SoDienThoai))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_MaKho)
                                    .addComponent(lbl_MaKho1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_Email)
                                    .addComponent(txt_TenNhanVien)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(lbl_SoLuong))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbl_MaKho4)
                                        .addComponent(lbl_TenVatTu)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_MaNhanVien)
                                    .addComponent(cbo_ChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPasswordField1))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_chucnang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_TenVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_MaKho4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jPasswordField1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_MaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_MaKho1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_MaKho2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_MaKho3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdo_KhongHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdo_HoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(lbl_DanhSach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_CuoiCung, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_TiepTheo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_TruocDo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_DauTien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CuoiCungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CuoiCungActionPerformed
        cuoiCung();
    }//GEN-LAST:event_btn_CuoiCungActionPerformed

    private void tbl_TaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_TaiKhoanMouseClicked
        clickHereTaiKhoan();
    }//GEN-LAST:event_tbl_TaiKhoanMouseClicked

    private void btn_TiepTheoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TiepTheoActionPerformed
        tiepTheo();
    }//GEN-LAST:event_btn_TiepTheoActionPerformed

    private void btn_TruocDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TruocDoActionPerformed
        truocDo();
    }//GEN-LAST:event_btn_TruocDoActionPerformed

    private void btn_DauTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DauTienActionPerformed
        dauTien();
    }//GEN-LAST:event_btn_DauTienActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        addTaiKhoan();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        deleteTaiKhoan();
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        updateTaiKhoan();
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        lamMoi();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        timKiemTaiKhoan();
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void cbo_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_TimKiemActionPerformed

    }//GEN-LAST:event_cbo_TimKiemActionPerformed

    private void rdo_HoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_HoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_HoatDongActionPerformed

    private void rdo_KhongHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_KhongHoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_KhongHoatDongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CuoiCung;
    private javax.swing.JButton btn_DauTien;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_TiepTheo;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_TruocDo;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbo_ChucVu;
    private javax.swing.JComboBox<String> cbo_TimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_DanhSach;
    private javax.swing.JLabel lbl_MaKho;
    private javax.swing.JLabel lbl_MaKho1;
    private javax.swing.JLabel lbl_MaKho2;
    private javax.swing.JLabel lbl_MaKho3;
    private javax.swing.JLabel lbl_MaKho4;
    private javax.swing.JLabel lbl_SoLuong;
    private javax.swing.JLabel lbl_TenVatTu;
    private javax.swing.JPanel pn_chucnang;
    private javax.swing.JRadioButton rdo_HoatDong;
    private javax.swing.JRadioButton rdo_KhongHoatDong;
    private javax.swing.JTable tbl_TaiKhoan;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_MaNhanVien;
    private javax.swing.JTextField txt_SoDienThoai;
    private javax.swing.JTextField txt_TenNhanVien;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
