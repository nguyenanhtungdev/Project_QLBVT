package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import connectDB.ConnectDB;
import model.HoaDon;
import model.HoaDon_DAO;
import model.KhachHang;
import other.ColorConstants;
import other.CustomTitleLable;
import other.RoundButton;
import other.RoundField;
import other.RoundedPanel;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Component;

public class QLHoaDon_view extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblQLHD;
	private DefaultTableModel modelTableHD;
	private JTable tableHD;
	private JScrollPane tablePanel;
	private RoundButton btnInDS;
	private JTextField dateField1;
	private JTextField dateField2;
	private static HoaDon_DAO hd_dao;
	private int STT = 1;
	private JTextField txtMaHD;
	private JPanel panel_ngayLap;
	private JDateChooser dateBD;
	private JDateChooser dateKT;
	private RoundButton btnXemCT_1;
	private ImageIcon iconlich;
	private JPanel panelLoc;
	private RoundButton btnSearch;
	private JPanel panelReload;
	private JButton btnReset;
	private JPanel panel_maHD;
	private JPanel panelMaHD;
	private JComboBox<String> comboBoxMaHD;
	private JPanel panel_lblMaHD;
	private JLabel lblMaHD;
	private JPanel panelDate;
	private JPanel panel_lblDate;
	private JLabel lblNgyLpHo;
	private JPanel panel_SDT;
	private JPanel panelSDT;
	private JTextField textField;
	private JPanel panel_lblSDT;
	private JLabel lblSinThoi;
	private JPanel panel_lblloc;
	private JPanel panellblreload;
	private JPanel panelXemCT;
	private RoundButton btnXemCT;
	private JComboBox<String> comboBoxSDT;
	private JPopupMenu filterMenu;
	private JMenuItem maHDItem;
	private JMenuItem dateItem;
	private JMenuItem sdtItem;
	private JPanel panelLogo;
	private JLabel lbl_Icon;
	private ImageIcon iconLogo;
	private JPanel panelChuaLogo;
	private JPanel panelTrong1;
	private DefaultTableModel modelTableHDCT;
	private JTable tableHDCT;
	private JPanel panelTableAndTotal;
	private JPanel panelTotal;
	private JPanel panelTableHDCT;
	private JPanel panelTongtien;
	private JPanel panelThueVAT;
	private JLabel lblThueVAT;
	private JPanel panelTongTien;
	private JLabel lblTongTien;
	private JPanel panelTongTienTT;
	private JLabel lblTongTienTT;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLHoaDon_view frame = new QLHoaDon_view();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void addButtonReloadListener(ActionListener listener) {
		btnReset.addActionListener(listener);
	}

	public void addButtonXemHDCT(ActionListener listener) {
		btnXemCT.addActionListener(listener);
	}

	public void addButtonMaHDItem(ActionListener listener) {
		maHDItem.addActionListener(listener);
	}

	public void addButtonDateItem(ActionListener listener) {
		dateItem.addActionListener(listener);
	}

	public void addButtonSDTItem(ActionListener listener) {
		sdtItem.addActionListener(listener);
	}

	public static JComboBox<String> timKiemSDT() {
		JComboBox<String> comboBox = new JComboBox<>();
		EventList<String> soDienThoaiList = new BasicEventList<>();
		List<HoaDon> danhSachKhachHang = hd_dao.getalltbHDKH();
		for (HoaDon khachHang : danhSachKhachHang) {
			String soDienThoai = khachHang.getKhachHang().getSoDienThoai();
			soDienThoaiList.add(soDienThoai);
			comboBox.addItem(soDienThoai);
		}
		AutoCompleteSupport.install(comboBox, soDienThoaiList);
		return comboBox;
	}

	public static JComboBox<String> timKiemMaHD() {
		JComboBox<String> comboBox = new JComboBox<>();
		EventList<String> maHDList = new BasicEventList<>();
		List<HoaDon> danhSachHD = hd_dao.getalltbHD();
		for (HoaDon hoaDon : danhSachHD) {
			String maHD = hoaDon.getMaHoaDon();
			maHDList.add(maHD);
			comboBox.addItem(maHD);
		}
		AutoCompleteSupport.install(comboBox, maHDList);
		comboBox.setEditable(true);
		return comboBox;
	}

