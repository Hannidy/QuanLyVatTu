
package UI;
import DAO.DAO_LoaiVatTu;
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
import model.model_LoaiVatTu;

public class DiaLog_LoaiVatTu extends javax.swing.JPanel {
    
    private DefaultTableModel tbl_ModelLoaiVatTu;
    private DAO_LoaiVatTu lvtdao = new DAO_LoaiVatTu();
    private List<model_LoaiVatTu> list_LoaiVatTu = new ArrayList<model_LoaiVatTu>();
    private pnVatTu pnLoaiVatTuRef;
        
    public DiaLog_LoaiVatTu(java.awt.Frame parent, boolean modal, pn_LoaiVatTu parentPanel) {
        super(parent, modal);
        initComponents();
        this.pnLoaiVatTuRef = parentPanel; // Gán tham chiếu đến pnVatTu
        this.styleButton();
        this.styleTextField();
        setLocationRelativeTo(null);
    }
    
    public void addLoaiVatTu() {
       boolean isValid = true;

       // Reset viền trước khi kiểm tra
       resetBorder(txt_Tenloaivattu);

       // Kiểm tra từng field
       if (txt_Tenloaivattu.getText().trim().isEmpty()) {
           setErrorBorder(txt_Tenloaivattu);
           isValid = false;
       }

       // Nếu có lỗi, hiển thị thông báo và dừng lại
       if (!isValid) {
           this.showNotification("Vui lòng nhập đủ thông tin!", true);
           return;
       }

       // Nếu hợp lệ, tiếp tục thêm loại vật tư
       model_LoaiVatTu lvt = new model_LoaiVatTu();
       lvt.setTenLoaiVatTu(txt_Tenloaivattu.getText().trim());

       try {
           String generatedId = lvtdao.generateAutoId(); // Tạo mã tự động
           lvt.setMaLoaiVatTu(generatedId);

           lvtdao.insert(lvt);
           this.showNotification("Thêm loại vật tư thành công!", false);

           // 🔔 Cập nhật bảng trong pnLoaiVatTu
           if (pnLoaiVatTuRef != null) {
               pnLoaiVatTuRef.fillToTableVatTu();
               pnLoaiVatTuRef.thongke();
               pnLoaiVatTuRef.themThongBao("Thêm", lvt.getTenLoaiVatTu()); // Cập nhật thông báo
           }

           // Đợi thông báo hiển thị xong rồi mới đóng form
           new Timer(1000, e -> dispose()).start();

       } catch (Exception e) {
           this.showNotification("Lỗi: " + e.getMessage(), true);
           this.showNotification("Thêm loại vật tư thất bại!", true);
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

        lbl_TenLoaiVatTu = new javax.swing.JLabel();
        btn_Them = new javax.swing.JButton();
        txt_Tenloaivattu = new javax.swing.JTextField();

        lbl_TenLoaiVatTu.setText("Tên Loại Vật Tư");

        btn_Them.setText("Thêm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Tenloaivattu)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_TenLoaiVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(btn_Them)))
                        .addGap(0, 113, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbl_TenLoaiVatTu)
                .addGap(18, 18, 18)
                .addComponent(txt_Tenloaivattu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Them)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Them;
    private javax.swing.JLabel lbl_TenLoaiVatTu;
    private javax.swing.JTextField txt_Tenloaivattu;
    // End of variables declaration//GEN-END:variables
}
