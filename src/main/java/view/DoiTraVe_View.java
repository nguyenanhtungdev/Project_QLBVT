package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import constant.ColorConstants;

import javax.swing.table.DefaultTableCellRenderer;

import other.PrimaryButton;

public class DoiTraVe_View extends View {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JTextField tenKhachHangField, soDienThoaiField, cccdField;
	private JTable danhSachVeTable;
	private PrimaryButton timKiemButton, xacNhanButton;
	private JRadioButton soDienThoaiRadioButton, cccdRadioButton;
	private ButtonGroup buttonGroup;

	// Thêm sự kiện cho lớp hoàn tiền view
	public void addSuKien(ActionListener listener) {
		soDienThoaiRadioButton.addActionListener(listener);
		cccdRadioButton.addActionListener(listener);
		timKiemButton.addActionListener(listener);
		xacNhanButton.addActionListener(listener);
	}

	public DoiTraVe_View(String name, String iconPath) {
		super(name, iconPath);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);
		setLocationRelativeTo(null);

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(null);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);

		// Phần trên dùng FlowLayout
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(0, 200));
		topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
		topPanel.setBackground(Color.WHITE);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		mainPanel.add(topPanel);

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setPreferredSize(new Dimension(0, 50));
		titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		topPanel.add(titlePanel);
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		JLabel lblTitle = new JLabel("Thông tin khách hàng");
		titlePanel.add(lblTitle);
		lblTitle.setOpaque(true);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setBorder(new EmptyBorder(5, 10, 5, 20));
		lblTitle.setBackground(new Color(70, 130, 169));

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		topPanel.add(inputPanel);
		inputPanel.setPreferredSize(new Dimension(0, 150));
		inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

		JPanel panel_InputThongTin = new JPanel();
		panel_InputThongTin
				.setBorder(new CompoundBorder(new LineBorder(new Color(70, 130, 169)), new EmptyBorder(10, 5, 5, 5)));
		panel_InputThongTin.setBackground(Color.WHITE);
		inputPanel.add(panel_InputThongTin);
		panel_InputThongTin.setLayout(new BoxLayout(panel_InputThongTin, BoxLayout.Y_AXIS));

		JPanel panel_InputThongTin1 = new JPanel();
		panel_InputThongTin1.setBackground(Color.WHITE);
		panel_InputThongTin.add(panel_InputThongTin1);
		panel_InputThongTin1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		panel_InputThongTin1.add(new JLabel("Tên khách hàng:"));
		tenKhachHangField = new JTextField(15);
		tenKhachHangField.putClientProperty("JTextField.placeholderText", "Nhập tên khách hàng");
		tenKhachHangField.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_InputThongTin1.add(tenKhachHangField);

		// Radio buttons for selecting search by phone or CCCD
		soDienThoaiRadioButton = new JRadioButton("Số điện thoại", false);
		cccdRadioButton = new JRadioButton("CCCD");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(soDienThoaiRadioButton);
		buttonGroup.add(cccdRadioButton);

		panel_InputThongTin1.add(soDienThoaiRadioButton);
		panel_InputThongTin1.add(cccdRadioButton);

		panel_InputThongTin1.add(new JLabel("Số điện thoại:"));
		soDienThoaiField = new JTextField(15);
		soDienThoaiField.putClientProperty("JTextField.placeholderText", "Nhập số điện thoại");
		soDienThoaiField.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_InputThongTin1.add(soDienThoaiField);

		panel_InputThongTin1.add(new JLabel("CCCD:"));
		cccdField = new JTextField(15);
		cccdField.putClientProperty("JTextField.placeholderText", "Nhập CCCD");
		cccdField.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_InputThongTin1.add(cccdField);

		// Add action listener to radio buttons to enable/disable fields
		soDienThoaiRadioButton.addActionListener(e -> {
			soDienThoaiField.setEnabled(true);
			cccdField.setEnabled(false);
		});
		cccdRadioButton.addActionListener(e -> {
			soDienThoaiField.setEnabled(false);
			cccdField.setEnabled(true);
		});

		timKiemButton = new PrimaryButton("Tìm kiếm", "/Image/search.png");
		timKiemButton.setBackground(new Color(0, 123, 255));
//		timKiemButton.setForeground(Color.WHITE);
		timKiemButton.setFont(new Font("Arial", Font.BOLD, 14));
