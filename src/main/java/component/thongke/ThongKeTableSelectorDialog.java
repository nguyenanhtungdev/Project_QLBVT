package component.thongke;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import constant.FontConstants;
import constant.FormatterConstants;
import other.PrimaryButton;
import other.TrainPanel;
import other.TrainScrollPane;
import util.StringUtils;

public class ThongKeTableSelectorDialog extends JDialog {

	private static final long serialVersionUID = -8929981100966771819L;

	private Box pNorth;
	private Box pSearch;
	private Box pTip;
	private Box pSouth;
	private DefaultTableModel model;
	private JTable table;
	private JLabel tip1;
	private JLabel tip2;
	private JLabel tip3;
	private JLabel lSelected;
	private JTextField tfSearch;
	private PrimaryButton btnOK;
	private PrimaryButton btnSearch;

	private Object[] columns;
	private Object[][] rows;
	private boolean isCancelled = true;

	public ThongKeTableSelectorDialog(Frame owner, String title, Object[] columns, Object[][] rows) {
		super(owner, title, ModalityType.APPLICATION_MODAL);

		this.columns = columns;
		this.rows = rows;

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setContentPane(new TrainPanel(new BorderLayout(8, 8), new Insets(16, 16, 16, 16)));

		add(pNorth = Box.createVerticalBox(), BorderLayout.NORTH);
		pNorth.add(pSearch = Box.createHorizontalBox());
		pSearch.add(tfSearch = new JTextField(20));
		pSearch.add(Box.createHorizontalStrut(8));
		pSearch.add(btnSearch = new PrimaryButton("Lọc"));
		pNorth.add(Box.createVerticalStrut(8));
		pNorth.add(pTip = Box.createVerticalBox());
		pTip.add(tip1 = new JLabel("ESC: Xóa bộ lọc, hủy tất cả lựa chọn"));
		pTip.add(tip2 = new JLabel("Bộ tìm kiếm sẽ lọc tất cả các cột"));
		pTip.add(tip3 = new JLabel("Các điều kiện tìm kiếm khác nhau được phân cách bởi dấu chấm phẩy (;)"));

		pSearch.setAlignmentX(LEFT_ALIGNMENT);
		pTip.setAlignmentX(LEFT_ALIGNMENT);
		tip1.setFont(FontConstants.CAPTION.deriveFont(Font.ITALIC));
		tip2.setFont(FontConstants.CAPTION.deriveFont(Font.ITALIC));
		tip3.setFont(FontConstants.CAPTION.deriveFont(Font.ITALIC));
		btnSearch.addActionListener(e -> onBtnSearch());

		add(new TrainScrollPane(table = new JTable(model = new DefaultTableModel())));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setDefaultEditor(Object.class, null);
		table.setDefaultRenderer(Object.class, createTableCellRenderer());
		table.addMouseListener(createMouseAdapter());

		add(pSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pSouth.add(lSelected = new JLabel("Chưa chọn dòng nào"));
		pSouth.add(Box.createHorizontalGlue());
		pSouth.add(btnOK = new PrimaryButton("OK"));

		btnOK.addActionListener(e -> {
			isCancelled = false;
			dispose();
		});

		loadTable();
		addShortcutHandlers();

		pack();
		setLocationRelativeTo(null);
	}

	private void onBtnSearch() {
		reset();

		String keyword = tfSearch.getText();
		if (keyword.isEmpty()) {
			loadTable();
			return;
		}

		String normalizedKeyword = StringUtils.normalize(keyword);
		String[] arrayOfNormalizedKeywords = normalizedKeyword.split(";");
		Stream.of(rows)
				.filter(p -> Stream.of(arrayOfNormalizedKeywords).anyMatch(
						kw -> Stream.of(p).map(Object::toString).anyMatch(t -> StringUtils.normalize(t).contains(kw))))
				.forEach(model::addRow);
		adjustColumnWidths();
	}

	private void addShortcutHandlers() {
		InputMap inputMap = btnSearch.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");

		ActionMap actionMap = btnSearch.getActionMap();
		actionMap.put("ENTER", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSearch.doClick();
			}
		});
		actionMap.put("ESCAPE", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				tfSearch.setText("");
				reset();
				loadTable();
			}
		});
	}

	private MouseListener createMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				lSelected.setText("Đã chọn " + table.getSelectedRows().length + " dòng");
			}
		};
	}

	private TableCellRenderer createTableCellRenderer() {
		return new DefaultTableCellRenderer() {
			private static final long serialVersionUID = -3315724474750674653L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (value instanceof Number || value instanceof LocalDateTime || value instanceof LocalDate
						|| value instanceof LocalTime) {

					if (value instanceof LocalDateTime target) {
						setText(target.format(FormatterConstants.DATETIME));
					} else if (value instanceof LocalDate target) {
						setText(target.format(FormatterConstants.DATE));
					} else if (value instanceof LocalTime target) {
						setText(target.format(FormatterConstants.TIME));
					}

					setHorizontalAlignment(RIGHT);
				} else if (value instanceof Boolean) {
					setHorizontalAlignment(CENTER);
				} else {
					setHorizontalAlignment(LEFT);
				}
				return c;
			}
		};
	}

	private void loadTable() {
		model.setColumnIdentifiers(columns);
		Stream.of(rows).forEach(model::addRow);
		adjustColumnWidths();
	}

	private void adjustColumnWidths() {
		int rowCount = table.getRowCount();
		int colCount = table.getColumnCount();
		JTableHeader tableHeader = table.getTableHeader();
		int columnMargin = table.getColumnModel().getColumnMargin();

		for (int col = 0; col < colCount; col++) {
			int maxWidth = 75; // Minimum width

			// Calculate maximum width based on cell content
			for (int row = 0; row < rowCount; row++) {
				TableCellRenderer cellRenderer = table.getCellRenderer(row, col);
				Component comp = table.prepareRenderer(cellRenderer, row, col);
				if (comp != null) {
					maxWidth = Math.max(comp.getPreferredSize().width + columnMargin, maxWidth);
				}
			}

			// Include header width
			if (tableHeader != null) {
				TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();
				Component headerComp = headerRenderer.getTableCellRendererComponent(table,
						table.getColumnModel().getColumn(col).getHeaderValue(), false, false, 0, col);
				if (headerComp != null) {
					maxWidth = Math.max(headerComp.getPreferredSize().width + columnMargin, maxWidth);
				}
			}

			// Set preferred width for the column
			table.getColumnModel().getColumn(col).setPreferredWidth(maxWidth);
		}

		// Adjust table container dimensions
//		table.setPreferredScrollableViewportSize(
//				new Dimension(table.getPreferredSize().width, table.getPreferredSize().height));
		table.revalidate();
		table.repaint();
	}

	private void reset() {
		table.clearSelection();
		model.setRowCount(0);
		lSelected.setText("Chưa chọn dòng nào");
	}

	public int[] getSelectedRows() {
		return table.getSelectedRows();
	}

	public boolean isCancelled() {
		return isCancelled;
	}

}
