package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import other.ColorConstants;
import other.PrimaryButton;
import other.SecondaryButton;
import other.TieuDe;

public class KetQuaThongKe_View extends View {

	private static final long serialVersionUID = 6103116999501325988L;

	public KetQuaThongKe_View(String name, String imagePath) {
		super(name, imagePath);

		setLayout(new BorderLayout());

		renderUI();
	}

	public void renderUI() {
		JPanel pMain = new JPanel(new GridBagLayout());
		pMain.setBackground(ColorConstants.BACKGROUND_COLOR);
		JScrollPane pScroll = new JScrollPane(pMain);
		pScroll.setBorder(null);

		TieuDe tieuDe = new TieuDe("Báo cáo");

		JLabel lNguoiTao = new JLabel("Người tạo: Nguyễn Anh Tùng");
		JLabel lThoiGianTao = new JLabel("Thời gian tạo: 10:45:29 24/9/2024");
		JLabel lLoaiBaoCao = new JLabel("Loại báo cáo: Số lượng vé");
		JLabel lTuNgay = new JLabel("Từ ngày: 17/9/2024");
		JLabel lDenNgay = new JLabel("Đến ngày: 23/9/2024");
		JLabel lLoaiKhachHang = new JLabel("Loại khách hàng: Tất cả");
		JLabel lLoaiVe = new JLabel("Loại vé: Tất cả");
		JLabel lTrangThaiVe = new JLabel("Trạng thái vé: Tất cả");
		JLabel lMaNhanVien = new JLabel("Mã nhân viên: NV00001");
		JLabel lTenNhanVien = new JLabel("Tên nhân viên: Nguyễn Anh Tùng");
		JLabel lMaTau = new JLabel("Mã tàu: Tất cả");
		JLabel lTenTau = new JLabel("Tên tàu: Tất cả");
		JLabel lMaChuyenTau = new JLabel("Mã chuyến tàu: Tất cả");
		JLabel lTuGa = new JLabel("Từ ga: Tất cả");
		JLabel lDenGa = new JLabel("Đến ga: Tất cả");
		JLabel lMaCaLam = new JLabel("Mã ca làm: Tất cả");
		JLabel lTuLuc = new JLabel("Từ lúc: Tất cả");
		JLabel lDenLuc = new JLabel("Đến lúc: Tất cả");
		JLabel lMaKhuyenMai = new JLabel("Mã khuyến mãi: Tất cả");

		Font font = new Font("Arial", Font.PLAIN, 16);
		lNguoiTao.setFont(font);
		lThoiGianTao.setFont(font);
		lLoaiBaoCao.setFont(font);
		lTuNgay.setFont(font);
		lDenNgay.setFont(font);
		lLoaiKhachHang.setFont(font);
		lLoaiVe.setFont(font);
		lTrangThaiVe.setFont(font);
		lMaNhanVien.setFont(font);
		lTenNhanVien.setFont(font);
		lMaTau.setFont(font);
		lTenTau.setFont(font);
		lMaChuyenTau.setFont(font);
		lTuGa.setFont(font);
		lDenGa.setFont(font);
		lMaCaLam.setFont(font);
		lTuLuc.setFont(font);
		lDenLuc.setFont(font);
		lMaKhuyenMai.setFont(font);

		TieuDe tieuDeBieuDo = new TieuDe("Biểu đồ", TieuDe.H2);

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(5000000, "doanh thu", "17/9");
		dataset.addValue(4500000, "doanh thu", "18/9");
		dataset.addValue(6200000, "doanh thu", "19/9");
		dataset.addValue(5800000, "doanh thu", "20/9");
		dataset.addValue(7000000, "doanh thu", "21/9");
		dataset.addValue(6500000, "doanh thu", "22/9");
		dataset.addValue(7500000, "doanh thu", "23/9");

		JFreeChart chart = ChartFactory.createLineChart("", "Ngày", "Doanh thu (VNĐ)", dataset,
				PlotOrientation.VERTICAL, true, true, true);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(0, 0, 0, 0));
		plot.setOutlineVisible(false);

		plot.setRangeGridlinePaint(Color.GRAY);
		plot.setDomainGridlinePaint(Color.GRAY);
		plot.setDomainGridlinesVisible(true);

		plot.getDomainAxis().setLabelFont(new Font("Arial", Font.PLAIN, 14));
		plot.getDomainAxis().setTickLabelFont(new Font("Arial", Font.PLAIN, 12));

		plot.getRangeAxis().setLabelFont(new Font("Arial", Font.PLAIN, 14));
		plot.getRangeAxis().setTickLabelFont(new Font("Arial", Font.PLAIN, 12));

		LineAndShapeRenderer renderer = new LineAndShapeRenderer(true, false);
		renderer.setSeriesPaint(0, ColorConstants.PRIMARY_COLOR);
		plot.setRenderer(renderer);

		ChartPanel pChart = new ChartPanel(chart);
		pChart.setMaximumDrawHeight(2048);
		pChart.setMaximumDrawWidth(1920);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 1;
		c.insets = new Insets(0, 0, 8, 0);
		pMain.add(tieuDe, c);

		// Cột 1
		c.insets = new Insets(4, 4, 4, 4);
		c.gridy = 1;
		pMain.add(lNguoiTao, c);
		c.gridy = 2;
		pMain.add(lLoaiBaoCao, c);
		c.gridy = 3;
		pMain.add(lLoaiKhachHang, c);
		c.gridy = 4;
		pMain.add(lLoaiVe, c);
		c.gridy = 5;
		pMain.add(lMaNhanVien, c);
		c.gridy = 6;
		pMain.add(lMaTau, c);
		c.gridy = 7;
		pMain.add(lMaChuyenTau, c);
		c.gridy = 8;
		pMain.add(lMaCaLam, c);
		c.gridy = 9;
		pMain.add(lMaKhuyenMai, c);

		// Cột 2
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 7;
		pMain.add(lTuGa, c);
		c.gridy = 8;
		pMain.add(lTuLuc, c);

		// Cột 3
		c.anchor = GridBagConstraints.NORTHEAST;
		c.gridx = 2;
		c.gridy = 1;
		pMain.add(lThoiGianTao, c);
		c.gridy = 2;
		pMain.add(lTuNgay, c);
		c.gridy = 3;
		pMain.add(lDenNgay, c);
		c.gridy = 4;
		pMain.add(lTrangThaiVe, c);
		c.gridy = 5;
		pMain.add(lTenNhanVien, c);
		c.gridy = 6;
		pMain.add(lTenTau, c);
		c.gridy = 7;
		pMain.add(lDenGa, c);
		c.gridy = 8;
		pMain.add(lDenLuc, c);

		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 10;
		c.insets = new Insets(4, 0, 8, 0);
		pMain.add(tieuDeBieuDo, c);

		c.gridy = 11;
		c.insets = new Insets(4, 4, 4, 4);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;
		pMain.add(pChart, c);

		add(pScroll);

		Box bThaoTac = Box.createHorizontalBox();
		SecondaryButton btnXuatPDF = new SecondaryButton("Xuất PDF");
		PrimaryButton btnIn = new PrimaryButton("In báo cáo");
		bThaoTac.add(btnXuatPDF);
		bThaoTac.add(Box.createGlue());
		bThaoTac.add(btnIn);
		add(bThaoTac, BorderLayout.SOUTH);
	}

	@Override
	public Insets getInsets() {
		return new Insets(16, 16, 16, 16);
	}

}
