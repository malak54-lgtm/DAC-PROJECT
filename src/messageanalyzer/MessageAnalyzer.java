package messageanalyzer;
import java.util.ArrayList;
import java.util.List;

public class MessageAnalyzer {

	public static int analyzeMessage(String message) {

		if (message == null || message.trim().isEmpty()) {
		    throw new IllegalArgumentException("Message cannot be empty.");
		}

		List<String> indicators = detectIndicators(message);

		boolean hasSuspiciousKeywords = indicators.contains("Suspicious keywords");
		boolean requestsCredentials = indicators.contains("Credential request");
		boolean hasUrgency = indicators.contains("Urgency language");
		boolean hasThreat = indicators.contains("Threat language");

		return MessageRiskCalculator.calculateRiskScore(
		        hasSuspiciousKeywords,
		        requestsCredentials,
		        hasUrgency,
		        hasThreat
		);
		}

public static void main(String[] args) {

	String message = "Hello, this is a long message. ".repeat(1000)
	        + "Your account will be suspended. Enter your password immediately.";

    int score = analyzeMessage(message);

    String riskLevel =
            MessageRiskCalculator.getRiskLevel(score);

    System.out.println("Message: " + message);
    System.out.println("Risk Score: " + score);
    System.out.println("Risk Level: " + riskLevel);
    System.out.println("Detected Indicators: "
            + detectIndicators(message));
}
public static List<String> detectIndicators(String message) {

List<String> indicators = new ArrayList<>();

if (message == null || message.trim().isEmpty()) {
    return indicators;
}

String text = MessagePreprocessor.preprocess(message);

if (text.contains("congratulations")
        || text.contains("you won a prize")
        || text.contains("free gift")
        || text.contains("claim your reward")
        || text.contains("verify your account")) {
    indicators.add("Suspicious keywords");
}

if (text.contains("password")
        || text.contains("otp")
        || text.contains("verification code")
        || text.contains("credit card")
        || text.contains("credit card number")
        || text.contains("bank account")
        || text.contains("bank account details")
        || text.contains("login credentials")) {
    indicators.add("Credential request");
}

if (text.contains("act now")
        || text.contains("immediately")
        || text.contains("last chance")
        || text.contains("within 24 hours")
        || text.contains("urgent")
        || text.contains("right away")) {
    indicators.add("Urgency language");
}

if (text.contains("account will be suspended")
        || text.contains("account will be deleted")
        || text.contains("account is blocked")
        || text.contains("account has been blocked")
        || text.contains("your account will be suspended")
        || text.contains("your account will be deleted")) {
    indicators.add("Threat language");
}
return indicators;
}
}
