package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import other.DetailRow;
import other.PrimaryButton;
import other.RoundedPanel;
import java.awt.event.ActionListener;
import javax.swing.border.CompoundBorder;

public class HoaDonChiTiet_View extends JFrame implements Printable {
	private static final long serialVersionUID = 1L;
	private JPanel panelLogo;
	private JLabel lbl_Icon;
	private ImageIcon iconLogo;
	private JPanel panelTrong1;
	private DefaultTableModel modelTableHDCT;
	private JTable tableHDCT;
	private JPanel panelTableAndTotal;
	private JPanel panelTableHDCT;
	private JPanel panelTotal;
	private JPanel panelTongtien;
	private JPanel panelTongTien;
	private JLabel lblTongTien;
	private JPanel panelThueVAT;
	private JLabel lblThueVAT;
	private JPanel panelTongTienTT;
	private JLabel lblTongTienTT;
	private JPanel panel;
	private JPanel panel_1;
	private PrimaryButton btnInCTHD;
	private JScrollPane scrollPane;
	private JLabel dateLabel;
	private JLabel maHoaDonLabel;
	private DetailRow donViBH;
	private DetailRow maSoThue;
	private DetailRow diaChi;
	private DetailRow soTaiKhoan;
	private DetailRow tenNganHang;
	private DetailRow hoTenKH;
	private DetailRow soDienThoai;
	private DetailRow email;
	private DetailRow loaiKhachHang;
	private DetailRow hinhThucThanhToan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HoaDonChiTiet_View frame = new HoaDonChiTiet_View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HoaDonChiTiet_View() {
		setTitle("Hoá Đơn Chi Tiết");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 700);

		JPanel panelChiTiet = new JPanel();
		panelChiTiet.setBackground(Color.WHITE);
		panelChiTiet.setLayout(new BorderLayout());
		panelChiTiet.setBackground(Color.WHITE);
		panelChiTiet.setBorder(
				new CompoundBorder(new LineBorder(new Color(70, 130, 169), 1, true), new EmptyBorder(20, 20, 20, 20)));

		// Header Panel
		JPanel headerPanelCT = new JPanel(new BorderLayout());
		headerPanelCT.setBorder(new EmptyBorder(0, 0, 25, 0));
		headerPanelCT.setBackground(Color.WHITE);
		panelLogo = new JPanel();
		panelLogo.setBackground(Color.WHITE);
		lbl_Icon = new JLabel("");
		iconLogo = new ImageIcon(getClass().getResource("/Image/logo1.png"));
		lbl_Icon.setPreferredSize(new Dimension(250, 70));
		lbl_Icon.setMaximumSize(new Dimension(250, 70));
		lbl_Icon.setIcon(new ImageIcon(iconLogo.getImage().getScaledInstance(130, 70, Image.SCALE_SMOOTH)));

		RoundedPanel panelChuaLogo = new RoundedPanel(20);
		panelChuaLogo.setPreferredSize(new Dimension(265, 65));
		panelChuaLogo.setBackground(new Color(70, 130, 169));
		panelChuaLogo.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelChuaLogo.setLayout(new BoxLayout(panelChuaLogo, BoxLayout.X_AXIS));
		lbl_Icon.setIcon(new ImageIcon(iconLogo.getImage().getScaledInstance(270, 80, Image.SCALE_SMOOTH)));
		panelChuaLogo.add(lbl_Icon);
		panelLogo.add(panelChuaLogo);
		headerPanelCT.add(panelLogo, BorderLayout.WEST);

		JLabel titleLabel = new JLabel("HÓA ĐƠN GIÁ TRỊ GIA TĂNG", JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setForeground(Color.BLUE);

		dateLabel = new JLabel("Ngày 03 tháng 10 năm 2024, 19 giờ 20 phút 37 giây", JLabel.CENTER);
		dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setBackground(Color.WHITE);
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		titlePanel.add(dateLabel, BorderLayout.SOUTH);
		headerPanelCT.add(titlePanel, BorderLayout.CENTER);

		JPanel codePanel = new JPanel();
		codePanel.setBorder(new EmptyBorder(0, 0, 0, 10));
		codePanel.setBackground(Color.WHITE);
		maHoaDonLabel = new JLabel("Mã hóa đơn: 1024123435");
		maHoaDonLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		maHoaDonLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		codePanel.setLayout(new BoxLayout(codePanel, BoxLayout.Y_AXIS));

		panelTrong1 = new JPanel();
		panelTrong1.setBackground(Color.WHITE);
		codePanel.add(panelTrong1);
		codePanel.add(maHoaDonLabel);

		headerPanelCT.add(codePanel, BorderLayout.EAST);
		panelChiTiet.add(headerPanelCT, BorderLayout.NORTH);

		// Content Panel
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(0, 0, 25, 10));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBackground(Color.WHITE);

		donViBH = new DetailRow("Đơn vị bán hàng: ", "");
		contentPanel.add(donViBH.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(10));

