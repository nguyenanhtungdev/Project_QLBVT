package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.concurrent.TimeUnit;
import controller.HienThi_Controller;
import other.ColorConstants;

public class ChoDangNhap extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JProgressBar progressBar;
    private JLabel lblStatus;
    private HienThi_Controller controller;

    public ChoDangNhap() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 600, 80);
        setUndecorated(true); // Loại bỏ thanh tiêu đề và nút điều khiển
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setBackground(ColorConstants.PRIMARY_COLOR);
        panel.setPreferredSize(new Dimension(0, 80));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        contentPane.add(panel);
        panel.setLayout(null);

        // Thanh tiến trình
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(10, 45, 580, 25);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        panel.add(progressBar);

        // Label trạng thái
        lblStatus = new JLabel("Đang khởi tạo...");
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblStatus.setBounds(10, 10, 300, 25);
        panel.add(lblStatus);
    }

    public void startProgress() {
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Mô phỏng quá trình tải giao diện chính
                for (int i = 0; i <= 100; i += 10) {
                    TimeUnit.MILLISECONDS.sleep(200); // Giả lập thời gian khởi tạo
                    publish(i); // Cập nhật tiến trình
                }

                controller = new HienThi_Controller();
                publish(100); // Cập nhật tiến trình hoàn tất

                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                int progress = chunks.get(chunks.size() - 1);
                progressBar.setValue(progress);

                if (progress < 50) {
                    lblStatus.setText("Đang tải tài nguyên...");
                } else if (progress < 80) {
                    lblStatus.setText("Đang khởi tạo giao diện...");
                } else {
                    lblStatus.setText("Hoàn tất!");
                }
            }

            @Override
            protected void done() {
                dispose(); // Đóng giao diện chờ
        		SwingUtilities.invokeLater(() -> {
        			FlatLightLaf.setup();
        			HienThi_Controller controller = new HienThi_Controller();
        			controller.showView();
        		});

            }
        };

        worker.execute();
    }

    public static void main(String[] args) {
        // Hiển thị giao diện chờ
        ChoDangNhap choDangNhap = new ChoDangNhap();
        choDangNhap.setVisible(true);

        // Bắt đầu tải giao diện chính
        choDangNhap.startProgress();
    }
}
