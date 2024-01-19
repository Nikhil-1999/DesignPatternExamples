/*
Mediator is a behavioral design pattern that reduces coupling between components of a program by making them communicate indirectly, through a special mediator object.
Mediator pattern is used to reduce communication complexity between multiple objects or classes.
Refs - https://www.javatpoint.com/mediator-pattern 
*/

import java.util.*;
import java.time.*;

// mediator interface
interface GroupChat {
    public void broadcastMessageInGroupChat(GroupChatParticipant user, String message);
}

// concrete mediator
class GroupChatImpl implements GroupChat {
    @Override
    public void broadcastMessageInGroupChat(GroupChatParticipant user, String message) {
        System.out.println(user.getName() + " sent a message at: " + LocalDateTime.now());
        System.out.println(message);
    }
}

// colleague
abstract class GroupChatParticipant {
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract void sendMessageInGroupChat(String message);
}

// concrete colleague
class User extends GroupChatParticipant {
    private GroupChat groupChat;

    public User(GroupChat groupChat) {
        this.groupChat = groupChat;
    }

    @Override
    public void sendMessageInGroupChat(String message) {
        groupChat.broadcastMessageInGroupChat(this, message);
    }
}

// client 
class MediatorDemo {
    public static void main(String[] args) {
        GroupChat groupChat = new GroupChatImpl();
        GroupChatParticipant user1 = new User(groupChat);
        user1.setName("Ram");
        GroupChatParticipant user2 = new User(groupChat);
        user2.setName("Shyam");
        user1.sendMessageInGroupChat("Hi team, how are we doing today?");
        user2.sendMessageInGroupChat("Doing good mate.");
    }
}

/*
Steps to create a mediator pattern ->
1. Declare the mediator interface and describe the desired communication methods and implement the concrete mediator class.
2. Components should store a reference to the mediator object. The connection is usually established in the component’s constructor, where a mediator object is passed as an argument.
3. Change the components’ code so that they call the mediator’s notification method instead of methods on other components.
*/