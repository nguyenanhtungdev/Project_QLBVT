package view;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.StyleConstants.ColorConstants;

import other.DangerPrimaryButton;
import other.PrimaryButton;
import other.RoundedButton;
import view.VeTau_View.MultiLineCellRenderer;

import java.awt.Color;
import java.awt.Image;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import javax.swing.JButton;

public class QuanLyNhanVien_View extends View {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_HoTen;
	private JTextField txt_SDT;
	private JTextField txt_CCCD;
	private JTextField txt_MaNV;
	private JTextField txt_Email;
	private JTextField txt_NgaySinh;
	private JComboBox comboBox_GioiTinh;
	private PrimaryButton btn_XoaTrang;
	private PrimaryButton btn_CapNhatTT;
	private PrimaryButton btn_XemDsHD;
	private JTable danhSachNhanVienJtable;
	private DefaultTableModel danhSachNhanVienModel;
	private String title[] = { "STT", "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Email", "Địa chỉ",
			"CCCD", "Giới tính", "Hệ số lương", "Chức vụ", "Trạng thái", "Ngày vào làm" };
	private JTextField txt_Loc;
	private JRadioButton rdbtn_CCCD;
	private JRadioButton rdbtn_DienThoai;
	private JTextField txtDiaChi;
	private PrimaryButton btnXacnhan;
	private PrimaryButton btnHuybo;
	private JRadioButton rdbtn_TenNV;
	private PrimaryButton btnTimKiem;
	private JRadioButton rdbtn_TatCa;

//	public void addSuKien(ActionListener listener, FocusListener focusListener, KeyListener keyListener) {
//		btn_CapNhatTT.addActionListener(listener);
//		btn_XoaTrang.addActionListener(listener);
//		btn_XemDsHD.addActionListener(listener);
//		btn_TimKiem.addActionListener(listener);
//
//		txt_HoTen.addFocusListener(focusListener);
//		txt_Email.addFocusListener(focusListener);
//		txt_SDT.addFocusListener(focusListener);
//		txt_CCCD.addFocusListener(focusListener);
//		txt_NgaySinh.addFocusListener(focusListener);
//
//		txt_HoTen.addKeyListener(keyListener);
//		txt_Email.addKeyListener(keyListener);
//		txt_SDT.addKeyListener(keyListener);
//		txt_CCCD.addKeyListener(keyListener);
//		txt_NgaySinh.addKeyListener(keyListener);
//
////		comboBox_LocLoaiKH.addActionListener(listener);
//	}
//
//	public void addSuKienTable(MouseListener mouseListener) {
//		danhSachNhanVienJtable.addMouseListener(mouseListener);
//	}

	public QuanLyNhanVien_View(String name, String imagePath) {
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

		JLabel lblNewLabel_7 = new JLabel("Thông tin nhân viên");
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

		JLabel lbl_MaKH = new JLabel("Mã nhân viên");
		lbl_MaKH.setHorizontalAlignment(SwingConstants.LEFT);
		panel_15.add(lbl_MaKH);
		lbl_MaKH.setForeground(new Color(70, 130, 169));
		lbl_MaKH.setFont(new Font("Arial", Font.BOLD, 18));
		lbl_MaKH.setPreferredSize(new Dimension(130, 20));

		txt_MaNV = new JTextField();
		txt_MaNV.setEnabled(false);
		panel_9_1.add(txt_MaNV);
		txt_MaNV.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_MaNV.setColumns(10);

		Component horizontalStrut_1_4_1_1 = Box.createHorizontalStrut(10);
		panel_InputThongTin1.add(horizontalStrut_1_4_1_1);

		JPanel panel_9 = new JPanel();
		panel_InputThongTin1.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		JPanel panel_15_1 = new JPanel();
		panel_15_1.setBackground(Color.WHITE);
		panel_9.add(panel_15_1);
		panel_15_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblHoten = new JLabel("Họ tên");
		lblHoten.setHorizontalAlignment(SwingConstants.LEFT);
		lblHoten.setForeground(new Color(70, 130, 169));
		lblHoten.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15_1.add(lblHoten);

		txt_HoTen = new JTextField();
		txt_HoTen.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_HoTen.setColumns(10);
		panel_9.add(txt_HoTen);

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

		JLabel lblCCCD = new JLabel("CCCD");
		lblCCCD.setForeground(new Color(70, 130, 169));
		lblCCCD.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15_2_1.add(lblCCCD);

		txt_CCCD = new JTextField();
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

		JPanel panel_TT6 = new JPanel();
		panel_InputThongTin2.add(panel_TT6);
		panel_TT6.setLayout(new BoxLayout(panel_TT6, BoxLayout.Y_AXIS));

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.WHITE);
		panel_TT6.add(panel_12);
		panel_12.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setForeground(constant.ColorConstants.PRIMARY_COLOR);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		panel_12.add(lblNewLabel_1);

