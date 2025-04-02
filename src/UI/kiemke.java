
package UI;

import DAO.DAO_Kho;
import DAO.DAO_PhieuNhap;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.util.Date;

/**
 *
 * @author ANH KHOA
 */
public class kiemke extends javax.swing.JFrame {
    private DefaultTableModel tableModel1; 
    //List<Object[]> danhSachHangHoa = DAO_KHO.kiemKeHangHoa();
                
    /**
     * Creates new form kiemke
     */                                                         
    public kiemke() {
        initComponents();
         tableModel1 = (DefaultTableModel) tblkiemke.getModel();
         kiemThuKiemKe();
         setLocationRelativeTo(this);
    } 
    private void kiemThuKiemKe() {
    List<Object[]> danhSachHangHoa = DAO_Kho.kiemKeHangHoa();
    
    if (danhSachHangHoa.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không có dữ liệu kiểm kê!", "Kiểm thử", JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) tblkiemke.getModel();
    model.setRowCount(0); // Xóa dữ liệu cũ

    for (Object[] row : danhSachHangHoa) {
        model.addRow(row); // Thêm dữ liệu vào bảng
    }

    JOptionPane.showMessageDialog(this, "kiểm kê thành công!", "Kiểm kê", JOptionPane.INFORMATION_MESSAGE);

    }
    



public void locDuLieuTheoThoiGian() {
    DefaultTableModel model = (DefaultTableModel) tblkiemke.getModel();
    model.setRowCount(0);

    java.util.Date selectedDate = data_boloc.getDate();
    if (selectedDate != null) {
        java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
        System.out.println("Ngày được chọn: " + sqlDate);

        List<Object[]> danhSach = DAO_PhieuNhap.kiemKePhieuNhap(sqlDate);

        if (!danhSach.isEmpty()) {
            for (Object[] row : danhSach) {
                model.addRow(row);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu kiểm kê cho ngày đã chọn.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày.");
    }
}





    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblkiemke = new javax.swing.JTable();
        pn_ChucNang = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        data_boloc = new com.toedter.calendar.JDateChooser();
        BtnKiemTra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblkiemke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã kho", "Mã vật tư", "Ngày nhập", "Số lượng đầu kỳ", "Số lượng nhập", "Số lượng xuất", "Số lượng tồn cuối kỳ"
            }
        ));
        jScrollPane1.setViewportView(tblkiemke);

        pn_ChucNang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setText("Kiểm kê");

        BtnKiemTra.setText("Bộ lọc");
        BtnKiemTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKiemTraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_ChucNangLayout = new javax.swing.GroupLayout(pn_ChucNang);
        pn_ChucNang.setLayout(pn_ChucNangLayout);
        pn_ChucNangLayout.setHorizontalGroup(
            pn_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_ChucNangLayout.createSequentialGroup()
                        .addComponent(data_boloc, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnKiemTra, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(539, Short.MAX_VALUE))
        );
        pn_ChucNangLayout.setVerticalGroup(
            pn_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ChucNangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(pn_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(data_boloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnKiemTra))
                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(pn_ChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_ChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKiemTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKiemTraActionPerformed

      locDuLieuTheoThoiGian();
    }//GEN-LAST:event_BtnKiemTraActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(kiemke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kiemke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kiemke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kiemke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kiemke().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnKiemTra;
    private com.toedter.calendar.JDateChooser data_boloc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pn_ChucNang;
    private javax.swing.JTable tblkiemke;
    // End of variables declaration//GEN-END:variables
}
