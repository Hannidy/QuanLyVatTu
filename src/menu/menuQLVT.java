package menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Font;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import Login.DangNhap;
import UI.pnKho;
import UI.pnNhaCungCap;
import UI.pnNhanVien;
import UI.pnPhieuNhap;
import UI.pnVatTu;

public class menuQLVT extends javax.swing.JFrame {
     private ResourceBundle bundle;
     
    private pnVatTu pnVatTu;
    private pnNhaCungCap pnNhaCungCap;
    private pnPhieuNhap pnPhieuNhap;
    private pnNhanVien pnNhanVien;
    private Object view;


 
    
    public menuQLVT() {
        initComponents();
        
        pnVatTu = new pnVatTu ();
        pnNhaCungCap = new pnNhaCungCap ();
        pnPhieuNhap = new pnPhieuNhap ();
        pnNhanVien = new pnNhanVien ();
        
        
        getContentPane().add(pnVatTu);

        
        
        codesize();
        bundle = ResourceBundle.getBundle("Language.Messages", new Locale("en", "US"));
        updateTexts();

    }

    public menuQLVT(String username) {
        initComponents(); // Khởi tạo giao diện
        codesize();
        try {
            pnVatTu vatTuPanel = new pnVatTu();
        } catch (Exception e) {
            e.printStackTrace();
        }
        lblUsername.setText(" " + username); // Gán tên đăng nhập vào JLabel
        
    }
    
    public void codesize() {
        String buttonStyle = "arc:10; borderWidth:0; focusWidth:0; innerFocusWidth:0;"
                + "background:#CBE5AE; "
                + "foreground:#000000;";
        String comboboxStyle = "arc:10; borderWidth:0; focusWidth:0; innerFocusWidth:0;"
                + "background:#CBE5AE; "
                + "foreground:#000000;";
        
        JComboBox [] comboBoxs = {languageBox};
        
        for ( JComboBox cbx : comboBoxs ){
            cbx.putClientProperty(FlatClientProperties.STYLE, comboboxStyle);
        }

        JButton[] buttons = {btn_VatTu, btn_Kho, btn_NhaCungCap, btn_PhieuNhap, btn_PhieuXuat, btn_PhongBan, btn_NhanVien, btn_LoaiVatTu, btn_DangXuat};

        for (JButton btn : buttons ) {
            btn.putClientProperty(FlatClientProperties.STYLE, buttonStyle);
        }
    }
    
 

