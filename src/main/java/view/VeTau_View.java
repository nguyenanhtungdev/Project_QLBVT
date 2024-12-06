package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import other.DangerPrimaryButton;
import other.PrimaryButton;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import constant.ColorConstants;

import javax.swing.border.CompoundBorder;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;

public class VeTau_View extends View {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_maVT;
	private JTextField txt_SDT;
	private JTextField txt_CCCD;
	private JTextField txt_Email;
	private JTable danhSachVeTau;
	private DefaultTableModel danhSachVeTauModel;
	private String title[] = { "STT", "Mã vé tàu", "Loại vé", "Mã ghế tàu", "Mã KH", "Họ tên", "SĐT", "Email",
			"Giới tính", "CCCD", "Ngày sinh", "Loại KH", "Chi Tiết","Giá vé" };
	private PrimaryButton btn_XoaTrang;
	private PrimaryButton btn_CapNhatTT;
	private PrimaryButton btn_XoaVeTau;
	private PrimaryButton btn_ThanhToan;
	private JTextField txt_MaKH;
	private JTextField txt_HoTen;
	private JTextField txt_NgaySinh;
	private JComboBox comboBox_LoaiKH;
	private JComboBox comboBox_GioiTinh;
	private JLabel lbl_TongSoVe;
	private DangerPrimaryButton btn_HuyBo;
	private JLabel lblTienTamTinh;
	private JLabel lbl_ThoiGianGiuVe;

	// Thêm sự kiện cho lớp thanh toán view
	public void addSuKien(ActionListener listener, FocusListener focusListener, KeyListener keyListener) {
		btn_ThanhToan.addActionListener(listener);
		btn_XoaTrang.addActionListener(listener);
		btn_XoaVeTau.addActionListener(listener);
		btn_HuyBo.addActionListener(listener);
		btn_CapNhatTT.addActionListener(listener);

		txt_HoTen.addFocusListener(focusListener);
		txt_Email.addFocusListener(focusListener);
		txt_SDT.addFocusListener(focusListener);
		txt_CCCD.addFocusListener(focusListener);
		txt_NgaySinh.addFocusListener(focusListener);

		txt_HoTen.addKeyListener(keyListener);
		txt_Email.addKeyListener(keyListener);
		txt_SDT.addKeyListener(keyListener);
		txt_CCCD.addKeyListener(keyListener);
		txt_NgaySinh.addKeyListener(keyListener);
	}

	public void addSuKienTable(MouseListener mouseListener) {
		danhSachVeTau.addMouseListener(mouseListener);
	}