//    btn_DangNhap = new RoundButton("Đăng Nhập", ColorConstants.PRIMARY_COLOR, ColorConstants.HOVER_COLOR);
	public QLHoaDon_view() {
		FlatLightLaf.setup();
		hd_dao = new HoaDon_DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1250, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		lblQLHD = new JLabel("Quản Lý Hoá Đơn");
		lblQLHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblQLHD.setFont(new Font("Arial", Font.BOLD, 20));

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(new Color(255, 255, 255));
		headerPanel.add(lblQLHD, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new EmptyBorder(15, 0, 3, 0));
		headerPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		contentPane.add(headerPanel, BorderLayout.NORTH);

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(255, 255, 255));
		panel.add(searchPanel, BorderLayout.CENTER);
		searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 3));

		panel_maHD = new JPanel();
		panel_maHD.setBackground(Color.WHITE);
		panel_maHD.setBorder(new EmptyBorder(0, 0, 0, 5));
		searchPanel.add(panel_maHD);
		panel_maHD.setLayout(new BoxLayout(panel_maHD, BoxLayout.Y_AXIS));

		panel_lblMaHD = new JPanel();
		panel_lblMaHD.setBorder(new EmptyBorder(0, 0, 5, 5));
		panel_lblMaHD.setBackground(Color.WHITE);
		panel_lblMaHD.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel_maHD.add(panel_lblMaHD);

		lblMaHD = new JLabel("Mã hoá đơn");
		lblMaHD.setForeground(new Color(70, 130, 169));
		lblMaHD.setFont(new Font("Arial", Font.BOLD, 19));
		panel_lblMaHD.add(lblMaHD);

		panelMaHD = new JPanel();
		panelMaHD.setBorder(new EmptyBorder(0, 5, 0, 0));
		panelMaHD.setBackground(Color.WHITE);
		panel_maHD.add(panelMaHD);
		panelMaHD.setLayout(new BoxLayout(panelMaHD, BoxLayout.X_AXIS));

		comboBoxMaHD = timKiemMaHD();
		comboBoxMaHD.setPreferredSize(new Dimension(130, 30));
		comboBoxMaHD.setFont(new Font("Arial", Font.PLAIN, 14));
		panelMaHD.add(comboBoxMaHD);

		panelDate = new JPanel();
		panelDate.setBackground(Color.WHITE);
		searchPanel.add(panelDate);
		panelDate.setLayout(new BoxLayout(panelDate, BoxLayout.Y_AXIS));

		panel_lblDate = new JPanel();
		panel_lblDate.setBorder(new EmptyBorder(0, 0, 5, 5));
		FlowLayout fl_panel_lblDate = (FlowLayout) panel_lblDate.getLayout();
		fl_panel_lblDate.setAlignment(FlowLayout.LEFT);
		panel_lblDate.setBackground(Color.WHITE);
		panelDate.add(panel_lblDate);

		lblNgyLpHo = new JLabel("Ngày lập hoá đơn");
		lblNgyLpHo.setForeground(new Color(70, 130, 169));
		lblNgyLpHo.setFont(new Font("Arial", Font.BOLD, 19));
		panel_lblDate.add(lblNgyLpHo);

		panel_ngayLap = new JPanel();
		panel_ngayLap.setBackground(new Color(255, 255, 255));
		panel_ngayLap.setBorder(new EmptyBorder(0, 5, 0, 0));
		panelDate.add(panel_ngayLap);

		dateBD = new JDateChooser();
		dateBD.setBackground(new Color(255, 255, 255));
		dateBD.getCalendarButton().setBackground(new Color(235, 235, 235));
		dateBD.getCalendarButton().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		dateBD.setBorder(null);
		dateBD.setPreferredSize(new Dimension(150, 30));
		dateBD.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 12));
		dateBD.setFont(new Font("Arial", Font.PLAIN, 16));
		dateBD.setDateFormatString("dd-MM-yyyy");
		iconlich = new ImageIcon(getClass().getResource("/Image/icon_lich.png"));
		panel_ngayLap.setLayout(new BoxLayout(panel_ngayLap, BoxLayout.X_AXIS));
		dateBD.setIcon(new ImageIcon(iconlich.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH)));
		String placeholder = "Tạo từ ngày";
		dateBD.setDate(null);
		JTextField dateField = (JTextField) dateBD.getDateEditor().getUiComponent();
		dateField.setText(placeholder);
		dateField.setForeground(Color.GRAY);
		dateField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (dateField.getText().equals(placeholder)) {
					dateField.setText("");
					dateField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (dateField.getText().isEmpty()) {
					dateField.setText(placeholder);
					dateField.setForeground(Color.GRAY);
				}
			}
		});
		panel_ngayLap.add(dateBD);

		dateKT = new JDateChooser();
		dateKT.setBorder(new EmptyBorder(0, 0, 0, 0));
		dateKT.getCalendarButton().setBorder(null);
		dateKT.getCalendarButton().setBackground(new Color(235, 235, 235));
		dateKT.getCalendarButton().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		dateKT.setBorder(null);
		dateKT.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 12));
		dateKT.setPreferredSize(new Dimension(150, 30));
		dateKT.setFont(new Font("Arial", Font.PLAIN, 16));
		dateKT.setDateFormatString("dd-MM-yyyy");
		dateKT.setIcon(new ImageIcon(iconlich.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH)));
		panel_ngayLap.add(dateKT);
		String placeholder1 = "Đến ngày";
		dateKT.setDate(null);
		JTextField dateField1 = (JTextField) dateKT.getDateEditor().getUiComponent();
		dateField1.setText(placeholder1);
		dateField1.setForeground(Color.GRAY);
		dateField1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (dateField1.getText().equals(placeholder1)) {
					dateField1.setText("");
					dateField1.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (dateField1.getText().isEmpty()) {
					dateField1.setText(placeholder1);
					dateField1.setForeground(Color.GRAY);
				}
			}
		});

		panel_SDT = new JPanel();
		panel_SDT.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_SDT.setBackground(Color.WHITE);
		searchPanel.add(panel_SDT);
		panel_SDT.setLayout(new BoxLayout(panel_SDT, BoxLayout.Y_AXIS));

		panel_lblSDT = new JPanel();
		panel_lblSDT.setBorder(new EmptyBorder(0, 0, 5, 5));
		FlowLayout flowLayout = (FlowLayout) panel_lblSDT.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_lblSDT.setBackground(Color.WHITE);
		panel_SDT.add(panel_lblSDT);

		lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setForeground(new Color(70, 130, 169));
		lblSinThoi.setFont(new Font("Arial", Font.BOLD, 19));
		panel_lblSDT.add(lblSinThoi);

		panelSDT = new JPanel();
		panelSDT.setBorder(new EmptyBorder(0, 5, 0, 0));
		panelSDT.setBackground(Color.WHITE);
		panel_SDT.add(panelSDT);
		panelSDT.setLayout(new BoxLayout(panelSDT, BoxLayout.X_AXIS));

		comboBoxSDT = timKiemSDT();
		comboBoxSDT.setPreferredSize(new Dimension(130, 30));
		comboBoxSDT.setFont(new Font("Arial", Font.PLAIN, 14));
		comboBoxSDT.setEditable(true);
		panelSDT.add(comboBoxSDT);

		panelReload = new JPanel();
		panelReload.setBackground(Color.WHITE);
		searchPanel.add(panelReload);
		panelReload.setLayout(new BorderLayout(0, 0));

		panellblreload = new JPanel();
		panellblreload.setBorder(new EmptyBorder(0, 0, 25, 0));
		panellblreload.setBackground(Color.WHITE);
		panelReload.add(panellblreload, BorderLayout.NORTH);
		btnReset = new JButton();
		btnReset.setIcon(new ImageIcon(getClass().getResource("/Image/reload.png")));
		btnReset.setBorderPainted(false);
		btnReset.setFocusPainted(false);
		btnReset.setVerticalTextPosition(SwingConstants.CENTER);
		btnReset.setHorizontalTextPosition(SwingConstants.CENTER);
		panelReload.add(btnReset);

		panelLoc = new JPanel();
		panelLoc.setBackground(Color.WHITE);
		searchPanel.add(panelLoc);
		panelLoc.setLayout(new BorderLayout(0, 0));

		panel_lblloc = new JPanel();
		panel_lblloc.setBackground(Color.WHITE);
		panel_lblloc.setBorder(new EmptyBorder(0, 0, 25, 0));
		panelLoc.add(panel_lblloc, BorderLayout.NORTH);
		btnSearch = new RoundButton("Lọc", "/Image/search.png");
		btnSearch.setPreferredSize(new Dimension(130, 30));
		btnSearch.setKhoangCachIcon(5);
		btnSearch.setHeSoBoGoc(10);
		btnSearch.setFont(new Font("Arial", Font.BOLD, 18));
		btnSearch.setBorder(new EmptyBorder(4, 10, 4, 10));
		panelLoc.add(btnSearch);
		filterMenu = new JPopupMenu();
		maHDItem = new JMenuItem("Lọc theo mã hóa đơn");
		dateItem = new JMenuItem("Lọc theo ngày");
		sdtItem = new JMenuItem("Lọc theo số điện thoại");

		filterMenu.add(maHDItem);
		filterMenu.add(dateItem);
		filterMenu.add(sdtItem);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterMenu.show(btnSearch, 0, btnSearch.getHeight());
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		CustomTitleLable lblDS = new CustomTitleLable("Danh sách hoá đơn");
		lblDS.setAlignmentY(1.0f);
		panel_1.add(lblDS);

		contentPane.add(headerPanel, BorderLayout.NORTH);

		String[] header = { "STT", "Mã hoá đơn", "Loại hoá đơn", "Tên khách hàng", "Số điện thoại", "Ngày lập",
				"Thuế VAT", "Tổng tiền" };
		Font headerFont = new Font("Arial", Font.BOLD, 18);
		modelTableHD = new DefaultTableModel(header, 0);

		tableHD = new JTable(modelTableHD);
		tableHD.setShowGrid(true);
		tableHD.setGridColor(new Color(225, 225, 225));
		tableHD.getTableHeader().setFont(headerFont);
		tableHD.setFont(new Font("Arial", Font.PLAIN, 16));
		tablePanel = new JScrollPane(tableHD);
		tableHD.getColumnModel().getColumn(0).setPreferredWidth(5);
		centerTableCells(tableHD);
		tableHD.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableHD.setRowHeight(30);
		contentPane.add(new JScrollPane(tableHD), BorderLayout.CENTER);
		DocDuLieuVaoTableHoaDon();

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(Color.WHITE);

		panelXemCT = new JPanel();
		panelXemCT.setBorder(new EmptyBorder(0, 0, 0, 20));
		panelXemCT.setBackground(Color.WHITE);
		buttonPanel.add(panelXemCT);
		panelXemCT.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnXemCT = new RoundButton("Xem chi tiết", "/Image/eye.png");
		btnXemCT.setVerticalTextPosition(SwingConstants.CENTER);
		btnXemCT.setKhoangCachIcon(5);
		btnXemCT.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnXemCT.setHeSoBoGoc(10);
		btnXemCT.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXemCT.setBorder(new EmptyBorder(4, 10, 4, 10));
		panelXemCT.add(btnXemCT);

		btnInDS = new RoundButton("In danh sách hoá đơn", "/Image/print.png");
		btnInDS.setBorder(new EmptyBorder(4, 10, 4, 10));
		btnInDS.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnInDS.setHeSoBoGoc(10);
		btnInDS.setKhoangCachIcon(5);
		btnInDS.setIconSize(26, 26);
		btnInDS.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnInDS.setVerticalTextPosition(SwingConstants.CENTER);
		buttonPanel.add(btnInDS);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
	}

	private JPanel createDetailRow(String label, String value) {
		JPanel row = new JPanel(new BorderLayout());
		row.setBackground(Color.WHITE);

		JLabel labelLabel = new JLabel(label);
		labelLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel valueLabel = new JLabel(value);
		valueLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		row.add(labelLabel, BorderLayout.WEST);
		row.add(valueLabel, BorderLayout.CENTER);

		return row;
	}

	private static void centerTableCells(JTable table) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa

		// Căn giữa cho từng cột
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}

	public JPanel getQLHoaDon_View() {
		return contentPane;
	}

	public JDateChooser getDateBD() {
		return dateBD;
	}

	public JDateChooser getDateKT() {
		return dateKT;
	}

	public JComboBox<String> getCombMaHD() {
		return comboBoxMaHD;
	}

	public JComboBox<String> getCombSDT() {
		return comboBoxSDT;
	}

	public JTable getTableHoaDon() {
		return tableHD;
	}

	public DefaultTableModel getModelHD() {
		return modelTableHD;
	}

	public void xoaDuLieu() {
		DefaultTableModel dm = (DefaultTableModel) tableHD.getModel();
		dm.getDataVector().removeAllElements();
	}

	public void DocDuLieuVaoTableHoaDon() {
		List<HoaDon> list = hd_dao.getalltbHDKH();
		for (HoaDon hd : list) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String ngayLapHoaDoFormatted = hd.getNgayLapHoaDon().format(formatter);

			modelTableHD
					.addRow(new Object[] { STT++, hd.getMaHoaDon(), hd.getLoaiHoaDon(), hd.getKhachHang().getHoTen(),
							hd.getKhachHang().getSoDienThoai(), ngayLapHoaDoFormatted, hd.getThueVAT(),

					});
		}
	}

}
