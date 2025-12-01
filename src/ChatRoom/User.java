package ChatRoom;

public interface User {
    void receiveMessage(String message);
    void sendMessage(String message);
}
