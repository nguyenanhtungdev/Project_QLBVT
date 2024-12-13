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

	private HomeView homeView = new HomeView();
	private TaiKhoan taiKhoan = new TaiKhoan();

	public HienThi_Controller() {

	}

	public void xuLyHienThi() {
		ArrayList<View> dsViewBanVe = BanVeTau_Controller.getInstance().getViewList();
		ArrayList<View> dsViewQuanLy = QuanLy_Controller.getInstance().getViewList();
		ArrayList<View> dsViewThongKe = ThongKe_Controller.getInstance().getViewList();
		ArrayList<View> dsViewCaiDat = CaiDat_Controller.getInstance().getViewList();
		
		QuanLy_Controller.getInstance().addView(taiKhoan.getNhanVien().getTenChucVu().trim());

		for (View view : dsViewBanVe) {
			homeView.addView(view.getName(), (JPanel) view.getContentPane());
		}
		for (View view : dsViewQuanLy) {
			if (taiKhoan.getNhanVien().getTenChucVu().trim().equals("NVBV")) {
				if (view.getName().equals("Hóa đơn") || view.getName().equals("Khách hàng")) {
					homeView.addView(view.getName(), (JPanel) view.getContentPane());
				}
			} else {
				homeView.addView(view.getName(), (JPanel) view.getContentPane());
			}
//			view.addView(page.getName(), (JPanel) page.getContentPane());
		}
		for (View view : dsViewThongKe) {
			homeView.addView(view.getName(), (JPanel) view.getContentPane());
		}

		for (View page : dsViewCaiDat) {
			homeView.addView(page.getName(), (JPanel) page.getContentPane());
		}

		// Tạo Left_Menu và truyền vào Home
		Left_Menu left_Menu_BanVe = new Left_Menu(dsViewBanVe, homeView);
		Left_Menu left_Menu_QuanLy = new Left_Menu(dsViewQuanLy, homeView);
		Left_Menu left_Menu_ThongKe = new Left_Menu(dsViewThongKe, homeView);
		Left_Menu left_Menu_CaiDat = new Left_Menu(dsViewCaiDat, homeView);

		// Thêm Left_Menu vào view
		homeView.addLeft_Menu("BanVe", left_Menu_BanVe.getLeft_Menu());
		homeView.addLeft_Menu("QuanLy", left_Menu_QuanLy.getLeft_Menu());
		homeView.addLeft_Menu("ThongKe", left_Menu_ThongKe.getLeft_Menu());
		homeView.addLeft_Menu("CaiDat", left_Menu_CaiDat.getLeft_Menu());

		// Thêm listener cho menu
		homeView.addCustomerMenuListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Object obj = e.getSource();

				if (obj == homeView.getLbl_BanVe()) {
					homeView.showView(dsViewBanVe.get(0).getName());
					homeView.showLeft_Menu("BanVe");
				} else if (obj == homeView.getLbl_QuanLy()) {
					homeView.showView(dsViewQuanLy.get(0).getName());
					homeView.showLeft_Menu("QuanLy");
				} else if (obj == homeView.getLbl_ThongKe()) {
					homeView.showView(dsViewThongKe.get(0).getName());
					homeView.showLeft_Menu("ThongKe");
					ThongKe_Controller.getInstance().refreshData();
				} else if (obj == homeView.getLbl_CaiDat()) {
					homeView.showView(dsViewCaiDat.get(0).getName());
					homeView.showLeft_Menu("CaiDat");
				}
			}
		});

		if (!taiKhoan.getNhanVien().getTenChucVu().trim().equals("NVBV")) {
			homeView.getLbl_BanVe().setVisible(false);
			homeView.showView(dsViewQuanLy.get(0).getName());
			homeView.showLeft_Menu("QuanLy");
		} else {
			homeView.showView("Home");
		}
		
		homeView.setNhanVien(taiKhoan.getNhanVien());
	}

	public void showView() {
		homeView.setVisible(true);
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
}
