package ChatRoom;

public class ChatUser implements User{
    public String name;
    ChatMediator chatMediator;
    public ChatUser(String name,ChatMediator chatMediator){
        this.name = name;
        this.chatMediator = chatMediator;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(name + " sends: " + message);
        chatMediator.sendMessage(message,this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " received: " + message);
    }
}
