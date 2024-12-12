package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.NhanVien;
import model.NhanVien_DAO;
import view.DangXuat_View;
import view.ThongTinCaNhan_View;
import view.View;

public class CaiDat_Controller implements ActionListener {

	private static CaiDat_Controller instance;
	private ArrayList<View> pageList = new ArrayList<View>();
	private ThongTinCaNhan_View thongTinCaNhan_View;
	private DangXuat_View dangXuat_View;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public ArrayList<View> getViewList() {
		return pageList;
	}

	public void setPageList(ArrayList<View> pageList) {
		this.pageList = pageList;
	}

	public static CaiDat_Controller getInstance() {
		if (instance == null)
			try {
				instance = new CaiDat_Controller();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return instance;
	}

	public void addView() {
		pageList.add(thongTinCaNhan_View);
		pageList.add(dangXuat_View);
	}

	public CaiDat_Controller() throws SQLException {
		this.thongTinCaNhan_View = new ThongTinCaNhan_View("Thông tin", "/Image/iconHoaDon.png");
		this.dangXuat_View = new DangXuat_View("Đăng xuất", "/Image/exit.png");
		addView();
		themSuKienTTCN();
	}

	private void themSuKienTTCN() {
		thongTinCaNhan_View.addSuKienCD(this);
	}

	private boolean capNhatThongTinNV() {
		String maNV = thongTinCaNhan_View.getTxt_MaNhanVien().getText().trim();
		String hoTen = thongTinCaNhan_View.getTxt_HoTen().getText().trim();
		String sdt = thongTinCaNhan_View.getTxt_SDT().getText().trim();
		String email = thongTinCaNhan_View.getTxt_Email().getText().trim();
		String cccd = thongTinCaNhan_View.getTxt_CCCD().getText().trim(); // Sửa lại cho đúng
		boolean gioiTinh = thongTinCaNhan_View.getComboBox_GioiTinh().getSelectedIndex() == 0;
		String chucVu = thongTinCaNhan_View.getTxt_ChucVu().getText().trim();

		// Định dạng ngày tháng
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ngaySinh = null;
		try {
			ngaySinh = LocalDate.parse(thongTinCaNhan_View.getTxt_NgaySinh().getText().trim(), formatter);
		} catch (DateTimeParseException e) {
			System.out.println("Định dạng ngày sinh không hợp lệ: " + e.getMessage());
			return false;
		}

		if (hoTen == null || hoTen.isEmpty()) {
			System.out.println("Họ tên không được để trống.");
			return false;
		}
		if (sdt == null || sdt.isEmpty()) {
			System.out.println("Số điện thoại không được để trống.");
			return false;
		}
		if (email == null || email.isEmpty()) {
			System.out.println("Email không được để trống.");
			return false;
		}
		if (cccd == null || cccd.isEmpty()) {
			System.out.println("CCCD không được để trống.");
			return false;
		}
		if (ngaySinh == null) {
			System.out.println("Ngày sinh không được để trống.");
			return false;
		}

		Object nhanVien[] = { maNV, hoTen, sdt, email, cccd, gioiTinh, chucVu, ngaySinh };

		if (NhanVien_DAO.getInstance().insertNhanVien(nhanVien)) {
			return true;
		}
		return false;
	}

	private void moKhoaInput() {
		thongTinCaNhan_View.getTxt_HoTen().setEnabled(true);
		thongTinCaNhan_View.getTxt_CCCD().setEnabled(true);
		thongTinCaNhan_View.getTxt_Email().setEnabled(true);
		thongTinCaNhan_View.getTxt_NgaySinh().setEnabled(true);
		thongTinCaNhan_View.getTxt_SDT().setEnabled(true);
		thongTinCaNhan_View.getComboBox_GioiTinh().setEnabled(true);
	}

	private void khoaInput() {
		thongTinCaNhan_View.getTxt_HoTen().setEnabled(false);
		thongTinCaNhan_View.getTxt_CCCD().setEnabled(false);
		thongTinCaNhan_View.getTxt_Email().setEnabled(false);
		thongTinCaNhan_View.getTxt_NgaySinh().setEnabled(false);
		thongTinCaNhan_View.getTxt_SDT().setEnabled(false);
		thongTinCaNhan_View.getComboBox_GioiTinh().setEnabled(false);
	}

	public void themThongTinInput(NhanVien nhanVien) {
		thongTinCaNhan_View.getTxt_MaNhanVien().setText(nhanVien.getMaNV());
		thongTinCaNhan_View.getTxt_HoTen().setText(nhanVien.getHoTenNV());
		thongTinCaNhan_View.getTxt_CCCD().setText(nhanVien.getCCCD());
		thongTinCaNhan_View.getTxt_Email().setText(nhanVien.getEmail());
//		thongTinCaNhan_View.getTxt_NgaySinh().setText(null);
		thongTinCaNhan_View.getTxt_SDT().setText(nhanVien.getSoDienThoai());
//		thongTinCaNhan_View.getComboBox_GioiTinh()
		thongTinCaNhan_View.getTxt_ChucVu().setText(nhanVien.getTenChucVu());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(thongTinCaNhan_View.getBtnCapNhat())) {
			thongTinCaNhan_View.getBtnHoanThanh().setVisible(true);
			thongTinCaNhan_View.getBtnHuyBo().setVisible(true);
			thongTinCaNhan_View.getBtnCapNhat().setVisible(false);
			moKhoaInput();
		} else if (obj.equals(thongTinCaNhan_View.getBtnHoanThanh())) {
			int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn chỉnh sửa?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				if (capNhatThongTinNV()) {
					JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Cập nhật không thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (obj.equals(thongTinCaNhan_View.getBtnHuyBo())) {
			thongTinCaNhan_View.getBtnHoanThanh().setVisible(false);
			thongTinCaNhan_View.getBtnHuyBo().setVisible(false);
			thongTinCaNhan_View.getBtnCapNhat().setVisible(true);
			khoaInput();
		}

	}

}
