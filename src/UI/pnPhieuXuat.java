/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class pnPhieuXuat extends javax.swing.JPanel {

    /**
     * Creates new form pnPhieuXuat
     */
    public pnPhieuXuat() {
        initComponents();
        styleCode();
        ((JTextField) txt_NgayXuat.getDateEditor().getUiComponent()).setEditable(false);
    }

    void styleCode(){
        txt_TimKiemCTPX.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "innerFocusWidth:0;");
        txt_TimKiemPX.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "innerFocusWidth:0;");
         btn_ThemCTPX.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
         btn_ThemPX.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
         btn_SuaCTPX.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
         btn_SuaPX.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
        btn_XoaCTPX.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
         btn_XoaPX.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
         btn_TimCTPX.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
         btn_TimPX.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
         btn_Lammoi1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
         btn_Lammoi.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
        
        tbl_ChiTietPhieuXuat.putClientProperty(FlatClientProperties.STYLE, ""
            + "showVerticalLines:true;"
            + "showHorizontalLines:true;"
            + "intercellSpacing:3,3;"
            + "selectionBackground:#A5D6A7;"
            + "selectionForeground:#000000;"
            + "gridColor:#BDBDBD;"
            + "border:1,1,1,1,#9E9E9E;"
            + "rowHeight:30;"
        );
        tbl_PhieuXuat.putClientProperty(FlatClientProperties.STYLE, ""
            + "showVerticalLines:true;"
            + "showHorizontalLines:true;"
            + "intercellSpacing:3,3;"
            + "selectionBackground:#A5D6A7;"
            + "selectionForeground:#000000;"
            + "gridColor:#BDBDBD;"
            + "border:1,1,1,1,#9E9E9E;"
            + "rowHeight:30;"
        );
//        pn_chucnang.putClientProperty(FlatClientProperties.STYLE, ""
//        + "background: #E0ECDE;"   // Màu nền xám nhạt (giống giao diện hiện đại)
//        + "arc: 15;"); // Bo tròn góc 15px

}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btn_SuaPX = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_NgayXuat = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txt_MaKho = new javax.swing.JTextField();
        txt_MaPhongBan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_PhieuXuat = new javax.swing.JTable();
        txt_TimKiemPX = new javax.swing.JTextField();
        btn_TimPX = new javax.swing.JButton();
        btn_ThemPX = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btn_XoaPX = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btn_XemCT1 = new javax.swing.JButton();
        btn_Lammoi1 = new javax.swing.JButton();
        btn_XuatExcel1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txt_TimKiemCTPX = new javax.swing.JTextField();
        btn_ThemCTPX = new javax.swing.JButton();
        btn_XoaCTPX = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btn_SuaCTPX = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btn_Lammoi = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btn_XemCT = new javax.swing.JButton();
        btn_TimCTPX = new javax.swing.JButton();
        btn_XuatExcel = new javax.swing.JButton();
        txt_MaPhieuXuatCTPX = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_MaVatTu = new javax.swing.JTextField();
        txt_SoLuong = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_ChiTietPhieuXuat = new javax.swing.JTable();

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_SuaPX.setText("Sửa");
        btn_SuaPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaPXActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Mã Kho :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Mã Phòng Ban :");

        tbl_PhieuXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Xuất", "Ngày Xuất", "Mã Kho", "Mã Phòng Ban"
            }
        ));
        tbl_PhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_PhieuXuatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_PhieuXuat);

        btn_TimPX.setText("Tìm Kiếm");
        btn_TimPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimPXActionPerformed(evt);
            }
        });

        btn_ThemPX.setText("Thêm");
        btn_ThemPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemPXActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 34)); // NOI18N
        jLabel2.setText("Phiếu Xuất ");

        btn_XoaPX.setText("Xóa");
        btn_XoaPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaPXActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Ngày Xuất :");

        btn_XemCT1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_XemCT1.setText("Xem Chi Tiết");
        btn_XemCT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XemCT1ActionPerformed(evt);
            }
        });

        btn_Lammoi1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_Lammoi1.setText("Làm Mới");
        btn_Lammoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Lammoi1ActionPerformed(evt);
            }
        });

        btn_XuatExcel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_XuatExcel1.setText("Xuất Excel");
        btn_XuatExcel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatExcel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_NgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_MaKho, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_MaPhongBan))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btn_ThemPX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_XoaPX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_SuaPX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_TimPX, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_TimKiemPX, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 70, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_Lammoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_XemCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_XuatExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimPX, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TimKiemPX, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txt_NgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_MaKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_MaPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_ThemPX, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_XoaPX, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_SuaPX, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Lammoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XemCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XuatExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Phiếu Xuất", jPanel1);

        btn_ThemCTPX.setText("Thêm");
        btn_ThemCTPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemCTPXActionPerformed(evt);
            }
        });

        btn_XoaCTPX.setText("Xóa");
        btn_XoaCTPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaCTPXActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel6.setText("Phiếu Xuất Chi Tiết");

        btn_SuaCTPX.setText("Sửa");
        btn_SuaCTPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaCTPXActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Mã Phiếu Xuất :");

        btn_Lammoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_Lammoi.setText("Làm Mới");
        btn_Lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LammoiActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Mã Vật Tư :");

        btn_XemCT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_XemCT.setText("Xem Chi Tiết");
        btn_XemCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XemCTActionPerformed(evt);
            }
        });

        btn_TimCTPX.setText("Tìm Kiếm");
        btn_TimCTPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimCTPXActionPerformed(evt);
            }
        });

        btn_XuatExcel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_XuatExcel.setText("Xuất Excel");
        btn_XuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatExcelActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Số Lượng :");

        tbl_ChiTietPhieuXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Phiếu Xuất", "Mã Vật Tư", "Số Lượng"
            }
        ));
        tbl_ChiTietPhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ChiTietPhieuXuatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_ChiTietPhieuXuat);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_ThemCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_XoaCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_SuaCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_MaPhieuXuatCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_MaVatTu))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_SoLuong))))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_TimCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_TimKiemCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_Lammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_XemCT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_XuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_TimCTPX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_TimKiemCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_MaPhieuXuatCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_MaVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_ThemCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_XoaCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_SuaCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Lammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XemCT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chi Tiết Phiếu Xuất", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SuaPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaPXActionPerformed
        //updatePhieuXuat();
    }//GEN-LAST:event_btn_SuaPXActionPerformed

    private void tbl_PhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PhieuXuatMouseClicked
        //clickHerePhieuXuat();
    }//GEN-LAST:event_tbl_PhieuXuatMouseClicked

    private void btn_TimPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimPXActionPerformed
