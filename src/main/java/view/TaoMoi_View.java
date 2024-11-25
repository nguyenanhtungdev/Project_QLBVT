package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import other.PrimaryButton;
import other.SecondaryButton;
import other.TrainTitle;

public class TaoMoi_View extends View {

	private static final long serialVersionUID = -3683814467745193485L;

	public TaoMoi_View(String name, String imagePath) {
		super(name, imagePath);

		setLayout(new FlowLayout(FlowLayout.LEADING));

		renderUI();
	}

	private void renderUI() {
		TrainTitle tieuDeMoi = new TrainTitle("Tạo thống kê mới");

		JLabel lLoai = new JLabel("Loại thống kê");
		JLabel lLoaiKH = new JLabel("Loại khách hàng");
		JLabel lLoaiVe = new JLabel("Loại vé");
		JLabel lTrangThaiVe = new JLabel("Trạng thái vé");
		JLabel lNhanVien = new JLabel("Nhân viên");
		JLabel lTau = new JLabel("Tàu");
		JLabel lChuyenTau = new JLabel("Chuyến tàu");
		JLabel lCaLam = new JLabel("Ca làm");
		JLabel lKhuyenMai = new JLabel("Khuyến mãi");
		JLabel lTuNgay = new JLabel("Từ ngày");
		JLabel lDenNgay = new JLabel("Đến ngày");

		Font font = new Font("Arial", Font.PLAIN, 16);
		lLoai.setFont(font);
		lLoaiKH.setFont(font);
		lLoaiVe.setFont(font);
		lTrangThaiVe.setFont(font);
		lNhanVien.setFont(font);
		lTau.setFont(font);
		lChuyenTau.setFont(font);
		lCaLam.setFont(font);
		lKhuyenMai.setFont(font);
		lTuNgay.setFont(font);
		lDenNgay.setFont(font);

		JComboBox<String> cbLoai = new JComboBox<>(
				new String[] { "Doanh thu", "Số lượng vé", "Số lượng hóa đơn", "Số lượng khuyến mãi", "Giá vé" });
		JComboBox<String> cbLoaiKH = new JComboBox<>(new String[] { "-- Tất cả --", "Khách thường", "Trẻ em",
				"Sinh viên", "Học sinh", "Người già", "Người khuyết tật" });
		JComboBox<String> cbLoaiVe = new JComboBox<>(new String[] { "-- Tất cả --", "Thường", "VIP" });
		JComboBox<String> cbTrangThaiVe = new JComboBox<>(new String[] { "-- Tất cả --", "Đã bán", "Đã hủy" });
		JComboBox<String> cbNhanVien = new JComboBox<>(new String[] { "-- Tất cả --", "Nguyễn Anh Tùng - NV12345" });
		JComboBox<String> cbTau = new JComboBox<>(new String[] { " --Tất cả --" });
		JComboBox<String> cbChuyenTau = new JComboBox<>(new String[] { "-- Tất cả --" });
		JComboBox<String> cbCaLam = new JComboBox<>(new String[] { "-- Tất cả --" });
		JComboBox<String> cbKhuyenMai = new JComboBox<>(new String[] { "-- Không có --" });
		JTextField tfTuNgay = new JTextField("-- Tất cả --");
		JTextField tfDenNgay = new JTextField("-- Tất cả --");

		cbLoai.setFont(font);
		cbLoaiKH.setFont(font);
		cbLoaiVe.setFont(font);
		cbTrangThaiVe.setFont(font);
		cbNhanVien.setFont(font);
		cbTau.setFont(font);
		cbChuyenTau.setFont(font);
		cbCaLam.setFont(font);
		cbKhuyenMai.setFont(font);
		tfTuNgay.setFont(font);
		tfDenNgay.setFont(font);

		JButton btnXoaRong = new SecondaryButton("Xóa rỗng");
		JButton btnXem = new PrimaryButton("Xem thống kê");

//		TieuDe tieuDeTuDong = new TieuDe("Tạo thống kê tự động");
//		tieuDeTuDong.setAlignmentX(LEFT_ALIGNMENT);

		JPanel pCenter = new JPanel(new GridBagLayout());
		pCenter.setOpaque(false);
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(0, 8, 8, 0);
		c.gridwidth = 5;
		pCenter.add(tieuDeMoi, c);
		c.gridwidth = 1;
		c.gridy = 1;
		pCenter.add(lLoai, c);
		c.gridy = 2;
		pCenter.add(lLoaiKH, c);
		c.gridy = 3;
		pCenter.add(lLoaiVe, c);
		c.gridx = 2;
		pCenter.add(lTrangThaiVe, c);
		c.gridx = 0;
		c.gridy = 4;
		pCenter.add(lNhanVien, c);
		c.gridy = 5;
		pCenter.add(lTau, c);
		c.gridy = 6;
		pCenter.add(lChuyenTau, c);
		c.gridy = 7;
		pCenter.add(lCaLam, c);
		c.gridy = 8;
		pCenter.add(lKhuyenMai, c);
		c.gridy = 9;
		pCenter.add(lTuNgay, c);
		c.gridx = 2;
		pCenter.add(lDenNgay, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		pCenter.add(cbLoai, c);
		c.gridy = 2;
		pCenter.add(cbLoaiKH, c);
		c.gridy = 3;
		pCenter.add(cbLoaiVe, c);
		c.gridx = 3;
		pCenter.add(cbTrangThaiVe, c);
		c.gridx = 1;
		c.gridy = 4;
		pCenter.add(cbNhanVien, c);
		c.gridy = 5;
		pCenter.add(cbTau, c);
		c.gridy = 6;
		pCenter.add(cbChuyenTau, c);
		c.gridy = 7;
		pCenter.add(cbCaLam, c);
		c.gridy = 8;
		pCenter.add(cbKhuyenMai, c);
		c.gridy = 9;
		pCenter.add(tfTuNgay, c);
		c.gridx = 3;
		pCenter.add(tfDenNgay, c);
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 2;
		pCenter.add(btnXoaRong, c);
		c.anchor = GridBagConstraints.NORTHEAST;
		c.gridx = 2;
		c.gridy = 10;
		c.gridwidth = 2;
		pCenter.add(btnXem, c);

		add(pCenter);

	}

	@Override
	public Insets getInsets() {
		return new Insets(16, 16, 16, 16);
	}

}
