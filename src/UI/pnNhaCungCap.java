package UI;

import DAO.DAO_NhaCungCap;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import model.model_NhaCungCap;
import util.Message;

public class pnNhaCungCap extends javax.swing.JPanel {

    private DefaultTableModel tbl_ModelNhaCungCap;
    private DAO_NhaCungCap nccdao = new DAO_NhaCungCap();
    private List<model_NhaCungCap> list_NhaCungCap = new ArrayList<model_NhaCungCap>();

    private String selectedtenNCC = "";
    private String selectedSDT = "";
    private String selectedemail = "";
    private String selecteddiachi = "";
    
    private List<String> danhSachThongBao = new ArrayList<>();
    private int soLuongThongBao = 0;
    private final String FILE_NAME = "notifications.txt";
    private final int NGAY_HET_HAN = 7; // Xóa thông báo cũ sau 7 ngày

    public pnNhaCungCap() {
        initComponents();
        btn_notification.addActionListener(e -> hienThiThongBao());
        addSearchListener();
        this.taiThongBaoTuFile();
        tbl_ModelNhaCungCap = (DefaultTableModel) tbl_NhaCungCap.getModel();
        fillToTableNhaCungCap();
        txtcode();
        styleCode();

    }

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
        window.setSize(270, 60);

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

