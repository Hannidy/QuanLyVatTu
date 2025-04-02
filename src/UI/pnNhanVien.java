package UI;

import DAO.DAO_NhanVien;
import com.formdev.flatlaf.FlatClientProperties;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.model_NhanVien;
import util.Message;

public class pnNhanVien extends javax.swing.JPanel {

    private DefaultTableModel tbl_ModelNhanVien;
    private DAO_NhanVien nvdao = new DAO_NhanVien();

    public pnNhanVien() {
        initComponents();
        tbl_ModelNhanVien = (DefaultTableModel) tbl_NhanVien.getModel();
        fillToTableNhanVien();
        txtcode();
        styleTable();

    }

    void txtcode() {
        txt_TimKiem.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "innerFocusWidth:0;");
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
//         btn_LamMoi.putClientProperty(FlatClientProperties.STYLE, ""
//                + "arc:999;" // Bo tròn nút
//                + "borderWidth:0;" // Không viền
//                + "focusWidth:1;" // Không viền khi focus
//                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
//                + "foreground:#000000;" // Màu chữ mặc định (đen)
//                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
//                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
//                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
//                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
         btn_notification.putClientProperty(FlatClientProperties.STYLE, ""
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

    public void styleTable() {
        tbl_NhanVien.putClientProperty(FlatClientProperties.STYLE, ""
                + "showVerticalLines:true;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:3,3;"
                + "selectionBackground:#A5D6A7;"
                + "selectionForeground:#000000;"
                + "gridColor:#BDBDBD;"
                + "border:1,1,1,1,#9E9E9E;"
                + "rowHeight:30;"
        );
        pn_chucnang.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: #FFFFFF;" // Màu nền xám nhạt (giống giao diện hiện đại)
                + "arc: 15;"); // Bo tròn góc 15px

    }


// Hiển thị danh sách nhân viên lên bảng
private void fillToTableNhanVien() {
    tbl_ModelNhanVien.setRowCount(0); // Xóa dữ liệu cũ trong bảng
    try {
        // Lấy danh sách nhân viên từ DAO
        List<model_NhanVien> list_NhanVien = nvdao.selectAll();
        
        // Duyệt qua từng nhân viên trong danh sách
        for (model_NhanVien nv : list_NhanVien) {
            // Sử dụng đúng phương thức isVaiTro() và isTrangThai()
            tbl_ModelNhanVien.addRow(new Object[]{
                nv.getMaNhanVien(), // Mã nhân viên
                nv.getTenNhanVien(), // Tên nhân viên
                nv.getEmail(),       // Email
                nv.getSoDienThoai(), // Số điện thoại
                nv.isVaiTro() ? "Trưởng Phòng" : "Nhân Viên", // Vai trò
                nv.isTrangThai() ? "Vô Hiệu Hóa" : "Hoạt Động" // Trạng thái
            });
        }
    } catch (Exception e) {
        // Hiển thị thông báo lỗi nếu có sự cố
        Message.alert("Lỗi truy vấn dữ liệu: " + e.getMessage());
    }
}



