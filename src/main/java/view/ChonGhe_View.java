package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import other.DangerPrimaryButton;
import other.PrimaryButton;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import java.awt.Component;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import constant.ColorConstants;
import model.TinhThanh;

import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;

public class ChonGhe_View extends View {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_top;
	private JPanel panel_center;
	private JPanel panel_bottom;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JRadioButton rdbtn_KhuHoi;
	private JRadioButton rdbtn_MotChieu;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private PrimaryButton btnTimKiemChuyenTau;
	private JPanel panel_10;
	private ButtonGroup buttonGroup, buttonGroup_1;
	private JPanel panel_11;
	private JLabel lblGaDi;
	private JPanel panel_12;
	private JLabel lblGaDen;
	private JPanel panel_13;
	private ImageIcon icon;
	private JPanel panel_14;
	private Component horizontalStrut;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private JPanel panel_15;
	private JPanel panel_16;
	private JLabel lblNewLabel_2;
	private JPanel panel_17;
	private JLabel lblNewLabel_3;
	private JPanel panel_18;
	private JPanel panel_19;
	public JPanel panel_dsChuyenTau;
	private JLabel lblTTChuyenTauTimKiem;
	private JPanel panel_21;
	public JPanel panel_DsToaTau;
	private JLabel lblNewLabel_8;
	private JPanel panel_toaTau;
	private JPanel panel_28;
	private JLabel lblNewLabel_16;
	private JPanel panel_29;
	private JLabel lblNewLabel_15;
	private JPanel panel_chuaDsGheTau;
	private JPanel panel_31;
	private JPanel panel_32;
	public JPanel panel_DsGheTau;
	private JPanel panel_34;
	private JPanel panel_35;
	private JPanel panel_36;
	private JLabel lblNewLabel_18;
	private JLabel lbl_SoGheTau;
	private JPanel panel_37;
	private JPanel panel_38;
	private JLabel lblNewLabel_20;
	private JLabel lblNewLabel_21;
	private JPanel panel_39;
	private JPanel panel_40;
	private JLabel lblNewLabel_22;
	private JLabel lblNewLabel_23;
	private JPanel panel_41;
	private JPanel panel_42;
	private JLabel lblNewLabel_24;
	private JLabel lblNewLabel_25;
	private JLabel lbl_TongSoVeTamThoi;
	private JPanel panel_43;
	private PrimaryButton btn_ChonNhanh;
	private JPanel panel_44;
	private JComboBox combobox_KhungGio;
	private JLabel lblNewLabel_28;
	private JPanel panel_45;
	private JPanel panel_46;
	public JLabel lblSoTrang;
	private JPanel panel_47;
	private JLabel lblSoChuyenTau;
	private JButton btnTrangSau;
	private JButton btnTrangTruoc;
	private JComboBox<TinhThanh> jcombobox_gadi, jcombobox_gaden;
	private JDateChooser dateChooser_NgayDi;
	private JDateChooser dateChooser_NgayVe;
	private JDateChooser dateChooser_NgayVe_1;
	private JLabel lbl_TenToaTau;
	private PrimaryButton btn_LamMoi;
	private PrimaryButton btn_QuayLai;
	private JLabel lbl_VeChieuVe;
	private JLabel lbl_VeChieuDi;
	private JPanel panel_20;
	private PrimaryButton btn_TiepTheo;
	private JPanel panel_22;
	private JLabel lbl_SoVeChieuDi;
	private JPanel panel_23;
	private JLabel lbl_SoVeChieuVe;
	private JPanel panel_24;

	// Thêm sự kiện cho lớp tìm kiếm view
	public void addSuKien(ActionListener listener) {
		jcombobox_gadi.addActionListener(listener);
		jcombobox_gaden.addActionListener(listener);
		btnTimKiemChuyenTau.addActionListener(listener);
		rdbtn_MotChieu.addActionListener(listener);
		rdbtn_KhuHoi.addActionListener(listener);
		btn_LamMoi.addActionListener(listener);
		combobox_KhungGio.addActionListener(listener);
		btn_ChonNhanh.addActionListener(listener);
		btn_QuayLai.addActionListener(listener);
		btn_TiepTheo.addActionListener(listener);
	}