		maSoThue = new DetailRow("Mã số thuế: ", "");
		contentPanel.add(maSoThue.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(10));

		diaChi = new DetailRow("Địa chỉ: ", "");
		contentPanel.add(diaChi.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(10));

		soTaiKhoan = new DetailRow("Số tài khoản: ", "");
		contentPanel.add(soTaiKhoan.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(10));

		tenNganHang = new DetailRow("Tên ngân hàng: ", "");
		contentPanel.add(tenNganHang.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(30));

		hoTenKH = new DetailRow("Họ và tên khách hàng: ", "");
		contentPanel.add(hoTenKH.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(10));

		soDienThoai = new DetailRow("Số điện thoại: ", "");
		contentPanel.add(soDienThoai.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(10));

		email = new DetailRow("Email: ", "");
		contentPanel.add(email.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(10));

		loaiKhachHang = new DetailRow("Loại khách hàng: ", "");
		contentPanel.add(loaiKhachHang.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(10));

		hinhThucThanhToan = new DetailRow("Hình thức thanh toán: ", "");
		contentPanel.add(hinhThucThanhToan.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(10));

		panelChiTiet.add(contentPanel, BorderLayout.CENTER);

		String[] headerCT = { "STT", "Mã vé", "Loại vé", "Số lượng vé", "Đơn giá", "Thành tiền trước thuế", "Giảm giá",
				"Thuế suất", "Tổng tiền sau thuế" };
		modelTableHDCT = new DefaultTableModel(headerCT, 0);
		tableHDCT = new JTable(modelTableHDCT);
		tableHDCT.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableHDCT.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableHDCT.getColumnModel().getColumn(5).setPreferredWidth(200);
		tableHDCT.getColumnModel().getColumn(8).setPreferredWidth(180);
		tableHDCT.setShowGrid(true);
		tableHDCT.setGridColor(new Color(225, 225, 225));
		tableHDCT.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tableHDCT.setFont(new Font("Arial", Font.PLAIN, 15));
		tableHDCT.setRowHeight(30);
		int rowHeight = tableHDCT.getRowHeight();
		int rowCount = modelTableHDCT.getRowCount();
		getContentPane().setLayout(new BorderLayout(0, 0));

		panelTableAndTotal = new JPanel();
		panelTableAndTotal.setBorder(new EmptyBorder(0, 0, 0, 10));
		panelTableAndTotal.setBackground(Color.WHITE);
		panelChiTiet.add(panelTableAndTotal, BorderLayout.SOUTH);
		panelTableAndTotal.setLayout(new BorderLayout(0, 0));
		panelTableHDCT = new JPanel();
		panelTableHDCT.setBorder(new EmptyBorder(0, 0, 0, 10));
		panelTableHDCT.setBackground(Color.WHITE);
		panelTableAndTotal.add(panelTableHDCT, BorderLayout.CENTER);
		panelTableHDCT.setLayout(new BorderLayout(0, 0));
		tableHDCT.setPreferredScrollableViewportSize(new Dimension(1000, rowHeight * rowCount));
		panelTableHDCT.add(new JScrollPane(tableHDCT));

		panelTotal = new JPanel();
		panelTotal.setBorder(new EmptyBorder(20, 0, 0, 5));
		FlowLayout flowLayout_1 = (FlowLayout) panelTotal.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelTotal.setBackground(Color.WHITE);
		panelTableAndTotal.add(panelTotal, BorderLayout.SOUTH);

		panelTongtien = new JPanel();
		panelTongtien.setBackground(Color.WHITE);
		panelTongtien.setBorder(new LineBorder(new Color(53, 154, 255), 1, true));
		panelTongtien.setPreferredSize(new Dimension(350, 120));
		panelTotal.add(panelTongtien);
		panelTongtien.setLayout(new BoxLayout(panelTongtien, BoxLayout.Y_AXIS));
		panelTongTien = new JPanel();
		panelTongTien.setBackground(Color.WHITE);
		panelTongtien.add(panelTongTien);
		panelTongTien.setLayout(new BorderLayout(0, 0));
		lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setForeground(new Color(70, 130, 169));
		lblTongTien.setFont(new Font("Arial", Font.BOLD, 18));
		panelTongTien.add(lblTongTien);

		panelThueVAT = new JPanel();
		panelThueVAT.setBackground(Color.WHITE);
		panelTongtien.add(panelThueVAT);
		panelThueVAT.setLayout(new BorderLayout(0, 0));
		lblThueVAT = new JLabel("Thuế VAT:");
		lblThueVAT.setForeground(new Color(70, 130, 169));
		lblThueVAT.setFont(new Font("Arial", Font.BOLD, 18));
		panelThueVAT.add(lblThueVAT);
		panelTongTienTT = new JPanel();
		panelTongTienTT.setBackground(Color.WHITE);
		panelTongtien.add(panelTongTienTT);
		panelTongTienTT.setLayout(new BorderLayout(0, 0));

