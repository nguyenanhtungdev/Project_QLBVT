package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import other.ColorConstants;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import controller.BanVe_Controller;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.concurrent.TimeUnit;

public class ChoDangNhap extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JProgressBar progressBar;

    public ChoDangNhap() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 600, 62);
        setUndecorated(true);  // Loại bỏ thanh tiêu đề và nút điều khiển
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(ColorConstants.PRIMARY_COLOR);
        panel_1.setPreferredSize(new Dimension(0, 60)); 
        panel_1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        // Thanh tiến trình
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(10, 26, 568, 25);
        progressBar.setValue(0); 
        progressBar.setStringPainted(true); 
        panel_1.add(progressBar);

        // Label hiển thị
        JLabel lblNewLabel = new JLabel("Đang tải...");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(10, 0, 92, 25);
        panel_1.add(lblNewLabel);
    }
    
    public void startProgress() {
        SwingWorker<Void, Integer> loginWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    TimeUnit.MILLISECONDS.sleep(30);  // Tốc độ cập nhật
                    publish(i);  // Gửi giá trị hiện tại cho tiến trình
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                int progress = chunks.get(chunks.size() - 1);
                progressBar.setValue(progress); 
            }

            @Override
            protected void done() {
                dispose();
                
                Home view = new Home();
                BanVe_Controller controller = new BanVe_Controller(view); 
                view.setVisible(true);
            }
        };

        loginWorker.execute(); 
    }
}
