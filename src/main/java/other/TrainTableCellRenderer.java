package other;

import java.awt.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TrainTableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = -3315724474750674653L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (value instanceof Number || value instanceof LocalDateTime || value instanceof LocalDate
				|| value instanceof LocalTime) {
			setHorizontalAlignment(RIGHT);
		} else if (value instanceof Boolean) {
			setHorizontalAlignment(CENTER);
		} else {
			setHorizontalAlignment(LEFT);
		}
		return c;
	}
}
