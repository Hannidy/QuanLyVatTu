package UI;

import DAO.DAO_Kho;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.model_Kho;
import util.Message;
import static UI.pnVatTu.showNotification;

    public class pnKho extends javax.swing.JPanel {

    private DefaultTableModel tbl_ModelKho;
    private DAO_Kho kDAO = new DAO_Kho();
    private List<model_Kho> list_Kho = new ArrayList<model_Kho>();

    private String selectedtenKho = "";  // Biến để lấy dữ liệu dòng
    private String selectedmaLoaiVatTu = "";  // Biến để lấy dữ liệu dòng
    private String selecteddiaChi = "";     // Biến để lấy dữ liệu dòng
    
     private ResourceBundle bundle;

    public pnKho() {
        initComponents();
        btn_notification.addActionListener(e -> hienThiThongBao());
        this.taiThongBaoTuFile();
        tbl_ModelKho = (DefaultTableModel) tbl_Kho.getModel();
        fillToTableKho();
        styleCode();
        
    }
  
    public void updateTexts() {
        if (bundle != null){
            System.out.println("🔄 Updating pnKho");
            lbl_Kho.setText(bundle.getString("lbl_Kho"));
            btn_TimKiem.setText(bundle.getString("btn_TimKiem"));
            btn_Them.setText(bundle.getString("btn_Them"));
            btn_Xoa.setText(bundle.getString("btn_Xoa"));
            btn_Sua.setText(bundle.getString("btn_Sua"));
        }else {
            System.out.println("⚠️ ResourceBundle is NULL in pnKho!");
        }
        
    }

    public static void showNotification(String message, boolean isError) {  // Thông báo trượt xuống
        JWindow window = new JWindow();
        window.setLayout(new BorderLayout());

        // Panel custom với góc bo tròn
        DiaLog_Kho.RoundedPanel panel = new DiaLog_Kho.RoundedPanel(20);
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

    public void styleCode() {
        txt_TimKiem.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "innerFocusWidth:0;");
        btn_Them.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "background:#CBE5AE; "
                + "innerFocusWidth:0");
        btn_Sua.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "background:#CBE5AE; "
                + "innerFocusWidth:0");
        btn_Xoa.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "background:#CBE5AE; "
                + "innerFocusWidth:0");
        btn_TimKiem.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "background:#CBE5AE; "
                + "innerFocusWidth:0");
        btn_notification.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "background:#CBE5AE; "
                + "innerFocusWidth:0");
