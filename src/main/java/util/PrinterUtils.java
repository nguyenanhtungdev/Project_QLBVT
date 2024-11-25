package util;

import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;

/**
 * Lớp tiện ích để in dữ liệu
 */
public class PrinterUtils {
	public static final int NO_DIALOG = 0;
	public static final int PAGE_FORMAT_DIALOG_ONLY = 1;
	public static final int PRINT_DIALOG_ONLY = 2;
	public static final int BOTH_DIALOG = 3;

	/**
	 * In dữ liệu
	 * 
	 * @param printable Dữ liệu cần in
	 * @param jobName   Tên công việc in
	 */
	public static void print(Printable printable, String jobName) {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setJobName(jobName == null ? "Không có tên" : jobName);
		printerJob.setPrintable(printable);

		if (printerJob.printDialog()) {
			try {
				printerJob.print();
			} catch (PrinterException ex) {
				JOptionPane.showMessageDialog(null, "Không thể in: " + ex.getMessage(), "Lỗi in",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * In dữ liệu
	 * 
	 * @param p       Dữ liệu cần in
	 * @param jobName Tên công việc in
	 * @param flag    Cờ hiển thị hộp thoại in
	 *                <ul>
	 *                <li>NO_DIALOG: Không hiển thị hộp thoại in</li>
	 *                <li>PAGE_FORMAT_DIALOG_ONLY: Chỉ hiển thị hộp thoại định dạng
	 *                trang</li>
	 *                <li>PRINT_DIALOG_ONLY: Chỉ hiển thị hộp thoại in</li>
	 *                <li>BOTH_DIALOG: Hiển thị cả hai hộp thoại</li>
	 *                </ul>
	 */
	public static void print(Printable p, String jobName, int flag) {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setJobName(jobName == null ? "Không có tên" : jobName);
		printerJob.setPrintable(p);
		if (flag == PAGE_FORMAT_DIALOG_ONLY || flag == BOTH_DIALOG)
			printerJob.pageDialog(printerJob.defaultPage());

		if (flag == PRINT_DIALOG_ONLY || flag == BOTH_DIALOG || printerJob.printDialog()) {
			doPrint(printerJob);
		}

		if (flag == NO_DIALOG) {
			doPrint(printerJob);
		}
	}

	private static void doPrint(PrinterJob printerJob) {
		try {
			printerJob.print();
		} catch (PrinterException ex) {
			JOptionPane.showMessageDialog(null, "Không thể in: " + ex.getMessage(), "Lỗi in",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