    // Thêm nhân viên mới
    private void addNhanVien() {
        if (txt_MatKhau.getText().isEmpty() ||
            txt_TenNhanVien.getText().isEmpty() ||
            txt_Email.getText().isEmpty()) {
            Message.alert("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

            model_NhanVien nv = new model_NhanVien();
            nv.setMatKhau(txt_MatKhau.getText().trim());
            nv.setTenNhanVien(txt_TenNhanVien.getText().trim());
            nv.setEmail(txt_Email.getText().trim());
            if (rdo_TruongPhong.isSelected()) {
                nv.setVaiTro(true);
            } else {
                nv.setVaiTro(false);     
            }
            if(rdo_VoHieuHoa.isSelected()){
                nv.setTrangThai(true);
            }else{
                nv.setTrangThai(false); 
            }

            try {
                nvdao.insert(nv);
                fillToTableNhanVien();
                Message.alert("Thêm nhân viên thành công!");

                }catch (Exception e) {
                Message.error("Lỗi: " + e.getMessage());
                Message.alert("Thêm nhân viên thất bại!");
                
        }
    }
            // Xóa vật tư
    private void deleteNhanVien() {
        int row = tbl_NhanVien.getSelectedRow();
        if (row < 0) {
            Message.alert("Bạn phải chọn một dòng để xóa!");
            return;
        }

        String maNhanVien = tbl_NhanVien.getValueAt(row, 0).toString();
        boolean confirm = Message.confirm("Bạn có chắc chắn muốn xóa?");
        if (confirm) {
            try{
                nvdao.delete(maNhanVien);
                fillToTableNhanVien();
                Message.alert("Xóa nhân viên thành công!");
            }catch (Exception e) {
                Message.error("Lỗi: " + e.getMessage());
                Message.alert("Xóa nhân viên thất bại!");
            }
        }else{
                return;
            }
        }

    // Cập nhật vật tư
    private void updateNhanVien() {
        int row = tbl_NhanVien.getSelectedRow();
        if (row < 0) {
            Message.alert("Bạn phải chọn một dòng để sửa!");
            return;
        }

        if (txt_MatKhau.getText().isEmpty() || txt_TenNhanVien.getText().isEmpty() || txt_Email.getText().isEmpty()) {
            Message.alert("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

            model_NhanVien nv = new model_NhanVien();
            nv.setMaNhanVien(tbl_NhanVien.getValueAt(row, 0).toString());
            nv.setMatKhau(txt_MatKhau.getText().trim());
            nv.setTenNhanVien(txt_MatKhau.getText().trim());
            nv.setEmail(txt_Email.getText().trim());
            if (rdo_TruongPhong.isSelected()) {
                nv.setVaiTro(true);
            } else {
                nv.setVaiTro(false);     
            }
            if(rdo_VoHieuHoa.isSelected()){
                nv.setTrangThai(true);
            }else{
                nv.setTrangThai(false); 
            }

            boolean confirm = Message.confirm("Bạn có chắc chắn muốn sửa thông tin nhân viên?");
            if (confirm) {
                try{
                nvdao.update(nv);
                fillToTableNhanVien();
                Message.alert("Cập nhật thông tin nhân viên thành công!");
                }catch (Exception e) {
                Message.error("Lỗi: " + e.getMessage());
                Message.alert("Cập nhật thông tin nhân viên thất bại!");
                }  
            }else {
                return;
            }
    }

    private void searchNhanVien() {
        String maNhanVien = txt_TimKiem.getText().trim();
        if (maNhanVien.isEmpty()) {
            Message.alert("Vui lòng nhập từ khóa để tìm kiếm!");
            return;
        }
        tbl_ModelNhanVien.setRowCount(0); // Xóa dữ liệu cũ
        List<model_NhanVien> list_NhanVien = nvdao.selectById(maNhanVien);
        
        if (list_NhanVien.isEmpty()) {
            Message.error("Không tìm thấy nhân viên!");
        } else {
            for (model_NhanVien nv : list_NhanVien) {
                tbl_ModelNhanVien.addRow(new Object[]{nv.getMaNhanVien(),
                                                      nv.getMatKhau(),
                                                      nv.getTenNhanVien(),
                                                      nv.getEmail(),
                                                      nv.getVaiTro(),
                                                      nv.getTrangThai()});
            }
        }
    }
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pn_chucnang = new javax.swing.JPanel();
        btn_Them = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_TimKiem = new javax.swing.JButton();
        txt_TimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_notification = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_NhanVien = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(990, 700));

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

        btn_TimKiem.setText("Tìm Kiếm");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nhân Viên");

        btn_notification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bell.png"))); // NOI18N

        tbl_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Mật Khẩu", "Tên Nhân Viên", "Email", "Vai Trò", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_NhanVien);

        javax.swing.GroupLayout pn_chucnangLayout = new javax.swing.GroupLayout(pn_chucnang);
        pn_chucnang.setLayout(pn_chucnangLayout);
        pn_chucnangLayout.setHorizontalGroup(
            pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_chucnangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_chucnangLayout.createSequentialGroup()
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 355, Short.MAX_VALUE)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(pn_chucnangLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_notification))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        pn_chucnangLayout.setVerticalGroup(
            pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_chucnangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_chucnangLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(51, 51, 51)
                        .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_notification))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_chucnang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_chucnang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhanVienMouseClicked

    }//GEN-LAST:event_tbl_NhanVienMouseClicked

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        //addNhanVien();
        DiaLog_NhanVien dialogNV = new DiaLog_NhanVien(null, true);
        dialogNV.setVisible(true);
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        deleteNhanVien();
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        updateNhanVien();
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        searchNhanVien();
    }//GEN-LAST:event_btn_TimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JButton btn_notification;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pn_chucnang;
    private javax.swing.JTable tbl_NhanVien;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