//		timKiemButton.setFocusPainted(false);
//		timKiemButton.setBorder(new EmptyBorder(4, 6, 4, 6));
		timKiemButton.setInsets(new Insets(4, 6, 4, 6));
		timKiemButton.setBorderRadius(10);
		timKiemButton.setIconTextGap(5);
		timKiemButton.setIconSize(22, 22);
		panel_InputThongTin1.add(timKiemButton);

		// Phần giữa chứa bảng
		JPanel panel_center = new JPanel();
		panel_center.setBackground(Color.WHITE);
		mainPanel.add(panel_center);
		panel_center.setLayout(new BoxLayout(panel_center, BoxLayout.Y_AXIS));

		JPanel panel_TitleDanhSach = new JPanel();
		panel_TitleDanhSach.setBackground(Color.WHITE);
		panel_TitleDanhSach.setPreferredSize(new Dimension(0, 60));
		panel_TitleDanhSach.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		panel_center.add(panel_TitleDanhSach);
		panel_TitleDanhSach.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));

		JLabel lblDanhSach = new JLabel("Danh sách hóa đơn");
		lblDanhSach.setOpaque(true);
		lblDanhSach.setForeground(Color.WHITE);
		lblDanhSach.setFont(new Font("Arial", Font.BOLD, 20));
		lblDanhSach.setBorder(new EmptyBorder(5, 10, 5, 20));
		lblDanhSach.setBackground(new Color(70, 130, 169));
		panel_TitleDanhSach.add(lblDanhSach);

		JPanel panel_Table = new JPanel();
		panel_Table.setBorder(new EmptyBorder(0, 20, 5, 15));
		panel_Table.setBackground(Color.WHITE);
		panel_Table.setPreferredSize(new Dimension(0, 530));
		panel_Table.setMaximumSize(new Dimension(Integer.MAX_VALUE, 530));
		panel_center.add(panel_Table);
		panel_Table.setLayout(new BoxLayout(panel_Table, BoxLayout.X_AXIS));

		String[] columnNames = { "STT", "Mã Hóa Đơn", "Tên Khách Hàng", "Ngày Lập Hóa Đơn", "Thành Tiền" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		danhSachVeTable = new JTable(tableModel);
		danhSachVeTable.setRowHeight(25);
		danhSachVeTable.setFont(new Font("Arial", Font.PLAIN, 16));
		danhSachVeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		danhSachVeTable.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (value instanceof java.util.Date) {
					SimpleDateFormat formatter = new SimpleDateFormat("dd 'thg' MM, yyyy");
					setText(formatter.format((java.util.Date) value));
				}
				return c;
			}
		});

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < danhSachVeTable.getColumnCount(); i++) {
			danhSachVeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		JTableHeader header = danhSachVeTable.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 16));
		header.setReorderingAllowed(false);
		for (int i = 0; i < danhSachVeTable.getColumnCount(); i++) {
			danhSachVeTable.getColumnModel().getColumn(i).setResizable(true);
		}

		JScrollPane tableScrollPane = new JScrollPane(danhSachVeTable);
		tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_Table.add(tableScrollPane);

		// Phần dưới dùng FlowLayout
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBorder(new EmptyBorder(0, 10, 0, 50));
		bottomPanel.setPreferredSize(new Dimension(0, 60));
		bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		bottomPanel.setBackground(Color.WHITE);
		mainPanel.add(bottomPanel);

		xacNhanButton = new PrimaryButton("Xác nhận trả vé");
		xacNhanButton.setNormalColor(new Color(70, 130, 180));
//		xacNhanButton.setForeground(Color.WHITE);
		xacNhanButton.setFont(new Font("Arial", Font.BOLD, 14));
//		xacNhanButton.setFocusPainted(false);
//		xacNhanButton.setBorder(new EmptyBorder(4, 6, 4, 6));
		xacNhanButton.setInsets(new Insets(4, 6, 4, 6));
//		xacNhanButton.setHeSoBoGoc(10);
//		xacNhanButton.setKhoangCachIcon(5);
//		xacNhanButton.setIconSize(22, 22);
		bottomPanel.add(xacNhanButton);
	}

	public JPanel getHoanTien_view() {
		return mainPanel;
	}

	public JRadioButton getSoDienThoaiRadioButton() {
		return soDienThoaiRadioButton;
	}

	public JTextField getSoDienThoaiField() {
		return soDienThoaiField;
	}

	public JTextField getCccdField() {
		return cccdField;
	}

	public JTable getDanhSachVeTable() {
		return danhSachVeTable;
	}

	public PrimaryButton getTimKiemButton() {
		return timKiemButton;
	}

	public PrimaryButton getXacNhanButton() {
		return xacNhanButton;
	}

	// Custom renderer với JTextArea để xuống dòng cho nội dung dài
	class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {
		public MultiLineCellRenderer() {
			setLineWrap(true);
			setWrapStyleWord(true);
			setOpaque(true);
			setFont(new Font("Arial", Font.PLAIN, 14));
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
}
