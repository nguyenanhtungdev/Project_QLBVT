package other;

import javax.swing.*;

import java.awt.*;
import java.time.LocalTime;

public class TrainTimePicker extends JPanel {

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

	public TrainTimePicker() {
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		setOpaque(false);

		add(cbHour = new JComboBox<>());
		add(Box.createHorizontalStrut(4));
		add(new JLabel(":"));
		add(Box.createHorizontalStrut(4));
		add(cbMinute = new JComboBox<>());
		add(Box.createHorizontalStrut(4));
		add(new JLabel(":"));
		add(Box.createHorizontalStrut(4));
		add(cnSecond = new JComboBox<>());

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

}