		txt_Email = new JTextField();
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

		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		lblNgaySinh.setForeground(new Color(70, 130, 169));
		lblNgaySinh.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15_2_1_2_1_2.add(lblNgaySinh);
		lblNgaySinh.setPreferredSize(new Dimension(130, 21));

		txt_NgaySinh = new JTextField();
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

		String gioTinh[] = { "Nam", "Nữ" };
		comboBox_GioiTinh = new JComboBox(gioTinh);
		comboBox_GioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		comboBox_GioiTinh.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_TT7.add(comboBox_GioiTinh);

		JPanel panel_TT8 = new JPanel();
		panel_InputThongTin2.add(panel_TT8);
		panel_TT8.setLayout(new BoxLayout(panel_TT8, BoxLayout.Y_AXIS));

		Component horizontalStrut_1_6 = Box.createHorizontalStrut(169);
		panel_InputThongTin2.add(horizontalStrut_1_6);

		JPanel panel_TT9 = new JPanel();
		panel_InputThongTin2.add(panel_TT9);
		panel_TT9.setLayout(new BoxLayout(panel_TT9, BoxLayout.Y_AXIS));

		JPanel panel_15_2_1_2_1_1 = new JPanel();
		panel_15_2_1_2_1_1.setBackground(Color.WHITE);
		panel_TT9.add(panel_15_2_1_2_1_1);
		panel_15_2_1_2_1_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setForeground(new Color(70, 130, 169));
		lblDiaChi.setFont(new Font("Arial", Font.BOLD, 18));
		panel_15_2_1_2_1_1.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDiaChi.setColumns(10);
		panel_TT9.add(txtDiaChi);

		JPanel panel_TT10 = new JPanel();
		panel_InputThongTin2.add(panel_TT10);
		panel_TT10.setLayout(new BoxLayout(panel_TT10, BoxLayout.Y_AXIS));

