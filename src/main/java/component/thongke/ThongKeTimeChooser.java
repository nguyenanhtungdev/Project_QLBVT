package component.thongke;

import javax.swing.*;

import other.TrainPanel;

import java.awt.*;
import java.time.LocalTime;

public class ThongKeTimeChooser extends TrainPanel {

	private static final long serialVersionUID = -2965832331883716717L;

	private JComboBox<Object> cbHour;
	private JComboBox<Object> cbMinute;
	private JComboBox<Object> cnSecond;

	public int getHour() {
		return cbHour.getSelectedIndex() - 1;
	}

	public int getMinute() {
		return cbMinute.getSelectedIndex() - 1;
	}

	public int getSecond() {
		return cnSecond.getSelectedIndex() - 1;
	}

	public void setTime(LocalTime time) {
		if (time == null) {
			cbHour.setSelectedIndex(0);
			cbMinute.setSelectedIndex(0);
			cnSecond.setSelectedIndex(0);
			return;
		}

		cbHour.setSelectedItem(time.getHour() + 1);
		cbMinute.setSelectedItem(time.getMinute() + 1);
		cnSecond.setSelectedItem(time.getSecond() + 1);
	}

	public ThongKeTimeChooser() {
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

		add(cbHour = createComboBox());
		add(Box.createHorizontalStrut(4));
		add(new JLabel(":"));
		add(Box.createHorizontalStrut(4));
		add(cbMinute = createComboBox());
		add(Box.createHorizontalStrut(4));
		add(new JLabel(":"));
		add(Box.createHorizontalStrut(4));
		add(cnSecond = createComboBox());

		cbHour.addItem("--");
		cbMinute.addItem("--");
		cnSecond.addItem("--");

		for (int i = 0; i < 24; i++)
			cbHour.addItem(i);
		for (int i = 0; i < 60; i++)
			cbMinute.addItem(i);
		for (int i = 0; i < 60; i++)
			cnSecond.addItem(i);
	}

	private JComboBox<Object> createComboBox() {
		JComboBox<Object> comboBox = new JComboBox<>() {

			private static final long serialVersionUID = -7507767422656111376L;

			@Override
			public Insets getInsets() {
				return new Insets(8, 12, 8, 12);
			}
		};
		return comboBox;
	}

	@Override
	public Insets getInsets() {
		return new Insets(4, 8, 4, 8);
	}

}
