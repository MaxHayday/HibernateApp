package com.maxhayday.data;

import com.maxhayday.model.Post;
import com.maxhayday.model.Region;
import com.maxhayday.model.Role;
import com.maxhayday.model.User;
import com.maxhayday.repository.PostRepository;
import com.maxhayday.repository.RegionRepository;
import com.maxhayday.repository.UserRepository;
import com.maxhayday.repository.hbn.HBPostRepositoryImpl;
import com.maxhayday.repository.hbn.HBRegionRepositoryImpl;
import com.maxhayday.repository.hbn.HBUserRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.List;

public class UserTestData {

    public static final Region REGION1 = new Region(1l, "UKR");
    public static final Region REGION2 = new Region(2L, "IRL");
    public static final User USER1 = new User(1l, "Max", "Hayday", null, REGION1, Role.USER);
    public static final User USER2 = new User(2l, "Ola", "Hayday", null, REGION2, Role.USER);


    public static final List<User> USERS = new ArrayList<>();

    public static final Post POST1 = new Post(1L, "Post 1 of Max", Timestamp.valueOf("2021-01-03 8:43:10"), Timestamp.valueOf("2021-01-03 8:43:10"), USER1);
    public static final Post POST2 = new Post(2l, "Post 1 of Ola", Timestamp.valueOf("2021-01-03 8:43:10"), Timestamp.valueOf("2021-01-03 8:43:10"), USER2);


    public static final List<Post> POSTS_LIST1 = new ArrayList<>();
    public static final List<Post> POSTS_LIST2 = new ArrayList<>();


    PostRepository postRepository = new HBPostRepositoryImpl();
    RegionRepository regionRepository = new HBRegionRepositoryImpl();
    UserRepository userRepository = new HBUserRepositoryImpl();


    public static User testDataOfUserForCreating() {
        Region region = new Region(3L, "POL");
        User user = new User(3l, "Dima", "Matsech", null, region, Role.USER);

        return user;
    }

    public static User testDataOfUserForUpdating() {
        List<Post> postList = new ArrayList<>();
        Region region = new Region(2L, "IRL");
        User user = new User(2l, "Olaa", "Hayday", postList, region, Role.USER);
        return user;
    }

    public static User expectedDataOfUserForUpdating() {
        List<Post> postList = new ArrayList<>();
        Post post1 = new Post(5L, "updated post 1 of Ola", Timestamp.valueOf("2021-01-03 8:43:10"), Timestamp.valueOf("2021-01-03 8:43:10"));
        postList.add(post1);
        Region region = new Region(3L, "UKR");
        User user = new User(3L, "Ola", "Hayday", postList, region, Role.USER);
        return user;
    }

    public static User expectedCreatedDataOfUser() {
        List<Post> postList = new ArrayList<>();
        Post post1 = new Post(3l, "Post 1 of Dima", Timestamp.valueOf("2021-01-03 8:43:10"), Timestamp.valueOf("2021-01-03 8:43:10"));
        postList.add(post1);
        Region region = new Region(3L, "POL");
        User user = new User(3l, "Dima", "Matsech", null, null, Role.USER);
        return user;
    }

    public static User expectedDataOfUserGetById() {
        List<Post> postList = new ArrayList<>();
        Post post1 = new Post(1L, "Post 1 of Max", Timestamp.valueOf("2021-03-04 21:53:52.0"), Timestamp.valueOf("2021-03-04 21:53:52.0"));
        postList.add(post1);
        Region region = new Region(1L, "UKR");
        User user = new User(1l, "Max", "Hayday", postList, region, Role.USER);
        return user;
    }

    public List<User> expectedDataOfAllUsers() throws SQLException, IOException, ClassNotFoundException, ParseException {
        List users = new ArrayList<>();

        List<Post> postList = new ArrayList<>();
        Region region = new Region(1l, "UKR");
        User user = new User(1l, "Max", "Hayday", null, region, Role.USER);
        Post post1 = new Post(1l, "Post 1 of Max", Timestamp.valueOf("2021-03-04 21:53:52.0"), Timestamp.valueOf("2021-03-04 21:53:52.0"), user);
        postList.add(post1);
        user.setPosts(postList);
        users.add(user);

        postList = new ArrayList<>();
        region = new Region(2l, "IRL");
        user = new User(2l, "Ola", "Hayday", null, region, Role.USER);

        post1 = new Post(2l, "Post 1 of Ola", Timestamp.valueOf("2021-03-04 21:53:52.0"), Timestamp.valueOf("2021-03-04 21:53:52.0"), user);
        postList.add(post1);
        user.setPosts(postList);
        users.add(user);

        return users;
    }

}