	public void addNextButtonListener(ActionListener listener) {
		btnTrangSau.addActionListener(listener);
	}

	public void addPrevButtonListener(ActionListener listener) {
		btnTrangTruoc.addActionListener(listener);
	}

	public static JComboBox<TinhThanh> timKiemTinhThanh() {
		JComboBox<TinhThanh> comboBox = new JComboBox<>();
		EventList<TinhThanh> tinhThanhList = new BasicEventList<>();

		for (TinhThanh tinh : TinhThanh.values()) {
			tinhThanhList.add(tinh);
		}

		// Kích hoạt tính năng tìm kiếm tự động bằng AutoCompleteSupport
		AutoCompleteSupport.install(comboBox, tinhThanhList);
		comboBox.setPreferredSize(new Dimension(0, 25));
		return comboBox;
	}

	public ChonGhe_View(String name, String imagePath) {
		super(name, imagePath);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1350, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		panel_top = new JPanel();
		panel_top.setBorder(new EmptyBorder(5, 10, 0, 0));
		panel_top.setBackground(Color.WHITE);
		panel_top.setPreferredSize(new Dimension(0, 90)); // Chiều cao cố định
		panel_top.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75)); // Đặt chiều cao tối đa
		contentPane.add(panel_top);
		panel_top.setLayout(new BoxLayout(panel_top, BoxLayout.X_AXIS));

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(10, 5, 5, 5));
		panel_top.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(210, 0)); 
		panel.setMaximumSize(new Dimension(210, Integer.MAX_VALUE));

		panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		panel_11.setBorder(null);
		panel.add(panel_11);
		panel_11.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lblGaDi = new JLabel("Ga đi");
		lblGaDi.setForeground(new Color(70, 130, 169));
		lblGaDi.setFont(new Font("Arial", Font.BOLD, 20));
		panel_11.add(lblGaDi);

		panel_13 = new JPanel();
		panel_13.setBackground(Color.WHITE);
		panel_13.setBorder(null);
		panel.add(panel_13);
		panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.X_AXIS));

		jcombobox_gadi = timKiemTinhThanh();
		jcombobox_gadi.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_13.add(jcombobox_gadi);
		icon = new ImageIcon(getClass().getResource("/Image/city.png"));

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new EmptyBorder(10, 5, 5, 5));
		panel_top.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		panel_1.setPreferredSize(new Dimension(210, 0));
		panel_1.setMaximumSize(new Dimension(210, Integer.MAX_VALUE));

		panel_12 = new JPanel();
		panel_12.setBorder(null);
		panel_12.setBackground(Color.WHITE);
		panel_1.add(panel_12);
		panel_12.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lblGaDen = new JLabel("Ga đến");
		lblGaDen.setForeground(new Color(70, 130, 169));
		lblGaDen.setFont(new Font("Arial", Font.BOLD, 20));
		panel_12.add(lblGaDen);

		panel_14 = new JPanel();
		panel_14.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_14.setBackground(Color.WHITE);
		panel_1.add(panel_14);
		panel_14.setLayout(new BoxLayout(panel_14, BoxLayout.X_AXIS));
		icon = new ImageIcon(getClass().getResource("/Image/city.png"));

		jcombobox_gaden = timKiemTinhThanh();
		jcombobox_gaden.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_14.add(jcombobox_gaden);

		horizontalStrut = Box.createHorizontalStrut(2);
		panel_14.add(horizontalStrut);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new EmptyBorder(30, 12, 5, 5));
		panel_2.setPreferredSize(new Dimension(120, 0));
		panel_2.setMaximumSize(new Dimension(120, Integer.MAX_VALUE));
		panel_top.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		rdbtn_MotChieu = new JRadioButton("Một chiều");
		rdbtn_MotChieu.setBackground(Color.WHITE);
		rdbtn_MotChieu.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtn_MotChieu.setSelected(true);
		panel_2.add(rdbtn_MotChieu);

		verticalStrut = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut);

		rdbtn_KhuHoi = new JRadioButton("Khứ hồi");
		rdbtn_KhuHoi.setBackground(Color.WHITE);
		rdbtn_KhuHoi.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_2.add(rdbtn_KhuHoi);

		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new EmptyBorder(10, 5, 5, 5));
		panel_top.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		panel_16 = new JPanel();
		panel_16.setBorder(new EmptyBorder(0, 0, 5, 0));
		panel_16.setBackground(Color.WHITE);
		panel_3.add(panel_16);
		panel_16.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lblNewLabel_2 = new JLabel("Ngày đi  ");
		lblNewLabel_2.setForeground(new Color(70, 130, 169));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		panel_16.add(lblNewLabel_2);

		panel_15 = new JPanel();
		panel_15.setBackground(Color.WHITE);
		panel_3.add(panel_15);
		panel_15.setLayout(new BoxLayout(panel_15, BoxLayout.X_AXIS));

		dateChooser_NgayDi = new JDateChooser();
		dateChooser_NgayDi.setLocale(new java.util.Locale("vi", "VN")); // Đặt ngôn ngữ là tiếng Việt
		dateChooser_NgayDi.getCalendarButton().setBorder(null);
		dateChooser_NgayDi.getCalendarButton().setBackground(Color.WHITE);
		dateChooser_NgayDi.setBorder(null);
		dateChooser_NgayDi.setPreferredSize(new Dimension(0, 25));
		dateChooser_NgayDi.setFont(new Font("Arial", Font.PLAIN, 16));
		icon = new ImageIcon(getClass().getResource("/Image/icon_lich.png"));
		dateChooser_NgayDi.setIcon(new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		panel_15.add(dateChooser_NgayDi);

		panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new EmptyBorder(10, 5, 5, 5));
		panel_top.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		panel_17 = new JPanel();
		panel_17.setBorder(new EmptyBorder(0, 0, 5, 0));
		panel_17.setBackground(Color.WHITE);
		panel_4.add(panel_17);
		panel_17.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lblNewLabel_3 = new JLabel("Ngày về");
		lblNewLabel_3.setForeground(new Color(70, 130, 169));
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
		panel_17.add(lblNewLabel_3);

		panel_18 = new JPanel();
		panel_18.setBackground(Color.WHITE);
		panel_4.add(panel_18);
		panel_18.setLayout(new BoxLayout(panel_18, BoxLayout.X_AXIS));

		dateChooser_NgayVe = new JDateChooser();

		dateChooser_NgayVe_1 = new JDateChooser();
		dateChooser_NgayVe_1.setEnabled(false);
		dateChooser_NgayVe_1.setBackground(Color.WHITE);
		dateChooser_NgayVe_1.setLocale(new java.util.Locale("vi", "VN")); // Đặt ngôn ngữ là tiếng Việt
		dateChooser_NgayVe_1.getCalendarButton().setBorder(null);
		dateChooser_NgayVe_1.getCalendarButton().setBackground(Color.WHITE);
		dateChooser_NgayVe_1.setBorder(null);
		dateChooser_NgayVe_1.setPreferredSize(new Dimension(0, 25));
		dateChooser_NgayVe_1.setFont(new Font("Arial", Font.PLAIN, 16));
		icon = new ImageIcon(getClass().getResource("/Image/icon_lich.png"));
		dateChooser_NgayVe_1.setIcon(new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		panel_18.add(dateChooser_NgayVe_1);

		panel_5 = new JPanel();
		panel_top.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		panel_6 = new JPanel();
		panel_6.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_6.setBackground(Color.WHITE);
		panel_6.setPreferredSize(new Dimension(210, 0));
		panel_6.setMaximumSize(new Dimension(210, Integer.MAX_VALUE));
		panel_top.add(panel_6);

		btnTimKiemChuyenTau = new PrimaryButton("Tìm kiếm", "/Image/search.png");
		btnTimKiemChuyenTau.setInsets(new Insets(4, 6, 4, 6));
		btnTimKiemChuyenTau.setBorderRadius(10);
		btnTimKiemChuyenTau.setIconTextGap(5);
		btnTimKiemChuyenTau.setIconSize(22, 22);
		btnTimKiemChuyenTau.setPreferredSize(new Dimension(150, 35));
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_6.add(btnTimKiemChuyenTau);

		btn_LamMoi = new PrimaryButton("Làm mới", "/Image/restore.png");
		btn_LamMoi.setIconTextGap(5);
		btn_LamMoi.setBorderRadius(10);
		btn_LamMoi.setInsets(new Insets(4, 9, 4, 9));
		btn_LamMoi.setIconSize(22, 22);
		btn_LamMoi.setPreferredSize(new Dimension(150, 35));
		panel_6.add(btn_LamMoi);

		panel_center = new JPanel();
		panel_center.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel_center);
		panel_center.setLayout(new BoxLayout(panel_center, BoxLayout.Y_AXIS));

		panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_7.setPreferredSize(new Dimension(0, 290));
		panel_7.setMaximumSize(new Dimension(Integer.MAX_VALUE, 290));
		panel_center.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));

		panel_19 = new JPanel();
		panel_19.setBackground(Color.WHITE);
		panel_19.setBorder(new EmptyBorder(10, 5, 0, 0));
		panel_19.setPreferredSize(new Dimension(0, 60));
		panel_19.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		panel_7.add(panel_19);
		panel_19.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lblTTChuyenTauTimKiem = new JLabel("Danh sách chuyến tàu đề xuất");
		lblTTChuyenTauTimKiem.setForeground(Color.WHITE);
		lblTTChuyenTauTimKiem.setBorder(new EmptyBorder(5, 10, 5, 20));
		lblTTChuyenTauTimKiem.setOpaque(true);
		lblTTChuyenTauTimKiem.setBackground(ColorConstants.PRIMARY_COLOR);
		lblTTChuyenTauTimKiem.setFont(new Font("Arial", Font.BOLD, 20));
		panel_19.add(lblTTChuyenTauTimKiem);

		panel_dsChuyenTau = new JPanel();
		panel_dsChuyenTau.setBorder(new EmptyBorder(5, 20, 10, 20));
		panel_dsChuyenTau.setBackground(Color.WHITE);
		panel_7.add(panel_dsChuyenTau);
		panel_dsChuyenTau.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));

		panel_45 = new JPanel();
		panel_45.setBorder(new EmptyBorder(0, 0, 0, 20));
		panel_45.setBackground(Color.WHITE);
		panel_7.add(panel_45);
		panel_45.setLayout(new BoxLayout(panel_45, BoxLayout.X_AXIS));

		panel_47 = new JPanel();
		panel_47.setBackground(Color.WHITE);
		panel_45.add(panel_47);
		panel_47.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));

		lblSoChuyenTau = new JLabel("Tổng số chuyến tàu: 0");
		lblSoChuyenTau.setFont(new Font("Arial", Font.BOLD, 16));
		panel_47.add(lblSoChuyenTau);

		panel_46 = new JPanel();
		panel_46.setBorder(new EmptyBorder(10, 0, 0, 0));
		panel_46.setBackground(Color.WHITE);
		panel_45.add(panel_46);
		panel_46.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		icon = new ImageIcon(getClass().getResource("/Image/chevron-left.png"));

		btnTrangTruoc = new JButton("Trang trước");
		btnTrangTruoc.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_46.add(btnTrangTruoc);

		lblSoTrang = new JLabel("trang: 01");
		lblSoTrang.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_46.add(lblSoTrang);

		btnTrangSau = new JButton("Trang sau");
		btnTrangSau.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_46.add(btnTrangSau);
		icon = new ImageIcon(getClass().getResource("/Image/chevron-right.png"));

		panel_44 = new JPanel();
		panel_44.setBorder(new EmptyBorder(5, 0, 5, 0));
		panel_44.setBackground(Color.WHITE);
		panel_7.add(panel_44);
		panel_44.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));

		String[] items = { "Còn trống", "Hết chỗ" };
		icon = new ImageIcon(getClass().getResource("/Image/restore.png"));

		lblNewLabel_28 = new JLabel("Khung giờ:");
		lblNewLabel_28.setFont(new Font("Arial", Font.BOLD, 16));
		panel_44.add(lblNewLabel_28);

		String[] items1 = { "Tất cả", "00:00 - 6:00", "6:00 - 12:00", "12:00 - 18:00", "18:00 - 00:00" };
		combobox_KhungGio = new JComboBox<>(items1);
		combobox_KhungGio.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_44.add(combobox_KhungGio);

		panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_8.setPreferredSize(new Dimension(0, 130));
		panel_8.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
		panel_center.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));

		panel_21 = new JPanel();
		panel_21.setBorder(new EmptyBorder(0, 5, 0, 0));
		panel_21.setBackground(Color.WHITE);
		panel_21.setPreferredSize(new Dimension(0, 35));
		panel_21.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		panel_8.add(panel_21);
		panel_21.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		lblNewLabel_8 = new JLabel("Danh sách toa tàu");
		lblNewLabel_8.setOpaque(true);
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8.setBorder(new EmptyBorder(5, 10, 5, 10));
		lblNewLabel_8.setBackground(new Color(70, 130, 169));
		panel_21.add(lblNewLabel_8);

		panel_DsToaTau = new JPanel();
		panel_DsToaTau.setBorder(null);
		panel_DsToaTau.setBackground(Color.WHITE);
		panel_8.add(panel_DsToaTau);
		panel_DsToaTau.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));

		panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_center.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		panel_31 = new JPanel();
		panel_31.setBorder(new EmptyBorder(5, 0, 0, 0));
		panel_31.setBackground(Color.WHITE);
		panel_31.setPreferredSize(new Dimension(0, 30));
		panel_31.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		panel_9.add(panel_31);
		panel_31.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		lbl_TenToaTau = new JLabel("");
		lbl_TenToaTau.setForeground(ColorConstants.PRIMARY_COLOR);
		lbl_TenToaTau.setFont(new Font("Arial", Font.BOLD, 18));
		panel_31.add(lbl_TenToaTau);

		panel_chuaDsGheTau = new JPanel();
		panel_chuaDsGheTau.setBorder(new EmptyBorder(0, 15, 0, 15));
		panel_chuaDsGheTau.setBackground(Color.WHITE);
		panel_chuaDsGheTau.setPreferredSize(new Dimension(0, 140)); // Chiều cao cố định
		panel_chuaDsGheTau.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140)); // Đặt chiều cao tối đa
		panel_9.add(panel_chuaDsGheTau);
		panel_chuaDsGheTau.setLayout(new BoxLayout(panel_chuaDsGheTau, BoxLayout.X_AXIS));

		panel_DsGheTau = new JPanel();
		panel_DsGheTau.setBackground(Color.WHITE);
		panel_chuaDsGheTau.add(panel_DsGheTau);

		panel_32 = new JPanel();
		panel_32.setBackground(Color.WHITE);
		panel_9.add(panel_32);
		panel_32.setLayout(new BoxLayout(panel_32, BoxLayout.X_AXIS));

		panel_35 = new JPanel();
		panel_35.setBackground(Color.WHITE);
		panel_32.add(panel_35);

		panel_34 = new JPanel();
		panel_34.setBackground(Color.WHITE);
		panel_32.add(panel_34);
		panel_34.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));

		panel_36 = new JPanel();
		panel_36.setBackground(Color.WHITE);
		panel_34.add(panel_36);

		lblNewLabel_18 = new JLabel("Tổng số ghế còn lại: ");
		lblNewLabel_18.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_18.setForeground(Color.BLACK);
		lblNewLabel_18.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_18.setBackground(Color.WHITE);
		panel_36.add(lblNewLabel_18);

		lbl_SoGheTau = new JLabel("  ");
		lbl_SoGheTau.setFont(new Font("Arial", Font.BOLD, 16));
		panel_36.add(lbl_SoGheTau);

		panel_37 = new JPanel();
		panel_37.setBackground(Color.WHITE);
		panel_34.add(panel_37);

		panel_38 = new JPanel();
		panel_38.setBackground(Color.LIGHT_GRAY);
		panel_37.add(panel_38);

		lblNewLabel_20 = new JLabel("    ");
		panel_38.add(lblNewLabel_20);

		lblNewLabel_21 = new JLabel("Đã mua");
		lblNewLabel_21.setFont(new Font("Arial", Font.BOLD, 16));
		panel_37.add(lblNewLabel_21);

		panel_39 = new JPanel();
		panel_39.setBackground(Color.WHITE);
		panel_34.add(panel_39);

		panel_40 = new JPanel();
		panel_40.setBackground(new Color(70, 130, 169));
		panel_39.add(panel_40);

		lblNewLabel_22 = new JLabel("    ");
		panel_40.add(lblNewLabel_22);

		lblNewLabel_23 = new JLabel("Còn trống");
		lblNewLabel_23.setFont(new Font("Arial", Font.BOLD, 16));
		panel_39.add(lblNewLabel_23);

		panel_41 = new JPanel();
		panel_41.setBackground(Color.WHITE);
		panel_34.add(panel_41);

		panel_42 = new JPanel();
		panel_42.setBackground(Color.RED);
		panel_41.add(panel_42);

		lblNewLabel_24 = new JLabel("    ");
		panel_42.add(lblNewLabel_24);

		lblNewLabel_25 = new JLabel("Bảo trì");
		lblNewLabel_25.setFont(new Font("Arial", Font.BOLD, 16));
		panel_41.add(lblNewLabel_25);

		panel_bottom = new JPanel();
		panel_bottom.setBorder(new EmptyBorder(5, 0, 0, 0));
		panel_bottom.setBackground(Color.WHITE);
		panel_bottom.setPreferredSize(new Dimension(0, 70));
		panel_bottom.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
		contentPane.add(panel_bottom);
		panel_bottom.setLayout(new BoxLayout(panel_bottom, BoxLayout.X_AXIS));
		
		panel_20 = new JPanel();
		panel_20.setBorder(new EmptyBorder(5, 20, 0, 0));
		panel_20.setBackground(Color.WHITE);
		panel_bottom.add(panel_20);
		panel_20.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
		
		btn_QuayLai = new PrimaryButton("Quay lại", "/Image/arrow-back-up.png");
		panel_20.add(btn_QuayLai);
		btn_QuayLai.setFont(new Font("Arial", Font.BOLD, 18));
		btn_QuayLai.setPreferredSize(new Dimension(140, 35));
		btn_QuayLai.setMaximumSize(new Dimension(2147483647, 50));
		btn_QuayLai.setInsets(new Insets(4, 6, 4, 6));
		btn_QuayLai.setIconTextGap(5);
		btn_QuayLai.setBorderRadius(10);
		btn_QuayLai.setAlignmentX(0.0f);
		
		btn_TiepTheo = new PrimaryButton("Chọn nhanh", "/Image/arrow-right.png");
		btn_TiepTheo.setVisible(false);

		btn_TiepTheo.setText("Tiếp theo");
		btn_TiepTheo.setPreferredSize(new Dimension(140, 35));
		btn_TiepTheo.setInsets(new Insets(4, 10, 4, 10));
		btn_TiepTheo.setIconTextGap(5);
		btn_TiepTheo.setFont(new Font("Arial", Font.BOLD, 18));
		btn_TiepTheo.setBorderRadius(10);
		panel_20.add(btn_TiepTheo);
		btn_QuayLai.setVisible(false);
		
		panel_22 = new JPanel();
		panel_22.setBorder(new EmptyBorder(15, 0, 0, 0));
		panel_22.setBackground(Color.WHITE);
		panel_bottom.add(panel_22);
		panel_22.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		lbl_VeChieuDi = new JLabel("Số vé chiều đi: ");
		panel_22.add(lbl_VeChieuDi);
		lbl_VeChieuDi.setFont(new Font("Arial", Font.BOLD, 18));
		
		lbl_SoVeChieuDi = new JLabel("0");
		lbl_SoVeChieuDi.setFont(new Font("Arial", Font.BOLD, 18));
		panel_22.add(lbl_SoVeChieuDi);
		lbl_SoVeChieuDi.setVisible(false);
		lbl_VeChieuDi.setVisible(false);
		
		panel_23 = new JPanel();
		panel_23.setBorder(new EmptyBorder(15, 0, 0, 0));
		panel_23.setBackground(Color.WHITE);
		panel_bottom.add(panel_23);
		panel_23.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		lbl_VeChieuVe = new JLabel("Số vé chiều về:");
		panel_23.add(lbl_VeChieuVe);
		lbl_VeChieuVe.setVisible(false);
		lbl_VeChieuVe.setFont(new Font("Arial", Font.BOLD, 18));
		
		lbl_SoVeChieuVe = new JLabel("0");
		lbl_SoVeChieuVe.setFont(new Font("Arial", Font.BOLD, 18));
		lbl_SoVeChieuVe.setVisible(false);
		panel_23.add(lbl_SoVeChieuVe);
		
		panel_24 = new JPanel();
		panel_24.setBorder(new EmptyBorder(15, 0, 0, 0));
		panel_24.setBackground(Color.WHITE);
		panel_bottom.add(panel_24);
		panel_24.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

		lbl_TongSoVeTamThoi = new JLabel("Số vé tạm thời: 0");
		panel_24.add(lbl_TongSoVeTamThoi);
		lbl_TongSoVeTamThoi.setFont(new Font("Arial", Font.BOLD, 18));

		panel_43 = new JPanel();
		panel_43.setBorder(new EmptyBorder(5, 0, 0, 0));
		panel_43.setBackground(Color.WHITE);
		panel_bottom.add(panel_43);
		panel_43.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));

		btn_ChonNhanh = new PrimaryButton("Chọn nhanh", "/Image/armchair.png");
		btn_ChonNhanh.setIconTextGap(5);
		btn_ChonNhanh.setBorderRadius(10);
		btn_ChonNhanh.setIconSize(24, 24);
		btn_ChonNhanh.setFont(new Font("Arial", Font.BOLD, 18));
		btn_ChonNhanh.setInsets(new Insets(4, 10, 4, 10));
		btn_ChonNhanh.setPreferredSize(new Dimension(170, 35));
		panel_43.add(btn_ChonNhanh);

		buttonGroup = new ButtonGroup();
		buttonGroup_1 = new ButtonGroup();

		buttonGroup.add(rdbtn_KhuHoi);
		buttonGroup.add(rdbtn_MotChieu);
	}

	public JPanel getTimKiem_View() {
		return contentPane;
	}

	public JLabel getLblSoTrang() {
		return lblSoTrang;
	}

	public void setLblSoTrang(JLabel lblSoTrang) {
		this.lblSoTrang = lblSoTrang;
	}

	public JButton getBtnTrangSau() {
		return btnTrangSau;
	}

	public JButton getBtnTrangTruoc() {
		return btnTrangTruoc;
	}

	public JPanel getPanel_dsChuyenTau() {
		return panel_dsChuyenTau;
	}

	public void setPanel_dsChuyenTau(JPanel panel_dsChuyenTau) {
		this.panel_dsChuyenTau = panel_dsChuyenTau;
	}

	public JLabel getLblSoChuyenTau() {
		return lblSoChuyenTau;
	}

	public void setLblSoChuyenTau(JLabel lblSoChuyenTau) {
		this.lblSoChuyenTau = lblSoChuyenTau;
	}

	public JLabel getLbl_TenToaTau() {
		return lbl_TenToaTau;
	}

	public void setLbl_TenToaTau(JLabel lbl_TenToaTau) {
		this.lbl_TenToaTau = lbl_TenToaTau;
	}

	public JLabel getLbl_SoGheTau() {
		return lbl_SoGheTau;
	}

	public void setLbl_SoGheTau(JLabel lbl_SoGheTau) {
		this.lbl_SoGheTau = lbl_SoGheTau;
	}

	public JComboBox<TinhThanh> getJcombobox_gadi() {
		return jcombobox_gadi;
	}

	public void setJcombobox_gadi(JComboBox<TinhThanh> jcombobox_gadi) {
		this.jcombobox_gadi = jcombobox_gadi;
	}

	public JComboBox<TinhThanh> getJcombobox_gaden() {
		return jcombobox_gaden;
	}

	public void setJcombobox_gaden(JComboBox<TinhThanh> jcombobox_gaden) {
		this.jcombobox_gaden = jcombobox_gaden;
	}

	public PrimaryButton getBtnTimKiemChuyenTau() {
		return btnTimKiemChuyenTau;
	}

	public void setBtnTimKiemChuyenTau(PrimaryButton btnTimKiemChuyenTau) {
		this.btnTimKiemChuyenTau = btnTimKiemChuyenTau;
	}

	public JDateChooser getDateChooser_NgayDi() {
		return dateChooser_NgayDi;
	}

	public JDateChooser getDateChooser_NgayVe() {
		return dateChooser_NgayVe_1;
	}

	public JRadioButton getRdbtn_KhuHoi() {
		return rdbtn_KhuHoi;
	}

	public void setRdbtn_KhuHoi(JRadioButton rdbtn_KhuHoi) {
		this.rdbtn_KhuHoi = rdbtn_KhuHoi;
	}

	public JRadioButton getRdbtn_MotChieu() {
		return rdbtn_MotChieu;
	}
	public PrimaryButton getBtn_LamMoi() {
		return btn_LamMoi;
	}
	
	public JComboBox getCombobox_KhungGio() {
		return combobox_KhungGio;
	}

	public JLabel getLblTTChuyenTauTimKiem() {
		return lblTTChuyenTauTimKiem;
	}
	
	public PrimaryButton getBtn_ChonNhanh() {
		return btn_ChonNhanh;
	}

	public JLabel getLbl_TongSoVeTamThoi() {
		return lbl_TongSoVeTamThoi;
	}

	public PrimaryButton getBtn_QuayLai() {
		return btn_QuayLai;
	}

	public PrimaryButton getBtn_TiepTheo() {
		return btn_TiepTheo;
	}

	public JLabel getLbl_VeChieuVe() {
		return lbl_VeChieuVe;
	}

	public JLabel getLbl_VeChieuDi() {
		return lbl_VeChieuDi;
	}

	public JLabel getLbl_SoVeChieuDi() {
		return lbl_SoVeChieuDi;
	}

	public JLabel getLbl_SoVeChieuVe() {
		return lbl_SoVeChieuVe;
	}
	
}
