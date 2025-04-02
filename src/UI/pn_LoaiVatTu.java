
package UI;

import DAO.DAO_LoaiVatTu;
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
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.Message;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.model_LoaiVatTu;

public class pn_LoaiVatTu extends javax.swing.JPanel {
    
    public DefaultTableModel tbl_ModelLoaiVatTu;
    private DAO_LoaiVatTu lvtdao = new DAO_LoaiVatTu();
    private DefaultComboBoxModel cbx_ModelThanhTimKiem;
    private List<model_LoaiVatTu> list_LoaiVatTu = new ArrayList<model_LoaiVatTu>();

    private int row = -1;
    private boolean suDungTimKiem = false;

    private String selectedTenVT = "";  // Biến để lấy dữ liệu dòng
    private String selectedmaLoaiVatTu = "";  // Biến để lấy dữ liệu dòng

    private List<String> danhSachThongBao = new ArrayList<>();
    private int soLuongThongBao = 0;
    private final String FILE_NAME = "notifications.txt";
    private final int NGAY_HET_HAN = 7; // Xóa thông báo cũ sau 7 ngày

    public pn_LoaiVatTu() {
        initComponents();
        btn_notification.addActionListener(e -> hienThiThongBao());
        this.taiThongBaoTuFile();
        addSearchListener();
        tbl_ModelLoaiVatTu = (DefaultTableModel) tbl_LoaiVatTu.getModel();
        cbx_ThanhTimKiem.setModel(new DefaultComboBoxModel<>(new String[]{
            " Mã Loại Vật Tư","Tên Loại Vật Tư", "Tất Cả"
        }));
        fillToTableLoaiVatTu();
        styleUI();
    }
// hiên thông báo 
        public static void showNotification(String message, boolean isError) { // Thông báo trượt xuống
        JWindow window = new JWindow();
        window.setLayout(new BorderLayout());

        // Panel custom với góc bo tròn
        DiaLog_VatTu.RoundedPanel panel = new DiaLog_VatTu.RoundedPanel(20);
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
// chỉnh sửa giao diện form 
        public void styleUI() {
        //STYLE JTable:
        tbl_LoaiVatTu.putClientProperty(FlatClientProperties.STYLE, ""
                + "showVerticalLines:true;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:3,3;"
                + "selectionBackground:#A5D6A7;"
                + "selectionForeground:#000000;"
                + "gridColor:#BDBDBD;"
                + "border:1,1,1,1,#9E9E9E;"
                + "rowHeight:30;"
        );

        tbl_LoaiVatTu.setSelectionBackground(new Color(165, 214, 167));
        tbl_LoaiVatTu.setSelectionForeground(Color.BLACK);

        //STYLE JPanel:
        pn_chucnang.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: #FFFFFF;" // Màu nền xám nhạt (giống giao diện hiện đại)
                + "arc: 15;"); // Bo tròn góc 15px

        //STYLE JTextField:
        txt_TimKiem.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "innerFocusWidth:0;");

