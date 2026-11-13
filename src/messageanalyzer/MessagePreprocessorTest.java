package messageanalyzer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MessagePreprocessorTest {

@Test
void testPreprocessLowercase() {
    String result = MessagePreprocessor.preprocess("HELLO");
    assertEquals("hello", result);
}

@Test
void testPreprocessTrim() {
    String result = MessagePreprocessor.preprocess("  Hello  ");
    assertEquals("hello", result);
}

@Test
void testPreprocessSpaces() {
    String result = MessagePreprocessor.preprocess("Hello    World");
    assertEquals("hello world", result);
}

@Test
void testPreprocessNull() {
    String result = MessagePreprocessor.preprocess(null);
    assertEquals("", result);
}
}
