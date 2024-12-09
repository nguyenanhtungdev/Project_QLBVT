package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import other.PrimaryButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Dimension;
import other.DangerPrimaryButton;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import other.CustomTitleLable;
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;

public class QuanLyCaLam_View extends View {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel modelTableCaLam;
	private JTable tableCaLam;
	private DefaultTableModel modelTableLichLV;
	private JTable tableLichLV;
	private JDateChooser lichTuan;
	private ImageIcon iconlich;
	private PrimaryButton btnLuu;
	private DangerPrimaryButton btnHuy;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyCaLam_View frame = new QuanLyCaLam_View("Ca Làm", "/Image/lichCaLam.png");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void addButtonLuuListener(ActionListener listener) {
		btnLuu.addActionListener(listener);
	}

	public void addButtonHuyListener(ActionListener listener) {
		btnHuy.addActionListener(listener);
	}

	public QuanLyCaLam_View(String name, String imagePath) {
		super(name, imagePath);
		FlatLightLaf.setup();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1250, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(5, 0, 5, 0));
		panel_8.setBackground(Color.WHITE);
		panel.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_8.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		CustomTitleLable cstmtlblDanhSchCa = new CustomTitleLable("Danh sách ca làm");
		cstmtlblDanhSchCa.setAlignmentY(0.7f);
		panel_1.add(cstmtlblDanhSchCa);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel.add(panel_3, BorderLayout.SOUTH);

