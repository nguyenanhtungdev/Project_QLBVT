package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class ThongTin_View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public ThongTin_View() {
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1250, 800);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
	}

}
