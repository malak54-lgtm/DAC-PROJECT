Message Analyzer – DAC-PROJECT
1. Overview
The Message Analyzer is responsible for analyzing Email and SMS content to detect suspicious messages and potential phishing attempts.
2. Message Preprocessing
The MessagePreprocessor class prepares messages before analysis by:
Converting text to lowercase.
Removing unnecessary spaces.
Normalizing the message content.
3. Suspicious Indicators Detection
The MessageAnalyzer detects four categories of suspicious indicators:
Suspicious Keywords: Congratulations, You won a prize, Free gift, Claim your reward, Verify your account.
Credential Requests: Password, OTP, Verification code, Credit card number, Bank account details, Login credentials.
Urgency Language: Act now, Immediately, Last chance, Within 24 hours, Urgent, Right away.
Threat Language: Account will be suspended, Account will be deleted, Account is blocked.
4. Risk Score Calculation
The MessageRiskCalculator calculates the risk score according to the following rules:
Indicator Risk Points
Suspicious Keywords +2
Credential Request +4
Urgency Language +2
Threat Language +3
5. Risk Level Classification
Risk Score Risk Level
0–2 Low
3–5 Medium
6 or more High
6. Error Handling
Empty or null messages are rejected with an IllegalArgumentException in the analyzeMessage method.
7. Main Classes
MessageAnalyzer.java: Analyzes messages and detects suspicious indicators.
MessagePreprocessor.java: Preprocesses message content.
MessageRiskCalculator.java: Calculates the risk score and determines the risk level.
MessagePreprocessorTest.java: Tests message preprocessing.
MessageRiskCalculatorTest.java: Tests risk calculation and classification.
MessageAnalyzerTest.java: Tests the full message analysis workflow with normal, suspicious, phishing, and invalid inputs.