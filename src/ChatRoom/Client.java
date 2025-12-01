package ChatRoom;

public class Client {
    public static void main(String[] args) {
        ChatMediator chatMediator = new ChatRoom();

        User chatUser1 = new ChatUser("Vishal",chatMediator);
        User chatUser2 = new ChatUser("Rahul",chatMediator);

        chatMediator.addUser(chatUser1);
        chatMediator.addUser(chatUser2);

        chatUser1.sendMessage("Hello World");
        chatUser2.sendMessage("Hi");

    }
}
