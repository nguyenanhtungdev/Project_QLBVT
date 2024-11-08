package other;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetailRow {
	private String label;
	private String value;
	private JPanel rowPanel;
	private JLabel valueLabel;

	public DetailRow(String label, String value) {
		this.label = label;
		this.value = value;
		this.rowPanel = createDetailRow();
	}

	private JPanel createDetailRow() {
		JPanel row = new JPanel(new BorderLayout());
		row.setBackground(Color.WHITE);

		JLabel labelLabel = new JLabel(label);
		labelLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		valueLabel = new JLabel(value);
		valueLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		row.add(labelLabel, BorderLayout.WEST);
		row.add(valueLabel, BorderLayout.CENTER);

		return row;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
		rowPanel.revalidate();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		if (valueLabel != null) {
			valueLabel.setText(value);
		}
	}

	public JPanel getRowPanel() {
		return rowPanel;
	}
}