//        btn_LamMoi.putClientProperty(FlatClientProperties.STYLE, ""
//                + "arc:999;"
//                + "borderWidth:0;"
//                + "focusWidth:0;"
//                + "background:#CBE5AE; "
//                + "innerFocusWidth:0");

        tbl_Kho.putClientProperty(FlatClientProperties.STYLE, ""
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

    // Hiển thị danh sách kho lên bảng
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

//        public void setForm(Kho k) {
//            
//        txt_TenKho.setText(k.getTenKho());
//        txt_DiaChi.setText(k.getDiaChi());
//        
//    }
    // Click vào bảng để lấy dữ liệu
//    public void clickHereKho() {
//        int row = tbl_Kho.getSelectedRow();
//        if (row > -1) {
//            try {
//                txt_TenKho.setText(tbl_Kho.getValueAt(row, 1).toString());
//                txt_DiaChi.setText(tbl_Kho.getValueAt(row, 2).toString());
//            } catch (Exception e) {
//                Message.error("Lỗi khi chuyển đổi dữ liệu: " + e.getMessage());
//            }
//        } else {
//            Message.alert("Vui lòng chọn một dòng trong bảng!");
//        }
//    }
    // Thêm kho mới
//    private void addKho() {
//        if (txt_TenKho.getText().isEmpty()
//                || txt_DiaChi.getText().isEmpty()) {
//            Message.alert("Vui lòng nhập đầy đủ thông tin!");
//            return;
//        }
//
//        model_Kho k = new model_Kho();
//        k.setTenKho(txt_TenKho.getText().trim());
//        k.setDiaChi(txt_DiaChi.getText().trim()); // Sửa lỗi convert
//
//        try {
//            kDAO.insert(k);
//            fillToTableKho();
//            Message.alert("Thêm kho thành công!");
//        } catch (Exception e) {
//            Message.error("Lỗi: " + e.getMessage());
//            Message.alert("Thêm kho thất bại!");
//        }
//    }
    // Xóa kho
    public void deleteKho() {
        int[] selectedRows = tbl_Kho.getSelectedRows(); // Lấy tất cả các dòng được chọn

        if (selectedRows.length == 0) {
            showNotification("Chọn ít nhất một dòng để xóa!", true);
            return;
        }

        boolean confirm = Message.confirm("Bạn có chắc chắn muốn xóa " + selectedRows.length + " vật tư?");
        if (!confirm) {
            return;
        }

        try {
            List<String> danhSachXoa = new ArrayList<>(); // Lưu các vật tư bị xóa để ghi vào thông báo

            for (int i = selectedRows.length - 1; i >= 0; i--) { // Xóa từ dưới lên để tránh lỗi chỉ số
                int row = selectedRows[i];
                String maVatTu = tbl_Kho.getValueAt(row, 0).toString();
                kDAO.delete(maVatTu); // Xóa từng vật tư
                danhSachXoa.add(maVatTu); // Thêm vào danh sách để ghi nhận thông báo
            }

            fillToTableKho(); // Cập nhật lại bảng sau khi xóa
            //thongke();
            showNotification("Đã xóa " + selectedRows.length + " vật tư!", false);

            // 🔔 Cập nhật thông báo chuông sau khi hoàn tất tất cả các xóa
            for (String maKho : danhSachXoa) {
                themThongBao("Xóa", maKho);
            }

        } catch (Exception e) {
            showNotification("Không thể xóa vật tư!", true);
        }
    }

    // Cập nhật kho
    private void updateKho() {
        int row = tbl_Kho.getSelectedRow();
        if (row < 0) {
            showNotification("Chọn một dòng để cập nhật!", true);
            return;
        }

        // Lấy dữ liệu từ JTable
        String maKho = tbl_Kho.getValueAt(row, 0).toString();
        String tenKho = tbl_Kho.getValueAt(row, 1).toString();
        String maLoaiVT = tbl_Kho.getValueAt(row, 2).toString();
        String diaChi = tbl_Kho.getValueAt(row, 3).toString();

        // Kiểm tra nếu có ô nào bị bỏ trống
        if (tenKho.isEmpty() || maLoaiVT.isEmpty() || diaChi.isEmpty()) {
            showNotification("Vui lòng nhập đầy đủ thông tin!", true);
            return;
        }

        // Tạo đối tượng Kho mới
        model_Kho k = new model_Kho();
        k.setMaKho(maKho);
        k.setTenKho(tenKho);
        k.setMaLoaiVatTu(maLoaiVT);
        k.setDiachi(diaChi);

        // Xác nhận cập nhật
        boolean confirm = Message.confirm("Bạn có chắc chắn muốn cập nhật kho có mã '" + maKho + "'?");
        if (confirm) {
            try {
                kDAO.update(k); // Cập nhật vào CSDL
                fillToTableKho(); // Cập nhật lại bảng để hiển thị dữ liệu mới
                showNotification("Cập nhật kho thành công!", false);

                // 🔔 Ghi nhận thông báo vào hệ thống chuông
                themThongBao("Cập nhật", tenKho);

            } catch (Exception e) {
                Message.error("Lỗi: " + e.getMessage());
                showNotification("Cập nhật kho thất bại!", true);
            }
        }
    }

    // Tìm kiếm kho
    private void searchKho() {
        String maKho = txt_TimKiem.getText().trim();
        if (maKho.isEmpty()) {
            Message.alert("Vui lòng nhập từ khóa để tìm kiếm!");
            return;
        }

        list_Kho = kDAO.selectById(maKho);
        tbl_ModelKho.setRowCount(0); // Xóa dữ liệu cũ
        if (list_Kho.isEmpty()) {
            Message.alert("Không tìm thấy kho!");
        } else {
            for (model_Kho k : list_Kho) {
                tbl_ModelKho.addRow(
                        new Object[]{
                            k.getMaKho(),
                            k.getTenKho(),
                            k.getMaLoaiVatTu(),
                            k.getDiachi()
                        }
                );
            }
        }
    }

    private List<String> danhSachThongBao = new ArrayList<>(); // Danh sách chứa nội dung thông báo
    private int soLuongThongBao = 0; // Số lượng thông báo chưa đọc

    public void themThongBao(String hanhDong, String maKho) {
        String thoiGian = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date());
        String thongBao = thoiGian + " - " + hanhDong + " Kho: " + maKho;

        danhSachThongBao.add(0, thongBao); // Thêm vào đầu danh sách
        soLuongThongBao = danhSachThongBao.size(); // Cập nhật số lượng thông báo

        // Cập nhật số lượng trên chuông 🔔
        btn_notification.setText("(" + soLuongThongBao + ")");

        // Lưu vào file để không bị mất khi thoát chương trình
        luuThongBaoVaoFile();
    }

    private void luuThongBaoVaoFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("notifications.txt", true))) { // Chế độ append
            writer.write(danhSachThongBao.get(0)); // Chỉ ghi thông báo mới nhất vào cuối file
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu thông báo: " + e.getMessage());
        }
    }

    private void taiThongBaoTuFile() {
        danhSachThongBao.clear(); // Xóa danh sách cũ trước khi tải
        try (BufferedReader reader = new BufferedReader(new FileReader("notifications.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                danhSachThongBao.add(line);
            }
            soLuongThongBao = danhSachThongBao.size();
            btn_notification.setText(soLuongThongBao > 0 ? "(" + soLuongThongBao + ")" : "🔔");
        } catch (IOException e) {
            System.err.println("Không tìm thấy file thông báo, có thể là lần chạy đầu tiên.");
        }
    }

    private void hienThiThongBao() {
        JPopupMenu popup = new JPopupMenu();
        if (danhSachThongBao.isEmpty()) {
            popup.add(new JMenuItem("Không có thông báo"));
        } else {
            for (String thongBao : danhSachThongBao) {
                popup.add(new JMenuItem(thongBao));
            }
        }
        popup.show(btn_notification, btn_notification.getWidth() / 2, btn_notification.getHeight());

        // Đặt lại số lượng thông báo về 0 khi người dùng bấm vào chuông
        soLuongThongBao = 0;
    }

    private Timer searchTimer;  // Biến cho hàm addSearchListener

    public void addSearchListener() {  // Hàm tìm kiếm tự động chờ 500ms sau khi nhập
        searchTimer = new Timer(500, e -> searchKho()); // Đợi 500ms trước khi tìm kiếm
        searchTimer.setRepeats(false); // Chỉ chạy một lần sau mỗi lần nhập

        txt_TimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchTimer.restart(); // Mỗi lần nhập ký tự, reset timer
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchTimer.restart(); // Khi xóa ký tự, cũng reset timer
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchTimer.restart();
            }
        });
    }

