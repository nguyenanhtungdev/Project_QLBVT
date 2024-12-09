package other;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.*;

public class MultiCheckPopupMenuEditor extends AbstractCellEditor implements TableCellEditor {
	private JPanel panel;
	private JCheckBox[] checkBoxes;
	private JPopupMenu popupMenu;
	private String[] options;
	private JTable table;
	private int row;
	private int column;

	public MultiCheckPopupMenuEditor(String[] options) {
		this.options = options;
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		checkBoxes = new JCheckBox[options.length];
		for (int i = 0; i < options.length; i++) {
			checkBoxes[i] = new JCheckBox(options[i]);
			checkBoxes[i].setFont(new Font("Arial", Font.PLAIN, 16));
			checkBoxes[i].setPreferredSize(new Dimension(140, 25));
			panel.add(checkBoxes[i]);
		}
		popupMenu = new JPopupMenu();
		for (JCheckBox checkBox : checkBoxes) {
			popupMenu.add(checkBox);
		}

		popupMenu.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				updateCellValue();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				updateCellValue();
			}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

			}
		});
	}

	@Override
	public Object getCellEditorValue() {
		StringBuilder selectedValues = new StringBuilder();
		for (JCheckBox checkBox : checkBoxes) {
			if (checkBox.isSelected()) {
				if (selectedValues.length() > 0) {
					selectedValues.append(", ");
				}
				selectedValues.append(checkBox.getText());
			}
		}
		return selectedValues.toString();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.table = table;
		this.row = row;
		this.column = column;
		updateCheckboxes(value);

		Rectangle cellRect = table.getCellRect(row, column, true);
		popupMenu.show(table, cellRect.x, cellRect.y + cellRect.height);
		return panel;
	}

	private void updateCheckboxes(Object value) {
		if (value != null && !value.toString().isEmpty()) {
			String[] selectedValues = value.toString().split(", ");
			Set<String> selectedSet = new HashSet<>(Arrays.asList(selectedValues));
			for (JCheckBox checkBox : checkBoxes) {
				checkBox.setSelected(selectedSet.contains(checkBox.getText()));
			}
		} else {
			for (JCheckBox checkBox : checkBoxes) {
				checkBox.setSelected(false);
			}
		}
	}

	private void updateCellValue() {
		StringBuilder selectedText = new StringBuilder();
		for (JCheckBox checkBox : checkBoxes) {
			if (checkBox.isSelected()) {
				if (selectedText.length() > 0) {
					selectedText.append(", ");
				}
				selectedText.append(checkBox.getText());
			}
		}
		table.setValueAt(selectedText.toString(), row, column);
		stopCellEditing();
	}
}