	public VeTau_View(String name, String imagePath) {
		super(name, imagePath);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel panel_top = new JPanel();
		panel_top.setPreferredSize(new Dimension(0, 200));
		panel_top.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
		panel_top.setBackground(Color.WHITE);
		contentPane.add(panel_top);
		panel_top.setLayout(new BoxLayout(panel_top, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(0, 50));
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		panel_top.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		JLabel lblNewLabel_7 = new JLabel("Thông tin khách hàng");
		panel.add(lblNewLabel_7);
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_7.setBorder(new EmptyBorder(5, 10, 5, 20));
		lblNewLabel_7.setBackground(new Color(70, 130, 169));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new EmptyBorder(0, 20, 0, 0));
		panel_top.add(panel_1);
		panel_1.setPreferredSize(new Dimension(0, 190));
		panel_1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 190));
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JPanel panel_InputThongTin = new JPanel();
		panel_InputThongTin
				.setBorder(new CompoundBorder(new LineBorder(new Color(70, 130, 169)), new EmptyBorder(5, 5, 10, 5)));
		panel_InputThongTin.setBackground(Color.WHITE);
		panel_1.add(panel_InputThongTin);
		panel_InputThongTin.setLayout(new BoxLayout(panel_InputThongTin, BoxLayout.Y_AXIS));

		JPanel panel_InputThongTin1 = new JPanel();
		panel_InputThongTin1.setBackground(Color.WHITE);
		panel_InputThongTin.add(panel_InputThongTin1);
		panel_InputThongTin1.setLayout(new BoxLayout(panel_InputThongTin1, BoxLayout.X_AXIS));

		JPanel panel_TT1 = new JPanel();
		panel_InputThongTin1.add(panel_TT1);
		panel_TT1.setLayout(new BoxLayout(panel_TT1, BoxLayout.Y_AXIS));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.WHITE);
		panel_TT1.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.X_AXIS));

		JPanel panel_TT2 = new JPanel();
		panel_InputThongTin1.add(panel_TT2);
		panel_TT2.setLayout(new BoxLayout(panel_TT2, BoxLayout.Y_AXIS));

		JPanel panel_9_1 = new JPanel();
		panel_9_1.setBackground(Color.WHITE);
		panel_TT2.add(panel_9_1);
		panel_9_1.setLayout(new BoxLayout(panel_9_1, BoxLayout.Y_AXIS));

		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.WHITE);
		panel_9_1.add(panel_15);
		panel_15.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lbl_CCCD = new JLabel("Mã vé tàu");
		lbl_CCCD.setHorizontalAlignment(SwingConstants.LEFT);
		panel_15.add(lbl_CCCD);
		lbl_CCCD.setForeground(new Color(70, 130, 169));
		lbl_CCCD.setFont(new Font("Arial", Font.BOLD, 18));

		txt_maVT = new JTextField();
		txt_maVT.setEnabled(false);
		txt_maVT.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_maVT.setColumns(10);
		panel_9_1.add(txt_maVT);

		Component horizontalStrut_1_4_1_1 = Box.createHorizontalStrut(10);
		panel_InputThongTin1.add(horizontalStrut_1_4_1_1);

		JPanel panel_9 = new JPanel();
		panel_InputThongTin1.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		JPanel panel_15_1 = new JPanel();
		panel_15_1.setBackground(Color.WHITE);
		panel_9.add(panel_15_1);
		panel_15_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lbl_CCCD_1 = new JLabel("Mã khách hàng");
		lbl_CCCD_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_CCCD_1.setForeground(new Color(70, 130, 169));
		lbl_CCCD_1.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15_1.add(lbl_CCCD_1);

		txt_MaKH = new JTextField();
		txt_MaKH.setEnabled(false);
		txt_MaKH.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_MaKH.setColumns(10);
		panel_9.add(txt_MaKH);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(10);
		panel_InputThongTin1.add(horizontalStrut_1_1);

		JPanel panel_TT3 = new JPanel();
		panel_InputThongTin1.add(panel_TT3);
		panel_TT3.setLayout(new BoxLayout(panel_TT3, BoxLayout.Y_AXIS));

		JPanel panel_15_2 = new JPanel();
		panel_15_2.setBackground(Color.WHITE);
		panel_TT3.add(panel_15_2);
		panel_15_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_2_2 = new JLabel("Số điện thoại");
		lblNewLabel_2_2.setForeground(new Color(70, 130, 169));
		lblNewLabel_2_2.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15_2.add(lblNewLabel_2_2);

		txt_SDT = new JTextField();
		txt_SDT.setEnabled(false);
		txt_SDT.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_SDT.setColumns(10);
		panel_TT3.add(txt_SDT);

		Component horizontalStrut_1_2 = Box.createHorizontalStrut(10);
		panel_InputThongTin1.add(horizontalStrut_1_2);

		JPanel panel_TT4 = new JPanel();
		panel_InputThongTin1.add(panel_TT4);
		panel_TT4.setLayout(new BoxLayout(panel_TT4, BoxLayout.Y_AXIS));

		JPanel panel_15_2_1 = new JPanel();
		panel_15_2_1.setBackground(Color.WHITE);
		panel_TT4.add(panel_15_2_1);
		panel_15_2_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_2_2_1 = new JLabel("CCCD");
		lblNewLabel_2_2_1.setForeground(new Color(70, 130, 169));
		lblNewLabel_2_2_1.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15_2_1.add(lblNewLabel_2_2_1);

		txt_CCCD = new JTextField();
		txt_CCCD.setEnabled(false);
		txt_CCCD.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_CCCD.setColumns(10);
		panel_TT4.add(txt_CCCD);

		JPanel panel_TT5 = new JPanel();
		panel_InputThongTin1.add(panel_TT5);
		panel_TT5.setLayout(new BoxLayout(panel_TT5, BoxLayout.Y_AXIS));

		JPanel panel_8 = new JPanel();
		panel_InputThongTin1.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));

		JPanel panel_InputThongTin2 = new JPanel();
		panel_InputThongTin2.setBackground(Color.WHITE);
		panel_InputThongTin.add(panel_InputThongTin2);
		panel_InputThongTin2.setLayout(new BoxLayout(panel_InputThongTin2, BoxLayout.X_AXIS));

		JPanel panel_13 = new JPanel();
		panel_InputThongTin2.add(panel_13);
		panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.Y_AXIS));

		JPanel panel_15_2_1_1_1 = new JPanel();
		panel_15_2_1_1_1.setBackground(Color.WHITE);
		panel_13.add(panel_15_2_1_1_1);
		panel_15_2_1_1_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_2_2_1_1_1 = new JLabel("Họ và tên");
		lblNewLabel_2_2_1_1_1.setForeground(new Color(70, 130, 169));
		lblNewLabel_2_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15_2_1_1_1.add(lblNewLabel_2_2_1_1_1);

		txt_HoTen = new JTextField();
		txt_HoTen.setEnabled(false);
		txt_HoTen.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_HoTen.setColumns(10);
		panel_13.add(txt_HoTen);

		Component horizontalStrut_1_4_1 = Box.createHorizontalStrut(10);
		panel_InputThongTin2.add(horizontalStrut_1_4_1);

		JPanel panel_TT6 = new JPanel();
		panel_InputThongTin2.add(panel_TT6);
		panel_TT6.setLayout(new BoxLayout(panel_TT6, BoxLayout.Y_AXIS));

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.WHITE);
		panel_TT6.add(panel_12);
		panel_12.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setForeground(ColorConstants.PRIMARY_COLOR);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		panel_12.add(lblNewLabel_1);

		txt_Email = new JTextField();
		txt_Email.setEnabled(false);
		txt_Email.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_Email.setColumns(10);
		panel_TT6.add(txt_Email);

		Component horizontalStrut_1_4 = Box.createHorizontalStrut(10);
		panel_InputThongTin2.add(horizontalStrut_1_4);

		JPanel panel_14 = new JPanel();
		panel_InputThongTin2.add(panel_14);
		panel_14.setLayout(new BoxLayout(panel_14, BoxLayout.Y_AXIS));

		JPanel panel_15_2_1_2_1_2 = new JPanel();
		panel_15_2_1_2_1_2.setBackground(Color.WHITE);
		panel_14.add(panel_15_2_1_2_1_2);
		panel_15_2_1_2_1_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_2_2_1_2_1_2 = new JLabel("Ngày sinh");
		lblNewLabel_2_2_1_2_1_2.setForeground(new Color(70, 130, 169));
		lblNewLabel_2_2_1_2_1_2.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15_2_1_2_1_2.add(lblNewLabel_2_2_1_2_1_2);

		txt_NgaySinh = new JTextField();
		txt_NgaySinh.setEnabled(false);
		txt_NgaySinh.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_NgaySinh.setColumns(10);
		panel_14.add(txt_NgaySinh);

		Component horizontalStrut_1_5_1 = Box.createHorizontalStrut(10);
		panel_InputThongTin2.add(horizontalStrut_1_5_1);

		JPanel panel_TT7 = new JPanel();
		panel_InputThongTin2.add(panel_TT7);
		panel_TT7.setLayout(new BoxLayout(panel_TT7, BoxLayout.Y_AXIS));

		JPanel panel_15_2_1_2 = new JPanel();
		panel_15_2_1_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_15_2_1_2.setBackground(Color.WHITE);
		panel_TT7.add(panel_15_2_1_2);
		panel_15_2_1_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_2_2_1_2 = new JLabel("Giới tính");
		lblNewLabel_2_2_1_2.setForeground(new Color(70, 130, 169));
		lblNewLabel_2_2_1_2.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15_2_1_2.add(lblNewLabel_2_2_1_2);

		String gioTinh[] = { "trống", "Nam", "Nữ" };
		comboBox_GioiTinh = new JComboBox(gioTinh);
		comboBox_GioiTinh.setEnabled(false);
		comboBox_GioiTinh.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_TT7.add(comboBox_GioiTinh);

		JPanel panel_TT8 = new JPanel();
		panel_InputThongTin2.add(panel_TT8);
		panel_TT8.setLayout(new BoxLayout(panel_TT8, BoxLayout.Y_AXIS));

		Component horizontalStrut_1_6 = Box.createHorizontalStrut(10);
		panel_InputThongTin2.add(horizontalStrut_1_6);

		JPanel panel_TT9 = new JPanel();
		panel_InputThongTin2.add(panel_TT9);
		panel_TT9.setLayout(new BoxLayout(panel_TT9, BoxLayout.Y_AXIS));

		JPanel panel_15_2_1_2_1_1 = new JPanel();
		panel_15_2_1_2_1_1.setBackground(Color.WHITE);
		panel_TT9.add(panel_15_2_1_2_1_1);
		panel_15_2_1_2_1_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_2_2_1_2_1_1 = new JLabel("Loại khách hàng");
		lblNewLabel_2_2_1_2_1_1.setForeground(new Color(70, 130, 169));
		lblNewLabel_2_2_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15_2_1_2_1_1.add(lblNewLabel_2_2_1_2_1_1);

		String loaiKH[] = {"Khách thường", "Trẻ em", "Học sinh", "Sinh viên", "Người già", "Khuyết tật"};
		comboBox_LoaiKH = new JComboBox(loaiKH);
		comboBox_LoaiKH.setEnabled(false);
		comboBox_LoaiKH.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_TT9.add(comboBox_LoaiKH);

		JPanel panel_TT10 = new JPanel();
		panel_InputThongTin2.add(panel_TT10);
		panel_TT10.setLayout(new BoxLayout(panel_TT10, BoxLayout.Y_AXIS));

		JPanel panel_11 = new JPanel();
		panel_InputThongTin2.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.Y_AXIS));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(0, 15, 0, 15));
		panel_3.setBackground(Color.WHITE);
		panel_3.setPreferredSize(new Dimension(180, 0));
		panel_3.setMaximumSize(new Dimension(180, Integer.MAX_VALUE));
		panel_1.add(panel_3);

		btn_XoaTrang = new PrimaryButton("Làm mới", "/Image/restore.png");

		btn_XoaTrang.setInsets(new Insets(4, 6, 4, 6));
		btn_XoaTrang.setBorderRadius(10);
		btn_XoaTrang.setIconTextGap(5);
		btn_XoaTrang.setIconSize(22, 22);
		btn_XoaTrang.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn_XoaTrang.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		btn_XoaTrang.setFont(new Font("Arial", Font.BOLD, 18));
		panel_3.add(btn_XoaTrang);

		btn_CapNhatTT = new PrimaryButton("Cập nhật", "/Image/icon_ChinhSua.png");
		btn_CapNhatTT.setInsets(new Insets(4, 6, 4, 6));
		btn_CapNhatTT.setBorderRadius(10);
		btn_CapNhatTT.setIconTextGap(5);
		btn_CapNhatTT.setIconSize(22, 22);
		btn_CapNhatTT.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn_CapNhatTT.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

		Component horizontalStrut_1_3_1 = Box.createVerticalStrut(20);
		panel_3.add(horizontalStrut_1_3_1);
		btn_CapNhatTT.setFont(new Font("Arial", Font.BOLD, 18));
		panel_3.add(btn_CapNhatTT);

		btn_XoaVeTau = new PrimaryButton("Xóa trắng", "/Image/icon_XoaTrang.png");
		btn_XoaVeTau.setText("Xóa vé tàu");
