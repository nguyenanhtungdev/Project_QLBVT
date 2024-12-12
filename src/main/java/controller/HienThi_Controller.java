package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.NhanVien;
import model.TaiKhoan;
import view.HomeView;
import view.Left_Menu;
import view.View;

public class HienThi_Controller {

	private static HienThi_Controller instance;

	public static HienThi_Controller getInstance() {
		return instance == null ? instance = new HienThi_Controller() : instance;
	}

	private HomeView view = new HomeView();
	private TaiKhoan taiKhoan = new TaiKhoan();

	public HienThi_Controller() {

	}

	public void xuLyHienThi() {
		ArrayList<View> dsViewBanVe = BanVeTau_Controller.getInstance().getViewList();
		ArrayList<View> dsViewQuanLy = QuanLy_Controller.getInstance().getViewList();
		ArrayList<View> dsViewThongKe = ThongKe_Controller.getInstance().getViewList();

		QuanLy_Controller.getInstance().addView(taiKhoan.getNhanVien().getTenChucVu().trim());

		for (View page : dsViewBanVe) {
			view.addView(page.getName(), (JPanel) page.getContentPane());
		}
		for (View page : dsViewQuanLy) {
			if (taiKhoan.getNhanVien().getTenChucVu().trim().equals("NVBV")) {
				if (page.getName().equals("Hóa đơn") || page.getName().equals("Khách hàng")) {
					view.addView(page.getName(), (JPanel) page.getContentPane());
				}
			} else {
				view.addView(page.getName(), (JPanel) page.getContentPane());
			}
		}
		for (View page : dsViewThongKe)
			view.addView(page.getName(), (JPanel) page.getContentPane());

		// Tạo Left_Menu và truyền vào Home
		Left_Menu left_Menu_BanVe = new Left_Menu(dsViewBanVe, view);
		Left_Menu left_Menu_QuanLy = new Left_Menu(dsViewQuanLy, view);
		Left_Menu left_Menu_ThongKe = new Left_Menu(dsViewThongKe, view);

		// Thêm Left_Menu vào view
		view.addLeft_Menu("BanVe", left_Menu_BanVe.getLeft_Menu());
		view.addLeft_Menu("QuanLy", left_Menu_QuanLy.getLeft_Menu());
		view.addLeft_Menu("ThongKe", left_Menu_ThongKe.getLeft_Menu());

		// Thêm listener cho menu
		view.addCustomerMenuListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Object obj = e.getSource();

				if (obj == view.getLbl_BanVe()) {
					view.showView(dsViewBanVe.get(0).getName());
					view.showLeft_Menu("BanVe");
				} else if (obj == view.getLbl_QuanLy()) {
					view.showView(dsViewQuanLy.get(0).getName());
					view.showLeft_Menu("QuanLy");
				} else if (obj == view.getLbl_ThongKe()) {
					view.showView(dsViewThongKe.get(0).getName());
					view.showLeft_Menu("ThongKe");
					ThongKe_Controller.getInstance().refreshData();
				}
			}
		});

		if (!taiKhoan.getNhanVien().getTenChucVu().trim().equals("NVBV")) {
			view.getLbl_BanVe().setVisible(false);
			view.showView(dsViewQuanLy.get(0).getName());
			view.showLeft_Menu("QuanLy");
		} else {
			view.showView("Home");
		}
	}

	public void showView() {
		view.setVisible(true);
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
}