        //STYLE JButton:
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
//        btn_thongKe.putClientProperty(FlatClientProperties.STYLE, ""
//                + "arc:999;" // Bo tròn nút
//                + "borderWidth:0;" // Không viền
//                + "focusWidth:1;" // Không viền khi focus
//                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
//                + "foreground:#000000;" // Màu chữ mặc định (đen)
//                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
//                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
//                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
//                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
        cbx_ThanhTimKiem.putClientProperty(FlatClientProperties.STYLE, ""
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
        
        // Hiển thị danh sách vật tư lên bảng
    public void fillToTableLoaiVatTu() {
        try {
            // Xóa toàn bộ dữ liệu cũ trước khi thêm mới
            tbl_ModelLoaiVatTu.setRowCount(0);

            // Lấy danh sách vật tư từ database
            list_LoaiVatTu = lvtdao.selectAll();
            if (list_LoaiVatTu != null) {
                for (model_LoaiVatTu vt : list_LoaiVatTu) {
                    tbl_ModelLoaiVatTu.addRow(new Object[]{
                        vt.getMaLoaiVatTu(), // Chỉ lấy Mã Vật Tư
                        vt.getTenLoaiVatTu(), // Chỉ lấy Tên Vật Tư
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi để dễ debug
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn dữ liệu: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public String layTatCaThongTin() {
        List<model_LoaiVatTu> list_LoaiVatTu = lvtdao.selectAll();
        return "Thông Tin: " + (row + 1) + " trên tổng số " + list_LoaiVatTu.size();
    }

    public String layThongTinTimKiem(List<model_LoaiVatTu> list_VatTu) {
        return "Thông Tin: " + (row + 1) + " trên tổng số " + list_VatTu.size();
    }


   private void timKiemLoaiVatTu() {
    String cbx_Columns = String.valueOf(cbx_ThanhTimKiem.getSelectedItem());
    String txt_keyWord = txt_TimKiem.getText().trim();

    if (txt_keyWord.isEmpty()) {
        tbl_ModelLoaiVatTu.setRowCount(0); // Nếu ô tìm kiếm trống, xóa bảng
        return;
    }

    List<model_LoaiVatTu> list_LoaiVatTu = lvtdao.selectByKeyWord(txt_keyWord);
    tbl_ModelLoaiVatTu.setRowCount(0); // Xóa dữ liệu cũ

    if (list_LoaiVatTu.isEmpty()) {
        showNotification("Không tìm thấy vật tư!", true);
    } else {
        for (model_LoaiVatTu lvt : list_LoaiVatTu) {
            tbl_ModelLoaiVatTu.addRow(new Object[]{
                lvt.getMaLoaiVatTu(),  // Chỉ hiển thị Mã Vật Tư
                lvt.getMaLoaiVatTu() // Chỉ hiển thị Mã Loại Vật Tư
            });
        }
    }
    suDungTimKiem = true;
}
    
     private Timer searchTimer;  // Biến cho hàm addSearchListener

    private void addSearchListener() {  // Hàm tìm kiếm tự động chờ 500ms sau khi nhập
        searchTimer = new Timer(500, e -> timKiemLoaiVatTu()); // Đợi 500ms trước khi tìm kiếm
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

    
    
        public void themThongBao(String hanhDong, String maPB) {
    String thoiGian = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date());
    String thongBao = "📢 " + thoiGian + " - " + hanhDong + " Loai Vat Tu : " + maPB; 

    danhSachThongBao.add(0, thongBao); // Thêm vào đầu danh sách
    soLuongThongBao = (int) danhSachThongBao.stream().filter(tb -> tb.startsWith("📢")).count();

    capNhatChuong();
    luuThongBaoVaoFile();
}

    private void luuThongBaoVaoFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String thongBao : danhSachThongBao) {
                writer.write(thongBao);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu thông báo: " + e.getMessage());
        }
    }

    private void taiThongBaoTuFile() {
        danhSachThongBao.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!kiemTraHetHan(line)) { // Chỉ thêm thông báo chưa hết hạn
                    danhSachThongBao.add(line);
                }
            }
            soLuongThongBao = danhSachThongBao.size();
            btn_notification.setText(soLuongThongBao > 0 ? "(" + soLuongThongBao + ")" : "🔔");

            // Cập nhật lại file sau khi xóa thông báo cũ
            luuThongBaoVaoFile();
        } catch (IOException e) {
            System.err.println("Không tìm thấy file thông báo, có thể là lần chạy đầu tiên.");
        }
    }

    private boolean kiemTraHetHan(String thongBao) {
        try {
            String thoiGian = thongBao.split(" - ")[0]; // Lấy phần thời gian
            Date ngayThongBao = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").parse(thoiGian);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -NGAY_HET_HAN); // Trừ 7 ngày
            return ngayThongBao.before(cal.getTime()); // Nếu thông báo trước ngày này thì đã hết hạn
        } catch (Exception e) {
            System.err.println("Lỗi khi kiểm tra hạn thông báo: " + e.getMessage());
            return false;
        }
    }

private void hienThiThongBao() {
    JPopupMenu popup = new JPopupMenu();
    
    if (danhSachThongBao.isEmpty()) {
        popup.add(new JMenuItem("Không có thông báo"));
    } else {
        for (int i = 0; i < danhSachThongBao.size(); i++) {
            String thongBao = danhSachThongBao.get(i);
            boolean chuaDoc = thongBao.startsWith("📢 "); // Kiểm tra nếu chưa đọc

            JMenuItem item = new JMenuItem(thongBao);
            final int index = i;

            // Xử lý khi click vào thông báo
            item.addActionListener(e -> {
                if (chuaDoc) {
                    danhSachThongBao.set(index, thongBao.substring(2)); // Xóa ký hiệu 📢
                    soLuongThongBao = (int) danhSachThongBao.stream().filter(tb -> tb.startsWith("📢")).count();
                    capNhatChuong();
                    luuThongBaoVaoFile();
                }
            });

            popup.add(item);
        }
    }

    popup.show(btn_notification, btn_notification.getWidth() / 2, btn_notification.getHeight());
}

// Cập nhật số lượng trên chuông
private void capNhatChuong() {
    btn_notification.setText(soLuongThongBao > 0 ? "(" + soLuongThongBao + ")" : "🔔"); // Khi về 0 chỉ còn 🔔
}

// delete ' update 

    // Xóa vật tư
    private void deleteVatTu() {
        int[] selectedRows = tbl_LoaiVatTu.getSelectedRows(); // Lấy tất cả các dòng được chọn

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
                String maVatTu = tbl_LoaiVatTu.getValueAt(row, 0).toString();
                lvtdao.delete(maVatTu); // Xóa từng vật tư
                danhSachXoa.add(maVatTu); // Thêm vào danh sách để ghi nhận thông báo
            }

            fillToTableLoaiVatTu(); // Cập nhật lại bảng sau khi xóa
            showNotification("Đã xóa " + selectedRows.length + " vật tư!", false);

            // 🔔 Cập nhật thông báo chuông sau khi hoàn tất tất cả các xóa
            for (String maVatTu : danhSachXoa) {
                themThongBao("Xóa", maVatTu);
            }

        } catch (Exception e) {
            showNotification("Không thể xóa vật tư!", true);
        }
    }

