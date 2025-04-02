package UI;

import DAO.DAO_PhieuNhap;
import com.formdev.flatlaf.FlatClientProperties;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.model_PhieuNhap;
import util.Message;

public class pnPhieuNhap extends javax.swing.JPanel {
    private DefaultTableModel tbl_ModelPhieuNhap;
    DAO_PhieuNhap pndao = new DAO_PhieuNhap() ;
    
    public pnPhieuNhap() {
        initComponents();
        tbl_ModelPhieuNhap = (DefaultTableModel) tbl_PhieuNhap.getModel();

        setDefaultNgayNhap();
        fillToTablePhieuNhap();
        setCalendarToVietnamese();
        
        styleCode();
        
        ((JTextField) txt_NgayNhap.getDateEditor().getUiComponent()).setEditable(false);
    }

    void styleCode(){
        txt_TimKiemCTPN.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "focusWidth:1;"
                + "innerFocusWidth:0;");
        txt_TimKiem.putClientProperty(FlatClientProperties.STYLE, ""
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
        btn_TimCTPN.putClientProperty(FlatClientProperties.STYLE, ""
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
        btn_LamMoi.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;" // Bo tròn nút
                + "borderWidth:0;" // Không viền
                + "focusWidth:1;" // Không viền khi focus
                + "background:#CBE5AE;" // Màu nền xanh lá nhạt
                + "foreground:#000000;" // Màu chữ mặc định (đen)
                + "hoverBackground:#A5D6A7;" // Màu nền khi hover (xanh lá đậm hơn)
                + "hoverForeground:#FFFFFF;" // Màu chữ khi hover (trắng)
                + "pressedBackground:#81C784;" // Màu nền khi bấm (xanh đậm)
                + "pressedForeground:#EEEEEE;"); // Màu chữ khi bấm (xám nhạt)
        
        tbl_ChiTietPhieuNhap.putClientProperty(FlatClientProperties.STYLE, ""
            + "showVerticalLines:true;"
            + "showHorizontalLines:true;"
            + "intercellSpacing:3,3;"
            + "selectionBackground:#A5D6A7;"
            + "selectionForeground:#000000;"
            + "gridColor:#BDBDBD;"
            + "border:1,1,1,1,#9E9E9E;"
            + "rowHeight:30;"
        );
        tbl_PhieuNhap.putClientProperty(FlatClientProperties.STYLE, ""
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
    
    void setCalendarToVietnamese() {
    Locale.setDefault(new Locale("vi", "VN")); // Đặt ngôn ngữ mặc định là Tiếng Việt
    txt_NgayNhap.setLocale(new Locale("vi", "VN")); // Thiết lập Locale cho JDateChooser
    }
    
    void setDefaultNgayNhap(){
        txt_NgayNhap.setDate(new Date());
//      java.util.Date ngayNhapUtil = txt_NgayNhap.getDate();
//      java.sql.Date ngayNhapSQL = new java.sql.Date(ngayNhapUtil.getTime());
//        Date ngayNhapJava = txt_NgayNhap.getDate();
//        txt_NgayNhap.setDate(ngayNhapJava);
      
    }
    
    // Hiển thị danh sách phiếu nhập lên bảng
    private void fillToTablePhieuNhap() {
        tbl_ModelPhieuNhap.setRowCount(0);
        try{
        List<model_PhieuNhap> list_PhieuNhap = pndao.selectAll();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Định dạng chuẩn
        for (model_PhieuNhap pn : list_PhieuNhap) {
            tbl_ModelPhieuNhap.addRow(
                    new Object[]{
                        pn.getMaPhieuNhap(),
                        pn.getNgayNhapFormatted(),
                        pn.getMaKho(),
                        pn.getMaNhaCungCap()
                    }
            );
        }
        }catch(Exception e){
            Message.alert("Lỗi truy vấn dữ liệu" + e.getMessage());
        }
    }

        public void setForm(model_PhieuNhap pn) {
            
        txt_NgayNhap.setDate(pn.getNgayNhap());
        txt_MaKho.setText(pn.getMaKho());
        txt_MaNhaCungCap.setText(pn.getMaNhaCungCap());
        
    }
    
    // Click vào bảng để lấy dữ liệu
    public void clickHerePhieuNhap() {
        int row = tbl_PhieuNhap.getSelectedRow();
        if (row > -1) {
            try {
                txt_NgayNhap.setDate((Date) tbl_PhieuNhap.getValueAt(row, 1));
                txt_MaKho.setText(tbl_PhieuNhap.getValueAt(row, 2).toString());
                txt_MaNhaCungCap.setText(tbl_PhieuNhap.getValueAt(row, 3).toString());
            } catch (Exception e) {
                Message.error("Lỗi khi chuyển đổi dữ liệu: " + e.getMessage());
            }
        } else {
            Message.alert("Vui lòng chọn một dòng trong bảng!");
        }
    }

    // Thêm phiếu nhập mới
    private void addPhieuNhap() {
        if (
            txt_MaKho.getText().isEmpty() ||
            txt_MaNhaCungCap.getText().isEmpty() ||
            txt_MaKho.getText().isEmpty()) {
            Message.alert("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

            model_PhieuNhap pn = new model_PhieuNhap();
            pn.setNgayNhap(txt_NgayNhap.getDate());
            pn.setMaKho(txt_MaKho.getText().trim()); // Sửa lỗi convert
            pn.setMaNhaCungCap(txt_MaNhaCungCap.getText().trim()); // Đã sửa lỗi gán sai
            
            try {
                pndao.insert(pn);
                fillToTablePhieuNhap();
                Message.alert("Thêm phiếu nhập thành công!");
            } catch(Exception e) {
                Message.error("Lỗi: " + e.getMessage());
                Message.alert("Thêm phiếu nhập thất bại!");
            }
    }

    // Xóa phiếu nhập
    private void deletePhieuNhap() {
        int row = tbl_PhieuNhap.getSelectedRow();
        if (row < 0) {
            Message.alert("Bạn phải chọn một dòng để xóa!");
            return;
        }

        String maPhieuNhap = tbl_PhieuNhap.getValueAt(row, 0).toString();
        boolean confirm = Message.confirm("Bạn có chắc chắn muốn xóa?");
        if (confirm) {
            try{
                pndao.delete(maPhieuNhap);
                fillToTablePhieuNhap();
                Message.alert("Xóa phiếu nhập thành công!");
            } catch(Exception e) {
                Message.error("Lỗi" + e.getMessage());
                Message.alert("Xóa phiếu nhập thất bại!");
            }
        }
    }

    // Cập nhật phiếu nhập
    private void updatePhieuNhap() {
        int row = tbl_PhieuNhap.getSelectedRow();
        if (row < 0) {
            Message.alert("Bạn phải chọn một dòng để sửa!");
            return;
        }

        if (
            txt_MaKho.getText().isEmpty() ||
            txt_MaNhaCungCap.getText().isEmpty()
            ) {
            Message.alert("Vui lòng nhập đầy đủ thông tin!");
            return;
        }
            model_PhieuNhap pn = new model_PhieuNhap();
            pn.setMaPhieuNhap(tbl_PhieuNhap.getValueAt(row, 0).toString());
            pn.setNgayNhap(txt_NgayNhap.getDate());
            pn.setMaKho(txt_MaKho.getText().trim());
            pn.setMaNhaCungCap(txt_MaNhaCungCap.getText().trim());

                boolean confirm = Message.confirm("Bạn có chắc chắn muốn cập nhật phiếu nhập có mã '" + pn.getMaPhieuNhap() + "' ?");
                if (confirm) {
                    try{
                    pndao.update(pn);
                    fillToTablePhieuNhap();
                    Message.alert("Cập nhật phiếu nhập thành công!");
                } catch(Exception e) {
                    Message.error("Lỗi: " + e.getMessage());
                    Message.alert("Cập nhật phiếu nhập thất bại!");
                }
                }else {
                    return;
                }
    }

    // Tìm kiếm phiếu nhập
    private void searchPhieuNhap() {
        String maPhieuNhap = txt_TimKiem.getText().trim();
        if (maPhieuNhap.isEmpty()) {
            Message.alert("Vui lòng nhập từ khóa để tìm kiếm!");
            return;
        }

        List<model_PhieuNhap> list_PhieuNhap = pndao.selectById(maPhieuNhap);
        tbl_ModelPhieuNhap.setRowCount(0); // Xóa dữ liệu cũ
        if (list_PhieuNhap.isEmpty()) {
            Message.alert("Không tìm thấy vật tư!");
        } else {
            for (model_PhieuNhap pn : list_PhieuNhap) {
                tbl_ModelPhieuNhap.addRow(
                        new Object[]{
                            pn.getMaPhieuNhap(),
                            pn.getNgayNhap(),
                            pn.getMaKho(),
                            pn.getMaNhaCungCap()
                    }
                );
            }
        }
    }
    
    private void refreshForm(){
        fillToTablePhieuNhap();
//        setDefaultNgayNhap();
        txt_MaKho.setText("");
        txt_MaNhaCungCap.setText("");
        txt_TimKiem.setText("");
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab1_PhieuNhap = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txt_TimKiem = new javax.swing.JTextField();
        btn_TimKiem = new javax.swing.JButton();
        btn_Them = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        txt_NgayNhap = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_MaKho = new javax.swing.JTextField();
        txt_MaNhaCungCap = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_PhieuNhap = new javax.swing.JTable();
        btn_LamMoi = new javax.swing.JButton();
        btn_XemCT2 = new javax.swing.JButton();
        btn_XuatExcel2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txt_MaPhieuNhapCTPN = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_MaVatTu = new javax.swing.JTextField();
        txt_soluong = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_ChiTietPhieuNhap = new javax.swing.JTable();
        txt_TimKiemCTPN = new javax.swing.JTextField();
        btn_ThemCTPX = new javax.swing.JButton();
        btn_XoaCTPX = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btn_SuaCTPX = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btn_Lammoi1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btn_XemCT1 = new javax.swing.JButton();
        btn_TimCTPN = new javax.swing.JButton();
        btn_XuatExcel1 = new javax.swing.JButton();

        tab1_PhieuNhap.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_TimKiem.setText("Tìm Kiếm");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 34)); // NOI18N
        jLabel2.setText("Phiếu Nhập");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Ngày Nhập :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Mã Kho :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Mã Nhà Cung Cấp :");

        tbl_PhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Nhập", "Ngày Nhập", "Mã Kho", "Mã Nhà Cung Cấp"
            }
        ));
        tbl_PhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_PhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_PhieuNhap);

        btn_LamMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_XemCT2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_XemCT2.setText("Xem Chi Tiết");
        btn_XemCT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XemCT2ActionPerformed(evt);
            }
        });

        btn_XuatExcel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_XuatExcel2.setText("Xuất Excel");
        btn_XuatExcel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatExcel2ActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_MaKho, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(txt_MaNhaCungCap)
                            .addComponent(txt_NgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_XemCT2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_XuatExcel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txt_NgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_MaKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_MaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XemCT2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XuatExcel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tab1_PhieuNhap.addTab("Phiếu Nhập", jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(988, 700));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Số Lượng :");

        tbl_ChiTietPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Phiếu Nhập", "Mã Vật Tư", "Số Lượng"
            }
        ));
        tbl_ChiTietPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ChiTietPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_ChiTietPhieuNhap);

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
        jLabel6.setText("Phiếu Nhập Chi Tiết");

        btn_SuaCTPX.setText("Sửa");
        btn_SuaCTPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaCTPXActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Mã Phiếu Nhập :");

        btn_Lammoi1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_Lammoi1.setText("Làm Mới");
        btn_Lammoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Lammoi1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Mã Vật Tư :");

        btn_XemCT1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_XemCT1.setText("Xem Chi Tiết");
        btn_XemCT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XemCT1ActionPerformed(evt);
            }
        });

        btn_TimCTPN.setText("Tìm Kiếm");
        btn_TimCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimCTPNActionPerformed(evt);
            }
        });

        btn_XuatExcel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_XuatExcel1.setText("Xuất Excel");
        btn_XuatExcel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatExcel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_ThemCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_XoaCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_SuaCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_MaPhieuNhapCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_MaVatTu))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_TimCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_TimKiemCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Lammoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_XemCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_XuatExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_TimKiemCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_TimCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_MaPhieuNhapCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_MaVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_ThemCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_XoaCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_SuaCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Lammoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XemCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XuatExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        tab1_PhieuNhap.addTab("Chi Tiết Phiếu Nhập", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab1_PhieuNhap)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab1_PhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        searchPhieuNhap();
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        addPhieuNhap();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        deletePhieuNhap();
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        updatePhieuNhap();
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void tbl_PhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PhieuNhapMouseClicked
        clickHerePhieuNhap();
    }//GEN-LAST:event_tbl_PhieuNhapMouseClicked

    private void tbl_ChiTietPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ChiTietPhieuNhapMouseClicked
        //clickHerePNCT();
    }//GEN-LAST:event_tbl_ChiTietPhieuNhapMouseClicked

    private void btn_ThemCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemCTPXActionPerformed
        //addPhieuNhapct();
    }//GEN-LAST:event_btn_ThemCTPXActionPerformed

    private void btn_XoaCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaCTPXActionPerformed
        //deleteCTPhieuNhap();
    }//GEN-LAST:event_btn_XoaCTPXActionPerformed

    private void btn_SuaCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaCTPXActionPerformed
        //updateCTPhieuNhap();
    }//GEN-LAST:event_btn_SuaCTPXActionPerformed

    private void btn_Lammoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Lammoi1ActionPerformed
