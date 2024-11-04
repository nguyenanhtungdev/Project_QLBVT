package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import view.Home;
import view.Left_Menu;
import view.Page;
import view.QLHoaDon_view;
import view.QLKhuyenMai_View;
import view.QLTau_View;
import view.QLToaTau_View;
import view.TimKiem_View;
import view.VeTau_View;

public class HienThi_Controller {
	private Home view;
	private TimKiem_View timKiem_View = new TimKiem_View();
	private VeTau_View veTau_View = new VeTau_View();

	private QLHoaDon_view qlHoaDon_view = new QLHoaDon_view();
	private QLTau_View qlTau_view = new QLTau_View();
//	private QLToaTau_View qlToaTau_view = new QLToaTau_View();
	private QLKhuyenMai_View qlKhuyenMai_view = new QLKhuyenMai_View();

	private ArrayList<Page> danhSachBanVe = new ArrayList<>();
	private ArrayList<Page> danhSachQuanLy = new ArrayList<>();

	public HienThi_Controller(Home view) {
		this.view = view;

		// Khởi tạo danh sách các trang cho Bán vé
		danhSachBanVe.add(new Page("Tìm kiếm", "/Image/search.png"));
		danhSachBanVe.add(new Page("Vé tàu", "/Image/tabler-icon-ticket.png"));

		// Khởi tạo danh sách các trang cho Quản lý
		danhSachQuanLy.add(new Page("Hóa đơn", "/Image/tabler-icon-file-settings.png"));
		danhSachQuanLy.add(new Page("Khách hàng", "/Image/tabler-icon-file-settings.png"));
		danhSachQuanLy.add(new Page("Khuyến mãi", "/Image/tabler-icon-file-settings.png"));
		danhSachQuanLy.add(new Page("Tàu", "/Image/tabler-icon-file-settings.png"));
//        danhSachQuanLy.add(new Page("Toa Tàu", "/Image/tabler-icon-file-settings.png"));
		danhSachQuanLy.add(new Page("Chuyến tàu", "/Image/tabler-icon-file-settings.png"));

		// Thêm các panel vào view
		view.addPanel("Tìm kiếm", timKiem_View.getTimKiem_View());
		view.addPanel("Vé tàu", veTau_View.getVeTau_View());
		view.addPanel("Quản lý hóa đơn", qlHoaDon_view.getQLHoaDon_View());
		view.addPanel("Quản lý Tàu", qlTau_view.getQLTau_View());
//        view.addPanel("Quản lý Toa Tàu", qlToaTau_view.getQLToaTau_View());
		view.addPanel("Quản lý Khuyến Mãi", qlKhuyenMai_view.getQLKhuyenMai_View());

		// Tạo Left_Menu và truyền vào Home
		Left_Menu left_Menu_BanVe = new Left_Menu(danhSachBanVe, view);
		Left_Menu left_Menu_QuanLy = new Left_Menu(danhSachQuanLy, view);

		// Thêm Left_Menu vào view
		view.addLeft_Menu("LeftMenu_BanVe", left_Menu_BanVe.getLeft_Menu());
		view.addLeft_Menu("LeftMenu_QuanLy", left_Menu_QuanLy.getLeft_Menu());

		// Thêm listener cho menu
		view.addCustomerMenuListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object obj = e.getSource();

				if (obj == view.getLbl_BanVe()) {
					view.showPanel("Tìm kiếm");
					view.showLeft_Menu("LeftMenu_BanVe");
				} else if (obj == view.getLbl_QuanLy()) {
					view.showPanel("Quản lý hóa đơn");
					view.showLeft_Menu("LeftMenu_QuanLy");
				}
			}
		});

		// Hiển thị trang Home mặc định
		view.showPanel("Home");
		khoiTaoDsController();
	}

	public void khoiTaoDsController() {
		try {
			QuanLy_Controller qlHoaDon_Controller = new QuanLy_Controller(qlHoaDon_view);
			QuanLy_Controller qlTau_Controller = new QuanLy_Controller(qlTau_view);
			QuanLy_Controller qlKhuyenMai_Controller = new QuanLy_Controller(qlKhuyenMai_view);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
