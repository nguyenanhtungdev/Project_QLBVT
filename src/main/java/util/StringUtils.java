package util;

import java.text.Normalizer;
import java.text.Normalizer.Form;

public class StringUtils {

	public static String normalize(String value) {
		return Normalizer.normalize(value.trim().toLowerCase(), Form.NFD)
				.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	}

}
