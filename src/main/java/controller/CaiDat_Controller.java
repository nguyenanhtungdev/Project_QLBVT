package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.NhanVien;
import model.NhanVien_DAO;
import view.DangXuat_View;
import view.ThongTinCaNhan_View;
import view.View;

public class CaiDat_Controller implements ActionListener, MouseListener, FocusListener, KeyListener {

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
		themThongTinInput(HienThi_Controller.getInstance().getTaiKhoan().getNhanVien());
	}

	private void themSuKienTTCN() {
		thongTinCaNhan_View.addSuKienCD(this, this, this);
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
		thongTinCaNhan_View.getTxt_NgaySinh().setText(formatter.format(LocalDate.parse(nhanVien.getNgaySinh().toString())));
		thongTinCaNhan_View.getTxt_SDT().setText(nhanVien.getSoDienThoai());
		thongTinCaNhan_View.getComboBox_GioiTinh().setSelectedItem(nhanVien.isGioiTinh() ? "Nam" : "Nữ");
		thongTinCaNhan_View.getTxt_ChucVu().setText(nhanVien.getTenChucVu());
	}
	
	public void xoaTrangInput() {
		thongTinCaNhan_View.getTxt_HoTen().setText("");;
		thongTinCaNhan_View.getTxt_CCCD().setText("");;
		thongTinCaNhan_View.getTxt_Email().setText("");
		thongTinCaNhan_View.getTxt_NgaySinh().setText("");
		thongTinCaNhan_View.getTxt_SDT().setText("");
		thongTinCaNhan_View.getComboBox_GioiTinh().setSelectedIndex(0);
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
			xoaTrangInput();
			themThongTinInput(HienThi_Controller.getInstance().getTaiKhoan().getNhanVien());
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(thongTinCaNhan_View.getTxt_NgaySinh())) {
			JTextField txtNgaySinh = thongTinCaNhan_View.getTxt_NgaySinh();
			String text = txtNgaySinh.getText().replaceAll("[^0-9/]", "");
			int length = text.length();

			if (length == 2 || length == 5) {
				if (length < txtNgaySinh.getText().length()) {
					txtNgaySinh.setText(text);
				} else {
					txtNgaySinh.setText(text + "/");
				}
			} else if (length > 10) {
				txtNgaySinh.setText(text.substring(0, 10));
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(thongTinCaNhan_View.getTxt_NgaySinh())) {
			JTextField txtNgaySinh = thongTinCaNhan_View.getTxt_NgaySinh();
			String text = txtNgaySinh.getText().trim();
			if (!text.matches("\\d{2}/\\d{2}/\\d{4}")) {
				JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ! Vui lòng nhập theo định dạng dd/MM/yyyy.",
						"Lỗi", JOptionPane.ERROR_MESSAGE);
				txtNgaySinh.requestFocus();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
