package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static List<Integer> suggestCustomerPayment(int totalAmount) {
        List<Integer> suggestions = new ArrayList<>();

        // Các mệnh giá tiền phổ biến tại Việt Nam
        int[] denominations = {500_000, 200_000, 100_000, 50_000, 20_000, 10_000, 5_000, 2_000, 1_000};

        // Gợi ý mệnh giá gần nhất mà khách có thể đưa
        for (int denomination : denominations) {
            if (denomination >= totalAmount) {
                suggestions.add(denomination);
            }
        }

        // Thêm các giá trị tùy chỉnh (tổng tiền + khoảng thêm để linh hoạt)
        int increments[] = {10_000, 20_000, 50_000}; // Khoảng tiền khách có thể đưa nhiều hơn
        for (int increment : increments) {
            int customAmount = totalAmount + increment;
            if (!suggestions.contains(customAmount)) {
                suggestions.add(customAmount);
            }
        }

        return suggestions;
    }

    public static void createAndShowGUI(int totalAmount) {
        // Tạo frame chính
        JFrame frame = new JFrame("Gợi ý số tiền khách đưa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Lấy danh sách gợi ý tiền
        List<Integer> suggestions = suggestCustomerPayment(totalAmount);

        // Tạo panel với GridLayout
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // Hiển thị 3 cột, khoảng cách 10px

        // Thêm các nút gợi ý vào panel
        for (int suggestion : suggestions) {
            JButton button = new JButton(suggestion + " VND");
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Khách chọn: " + suggestion + " VND"));
            gridPanel.add(button);
        }

        // Thêm panel vào frame
        frame.add(gridPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        int totalAmount = 360_000; // Tổng tiền cần thanh toán

        // Hiển thị giao diện
        SwingUtilities.invokeLater(() -> createAndShowGUI(totalAmount));
    }
}