//        try {
//            searchPhieuXuat();
//        } catch (ParseException ex) {
//            Logger.getLogger(pnPhieuXuat.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btn_TimPXActionPerformed

    private void btn_ThemPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemPXActionPerformed
        //addPhieuXuat();
    }//GEN-LAST:event_btn_ThemPXActionPerformed

    private void btn_XoaPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaPXActionPerformed
       // deletePhieuXuat();
    }//GEN-LAST:event_btn_XoaPXActionPerformed

    private void btn_ThemCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemCTPXActionPerformed
        //addChiTietPhieuXuat();
    }//GEN-LAST:event_btn_ThemCTPXActionPerformed

    private void btn_XoaCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaCTPXActionPerformed
        //deleteChiTietPhieuXuat();
    }//GEN-LAST:event_btn_XoaCTPXActionPerformed

    private void btn_SuaCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaCTPXActionPerformed
        //updateChiTietPhieuXuat();
    }//GEN-LAST:event_btn_SuaCTPXActionPerformed

    private void btn_LammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LammoiActionPerformed
//        fillToTableChiTietPhieuXuat();
//        fillToTablePhieuXuat();
//        txt_TimKiemCTPX.setText("");
//        txt_TimKiemPX.setText("");
//        txt_NgayXuat.setDate(null);
//        txt_MaKho.setText("");
//        txt_MaPhongBan.setText("");
//        txt_MaPhieuXuatCTPX.setText("");
//        txt_MaVatTu.setText("");
//        txt_SoLuong.setText("");
    }//GEN-LAST:event_btn_LammoiActionPerformed

    private void btn_XemCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XemCTActionPerformed
        //viewChiTietPhieuXuatInPX();
    }//GEN-LAST:event_btn_XemCTActionPerformed

    private void btn_TimCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimCTPXActionPerformed
//        try {
//            searchChiTietPhieuXuat();
//        } catch (ParseException ex) {
//            Logger.getLogger(pnPhieuXuat.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btn_TimCTPXActionPerformed

    private void btn_XuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatExcelActionPerformed
        //exportToExcel();
    }//GEN-LAST:event_btn_XuatExcelActionPerformed

    private void tbl_ChiTietPhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ChiTietPhieuXuatMouseClicked
        //clickHereChiTietPhieuXuat();
    }//GEN-LAST:event_tbl_ChiTietPhieuXuatMouseClicked

    private void btn_XemCT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XemCT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_XemCT1ActionPerformed

    private void btn_Lammoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Lammoi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Lammoi1ActionPerformed

    private void btn_XuatExcel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatExcel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_XuatExcel1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Lammoi;
    private javax.swing.JButton btn_Lammoi1;
    private javax.swing.JButton btn_SuaCTPX;
    private javax.swing.JButton btn_SuaPX;
    private javax.swing.JButton btn_ThemCTPX;
    private javax.swing.JButton btn_ThemPX;
    private javax.swing.JButton btn_TimCTPX;
    private javax.swing.JButton btn_TimPX;
    private javax.swing.JButton btn_XemCT;
    private javax.swing.JButton btn_XemCT1;
    private javax.swing.JButton btn_XoaCTPX;
    private javax.swing.JButton btn_XoaPX;
    private javax.swing.JButton btn_XuatExcel;
    private javax.swing.JButton btn_XuatExcel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbl_ChiTietPhieuXuat;
    private javax.swing.JTable tbl_PhieuXuat;
    private javax.swing.JTextField txt_MaKho;
    private javax.swing.JTextField txt_MaPhieuXuatCTPX;
    private javax.swing.JTextField txt_MaPhongBan;
    private javax.swing.JTextField txt_MaVatTu;
    private com.toedter.calendar.JDateChooser txt_NgayXuat;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextField txt_TimKiemCTPX;
    private javax.swing.JTextField txt_TimKiemPX;
    // End of variables declaration//GEN-END:variables
}