		JPanel panel_11 = new JPanel();
		panel_InputThongTin2.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.Y_AXIS));

		Component horizontalStrut_2 = Box.createHorizontalStrut(3);
		panel_1.add(horizontalStrut_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new CompoundBorder(new LineBorder(new Color(70, 130, 169), 0), new EmptyBorder(0, 10, 0, 10)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setPreferredSize(new Dimension(180, 0));
		panel_3.setMaximumSize(new Dimension(180, Integer.MAX_VALUE));
		panel_1.add(panel_3);

//		btn_XoaTrang = new PrimaryButton("Làm mới","/Image/restore.png");
//		btn_XoaTrang.setInsets(new Insets(4, 6, 4, 6));
//		btn_XoaTrang.setBorderRadius(10);
//		btn_XoaTrang.setIconTextGap(5);
//		btn_XoaTrang.setPreferredSize(new Dimension(150, 33));
//		btn_XoaTrang.setIconSize(24, 24);
//		btn_XoaTrang.setAlignmentX(Component.LEFT_ALIGNMENT);
//		btn_XoaTrang.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		Component vs_1_1 = Box.createVerticalStrut(20);
		panel_3.add(vs_1_1);

		btnXacnhan = new PrimaryButton("Cập nhật", "/Image/icon_ChinhSua.png");
		btnXacnhan.setPreferredSize(new Dimension(145, 40));
		btnXacnhan.setMaximumSize(new Dimension(2147483647, 40));
		btnXacnhan.setInsets(new Insets(4, 6, 4, 6));
		btnXacnhan.setIconTextGap(5);
		btnXacnhan.setBorderRadius(10);
		btnXacnhan.setAlignmentX(0.0f);
		panel_3.add(btnXacnhan);
		
		Component vs_1 = Box.createVerticalStrut(20);
		panel_3.add(vs_1);

		btnHuybo = new PrimaryButton("Làm mới", "/Image/restore.png");
		btnHuybo.setPreferredSize(new Dimension(145, 40));
		btnHuybo.setMaximumSize(new Dimension(2147483647, 40));
		btnHuybo.setInsets(new Insets(4, 6, 4, 6));
		btnHuybo.setIconTextGap(5);
		btnHuybo.setBorderRadius(10);
		btnHuybo.setAlignmentX(0.0f);
		panel_3.add(btnHuybo);
//		btn_XoaTrang.setFont(new Font("Arial", Font.BOLD, 18));
//		panel_3.add(btn_XoaTrang);
//		
//		iconBtn_XoaTrang = new ImageIcon(getClass().getResource("/Image/restore.png"));
//		iconBtn_XoaTrang.setInsets(new Insets(4, 6, 4, 6));
//		iconBtn_XoaTrang.setBorderRadius(10);
//		iconBtn_XoaTrang.setIconTextGap(5);
//		iconBtn_XoaTrang.setPreferredSize(new Dimension(150, 33));
//		iconBtn_XoaTrang.setIconSize(24, 24);
//
//		btn_CapNhatTT = new PrimaryButton("Cập nhật", "/Image/icon_ChinhSua.png");
//		btn_CapNhatTT.setInsets(new Insets(4, 6, 4, 6));
//		btn_CapNhatTT.setBorderRadius(10);
//		btn_CapNhatTT.setPreferredSize(new Dimension(150, 33));
//		btn_CapNhatTT.setIconTextGap(5);
//		btn_CapNhatTT.setIconSize(24, 24);
//		btn_CapNhatTT.setAlignmentX(Component.LEFT_ALIGNMENT);
//		btn_CapNhatTT.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
//
//		Component horizontalStrut_1_3_1 = Box.createVerticalStrut(18);
//		panel_3.add(horizontalStrut_1_3_1);
//		btn_CapNhatTT.setFont(new Font("Arial", Font.BOLD, 18));
//		panel_3.add(btn_CapNhatTT);
//
//		btn_XemDsHD = new PrimaryButton("Xem thêm", "/Image/excel.png");
//		btn_XemDsHD.setText("Xuất Excel");
//		btn_XemDsHD.setInsets(new Insets(4, 6, 4, 6));
//		btn_XemDsHD.setBorderRadius(10);
//		btn_XemDsHD.setIconTextGap(5);
//		btn_XemDsHD.setIconSize(22, 22);
//		btn_XemDsHD.setAlignmentX(Component.LEFT_ALIGNMENT);
//		btn_XemDsHD.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
//
//		Component horizontalStrut_1_3_1_1 = Box.createVerticalStrut(18);
//		panel_3.add(horizontalStrut_1_3_1_1);
//		btn_XemDsHD.setFont(new Font("Arial", Font.BOLD, 18));
//		panel_3.add(btn_XemDsHD);
//
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

		JLabel lblNewLabel_7_1 = new JLabel("Danh sách nhân viên");
		lblNewLabel_7_1.setOpaque(true);
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_7_1.setBorder(new EmptyBorder(5, 10, 5, 20));
		lblNewLabel_7_1.setBackground(new Color(70, 130, 169));
		panel_4.add(lblNewLabel_7_1);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EmptyBorder(0, 60, 0, 0));
		panel_7.setBackground(Color.WHITE);
		panel_4.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));

		txt_Loc = new JTextField();
		txt_Loc.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_Loc.setPreferredSize(new Dimension(0, 30));
		txt_Loc.setColumns(13);
		panel_7.add(txt_Loc);

		rdbtn_TenNV = new JRadioButton("Tên nhân viên");
		rdbtn_TenNV.setSelected(true);
		rdbtn_TenNV.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtn_TenNV.setBackground(Color.WHITE);
		panel_7.add(rdbtn_TenNV);

		rdbtn_DienThoai = new JRadioButton("Điện thoại");
		rdbtn_DienThoai.setBackground(Color.WHITE);
		rdbtn_DienThoai.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_7.add(rdbtn_DienThoai);

		rdbtn_CCCD = new JRadioButton("CCCD");
		rdbtn_CCCD.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtn_CCCD.setBackground(Color.WHITE);
		panel_7.add(rdbtn_CCCD);

		rdbtn_TatCa = new JRadioButton("Tất cả");
		rdbtn_TatCa.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtn_TatCa.setBackground(Color.WHITE);
		panel_7.add(rdbtn_TatCa);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtn_CCCD);
		buttonGroup.add(rdbtn_DienThoai);
		buttonGroup.add(rdbtn_TenNV);
		buttonGroup.add(rdbtn_TatCa);

		btnTimKiem = new PrimaryButton("Tìm kiếm", "/Image/search.png");
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 18));
		btnTimKiem.setInsets(new Insets(4, 6, 4, 6));
		btnTimKiem.setBorderRadius(10);
		btnTimKiem.setIconTextGap(5);
		btnTimKiem.setIconSize(22, 22);
		btnTimKiem.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnTimKiem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		btnTimKiem.setPreferredSize(new Dimension(155, 35));
		panel_7.add(btnTimKiem);

