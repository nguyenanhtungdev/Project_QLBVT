package controller;

import view.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import model.KhachHang;
import model.KhachHang_DAO;

public class BanVe_Controller {
	private Home view;
	private TimKiem_View timKiem_View = new TimKiem_View();
	private VeTau_View veTau_View = new VeTau_View();
	private QLHoaDon_view qlHoaDon_view = new QLHoaDon_view();

	private ArrayList<Page> danhSachBanVe = new ArrayList<>();
	private ArrayList<Page> danhSachQuanLy = new ArrayList<>();

	public BanVe_Controller(Home view) {
		this.view = view;

		// Khởi tạo danh sách các trang cho Bán vé
		danhSachBanVe.add(new Page("Tìm kiếm", "/Image/search.png"));
		danhSachBanVe.add(new Page("Vé tàu", "/Image/tabler-icon-ticket.png"));

		// Khởi tạo danh sách các trang cho Quản lý
		danhSachQuanLy.add(new Page("Hóa đơn", "/Image/search.png"));
		danhSachQuanLy.add(new Page("Khách hàng", "/Image/search.png"));
		danhSachQuanLy.add(new Page("Khuyến mãi", "/Image/search.png"));
		danhSachQuanLy.add(new Page("Tàu", "/Image/search.png"));
		danhSachQuanLy.add(new Page("Chuyến tàu", "/Image/search.png"));

		// Thêm các panel vào view
		view.addPanel("Tìm kiếm", timKiem_View.getTimKiem_View());
		view.addPanel("Vé tàu", veTau_View.getVeTau_View());
		view.addPanel("Quản lý hóa đơn", qlHoaDon_view.getQLHoaDon_View());

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
	}

	private KhachHang_DAO khachHang_DAO = new KhachHang_DAO();

	public ArrayList<KhachHang> getDataKhachHang() {
		return khachHang_DAO.getalltbKH();
	}

	public double getGiamGia(String maKH) {
		for (KhachHang kh : khachHang_DAO.getalltbKH()) {
			if (kh.getMaKhachHang().equals(maKH)) {
				return kh.getLoaiKH().getDiscount();
			}
		}
		return 0;
	}
}