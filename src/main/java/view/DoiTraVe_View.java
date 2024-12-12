package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
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
import java.awt.event.ActionEvent;

public class DoiTraVe_View extends View {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JTextField tenKhachHangField, soDienThoaiField, cccdField;
	private JTable danhSachVeTable;
	private PrimaryButton timKiemButton, xacNhanButton;
	private JRadioButton soDienThoaiRadioButton, cccdRadioButton;
	private ButtonGroup buttonGroup;
	private PrimaryButton lamMoiButton;

	// Thêm sự kiện cho lớp hoàn tiền view
	public void addSuKien(ActionListener listener, MouseListener mouseListener) {
		soDienThoaiRadioButton.addActionListener(listener);
		cccdRadioButton.addActionListener(listener);
		timKiemButton.addActionListener(listener);
		xacNhanButton.addActionListener(listener);
		lamMoiButton.addActionListener(listener);
		danhSachVeTable.addMouseListener(mouseListener);
		
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

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new CompoundBorder(new LineBorder(ColorConstants.PRIMARY_COLOR), new EmptyBorder(5, 5, 5, 5)));
		panel_1.setBackground(Color.WHITE);
		inputPanel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		buttonGroup = new ButtonGroup();

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		Component horizontalStrut_1_5_1 = Box.createHorizontalStrut(80);
		panel_2.add(horizontalStrut_1_5_1);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_4.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel label = new JLabel("Tên khách hàng:");
		panel_8.add(label);
		label.setFont(new Font("Arial", Font.BOLD, 18));
		label.setForeground(ColorConstants.PRIMARY_COLOR);
		tenKhachHangField = new JTextField(15);
		panel_4.add(tenKhachHangField);
		tenKhachHangField.putClientProperty("JTextField.placeholderText", "Nhập tên khách hàng");
		tenKhachHangField.setFont(new Font("Arial", Font.PLAIN, 16));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBorder(new EmptyBorder(20, 0, 0, 0));
		panel_2.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));

		Component horizontalStrut_1_5_2 = Box.createHorizontalStrut(255);
		panel_2.add(horizontalStrut_1_5_2);

		// Radio buttons for selecting search by phone or CCCD
		soDienThoaiRadioButton = new JRadioButton("Số điện thoại", true);
		panel_5.add(soDienThoaiRadioButton);
		soDienThoaiRadioButton.setBackground(Color.WHITE);
		soDienThoaiRadioButton.setFont(new Font("Arial", Font.BOLD, 18));
		soDienThoaiRadioButton.setForeground(ColorConstants.PRIMARY_COLOR);
		buttonGroup.add(soDienThoaiRadioButton);
		cccdRadioButton = new JRadioButton("CCCD");
		panel_5.add(cccdRadioButton);
		cccdRadioButton.setBackground(Color.WHITE);
		cccdRadioButton.setForeground(ColorConstants.PRIMARY_COLOR);
		cccdRadioButton.setFont(new Font("Arial", Font.BOLD, 18));
		buttonGroup.add(cccdRadioButton);
		cccdRadioButton.addActionListener(e -> {
			soDienThoaiField.setEnabled(false);
			cccdField.setEnabled(true);
		});

		// Add action listener to radio buttons to enable/disable fields
		soDienThoaiRadioButton.addActionListener(e -> {
			soDienThoaiField.setEnabled(true);
			cccdField.setEnabled(false);
		});

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_1.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));

		Component horizontalStrut_1_5_11 = Box.createHorizontalStrut(80);
		panel_3.add(horizontalStrut_1_5_11);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_6.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel label_1 = new JLabel("Số điện thoại:");
		panel_9.add(label_1);
		label_1.setFont(new Font("Arial", Font.BOLD, 18));
		label_1.setForeground(ColorConstants.PRIMARY_COLOR);
		soDienThoaiField = new JTextField(15);
		panel_6.add(soDienThoaiField);
		soDienThoaiField.putClientProperty("JTextField.placeholderText", "Nhập số điện thoại");
		soDienThoaiField.setFont(new Font("Arial", Font.PLAIN, 16));

		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));

		JPanel panel_InputThongTin1 = new JPanel();
		panel_7.add(panel_InputThongTin1);
		panel_InputThongTin1.setBorder(null);
		panel_InputThongTin1.setBackground(Color.WHITE);
		panel_InputThongTin1.setLayout(new BoxLayout(panel_InputThongTin1, BoxLayout.Y_AXIS));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.WHITE);
		panel_InputThongTin1.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel label_2 = new JLabel("CCCD:");
		label_2.setForeground(new Color(70, 130, 169));
		label_2.setFont(new Font("Arial", Font.BOLD, 18));
		panel_10.add(label_2);
		cccdField = new JTextField(15);
		cccdField.setEnabled(false);
		cccdField.putClientProperty("JTextField.placeholderText", "Nhập CCCD");
		cccdField.setFont(new Font("Arial", Font.PLAIN, 16));
		panel_InputThongTin1.add(cccdField);

		JPanel panel_InputThongTin = new JPanel();
		panel_InputThongTin
				.setBorder(new CompoundBorder(new LineBorder(new Color(70, 130, 169), 0), new EmptyBorder(0, 0, 0, 0)));
		panel_InputThongTin.setBackground(Color.WHITE);
		inputPanel.add(panel_InputThongTin);
		panel_InputThongTin.setLayout(new BoxLayout(panel_InputThongTin, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel_InputThongTin.add(panel);
		panel.setPreferredSize(new Dimension(180, 0));
		panel.setMaximumSize(new Dimension(180, Integer.MAX_VALUE));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

		lamMoiButton = new PrimaryButton("Làm mới", "/Image/restore.png");
		panel.add(lamMoiButton);
		lamMoiButton.setFont(new Font("Arial", Font.BOLD, 18));
		lamMoiButton.setInsets(new Insets(4, 6, 4, 6));
		lamMoiButton.setBorderRadius(10);
		lamMoiButton.setIconTextGap(5);
		lamMoiButton.setIconSize(22, 22);
		lamMoiButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		lamMoiButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		lamMoiButton.setPreferredSize(new Dimension(135, 35));

		timKiemButton = new PrimaryButton("Tìm kiếm", "/Image/search.png");
		panel.add(timKiemButton);
		timKiemButton.setFont(new Font("Arial", Font.BOLD, 18));
		timKiemButton.setInsets(new Insets(4, 6, 4, 6));
		timKiemButton.setBorderRadius(10);
		timKiemButton.setIconTextGap(5);
		timKiemButton.setIconSize(22, 22);
		timKiemButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		timKiemButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		timKiemButton.setPreferredSize(new Dimension(135, 35));

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

		String[] columnNames = { "STT", "Mã Hóa Đơn", "Mã Khách Hàng", "Tên Khách Hàng", "Ngày Lập Hóa Đơn",
				"Thành Tiền" };
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
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new EmptyBorder(0, 10, 0, 50));
		bottomPanel.setPreferredSize(new Dimension(0, 60));
		bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		bottomPanel.setBackground(Color.WHITE);
		mainPanel.add(bottomPanel);

		xacNhanButton = new PrimaryButton("Xác nhận trả vé");
		xacNhanButton.setNormalColor(new Color(70, 130, 180));
		xacNhanButton.setFont(new Font("Arial", Font.BOLD, 18));
		xacNhanButton.setInsets(new Insets(4, 6, 4, 6));
		xacNhanButton.setPreferredSize(new Dimension(160, 35));
		xacNhanButton.setVisible(false);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
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

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JTextField getTenKhachHangField() {
		return tenKhachHangField;
	}

	public JRadioButton getCccdRadioButton() {
		return cccdRadioButton;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public PrimaryButton getLamMoiButton() {
		return lamMoiButton;
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
