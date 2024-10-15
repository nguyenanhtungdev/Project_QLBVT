package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import orther.ColorConstants;

import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFormattedTextField;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.SwingWorker;

import java.awt.Label;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class ChoDangNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application. 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoDangNhap frame = new ChoDangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChoDangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 92);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(ColorConstants.PRIMARY_COLOR);
		panel_1.setPreferredSize(new Dimension(0, 60)); // Chiều cao cố định 
		panel_1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Đặt chiều cao tối đa
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar(0, 100);
		progressBar.setBounds(10, 26, 568, 25);
        progressBar.setValue(0);  // Giá trị ban đầu
        progressBar.setStringPainted(true);  // Hiển thị giá trị phần trăm
		panel_1.add(progressBar);
		
		// Sử dụng SwingWorker để thực hiện cập nhật tiến trình
        SwingWorker<Void, Integer> loginWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Giả lập quá trình đăng nhập với tiến trình từ 0 -> 100
                for (int i = 0; i <= 100; i++) {
                    TimeUnit.MILLISECONDS.sleep(10);  // Tốc độ cập nhật
                    publish(i);  // Gửi giá trị hiện tại cho tiến trình
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                // Cập nhật thanh tiến trình
                int progress = chunks.get(chunks.size() - 1);
                progressBar.setValue(progress);
            }

            @Override
            protected void done() {
                // Đóng màn hình chờ sau khi hoàn thành
            	dispose();
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công!", "Đăng nhập", JOptionPane.INFORMATION_MESSAGE);
            }
        };

        loginWorker.execute();
		
		JLabel lblNewLabel = new JLabel("Đang tải...");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 0, 92, 25);
		panel_1.add(lblNewLabel);
	}
}