    // Cập nhật vật tư
    private void updateVatTu() {
        int row = tbl_LoaiVatTu.getSelectedRow();
        if (row < 0) {
            showNotification("Chọn một dòng để cập nhật!", true);
            return;
        }

        // Lấy dữ liệu từ JTable chỉ với 3 cột
        String tenLoaiVT = tbl_LoaiVatTu.getValueAt(row, 1).toString().trim();
        String maLoaiVT = tbl_LoaiVatTu.getValueAt(row, 2).toString().trim();

        // Kiểm tra nếu có ô nào bị bỏ trống
        if (tenLoaiVT.isEmpty() || maLoaiVT.isEmpty()) {
            showNotification("Vui lòng nhập đầy đủ thông tin!", true);
            return;
        }

        // Tạo đối tượng Vật Tư mới
        model_LoaiVatTu lvt = new model_LoaiVatTu();
        lvt.setMaLoaiVatTu(maLoaiVT);
        lvt.setTenLoaiVatTu(tenLoaiVT);

        // Xác nhận cập nhật
        boolean confirm = Message.confirm("Bạn có chắc chắn muốn cập nhật vật tư có mã '" + maLoaiVT + "'?");
        if (confirm) {
            try {
                lvtdao.update(lvt); // Cập nhật vào CSDL
                fillToTableLoaiVatTu(); // Cập nhật lại bảng để hiển thị dữ liệu mới
                showNotification("Cập nhật vật tư thành công!", false);

                // 🔔 Ghi nhận thông báo vào hệ thống chuông
                themThongBao("Cập nhật", tenLoaiVT);

            } catch (Exception e) {
                Message.error("Lỗi: " + e.getMessage());
                showNotification("Cập nhật vật tư thất bại!", true);
            }
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_chucnang = new javax.swing.JPanel();
        btn_Them = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_TimKiem = new javax.swing.JButton();
        txt_TimKiem = new javax.swing.JTextField();
        btn_notification = new javax.swing.JButton();
        cbx_ThanhTimKiem = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_LoaiVatTu = new javax.swing.JTable();

        pn_chucnang.setBackground(new java.awt.Color(255, 255, 255));

        btn_Them.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_Them.setText("Thêm");

        btn_Xoa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_Xoa.setText("Xóa");

        btn_Sua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_Sua.setText("Sửa");

        btn_TimKiem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_TimKiem.setText("Tìm Kiếm");

        txt_TimKiem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btn_notification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bell.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/lammoi.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Loại Vật Tư ");

        javax.swing.GroupLayout pn_chucnangLayout = new javax.swing.GroupLayout(pn_chucnang);
        pn_chucnang.setLayout(pn_chucnangLayout);
        pn_chucnangLayout.setHorizontalGroup(
            pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_chucnangLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_notification, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pn_chucnangLayout.createSequentialGroup()
                .addComponent(btn_TimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbx_ThanhTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        pn_chucnangLayout.setVerticalGroup(
            pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_chucnangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_chucnangLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_notification))
                .addGap(51, 51, 51)
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pn_chucnangLayout.createSequentialGroup()
                            .addComponent(btn_Them)
                            .addGap(0, 6, Short.MAX_VALUE))
                        .addComponent(btn_Xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbx_ThanhTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );

        tbl_LoaiVatTu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã Loại Vật Tư", "Tên Loại Vật Tư"
            }
        ));
        jScrollPane1.setViewportView(tbl_LoaiVatTu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_chucnang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_chucnang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        fillToTableLoaiVatTu();
    }//GEN-LAST:event_jLabel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JButton btn_notification;
    private javax.swing.JComboBox<String> cbx_ThanhTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pn_chucnang;
    private javax.swing.JTable tbl_LoaiVatTu;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
