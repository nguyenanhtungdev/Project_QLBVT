package constant;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatterConstants {

	public static final NumberFormat MONEY = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	public static final DateTimeFormatter DATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss");
	public static final DateTimeFormatter HOUR = DateTimeFormatter.ofPattern("HH");

}
