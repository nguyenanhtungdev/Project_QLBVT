package component.thongke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.stream.Stream;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constant.FontConstants;
import other.PrimaryButton;
import other.TrainPanel;
import other.TrainScrollPane;
import util.StringUtils;

public class ThongKeListSelectorDialog extends JDialog {

	private static final long serialVersionUID = -8929981100966771819L;

	private Box pNorth;
	private Box pSearch;
	private Box pTip;
	private Box pSouth;
	private DefaultListModel<Object> model;
	private JList<Object> list;
	private JLabel tip1;
	private JLabel lSelected;
	private JTextField tfSearch;
	private PrimaryButton btnOK;
	private PrimaryButton btnSearch;

	private Object[] rows;
	private boolean isCancelled = true;

	public ThongKeListSelectorDialog(Frame owner, String title, Object[] rows) {
		super(owner, title, ModalityType.APPLICATION_MODAL);

		this.rows = rows;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setContentPane(new TrainPanel(new BorderLayout(8, 8), new Insets(16, 16, 16, 16)));

		// NORTH
		add(pNorth = Box.createVerticalBox(), BorderLayout.NORTH);
		pNorth.add(pSearch = Box.createHorizontalBox());
		pSearch.add(tfSearch = new JTextField(20));
		pSearch.add(Box.createHorizontalStrut(8));
		pSearch.add(btnSearch = new PrimaryButton("Lọc"));
		pNorth.add(Box.createVerticalStrut(8));
		pNorth.add(pTip = Box.createVerticalBox());
		pTip.add(tip1 = new JLabel("ESC: Xóa bộ lọc, hủy tất cả lựa chọn"));

		pSearch.setAlignmentX(LEFT_ALIGNMENT);
		pTip.setAlignmentX(LEFT_ALIGNMENT);
		tip1.setFont(FontConstants.CAPTION.deriveFont(Font.ITALIC));
		btnSearch.addActionListener(e -> onBtnSearch());

		// CENTER
		add(new TrainScrollPane(list = new JList<>(model = new DefaultListModel<>())));
		list.setBorder(new LineBorder(Color.LIGHT_GRAY));
		list.setCellRenderer(createListCellRenederer());
		list.addListSelectionListener(e -> lSelected.setText("Đã chọn " + list.getSelectedIndices().length + " mục"));

		// SOUTH
		add(pSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pSouth.add(lSelected = new JLabel("Chưa chọn mục nào"));
		pSouth.add(Box.createHorizontalGlue());
		pSouth.add(btnOK = new PrimaryButton("OK"));

		btnOK.addActionListener(e -> {
			isCancelled = false;
			dispose();
		});

		loadTable();
		addShortcutHanlders();
		pack();
		setLocationRelativeTo(null);
	}

	public void setSelectedIndices(int[] indices) {
		list.setSelectedIndices(indices);
	}

	private void onBtnSearch() {
		reset();

		String keyword = tfSearch.getText();
		if (keyword.isEmpty()) {
			loadTable();
			return;
		}

		String normalizedKeyword = StringUtils.normalize(keyword);
		String[] arrayOfNormalizedKeyword = normalizedKeyword.split(";");
		Stream.of(rows).map(Object::toString)
				.filter(p -> Stream.of(arrayOfNormalizedKeyword).anyMatch(kw -> StringUtils.normalize(p).contains(kw)))
				.forEach(model::addElement);
		list.setSelectedIndex(0);
	}

	private void addShortcutHanlders() {
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

	private void loadTable() {
		model.clear();
		Stream.of(rows).forEach(model::addElement);
	}

	private void reset() {
		model.clear();
		list.clearSelection();
		lSelected.setText("Chưa chọn mục nào");
	}

	private ListCellRenderer<Object> createListCellRenederer() {
		return new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				label.setFont(FontConstants.TEXT);
				label.setBorder(new EmptyBorder(4, 8, 4, 8));
				return label;
			}
		};
	}

	public int[] getSelectedIndices() {
		return list.getSelectedIndices();
	}

	public boolean isCancelled() {
		return isCancelled;
	}

}
