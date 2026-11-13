package messageanalyzer;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class MessageAnalyzerTest {

// 1. اختبار رسالة عادية (النتيجة المتوقعة: 0 نقاط ومستوى Low)
@Test
void testNormalMessage() {
    String message = "Hello, how are you? See you tomorrow.";
    
    int score = MessageAnalyzer.analyzeMessage(message);
    String riskLevel = MessageRiskCalculator.getRiskLevel(score);
    List<String> indicators = MessageAnalyzer.detectIndicators(message);

    assertEquals(0, score, "Normal message should have a score of 0");
    assertEquals("Low", riskLevel, "Normal message should be Low risk");
    assertTrue(indicators.isEmpty(), "Normal message should have no indicators");
}

// 2. اختبار رسالة مشبوهة (تحتوي على كلمات جوائز ومكافآت)
@Test
void testSuspiciousMessage() {
    String message = "Congratulations! You won a free gift. Click here to claim your reward.";
    
    int score = MessageAnalyzer.analyzeMessage(message);
    String riskLevel = MessageRiskCalculator.getRiskLevel(score);
    List<String> indicators = MessageAnalyzer.detectIndicators(message);

    assertEquals(2, score, "Suspicious keywords should give 2 points");
    assertEquals("Low", riskLevel);
    assertTrue(indicators.contains("Suspicious keywords"));
}

// 3. اختبار رسالة تطلب كلمة المرور مع تهديد بإغلاق الحساب
@Test
void testCredentialRequestAndThreatMessage() {
    String message = "Your account is blocked. Enter your password to restore access.";
    
    int score = MessageAnalyzer.analyzeMessage(message);
    String riskLevel = MessageRiskCalculator.getRiskLevel(score);
    List<String> indicators = MessageAnalyzer.detectIndicators(message);

    // 4 نقاط لطلب كلمة المرور + 3 نقاط للتهديد = 7 نقاط (High)
    assertEquals(7, score);
    assertEquals("High", riskLevel);
    assertTrue(indicators.contains("Credential request"));
    assertTrue(indicators.contains("Threat language"));
}

// 4. اختبار رسالة تحتوي على تهديد واستعجال
@Test
void testUrgencyAndThreatMessage() {
    String message = "Your account will be suspended immediately. Verify your information within 24 hours.";
    
    int score = MessageAnalyzer.analyzeMessage(message);
    String riskLevel = MessageRiskCalculator.getRiskLevel(score);
    List<String> indicators = MessageAnalyzer.detectIndicators(message);

    // 3 نقاط للتهديد + 2 نقطة للاستعجال = 5 نقاط (Medium)
    assertEquals(5, score);
    assertEquals("Medium", riskLevel);
    assertTrue(indicators.contains("Threat language"));
    assertTrue(indicators.contains("Urgency language"));
}

// 5. اختبار الرسائل الفارغة و null (التأكد من رفضها بـ IllegalArgumentException)
@Test
void testEmptyAndNullMessages() {
    assertThrows(IllegalArgumentException.class, () -> {
        MessageAnalyzer.analyzeMessage("");
    }, "Empty string should throw IllegalArgumentException");

    assertThrows(IllegalArgumentException.class, () -> {
        MessageAnalyzer.analyzeMessage("   ");
    }, "Whitespace-only string should throw IllegalArgumentException");

    assertThrows(IllegalArgumentException.class, () -> {
        MessageAnalyzer.analyzeMessage(null);
    }, "Null message should throw IllegalArgumentException");
}

// 6. اختبار عدم الحساسية لحالة الأحرف (Case Insensitive) والرسائل الطويلة جداً
@Test
void testCaseInsensitivityAndLongMessage() {
    String longMessage = "Normal text. ".repeat(500) + "ACT NOW and send your PASSWORD!";
    
    int score = MessageAnalyzer.analyzeMessage(longMessage);
    String riskLevel = MessageRiskCalculator.getRiskLevel(score);

    // 2 للاستعجال + 4 لكلمة المرور = 6 نقاط (High)
    assertEquals(6, score);
    assertEquals("High", riskLevel);
}
}