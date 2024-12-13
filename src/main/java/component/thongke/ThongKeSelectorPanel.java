package component.thongke;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import other.PrimaryButton;
import other.TrainPanel;

public class ThongKeSelectorPanel extends TrainPanel {

	private static final long serialVersionUID = 8471346772296133085L;

	private JLabel label;
	private JTextField textField;
	private PrimaryButton button;

	public ThongKeSelectorPanel(String title) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		add(label = new JLabel(title));
		add(Box.createHorizontalStrut(8));
		add(textField = createTextField());
		add(Box.createHorizontalStrut(8));
		add(button = new PrimaryButton("Chọn"));

		textField.setFocusable(false);
		textField.setEditable(false);
		textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
	}

	private JTextField createTextField() {
		return new JTextField(50) {
			private static final long serialVersionUID = -3557557592076443256L;

			@Override
			public Insets getInsets() {
				return new Insets(8, 12, 8, 12);
			}
		};
	}

	public void addBtnSelectorListener(ActionListener listener) {
		button.addActionListener(listener);
	}

	public JLabel getLabel() {
		return label;
	}

	public JTextField getTextField() {
		return textField;
	}

	public PrimaryButton getButton() {
		return button;
	}

	@Override
	public Insets getInsets() {
		return new Insets(4, 8, 4, 8);
	}

}
