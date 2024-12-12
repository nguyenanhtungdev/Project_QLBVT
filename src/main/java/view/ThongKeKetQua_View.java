package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.data.category.DefaultCategoryDataset;

import controller.ThongKe_Controller;
import model.NhanVien;
import model.Tau.TrangThaiTau;
import model.ThongKeFilters;
import model.KhachHang.LoaiKhachHang;
import other.PrimaryButton;
import other.TrainChart;
import other.TrainPanel;
import other.TrainScrollPane;
import other.TrainTitle;
import util.PrinterUtils;

public class ThongKeKetQua_View extends JDialog implements Printable {

	private static final long serialVersionUID = 6103116999501325988L;

	private TrainPanel pMain;
	private Box pNorth;
	private Box pSouth;
	private TrainPanel pCharts;
	private PrimaryButton btnIn;

	public ThongKeKetQua_View(Frame owner, String title, ThongKeFilters tieuChi, NhanVien nhanVien) {
		super(owner, title, ModalityType.MODELESS);
		setContentPane(new TrainPanel(new BorderLayout(16, 16), new Insets(16, 16, 16, 16)));

		add(new TrainScrollPane(pMain = new TrainPanel(new BorderLayout(), new Insets(16, 16, 16, 16))));

		String createdBy = nhanVien.getHoTenNV();
		String createdTime = LocalDateTime.now().format(ThongKe_Controller.FMT_DATETIME);
		String fromTime = tieuChi.getTuLuc() == null ? "Không giới hạn"
				: tieuChi.getTuLuc().format(ThongKe_Controller.FMT_DATETIME);
		String toTime = tieuChi.getDenLuc() == null ? "Không giới hạn"
				: tieuChi.getDenLuc().format(ThongKe_Controller.FMT_DATETIME);
		String khachHangs = tieuChi.getKhachHang() == null ? "Tất cả khách hàng"
				: Arrays.stream(tieuChi.getKhachHang()).map(kh -> kh.getHoTen()).collect(Collectors.joining(", "));
		String loaiKhachHangs = tieuChi.getKhachHangCategory() == null ? "Tất cả khách hàng"
				: Arrays.stream(tieuChi.getKhachHangCategory()).map(LoaiKhachHang::toString)
						.collect(Collectors.joining(", "));
		String nhanViens = tieuChi.getNhanVien() == null ? "Tất cả"
				: Arrays.stream(tieuChi.getNhanVien()).map(nv -> nv.getMaNV() + " - " + nv.getHoTenNV())
						.collect(Collectors.joining(", "));
		String caLams = tieuChi.getCaLam() == null ? "Tất cả"
				: Arrays.stream(tieuChi.getCaLam()).map(ca -> ca.getMaCa() + " - " + ca.getTenCa())
						.collect(Collectors.joining(", "));
		String khuyenMais = tieuChi.getKhuyenMai() == null ? "Tất cả"
				: Arrays.stream(tieuChi.getKhuyenMai()).map(km -> km.getMaKhuyenMai() + " - " + km.getTenKhuyenMai())
						.collect(Collectors.joining(", "));
		String chuyenTaus = tieuChi.getChuyenTau() == null ? "Tất cả"
				: Arrays.stream(tieuChi.getChuyenTau()).map(ct -> ct.getMaChuyenTau())
						.collect(Collectors.joining(", "));
		String taus = tieuChi.getTau() == null ? "Tất cả"
				: Arrays.stream(tieuChi.getTau()).map(t -> t.getMaTau() + " - " + t.getTenTau())
						.collect(Collectors.joining(", "));
		String tauStatus = tieuChi.getTauStatus() == null ? "Tất cả"
				: Arrays.stream(tieuChi.getTauStatus()).map(TrangThaiTau::toString).collect(Collectors.joining(", "));
		String toaTaus = tieuChi.getToaTau() == null ? "Tất cả"
				: Arrays.stream(tieuChi.getToaTau()).map(tt -> tt.getMaToaTau() + " - " + tt.getTenToaTau())
						.collect(Collectors.joining(", "));
		String toaTauStatus = tieuChi.getToaTauStatus() == null ? "Tất cả"
				: convertArrayToList(tieuChi.getToaTauStatus()).stream().map(tts -> tts ? "Còn trống" : "Đã đầy")
						.collect(Collectors.joining(", "));
		String gheTaus = tieuChi.getGheTauStatus() == null ? "Tất cả"
				: Arrays.stream(tieuChi.getGheTauStatus()).map(gt -> gt).collect(Collectors.joining(", "));
		String gheTauStatus = tieuChi.getGheTauStatus() == null ? "Tất cả"
				: Arrays.stream(tieuChi.getGheTauStatus()).map(ts -> ts).collect(Collectors.joining(", "));
		String veStatus = tieuChi.getLoaiVe() == null ? "Tất cả"
				: convertArrayToList(tieuChi.getLoaiVe()).stream().map(lv -> lv ? "Đã hủy" : "Chưa hủy")
						.collect(Collectors.joining(", "));

		pMain.add(pNorth = Box.createVerticalBox(), BorderLayout.NORTH);
		pNorth.add(new TrainTitle("Báo cáo"));
		pNorth.add(createRow("Người tạo báo cáo", createdBy, "Thời gian tạo báo cáo", createdTime));
		pNorth.add(createRow("Thống kê từ lúc", fromTime, true));
		pNorth.add(createRow("Thống kê đến lúc", toTime, true));
		pNorth.add(createRow("Khách hàng được thống kê", khachHangs, "Loại khách hàng được thống kê", loaiKhachHangs));
		pNorth.add(createRow("Nhân viên được thống kê", nhanViens));
		pNorth.add(createRow("Ca làm được thống kê", caLams));
		pNorth.add(createRow("Khuyến mãi được thống kê", khuyenMais));
		pNorth.add(createRow("Chuyến tàu được thống kê", chuyenTaus));
		pNorth.add(createRow("Toa tàu được thống kê", toaTaus, "Trạng thái toa tàu", toaTauStatus));
		pNorth.add(createRow("Tàu được thống kê", taus, "Trạng thái tàu", tauStatus));
		pNorth.add(createRow("Ghế tàu được thống kê", gheTaus, "Trạng thái ghế tàu", gheTauStatus));
		pNorth.add(createRow("Trạng thái vé tàu", veStatus));

		pMain.add(pCharts = new TrainPanel(new GridLayout(0, 2)), BorderLayout.CENTER);

		add(pSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pSouth.add(Box.createGlue());
		pSouth.add(btnIn = new PrimaryButton("In báo cáo"));

		btnIn.addActionListener(e -> PrinterUtils.print(this, "In kết quả thống kê"));

		setSize(1900, 1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void addChart(DefaultCategoryDataset dataset, String title, String xLabel, String yLabel, String chartType) {
		TrainChart tc = null;

		if (chartType.equals("bar")) {
			tc = TrainChart.createBarChart(dataset, title, xLabel, yLabel);
		} else if (chartType.equals("line")) {
			tc = TrainChart.createLineChart(dataset, title, xLabel, yLabel);
		}

		tc.getPanel().setPreferredSize(new Dimension(0, 0));
		pCharts.add(tc.getPanel());
	}

	private Box createRow(String label, String value) {
		Box row = Box.createHorizontalBox();
		row.add(new JLabel(label + ": " + value));
		row.add(Box.createHorizontalGlue());
		return row;
	}

	private Box createRow(String label, String value, boolean rightAlignment) {
		Box row = Box.createHorizontalBox();
		row.add(Box.createHorizontalGlue());
		row.add(new JLabel(label + ": " + value));
		return row;
	}

	private Box createRow(String label1, String value1, String label2, String value2) {
		Box row = Box.createHorizontalBox();
		row.add(new JLabel(label1 + ": " + value1));
		row.add(Box.createHorizontalGlue());
		row.add(new JLabel(label2 + ": " + value2));
		return row;
	}

	private List<Boolean> convertArrayToList(boolean[] array) {
		List<Boolean> list = new ArrayList<>();
		for (boolean b : array) {
			list.add(b);
		}
		return list;
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		double pageWidth = pageFormat.getImageableWidth();
		double pageHeight = pageFormat.getImageableHeight();
		double scaleX = pageWidth / pMain.getWidth();
		g2d.scale(scaleX, scaleX);

		double scaledPageHeight = pageHeight / scaleX;
		int totalPages = (int) Math.ceil(pMain.getHeight() / scaledPageHeight);

		if (pageIndex >= totalPages) {
			return NO_SUCH_PAGE;
		}

		g2d.translate(pageFormat.getImageableX(), -pageIndex * scaledPageHeight);

		pMain.printAll(g2d);

		return PAGE_EXISTS;
	}

}
