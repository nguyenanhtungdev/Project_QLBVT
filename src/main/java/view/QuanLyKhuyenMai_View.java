package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import model.KhuyenMai;
import model.KhuyenMai_DAO;
import other.CustomTitleLable;
import other.DangerPrimaryButton;
import other.PrimaryButton;

import java.awt.FlowLayout;
import java.awt.Component;

public class QuanLyKhuyenMai_View extends View {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDateChooser dateKTKM;
	private PrimaryButton btnThem;
	private DangerPrimaryButton btnHuy;
	private DefaultTableModel modelTableKM;
	private JTable tableKM;
	private JPanel panelSearch1;
	private JComboBox<String> comboBoxMaKM;
	private JComboBox<String> comboBoxTrangThai;
	private JButton btnReset;
	private PrimaryButton btnSearch;
	private static KhuyenMai_DAO km_dao;
	private ImageIcon iconlich;
	private JTextField txtTenKM;
	private JTextField txtSLKM;
	private JTextArea txtNDKM;
	private JTextField txtGiamGia;
	private PrimaryButton btnCapNhap;
	private PrimaryButton btnGuiTB;
	private JDateChooser dateBDKM;

	public void addButtonReloadListener(ActionListener listener) {
		btnReset.addActionListener(listener);
	}

	public void addButtonTimListener(ActionListener listener) {
		btnSearch.addActionListener(listener);
	}

	public void addButtonThemListener(ActionListener listener) {
		btnThem.addActionListener(listener);
	}

	public void addButtonCapNhapListener(ActionListener listener) {
		btnCapNhap.addActionListener(listener);
	}

	public void addButtonHuyListener(ActionListener listener) {
		btnHuy.addActionListener(listener);
	}

	public void addButtonThongBaoListener(ActionListener listener) {
		btnGuiTB.addActionListener(listener);
	}

	// Phương thức tạo JComboBox với tính năng gợi ý
	public static JComboBox<String> timKiemMaKhuyenMai() {
		JComboBox<String> comboBox = new JComboBox<>();
		EventList<String> maKMList = new BasicEventList<>();
		List<KhuyenMai> danhSachKM;
		danhSachKM = km_dao.getAll();
		for (KhuyenMai km : danhSachKM) {
			String maKM = km.getMaKhuyenMai();
			maKMList.add(maKM);
			comboBox.addItem(maKM);
		}
		AutoCompleteSupport.install(comboBox, maKMList);

		return comboBox;
	}