//		btn_TimKiem = new PrimaryButton("Lọc nhanh", "/Image/search.png");
//		panel_7.add(btn_TimKiem);
//		btn_TimKiem.setMaximumSize(new Dimension(2147483647, 50));
//		btn_TimKiem.setInsets(new Insets(4, 6, 4, 6));
//		btn_TimKiem.setIconTextGap(5);
//		btn_TimKiem.setFont(new Font("Arial", Font.BOLD, 18));
//		btn_TimKiem.setPreferredSize(new Dimension(150, 33));
//		btn_TimKiem.setBorderRadius(10);
//		btn_TimKiem.setAlignmentX(0.0f);

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(0, 20, 20, 15));
		panel_5.setBackground(Color.WHITE);
		panel_5.setPreferredSize(new Dimension(0, 530));
		panel_5.setMaximumSize(new Dimension(Integer.MAX_VALUE, 530));
		panel_center.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));

		danhSachNhanVienModel = new DefaultTableModel(title, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		danhSachNhanVienJtable = new JTable(danhSachNhanVienModel);
		danhSachNhanVienJtable.setRowHeight(25);
		danhSachNhanVienJtable.setFont(new Font("Arial", Font.PLAIN, 16));
		danhSachNhanVienJtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// Vô hiệu hóa chế độ tự động điều chỉnh kích
																			// thước cột

		// Tùy chỉnh font chữ cho header của JTable
		JTableHeader header = danhSachNhanVienJtable.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 18));

		danhSachNhanVienJtable.getColumnModel().getColumn(0).setPreferredWidth(50);
		danhSachNhanVienJtable.getColumnModel().getColumn(1).setPreferredWidth(140);
		danhSachNhanVienJtable.getColumnModel().getColumn(2).setPreferredWidth(140);
		danhSachNhanVienJtable.getColumnModel().getColumn(3).setPreferredWidth(140);
		danhSachNhanVienJtable.getColumnModel().getColumn(4).setPreferredWidth(140);
		danhSachNhanVienJtable.getColumnModel().getColumn(5).setPreferredWidth(140);
		danhSachNhanVienJtable.getColumnModel().getColumn(6).setPreferredWidth(140);
		danhSachNhanVienJtable.getColumnModel().getColumn(7).setPreferredWidth(140);
		danhSachNhanVienJtable.getColumnModel().getColumn(8).setPreferredWidth(140);
		danhSachNhanVienJtable.getColumnModel().getColumn(9).setPreferredWidth(140);
		danhSachNhanVienJtable.getColumnModel().getColumn(10).setPreferredWidth(140);
		danhSachNhanVienJtable.getColumnModel().getColumn(11).setPreferredWidth(140);
		danhSachNhanVienJtable.getColumnModel().getColumn(12).setPreferredWidth(140);

		header.setReorderingAllowed(false);
		// Thiết lập chiều rộng và khóa không cho thay đổi kích thước cho các cột
		for (int i = 0; i < danhSachNhanVienJtable.getColumnCount(); i++) {
			danhSachNhanVienJtable.getColumnModel().getColumn(i).setResizable(false); // Khóa không cho thay đổi kích
																						// thước
		}

		// Thêm vào JScrollPane
		JScrollPane scrollPane = new JScrollPane(danhSachNhanVienJtable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_5.add(scrollPane);
	}

	public JTextField getTxt_LocSDT() {
		return txt_Loc;
	}

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

	public JComboBox getComboBox_GioiTinh() {
		return comboBox_GioiTinh;
	}

