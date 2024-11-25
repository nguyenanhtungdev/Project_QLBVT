package controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.ChiTiet_HoaDon;
import model.ChiTiet_HoaDon_DAO;
import model.ChuyenTau;
import model.ChuyenTau_DAO;
import model.GiaVe;
import model.GiaVe_DAO;
import model.HoaDon;
import model.HoaDon_DAO;
import model.KhuyenMai;
import model.KhuyenMai_DAO;
import model.ThongKeNgay;
import model.ThongKeTuan;
import model.TrangThaiVeTau;
import model.VeTau;
import model.VeTau_DAO;
import util.PrinterUtils;
import view.KetQuaThongKe_View;
import view.View;
import view.TaoMoi_View;
import view.TongQuan_View;

public class ThongKe_Controller {

	private static ThongKe_Controller instance;

	public static ThongKe_Controller getInstance() {
		if (instance == null)
			instance = new ThongKe_Controller();
		return instance;
	}

	private ArrayList<View> pageList;
	private TongQuan_View tongQuanPage;
	private TaoMoi_View taoMoiPage;
	private KetQuaThongKe_View ketQuaThongKePage;

	public ArrayList<View> getViewList() {
		return pageList;
	}

	public ThongKe_Controller() {
		pageList = new ArrayList<>();
		pageList.add(tongQuanPage = new TongQuan_View("Tổng quan", "/Image/tabler-icon-report-analytics.png"));
		pageList.add(taoMoiPage = new TaoMoi_View("Tạo mới", "/Image/tabler-icon-report-medical.png"));
//		pageList.add(ketQuaThongKePage = new KetQuaThongKePage("Kết quả", "/Image/tabler-icon-report-medical.png"));

		tongQuanPage.addInActionListener(this::inTongQuan);
	}

	public void loadThongKe() {
		tongQuanPage.loadThongKe(taoThongKeNgay(LocalDate.of(2024, 10, 20)),
				taoThongKeTuan(LocalDate.of(2024, 10, 19), 7));
	}

	private ThongKeNgay taoThongKeNgay(LocalDate date) {
		try {
			List<HoaDon> hoaDons = HoaDon_DAO.getInstance().getalltbHD().stream()
					.filter(p -> LocalDate.from(p.getNgayLapHoaDon()).isEqual(date)).toList();

			List<ChiTiet_HoaDon> chiTietHoaDons = ChiTiet_HoaDon_DAO.getInstance().getAll().stream()
					.filter(p -> hoaDons.stream().anyMatch(p1 -> p1.getMaHoaDon().equals(p.getHoaDon().getMaHoaDon())))
					.toList();

			List<VeTau> veTaus = VeTau_DAO.getInstance().getalltbVT().stream().filter(
					p -> chiTietHoaDons.stream().anyMatch(p1 -> p1.getVeTau().getMaVeTau().equals(p.getMaVeTau())))
					.toList();

			List<ChuyenTau> chuyenTaus = ChuyenTau_DAO.getInstance().getAll().stream().filter(
					p -> veTaus.stream().anyMatch(p1 -> p1.getChuyenTau().getMaChuyenTau().equals(p.getMaChuyenTau())))
					.toList();

			List<GiaVe> giaVes = GiaVe_DAO.getInstance().getAllGiaVe().stream()
					.filter(p -> chuyenTaus.stream().anyMatch(p1 -> p1.getGiaVe().getMaGiaVe().equals(p.getMaGiaVe())))
					.toList();

			List<KhuyenMai> khuyenMais = KhuyenMai_DAO.getInstance().getAll().stream().filter(p -> chiTietHoaDons
					.stream().anyMatch(p1 -> p1.getKhuyenMai().getMaKhuyenMai().equals(p.getMaKhuyenMai()))).toList();

			double doanhThu = giaVes.stream().map(m -> m.getGiaVe()).reduce(0D, (acc, curr) -> acc + curr);
			int soLuongHoaDon = (int) hoaDons.size();
			int soLuongVeBanRa = (int) veTaus.size();
			int soLuongVeDaHuy = (int) veTaus.stream().filter(t -> t.getTrangThai() == TrangThaiVeTau.DA_HUY).count();
			int soLuongKhuyenMaiDaDung = khuyenMais.size();
			return new ThongKeNgay(date, doanhThu, soLuongHoaDon, soLuongVeBanRa, soLuongVeDaHuy,
					soLuongKhuyenMaiDaDung);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ThongKeTuan taoThongKeTuan(LocalDate endDate, int numOfDays) {
		ThongKeNgay[] thongKeNgays = new ThongKeNgay[numOfDays];
		for (int i = 0; i < thongKeNgays.length; i++) {
			thongKeNgays[i] = taoThongKeNgay(endDate.minusDays(numOfDays - i));
		}

		double[] doanhThu = Stream.of(thongKeNgays).mapToDouble(m -> m.getDoanhThu()).toArray();
		int[] soLuongHoaDon = Stream.of(thongKeNgays).mapToInt(m -> m.getSoLuongHoaDon()).toArray();
		int[] soLuongVeBanRa = Stream.of(thongKeNgays).mapToInt(m -> m.getSoLuongVeBanRa()).toArray();
		int[] soLuongVeDaHuy = Stream.of(thongKeNgays).mapToInt(m -> m.getSoLuongVeDaHuy()).toArray();
		int[] soLuongKhuyenMaiDaDung = Stream.of(thongKeNgays).mapToInt(m -> m.getSoLuongKhuyenMaiDaDung()).toArray();
		return new ThongKeTuan(endDate, doanhThu, soLuongHoaDon, soLuongVeBanRa, soLuongVeDaHuy,
				soLuongKhuyenMaiDaDung);
	}

	private void inTongQuan(ActionEvent e) {
		PrinterUtils.print(tongQuanPage, "In thống kê tổng quan");
	}

}