		lblTongTienTT = new JLabel("Tổng tiền thanh toán:");
		lblTongTienTT.setForeground(new Color(70, 130, 169));
		lblTongTienTT.setFont(new Font("Arial", Font.BOLD, 18));
		panelTongTienTT.add(lblTongTienTT, BorderLayout.CENTER);

		scrollPane = new JScrollPane(panelChiTiet);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		getContentPane().add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.SOUTH);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1);

		btnInCTHD = new PrimaryButton("In danh sách hoá đơn", "/Image/print.png");
		btnInCTHD.setText("In hoá đơn");
		btnInCTHD.setVerticalTextPosition(SwingConstants.CENTER);
		btnInCTHD.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnInCTHD.setBorderRadius(10);
		btnInCTHD.setIconSize(26, 26);
		btnInCTHD.setFont(new Font("Arial", Font.BOLD, 16));
		btnInCTHD.setBorder(new EmptyBorder(4, 10, 4, 10));
		panel_1.add(btnInCTHD);
	}

	public JLabel getLblTongTien() {
		return lblTongTien;
	}

	public void setLblTongTien(JLabel lblTongTien) {
		this.lblTongTien = lblTongTien;
	}

	public JLabel getLblThueVAT() {
		return lblThueVAT;
	}

	public void setLblThueVAT(JLabel lblThueVAT) {
		this.lblThueVAT = lblThueVAT;
	}

	public JLabel getLblTongTienTT() {
		return lblTongTienTT;
	}

	public void setLblTongTienTT(JLabel lblTongTienTT) {
		this.lblTongTienTT = lblTongTienTT;
	}

	public DefaultTableModel getModelTableHDCT() {
		return modelTableHDCT;
	}

	public void setModelTableHDCT(DefaultTableModel modelTableHDCT) {
		this.modelTableHDCT = modelTableHDCT;
	}

	public JTable getTableHDCT() {
		return tableHDCT;
	}

	public void setTableHDCT(JTable tableHDCT) {
		this.tableHDCT = tableHDCT;
	}

	public DetailRow getDonViBH() {
		return donViBH;
	}

	public void setDonViBH(DetailRow donViBH) {
		this.donViBH = donViBH;
	}

	public DetailRow getMaSoThue() {
		return maSoThue;
	}

	public void setMaSoThue(DetailRow maSoThue) {
		this.maSoThue = maSoThue;
	}

	public DetailRow getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(DetailRow diaChi) {
		this.diaChi = diaChi;
	}

	public DetailRow getSoTaiKhoan() {
		return soTaiKhoan;
	}

	public void setSoTaiKhoan(DetailRow soTaiKhoan) {
		this.soTaiKhoan = soTaiKhoan;
	}

	public DetailRow getTenNganHang() {
		return tenNganHang;
	}

	public void setTenNganHang(DetailRow tenNganHang) {
		this.tenNganHang = tenNganHang;
	}

	public DetailRow getHoTenKH() {
		return hoTenKH;
	}

	public void setHoTenKH(DetailRow hoTenKH) {
		this.hoTenKH = hoTenKH;
	}

	public DetailRow getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(DetailRow soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public DetailRow getEmail() {
		return email;
	}

	public void setEmail(DetailRow email) {
		this.email = email;
	}

	public DetailRow getLoaiKhachHang() {
		return loaiKhachHang;
	}

	public void setLoaiKhachHang(DetailRow loaiKhachHang) {
		this.loaiKhachHang = loaiKhachHang;
	}

	public DetailRow getHinhThucThanhToan() {
		return hinhThucThanhToan;
	}

	public void setHinhThucThanhToan(DetailRow hinhThucThanhToan) {
		this.hinhThucThanhToan = hinhThucThanhToan;
	}

	public JLabel getMaHoaDonLabel() {
		return maHoaDonLabel;
	}

	public void setMaHoaDonLabel(JLabel maHoaDonLabel) {
		this.maHoaDonLabel = maHoaDonLabel;
	}

	public JLabel getDateLabel() {
		return dateLabel;
	}

	public void setDateLabel(JLabel dateLabel) {
		this.dateLabel = dateLabel;
	}

	public void addInActionListener(ActionListener actionListener) {
		btnInCTHD.addActionListener(actionListener);
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex > 0) {
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		int panelWidth = scrollPane.getViewport().getView().getPreferredSize().width;
		int panelHeight = scrollPane.getViewport().getView().getPreferredSize().height;
		double pageWidth = pageFormat.getImageableWidth();
		double pageHeight = pageFormat.getImageableHeight();
		double scaleX = pageWidth / panelWidth;
		double scaleY = pageHeight / panelHeight;
		double scale = Math.min(scaleX, scaleY);

		g2d.scale(scale, scale);
		scrollPane.getViewport().getView().printAll(g2d);

		return PAGE_EXISTS;
	}

	public void print() {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);

		if (job.printDialog()) {
			try {
				job.print();
			} catch (PrinterException e) {
				e.printStackTrace();
			}
		}
	}

}