	@SuppressWarnings("unused")
	public QuanLyKhuyenMai_View(String name, String imagePath) {
		super(name, imagePath);
		FlatLightLaf.setup();
		km_dao = new KhuyenMai_DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1250, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(Color.WHITE);

		JPanel panelTitle = new JPanel();
		panelTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
		panelTitle.setBackground(Color.WHITE);
		headerPanel.add(panelTitle, BorderLayout.NORTH);
		contentPane.add(headerPanel, BorderLayout.NORTH);

		JPanel pThemKM = new JPanel();
		pThemKM.setBackground(Color.WHITE);
		headerPanel.add(pThemKM, BorderLayout.CENTER);
		pThemKM.setBorder(
				new CompoundBorder(new LineBorder(new Color(192, 192, 192), 1, true), new EmptyBorder(10, 10, 10, 10)));
		pThemKM.setLayout(new BorderLayout(0, 0));

		JPanel panelNhap = new JPanel();
		panelNhap.setBackground(Color.WHITE);
		pThemKM.add(panelNhap, BorderLayout.CENTER);
		panelNhap.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JPanel panelNhap1 = new JPanel();
		panelNhap1.setBackground(Color.WHITE);
		panelNhap1.setBorder(new EmptyBorder(10, 0, 0, 10));
		panelNhap.add(panelNhap1);
		panelNhap1.setLayout(new BorderLayout(0, 0));

		JPanel panelNhap2 = new JPanel();
		panelNhap2.setBackground(Color.WHITE);
		panelNhap2.setBorder(new EmptyBorder(5, 5, 0, 5));
		panelNhap1.add(panelNhap2, BorderLayout.NORTH);
		panelNhap2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		JPanel panelTenKM = new JPanel();
		panelTenKM.setBackground(Color.WHITE);
		panelTenKM.setBorder(new EmptyBorder(0, 0, 0, 68));
		panelNhap2.add(panelTenKM);

		JLabel lblTenKM = new JLabel("Tên khuyến mãi");
		lblTenKM.setForeground(new Color(70, 130, 180));
		lblTenKM.setFont(new Font("Arial", Font.BOLD, 16));
		panelTenKM.add(lblTenKM);

		JPanel panelTxtTenkm = new JPanel();
		panelTxtTenkm.setBackground(Color.WHITE);
		panelTxtTenkm.setBorder(new EmptyBorder(0, 0, 0, 20));
		panelNhap2.add(panelTxtTenkm);

		txtTenKM = new JTextField(14);
		panelTxtTenkm.add(txtTenKM);
		txtTenKM.setFont(new Font("Arial", Font.PLAIN, 16));

		JPanel panelSL = new JPanel();
		panelSL.setBorder(new EmptyBorder(0, 3, 0, 0));
		panelSL.setBackground(Color.WHITE);
		panelNhap2.add(panelSL);

		JLabel lblSLKM = new JLabel("Số lượng khuyến mãi");
		lblSLKM.setHorizontalAlignment(SwingConstants.CENTER);
		lblSLKM.setForeground(new Color(70, 130, 180));
		lblSLKM.setFont(new Font("Arial", Font.BOLD, 16));
		panelSL.add(lblSLKM);

		JPanel panelTxtSL = new JPanel();
		panelTxtSL.setBackground(Color.WHITE);
		panelTxtSL.setBorder(new EmptyBorder(0, 5, 0, 0));
		panelNhap2.add(panelTxtSL);
		panelTxtSL.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		txtSLKM = new JTextField(14);
		txtSLKM.setFont(new Font("Arial", Font.PLAIN, 16));
		panelTxtSL.add(txtSLKM);

		JPanel panelNhap3 = new JPanel();
		panelNhap3.setBackground(Color.WHITE);
		panelNhap3.setBorder(new EmptyBorder(0, 5, 0, 5));
		panelNhap1.add(panelNhap3);
		panelNhap3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		JPanel panelNDkm = new JPanel();
		panelNDkm.setBackground(Color.WHITE);
		panelNDkm.setBorder(new EmptyBorder(0, 0, 80, 0));
		FlowLayout fl_panelNDkm = (FlowLayout) panelNDkm.getLayout();
		panelNhap3.add(panelNDkm);

		JLabel lblNDKM = new JLabel(" Nội dung khuyến mãi");
		lblNDKM.setForeground(new Color(70, 130, 180));
		lblNDKM.setFont(new Font("Arial", Font.BOLD, 16));
		panelNDkm.add(lblNDKM);

		JPanel panelTxtNDkm = new JPanel();
		panelTxtNDkm.setBackground(Color.WHITE);
		panelTxtNDkm.setBorder(new EmptyBorder(0, 23, 0, 20));
		panelNhap3.add(panelTxtNDkm);

		txtNDKM = new JTextArea();
		txtNDKM.setWrapStyleWord(true);
		txtNDKM.setPreferredSize(new Dimension(195, 100));
		txtNDKM.setMargin(new Insets(5, 5, 5, 5));
		txtNDKM.setLineWrap(true);
		txtNDKM.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNDKM.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		panelTxtNDkm.add(txtNDKM);

		JPanel panelDate = new JPanel();
		panelDate.setBackground(Color.WHITE);
		panelDate.setBorder(new EmptyBorder(0, 0, 25, 0));
		panelNhap3.add(panelDate);
		panelDate.setLayout(new BoxLayout(panelDate, BoxLayout.Y_AXIS));

		Component verticalStrut_1 = Box.createVerticalStrut(7);
		panelDate.add(verticalStrut_1);

		JPanel panelDateBD = new JPanel();
		panelDateBD.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelDateBD.setBackground(Color.WHITE);
		panelDate.add(panelDateBD);
		panelDateBD.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JPanel panelDateBD_1 = new JPanel();
		panelDateBD_1.setBorder(new EmptyBorder(0, 0, 0, 10));
		panelDateBD_1.setBackground(Color.WHITE);
		panelDateBD.add(panelDateBD_1);

		JLabel lblDateBD = new JLabel("Ngày bắt đầu ");
		lblDateBD.setForeground(new Color(70, 130, 180));
		lblDateBD.setFont(new Font("Arial", Font.BOLD, 16));
		panelDateBD_1.add(lblDateBD);

		dateBDKM = new JDateChooser();
		dateBDKM.getCalendarButton().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		dateBDKM.getCalendarButton().setBackground(new Color(235, 235, 235));
		dateBDKM.setPreferredSize(new Dimension(252, 25));
		dateBDKM.setFont(new Font("Arial", Font.PLAIN, 16));
		dateBDKM.setDateFormatString("dd-MM-yyyy");
		dateBDKM.setBorder(new EmptyBorder(0, 56, 0, 0));
		dateBDKM.setBackground(Color.WHITE);
		iconlich = new ImageIcon(getClass().getResource("/Image/icon_lich.png"));
		dateBDKM.setIcon(new ImageIcon(iconlich.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH)));
		panelDateBD.add(dateBDKM);

