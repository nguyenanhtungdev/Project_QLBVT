package other;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import constant.ColorConstants;

public class DetailRow {
	private String label;
	private String value;
	private JPanel rowPanel;
	private JLabel valueLabel;
	private Color color_Label = Color.BLACK;
	private JLabel labelLabel;

	public DetailRow(String label, String value) {
		this.label = label;
		this.value = value;
		this.rowPanel = createDetailRow();
	}

	private JPanel createDetailRow() {
		JPanel row = new JPanel(new BorderLayout());
		row.setBackground(Color.WHITE);

		labelLabel = new JLabel(label);
		labelLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelLabel.setForeground(color_Label);

		valueLabel = new JLabel(value);
		valueLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));

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

	public Color getColor_Label() {
		return color_Label;
	}

	public void setColor_Label(Color color_Label) {
		this.color_Label = color_Label;
		labelLabel.setForeground(this.color_Label);
	}

	public JPanel getRowPanel() {
		return rowPanel;
	}
}
