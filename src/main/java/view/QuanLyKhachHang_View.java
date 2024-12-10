package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import model.KhachHang;
import model.KhachHang_DAO;
import other.PrimaryButton;
import other.RoundedButton;
import other.RoundedPanel;

import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JSeparator;

public class QuanLyKhachHang_View extends View {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfEmail;
	private JTextField tfNgaySinh;
	private JTextField tffTen;
	private JTextField tffSdt;
	private JTextField tffCCCD;
	private JTextField tfHoten;
	private JTextField tfCCCD;
	private JTextField tfSDT;
	private JComboBox cbGioiTinh;
	private JComboBox cbLoaikh;
	private JTable customerTable;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private RoundedButton btnHuybo;
	private RoundedButton btnXacnhan;
	private RoundedPanel mainPanel;
	private JTextField tenKhachHangField;
	private JTextField CCCDKhachHangField;
	private JTextField SDTKhachHangField;
	private JTextField EmailKhachHangField;
	private JComboBox GioiTinhCB;
	private JComboBox LoaiKHCB;
	private Container pr;
	private static JTable danhSachKhachHang;
	private boolean isAdding = true;
	private String originalHoTen;
	private String originalCCCD;
	private String originalSDT;
	private String originalEmail;
	private String originalNgaySinh;
	private String originalGioiTinh;
	private String originalLoaiKH;
	private JTextField tfFind;
	private JComboBox timKiemCB;
	DefaultTableModel originalModel = new DefaultTableModel();
	private JDateChooser dateKH;
	private ImageIcon iconlich;
	private JTextField NgaySinhKHField;
	
	public QuanLyKhachHang_View(String name, String imagePath) {
		super(name, imagePath);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);
		
		mainPanel = new RoundedPanel(15);  
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(null);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setContentPane(mainPanel);
		
		JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(0, 200));
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
		
		JPanel pthemkh = new JPanel();
		pthemkh.setBackground(Color.WHITE);
		pthemkh.setPreferredSize(new Dimension(0, 50));
		pthemkh.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        topPanel.add(pthemkh);
        pthemkh.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pthemkh.setBounds(10, 11, 1218, 228); 
		
		JLabel lblTitle = new JLabel("Thêm khách hàng");
		pthemkh.add(lblTitle);
        lblTitle.setOpaque(true);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBorder(new EmptyBorder(5, 10, 5, 20));
        lblTitle.setBackground(new Color(70, 130, 169));
        
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
        topPanel.add(inputPanel);
        inputPanel.setPreferredSize(new Dimension(0, 200));
        inputPanel.setMaximumSize(new Dimension(2147483647, 200));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

        RoundedPanel panel_InputThongTin = new RoundedPanel(15); 
        panel_InputThongTin.setBorder(new CompoundBorder(new LineBorder(new Color(70, 130, 169)), new EmptyBorder(10, 5, 5, 5)));
        panel_InputThongTin.setBackground(Color.WHITE);
        inputPanel.add(panel_InputThongTin);
        panel_InputThongTin.setLayout(new BoxLayout(panel_InputThongTin, BoxLayout.X_AXIS));

        JPanel panel_InputThongTin1 = new JPanel();
        panel_InputThongTin1.setBackground(Color.WHITE);
        panel_InputThongTin.add(panel_InputThongTin1);
        panel_InputThongTin1.setLayout(null);
        
        JPanel panel_Input_Left = new JPanel() ;
        panel_Input_Left.setBounds(0, -3,1022, 120);
        panel_InputThongTin1.add(panel_Input_Left) ; 
        panel_Input_Left.setBackground(new Color(255, 255, 255));
        panel_Input_Left.setLayout(null);
        
        JPanel panel_Input_Right = new JPanel() ;
        panel_Input_Right.setForeground(new Color(0, 128, 255));
        panel_Input_Right.setBounds(1032, 0, 164, 111);
        panel_InputThongTin1.add(panel_Input_Right) ; 
        panel_Input_Right.setBackground(new Color(255, 255, 255));
        panel_Input_Right.setLayout(null);
        panel_Input_Right.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 255)));
        
        JLabel label = new JLabel("Họ và tên:");
        label.setBounds(5, 10, 98, 14);
        panel_Input_Left.add(label) ; 
        tenKhachHangField = new JTextField(15) ;
        tenKhachHangField.setBounds(126, 3, 306, 25);
        tenKhachHangField.putClientProperty("JTextField.placeholderText", "Nhập tên khách hàng");
        tenKhachHangField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_Input_Left.add(tenKhachHangField);
        
        JLabel label_1 = new JLabel("CCCD:");
        label_1.setBounds(5, 55, 79, 14);
        panel_Input_Left.add(label_1) ; 
        CCCDKhachHangField = new JTextField(15) ;
        CCCDKhachHangField.setBounds(126, 48, 306, 25);
        CCCDKhachHangField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_Input_Left.add(CCCDKhachHangField);
        
        JLabel label_2 = new JLabel("Số điện thoại:");
        label_2.setBounds(5, 95, 112, 14);
        panel_Input_Left.add(label_2) ; 
        SDTKhachHangField = new JTextField(15) ;
        SDTKhachHangField.setBounds(126, 88, 306, 25);
        SDTKhachHangField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_Input_Left.add(SDTKhachHangField);
        
        JLabel label_3 = new JLabel("Email:");
        label_3.setBounds(577, 10, 89, 14);
        panel_Input_Left.add(label_3) ; 
        EmailKhachHangField = new JTextField(15) ;
        EmailKhachHangField.setBounds(689, 3, 306, 25);
        EmailKhachHangField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_Input_Left.add(EmailKhachHangField);
        
        JLabel label_4 = new JLabel("Giới tính:");
        label_4.setBounds(577, 55, 89, 14);
        panel_Input_Left.add(label_4) ;
        GioiTinhCB = new JComboBox();
        GioiTinhCB.setBounds(689, 48, 98, 25);
        GioiTinhCB.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
        GioiTinhCB.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_Input_Left.add(GioiTinhCB);
        
        JLabel lblLoaiKh = new JLabel("Loại KH:");
        lblLoaiKh.setBounds(818, 55, 177, 14);
        panel_Input_Left.add(lblLoaiKh) ;
        LoaiKHCB = new JComboBox();
        LoaiKHCB.setBounds(874, 48, 121, 25);
        LoaiKHCB.setModel(new DefaultComboBoxModel(new String[] {"TRE_EM", "SINH_VIEN", "HOC_SINH", "NGUOI_GIA", "NGUOI_KHUYET_TAT", "KHACH_THUONG"}));
        LoaiKHCB.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_Input_Left.add(LoaiKHCB);
        
        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        lblNgaySinh.setBounds(577, 95, 89, 14);
        panel_Input_Left.add(lblNgaySinh) ;
		NgaySinhKHField = new JTextField(15);
		NgaySinhKHField.setFont(new Font("Arial", Font.PLAIN, 16));
		NgaySinhKHField.setBounds(689, 88, 306, 25);
		panel_Input_Left.add(NgaySinhKHField);
        
        btnHuybo = new PrimaryButton("Hủy bỏ", "/Image/armchair.png");
		btnHuybo.setForeground(new Color(255, 255, 255));
		btnHuybo.setBackground(Color.decode("#FF3014"));
		btnHuybo.setFont(new Font("Arial", Font.BOLD, 17));
		btnHuybo.setBounds(23, 62, 124, 40);
		btnHuybo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHuyBo();
			}
		});
		panel_Input_Right.add(btnHuybo);
        
		btnXacnhan = new PrimaryButton("Xác nhận", "/Image/armchair.png");
		btnXacnhan.setFont(new Font("Arial", Font.BOLD, 17));
		btnXacnhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXacNhan();
			}
		});
		btnXacnhan.setBounds(23, 11, 124, 40);
		panel_Input_Right.add(btnXacnhan);
		
		JPanel panel_center = new JPanel();
        panel_center.setBackground(Color.WHITE);
        mainPanel.add(panel_center);
        panel_center.setLayout(new BoxLayout(panel_center, BoxLayout.Y_AXIS));
        
        JPanel panel_TitleDanhSach = new JPanel();
        panel_TitleDanhSach.setBackground(Color.WHITE);
        panel_TitleDanhSach.setPreferredSize(new Dimension(0, 60));
        panel_TitleDanhSach.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        panel_center.add(panel_TitleDanhSach);
        panel_TitleDanhSach.setLayout(null);
        
        JLabel lblDanhSach = new JLabel("Danh sách khách hàng");
        lblDanhSach.setBounds(10, 15, 242, 34);
        lblDanhSach.setOpaque(true);
        lblDanhSach.setForeground(Color.WHITE);
        lblDanhSach.setFont(new Font("Arial", Font.BOLD, 20));
        lblDanhSach.setBorder(new EmptyBorder(5, 10, 5, 20));
        lblDanhSach.setBackground(new Color(70, 130, 169));
        panel_TitleDanhSach.add(lblDanhSach);
        
        JButton btnDeleteKH = new JButton("Xóa khách hàng");
        btnDeleteKH.setBounds(1058, 20, 170, 27);
        btnDeleteKH.setForeground(Color.WHITE);
        btnDeleteKH.setFont(new Font("Arial", Font.BOLD, 16));
        btnDeleteKH.setBackground(new Color(70, 130, 169));
        panel_TitleDanhSach.add(btnDeleteKH);
        btnDeleteKH.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnXoaKH() ; 
				
			}
		});
        
		JPanel panel_TimKiem = new JPanel();
		panel_TimKiem.setBounds(40, -39, 1238, 49);
		panel_TitleDanhSach.add(panel_TimKiem);
		panel_TimKiem.setBackground(Color.WHITE);
		panel_TimKiem.setPreferredSize(new Dimension(0, 50)); // Chiều cao panel
		panel_TimKiem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		panel_TimKiem.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // Căn chỉnh các thành phần
		
		JLabel lblTen = new JLabel("Tên:");
		lblTen.setFont(new Font("Arial", Font.BOLD, 16));
		panel_TimKiem.add(lblTen);
		
        JTextField tfTen = new JTextField();
        tfTen.setFont(new Font("Arial", Font.PLAIN, 16));
        tfTen.setPreferredSize(new Dimension(150, 30));
        panel_TimKiem.add(tfTen);
		
		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setFont(new Font("Arial", Font.BOLD, 16));
		panel_TimKiem.add(lblCCCD);
		
        JTextField tfCCCD_1 = new JTextField();
        tfCCCD_1.setFont(new Font("Arial", Font.PLAIN, 16));
        tfCCCD_1.setPreferredSize(new Dimension(150, 30));
        panel_TimKiem.add(tfCCCD_1);
	

        JLabel lblSDT = new JLabel("SĐT:");
        lblSDT.setFont(new Font("Arial", Font.BOLD, 16));
        panel_TimKiem.add(lblSDT);
	
        JTextField tfSDT_1 = new JTextField();
        tfSDT_1.setFont(new Font("Arial", Font.PLAIN, 16));
        tfSDT_1.setPreferredSize(new Dimension(150, 30));
        panel_TimKiem.add(tfSDT_1);
        
		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 16));
		btnTimKiem.setBackground(new Color(70, 130, 169));
		btnTimKiem.setBounds(899, 20, 135, 27);
		panel_TitleDanhSach.add(btnTimKiem);
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnTimKiem();
				
			}
		});
		
		timKiemCB = new JComboBox();
		timKiemCB.setFont(new Font("Arial", Font.PLAIN, 15));
		timKiemCB.setModel(new DefaultComboBoxModel(new String[] {"Tên khách hàng", "Số điện thoại", "CCCD"}));
		timKiemCB.setBounds(560, 21, 146, 27);
		panel_TitleDanhSach.add(timKiemCB);
		
		tfFind = new JTextField();
		tfFind.setFont(new Font("Arial", Font.PLAIN, 15));
		tfFind.setBounds(730, 20, 146, 27);
		panel_TitleDanhSach.add(tfFind);
		tfFind.setColumns(10);

        JPanel panel_Table = new JPanel();
        panel_Table.setBorder(new EmptyBorder(0, 20, 5, 15));
        panel_Table.setBackground(Color.WHITE);
        panel_Table.setPreferredSize(new Dimension(0, 530));
        panel_Table.setMaximumSize(new Dimension(Integer.MAX_VALUE, 530));
        panel_center.add(panel_Table);
        panel_Table.setLayout(new BoxLayout(panel_Table, BoxLayout.X_AXIS));
		
        String[] columnNames = {"STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email", "Giới tính", "CCCD", "Ngày sinh", "Loại khách hàng"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        danhSachKhachHang = new JTable(tableModel);
        danhSachKhachHang.setRowHeight(25);
        danhSachKhachHang.setFont(new Font("Arial", Font.PLAIN, 16));
        danhSachKhachHang.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < danhSachKhachHang.getColumnCount(); i++) {
            danhSachKhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = danhSachKhachHang.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setReorderingAllowed(false);
        JScrollPane tableScrollPane = new JScrollPane(danhSachKhachHang);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel_Table.add(tableScrollPane);
        loadDataToTable();
        addRowClickListener() ;
        //-----------------------------------
        
        originalModel.setColumnIdentifiers(new Object[] {"STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email", "Giới tính", "CCCD", "Ngày sinh", "Loại khách hàng"});

        // Lưu dữ liệu của bảng vào originalModel
        for (int i = 0; i < danhSachKhachHang.getRowCount(); i++) {
            Object[] rowData = new Object[danhSachKhachHang.getColumnCount()];
            for (int j = 0; j < danhSachKhachHang.getColumnCount(); j++) {
                rowData[j] = danhSachKhachHang.getValueAt(i, j);
            }
            originalModel.addRow(rowData);
        }
	}

	public void btnXacNhan() {
	    String maKH = taoMaKhachHang();
	    String hoTen = tenKhachHangField.getText().trim();
	    String cccd = CCCDKhachHangField.getText().trim();
	    String sdt = SDTKhachHangField.getText().trim();
	    String email = EmailKhachHangField.getText().trim();
	    String ngaySinh = NgaySinhKHField.getText().trim() ; 
	    String gioiTinh = (String) GioiTinhCB.getSelectedItem();
	    String loaiKhachHang = (String) LoaiKHCB.getSelectedItem();

	    if (isAdding) {
	        if (checkDuLieuNhap()) {
	            DefaultTableModel model = (DefaultTableModel) danhSachKhachHang.getModel();
	            int stt = model.getRowCount() + 1; 
	            model.addRow(new Object[]{stt, maKH, hoTen, sdt, email, gioiTinh, cccd, ngaySinh, loaiKhachHang});
	            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	        }
	    } else {
	        if (hoTen.equals(originalHoTen) && cccd.equals(originalCCCD) && sdt.equals(originalSDT) &&
	            email.equals(originalEmail) && ngaySinh.equals(originalNgaySinh) && gioiTinh.equals(originalGioiTinh) &&
	            loaiKhachHang.equals(originalLoaiKH)) {
	            JOptionPane.showMessageDialog(this, "Thông tin chưa được chỉnh sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            if (checkDuLieuNhap()) {
	                DefaultTableModel model = (DefaultTableModel) danhSachKhachHang.getModel();
	                int row = danhSachKhachHang.getSelectedRow();

	                model.setValueAt(hoTen, row, 2);
	                model.setValueAt(sdt, row, 3);
	                model.setValueAt(email, row, 4);
	                model.setValueAt(gioiTinh, row, 5);
	                model.setValueAt(cccd, row, 6);
	                model.setValueAt(ngaySinh, row, 7);
	                model.setValueAt(loaiKhachHang, row, 8);

	                JOptionPane.showMessageDialog(this, "Thông tin đã được cập nhật!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	            }
	        }
	    }
	}

	public static String taoMaKhachHang() {
	    DefaultTableModel model = (DefaultTableModel) danhSachKhachHang.getModel();
	    int soLuongKhachHang = model.getRowCount();
	    String maKhachHang = "KH" + String.format("%07d", soLuongKhachHang + 1);
	    return maKhachHang;
    }
	
	public boolean checkDuLieuNhap() {
		String hoTen = tenKhachHangField.getText();
	    String cccd = CCCDKhachHangField.getText();
	    String sdt = SDTKhachHangField.getText();
	    String email = EmailKhachHangField.getText();
	    
		if (hoTen == null || !hoTen.matches("^[a-zA-Z\\p{L} ]+$")) {
			showError("Họ tên khách hàng chỉ được chứa các chữ cái và khoảng trắng.");
	    	return false ; 
		}
		if (sdt == null || !sdt.matches("^\\d{10}$")) {
			showError("Số điện thoại phải có 10 ký tự số. ");
			return false ; 
		}
		if (email == null || !email.matches("^[a-zA-Z0-9._-]+@gmail.com$")) {
			showError("Định dạng email không hợp lệ! ");
			return false ; 
		}
		if (cccd == null || !cccd.matches("^\\d{12}$")) {
			showError("Định dạng CCCD phải có 12 chữ số ");
			return false ; 
		}
		return true ; 
	}
	public void btnHuyBo() {
	    tenKhachHangField.setText("");
	    CCCDKhachHangField.setText("");
	    SDTKhachHangField.setText("");
	    EmailKhachHangField.setText("");
	    NgaySinhKHField.setText("");
	    GioiTinhCB.setSelectedIndex(0);
	    LoaiKHCB.setSelectedIndex(0);
	}
	private static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
	public void loadDataToTable() {
	    try {
	        KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
	        List<KhachHang> khachHangList = khachHang_DAO.getAll();
	        DefaultTableModel model = (DefaultTableModel) danhSachKhachHang.getModel();
	        model.setRowCount(0);

	        int stt = 1;
	        for (KhachHang kh : khachHangList) {
	            model.addRow(new Object[]{
	                stt++,                       
	                kh.getMaKhachHang(),          
	                kh.getHoTen(),                
	                kh.getSoDienThoai(),          
	                kh.getEmail(),                
	                kh.isGioiTinh() ? "Nam" : "Nữ", 
	                kh.getCCCD(),           
	                kh.getNgaySinh(),             
	                kh.getLoaiKH()                
	            });
	        }


	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu khách hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	    }
	}
	public void addRowClickListener() {
        danhSachKhachHang.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
                int row = danhSachKhachHang.getSelectedRow();
                if (row != -1) {
                    originalHoTen = danhSachKhachHang.getValueAt(row, 2).toString();
                    originalCCCD = danhSachKhachHang.getValueAt(row, 6).toString();
                    originalSDT = danhSachKhachHang.getValueAt(row, 3).toString();
                    originalEmail = danhSachKhachHang.getValueAt(row, 4).toString();
                    originalNgaySinh = danhSachKhachHang.getValueAt(row, 7).toString();
                    originalGioiTinh = danhSachKhachHang.getValueAt(row, 5).toString();
                    originalLoaiKH = danhSachKhachHang.getValueAt(row,8).toString() ; 

                    tenKhachHangField.setText(originalHoTen);
                    CCCDKhachHangField.setText(originalCCCD);
                    SDTKhachHangField.setText(originalSDT);
                    EmailKhachHangField.setText(originalEmail);
                    NgaySinhKHField.setText(originalNgaySinh);
                    if ("Nam".equals(originalGioiTinh)) {
                        GioiTinhCB.setSelectedIndex(0);  
                    } else if ("Nữ".equals(originalGioiTinh)) {
                        GioiTinhCB.setSelectedIndex(1); 
                    }
                    if("TRE_EM".equals(originalLoaiKH)) {
                    	LoaiKHCB.setSelectedIndex(0) ; 
                    }else if("SINH_VIEN".equals(originalLoaiKH)) {
                    	LoaiKHCB.setSelectedIndex(1) ;
                    }else if("HOC_SINH".equals(originalLoaiKH)) {
                    	LoaiKHCB.setSelectedIndex(2) ;
                    }else if("NGUOI_GIA".equals(originalLoaiKH)) {
                    	LoaiKHCB.setSelectedIndex(3) ;
                    }else if("NGUOI_KHUYET_TAT".equals(originalLoaiKH)) {
                    	LoaiKHCB.setSelectedIndex(4) ;
                    }else if("KHACH_THUONG".equals(originalLoaiKH)) {
                    	LoaiKHCB.setSelectedIndex(5) ;
                    }
                    isAdding = false;
                }
            }
        });
    }
	public void btnXoaKH() {
		int row = danhSachKhachHang.getSelectedRow();
	    if (row == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	    }
	    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khách hàng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
	    
	    if (confirm == JOptionPane.YES_OPTION) {
	        
	        DefaultTableModel model = (DefaultTableModel) danhSachKhachHang.getModel();
	        model.removeRow(row);
	        updateSTT();
	        JOptionPane.showMessageDialog(this, "Đã xóa thông tin khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	        btnHuyBo();
	    }
	}
	private void updateSTT() {
	    DefaultTableModel model = (DefaultTableModel) danhSachKhachHang.getModel();
	    for (int i = 0; i < model.getRowCount(); i++) {
	        model.setValueAt(i + 1, i, 0);
	    }
	}
	public void btnTimKiem() {
	    String searchText = tfFind.getText().trim();
	    String selectedCondition = (String) timKiemCB.getSelectedItem();
	        DefaultTableModel model = (DefaultTableModel) danhSachKhachHang.getModel();
	        model.setRowCount(0); 
	        model.setColumnIdentifiers(new Object[] {"STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email", "Giới tính", "CCCD", "Ngày sinh", "Loại khách hàng"});
	        
	        for (int i = 0; i < originalModel.getRowCount(); i++) {
	            Object[] rowData = new Object[originalModel.getColumnCount()];
	            for (int j = 0; j < originalModel.getColumnCount(); j++) {
	                rowData[j] = originalModel.getValueAt(i, j);
	            }
	            model.addRow(rowData);
	        }
	       
	    DefaultTableModel model1 = (DefaultTableModel) danhSachKhachHang.getModel();
	    DefaultTableModel filteredModel = new DefaultTableModel();
	    filteredModel.setColumnIdentifiers(new Object[] {"STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email", "Giới tính", "CCCD", "Ngày sinh", "Loại khách hàng"});
	    
	    boolean found = false;

	    for (int i = 0; i < model1.getRowCount(); i++) {
	        boolean match = false;
	        switch (selectedCondition) {
	            case "Tên khách hàng":
	                match = model1.getValueAt(i, 2).toString().toLowerCase().contains(searchText.toLowerCase());
	                break;
	            case "Số điện thoại":
	                match = model1.getValueAt(i, 3).toString().toLowerCase().contains(searchText.toLowerCase());
	                break;
	            case "CCCD":
	                match = model1.getValueAt(i, 6).toString().toLowerCase().contains(searchText.toLowerCase());
	                break;
	        }

	        if (match) {
	            Object[] rowData = new Object[model1.getColumnCount()];
	            for (int j = 0; j < model1.getColumnCount(); j++) {
	                rowData[j] = model1.getValueAt(i, j);
	            }
	            filteredModel.addRow(rowData);
	            found = true;
	        }
	    }
	    if (found) {
	        danhSachKhachHang.setModel(filteredModel);
	    } else {
	        JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
}

