/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;

import DAO.DAO_Kho;
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
import model.model_Kho;
import util.Message;

/**
 *
 * @author RubyNgoc
 */
public class DiaLog_Kho extends javax.swing.JDialog {

    private DefaultTableModel tbl_ModelKho;
    DAO_Kho kDAO = new DAO_Kho();
    private pnKho pnKhoRef;
    private List<model_Kho> list_Kho = new ArrayList<model_Kho>();

    /**
     * Creates new form DIaLog_Kho
     */
    public DiaLog_Kho(java.awt.Frame parent, boolean modal, pnKho parentPanel) {
        super(parent, modal);
        initComponents();
        pnKhoRef = parentPanel;
        setLocationRelativeTo(null);
        this.styleButton();
        this.styleTextField();
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
        window.setSize(250, 60);

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
        this.txt_tenKho.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "background:#FFFFFF;"
                + "foreground:#000000;"
                + "innerFocusWidth:1;");
        this.txt_maloaivatTu.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "background:#FFFFFF;"
                + "foreground:#000000;"
                + "innerFocusWidth:1;");
        this.txt_diaChi.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "background:#FFFFFF;"
                + "foreground:#000000;"
                + "innerFocusWidth:1;");
    }

    public void setData(String tenKho, String maloaivatTu, String diaChi) {
        txt_tenKho.setText(tenKho);
        txt_maloaivatTu.setText(maloaivatTu);
        txt_diaChi.setText(diaChi);
    }

    public void fillToTableKho() {
        try {
            // Xóa toàn bộ dữ liệu cũ trước khi thêm mới
            tbl_ModelKho.setRowCount(0);

            // Lấy danh sách kho từ database
            list_Kho = kDAO.selectAll();
            if (list_Kho != null) {
                for (model_Kho k : list_Kho) {
                    tbl_ModelKho.addRow(new Object[]{
                        k.getMaKho(), // Mã Kho
                        k.getTenKho(), // Tên Kho
                        k.getMaLoaiVatTu(), // Mã Loại Vật Tư
                        k.getDiachi() // Địa Chỉ
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi để dễ debug
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn dữ liệu: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addKho() {
        boolean isValid = true;

        // Reset viền trước khi kiểm tra
        resetBorder(txt_tenKho);
        resetBorder(txt_maloaivatTu);
        resetBorder(txt_diaChi);

        // Kiểm tra từng field
        if (txt_tenKho.getText().trim().isEmpty()) {
            setErrorBorder(txt_tenKho);
            isValid = false;
        }
        if (txt_maloaivatTu.getText().trim().isEmpty()) {
            setErrorBorder(txt_maloaivatTu);
            isValid = false;
        }
        if (txt_diaChi.getText().trim().isEmpty()) {
            setErrorBorder(txt_diaChi);
            isValid = false;
        }

        // Nếu có lỗi, hiển thị thông báo và dừng lại
        if (!isValid) {
            showNotification("Vui lòng nhập đủ thông tin!", true);
            return;
        }

        // Nếu hợp lệ, tiếp tục thêm kho
        model_Kho k = new model_Kho();
        k.setTenKho(txt_tenKho.getText().trim());
        k.setMaLoaiVatTu(txt_maloaivatTu.getText().trim());
        k.setDiachi(txt_diaChi.getText().trim());

        try {
            kDAO.insert(k);
            showNotification("Thêm kho thành công!", false);

            // 🔔 Cập nhật bảng nếu có tham chiếu đến pnKho
            if (pnKhoRef != null) {
                pnKhoRef.fillToTableKho();
                pnKhoRef.themThongBao("Thêm", k.getTenKho()); // Cập nhật thông báo
            }

            // Đợi thông báo hiển thị xong rồi mới đóng form
            new Timer(1000, e -> dispose()).start();

        } catch (Exception e) {
            showNotification("Lỗi: " + e.getMessage(), true);
            showNotification("Thêm kho thất bại!", true);
        }
    }
    
    public void lamMoi(){
        pnKhoRef.fillToTableKho();
        this.txt_tenKho.setText("");
        this.txt_maloaivatTu.setText("");
        this.txt_diaChi.setText("");
    }

    private void setErrorBorder(JTextField field) {
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED)); // Gạch đỏ dưới
    }

    // Đặt lại viền mặc định cho JTextField khi nhập đúng
    private void resetBorder(JTextField field) {
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 200, 200))); // Viền xám nhạt
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_tenKho = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_maloaivatTu = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_diaChi = new javax.swing.JTextField();
        btn_Them = new javax.swing.JButton();
        btn_lamMoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Kho");

        jLabel3.setText("Tên Kho:");

        jLabel4.setText("Mã Loại Vật Tư:");

        jLabel5.setText("Địa Chỉ:");

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
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_tenKho)
                    .addComponent(txt_maloaivatTu)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txt_diaChi))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(btn_Them)
                .addGap(18, 18, 18)
                .addComponent(btn_lamMoi)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txt_tenKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txt_maloaivatTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them)
                    .addComponent(btn_lamMoi))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        addKho();
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
                DiaLog_Kho dialog = new DiaLog_Kho(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txt_diaChi;
    private javax.swing.JTextField txt_maloaivatTu;
    private javax.swing.JTextField txt_tenKho;
    // End of variables declaration//GEN-END:variables
}