    private void updateLanguage() {
    String selectedLang = (String) languageBox.getSelectedItem();
    
    // Chọn locale phù hợp
    Locale newLocale;
    if ("English".equals(selectedLang)) {
        newLocale = new Locale("en", "US");
    } else {
        newLocale = new Locale("vi", "VN");
    }

    // Debug kiểm tra xem ngôn ngữ có thực sự thay đổi không
    System.out.println("🔄 Switching language to: " + selectedLang);
    System.out.println("Before Locale Change: " + Locale.getDefault());

    // Cập nhật locale toàn hệ thống
    Locale.setDefault(newLocale);
    
    // Xóa cache và load lại ResourceBundle
    ResourceBundle.clearCache();
    bundle = ResourceBundle.getBundle("Language.Messages", newLocale);

    System.out.println("✅ New Locale Set: " + Locale.getDefault());

    // Cập nhật giao diện
    updateTexts();

    // Đặt lại lựa chọn trong ComboBox để tránh lỗi bị "đóng băng"
    languageBox.setSelectedItem(selectedLang);

    // Cập nhật lại giao diện
    SwingUtilities.updateComponentTreeUI(this);
    revalidate();
    repaint();
}
       
    
    private void updateTexts() {
    btn_VatTu.setText(bundle.getString("btn_VatTu"));
    btn_Kho.setText(bundle.getString("btn_Kho"));
    btn_NhaCungCap.setText(bundle.getString("btn_NhaCungCap"));
    btn_PhieuNhap.setText(bundle.getString("btn_PhieuNhap"));
    btn_PhieuXuat.setText(bundle.getString("btn_PhieuXuat"));
    btn_PhongBan.setText(bundle.getString("btn_PhongBan"));
    btn_NhanVien.setText(bundle.getString("btn_NhanVien"));
    btn_LoaiVatTu.setText(bundle.getString("btn_LoaiVatTu"));
    btn_DangXuat.setText(bundle.getString("btn_DangXuat"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_VatTu = new javax.swing.JButton();
        btn_Kho = new javax.swing.JButton();
        btn_PhieuNhap = new javax.swing.JButton();
        btn_PhieuXuat = new javax.swing.JButton();
        btn_PhongBan = new javax.swing.JButton();
        btn_DangXuat = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        btn_NhanVien = new javax.swing.JButton();
        btn_LoaiVatTu = new javax.swing.JButton();
        btn_NhaCungCap = new javax.swing.JButton();
        languageBox = new javax.swing.JComboBox<>();
        pn_Form = new javax.swing.JPanel();
        pnKho = new UI.pnKho();
        pnPhieuNhap1 = new UI.pnPhieuNhap();
        pnPhieuXuat1 = new UI.pnPhieuXuat();
        pn_LoaiVatTu1 = new UI.pn_LoaiVatTu();
        pnVatTu1 = new UI.pnVatTu();
        pnNhaCungCap1 = new UI.pnNhaCungCap();
        pnPhongBan1 = new UI.pnPhongBan();
        pnNhanVien1 = new UI.pnNhanVien();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pn_menu.setBackground(new java.awt.Color(203, 229, 174));
        pn_menu.setPreferredSize(new java.awt.Dimension(200, 700));
        pn_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pn_menuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pn_menuMouseExited(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/erp (1).png"))); // NOI18N

        btn_VatTu.setText("Vật Tư");
        btn_VatTu.setBorder(null);
        btn_VatTu.setFocusPainted(false);
        btn_VatTu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_VatTuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_VatTuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_VatTuMouseExited(evt);
            }
        });
        btn_VatTu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VatTuActionPerformed(evt);
            }
        });

        btn_Kho.setText("Kho");
        btn_Kho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_KhoActionPerformed(evt);
            }
        });

        btn_PhieuNhap.setText("Phiếu Nhập");
        btn_PhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PhieuNhapActionPerformed(evt);
            }
        });

        btn_PhieuXuat.setText("Phiếu Xuất");
        btn_PhieuXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PhieuXuatActionPerformed(evt);
            }
        });

        btn_PhongBan.setText("Phòng Ban");
        btn_PhongBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PhongBanActionPerformed(evt);
            }
        });

        btn_DangXuat.setText("Đăng Xuất");
        btn_DangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DangXuatActionPerformed(evt);
            }
        });

        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add-user.png"))); // NOI18N
        lblUsername.setText(" Hello Admin !");

        btn_NhanVien.setText("Nhân Viên");
        btn_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NhanVienActionPerformed(evt);
            }
        });

        btn_LoaiVatTu.setText("Loại Vật Tư");
        btn_LoaiVatTu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LoaiVatTuActionPerformed(evt);
            }
        });

        btn_NhaCungCap.setText("Nhà Cung Cấp");
        btn_NhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NhaCungCapActionPerformed(evt);
            }
        });

        languageBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        languageBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "VietNam" }));
        languageBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        languageBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                languageBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_menuLayout = new javax.swing.GroupLayout(pn_menu);
        pn_menu.setLayout(pn_menuLayout);
        pn_menuLayout.setHorizontalGroup(
            pn_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_menuLayout.createSequentialGroup()
                .addGroup(pn_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_menuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pn_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btn_PhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(btn_PhieuXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(btn_PhongBan, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(btn_NhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(btn_LoaiVatTu, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(btn_VatTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_NhaCungCap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Kho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_DangXuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(languageBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pn_menuLayout.setVerticalGroup(
            pn_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_menuLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btn_VatTu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_LoaiVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Kho, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_NhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_PhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_PhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_PhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(languageBox, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(55, 55, 55)
                .addComponent(btn_DangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        pn_Form.setBackground(new java.awt.Color(255, 255, 255));
        pn_Form.setLayout(new java.awt.CardLayout());
        pn_Form.add(pnKho, "card3");
        pn_Form.add(pnPhieuNhap1, "card5");
        pn_Form.add(pnPhieuXuat1, "card6");
        pn_Form.add(pn_LoaiVatTu1, "card8");
        pn_Form.add(pnVatTu1, "card6");
        pn_Form.add(pnNhaCungCap1, "card7");
        pn_Form.add(pnPhongBan1, "card8");
        pn_Form.add(pnNhanVien1, "card9");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_Form, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_menu, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
            .addComponent(pn_Form, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_DangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangXuatActionPerformed
        DangNhap DN = new DangNhap();
        DN.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_DangXuatActionPerformed

    private void btn_VatTuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VatTuActionPerformed
            pnVatTu1.setVisible(true);
            pnKho.setVisible(false);
            pnNhaCungCap1.setVisible(false);
            pnPhieuNhap1.setVisible(false);
            pnPhieuXuat1.setVisible(false);
            pnPhongBan1.setVisible(false);
            pnNhanVien1.setVisible(false);
            pn_LoaiVatTu1.setVisible(false);
    }//GEN-LAST:event_btn_VatTuActionPerformed

    private void btn_KhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_KhoActionPerformed
            pnVatTu1.setVisible(false);
            pnKho.setVisible(true);
            pnNhaCungCap1.setVisible(false);
            pnPhieuNhap1.setVisible(false);
            pnPhieuXuat1.setVisible(false);
            pnPhongBan1.setVisible(false);
            pnNhanVien1.setVisible(false);
            pn_LoaiVatTu1.setVisible(false);
    }//GEN-LAST:event_btn_KhoActionPerformed

    private void btn_PhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PhieuNhapActionPerformed
            pnVatTu1.setVisible(false);
            pnKho.setVisible(false);
            pnNhaCungCap1.setVisible(false);
            pnPhieuNhap1.setVisible(true);
            pnPhieuXuat1.setVisible(false);
            pnPhongBan1.setVisible(false);
            pnNhanVien1.setVisible(false);
            pn_LoaiVatTu1.setVisible(false);
    }//GEN-LAST:event_btn_PhieuNhapActionPerformed

    private void btn_PhieuXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PhieuXuatActionPerformed
            pnVatTu1.setVisible(false);
            pnKho.setVisible(false);
            pnNhaCungCap1.setVisible(false);
            pnPhieuNhap1.setVisible(false);
            pnPhieuXuat1.setVisible(true);
            pnPhongBan1.setVisible(false);
            pnNhanVien1.setVisible(false);
            pn_LoaiVatTu1.setVisible(false);
    }//GEN-LAST:event_btn_PhieuXuatActionPerformed

    private void btn_PhongBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PhongBanActionPerformed
            pnVatTu1.setVisible(false);
            pnKho.setVisible(false);
            pnNhaCungCap1.setVisible(false);
            pnPhieuNhap1.setVisible(false);
            pnPhieuXuat1.setVisible(false);
            pnPhongBan1.setVisible(true);
            pnNhanVien1.setVisible(false);
            pn_LoaiVatTu1.setVisible(false);
    }//GEN-LAST:event_btn_PhongBanActionPerformed

    private void btn_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NhanVienActionPerformed
            pnVatTu1.setVisible(false);
            pnKho.setVisible(false);
            pnNhaCungCap1.setVisible(false);
            pnPhieuNhap1.setVisible(false);
            pnPhieuXuat1.setVisible(false);
            pnPhongBan1.setVisible(false);
            pnNhanVien1.setVisible(true);
            pn_LoaiVatTu1.setVisible(false);
    }//GEN-LAST:event_btn_NhanVienActionPerformed

    private void btn_VatTuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_VatTuMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_VatTuMouseEntered

    private void btn_VatTuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_VatTuMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_VatTuMouseExited

    private void btn_VatTuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_VatTuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_VatTuMouseClicked

    private void pn_menuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_menuMouseEntered
        // TODO add your handling code here;
    }//GEN-LAST:event_pn_menuMouseEntered

    private void pn_menuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_menuMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pn_menuMouseExited

    private void btn_NhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NhaCungCapActionPerformed
            pnVatTu1.setVisible(false);
            pnKho.setVisible(false);
            pnNhaCungCap1.setVisible(true);
            pnPhieuNhap1.setVisible(false);
            pnPhieuXuat1.setVisible(false);
            pnPhongBan1.setVisible(false);
            pnNhanVien1.setVisible(false);
            pn_LoaiVatTu1.setVisible(false);
    }//GEN-LAST:event_btn_NhaCungCapActionPerformed

    private void languageBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_languageBoxActionPerformed
        updateLanguage();
    }//GEN-LAST:event_languageBoxActionPerformed

    private void btn_LoaiVatTuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LoaiVatTuActionPerformed
            pnVatTu1.setVisible(false);
            pnKho.setVisible(false);
            pnNhaCungCap1.setVisible(false);
            pnPhieuNhap1.setVisible(false);
            pnPhieuXuat1.setVisible(false);
            pnPhongBan1.setVisible(false);
            pnNhanVien1.setVisible(false);
            pn_LoaiVatTu1.setVisible(true);
    }//GEN-LAST:event_btn_LoaiVatTuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatRobotoFont.install();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuQLVT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_DangXuat;
    private javax.swing.JButton btn_Kho;
    private javax.swing.JButton btn_LoaiVatTu;
    private javax.swing.JButton btn_NhaCungCap;
    private javax.swing.JButton btn_NhanVien;
    private javax.swing.JButton btn_PhieuNhap;
    private javax.swing.JButton btn_PhieuXuat;
    private javax.swing.JButton btn_PhongBan;
    private javax.swing.JButton btn_VatTu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> languageBox;
    private javax.swing.JLabel lblUsername;
    private UI.pnKho pnKho;
    private UI.pnNhaCungCap pnNhaCungCap1;
    private UI.pnNhanVien pnNhanVien1;
    private UI.pnPhieuNhap pnPhieuNhap1;
    private UI.pnPhieuXuat pnPhieuXuat1;
    private UI.pnPhongBan pnPhongBan1;
    private UI.pnVatTu pnVatTu1;
    private javax.swing.JPanel pn_Form;
    private UI.pn_LoaiVatTu pn_LoaiVatTu1;
    private javax.swing.JPanel pn_menu;
    // End of variables declaration//GEN-END:variables
}
