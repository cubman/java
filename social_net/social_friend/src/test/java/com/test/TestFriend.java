package com.test;

import com.friend.Friend;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestFriend {

    @Test
    public void testFriend() {
        Friend f = new Friend();
        f.setName("1");
        Assert.assertEquals(f.getName(), "1");

        Friend fr = mock(Friend.class);
        fr.setName("2");
        when(fr.getName()).thenReturn("2");
    }
}