//	public JComboBox getComboBox_LoaiKH() {
//		return comboBox_LoaiKH;
//	}

//	public PrimaryButton getBtn_XoaTrang() {
//		return btn_XoaTrang;
//	}
//
//	public PrimaryButton getBtn_CapNhatTT() {
//		return btn_CapNhatTT;
//	}
//
//	public PrimaryButton getBtn_XemDsHD() {
//		return btn_XemDsHD;
//	}
//
//	public PrimaryButton getBtn_TimKiem() {
//		return btn_TimKiem;
//	}

	public JTable getDanhSachNhanVienJtable() {
		return danhSachNhanVienJtable;
	}

	public DefaultTableModel getDanhSachNhanVienModel() {
		return danhSachNhanVienModel;
	}

	public JTextField getTxt_MaNV() {
		return txt_MaNV;
	}

	public void setTxt_MaNV(String maNv) {
		txt_MaNV.setText(maNv);
	}

	public JTextField getTxt_HoTen() {
		return txt_HoTen;
	}

	public JTextField getTxt_SDT() {
		return txt_SDT;
	}

	public JTextField getTxt_CCCD() {
		return txt_CCCD;
	}

	public JTextField getTxt_MaKH() {
		return txt_MaNV;
	}

	public JTextField getTxt_Email() {
		return txt_Email;
	}

	public JTextField getTxt_NgaySinh() {
		return txt_NgaySinh;
	}

	public JTextField getTxt_DiaChi() {
		return txtDiaChi;
	}

	public JTextField getTxt_Loc() {
		return txt_Loc;
	}

	public JRadioButton getRdbtn_TenNV() {
		return rdbtn_TenNV;
	}

	public JRadioButton getRdbtn_DienThoai() {
		return rdbtn_DienThoai;
	}

	public JRadioButton getRdbtn_CCCD() {
		return rdbtn_CCCD;
	}

	public JRadioButton getRdbtn_TatCa() {
		return rdbtn_TatCa;
	}

	public void addBtnXacNhan(ActionListener listener) {
		btnXacnhan.addActionListener(listener);
	}

//	public void addBtnCapNhat(ActionListener listener) {
//		btnCapNhat.addActionListener(listener); 
//	}
	public void addBtnHuyBo(ActionListener listener) {
		btnHuybo.addActionListener(listener);
	}

	public void addBtnTimKiem(ActionListener listener) {
		btnTimKiem.addActionListener(listener);
	}
}