//    private void refreshForm(){
//        fillToTableKho();
//        txt_TenKho.setText("");
//        txt_DiaChi.setText("");
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_chucnang = new javax.swing.JPanel();
        btn_Them = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_TimKiem = new javax.swing.JButton();
        txt_TimKiem = new javax.swing.JTextField();
        lbl_Kho = new javax.swing.JLabel();
        btn_notification = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Kho = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

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

        lbl_Kho.setBackground(new java.awt.Color(191, 239, 255));
        lbl_Kho.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lbl_Kho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Kho.setText("Kho");

        btn_notification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bell.png"))); // NOI18N

        javax.swing.GroupLayout pn_chucnangLayout = new javax.swing.GroupLayout(pn_chucnang);
        pn_chucnang.setLayout(pn_chucnangLayout);
        pn_chucnangLayout.setHorizontalGroup(
            pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_chucnangLayout.createSequentialGroup()
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_chucnangLayout.createSequentialGroup()
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 358, Short.MAX_VALUE)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_chucnangLayout.createSequentialGroup()
                        .addComponent(lbl_Kho)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_chucnangLayout.createSequentialGroup()
                        .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_notification, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        pn_chucnangLayout.setVerticalGroup(
            pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_chucnangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Kho)
                    .addComponent(btn_notification))
                .addGap(61, 61, 61)
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_Kho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Kho", "Tên Kho", "Mã Loại Vật Tư", "Địa Chỉ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_Kho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KhoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Kho);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_chucnang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_chucnang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        //addKho();
        DiaLog_Kho dialogKho = new DiaLog_Kho(null, true, this);
        dialogKho.setData(selectedtenKho, selectedmaLoaiVatTu, selecteddiaChi);
        dialogKho.setVisible(true);
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        deleteKho();
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        updateKho();
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        searchKho();
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void tbl_KhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhoMouseClicked
        // clickHereKho();
        int selectedRow = tbl_Kho.getSelectedRow(); // Lấy dòng đang chọn

        if (selectedRow != -1) { // Kiểm tra có dòng được chọn không
            String tenKho = tbl_Kho.getValueAt(selectedRow, 1).toString();
            String maLoaiVatTu = tbl_Kho.getValueAt(selectedRow, 2).toString();
            String diaChi = tbl_Kho.getValueAt(selectedRow, 3).toString();

            // Lưu vào biến toàn cục để truyền vào JDialo
            selectedtenKho = tenKho;
            selectedmaLoaiVatTu = maLoaiVatTu;
            selecteddiaChi = diaChi;
        }
    }//GEN-LAST:event_tbl_KhoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JButton btn_notification;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Kho;
    private javax.swing.JPanel pn_chucnang;
    private javax.swing.JTable tbl_Kho;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
