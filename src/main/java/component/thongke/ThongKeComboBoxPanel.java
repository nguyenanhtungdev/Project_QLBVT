package component.thongke;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import other.TrainPanel;

public class ThongKeComboBoxPanel extends TrainPanel {

	private static final long serialVersionUID = -7083532538034122672L;

	private JLabel label;
	private JComboBox<Object> comboBox;

	public ThongKeComboBoxPanel(String title, Object[] items) {
		add(label = new JLabel(title));
		add(comboBox = new JComboBox<>(items));
	}

	public JLabel getLabel() {
		return label;
	}

	public JComboBox<Object> getComboBox() {
		return comboBox;
	}

}