		String[] header = { "STT", "Mã ca", "Tên ca", "Thời gian bắt đầu", "Thời gian kết thúc", "Ghi chú" };
		Font headerFont = new Font("Arial", Font.BOLD, 18);
		modelTableCaLam = new DefaultTableModel(header, 0) {
			private static final long serialVersionUID = 277400464860378899L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableCaLam = new JTable(modelTableCaLam);
		tableCaLam.setShowGrid(true);
		tableCaLam.setGridColor(new Color(225, 225, 225));
		tableCaLam.getTableHeader().setFont(headerFont);
		Font font = new Font("Arial", Font.PLAIN, 16);
		tableCaLam.setFont(font);
		tableCaLam.getTableHeader().setReorderingAllowed(false);
		panel_3.setLayout(new BorderLayout(0, 0));
		tableCaLam.getColumnModel().getColumn(0).setPreferredWidth(5);
		tableCaLam.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableCaLam.setRowHeight(30);
		tableCaLam.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JTextArea textArea = new JTextArea();
				textArea.setText(value == null ? "" : value.toString());
				textArea.setWrapStyleWord(true);
				textArea.setLineWrap(true);
				textArea.setBackground(isSelected ? new Color(240, 240, 255) : table.getBackground());
				textArea.setEditable(false);
				textArea.setFont(table.getFont());
				textArea.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)));
				return textArea;
			}
		});

		tableCaLam.setSelectionBackground(new Color(240, 240, 255));
		tableCaLam.setSelectionForeground(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane(tableCaLam);
		scrollPane.setPreferredSize(new Dimension(400, 148));
		tableCaLam.setPreferredScrollableViewportSize(new Dimension(400, 148));
		panel_3.add(scrollPane, BorderLayout.NORTH);

		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(30, 0, 5, 0));
		panel_5.setBackground(Color.WHITE);
		panel_4.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(5, 0));

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_5.add(panel_6, BorderLayout.WEST);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

		CustomTitleLable cstmtlblDanhSchCa_1 = new CustomTitleLable("Lịch làm việc");
		cstmtlblDanhSchCa_1.setAlignmentY(0.7f);
		panel_6.add(cstmtlblDanhSchCa_1);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_5.add(panel_7, BorderLayout.EAST);

		lichTuan = new JDateChooser();
		lichTuan.getCalendarButton().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		lichTuan.getCalendarButton().setBackground(new Color(235, 235, 235));
		lichTuan.setPreferredSize(new Dimension(252, 25));
		lichTuan.setFont(new Font("Arial", Font.PLAIN, 16));
		lichTuan.setDateFormatString("dd-MM-yyyy");
		lichTuan.setBorder(new EmptyBorder(0, 56, 0, 5));
		lichTuan.setBackground(Color.WHITE);
		iconlich = new ImageIcon(getClass().getResource("/Image/icon_lich.png"));
		lichTuan.setIcon(new ImageIcon(iconlich.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH)));

		JLabel lblWeek = new JLabel("Tuần: ");
		lblWeek.setFont(new Font("Arial", Font.BOLD, 16));
		panel_7.add(lichTuan);
		panel_7.add(lblWeek);

		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date startOfWeek = calendar.getTime();
		calendar.add(Calendar.DATE, 6);
		Date endOfWeek = calendar.getTime();
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String weekRange = dateFormat.format(startOfWeek) + " - " + dateFormat.format(endOfWeek);

		lblWeek.setText("Tuần " + weekOfYear + ": " + weekRange);
		lichTuan.setDate(startOfWeek);
		lichTuan.getDateEditor().addPropertyChangeListener("date", evt -> {
			Date selectedDate = lichTuan.getDate();
			if (selectedDate != null) {
				calendar.setTime(selectedDate);
				calendar.setFirstDayOfWeek(Calendar.MONDAY);
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				Date newStartOfWeek = calendar.getTime();
				calendar.add(Calendar.DATE, 6);
				Date newEndOfWeek = calendar.getTime();
				int newWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
				String newWeekRange = dateFormat.format(newStartOfWeek) + " - " + dateFormat.format(newEndOfWeek);
				lblWeek.setText("Tuần " + newWeekOfYear + ": " + newWeekRange);
				lichTuan.setDate(newStartOfWeek);

			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(0, 1, 0, 0, (Color) Color.GRAY));
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(7);
		flowLayout.setVgap(0);
		panel_2.setBackground(Color.WHITE);
		panel_7.add(panel_2);

		btnLuu = new PrimaryButton("Lưu", "/Image/icon-Save.png");
		btnLuu.setPreferredSize(new Dimension(170, 35));
		btnLuu.setIconSize(28, 28);
		btnLuu.setFont(new Font("Arial", Font.BOLD, 18));
		btnLuu.setBorderRadius(15);
		panel_2.add(btnLuu);

		btnHuy = new DangerPrimaryButton("Huỷ bỏ     ", "/Image/Cancel.png");
		btnHuy.setPreferredSize(new Dimension(170, 35));
		btnHuy.setIconSize(30, 30);
		btnHuy.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuy.setBorderRadius(15);
		panel_2.add(btnHuy);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(Color.WHITE);
		panel_4.add(panel_3_1, BorderLayout.CENTER);
		panel_3_1.setLayout(new BorderLayout(0, 0));

		String[] header1 = { "Nhân viên", "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật" };
		modelTableLichLV = new DefaultTableModel(header1, 0) {
			private static final long serialVersionUID = 277400464860378899L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableLichLV = new JTable(modelTableLichLV);
		tableLichLV.setShowGrid(true);
		tableLichLV.setGridColor(new Color(225, 225, 225));
		tableLichLV.getTableHeader().setFont(headerFont);
		tableLichLV.setFont(font);
		tableLichLV.getTableHeader().setReorderingAllowed(false);
		tableLichLV.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		Font font1 = new Font("Arial", Font.PLAIN, 17);
		tableLichLV.setFont(font1);
		panel_3_1.setLayout(new BorderLayout(0, 0));
		tableLichLV.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableLichLV.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableLichLV.setRowHeight(45);
		tableLichLV.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JTextArea textArea = new JTextArea();
				textArea.setText(value == null ? "" : value.toString());
				textArea.setWrapStyleWord(true);
				textArea.setLineWrap(true);
				textArea.setBackground(isSelected ? new Color(240, 240, 255) : table.getBackground());
				textArea.setEditable(false);
				textArea.setFont(table.getFont());
				textArea.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)));
				return textArea;
			}
		});
		tableLichLV.setSelectionBackground(new Color(240, 240, 255));
		tableLichLV.setSelectionForeground(Color.BLACK);
		JScrollPane scrollPane1 = new JScrollPane(tableLichLV);
		panel_3_1.add(scrollPane1, BorderLayout.NORTH);

	}

	public JPanel getQLCaLam_View() {
		return contentPane;
	}

	public DefaultTableModel getModelTableCaLam() {
		return modelTableCaLam;
	}

	public void setModelTableCaLam(DefaultTableModel modelTableCaLam) {
		this.modelTableCaLam = modelTableCaLam;
	}

	public JTable getTableCaLam() {
		return tableCaLam;
	}

	public void setTableCaLam(JTable tableCaLam) {
		this.tableCaLam = tableCaLam;
	}

	public DefaultTableModel getModelTableLichLV() {
		return modelTableLichLV;
	}

	public void setModelTableLichLV(DefaultTableModel modelTableLichLV) {
		this.modelTableLichLV = modelTableLichLV;
	}

	public JTable getTableLichLV() {
		return tableLichLV;
	}

	public void setTableLichLV(JTable tableLichLV) {
		this.tableLichLV = tableLichLV;
	}

	public JDateChooser getLichTuan() {
		return lichTuan;
	}

	public void setLichTuan(JDateChooser lichTuan) {
		this.lichTuan = lichTuan;
	}

}