//        fillToTableCTPN();
//        fillToTablePN();
//        txt_NgayNhap.setDate(null);
//        txt_MaKho.setText(null);
//        txt_MaNhaCungCap.setText(null);
//        txt_MaPhieuNhapCTPN.setText(null);
//        txt_MaPhieuNhap.setText(null);
//        txt_soluong.setText(null);
//        txt_TimKiemCTPN.setText(null);
//        txt_TimKiemPN.setText(null);
    }//GEN-LAST:event_btn_Lammoi1ActionPerformed

    private void btn_XemCT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XemCT1ActionPerformed
        //viewChiTietPhieuNhapInPN();
    }//GEN-LAST:event_btn_XemCT1ActionPerformed

    private void btn_TimCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimCTPNActionPerformed
//        try {
//            // TODO add your handling code here:
//            searchChiTietPhieuNhap();
//        } catch (ParseException ex) {
//            Logger.getLogger(pnPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btn_TimCTPNActionPerformed

    private void btn_XuatExcel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatExcel1ActionPerformed
        //exportToExcel();
    }//GEN-LAST:event_btn_XuatExcel1ActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        refreshForm();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_XemCT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XemCT2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_XemCT2ActionPerformed

    private void btn_XuatExcel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatExcel2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_XuatExcel2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Lammoi1;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_SuaCTPX;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_ThemCTPX;
    private javax.swing.JButton btn_TimCTPN;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_XemCT1;
    private javax.swing.JButton btn_XemCT2;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JButton btn_XoaCTPX;
    private javax.swing.JButton btn_XuatExcel1;
    private javax.swing.JButton btn_XuatExcel2;
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
    private javax.swing.JTabbedPane tab1_PhieuNhap;
    private javax.swing.JTable tbl_ChiTietPhieuNhap;
    private javax.swing.JTable tbl_PhieuNhap;
    private javax.swing.JTextField txt_MaKho;
    private javax.swing.JTextField txt_MaNhaCungCap;
    private javax.swing.JTextField txt_MaPhieuNhapCTPN;
    private javax.swing.JTextField txt_MaVatTu;
    private com.toedter.calendar.JDateChooser txt_NgayNhap;
    private javax.swing.JTextField txt_TimKiem;
    private javax.swing.JTextField txt_TimKiemCTPN;
    private javax.swing.JTextField txt_soluong;
    // End of variables declaration//GEN-END:variables
}
