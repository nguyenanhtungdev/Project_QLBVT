package other;

import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;

public class PrinterUltilities {
	private static PrinterUltilities instance;

	public static PrinterUltilities getInstance() {
		if (instance == null)
			instance = new PrinterUltilities();
		return instance;
	}

	public void print(Printable p, String jobName) {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setJobName(jobName == null ? "Không có tên" : jobName);
		printerJob.setPrintable(p);

		if (printerJob.printDialog()) {
			try {
				printerJob.print();
			} catch (PrinterException ex) {
				JOptionPane.showMessageDialog(null, "Không thể in: " + ex.getMessage(), "Lỗi in",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
