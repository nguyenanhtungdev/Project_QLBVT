package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTable;

import org.jfree.data.category.DefaultCategoryDataset;

import controller.ThongKe_Controller;
import model.NhanVien;
import model.Tau;
import model.Tau.TrangThaiTau;
import model.ThongKeFilters;
import model.ToaTau;
import model.CaLam;
import model.ChuyenTau;
import model.GheTau;
import model.KhachHang;
import model.KhachHang.LoaiKhachHang;
import model.KhuyenMai;
import other.PrimaryButton;
import other.TrainChart;
import other.TrainPanel;
import other.TrainScrollPane;
import other.TrainTitle;
import util.PrinterUtils;

public class ThongKeKetQua_View extends View implements Printable {

	private static final long serialVersionUID = 6103116999501325988L;

	private TrainPanel pMain;
	private Box pNorth;
	private Box pSouth;
	private TrainPanel pCharts;
	private PrimaryButton btnIn;

	public ThongKeKetQua_View(String title, ThongKeFilters f, NhanVien nhanVien) {
		super(title, null);
		setTitle(title);
		setSize(1900, 1200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(new TrainPanel(new BorderLayout(16, 16), new Insets(16, 16, 16, 16)));

		add(new TrainScrollPane(pMain = new TrainPanel(new BorderLayout(), new Insets(16, 16, 16, 16))));

		String createdBy = nhanVien.getHoTenNV();
		String createdTime = LocalDateTime.now().format(ThongKe_Controller.FMT_DATETIME);

		String from = isBlank(f.getTuLuc()) ? "Không giới hạn" : f.getTuLuc().format(ThongKe_Controller.FMT_DATETIME);
		String to = isBlank(f.getDenLuc()) ? "Không giới hạn" : f.getDenLuc().format(ThongKe_Controller.FMT_DATETIME);

		String khachHangs = isBlank(f.getKhachHang()) ? "Tất cả khách hàng"
				: toStream(f.getKhachHang()).map(KhachHang::getMaKhachHang).collect(Collectors.joining(", "));
		String loaiKhachHangs = isBlank(f.getKhachHangCategory()) ? "Tất cả loại khách hàng"
				: toStream(f.getKhachHangCategory()).map(LoaiKhachHang::toString).collect(Collectors.joining(", "));

		String nhanViens = isBlank(f.getNhanVien()) ? "Tất cả nhân viên"
				: toStream(f.getNhanVien()).map(NhanVien::getMaNV).collect(Collectors.joining(", "));
		String caLams = isBlank(f.getCaLam()) ? "Tất cả ca làm"
				: toStream(f.getCaLam()).map(CaLam::getMaCa).collect(Collectors.joining(", "));
		String khuyenMais = isBlank(f.getKhuyenMai()) ? "Tất cả khuyến mãi"
				: (f.getKhuyenMai()[0] == null) ? "Không áp dụng"
						: toStream(f.getKhuyenMai()).map(KhuyenMai::getMaKhuyenMai).collect(Collectors.joining(", "));

		String chuyenTaus = isBlank(f.getChuyenTau()) ? "Tất cả chuyến tàu"
				: toStream(f.getChuyenTau()).map(ChuyenTau::getMaChuyenTau).collect(Collectors.joining(", "));
		String taus = isBlank(f.getTau()) ? "Tất cả tàu"
				: toStream(f.getTau()).map(Tau::getMaTau).collect(Collectors.joining(", "));
		String tauStatus = isBlank(f.getTauStatus()) ? "Tất cả trạng thái tàu"
				: toStream(f.getTauStatus()).map(TrangThaiTau::toString).collect(Collectors.joining(", "));
		String toaTaus = isBlank(f.getToaTau()) ? "Tất cả toa tàu"
				: toStream(f.getToaTau()).map(ToaTau::getMaToaTau).collect(Collectors.joining(", "));
		String ttToaTaus = (isBlank(f.getToaTauStatus()) || f.getToaTauStatus().length == 2)
				? "Tất cả trạng thái toa tàu"
				: f.getToaTauStatus()[0] ? "Còn trống" : "Đã đầy";
		String gheTaus = isBlank(f.getGheTau()) ? "Tất cả"
				: toStream(f.getGheTau()).map(GheTau::getMaGheTau).collect(Collectors.joining(", "));
		String ttGheTaus = isBlank(f.getGheTauStatus()) ? "Tất cả"
				: toStream(f.getGheTauStatus()).map(ts -> ts).collect(Collectors.joining(", "));
		String loaiVe = (isBlank(f.getLoaiVe()) || f.getLoaiVe().length == 2) ? "Tất cả loại vé"
				: f.getLoaiVe()[0] ? "Vé VIP" : "Vé thường";
		String ttVes = (isBlank(f.getTrangThaiVe()) || f.getTrangThaiVe().length == 2) ? "Tất cả trạng thái vé"
				: f.getTrangThaiVe()[0] ? "Đã hủy" : "Chưa hủy";

		pMain.add(pNorth = Box.createVerticalBox(), BorderLayout.NORTH);
		pNorth.add(new TrainTitle("Báo cáo"));
		pNorth.add(createRow("Người tạo báo cáo", createdBy, "Thời gian tạo báo cáo", createdTime));
		pNorth.add(createRow("Thống kê từ lúc", from, true));
		pNorth.add(createRow("Thống kê đến lúc", to, true));
		pNorth.add(createRow("Khách hàng", khachHangs, "Loại khách hàng được thống kê", loaiKhachHangs));
		pNorth.add(createRow("Nhân viên", nhanViens));
		pNorth.add(createRow("Ca làm", caLams));
		pNorth.add(createRow("Khuyến mãi", khuyenMais));
		pNorth.add(createRow("Chuyến tàu", chuyenTaus));
		pNorth.add(createRow("Toa tàu", toaTaus, "Trạng thái toa tàu", ttToaTaus));
		pNorth.add(createRow("Tàu", taus, "Trạng thái tàu", tauStatus));
		pNorth.add(createRow("Ghế tàu", gheTaus, "Trạng thái ghế tàu", ttGheTaus));
		pNorth.add(createRow("Loại vé tàu", loaiVe, "Trạng thái vé tàu", ttVes));

		pMain.add(pCharts = new TrainPanel(new GridLayout(0, 2)), BorderLayout.CENTER);

		add(pSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pSouth.add(Box.createGlue());
		pSouth.add(btnIn = new PrimaryButton("In báo cáo"));

		btnIn.addActionListener(e -> PrinterUtils.print(this, "In kết quả thống kê"));
	}

	private boolean isBlank(Object object) {
		if (object == null) {
			return true;
		}

		if (object.getClass().isArray()) {
			return object == null || ((Object[]) object).length == 0;
		}

		return object == null;
	}

	private <T> Stream<T> toStream(T[] object) {
		return Arrays.stream(object);
	}

	public void addChart(DefaultCategoryDataset dataset, String title, String xLabel, String yLabel, String chartType) {
		TrainChart tc = null;

		if (chartType.equals("bar")) {
			tc = TrainChart.createBarChart(dataset, title, xLabel, yLabel);
		} else if (chartType.equals("line")) {
			tc = TrainChart.createLineChart(dataset, title, xLabel, yLabel);
		}

		tc.getPanel().setPreferredSize(new Dimension(0, 400));
		pCharts.add(tc.getPanel());
	}

	public void addTable(String title, String[] columnNames, List<Object[]> data) {
		JTable table = new JTable(data.size(), columnNames.length);

		for (int i = 0; i < columnNames.length; i++) {
			table.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
		}

		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < columnNames.length; j++) {
				table.setValueAt(data.get(i)[j], i, j);
			}
		}

		TrainScrollPane scrollPane = new TrainScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(0, 400));
		pCharts.add(scrollPane);
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

		pSouth.setVisible(false);
		pMain.printAll(g2d);
		pSouth.setVisible(true);

		return PAGE_EXISTS;
	}

}
