package com.social;

import com.friend.Friend;
import com.friend.IAddFriend;

public class Main {
    public static void main(String[] args) {
        IAddFriend addFriand = new AddFriendImpl();

        addFriand.addFriend(new Friend());
    }
}
