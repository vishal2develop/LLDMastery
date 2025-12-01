package ChatRoom;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator{

    List<User> users= new ArrayList<>();
    @Override
    public void sendMessage(String message, User sender) {
        for(User user: users){
            if (user!=sender){
                user.receiveMessage(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
