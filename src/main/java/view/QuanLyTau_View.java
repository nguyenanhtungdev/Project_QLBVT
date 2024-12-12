package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import model.Tau;
import model.Tau_DAO;

import other.CustomTitleLable;
import other.PrimaryButton;

import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class QuanLyTau_View extends View {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel modelTableTau;
	private JTable tableTau;
	private JScrollPane tablePanelTau;
	private PrimaryButton btnSearch;
	private JPanel panel_46;
	private JButton btnTrangTruoc;
	private JLabel lblSoTrang;
	private JButton btnTrangSau;
	public JPanel trainContainer;
	private JLabel lblSoTau;
	private static Tau_DAO t_dao;
	private JPanel panelDS;
	private DefaultTableModel modelTableTTau;
	private JScrollPane tablePanelTTau;
	private JTable tableTTau;
	private JPanel panel_TabelTTau;
	private JPanel panel_TableTau;
	private JPanel panel_Search;
	private JPanel panel_ToaTau;
	private JPanel headerPanel;
	private JPanel panel_TenTau;
	private JPanel panel_45;
	private DefaultTableModel modelTableGhe;
	private JTable tableTGhe;
	private JScrollPane tablePanelGhe;
	private JPanel panel_GheTau;
	private JPanel Panel_DSGheAndTable;
	private JPanel panel_DSGhe;
	private JPanel panel_TenToaTau;
	private JLabel lblTenToaTau;
	private JPanel panel_tenTauAndChuThich;
	private JPanel panel_baotri;
	private JPanel panel_daDat;
	private JPanel panel_xam;
	private JLabel lblXam;
	private JLabel lblDaDat;
	private JPanel panel_trong;
	private JPanel panel_xanh;
	private JLabel lblXanh;
	private JLabel lblTrong;
	private JPanel panel_do;
	private JLabel lblDo;
	private JLabel lblBaotri;
	private JButton btnReset;
	private JComboBox<String> comboBoxMaTau;
	private JComboBox<String> comboBoxTrangThai;

	public static JComboBox<String> timKiemMaTau() {
		JComboBox<String> comboBox = new JComboBox<>();
		EventList<String> maTauList = new BasicEventList<>();
		List<Tau> danhSachTau;
		danhSachTau = t_dao.getAll();
		for (Tau tau : danhSachTau) {
			String maTau = tau.getMaTau();
			maTauList.add(maTau);
			comboBox.addItem(maTau);
		}
		AutoCompleteSupport.install(comboBox, maTauList);

		return comboBox;
	}

	public void addNextButtonListener(ActionListener listener) {
		btnTrangSau.addActionListener(listener);
	}

	public void addPrevButtonListener(ActionListener listener) {
		btnTrangTruoc.addActionListener(listener);
	}

	public void addButtonReloadListener(ActionListener listener) {
		btnReset.addActionListener(listener);
	}

	public void addButtonSearchListener(ActionListener listener) {
		btnSearch.addActionListener(listener);
	}

	public QuanLyTau_View(String name, String imagePath) {
		super(name, imagePath);
		FlatLightLaf.setup();
		t_dao = new Tau_DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1250, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(4, 2, 4, 2));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(Color.WHITE);
		JPanel panel_DSTau = new JPanel();
		panel_DSTau.setBackground(Color.WHITE);
		panel_DSTau.setBorder(new EmptyBorder(5, 5, 5, 5));
		headerPanel.add(panel_DSTau, BorderLayout.CENTER);
		panel_DSTau.setLayout(new BorderLayout(0, 0));

		trainContainer = new JPanel();
		trainContainer.setBorder(new EmptyBorder(5, 20, 0, 20));
		trainContainer.setBackground(Color.WHITE);
		trainContainer.setLayout(new GridLayout(1, 5, 20, 0));
		trainContainer.setBackground(Color.WHITE);

		trainContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 2));
		panel_DSTau.add(trainContainer);

		JPanel panel_Tilte = new JPanel();
		panel_Tilte.setBackground(Color.WHITE);
		headerPanel.add(panel_Tilte, BorderLayout.NORTH);
		panel_Tilte.setLayout(new BorderLayout(0, 0));

		panel_45 = new JPanel();
		panel_DSTau.add(panel_45, BorderLayout.SOUTH);
		panel_45.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_45.setBackground(Color.WHITE);
		panel_45.setLayout(new BorderLayout(0, 0));

		// GD toa tau
		panel_TenTau = new JPanel();
		panel_TenTau.setBorder(new EmptyBorder(5, 0, 0, 0));
		panel_TenTau.setBackground(Color.WHITE);
		panel_TenTau.setLayout(new BoxLayout(panel_TenTau, BoxLayout.X_AXIS));
		panel_TenTau.setVisible(false);

		/////////////// Tàu
		panel_46 = new JPanel();
		panel_46.setBackground(Color.WHITE);
		panel_45.add(panel_46, BorderLayout.CENTER);
		panel_46.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 5));

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new MatteBorder(0, 0, 0, 1, (Color) Color.GRAY));
		panel_6.setBackground(Color.WHITE);
		panel_46.add(panel_6);

		lblSoTau = new JLabel("Tổng số Tàu: 10");
		lblSoTau.setFont(new Font("Arial", Font.BOLD, 16));
		panel_6.add(lblSoTau);

		btnTrangTruoc = new JButton("Trang trước");
		btnTrangTruoc.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_46.add(btnTrangTruoc);

		lblSoTrang = new JLabel("trang: 01");
		lblSoTrang.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_46.add(lblSoTrang);

		btnTrangSau = new JButton("Trang sau");
		btnTrangSau.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_46.add(btnTrangSau);

		///////// Toa Tàu
		panel_ToaTau = new JPanel();
		panel_ToaTau.setBackground(Color.WHITE);
		panel_ToaTau.setBorder(new EmptyBorder(0, 10, 5, 10));
		panel_ToaTau.setLayout(new GridLayout(1, 5, 0, 0));
		panel_ToaTau.setVisible(false);

		///////// tàu
		panel_TableTau = new JPanel();
		panel_TableTau.setBackground(Color.WHITE);
		panel_TableTau.setBorder(new EmptyBorder(0, 10, 0, 10));
		contentPane.add(panel_TableTau, BorderLayout.CENTER);
		panel_TableTau.setLayout(new BorderLayout(0, 0));

		panel_Search = new JPanel();
		panel_Search.setBackground(Color.WHITE);
		panel_Search.setBorder(new EmptyBorder(5, 0, 0, 0));
		panel_TableTau.add(panel_Search, BorderLayout.NORTH);
		panel_Search.setLayout(new BorderLayout(0, 0));

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.WHITE);
		searchPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_Search.add(searchPanel, BorderLayout.EAST);
		searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 2));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new EmptyBorder(0, 0, 0, 10));
		searchPanel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		JLabel label = new JLabel("Mã tàu ");
		label.setFont(new Font("Arial", Font.BOLD, 16));
		panel_2.add(label);

		comboBoxMaTau = timKiemMaTau();
		comboBoxMaTau.setEditable(true); // Cho phép nhập liệu
		comboBoxMaTau.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_2.add(comboBoxMaTau);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new EmptyBorder(0, 0, 0, 5));
		searchPanel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		JLabel label_1 = new JLabel("Trạng thái ");
		label_1.setFont(new Font("Arial", Font.BOLD, 16));
		panel_3.add(label_1);
		String[] items = { "", "Hoạt động", "Bảo trì", "Dừng hoạt động" };
		comboBoxTrangThai = new JComboBox<>(items);
		comboBoxTrangThai.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBoxMaTau.setPreferredSize(new Dimension(150, comboBoxMaTau.getPreferredSize().height));
		panel_3.add(comboBoxTrangThai);

		btnReset = new JButton();
		btnReset.setIcon(new ImageIcon(getClass().getResource("/Image/reload.png")));
		btnReset.setBorderPainted(false);
		btnReset.setFocusPainted(false);
		btnReset.setVerticalTextPosition(SwingConstants.CENTER);
		btnReset.setHorizontalTextPosition(SwingConstants.CENTER);
		searchPanel.add(btnReset);

		btnSearch = new PrimaryButton("Tìm kiếm", "/Image/search.png");
		btnSearch.setBorder(new EmptyBorder(4, 10, 4, 10));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSearch.setBorderRadius(10);
		btnSearch.setIconSize(26, 26);
		btnSearch.setVerticalTextPosition(SwingConstants.CENTER);
		btnSearch.setVerticalAlignment(SwingConstants.TOP);
		btnSearch.setHorizontalTextPosition(SwingConstants.RIGHT);
		searchPanel.add(btnSearch);

		JPanel panel_lbldsTau = new JPanel();
		panel_lbldsTau.setBackground(Color.WHITE);
		panel_lbldsTau.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_lbldsTau.setLayout(new BoxLayout(panel_lbldsTau, BoxLayout.X_AXIS));
		CustomTitleLable lblDS = new CustomTitleLable("Danh sách tàu");
		lblDS.setAlignmentY(0.7f);
		panel_lbldsTau.add(lblDS);
		panel_Search.add(panel_lbldsTau, BorderLayout.WEST);
		contentPane.add(headerPanel, BorderLayout.NORTH);

		String[] header = { "STT", "Mã tàu", "Tên tàu", "Số toa", "Năm sản xuất", "Nhà sản xuất", "Tốc độ trung bình",
				"Tốc độ tối đa", "Trạng thái", "Ghi chú" };
		modelTableTau = new DefaultTableModel(header, 0) {
			private static final long serialVersionUID = 6293001865070277594L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		tableTau = new JTable(modelTableTau);
		tableTau.setShowGrid(true);
		tableTau.setGridColor(new Color(225, 225, 225));
		tableTau.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableTau.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
		Font font = new Font("Arial", Font.PLAIN, 13); 
		tableTau.setFont(font);
		tableTau.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableTau.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableTau.getColumnModel().getColumn(2).setPreferredWidth(170);
		tableTau.getColumnModel().getColumn(3).setPreferredWidth(30);
		tableTau.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableTau.getColumnModel().getColumn(5).setPreferredWidth(165);
		tableTau.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableTau.getColumnModel().getColumn(7).setPreferredWidth(70);
		tableTau.getColumnModel().getColumn(8).setPreferredWidth(50);
		tableTau.getColumnModel().getColumn(9).setPreferredWidth(170);
		tablePanelTau = new JScrollPane(tableTau);
		tableTau.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableTau.getTableHeader().setReorderingAllowed(false);
		tableTau.setRowHeight(30);
		panel_TableTau.add(tablePanelTau);

		// ds Toa Tau
		panelDS = new JPanel();
		panelDS.setLayout(new BorderLayout());
		String[] headerTT = { "STT", "Mã toa", "Tên toa", "Số thu tự toa", "Loại toa", "Số lượng ghế", "Trạng thái" };
		modelTableTTau = new DefaultTableModel(headerTT, 0) {
			private static final long serialVersionUID = 1646158358200751731L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		tableTTau = new JTable(modelTableTTau);
		tableTTau.setShowGrid(true);
		tableTTau.setGridColor(new Color(225, 225, 225));
		tableTau.getColumnModel().getColumn(0).setPreferredWidth(20); // STT
		tablePanelTTau = new JScrollPane(tableTTau);
		tableTTau.setRowHeight(30);
		tableTTau.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		Font font1 = new Font("Arial", Font.PLAIN, 16); 
		tableTTau.setFont(font1);
		tableTTau.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableTTau.getTableHeader().setReorderingAllowed(false);

		JPanel panel_lblTTau = new JPanel();
		panel_lblTTau.setBackground(Color.WHITE);
		panel_lblTTau.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_lblTTau.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		CustomTitleLable lblDSTT = new CustomTitleLable("Danh sách toa tàu");
		panel.add(lblDSTT);

		panelDS.add(panel_lblTTau, BorderLayout.NORTH);
		panel_TabelTTau = new JPanel();
		panelDS.add(panel_TabelTTau, BorderLayout.CENTER);
		panel_TabelTTau.setLayout(new BoxLayout(panel_TabelTTau, BoxLayout.X_AXIS));
		panel_TabelTTau.add(tablePanelTTau);
		panelDS.setVisible(false);

		// GheTau
		panel_GheTau = new JPanel();
		panel_GheTau.setBackground(Color.WHITE);
		panel_GheTau.setLayout(new BorderLayout(0, 0));

		panel_tenTauAndChuThich = new JPanel();
		panel_tenTauAndChuThich.setBackground(Color.WHITE);
		panel_tenTauAndChuThich.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_GheTau.add(panel_tenTauAndChuThich, BorderLayout.NORTH);
		panel_tenTauAndChuThich.setLayout(new BorderLayout(0, 0));

		panel_TenToaTau = new JPanel();
		panel_TenToaTau.setBackground(Color.WHITE);
		panel_TenToaTau.setBorder(new EmptyBorder(0, 0, 40, 0));
		panel_TenToaTau.setBorder(new EmptyBorder(5, 0, 0, 100));
		FlowLayout fl_panel_tenTau = (FlowLayout) panel_TenToaTau.getLayout();
		fl_panel_tenTau.setAlignment(FlowLayout.RIGHT);
		panel_tenTauAndChuThich.add(panel_TenToaTau, BorderLayout.CENTER);

		lblTenToaTau = new JLabel("", SwingConstants.CENTER);
		lblTenToaTau.setFont(new Font("Arial", Font.BOLD, 16));
		lblTenToaTau.setForeground(new Color(70, 130, 180));
		panel_TenToaTau.add(lblTenToaTau);

		JPanel panel_chuThich = new JPanel();
		panel_chuThich.setBorder(new EmptyBorder(0, 50, 0, 10));
		panel_chuThich.setBackground(Color.WHITE);
		panel_tenTauAndChuThich.add(panel_chuThich, BorderLayout.EAST);
		panel_chuThich.setLayout(new BoxLayout(panel_chuThich, BoxLayout.X_AXIS));

		panel_daDat = new JPanel();
		panel_daDat.setBackground(Color.WHITE);
		panel_chuThich.add(panel_daDat);

		panel_xam = new JPanel();
		panel_xam.setBackground(Color.LIGHT_GRAY);
		panel_daDat.add(panel_xam);

		lblXam = new JLabel("    ");
		panel_xam.add(lblXam);

		lblDaDat = new JLabel("Đã mua");
		lblDaDat.setFont(new Font("Arial", Font.BOLD, 16));
		panel_daDat.add(lblDaDat);

		panel_trong = new JPanel();
		panel_trong.setBackground(Color.WHITE);
		panel_chuThich.add(panel_trong);

		panel_xanh = new JPanel();
		panel_xanh.setBackground(new Color(70, 130, 169));
		panel_trong.add(panel_xanh);

		lblXanh = new JLabel("    ");
		panel_xanh.add(lblXanh);

		lblTrong = new JLabel("Còn trống");
		lblTrong.setFont(new Font("Arial", Font.BOLD, 16));
		panel_trong.add(lblTrong);

		panel_baotri = new JPanel();
		panel_baotri.setBackground(Color.WHITE);
		panel_chuThich.add(panel_baotri);

		panel_do = new JPanel();
		panel_do.setBackground(Color.RED);
		panel_baotri.add(panel_do);

		lblDo = new JLabel("    ");
		panel_do.add(lblDo);

		lblBaotri = new JLabel("Bảo trì");
		lblBaotri.setFont(new Font("Arial", Font.BOLD, 16));
		panel_baotri.add(lblBaotri);

		Panel_DSGheAndTable = new JPanel();
		Panel_DSGheAndTable.setBackground(Color.WHITE);
		Panel_DSGheAndTable.setBorder(new EmptyBorder(0, 10, 10, 10));
		Panel_DSGheAndTable.setLayout(new BorderLayout());
		panel_GheTau.add(Panel_DSGheAndTable);

		panel_DSGhe = new JPanel();
		panel_DSGhe.setBackground(Color.WHITE);
		panel_DSGhe.setBorder(
				new CompoundBorder(new LineBorder(new Color(149, 149, 255), 1, true), new EmptyBorder(10, 10, 10, 10)));

		JPanel panel_TableGhe = new JPanel();
		panel_TableGhe.setBackground(Color.WHITE);
		panel_TableGhe.setBorder(new EmptyBorder(10, 0, 0, 0));
		Panel_DSGheAndTable.add(panel_TableGhe, BorderLayout.CENTER);

		// tableGhe
		String[] headerGhe = { "STT", "Mã ghế", "Loại ghế", "Số toa", "Trạng thái" };
		modelTableGhe = new DefaultTableModel(headerGhe, 0) {
			private static final long serialVersionUID = 1489491129915894460L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		panel_TableGhe.setLayout(new BoxLayout(panel_TableGhe, BoxLayout.X_AXIS));
		tableTGhe = new JTable(modelTableGhe);
		tableTGhe.setShowGrid(true);
		tableTGhe.setGridColor(new Color(225, 225, 225));
		tableTGhe.getColumnModel().getColumn(0).setPreferredWidth(20); // STT
		tablePanelGhe = new JScrollPane(tableTGhe);
		tableTGhe.setRowHeight(30);
		tableTGhe.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tableTGhe.setFont(font1);
		tableTGhe.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableTGhe.getTableHeader().setReorderingAllowed(false);
		panel_TableGhe.add(tablePanelGhe);

		panel_GheTau.setVisible(false);
	}

	public JPanel getQLTau_View() {
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

	// dsTauIcon
	public JPanel getPanel_dsTau() {
		return trainContainer;
	}

	public void setPanel_dsTau(JPanel trainContainer) {
		this.trainContainer = trainContainer;
	}

	// dsToaTau
	public JPanel getPanel_dsToaTau() {
		return panelDS;
	}

	public void setPanel_dsToaTau(JPanel panelDS) {
		this.panelDS = panelDS;
	}

	public JPanel getPanel_toaTauIcon() {
		return panel_ToaTau;
	}

	public void setPanel_toaTauIcon(JPanel panel_ToaTau) {
		this.panel_ToaTau = panel_ToaTau;
	}

	public JPanel getPanel_TableTau() {
		return panel_TableTau;
	}

	public void setPanel_TableTau(JPanel panel_TableTau) {
		this.panel_TableTau = panel_TableTau;
	}

	public JPanel getPanel_headerPanel() {
		return headerPanel;
	}

	public void setPanel_headerPanel(JPanel headerPanel) {
		this.headerPanel = headerPanel;
	}

	public JPanel getPanel_Page() {
		return panel_45;
	}

	public void setPanel_Page(JPanel panel_45) {
		this.panel_45 = panel_45;
	}

	public JPanel getPanel_TenTau() {
		return panel_TenTau;
	}

	public void setPanel_TenTau(JPanel panel_TenTau) {
		this.panel_TenTau = panel_TenTau;
	}

	public JPanel getPanel_GheTau() {
		return panel_GheTau;
	}

	public void setPanel_GheTau(JPanel panel_GheTau) {
		this.panel_GheTau = panel_GheTau;
	}

	public JPanel getPanel_TenToaTau() {
		return panel_TenToaTau;
	}

	public void setPanel_TenToaTau(JPanel panel_TenToaTau) {
		this.panel_TenToaTau = panel_TenToaTau;
	}

	public JPanel getPanel_DSGheAndTable() {
		return Panel_DSGheAndTable;
	}

	public void setPanel_DSGheAndTable(JPanel Panel_DSGheAndTable) {
		this.Panel_DSGheAndTable = Panel_DSGheAndTable;
	}

	public JPanel getPanel_DSGhe() {
		return panel_DSGhe;
	}

	public void setPanel_DSGhe(JPanel panel_DSGhe) {
		this.panel_DSGhe = panel_DSGhe;
	}

	public JLabel getLblSoTau() {
		return lblSoTau;
	}

	public void setLblSoTau(JLabel lblSoTau) {
		this.lblSoTau = lblSoTau;
	}

	public JLabel getlblTenToaTau() {
		return lblTenToaTau;
	}

	public void setlblTenToaTau(JLabel lblTenToaTau) {
		this.lblTenToaTau = lblTenToaTau;
	}

	public DefaultTableModel getModelTau() {
		return modelTableTau;
	}

	public void setModelTau(DefaultTableModel modelTableTau) {
		this.modelTableTau = modelTableTau;
	}

	public DefaultTableModel getModelTTau() {
		return modelTableTTau;
	}

	public void setModelTTau(DefaultTableModel modelTableTTau) {
		this.modelTableTTau = modelTableTTau;
	}

	public DefaultTableModel getModelGheTau() {
		return modelTableGhe;
	}

	public void setModelGheTau(DefaultTableModel modelTableGhe) {
		this.modelTableGhe = modelTableGhe;
	}

	public JComboBox<String> getComBmaTau() {
		return comboBoxMaTau;
	}

	public JComboBox<String> getComBTThai() {
		return comboBoxTrangThai;
	}

	public JTable getTableTau() {
		return tableTau;
	}

	public JTable getTableGheTau() {
		return tableTGhe;
	}
}
