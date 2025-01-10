
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constant.ColorConstants;

import javax.swing.border.CompoundBorder;

import other.PrimaryButton;
import other.RoundedPanel;
import other.DangerPrimaryButton;
import other.DetailRow;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

public class ThongTinVe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelLogo;
	private JLabel lbl_Icon;
	private ImageIcon iconLogo;
	private JPanel panelTrong1;
	private JPanel panelTableAndTotal;
	private JPanel panelTableHDCT;
	private JPanel panelTotal;
	private JPanel panelTongtien;
	private JPanel panelThueVAT;
	private JLabel lblThueVAT;
	private JPanel panelTongTienTT;
	private JLabel lblTongTienTT;
	private JPanel panel;
	private JPanel panel_1;
	private PrimaryButton hoanVeButton;
	private DangerPrimaryButton inPDFButton;
	private JScrollPane scrollPane;
	private JLabel dateLabel;
	private DetailRow maHoaDonLabel;
	private DetailRow tenKhachHangLabel;
	private DetailRow soDienThoaiLabel;
	private DetailRow soTienLabel;
	private DetailRow soTienTraLabel;
	private JTextArea lydoHoanTienField;
	private ButtonGroup lydoButtonGroup;
	private JTextField tenKhachHangField;
	private JTextField soDienThoaiField;
	private JTextField ngayMuaField;
	private JTextField soTienDaThanhToanField;
	private JTextArea lyDoNgoaiLeTextArea;
	private JTextField tienHoanField;
	private JTextField tiLeHoanTienField;
	private JPanel panel_2;
	private DetailRow soTienDaThanhToanLabel;
	private DetailRow ngayMuaLabel;
	private DetailRow lydoHoanTienLabel;
	private DetailRow ghiChuLabel;
	private DetailRow ngayHoanTienLabel;
	private DetailRow maKhachHangLabel;
	private PrimaryButton hoanVeButton_1;

	public void addSuKien(ActionListener listener) {
		hoanVeButton.addActionListener(listener);
		hoanVeButton_1.addActionListener(listener);
		inPDFButton.addActionListener(listener);
	}

	public ThongTinVe() {
		setTitle("Thông tin vé tàu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		setLocationRelativeTo(null);

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
		iconLogo = new ImageIcon(getClass().getResource("/Image/Logo1.png"));
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

		JLabel titleLabel = new JLabel("THÔNG TIN VÉ TÀU", JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setForeground(Color.BLUE);

		dateLabel = new JLabel("", JLabel.CENTER);
		dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setBackground(Color.WHITE);
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		titlePanel.add(dateLabel, BorderLayout.SOUTH);
		headerPanelCT.add(titlePanel, BorderLayout.CENTER);

		// Content Panel
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBackground(Color.WHITE);

		maKhachHangLabel = new DetailRow("Mã khách hàng: ", "");
		maKhachHangLabel.getRowPanel().setFont(new Font("Arial", Font.PLAIN, 18));
		maKhachHangLabel.setColor_Label(ColorConstants.PRIMARY_COLOR);
		contentPanel.add(maKhachHangLabel.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(20));

		maHoaDonLabel = new DetailRow("Mã hóa đơn: ", "");
		maHoaDonLabel.getRowPanel().setFont(new Font("Arial", Font.PLAIN, 18));
		maHoaDonLabel.setColor_Label(ColorConstants.PRIMARY_COLOR);
		contentPanel.add(maHoaDonLabel.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(20));

		tenKhachHangLabel = new DetailRow("Tên khách hàng: ", "");
		tenKhachHangLabel.getRowPanel().setFont(new Font("Arial", Font.PLAIN, 18));
		tenKhachHangLabel.setColor_Label(ColorConstants.PRIMARY_COLOR);
		contentPanel.add(tenKhachHangLabel.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(20));

		soDienThoaiLabel = new DetailRow("Số điện thoại: ", "");
		soDienThoaiLabel.getRowPanel().setFont(new Font("Arial", Font.PLAIN, 18));
		soDienThoaiLabel.setColor_Label(ColorConstants.PRIMARY_COLOR);
		contentPanel.add(soDienThoaiLabel.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(20));

		ngayMuaLabel = new DetailRow("Thời gian mua: ", "");
		ngayMuaLabel.getRowPanel().setFont(new Font("Arial", Font.PLAIN, 18));
		ngayMuaLabel.setColor_Label(ColorConstants.PRIMARY_COLOR);
		contentPanel.add(ngayMuaLabel.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(20));

		ngayHoanTienLabel = new DetailRow("Thời gian hoàn tiền: ", "");
		ngayHoanTienLabel.getRowPanel().setFont(new Font("Arial", Font.PLAIN, 18));
		ngayHoanTienLabel.setColor_Label(ColorConstants.PRIMARY_COLOR);
		contentPanel.add(ngayHoanTienLabel.getRowPanel());
		contentPanel.add(Box.createVerticalStrut(20));

		soTienDaThanhToanLabel = new DetailRow("Số tiền đã thanh toán: ", "");
		soTienDaThanhToanLabel.getRowPanel().setFont(new Font("Arial", Font.PLAIN, 18));
		soTienDaThanhToanLabel.setColor_Label(ColorConstants.PRIMARY_COLOR);
		contentPanel.add(soTienDaThanhToanLabel.getRowPanel());

		contentPanel.add(Box.createVerticalStrut(20));

		lydoHoanTienLabel = new DetailRow("Lý do hoàn tiền: ", "");
		lydoHoanTienLabel.getRowPanel().setFont(new Font("Arial", Font.PLAIN, 18));
		lydoHoanTienLabel.setColor_Label(ColorConstants.PRIMARY_COLOR);
		contentPanel.add(lydoHoanTienLabel.getRowPanel());

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		contentPanel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lyDoNgoaiLeTextArea = new JTextArea(3, 20);
		lyDoNgoaiLeTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
		lyDoNgoaiLeTextArea.setLineWrap(true);
		lyDoNgoaiLeTextArea.setWrapStyleWord(true);
		lyDoNgoaiLeTextArea.setBorder(new LineBorder(ColorConstants.PRIMARY_COLOR, 1, true));
		contentPanel.add(lyDoNgoaiLeTextArea);

		contentPanel.add(Box.createVerticalStrut(20));

		ghiChuLabel = new DetailRow("Ghi chú: ", "");
		ghiChuLabel.getRowPanel().setFont(new Font("Arial", Font.PLAIN, 18));
		ghiChuLabel.setColor_Label(ColorConstants.PRIMARY_COLOR);
		lydoHoanTienField = new JTextArea();
		lydoHoanTienField.setFont(new Font("Arial", Font.PLAIN, 18));
		lydoHoanTienField.setLineWrap(true);
		lydoHoanTienField.setWrapStyleWord(true);
		lydoHoanTienField.setPreferredSize(new Dimension(lydoHoanTienField.getPreferredSize().width, 100));
		lydoHoanTienField.setBorder(new LineBorder(ColorConstants.PRIMARY_COLOR, 1, true));
		contentPanel.add(ghiChuLabel.getRowPanel());
		contentPanel.add(lydoHoanTienField);

		panelChiTiet.add(contentPanel, BorderLayout.CENTER);

		panelTableAndTotal = new JPanel();
		panelTableAndTotal.setBorder(new EmptyBorder(0, 0, 0, 10));
		panelTableAndTotal.setBackground(Color.WHITE);
		panelChiTiet.add(panelTableAndTotal, BorderLayout.SOUTH);
		panelTableAndTotal.setLayout(new BorderLayout(0, 0));

		panelTotal = new JPanel();
		panelTotal.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelTotal.setBackground(Color.WHITE);
		panelTableAndTotal.add(panelTotal, BorderLayout.SOUTH);
		panelTotal.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));

		panelTongtien = new JPanel();
		panelTongtien.setBackground(Color.WHITE);
		panelTongtien.setBorder(new LineBorder(ColorConstants.PRIMARY_COLOR, 1, true));
		panelTongtien.setPreferredSize(new Dimension(350, 120));
		panelTotal.add(panelTongtien);
		panelTongtien.setLayout(new BoxLayout(panelTongtien, BoxLayout.Y_AXIS));

		panelThueVAT = new JPanel();
		panelThueVAT.setBorder(new EmptyBorder(0, 10, 0, 10));
		panelThueVAT.setBackground(Color.WHITE);
		panelThueVAT.setLayout(new BorderLayout(0, 0));
		lblThueVAT = new JLabel("Tỉ lệ hoàn tiền:");
		lblThueVAT.setForeground(new Color(70, 130, 169));
		lblThueVAT.setFont(new Font("Arial", Font.BOLD, 18));
		tiLeHoanTienField = new JTextField();
		tiLeHoanTienField.setHorizontalAlignment(SwingConstants.RIGHT);
		tiLeHoanTienField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tiLeHoanTienField.setBackground(Color.WHITE);
		tiLeHoanTienField.setEditable(false);
		tiLeHoanTienField.setBorder(null);
		panelThueVAT.add(tiLeHoanTienField, BorderLayout.EAST);

		panelThueVAT.add(lblThueVAT, BorderLayout.CENTER);
		panelTongtien.add(panelThueVAT);

		panelTongTienTT = new JPanel();
		panelTongTienTT.setBorder(new EmptyBorder(0, 10, 0, 10));
		panelTongTienTT.setBackground(Color.WHITE);
		panelTongTienTT.setLayout(new BorderLayout(0, 0));
		lblTongTienTT = new JLabel("Số tiền trả lại:");
		lblTongTienTT.setForeground(new Color(70, 130, 169));
		lblTongTienTT.setFont(new Font("Arial", Font.BOLD, 18));
		tienHoanField = new JTextField();
		tienHoanField.setHorizontalAlignment(SwingConstants.RIGHT);
		tienHoanField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tienHoanField.setBackground(Color.WHITE);
		tienHoanField.setEditable(false);
		tienHoanField.setBorder(null);
		panelTongTienTT.add(tienHoanField, BorderLayout.EAST);
		panelTongTienTT.add(lblTongTienTT, BorderLayout.CENTER);
		panelTongtien.add(panelTongTienTT);

		scrollPane = new JScrollPane(panelChiTiet);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		getContentPane().add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.SOUTH);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1);

		hoanVeButton = new PrimaryButton("Hoàn vé", "/Image/return.png");
		hoanVeButton.setForeground(Color.WHITE);
		hoanVeButton.setFont(new Font("Arial", Font.BOLD, 14));
		hoanVeButton.setPreferredSize(new Dimension(170, 40));
		panel_1.add(hoanVeButton);

		hoanVeButton_1 = new PrimaryButton("Xem trước PDF", "/Image/show_detail.png");
		hoanVeButton_1.setPreferredSize(new Dimension(170, 40));
		hoanVeButton_1.setForeground(Color.WHITE);
		hoanVeButton_1.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1.add(hoanVeButton_1);

		inPDFButton = new DangerPrimaryButton("In PDF", "/Image/printer.png");
		inPDFButton.setForeground(Color.WHITE);
		inPDFButton.setFont(new Font("Arial", Font.BOLD, 14));
		inPDFButton.setPreferredSize(new Dimension(170, 40));
		panel_1.add(inPDFButton);
	}

	public PrimaryButton getHoanVeButton() {
		return hoanVeButton;
	}

	public DangerPrimaryButton getInPDFButton() {
		return inPDFButton;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getPanelLogo() {
		return panelLogo;
	}

	public JLabel getLbl_Icon() {
		return lbl_Icon;
	}

	public ImageIcon getIconLogo() {
		return iconLogo;
	}

	public JPanel getPanelTrong1() {
		return panelTrong1;
	}

	public JPanel getPanelTableAndTotal() {
		return panelTableAndTotal;
	}

	public JPanel getPanelTableHDCT() {
		return panelTableHDCT;
	}

	public JPanel getPanelTotal() {
		return panelTotal;
	}

	public JPanel getPanelTongtien() {
		return panelTongtien;
	}

	public JPanel getPanelThueVAT() {
		return panelThueVAT;
	}

	public JLabel getLblThueVAT() {
		return lblThueVAT;
	}

	public JPanel getPanelTongTienTT() {
		return panelTongTienTT;
	}

	public JLabel getLblTongTienTT() {
		return lblTongTienTT;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JLabel getDateLabel() {
		return dateLabel;
	}

	public DetailRow getTenKhachHangLabel() {
		return tenKhachHangLabel;
	}

	public DetailRow getSoDienThoaiLabel() {
		return soDienThoaiLabel;
	}

	public DetailRow getSoTienLabel() {
		return soTienLabel;
	}

	public DetailRow getSoTienTraLabel() {
		return soTienTraLabel;
	}

	public JTextArea getLydoHoanTienField() {
		return lydoHoanTienField;
	}

	public ButtonGroup getLydoButtonGroup() {
		return lydoButtonGroup;
	}

	public JTextField getTenKhachHangField() {
		return tenKhachHangField;
	}

	public JTextField getSoDienThoaiField() {
		return soDienThoaiField;
	}

	public JTextField getNgayMuaField() {
		return ngayMuaField;
	}

	public JTextField getSoTienDaThanhToanField() {
		return soTienDaThanhToanField;
	}

	public JTextArea getLyDoNgoaiLeTextArea() {
		return lyDoNgoaiLeTextArea;
	}

	public JTextField getTienHoanField() {
		return tienHoanField;
	}

	public JTextField getTiLeHoanTienField() {
		return tiLeHoanTienField;
	}

	public JPanel getPanel_2() {
		return panel_2;
	}

	public DetailRow getSoTienDaThanhToanLabel() {
		return soTienDaThanhToanLabel;
	}

	public DetailRow getNgayMuaLabel() {
		return ngayMuaLabel;
	}

	public DetailRow getLydoHoanTienLabel() {
		return lydoHoanTienLabel;
	}

	public DetailRow getGhiChuLabel() {
		return ghiChuLabel;
	}

	public DetailRow getNgayHoanTienLabel() {
		return ngayHoanTienLabel;
	}

	public DetailRow getMaKhachHangLabel() {
		return maKhachHangLabel;
	}

	public DetailRow getMaHoaDonLabel() {
		return maHoaDonLabel;
	}

	public PrimaryButton getHoanVeButton_1() {
		return hoanVeButton_1;
	}

}
