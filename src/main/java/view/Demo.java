package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class Demo {
    public static void main(String[] args) {
        // Dữ liệu mẫu
        Object[][] data = {
                {"John Doe", 30, "Male"},
                {"Jane Smith", 25, "Female"},
                {"Tom Brown", 40, "Male"},
                {"Emily White", 35, "Female"},
                {"Michael Green", 50, "Male"}
        };

        // Tên cột
        String[] columnNames = {"Name", "Age", "Gender"};

        // Tạo bảng với dữ liệu và tên cột
        JTable table = new JTable(data, columnNames) {
            // Tùy chỉnh renderer để tạo màu xen kẽ cho các dòng
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? new Color(240, 240, 240) : Color.WHITE);
                }
                return c;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa bất kỳ ô nào
            }
        };

        // Thiết lập font cho bảng
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);  // Tăng chiều cao hàng để tạo khoảng trống

        // Tùy chỉnh tiêu đề bảng
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(100, 149, 237));  // Màu xanh cho tiêu đề
        header.setForeground(Color.WHITE);

        // Đặt border và căn chỉnh cho header
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        table.setFillsViewportHeight(true);  // Tự động điều chỉnh chiều cao bảng

        // Điều chỉnh chiều rộng cột dựa trên nội dung
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < table.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(150);
        }

        // Đặt màu nền cho dòng được chọn
        table.setSelectionBackground(new Color(135, 206, 250));  // Màu xanh nhạt cho dòng được chọn
        table.setSelectionForeground(Color.BLACK);

        // Thêm đường viền cho các ô
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);

        // Thêm bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        // Tùy chỉnh renderer để xóa border khi ô được chọn
        DefaultTableCellRenderer noBorderRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Xóa border khi ô được chọn
                if (isSelected) {
                    setBorder(BorderFactory.createEmptyBorder()); // Xóa viền khi được chọn
                }

                return cell;
            }
        };

        // Áp dụng renderer cho tất cả các cột
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(noBorderRenderer);
        }


        // Tạo JFrame để hiển thị bảng
        JFrame frame = new JFrame("Formatted Table Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }
}
