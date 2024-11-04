package other;

import javax.swing.*;
import java.awt.*;

public class SeatButton extends JButton {
    public SeatButton(int seatNumber, Color backgroundColor, Color textColor) {
        // Đặt kích thước và màu nền
        this.setPreferredSize(new Dimension(55, 35)); // Kích thước hình vuông
        this.setBackground(backgroundColor);

        // Sử dụng GridBagLayout để căn giữa số trong nút
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Cột 0
        gbc.gridy = 0; // Hàng 0
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa
        gbc.fill = GridBagConstraints.NONE; // Không kéo dài
        gbc.weightx = 1.0; // Trọng số x
        gbc.weighty = 1.0; // Trọng số y

        // Thiết lập số ghế làm nội dung của JButton
        this.setText(String.valueOf(seatNumber));
        this.setForeground(textColor); // Màu chữ
        
        // Thiết lập phông chữ lớn hơn và in đậm
        this.setFont(new Font("Arial", Font.BOLD, 16)); // Phông chữ Arial, in đậm, kích thước 16
        
        this.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa số trong JButton
        this.setBorderPainted(false); // Loại bỏ viền nút
        this.setFocusPainted(false); // Loại bỏ viền khi nhấn nút
    }
}
