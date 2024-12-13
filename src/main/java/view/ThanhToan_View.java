package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.border.EmptyBorder;

import other.PrimaryButton;
import view.VeTau_View.MultiLineCellRenderer;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import constant.ColorConstants;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Box;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ThanhToan_View extends View {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PrimaryButton btn_QuayLai;
	private PrimaryButton btn_XemTruoc;
	private PrimaryButton btn_ThuTien;
	private JTextField txt_TienKhachDua; 
	private JTextField txt_TienTraLai;
	private JTable danhSachHoaDonJTable;
	private DefaultTableModel danhSachHoaDonModel;
	private String title[] = { "STT", "Mã vé", "Loại vé", "Số lượng", "Đơn giá", "Giảm giá ","Giảm giá khác", "Thành tiền"};
	private JLabel lbl_MaNV;
	private JLabel lbl_HoTenNV;
	private JPanel panel_12;
	private JLabel lbl_HoTenKH;
	private JLabel lbl_SoDT;
	private JLabel lbl_HinhThucThanhToan;
	private JLabel lbl_MaKH;
	private JLabel lbl_SauThue;
	private JLabel lbl_TongTienThanhToan;
	private JLabel lbl_TongTien;
	private JPanel panel_GoiYTien;
	private JPanel panel_Tien;
	private PrimaryButton btn_ThemKhuyenMai;
	private PrimaryButton btn_ThemGhiChu;
	private JComboBox comboBox_LoaiHD;
	
	public void addSuKienThanhToan(ActionListener listener, KeyListener keyListener) {
		btn_QuayLai.addActionListener(listener);
		btn_XemTruoc.addActionListener(listener);
		btn_ThuTien.addActionListener(listener);
		btn_ThemKhuyenMai.addActionListener(listener);
		btn_ThemGhiChu.addActionListener(listener);
		txt_TienKhachDua.addKeyListener(keyListener);
		
	}
	public void addSuKienTableThanhToan(MouseListener mouseListener) {
		danhSachHoaDonJTable.addMouseListener(mouseListener);
	}
	public String showNoteInputDialog(Component parent, String title, String message, String defaultNote) {
	    JTextArea textArea = new JTextArea(5, 20); 
	    textArea.setLineWrap(true); 
	    textArea.setWrapStyleWord(true);
	    JScrollPane scrollPane = new JScrollPane(textArea);

	    if (defaultNote != null && !defaultNote.trim().isEmpty()) {
	        textArea.setText(defaultNote); 
	    }

	    int result = JOptionPane.showConfirmDialog(
	            parent,
	            scrollPane,
	            title,
	            JOptionPane.OK_CANCEL_OPTION,
	            JOptionPane.PLAIN_MESSAGE
	    );

	    if (result == JOptionPane.OK_OPTION) {
	        return textArea.getText().trim();
	    } else if(!defaultNote.equals("")) {
	    	return textArea.getText().trim();
	    }
	    else {
	    	return null;
	    }
	}


		
	public ThanhToan_View(String name, String imagePath) {
		super(name, imagePath);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1350, 850);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		JPanel panel_Left = new JPanel();
		panel_Left.setBorder(new MatteBorder(0, 0, 0, 2, ColorConstants.PRIMARY_COLOR));
		panel_Left.setBackground(Color.WHITE);
		contentPane.add(panel_Left);
		panel_Left.setLayout(new BoxLayout(panel_Left, BoxLayout.Y_AXIS));
		
		JPanel panel_16 = new JPanel();
		panel_16.setPreferredSize(new Dimension(0, 600)); 
		panel_16.setMaximumSize(new Dimension(Integer.MAX_VALUE, 600));
		panel_Left.add(panel_16);
		panel_16.setLayout(new BoxLayout(panel_16, BoxLayout.Y_AXIS));
		
		JPanel panel_21 = new JPanel();
		panel_21.setBackground(Color.WHITE);
		panel_21.setPreferredSize(new Dimension(0, 55)); 
		panel_21.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
		panel_16.add(panel_21);
		panel_21.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));
		
		JLabel lblNewLabel_2_1 = new JLabel("Chi tiết hóa đơn");
		lblNewLabel_2_1.setOpaque(true);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1.setBorder(new EmptyBorder(5, 10, 5, 20));
		lblNewLabel_2_1.setBackground(new Color(70, 130, 169));
		panel_21.add(lblNewLabel_2_1);
		
		JPanel panel_22 = new JPanel();
		panel_22.setBackground(Color.WHITE);
		panel_22.setBorder(new EmptyBorder(0, 10, 0, 10));
		panel_16.add(panel_22);
		
		danhSachHoaDonModel = new DefaultTableModel(title, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		danhSachHoaDonJTable = new JTable(danhSachHoaDonModel);
		danhSachHoaDonJTable.setBackground(Color.WHITE);
		danhSachHoaDonJTable.setRowHeight(25);
		danhSachHoaDonJTable.setFont(new Font("Arial", Font.PLAIN, 16));
		danhSachHoaDonJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// Vô hiệu hóa chế độ tự động điều chỉnh kích thước cột
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// Gán renderer căn giữa cho tất cả các cột trong bảng
		for (int i = 0; i < danhSachHoaDonJTable.getColumnCount(); i++) {
			danhSachHoaDonJTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		// Tùy chỉnh font chữ cho header của JTable
		JTableHeader header = danhSachHoaDonJTable.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 18));

		// Thiết lập chiều rộng cho các cột
		danhSachHoaDonJTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		danhSachHoaDonJTable.getColumnModel().getColumn(1).setPreferredWidth(130);
		danhSachHoaDonJTable.getColumnModel().getColumn(2).setPreferredWidth(110);
		danhSachHoaDonJTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		danhSachHoaDonJTable.getColumnModel().getColumn(4).setPreferredWidth(180);
		danhSachHoaDonJTable.getColumnModel().getColumn(5).setPreferredWidth(140);
		danhSachHoaDonJTable.getColumnModel().getColumn(6).setPreferredWidth(140);
		danhSachHoaDonJTable.getColumnModel().getColumn(7).setPreferredWidth(180);

		header.setReorderingAllowed(false); // Không cho di chuyển cột
		// Thiết lập chiều rộng và khóa không cho thay đổi kích thước cho các cột
		for (int i = 0; i < danhSachHoaDonJTable.getColumnCount(); i++) {
			danhSachHoaDonJTable.getColumnModel().getColumn(i).setResizable(false); // Khóa không cho thay đổi kích thước
		}
		

		// Thêm vào JScrollPane
		JScrollPane scrollPane = new JScrollPane(danhSachHoaDonJTable);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_22.setLayout(new BoxLayout(panel_22, BoxLayout.X_AXIS));
		panel_22.add(scrollPane);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBackground(Color.WHITE);
		panel_Left.add(panel_20);
		panel_20.setLayout(new BoxLayout(panel_20, BoxLayout.Y_AXIS));
		
		JPanel panel_24 = new JPanel();
		panel_24.setBackground(Color.WHITE);
		panel_24.setBorder(new EmptyBorder(0, 0, 0, 15));
		panel_24.setPreferredSize(new Dimension(0, 60)); 
		panel_24.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		panel_20.add(panel_24);
		panel_24.setLayout(new FlowLayout(FlowLayout.RIGHT, 25, 20));
		
		JPanel panel_25_1_1_1 = new JPanel();
		panel_25_1_1_1.setBackground(Color.WHITE);
		panel_24.add(panel_25_1_1_1);
		panel_25_1_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JPanel panel_25_1_1 = new JPanel();
		panel_25_1_1.setBackground(Color.WHITE);
		panel_24.add(panel_25_1_1);
		panel_25_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JLabel lblNewLabel_4_2_1 = new JLabel("Tổng tiền:  ");
		lblNewLabel_4_2_1.setForeground(new Color(70, 130, 169));
		lblNewLabel_4_2_1.setFont(new Font("Arial", Font.BOLD, 18));
		panel_25_1_1.add(lblNewLabel_4_2_1);
		
		lbl_TongTien = new JLabel("10.000.000đ");
		lbl_TongTien.setForeground(Color.BLACK);
		lbl_TongTien.setFont(new Font("Arial", Font.BOLD, 18));
		panel_25_1_1.add(lbl_TongTien);
		
		JPanel panel_25_1 = new JPanel();
		panel_25_1.setBackground(Color.WHITE);
		panel_24.add(panel_25_1);
		panel_25_1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JLabel lblNewLabel_4_2 = new JLabel("Thuế (10%):  ");
		lblNewLabel_4_2.setForeground(new Color(70, 130, 169));
		lblNewLabel_4_2.setFont(new Font("Arial", Font.BOLD, 18));
		panel_25_1.add(lblNewLabel_4_2);
		
		lbl_SauThue = new JLabel("10.000.000đ");
		lbl_SauThue.setForeground(Color.BLACK);
		lbl_SauThue.setFont(new Font("Arial", Font.BOLD, 18));
		panel_25_1.add(lbl_SauThue);
		
		JPanel panel_25 = new JPanel();
		panel_25.setBackground(Color.WHITE);
		panel_24.add(panel_25);
		panel_25.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Tổng tiền thanh toán:  ");
		panel_25.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_4.setForeground(ColorConstants.PRIMARY_COLOR);
		
		lbl_TongTienThanhToan = new JLabel("10.000.000đ");
		panel_25.add(lbl_TongTienThanhToan);
		lbl_TongTienThanhToan.setForeground(Color.RED);
		lbl_TongTienThanhToan.setFont(new Font("Arial", Font.BOLD, 18));
		
		JPanel panel_26 = new JPanel();
		panel_26.setBackground(Color.WHITE);
		panel_20.add(panel_26);
		panel_26.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btn_ThemKhuyenMai = new PrimaryButton("Thêm khuyến mãi", "/Image/icon_them.png");
		btn_ThemKhuyenMai.setPreferredSize(new Dimension(180, 40));
		btn_ThemKhuyenMai.setMaximumSize(new Dimension(2147483647, 50));
		btn_ThemKhuyenMai.setInsets(new Insets(4, 6, 4, 6));
		btn_ThemKhuyenMai.setIconTextGap(5);
		btn_ThemKhuyenMai.setBorderRadius(10);
		btn_ThemKhuyenMai.setAlignmentX(0.0f);
		panel_26.add(btn_ThemKhuyenMai);
		
		btn_ThemGhiChu = new PrimaryButton("Thêm ghi chú", "/Image/notes.png");
		btn_ThemGhiChu.setPreferredSize(new Dimension(165, 40));
		btn_ThemGhiChu.setMaximumSize(new Dimension(2147483647, 50));
		btn_ThemGhiChu.setInsets(new Insets(4, 6, 4, 6));
		btn_ThemGhiChu.setIconTextGap(5);
		btn_ThemGhiChu.setBorderRadius(10);
		btn_ThemGhiChu.setAlignmentX(0.0f);
		panel_26.add(btn_ThemGhiChu);
		
		JPanel panel_23 = new JPanel();
		panel_23.setBackground(Color.WHITE);
		panel_20.add(panel_23);
		panel_23.setLayout(new BoxLayout(panel_23, BoxLayout.X_AXIS));

		JPanel panel_Right = new JPanel();
		panel_Right.setPreferredSize(new Dimension(440, 0)); 
		panel_Right.setMaximumSize(new Dimension(420, Integer.MAX_VALUE)); 
		panel_Right.setBackground(Color.WHITE);
		contentPane.add(panel_Right);
		panel_Right.setLayout(new BoxLayout(panel_Right, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel_Right.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_8.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_8.setPreferredSize(new Dimension(0, 55));
		panel_8.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
		panel.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));
		
		JLabel lblNewLabel_2 = new JLabel("Thông tin hóa đơn");
		lblNewLabel_2.setBorder(new EmptyBorder(5, 10, 5, 20));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(ColorConstants.PRIMARY_COLOR);
		panel_8.add(lblNewLabel_2);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.WHITE);
		panel_9.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_3 = new JLabel("Mã nhân viên: ");
		lblNewLabel_3.setForeground(ColorConstants.PRIMARY_COLOR);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 18));
		panel_10.add(lblNewLabel_3);
		
		lbl_MaNV = new JLabel("");
		lbl_MaNV.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_10.add(lbl_MaNV);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		panel_9.add(panel_11);
		panel_11.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_3_1 = new JLabel("Họ tên nhân viên:");
		lblNewLabel_3_1.setForeground(ColorConstants.PRIMARY_COLOR);
		lblNewLabel_3_1.setFont(new Font("Arial", Font.BOLD, 18));
		panel_11.add(lblNewLabel_3_1);
		
		lbl_HoTenNV = new JLabel("");
		lbl_HoTenNV.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_11.add(lbl_HoTenNV);
		
		panel_12 = new JPanel();
		panel_12.setBackground(Color.WHITE);
		panel_9.add(panel_12);
		panel_12.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_3_2 = new JLabel("Mã khách hàng:");
		lblNewLabel_3_2.setForeground(ColorConstants.PRIMARY_COLOR);
		lblNewLabel_3_2.setFont(new Font("Arial", Font.BOLD, 18));
		panel_12.add(lblNewLabel_3_2);
		
		lbl_MaKH = new JLabel("");
		lbl_MaKH.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_12.add(lbl_MaKH);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.WHITE);
		panel_9.add(panel_13);
		panel_13.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_3_3 = new JLabel("Họ tên khách hàng:");
		lblNewLabel_3_3.setForeground(ColorConstants.PRIMARY_COLOR);
		lblNewLabel_3_3.setFont(new Font("Arial", Font.BOLD, 18));
		panel_13.add(lblNewLabel_3_3);
		
		lbl_HoTenKH = new JLabel("");
		lbl_HoTenKH.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_13.add(lbl_HoTenKH);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.WHITE);
		panel_9.add(panel_14);
		panel_14.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_3_4 = new JLabel("Số điện thoại:");
		lblNewLabel_3_4.setForeground(ColorConstants.PRIMARY_COLOR);
		lblNewLabel_3_4.setFont(new Font("Arial", Font.BOLD, 18));
		panel_14.add(lblNewLabel_3_4);
		
		lbl_SoDT = new JLabel("");
		lbl_SoDT.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_14.add(lbl_SoDT);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.WHITE);
		panel_9.add(panel_15);
		panel_15.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_3_5 = new JLabel("Hình thức thanh toán:");
		lblNewLabel_3_5.setForeground(ColorConstants.PRIMARY_COLOR);
		lblNewLabel_3_5.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15.add(lblNewLabel_3_5);
		
		lbl_HinhThucThanhToan = new JLabel("");
		lbl_HinhThucThanhToan.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_15.add(lbl_HinhThucThanhToan);
		
		JPanel panel_15_1 = new JPanel();
		panel_15_1.setBackground(Color.WHITE);
		panel_9.add(panel_15_1);
		panel_15_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_3_5_1 = new JLabel("Loại hóa đơn:");
		lblNewLabel_3_5_1.setForeground(new Color(70, 130, 169));
		lblNewLabel_3_5_1.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15_1.add(lblNewLabel_3_5_1);
		
		comboBox_LoaiHD = new JComboBox();
		comboBox_LoaiHD.setFont(new Font("Arial", Font.PLAIN, 18));
		comboBox_LoaiHD.setModel(new DefaultComboBoxModel(new String[] {"Thanh toán ", "Giữ chỗ"}));
		panel_15_1.add(comboBox_LoaiHD);
		
		JLabel lbl_HinhThucThanhToan_1 = new JLabel("");
		lbl_HinhThucThanhToan_1.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_15_1.add(lbl_HinhThucThanhToan_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setPreferredSize(new Dimension(0, 400)); 
		panel_1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
		panel_Right.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setBackground(Color.WHITE);
		panel_5_1.setBorder(new EmptyBorder(0, 5, 0, 5));
		panel_5_1.setPreferredSize(new Dimension(0, 45));
		panel_5_1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
		panel_4.add(panel_5_1);
		panel_5_1.setLayout(new BoxLayout(panel_5_1, BoxLayout.X_AXIS));
		
		JPanel panel_18 = new JPanel();
		panel_18.setPreferredSize(new Dimension(210, 0)); 
		panel_18.setMaximumSize(new Dimension(210, Integer.MAX_VALUE)); 
		panel_5_1.add(panel_18);
		panel_18.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Tiền khách đưa");
		panel_18.add(lblNewLabel_1);
		lblNewLabel_1.setBorder(new EmptyBorder(5, 10, 5, 20));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(ColorConstants.PRIMARY_COLOR);
		
		Component horizontalStrut_1_4_1_1 = Box.createHorizontalStrut(10);
		panel_5_1.add(horizontalStrut_1_4_1_1);
		
		JPanel panel_19 = new JPanel();
		panel_5_1.add(panel_19);
		panel_19.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1_1 = new JLabel("Tiền trả lại khách");
		panel_19.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setOpaque(true);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1.setBorder(new EmptyBorder(5, 10, 5, 20));
		lblNewLabel_1_1.setBackground(new Color(70, 130, 169));
		
		JPanel panel_17 = new JPanel();
		panel_17.setBackground(Color.WHITE);
		panel_17.setBorder(new EmptyBorder(10, 5, 0, 5));
		panel_4.add(panel_17);
		panel_17.setPreferredSize(new Dimension(0, 45));
		panel_17.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
		panel_17.setLayout(new BoxLayout(panel_17, BoxLayout.X_AXIS));
		
		txt_TienKhachDua = new JTextField();
		panel_17.add(txt_TienKhachDua);
		txt_TienKhachDua.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_TienKhachDua.setColumns(10);
		
		Component horizontalStrut_1_4_1_1_1 = Box.createHorizontalStrut(10);
		panel_17.add(horizontalStrut_1_4_1_1_1);
		
		txt_TienTraLai = new JTextField();
		txt_TienTraLai.setEditable(false);
		txt_TienTraLai.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_TienTraLai.setColumns(10);
		panel_17.add(txt_TienTraLai);
		
		panel_Tien = new JPanel();
		panel_Tien.setBackground(Color.WHITE);
		panel_Tien.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_Tien.setLayout(new BoxLayout(panel_Tien, BoxLayout.X_AXIS));
		panel_4.add(panel_Tien);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setPreferredSize(new Dimension(0, 140)); 
		panel_3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
		panel_1.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setPreferredSize(new Dimension(0, 35)); 
		panel_5.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		panel_3.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		
		JLabel lblNewLabel = new JLabel("Gợi ý tiền mặt");
		lblNewLabel.setBorder(new EmptyBorder(5, 10, 5, 20));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		panel_5.add(lblNewLabel);
		
		panel_GoiYTien = new JPanel();
		panel_GoiYTien.setBorder(new EmptyBorder(0, 10, 10, 10));
		panel_GoiYTien.setBackground(Color.WHITE);
		panel_GoiYTien.setPreferredSize(new Dimension(0, 150)); 
		panel_GoiYTien.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
		panel_3.add(panel_GoiYTien);
		panel_GoiYTien.setLayout(new BoxLayout(panel_GoiYTien, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(2, 0, 0, 0, ColorConstants.PRIMARY_COLOR));
		panel_2.setBackground(Color.WHITE);
		panel_2.setPreferredSize(new Dimension(0, 65)); 
		panel_2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		panel_Right.add(panel_2);
		
		btn_QuayLai = new PrimaryButton("Quay lại", "/Image/arrow-back-up.png");
		btn_QuayLai.setFont(new Font("Arial", Font.BOLD, 18));
		btn_QuayLai.setInsets(new Insets(4, 6, 4, 6));
		btn_QuayLai.setBorderRadius(10);
		btn_QuayLai.setIconTextGap(5);
		btn_QuayLai.setIconSize(22, 22);
		btn_QuayLai.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn_QuayLai.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		btn_QuayLai.setPreferredSize(new Dimension(135, 40));
		panel_2.add(btn_QuayLai);
		
		btn_XemTruoc = new PrimaryButton("Xem trước", "/Image/file-description.png");
		btn_XemTruoc.setFont(new Font("Arial", Font.BOLD, 18));
		btn_XemTruoc.setInsets(new Insets(4, 6, 4, 6));
		btn_XemTruoc.setBorderRadius(10);
		btn_XemTruoc.setIconTextGap(5);
		btn_XemTruoc.setIconSize(22, 22);
		btn_XemTruoc.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn_XemTruoc.setPreferredSize(new Dimension(135, 40));
		btn_XemTruoc.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		panel_2.add(btn_XemTruoc);
		btn_XemTruoc.setVisible(false);
		
		btn_ThuTien = new PrimaryButton("Thu tiền", "/Image/file-check.png");
		btn_ThuTien.setFont(new Font("Arial", Font.BOLD, 18));
		btn_ThuTien.setInsets(new Insets(4, 6, 4, 6));
		btn_ThuTien.setBorderRadius(10);
		btn_ThuTien.setIconTextGap(5);
		btn_ThuTien.setIconSize(22, 22);
		btn_ThuTien.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn_ThuTien.setPreferredSize(new Dimension(135, 40));
		btn_QuayLai.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		panel_2.add(btn_ThuTien);
	}
	
	public PrimaryButton getBtn_QuayLai() {
		return btn_QuayLai;
	}

	public PrimaryButton getBtn_XemTruoc() {
		return btn_XemTruoc;
	}

	public PrimaryButton getBtn_ThuTien() {
		return btn_ThuTien;
	}

	public JPanel getThanhToan_View() {
		return contentPane;
	}
	public JTable getDanhSachHoaDonJTable() {
		return danhSachHoaDonJTable;
	}
	public DefaultTableModel getDanhSachHoaDonModel() {
		return danhSachHoaDonModel;
	}
	public JLabel getLbl_MaNV() {
		return lbl_MaNV;
	}
	public JLabel getLbl_HoTenNV() {
		return lbl_HoTenNV;
	}
	public JLabel getLbl_HoTenKH() {
		return lbl_HoTenKH;
	}
	public JLabel getLbl_SoDT() {
		return lbl_SoDT;
	}
	public JLabel getLbl_HinhThucThanhToan() {
		return lbl_HinhThucThanhToan;
	}
	public JLabel getLbl_MaKH() {
		return lbl_MaKH;
	}
	public JLabel getLbl_SauThue() {
		return lbl_SauThue;
	}
	public JLabel getLbl_TongTien() {
		return lbl_TongTien;
	}
	public JLabel getLbl_TongTienThanhToan() {
		return lbl_TongTienThanhToan;
	}
	public JPanel getPanel_GoiYTien() {
		return panel_GoiYTien;
	}
	public JTextField getTxt_TienKhachDua() {
		return txt_TienKhachDua;
	}
	public JTextField getTxt_TienTraLai() {
		return txt_TienTraLai;
	}
	public JPanel getPanel_Tien() {
		return panel_Tien;
	}
	public PrimaryButton getBtn_ThemKhuyenMai() {
		return btn_ThemKhuyenMai;
	}
	public PrimaryButton getBtn_ThemGhiChu() {
		return btn_ThemGhiChu;
	}
	public JComboBox getComboBox_LoaiHD() {
		return comboBox_LoaiHD;
	}
	
}
