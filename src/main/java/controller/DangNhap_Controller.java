package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.formdev.flatlaf.FlatLightLaf;

import model.NhanVien;
import model.TaiKhoan;
import model.TaiKhoan_DAO;
import view.ChoDangNhap;
import view.DangNhap;

public class DangNhap_Controller implements ActionListener, KeyListener {
	private TaiKhoan_DAO taiKhoan_DAO;
	private DangNhap dangNhap;
	private ChoDangNhap choDangNhap;
	private HienThi_Controller controller;

	public DangNhap_Controller() {
		taiKhoan_DAO = new TaiKhoan_DAO();
		dangNhap = new DangNhap();
		choDangNhap = new ChoDangNhap();
		dangNhap.setVisible(true);

		dangNhap.getBtn_DangNhap().addActionListener(this);
		dangNhap.getTxt_passWord().addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == dangNhap.getBtn_DangNhap()) {
			performLogin();
		}
	}

	// Hàm xử lý đăng nhập
	public TaiKhoan xuLyDangNhap() {
		for (TaiKhoan tk : taiKhoan_DAO.getalltbTK()) {
			String password = new String(dangNhap.getTxt_passWord().getPassword()).trim();
			if (dangNhap.getTxt_userName().getText().trim().equals(tk.getTenDangNhap())
					&& password.equals(tk.getMatKhau())) {
				return tk;
			}
		}
		return null;
	}

	private void performLogin() {
		if (xuLyDangNhap() != null) {
			dangNhap.dispose();
			choDangNhap.setVisible(true);
			startProgress();
		} else {
			JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng!", "Đăng nhập",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void startProgress() {
		SwingWorker<Void, Integer> worker = new SwingWorker<>() {
			@Override
			protected Void doInBackground() throws Exception {
				for (int i = 0; i <= 100; i += 10) {
					TimeUnit.MILLISECONDS.sleep(200);
					publish(i);
				}

				controller = new HienThi_Controller();
				publish(100);

				return null;
			}

			@Override
			protected void process(java.util.List<Integer> chunks) {
				int progress = chunks.get(chunks.size() - 1);
				choDangNhap.getProgressBar().setValue(progress);

				if (progress < 50) {
					choDangNhap.getLblStatus().setText("Đang tải tài nguyên...");
				} else if (progress < 80) {
					choDangNhap.getLblStatus().setText("Đang khởi tạo giao diện...");
				} else {
					choDangNhap.getLblStatus().setText("Hoàn tất!");
				}
			}

			@Override
			protected void done() {
				choDangNhap.dispose();
				SwingUtilities.invokeLater(() -> {
					FlatLightLaf.setup();
					HienThi_Controller controller = new HienThi_Controller();
					controller.setTaiKhoan(xuLyDangNhap());
					controller.xuLyHienThi();
					controller.showView();
				});

			}
		};

		worker.execute();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			performLogin();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
