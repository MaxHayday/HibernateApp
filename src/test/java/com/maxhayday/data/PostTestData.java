package com.maxhayday.data;

import com.maxhayday.model.Post;
import com.maxhayday.model.Region;
import com.maxhayday.model.Role;
import com.maxhayday.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class PostTestData {

    public static Post testDataOfPostForCreating() {
        Post post = new Post(3L, "Post 1 of Ira", Timestamp.valueOf("2021-03-04 23:15:10"), Timestamp.valueOf("2021-03-04 23:15:10"));
        return post;
    }

    public static Post testDataOfPostForUpdating() {
        Post post = new Post(2L, "Updated Post 1 of Ola", Timestamp.valueOf("2021-03-04 23:15:35.0"), Timestamp.valueOf("2021-03-04 23:15:35.0"));
        return post;
    }

    public static Post expectedCreatedDataOfPost() {
        Post post = new Post(3L, "Post 1 of Ira", Timestamp.valueOf("2021-03-04 23:15:10"), Timestamp.valueOf("2021-03-04 23:15:10"));

        return post;
    }

    public static Post expectedDataOfPostGetById() {
        Post post = new Post(1L, "Updated Post 1 of Max", Timestamp.valueOf("2021-03-04 23:15:10"), Timestamp.valueOf("2021-03-04 23:15:10"));
        return post;
    }

    public static List<Post> expectedDataOfAllPosts() {
        ArrayList<User> users = new ArrayList<>();
        List<Post> postList = new ArrayList<>();
        Post post1 = new Post(1L, "Post 1 of Max", Timestamp.valueOf("2021-03-04 23:15:10"), Timestamp.valueOf("2021-03-04 23:15:10"));
        postList.add(post1);
        Region region = new Region(1L, "UKR");
        User user = new User(1L, "Max", "Hayday", postList, region, Role.USER);
        users.add(user);

        post1 = new Post(2L, "Post 1 of Ola", Timestamp.valueOf("2021-03-04 23:15:35.0"), Timestamp.valueOf("2021-03-04 23:15:35.0"));
        postList.add(post1);
        region = new Region(2L, "IRL");
        user = new User(2L, "Ola", "Hayday", postList, region, Role.USER);

        users.add(user);

        return postList;
    }
}
