package messageanalyzer;

public class MessagePreprocessor {
	public static String preprocess(String message) {

		if (message == null) {
		    return "";
		}

		return message.toLowerCase().trim().replaceAll("\\s+", " ");
		}

}