    void txtcode() {
        txt_TimKiem.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "innerFocusWidth:0;");
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
//        btn_LamMoi.putClientProperty(FlatClientProperties.STYLE, ""
//                + "arc:999;" // Bo tròn nút
//                + "borderWidth:0;" // Không viền
//                + "focusWidth:1;" // Không viền khi focus
//                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
//                + "foreground:#000000;" // Màu chữ mặc định (đen)
//                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
//                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
//                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
//                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
    }

    void styleCode() {
        txt_TimKiem.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "innerFocusWidth:0;");
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
//        btn_LamMoi.putClientProperty(FlatClientProperties.STYLE, ""
//                + "arc:999;" // Bo tròn nút
//                + "borderWidth:0;" // Không viền
//                + "focusWidth:1;" // Không viền khi focus
//                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
//                + "foreground:#000000;" // Màu chữ mặc định (đen)
//                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
//                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
//                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
//                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
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

        tbl_NhaCungCap.putClientProperty(FlatClientProperties.STYLE, ""
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
    // Hiển thị danh sách vật tư lên bảng

    public void fillToTableNhaCungCap() {
        try {
            // Xóa toàn bộ dữ liệu cũ trước khi thêm mới
            tbl_ModelNhaCungCap.setRowCount(0);

            // Lấy danh sách nhà cung cấp từ database
            List<model_NhaCungCap> list_NhaCungCap = nccdao.selectAll();
            if (list_NhaCungCap != null) {
                for (model_NhaCungCap ncc : list_NhaCungCap) {
                    tbl_ModelNhaCungCap.addRow(new Object[]{
                        ncc.getMaNhaCungCap(),
                        ncc.getTenNhaCungCap(),
                        ncc.getSoDienThoai(),
                        ncc.getEmail(),
                        ncc.getDiaChi()
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để dễ debug
            showNotification("Lỗi truy vấn dữ liệu: ", true);
        }
    }



    // Xóa nhà cung cấp
    private void deleteNhaCungCap() {
        try {
            // Lấy chỉ số dòng đang chọn
            int row = tbl_NhaCungCap.getSelectedRow();
            if (row < 0) {
                showNotification("Bạn phải chọn một dòng để xóa!", true);
                return;
            }

            // Lấy mã nhà cung cấp từ bảng
            String maNhaCungCap = tbl_NhaCungCap.getValueAt(row, 0).toString();

            // Hiển thị hộp thoại xác nhận
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa nhà cung cấp này?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Thực hiện xóa
                nccdao.delete(maNhaCungCap);
                fillToTableNhaCungCap();
                showNotification("Xóa nhà cung cấp thành công!", false);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để debug dễ hơn
            showNotification("Lỗi khi xóa nhà cung cấp: ", true);
        }
    }

    // Cập nhật nhà cung cấp
    private void updateNhaCungCap() {
        int row = tbl_NhaCungCap.getSelectedRow();
        if (row < 0) {
            showNotification("Chọn một dòng để cập nhật!", true);
            return;
        }

        // Lấy dữ liệu từ JTable với 5 cột
        String maNCC = tbl_NhaCungCap.getValueAt(row, 0).toString();
        String tenNCC = tbl_NhaCungCap.getValueAt(row, 1).toString().trim();
        String soDienThoai = tbl_NhaCungCap.getValueAt(row, 2).toString().trim();
        String email = tbl_NhaCungCap.getValueAt(row, 3).toString().trim();
        String diaChi = tbl_NhaCungCap.getValueAt(row, 4).toString().trim();

        // Kiểm tra nếu có ô nào bị bỏ trống
        if (tenNCC.isEmpty() || soDienThoai.isEmpty() || email.isEmpty() || diaChi.isEmpty()) {
            showNotification("Vui lòng nhập đầy đủ thông tin!", true);
            return;
        }

        // Kiểm tra định dạng email
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showNotification("Email không hợp lệ!", true);
            return;
        }

        // Kiểm tra số điện thoại chỉ chứa số
        if (!soDienThoai.matches("\\d+")) {
            showNotification("Số điện thoại chỉ được chứa chữ số!", true);
            return;
        }

        // Xác nhận cập nhật
        boolean confirm = Message.confirm("Bạn có chắc chắn muốn cập nhật nhà cung cấp có mã '" + maNCC + "'?");
        if (confirm) {
            try {
                // Tạo đối tượng Nhà Cung Cấp mới
                model_NhaCungCap ncc = new model_NhaCungCap();
                ncc.setMaNhaCungCap(maNCC);
                ncc.setTenNhaCungCap(tenNCC);
                ncc.setSoDienThoai(soDienThoai);
                ncc.setEmail(email);
                ncc.setDiaChi(diaChi);

                // Cập nhật vào CSDL
                nccdao.update(ncc);
                fillToTableNhaCungCap(); // Cập nhật lại bảng để hiển thị dữ liệu mới
                showNotification("Cập nhật nhà cung cấp thành công!", false);

                // 🔔 Ghi nhận thông báo vào hệ thống chuông
                themThongBao("Cập nhật", tenNCC);

            } catch (Exception e) {
                Message.error("Lỗi: " + e.getMessage());
                showNotification("Cập nhật nhà cung cấp thất bại!", true);
            }
        }
    }

    // Tìm kiếm nhà cung cấp
    private void searchNhaCungCap() {
        String maNhaCungCap = txt_TimKiem.getText().trim();
        if (maNhaCungCap.isEmpty()) {
            Message.alert("Vui lòng nhập từ khóa để tìm kiếm!");
            return;
        }

        List<model_NhaCungCap> list_NhaCungCap = nccdao.selectById(maNhaCungCap);
        tbl_ModelNhaCungCap.setRowCount(0); // Xóa dữ liệu cũ
        if (list_NhaCungCap.isEmpty()) {
            Message.alert("Không tìm thấy nhà cung cấp!");
        } else {
            for (model_NhaCungCap ncc : list_NhaCungCap) {
                tbl_ModelNhaCungCap.addRow(
                        new Object[]{
                            ncc.getMaNhaCungCap(),
                            ncc.getTenNhaCungCap(),
                            ncc.getSoDienThoai(),
                            ncc.getEmail(),
                            ncc.getDiaChi()
                        }
                );
            }
        }
    }



    public void themThongBao(String hanhDong, String maPB) {
    String thoiGian = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date());
    String thongBao = "📢 " + thoiGian + " - " + hanhDong + " Nhà Cung Cấp : " + maPB; 

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

    private Timer searchTimer;  // Biến cho hàm addSearchListener

    private void addSearchListener() {  // Hàm tìm kiếm tự động chờ 500ms sau khi nhập
        searchTimer = new Timer(500, e -> searchNhaCungCap()); // Đợi 500ms trước khi tìm kiếm
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_notification = new javax.swing.JButton();
        pn_chucnang = new javax.swing.JPanel();
        btn_TimKiem = new javax.swing.JButton();
        txt_TimKiem = new javax.swing.JTextField();
        btn_Sua = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Them = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_NhaCungCap = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(191, 239, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nhà Cung Cấp");

        btn_notification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bell.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 621, Short.MAX_VALUE)
                .addComponent(btn_notification)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_notification)
                    .addComponent(jLabel1))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pn_chucnang.setBackground(new java.awt.Color(255, 255, 255));

        btn_TimKiem.setText("Tìm Kiếm");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_chucnangLayout = new javax.swing.GroupLayout(pn_chucnang);
        pn_chucnang.setLayout(pn_chucnangLayout);
        pn_chucnangLayout.setHorizontalGroup(
            pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_chucnangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pn_chucnangLayout.setVerticalGroup(
            pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_chucnangLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pn_chucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        tbl_NhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Số Điện Thoại", "E-mail", "Địa Chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_NhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_NhaCungCap);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pn_chucnang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_chucnang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        DiaLog_NhaCungCap dialogncc = new DiaLog_NhaCungCap(null, true, this);
        dialogncc.setData(selectedtenNCC, selectedSDT, selectedemail, selecteddiachi);
        dialogncc.setVisible(true);
        //addNhaCungCap();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        deleteNhaCungCap();
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        updateNhaCungCap();
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        searchNhaCungCap();
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void tbl_NhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhaCungCapMouseClicked
        //clickHereNhaCungCap();
        int selectedRow = tbl_NhaCungCap.getSelectedRow(); // Lấy dòng đang chọn

        if (selectedRow != -1) { // Kiểm tra có dòng được chọn không
            String tenNCC = tbl_NhaCungCap.getValueAt(selectedRow, 1).toString();
            String SDT = tbl_NhaCungCap.getValueAt(selectedRow, 2).toString();
            String email = tbl_NhaCungCap.getValueAt(selectedRow, 3).toString();
            String diachi = tbl_NhaCungCap.getValueAt(selectedRow, 4).toString();

            // Lưu vào biến toàn cục để truyền vào JDialo
            selectedtenNCC = tenNCC;
            selectedSDT = SDT;
            selectedemail = email;
            selecteddiachi = diachi;
        }
    }//GEN-LAST:event_tbl_NhaCungCapMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JButton btn_notification;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pn_chucnang;
    private javax.swing.JTable tbl_NhaCungCap;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
