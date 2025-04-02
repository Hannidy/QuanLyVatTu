/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;

import DAO.DAO_VatTu;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.management.Notification;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import model.model_VatTu;


public class DiaLog_VatTu extends javax.swing.JDialog {

    private DefaultTableModel tbl_ModelVatTu;
    private DAO_VatTu vtdao = new DAO_VatTu();
    private List<model_VatTu> list_VatTu = new ArrayList<model_VatTu>();
    private pnVatTu pnVatTuRef;

    public DiaLog_VatTu(java.awt.Frame parent, boolean modal, pnVatTu parentPanel) {
        super(parent, modal);
        initComponents();
        this.pnVatTuRef = parentPanel; // Gán tham chiếu đến pnVatTu
        this.styleButton();
        this.styleTextField();
        setLocationRelativeTo(null);
    }

    public static void showNotification(String message, boolean isError) {  // Thông báo trượt xuống
        JWindow window = new JWindow();
        window.setLayout(new BorderLayout());

        // Panel custom với góc bo tròn
        RoundedPanel panel = new RoundedPanel(20);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // JLabel chứa thông báo
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(Color.black);

        // Màu nền dựa vào loại thông báo
        if (isError) {
            panel.setBackground(new Color(255, 105, 97)); // Đỏ tươi cho lỗi
        } else {
            panel.setBackground(new Color(215, 233, 168)); // Xanh lá cây
        }

        panel.add(label, BorderLayout.CENTER);
        window.add(panel, BorderLayout.CENTER);
        window.setSize(230, 60);

        // Định vị cửa sổ thông báo ở giữa phía trên màn hình
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - window.getWidth()) / 2;
        int startY = -50; // Bắt đầu ngoài màn hình
        int endY = 50; // Hiển thị tại vị trí này
        window.setLocation(x, startY);
        window.setVisible(true);

