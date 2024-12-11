package other;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.KhachHang;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportExcel {

    public static boolean exportKhachHangToExcel(String filePath, List<KhachHang> khachHangList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Danh sách khách hàng");

        // Tạo tiêu đề cột
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Mã khách hàng", "Họ tên", "Số điện thoại", "CCCD", "Email", "Ngày sinh", "Giới tính", "Loại khách hàng"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(createHeaderCellStyle(workbook));
        }

        // Thêm dữ liệu
        int rowNum = 1;
        for (KhachHang kh : khachHangList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(kh.getMaKhachHang());
            row.createCell(1).setCellValue(kh.getHoTen());
            row.createCell(2).setCellValue(kh.getSoDienThoai());
            row.createCell(3).setCellValue(kh.getCCCD());
            row.createCell(4).setCellValue(kh.getEmail());
            row.createCell(5).setCellValue(kh.getNgaySinh().toString());
            row.createCell(6).setCellValue(kh.isGioiTinh() ? "Nam" : "Nữ");
            row.createCell(7).setCellValue(kh.getLoaiKH().name());
        }

        // Tự động điều chỉnh độ rộng cột
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Ghi dữ liệu vào file
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Lỗi khi ghi file
        }

        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Mở file sau khi xuất
        openFile(filePath);

        return true; // Xuất thành công
    }

    private static CellStyle createHeaderCellStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    private static void openFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("Desktop không được hỗ trợ trên hệ thống này!");
                }
            } else {
                System.out.println("File không tồn tại: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
