package other;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.stream.Stream;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TrainTableSelector extends JDialog {

	private static final long serialVersionUID = -8929981100966771819L;
	private Box pNorth;
	private Box pSouth;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel lSelected;
	private JTextField tfSearch;
	private PrimaryButton btnOK;
	private PrimaryButton btnSearch;

	private int[] selectedRows = new int[0];
	private Object[] columns;
	private Object[] rows;

	public TrainTableSelector(String title, Object[] columns, Object[] rows) {
		super(null, title, ModalityType.APPLICATION_MODAL);

		this.columns = columns;
		this.rows = rows;

		this.setContentPane(new TrainPanel(new BorderLayout(8, 8)));
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				reset();
			}
		});

		add(pNorth = Box.createHorizontalBox(), BorderLayout.NORTH);
		pNorth.add(tfSearch = new JTextField(20));
		pNorth.add(Box.createHorizontalStrut(16));
		pNorth.add(btnSearch = new PrimaryButton("Tìm kiếm"));
		btnSearch.addActionListener(e -> onBtnSearch());

		add(new TrainScrollPane(table = new JTable(tableModel = new DefaultTableModel())));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setDefaultEditor(Object.class, null);
		table.setDefaultRenderer(Object.class, new TrainTableCellRenderer());
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				selectedRows = table.getSelectedRows();
				lSelected.setText("Đã chọn " + selectedRows.length + " dòng");
			}
		});

		add(pSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pSouth.add(lSelected = new JLabel("Chưa chọn dòng nào"));
		pSouth.add(Box.createHorizontalGlue());
		pSouth.add(btnOK = new PrimaryButton("OK"));

		btnOK.addActionListener(e -> dispose());

		loadTable();

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void onBtnSearch() {
		reset();

		String keyword = tfSearch.getText();

		if (keyword.isEmpty()) {
			loadTable();
			return;
		}

		String normalizedKeyword = util.StringUtils.normalize(keyword);

		for (Object row : rows) {
			if (row instanceof Object[] target && Stream.of(target).map(Object::toString)
					.anyMatch(cell -> util.StringUtils.normalize(cell).contains(normalizedKeyword))) {
				tableModel.addRow(target);
				continue;
			}

			Field[] fields = row.getClass().getDeclaredFields();
			if (Stream.of(fields).map(f -> {
				try {
					f.setAccessible(true);
					return f.get(row);
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
					return null;
				}
			}).anyMatch(cell -> util.StringUtils.normalize(cell.toString()).contains(normalizedKeyword))) {
				tableModel.addRow(Stream.of(fields).map(f -> {
					try {
						f.setAccessible(true);
						return f.get(row);
					} catch (IllegalArgumentException | IllegalAccessException e1) {
						e1.printStackTrace();
						return null;
					}
				}).toArray());
			}
		}

		adjustColumnWidths();
	}

	private void loadTable() {
		tableModel.setColumnIdentifiers(columns);
		for (Object row : rows) {
			if (row instanceof Object[]) {
				tableModel.addRow((Object[]) row);
				continue;
			}

			Field[] fields = row.getClass().getDeclaredFields();
			tableModel.addRow(Stream.of(fields).map(f -> {
				try {
					f.setAccessible(true);
					return f.get(row);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
					return null;
				}
			}).toArray());
		}

		adjustColumnWidths();
	}

	private void adjustColumnWidths() {
		for (int col = 0; col < table.getColumnCount(); col++) {
			int maxWidth = 75;
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer cellRenderer = table.getCellRenderer(row, col);
				Component comp = table.prepareRenderer(cellRenderer, row, col);
				maxWidth = Math.max(comp.getPreferredSize().width + 10, maxWidth);
			}

			// Include header width
			TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
			Component headerComp = headerRenderer.getTableCellRendererComponent(table,
					table.getColumnModel().getColumn(col).getHeaderValue(), false, false, 0, 0);
			maxWidth = Math.max(headerComp.getPreferredSize().width + 10, maxWidth);

			table.getColumnModel().getColumn(col).setPreferredWidth(maxWidth);
		}

		this.setPreferredSize(new Dimension(table.getPreferredSize().width, this.getPreferredSize().height));
	}

	private void reset() {
		table.clearSelection();
		tableModel.setRowCount(0);
		selectedRows = new int[0];
		lSelected.setText("Chưa chọn dòng nào");
	}

	public int[] getSelectedRows() {
		return selectedRows;
	}

}
