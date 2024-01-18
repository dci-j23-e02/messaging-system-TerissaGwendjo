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