//		btn_XoaTrang.setBorder(new EmptyBorder(4, 6, 4, 6));
		btn_XoaVeTau.setInsets(new Insets(4, 6, 4, 6));
//		btn_XoaTrang.setHeSoBoGoc(10);
		btn_XoaVeTau.setBorderRadius(10);
//		btn_XoaTrang.setKhoangCachIcon(5);
		btn_XoaVeTau.setIconTextGap(5);
		btn_XoaVeTau.setIconSize(22, 22);
		btn_XoaVeTau.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn_XoaVeTau.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

		Component horizontalStrut_1_3_1_1 = Box.createVerticalStrut(20);
		panel_3.add(horizontalStrut_1_3_1_1);
		btn_XoaVeTau.setFont(new Font("Arial", Font.BOLD, 18));
		panel_3.add(btn_XoaVeTau);

		JPanel panel_center = new JPanel();
		panel_center.setBackground(Color.WHITE);
		contentPane.add(panel_center);
		panel_center.setLayout(new BoxLayout(panel_center, BoxLayout.Y_AXIS));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setPreferredSize(new Dimension(0, 60));
		panel_4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		panel_center.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));

		JLabel lblNewLabel_7_1 = new JLabel("Danh sách vé tàu");
		lblNewLabel_7_1.setOpaque(true);
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_7_1.setBorder(new EmptyBorder(5, 10, 5, 20));
		lblNewLabel_7_1.setBackground(new Color(70, 130, 169));
		panel_4.add(lblNewLabel_7_1);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(0, 20, 5, 15));
		panel_5.setBackground(Color.WHITE);
		panel_5.setPreferredSize(new Dimension(0, 530));
		panel_5.setMaximumSize(new Dimension(Integer.MAX_VALUE, 530));
		panel_center.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));

		// Khởi tạo DefaultTableModel không cho phép chỉnh sửa ô
		danhSachVeTauModel = new DefaultTableModel(title, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		danhSachVeTau = new JTable(danhSachVeTauModel);
		danhSachVeTau.setRowHeight(25);
		danhSachVeTau.setFont(new Font("Arial", Font.PLAIN, 16));
		danhSachVeTau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// Vô hiệu hóa chế độ tự động điều chỉnh kích thước cột
		// Tạo renderer để căn giữa nội dung
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// Gán renderer căn giữa cho tất cả các cột trong bảng
		for (int i = 0; i < danhSachVeTau.getColumnCount(); i++) {
			danhSachVeTau.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		// Tùy chỉnh font chữ cho header của JTable
		JTableHeader header = danhSachVeTau.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 16));

		// Thiết lập chiều rộng cho các cột
		danhSachVeTau.getColumnModel().getColumn(0).setPreferredWidth(50);
		danhSachVeTau.getColumnModel().getColumn(1).setPreferredWidth(130);
		danhSachVeTau.getColumnModel().getColumn(2).setPreferredWidth(110);
		danhSachVeTau.getColumnModel().getColumn(3).setPreferredWidth(130);
		danhSachVeTau.getColumnModel().getColumn(4).setPreferredWidth(140);
		danhSachVeTau.getColumnModel().getColumn(5).setPreferredWidth(180);
		danhSachVeTau.getColumnModel().getColumn(6).setPreferredWidth(140);
		danhSachVeTau.getColumnModel().getColumn(7).setPreferredWidth(170);
		danhSachVeTau.getColumnModel().getColumn(8).setPreferredWidth(90);
		danhSachVeTau.getColumnModel().getColumn(9).setPreferredWidth(160);
		danhSachVeTau.getColumnModel().getColumn(10).setPreferredWidth(140);
		danhSachVeTau.getColumnModel().getColumn(11).setPreferredWidth(100);
		danhSachVeTau.getColumnModel().getColumn(12).setPreferredWidth(300);
		danhSachVeTau.getColumnModel().getColumn(13).setPreferredWidth(150);

		danhSachVeTau.getColumnModel().getColumn(12).setCellRenderer(new MultiLineCellRenderer());
		header.setReorderingAllowed(false);
		// Thiết lập chiều rộng và khóa không cho thay đổi kích thước cho các cột
		for (int i = 0; i < danhSachVeTau.getColumnCount(); i++) {
			danhSachVeTau.getColumnModel().getColumn(i).setResizable(false); // Khóa không cho thay đổi kích thước
		}

		// Thêm vào JScrollPane
		JScrollPane scrollPane = new JScrollPane(danhSachVeTau);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_5.add(scrollPane);

		JPanel panel_bottom = new JPanel();
		panel_bottom.setBorder(new EmptyBorder(0, 10, 0, 50));
		panel_bottom.setPreferredSize(new Dimension(0, 60));
		panel_bottom.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		panel_bottom.setBackground(Color.WHITE);
		contentPane.add(panel_bottom);
		panel_bottom.setLayout(new BoxLayout(panel_bottom, BoxLayout.X_AXIS));

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_bottom.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_6.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));

		JLabel lblNewLabel_3 = new JLabel("Tổng số vé:");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 18));
		panel_2.add(lblNewLabel_3);

		lbl_TongSoVe = new JLabel("0");
		lbl_TongSoVe.setFont(new Font("Arial", Font.BOLD, 18));
		panel_2.add(lbl_TongSoVe);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.WHITE);
		panel_6.add(panel_2_1);
		panel_2_1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));

		JLabel lblNewLabel_3_1 = new JLabel("Tổng tiền tạm tính:");
		lblNewLabel_3_1.setFont(new Font("Arial", Font.BOLD, 18));
		panel_2_1.add(lblNewLabel_3_1);

		lblTienTamTinh = new JLabel("0");
		lblTienTamTinh.setForeground(Color.RED);
		lblTienTamTinh.setFont(new Font("Arial", Font.BOLD, 18));
		panel_2_1.add(lblTienTamTinh);

		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBackground(Color.WHITE);
		panel_6.add(panel_2_2);
		panel_2_2.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));

		JLabel lblNewLabel_3_2 = new JLabel("Thời gian giữ vé");
		lblNewLabel_3_2.setFont(new Font("Arial", Font.BOLD, 18));
		panel_2_2.add(lblNewLabel_3_2);

		lbl_ThoiGianGiuVe = new JLabel("  ");
		lbl_ThoiGianGiuVe.setForeground(Color.RED);
		lbl_ThoiGianGiuVe.setFont(new Font("Arial", Font.BOLD, 18));
		panel_2_2.add(lbl_ThoiGianGiuVe);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_bottom.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));

		btn_HuyBo = new DangerPrimaryButton("Hủy bỏ", "/Image/icon_delete.png");
