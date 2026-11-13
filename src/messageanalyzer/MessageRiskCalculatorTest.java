package messageanalyzer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class MessageRiskCalculatorTest {
@Test
void testCalculateRiskScore() {

    int score = MessageRiskCalculator.calculateRiskScore(
            true, true, true, true);

    assertEquals(11, score);
}

@Test
void testGetRiskLevelLow() {

    String level = MessageRiskCalculator.getRiskLevel(2);

    assertEquals("Low", level);
}

@Test
void testGetRiskLevelMedium() {

    String level = MessageRiskCalculator.getRiskLevel(4);

    assertEquals("Medium", level);
}

@Test
void testGetRiskLevelHigh() {

    String level = MessageRiskCalculator.getRiskLevel(9);

    assertEquals("High", level);
}
}
