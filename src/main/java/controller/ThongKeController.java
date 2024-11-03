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
import other.PrinterUltilities;
import view.KetQuaThongKePage;
import view.Page;
import view.TaoMoiPage;
import view.ThongKeView;
import view.TongQuanPage;

public class ThongKeController {

	private static ThongKeController instance;

	public static ThongKeController getInstance() {
		if (instance == null)
			instance = new ThongKeController();
		return instance;
	}

	private ThongKeView view;
	private ArrayList<Page> pageList;
	private TongQuanPage tongQuanPage;
	private TaoMoiPage taoMoiPage;
	private KetQuaThongKePage ketQuaThongKePage;

	public ThongKeView getView() {
		return view;
	}

	public ArrayList<Page> getPages() {
		return pageList;
	}

	public ThongKeController() {
		tongQuanPage = new TongQuanPage("Tổng quan", "/Image/tabler-icon-report-analytics.png");
		taoMoiPage = new TaoMoiPage("Tạo mới", "/Image/tabler-icon-report-medical.png");
//		ketQuaThongKePage = new KetQuaThongKePage("Kết quả", "/Image/tabler-icon-report-medical.png");

		tongQuanPage.addInActionListener(this::inTongQuan);

		pageList = new ArrayList<>();
		pageList.add(tongQuanPage);
		pageList.add(taoMoiPage);
//		pageList.add(ketQuaThongKePage);

		view = new ThongKeView("Thống kê", "/Image/tabler-icon-report-analytics.png");
		view.addPages(pageList);
	}

	public void setVisiblePage(String name) {
		view.setVisiblePage(name);
		for (Page page : pageList) {
			if (name.equals(page.getName())) {
				if (page instanceof TongQuanPage tongQuanPage) {
					tongQuanPage.loadThongKe(taoThongKeNgay(LocalDate.of(2024, 10, 20)),
							taoThongKeTuan(LocalDate.of(2024, 10, 19), 7));
				}
			}
		}
	}

	public void setVisiblePage(int index) {
		setVisiblePage(pageList.get(index).getName());
	}

	private ThongKeNgay taoThongKeNgay(LocalDate date) {
		try {
			HoaDon_DAO hoaDonDAO = new HoaDon_DAO();
			List<HoaDon> hoaDons = hoaDonDAO.getalltbHD().stream()
					.filter(p -> LocalDate.from(p.getNgayLapHoaDon()).isEqual(date)).toList();

			List<ChiTiet_HoaDon> chiTietHoaDons = ChiTiet_HoaDon_DAO.getInstance().findAll().stream()
					.filter(p -> hoaDons.stream().anyMatch(p1 -> p1.getMaHoaDon().equals(p.getHoaDon().getMaHoaDon())))
					.toList();

			VeTau_DAO veTauDAO = new VeTau_DAO();
			List<VeTau> veTaus = veTauDAO.getalltbVT().stream().filter(
					p -> chiTietHoaDons.stream().anyMatch(p1 -> p1.getVeTau().getMaVeTau().equals(p.getMaVeTau())))
					.toList();

			ChuyenTau_DAO chuyenTauDao = new ChuyenTau_DAO();
			List<ChuyenTau> chuyenTaus = chuyenTauDao.findAll().stream().filter(
					p -> veTaus.stream().anyMatch(p1 -> p1.getChuyenTau().getMaChuyenTau().equals(p.getMaChuyenTau())))
					.toList();

			GiaVe_DAO giaVeDao = new GiaVe_DAO();
			List<GiaVe> giaVes = giaVeDao.getAllGiaVe().stream()
					.filter(p -> chuyenTaus.stream().anyMatch(p1 -> p1.getGiaVe().getMaGiaVe().equals(p.getMaGiaVe())))
					.toList();

			List<KhuyenMai> khuyenMais = KhuyenMai_DAO.getInstance().findAll().stream().filter(p -> chiTietHoaDons
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
		PrinterUltilities.getInstance().print(tongQuanPage, null);
	}

}