		JPanel panelDate1 = new JPanel();
		panelDate1.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelDate1.setBackground(Color.WHITE);
		panelDate.add(panelDate1);
		panelDate1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JPanel panelDate3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelDate3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelDate3.setBackground(Color.WHITE);
		panelDate3.setBorder(new EmptyBorder(0, 0, 0, 10));
		panelDate1.add(panelDate3);

		JLabel lblDateKT = new JLabel("Ngày kết thúc");
		lblDateKT.setForeground(new Color(70, 130, 180));
		lblDateKT.setFont(new Font("Arial", Font.BOLD, 16));
		panelDate3.add(lblDateKT);
		dateKTKM = new JDateChooser();
		dateKTKM.setBackground(Color.WHITE);
		dateKTKM.getCalendarButton().setBackground(new Color(235, 235, 235));
		dateKTKM.getCalendarButton().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		dateKTKM.setBorder(new EmptyBorder(0, 56, 0, 0));
		dateKTKM.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 16));
		dateKTKM.setFont(new Font("Arial", Font.PLAIN, 16));
		dateKTKM.setDateFormatString("dd-MM-yyyy");
		dateKTKM.setPreferredSize(new Dimension(252, 25));
		iconlich = new ImageIcon(getClass().getResource("/Image/icon_lich.png"));
		dateKTKM.setIcon(new ImageIcon(iconlich.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH)));
		panelDate1.add(dateKTKM);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.WHITE);
		panelDate.add(panel);

		JPanel panelGiamGia = new JPanel();
		FlowLayout fl_panelGiamGia = (FlowLayout) panelGiamGia.getLayout();
		fl_panelGiamGia.setAlignment(FlowLayout.LEFT);
		panelGiamGia.setBorder(new EmptyBorder(0, 0, 0, 45));
		panelGiamGia.setBackground(Color.WHITE);
		panel.add(panelGiamGia);

		JLabel lblGiamGia = new JLabel("Giảm giá");
		lblGiamGia.setForeground(new Color(70, 130, 180));
		lblGiamGia.setFont(new Font("Arial", Font.BOLD, 16));
		panelGiamGia.add(lblGiamGia);

		JPanel panelGiamGiat = new JPanel();
		panelGiamGiat.setBorder(new EmptyBorder(0, 55, 0, 0));
		panelGiamGiat.setBackground(Color.WHITE);
		panel.add(panelGiamGiat);
		panelGiamGiat.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		txtGiamGia = new JTextField(14);
		txtGiamGia.setFont(new Font("Arial", Font.PLAIN, 16));
		panelGiamGiat.add(txtGiamGia);

		JPanel panelNut = new JPanel();
		panelNut.setBackground(Color.WHITE);
		pThemKM.add(panelNut, BorderLayout.EAST);
		panelNut.setLayout(new BoxLayout(panelNut, BoxLayout.Y_AXIS));

		panelNut.setBorder(
				new CompoundBorder(new LineBorder(new Color(192, 192, 192), 1, true), new EmptyBorder(0, 20, 0, 20)));

		btnThem = new PrimaryButton("Thêm   ", "/Image/plus.png");
		btnThem.setText("Thêm     ");
		btnThem.setBorderRadius(15);
		btnThem.setFont(new Font("Arial", Font.BOLD, 18));
		btnThem.setIconSize(32, 32);
		btnThem.setPreferredSize(new Dimension(170, 35));

		btnHuy = new DangerPrimaryButton("Huỷ bỏ     ", "/Image/cancel.png");
		btnHuy.setBorderRadius(15); // Độ bo góc
		btnHuy.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuy.setIconSize(32, 32);
		btnHuy.setPreferredSize(new Dimension(170, 35));

		panelNut.add(Box.createVerticalGlue());
		JPanel panelBtnThem = new JPanel();
		panelBtnThem.setBackground(Color.WHITE);
		panelNut.add(panelBtnThem);
		panelBtnThem.setLayout(new BorderLayout(0, 0));
		panelBtnThem.add(btnThem, BorderLayout.SOUTH);
		panelNut.add(Box.createVerticalStrut(20));

		JPanel panelBtnCapNhap = new JPanel();
		panelBtnCapNhap.setBorder(new EmptyBorder(15, 0, 0, 0));
		panelBtnCapNhap.setBackground(Color.WHITE);
		panelNut.add(panelBtnCapNhap);
		panelBtnCapNhap.setLayout(new BorderLayout(0, 0));

		btnCapNhap = new PrimaryButton("Cập nhập", "/Image/update.png");
		btnCapNhap.setText("Cập nhật");
		btnCapNhap.setPreferredSize(new Dimension(170, 35));
		btnCapNhap.setFont(new Font("Arial", Font.BOLD, 18));
		btnCapNhap.setBorderRadius(15);
		btnCapNhap.setIconSize(32, 32);
		panelBtnCapNhap.add(btnCapNhap, BorderLayout.NORTH);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panelNut.add(verticalStrut_2);

		JPanel panelBtnHuy = new JPanel();
		panelBtnHuy.setBackground(Color.WHITE);
		panelNut.add(panelBtnHuy);
		panelBtnHuy.setLayout(new BorderLayout(0, 0));
		panelBtnHuy.add(btnHuy, BorderLayout.NORTH);
		panelNut.add(Box.createVerticalGlue());

		String[] header = { "STT", "Mã khuyến mãi", "Tên khuyến mãi", "Nội dung", "Giảm giá", "Số lượng",
				"Ngày bắt đầu", "Ngày kết thúc", "Trạng thái" };
		Font headerFont = new Font("Arial", Font.BOLD, 18);
		modelTableKM = new DefaultTableModel(header, 0) {
			private static final long serialVersionUID = 277400464860378899L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableKM = new JTable(modelTableKM);
		tableKM.setShowGrid(true);
		tableKM.setGridColor(new Color(225, 225, 225));
		tableKM.getTableHeader().setFont(headerFont);
		tableKM.setFont(new Font("Arial", Font.PLAIN, 16));
		tableKM.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		Font font1 = new Font("Arial", Font.PLAIN, 14);
		tableKM.setFont(font1);
		tableKM.getColumnModel().getColumn(0).setPreferredWidth(5);
		tableKM.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableKM.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableKM.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableKM.getColumnModel().getColumn(4).setPreferredWidth(50);
		tableKM.getColumnModel().getColumn(5).setPreferredWidth(50);
		tableKM.getTableHeader().setReorderingAllowed(false);

		JPanel panelSearchAndTable = new JPanel();
		panelSearchAndTable.setBorder(new EmptyBorder(15, 0, 0, 0));
		panelSearchAndTable.setBackground(Color.WHITE);
		contentPane.add(panelSearchAndTable, BorderLayout.CENTER);
		panelSearchAndTable.setLayout(new BorderLayout(0, 0));

		JPanel panelSearch = new JPanel();
		panelSearchAndTable.add(panelSearch, BorderLayout.NORTH);
		tableKM.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableKM.setRowHeight(30);
		panelSearchAndTable.add(new JScrollPane(tableKM));
		panelSearch.setLayout(new BoxLayout(panelSearch, BoxLayout.X_AXIS));

		panelSearch1 = new JPanel();
		panelSearch1.setBackground(Color.WHITE);
		panelSearch1.setBorder(new EmptyBorder(5, 0, 0, 0));
		panelSearch.add(panelSearch1);
		panelSearch1.setLayout(new BorderLayout(0, 0));

		JPanel paelSearch2 = new JPanel();
		paelSearch2.setBackground(Color.WHITE);
		paelSearch2.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelSearch1.add(paelSearch2, BorderLayout.EAST);
		paelSearch2.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 2));

		JPanel panelMakm = new JPanel();
		panelMakm.setBackground(Color.WHITE);
		panelMakm.setBorder(new EmptyBorder(0, 0, 0, 10));
		paelSearch2.add(panelMakm);
		panelMakm.setLayout(new BoxLayout(panelMakm, BoxLayout.X_AXIS));

		JLabel lblMakm = new JLabel("Mã khuyến mãi ");
		lblMakm.setFont(new Font("Arial", Font.BOLD, 16));
		panelMakm.add(lblMakm);

		comboBoxMaKM = timKiemMaKhuyenMai();
		comboBoxMaKM.setEditable(true);
		comboBoxMaKM.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBoxMaKM.setPreferredSize(new Dimension(150, 25));
		panelMakm.add(comboBoxMaKM);

		JPanel paneTT = new JPanel();
		paneTT.setBackground(Color.WHITE);
		paneTT.setBorder(new EmptyBorder(0, 0, 0, 5));
		paelSearch2.add(paneTT);
		paneTT.setLayout(new BoxLayout(paneTT, BoxLayout.X_AXIS));

		JLabel lblTT = new JLabel("Tình trạng ");
		lblTT.setFont(new Font("Arial", Font.BOLD, 16));
		paneTT.add(lblTT);
		String[] items = { "", "Hết hạn sau 7 ngày", "Còn số lượng", "Hết số lượng", "Hết hạn sử dụng" };
		comboBoxTrangThai = new JComboBox<>(items);
		comboBoxTrangThai.setFont(new Font("Arial", Font.PLAIN, 16));
		paneTT.add(comboBoxTrangThai);

		btnReset = new JButton();
		btnReset.setIcon(new ImageIcon(getClass().getResource("/Image/reload.png")));
		btnReset.setBorderPainted(false);
		btnReset.setFocusPainted(false);
		btnReset.setVerticalTextPosition(SwingConstants.CENTER);
		btnReset.setHorizontalTextPosition(SwingConstants.CENTER);
		paelSearch2.add(btnReset);

		btnSearch = new PrimaryButton("Tìm kiếm", "/Image/search.png");
		btnSearch.setPreferredSize(new Dimension(170, 35));
		btnSearch.setFont(new Font("Arial", Font.BOLD, 18));
		btnSearch.setBorderRadius(10);
		btnSearch.setIconSize(26, 26);
		btnSearch.setVerticalTextPosition(SwingConstants.CENTER);
		btnSearch.setHorizontalTextPosition(SwingConstants.RIGHT);
		paelSearch2.add(btnSearch);

		JPanel panelLblDSkm = new JPanel();
		panelLblDSkm.setBackground(Color.WHITE);
		panelLblDSkm.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelLblDSkm.setLayout(new BoxLayout(panelLblDSkm, BoxLayout.X_AXIS));
		CustomTitleLable lblDS = new CustomTitleLable("Danh sách khuyến mãi");
		lblDS.setAlignmentY(0.7f);
		panelLblDSkm.add(lblDS);
		panelSearch1.add(panelLblDSkm, BorderLayout.WEST);

		JPanel panelThongBao = new JPanel();
		panelThongBao.setBackground(Color.WHITE);
		panelSearchAndTable.add(panelThongBao, BorderLayout.SOUTH);
		panelThongBao.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnGuiTB = new PrimaryButton("Gửi thông báo", "/Image/Thongbao.png");
		btnGuiTB.setText("Gửi thông báo");
		btnGuiTB.setIconSize(32, 32);
		btnGuiTB.setPreferredSize(new Dimension(220, 35));
		btnGuiTB.setFont(new Font("Arial", Font.BOLD, 18));
		btnGuiTB.setBorderRadius(15);
		panelThongBao.add(btnGuiTB);
	}

	public JTextField getTxtGiamGia() {
		return txtGiamGia;
	}

	public void setTxtGiamGia(JTextField txtGiamGia) {
		this.txtGiamGia = txtGiamGia;
	}

	public JPanel getQLKhuyenMai_View() {
		return contentPane;
	}

	public JTextField getTxtTenkm() {
		return txtTenKM;
	}

	public JTextField getTxtSLKM() {
		return txtSLKM;
	}

	public JTextArea getTxtNDKM() {
		return txtNDKM;
	}

	public JDateChooser getDateKTKM() {
		return dateKTKM;
	}

	public JTable getTableKM() {
		return tableKM;
	}

	public JDateChooser getDateBDKM() {
		return dateBDKM;
	}

	public void setDateBDKM(JDateChooser dateBDKM) {
		this.dateBDKM = dateBDKM;
	}

	public DefaultTableModel getModelTableKM() {
		return modelTableKM;
	}

	public JComboBox<String> getComboBoxMaKM() {
		return comboBoxMaKM;
	}

	public JComboBox<String> getComboBoxTrangThai() {
		return comboBoxTrangThai;
	}
}
