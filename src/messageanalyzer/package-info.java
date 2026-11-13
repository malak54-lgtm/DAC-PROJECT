/**
Message Analyzer Module.
This package analyzes Email and SMS message content
to detect suspicious and potentially phishing messages.
Detection rules:
Suspicious keywords: +2 points
Credential requests: +4 points
Urgency language: +2 points
Threat language: +3 points
Risk levels:
Low: 0 to 2
Medium: 3 to 5
High: 6 or more
The analyzer focuses on message content,
not URLs or links.
*/
package messageanalyzer;