### Assignment: Testing Exception Handling in a Messaging System with JUnit 5

#### Background
In this assignment, you will be writing tests for a simple messaging system using JUnit 5. The system enforces rules to maintain the integrity and appropriateness of messages. Your objective is to write tests that ensure the system throws the correct exceptions when these rules are violated.

#### Requirements
The messaging system has the following rules:
1. No messages with banned words are allowed.
2. Users cannot receive messages from senders they have blocked.
3. Messages cannot exceed a specified length limit.

Violations of these rules should result in specific exceptions being thrown:
- `BannedWordException` for banned words.
- `UserBlockedException` for messages from blocked senders.
- `MessageTooLongException` for messages that are too long.

#### Messaging System Code
Here is the complete code for the messaging system. Your task is to write tests for this system.

```java
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MessagingSystem {

    private Set<String> bannedWords = new HashSet<>(Arrays.asList("badword1", "badword2"));
    private Set<String> blockedUsers = new HashSet<>();
    private static final int MAX_MESSAGE_LENGTH = 500;

    public void sendMessage(String sender, String recipient, String messageContent) throws BannedWordException, UserBlockedException, MessageTooLongException {
        if (messageContent.length() > MAX_MESSAGE_LENGTH) {
            throw new MessageTooLongException("Message exceeds the maximum allowed length.");
        }

        for (String bannedWord : bannedWords) {
            if (messageContent.contains(bannedWord)) {
                throw new BannedWordException("Message contains a banned word: " + bannedWord);
            }
        }

        if (blockedUsers.contains(sender)) {
            throw new UserBlockedException("Sender is blocked by the recipient.");
        }

        // Message sending logic would go here
    }

    public void blockUser(String username) {
        blockedUsers.add(username);
    }
}

class BannedWordException extends Exception {
    public BannedWordException(String message) {
        super(message);
    }
}

class UserBlockedException extends Exception {
    public UserBlockedException(String message) {
        super(message);
    }
}

class MessageTooLongException extends Exception {
    public MessageTooLongException(String message) {
        super(message);
    }
}
```

#### Assignment Tasks
Create a JUnit 5 test class named `MessagingSystemTest`. Within this class, write test methods to verify that the correct exceptions are thrown under different circumstances. Follow these steps:

1. **Test Banned Word Exception**
   - Create a test method named `testSendMessageWithBannedWordThrowsException`.
   - Use the `assertThrows` method to assert that a `BannedWordException` is thrown when a message containing banned words is sent.
   - Capture the thrown exception and assert that the exception message is correct.

2. **Test User Blocked Exception**
   - Create a test method named `testSendMessageToBlockedUserThrowsException`.
   - Use the `assertThrows` method to assert that a `UserBlockedException` is thrown when a message is sent to a user who has blocked the sender.
   - Capture the thrown exception and assert that the exception message is correct.

3. **Test Message Too Long Exception**
   - Create a test method named `testSendMessageThatIsTooLongThrowsException`.
   - Use the `assertThrows` method to assert that a `MessageTooLongException` is thrown when a message exceeding the maximum allowed length is sent.
   - Capture the thrown exception and assert that the exception message is correct.

#### Tips
- Use the `@Test` annotation to indicate that a method is a test method.
- Use descriptive test method names that clearly state the purpose of each test.
- Use the `@BeforeEach` annotation if you need to set up common objects or state before each test.
- Remember to use the `assertThrows` method to both assert that an exception is thrown and to capture the thrown exception for further assertions.

#### Submission
Submit your `MessagingSystemTest` class containing all the test methods. Ensure that your tests are passing when run against the provided messaging system code. Your tests should be comprehensive and cover the scenarios outlined in the requirements.

Good luck, and happy testing!

