package com.futurell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description:
 * @Author: lilei58
 * @Date: Created in 2021/7/20 上午7:46
 */
public class CollectionDemo {

    public static void main(String[] args) {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 20; i++) {
            User user = new User(i, "user" + i);
            list.add(user);
        }

        Iterator<User> it = list.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if ("user6".equals(user.getName())) {
                list.remove(user);
            }
        }
    }
}