        // Tạo hiệu ứng trượt xuống
        Timer slideTimer = new Timer(2, new ActionListener() { // Giảm từ 5ms xuống 2ms
            int y = startY;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (y < endY) {
                    y += 5; // Tăng khoảng cách di chuyển từ 2px lên 5px để nhanh hơn
                    window.setLocation(x, y);
                } else {
                    ((Timer) e.getSource()).stop();

                    // Sau khi hiển thị, đợi 1 giây rồi mờ dần
                    Timer fadeTimer = new Timer(30, new ActionListener() { // Làm mờ nhanh hơn
                        float opacity = 1f;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            opacity -= 0.05f;
                            if (opacity <= 0) {
                                ((Timer) e.getSource()).stop();
                                window.dispose();
                            } else {
                                window.setOpacity(opacity);
                            }
                        }
                    });

                    // Đợi 1 giây trước khi mờ dần
                    Timer delayTimer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fadeTimer.start();
                        }
                    });
                    delayTimer.setRepeats(false);
                    delayTimer.start();
                }
            }
        });
        slideTimer.start();
    }

    // Panel bo góc tùy chỉnh
    static class RoundedPanel extends JPanel {

        private int cornerRadius;

        public RoundedPanel(int radius) {
            this.cornerRadius = radius;
            setOpaque(false); // Để JPanel không bị che khuất màu nền
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Vẽ hình chữ nhật bo tròn
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2d.dispose();
        }
    }

    public void styleButton() {  // Custom button
        btn_Them.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "background:#CBE5AE; "
                + "foreground:#000000;"
                + "innerFocusWidth:0");
        btn_lamMoi.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "background:#CBE5AE; "
                + "foreground:#000000;"
                + "innerFocusWidth:0");

    }

    public void styleTextField() {  // Custom textfield
        this.txt_tenVT.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "background:#FFFFFF;"
                + "foreground:#000000;"
                + "innerFocusWidth:1;");
        this.txt_maLoaiVatTu.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "background:#FFFFFF;"
                + "foreground:#000000;"
                + "innerFocusWidth:1;");
    }

    public void setData(String tenVT, String maLoaiVatTu) {
        txt_tenVT.setText(tenVT);
        txt_maLoaiVatTu.setText(maLoaiVatTu);
    }

    public void fillToTableVatTu() {
        try {
            // Xóa toàn bộ dữ liệu cũ trước khi thêm mới
            tbl_ModelVatTu.setRowCount(0);

            // Lấy danh sách vật tư từ database
            list_VatTu = vtdao.selectAll();
            if (list_VatTu != null) {
                for (model_VatTu vt : list_VatTu) {
                    tbl_ModelVatTu.addRow(new Object[]{
                        vt.getMaVatTu(), // Chỉ lấy Mã Vật Tư
                        vt.getTenVatTu(), // Chỉ lấy Tên Vật Tư
                        vt.getMaLoaiVatTu() // Chỉ lấy Mã Loại Vật Tư
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi để dễ debug
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn dữ liệu: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void lamMoi() {
        pnVatTuRef.fillToTableVatTu();
        this.txt_tenVT.setText("");
        this.txt_maLoaiVatTu.setText("");
    }

    public void addVatTu() {
        boolean isValid = true;

        // Reset viền trước khi kiểm tra
        resetBorder(txt_tenVT);
        resetBorder(txt_maLoaiVatTu); // Chỉ cần kiểm tra TenVatTu và MaLoaiVatTu

        // Kiểm tra từng field
        if (txt_tenVT.getText().trim().isEmpty()) {
            setErrorBorder(txt_tenVT);
            isValid = false;
        }
        if (txt_maLoaiVatTu.getText().trim().isEmpty()) {
            setErrorBorder(txt_maLoaiVatTu);
            isValid = false;
        }

        // Nếu có lỗi, hiển thị thông báo và dừng lại
        if (!isValid) {
            this.showNotification("Vui lòng nhập đủ thông tin!", true);
            return;
        }

        // Nếu hợp lệ, tiếp tục thêm vật tư
        model_VatTu vt = new model_VatTu();
        vt.setTenVatTu(txt_tenVT.getText().trim());
        vt.setMaLoaiVatTu(txt_maLoaiVatTu.getText().trim()); // Chỉ thêm MaLoaiVatTu

        try {
            vtdao.insert(vt);
            this.showNotification("Thêm vật tư thành công!", false);

            // 🔔 Cập nhật bảng trong pnVatTu
            if (pnVatTuRef != null) {
                pnVatTuRef.fillToTableVatTu();
                pnVatTuRef.thongke();
                pnVatTuRef.themThongBao("Thêm", vt.getTenVatTu()); // Cập nhật thông báo
            }

            // Đợi thông báo hiển thị xong rồi mới đóng form
            new Timer(1000, e -> dispose()).start();

        } catch (Exception e) {
            this.showNotification("Lỗi: " + e.getMessage(), true);
            this.showNotification("Thêm vật tư thất bại!", true);
        }
    }

    // Đặt viền đỏ cho JTextField khi có lỗi
    private void setErrorBorder(JTextField field) {
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED)); // Gạch đỏ dưới
    }

    // Đặt lại viền mặc định cho JTextField khi nhập đúng
    private void resetBorder(JTextField field) {
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 200, 200))); // Viền xám nhạt
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_tenVT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_maLoaiVatTu = new javax.swing.JTextField();
        btn_Them = new javax.swing.JButton();
        btn_lamMoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Vat Tu");

        jLabel2.setText("Ten Vat Tu:");

        jLabel3.setText("MaLoaiVatTu:");

        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_lamMoi.setText("Làm Mới");
        btn_lamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                    .addComponent(txt_tenVT)
                    .addComponent(txt_maLoaiVatTu)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 257, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(btn_Them)
                .addGap(18, 18, 18)
                .addComponent(btn_lamMoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_tenVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_maLoaiVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them)
                    .addComponent(btn_lamMoi))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        this.addVatTu();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_lamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoiActionPerformed
        // TODO add your handling code here:
        lamMoi();
    }//GEN-LAST:event_btn_lamMoiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatRobotoFont.install();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DiaLog_VatTu dialog = new DiaLog_VatTu(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_lamMoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txt_maLoaiVatTu;
    private javax.swing.JTextField txt_tenVT;
    // End of variables declaration//GEN-END:variables
}
