
package UI;

import DAO.DAO_ChuyenDoiDonVi;
import javax.swing.JOptionPane;


public class pnTonKho extends javax.swing.JPanel {

 
    public pnTonKho() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_TonKho = new javax.swing.JTable();
        btn_QuyDoi = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Tồn Kho");

        tbl_TonKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Kho", "Mã Vật Tư ", "Số Lượng ", "Đơn Vị ", "Tồn Tối Thiểu", "Tồn Tối Đa", "Vị Trí"
            }
        ));
        jScrollPane1.setViewportView(tbl_TonKho);

        btn_QuyDoi.setText("Quy Đổi");
        btn_QuyDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QuyDoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_QuyDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(btn_QuyDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_QuyDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QuyDoiActionPerformed
         int selectedRow = tbl_TonKho.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn vật tư cần chuyển đổi!");
        return;
    }

    String maKho = tbl_TonKho.getValueAt(selectedRow, 0).toString();
    String maVatTu = tbl_TonKho.getValueAt(selectedRow, 1).toString();
    int soLuongHienTai = Integer.parseInt(tbl_TonKho.getValueAt(selectedRow, 2).toString());
    String donViHienTai = tbl_TonKho.getValueAt(selectedRow, 3).toString();

    // Nhập đơn vị cần chuyển đổi
    String donViMoi = JOptionPane.showInputDialog(this, "Nhập đơn vị mới:");
    if (donViMoi == null || donViMoi.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Đơn vị mới không hợp lệ!");
        return;
    }

    // Nhập hệ số quy đổi
    String heSoInput = JOptionPane.showInputDialog(this, "Nhập hệ số quy đổi:");
    if (heSoInput == null || heSoInput.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Hệ số quy đổi không hợp lệ!");
        return;
    }

    double heSoQuyDoi;
    try {
        heSoQuyDoi = Double.parseDouble(heSoInput);
        if (heSoQuyDoi <= 0) {
            JOptionPane.showMessageDialog(this, "Hệ số quy đổi phải lớn hơn 0!");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Hệ số quy đổi không hợp lệ!");
        return;
    }

    // Gọi DAO để xử lý chuyển đổi
    DAO_ChuyenDoiDonVi dao = new DAO_ChuyenDoiDonVi();
    boolean success = dao.chuyenDoiTonKho(maKho, maVatTu, donViMoi, heSoQuyDoi);

    if (success) {
        JOptionPane.showMessageDialog(this, "Chuyển đổi đơn vị thành công!");
        loadTonKho(); // Hàm reload lại dữ liệu trên bảng
    } else {
        JOptionPane.showMessageDialog(this, "Chuyển đổi đơn vị thất bại! Kiểm tra lại thông tin.");
    }
    }//GEN-LAST:event_btn_QuyDoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_QuyDoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_TonKho;
    // End of variables declaration//GEN-END:variables
}