//		btn_HuyBo.setBorder(new EmptyBorder(6, 20, 6, 20));
		btn_HuyBo.setInsets(new Insets(6, 20, 6, 20));
//		btn_HuyBo.setHeSoBoGoc(10);
		btn_HuyBo.setBorderRadius(10);
//		btn_HuyBo.setKhoangCachIcon(8);
		btn_HuyBo.setIconTextGap(8);
		btn_HuyBo.setIconSize(24, 24);
		btn_HuyBo.setAlignmentX(Component.LEFT_ALIGNMENT);
//		btn_HuyBo.setColor_1(ColorConstants.DELETE);
//		btn_HuyBo.setColor_2(ColorConstants.DELETE_HOVER);
		btn_HuyBo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		btn_HuyBo.setFont(new Font("Arial", Font.BOLD, 20));

		panel_7.add(btn_HuyBo);

		btn_ThanhToan = new PrimaryButton("Thanh toán", "/Image/icon_ThanhToan.png");
		btn_ThanhToan.setInsets(new Insets(6, 20, 6, 20));
		btn_ThanhToan.setBorderRadius(10);
		btn_ThanhToan.setIconTextGap(5);
		btn_ThanhToan.setIconSize(24, 24);
		btn_ThanhToan.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn_ThanhToan.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		btn_ThanhToan.setFont(new Font("Arial", Font.BOLD, 20));

		panel_7.add(btn_ThanhToan);
	}

	// Custom renderer với JTextArea để xuống dòng cho nội dung dài
	class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {
		public MultiLineCellRenderer() {
			setLineWrap(true);
			setWrapStyleWord(true);
			setOpaque(true); 
			setFont(new Font("Arial", Font.PLAIN, 16));
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
		
			setText(value != null ? value.toString() : "");
			if (isSelected) {
				setBackground(table.getSelectionBackground());
				setForeground(table.getSelectionForeground());
			} else {
				setBackground(table.getBackground());
				setForeground(table.getForeground());
			}
			setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
			int preferredHeight = getPreferredSize().height;
			if (table.getRowHeight(row) != preferredHeight) {
				table.setRowHeight(row, preferredHeight);
			}

			return this;
		}
	}

	public JButton getBtn_ThanhToan() {
		return btn_ThanhToan;
	}

	public JTable getDanhSachVeTau() {
		return danhSachVeTau;
	}

	public void setDanhSachVeTau(JTable danhSachVeTau) {
		this.danhSachVeTau = danhSachVeTau;
	}

	public DefaultTableModel getDanhSachVeTauModel() {
		return danhSachVeTauModel;
	}

	public void setDanhSachVeTauModel(DefaultTableModel danhSachVeTauModel) {
		this.danhSachVeTauModel = danhSachVeTauModel;
	}

	public JPanel getVeTau_View() {
		return contentPane;
	}

	public PrimaryButton getBtn_ThemTT() {
		return btn_XoaTrang;
	}

	public PrimaryButton getBtn_CapNhatTT() {
		return btn_CapNhatTT;
	}

	public PrimaryButton getBtn_XoaTrang() {
		return btn_XoaTrang;
	}

	public JTextField getTxt_SDT() {
		return txt_SDT;
	}

	public JTextField getTxt_CCCD() {
		return txt_CCCD;
	}

	public JTextField getTxt_Email() {
		return txt_Email;
	}

	public JTextField getTxt_maVT() {
		return txt_maVT;
	}

	public JTextField getTxt_MaKH() {
		return txt_MaKH;
	}

	public JTextField getTxt_HoTen() {
		return txt_HoTen;
	}

	public JTextField getTxt_NgaySinh() {
		return txt_NgaySinh;
	}

	public JComboBox getComboBox_LoaiKH() {
		return comboBox_LoaiKH;
	}

	public JComboBox getComboBox_GioiTinh() {
		return comboBox_GioiTinh;
	}

	public JLabel getLbl_TongSoVe() {
		return lbl_TongSoVe;
	}

	public DangerPrimaryButton getBtn_HuyBo() {
		return btn_HuyBo;
	}

	public JLabel getLblTienTamTinh() {
		return lblTienTamTinh;
	}

	public JLabel getLbl_ThoiGianGiuVe() {
		return lbl_ThoiGianGiuVe;
	}

	public PrimaryButton getBtn_XoaVeTau() {
		return btn_XoaVeTau;
	}
	
}
