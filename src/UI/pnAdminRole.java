/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import jdbc.JdbcHelper;

/**
 *
 * @author ADMIN
 */
public class pnAdminRole extends javax.swing.JPanel {

    private DefaultTableModel tblTableModel;

    public pnAdminRole() {
        initComponents();
        initTable();
        loadUserData();
        addTableSelectionListener();
    }

    private void initTable() {
        tblTableModel = new DefaultTableModel(new String[]{"Username", "Email", "Role"}, 0);
        tbl_role.setModel(tblTableModel);
    }

    private void loadUserData() {
        tblTableModel.setRowCount(0);
        try (Connection conn = JdbcHelper.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT username, email, role FROM Users")) {

            while (rs.next()) {
                tblTableModel.addRow(new Object[]{
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("role")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    private void addTableSelectionListener() {
        tbl_role.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = tbl_role.getSelectedRow();
            if (selectedRow != -1) {
                String currentRole = tbl_role.getValueAt(selectedRow, 2).toString();
                cb_role.setSelectedItem(currentRole); // Cập nhật cb_role theo dòng chọn
            }
        });
    }

    private void updateUserRole() {
        int selectedRow = tbl_role.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản để cập nhật!");
            return;
        }

        String username = tbl_role.getValueAt(selectedRow, 0).toString();
        String newRole = cb_role.getSelectedItem().toString();

        try (Connection conn = JdbcHelper.getConnection(); PreparedStatement stmt = conn.prepareStatement("UPDATE Users SET role = ? WHERE username = ?")) {

            stmt.setString(1, newRole);
            stmt.setString(2, username);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                loadUserData();  // Làm mới bảng sau khi cập nhật
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi cập nhật dữ liệu: " + e.getMessage());
        }
    }

    private void searchUser() {
        String searchValue = txt_HienTimKiem.getText().trim();

        try (Connection conn = JdbcHelper.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT username, email, role FROM Users WHERE username LIKE ?")) {

            stmt.setString(1, "%" + searchValue + "%");
            try (ResultSet rs = stmt.executeQuery()) {

                tblTableModel.setRowCount(0); // Sửa lỗi: dùng tblTableModel

                while (rs.next()) {
                    tblTableModel.addRow(new Object[]{
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("role")
                    });
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm: " + e.getMessage());
        }
    }

    private void deleteUser() {
        int selectedRow = tbl_role.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản để xóa!");
            return;
        }

        String username = tblTableModel.getValueAt(selectedRow, 0).toString();

        // Thêm hộp thoại xác nhận trước khi xóa
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn xóa tài khoản \"" + username + "\" không?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try (Connection conn = JdbcHelper.getConnection(); PreparedStatement stmt = conn.prepareStatement("DELETE FROM Users WHERE username = ?")) {

            stmt.setString(1, username);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadUserData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại! Người dùng có thể không tồn tại.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi xóa dữ liệu: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_role = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pn_chucnang = new javax.swing.JPanel();
        btn_updaterole = new javax.swing.JButton();
        btn_deleterole = new javax.swing.JButton();
        btn_searchrole = new javax.swing.JButton();
        txt_HienTimKiem = new javax.swing.JTextField();
        cb_role = new javax.swing.JComboBox<>();

        tbl_role.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên Người Dùng", "E-mail", "Vai Trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_role.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_roleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_role);

        jLabel1.setBackground(new java.awt.Color(191, 239, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ROLE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        pn_chucnang.setBackground(new java.awt.Color(255, 255, 255));

        btn_updaterole.setText("Cập Nhật Quyền Hạn");
        btn_updaterole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateroleActionPerformed(evt);
            }
        });

        btn_deleterole.setText("Xóa");
        btn_deleterole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteroleActionPerformed(evt);
            }
        });

        btn_searchrole.setText("Tìm Kiếm");
        btn_searchrole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchroleActionPerformed(evt);
            }
        });

        cb_role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Manager", "Employee", "Warehouse" }));

        javax.swing.GroupLayout pn_chucnangLayout = new javax.swing.GroupLayout(pn_chucnang);
        pn_chucnang.setLayout(pn_chucnangLayout);
        pn_chucnangLayout.setHorizontalGroup(
            pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_chucnangLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btn_updaterole, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_deleterole, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cb_role, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_searchrole, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_HienTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pn_chucnangLayout.setVerticalGroup(
            pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_chucnangLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_HienTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_updaterole, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_deleterole, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_searchrole, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cb_role, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_chucnang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_chucnang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_roleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_roleMouseClicked
        //clickHereVatTu();
    }//GEN-LAST:event_tbl_roleMouseClicked

    private void btn_deleteroleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteroleActionPerformed
        deleteUser();
    }//GEN-LAST:event_btn_deleteroleActionPerformed

    private void btn_searchroleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchroleActionPerformed
        searchUser();
    }//GEN-LAST:event_btn_searchroleActionPerformed

    private void btn_updateroleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateroleActionPerformed
        updateUserRole();
    }//GEN-LAST:event_btn_updateroleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_deleterole;
    private javax.swing.JButton btn_searchrole;
    private javax.swing.JButton btn_updaterole;
    private javax.swing.JComboBox<String> cb_role;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pn_chucnang;
    private javax.swing.JTable tbl_role;
    private javax.swing.JTextField txt_HienTimKiem;
    // End of variables declaration//GEN-END:variables
}
