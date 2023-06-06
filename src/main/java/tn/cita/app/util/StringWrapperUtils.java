package tn.cita.app.util;

public interface StringWrapperUtils {
	
	public static String trimIfBlank(final String str) {
		return (str == null || str.isBlank()) ? null : str.strip();
	}
	
}



