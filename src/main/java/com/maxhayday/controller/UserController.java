package com.maxhayday.controller;

import com.maxhayday.model.Post;
import com.maxhayday.model.Region;
import com.maxhayday.model.Role;
import com.maxhayday.model.User;
import com.maxhayday.service.PostService;
import com.maxhayday.service.RegionService;
import com.maxhayday.service.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private UserService userService;
    private PostService postService;
    private RegionService regionService;
    private User user;
    private Post post;
    private Region region;
    private List<User> userList;
    private List<Post> postList;

    public UserController() {
        try {
            userService = new UserService();
            postService = new PostService();
            regionService = new RegionService();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void save(Long id, String firstName, String lastName, List<String> posts, String regionStr, String roleStr) throws SQLException, IOException, ClassNotFoundException {
        postList = new ArrayList<>();

        region = new Region(id, regionStr);
        //region = Region.builder().id(id).name(regionStr).build();
        region = regionService.save(region);
//        user = User.builder().id(id)
//                .name(firstName)
//                .lastName(lastName)
//                .posts(null)
//                .region(region)
//                .role(checkRole(roleStr))
//                .build();
        user = new User(id, firstName, lastName, null, region, checkRole(roleStr));
        try {
            user = userService.save(user);
            for (String p :
                    posts) {
                post = new Post(id, p, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), user);
                //post = Post.builder().id(id).content(p).created(Timestamp.valueOf(LocalDateTime.now())).updated(Timestamp.valueOf(LocalDateTime.now())).user(user).build();
                postService.save(post);
                postList.add(post);
            }
        } catch (IOException | ParseException e) {
            System.out.println("Something went wrong...");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        userList = new ArrayList<>();
        try {
            userList = userService.getAll();
            return userList;
        } catch (IOException | ParseException | SQLException | ClassNotFoundException e) {
            System.out.println("You haven`t users.");
        }
        return null;
    }

    public void update(Long id, String firstName, String lastName, List<String> posts, String regionStr, String roleStr) {
        user = null;
        try {
            user = userService.getById(id);
            region = user.getRegion();
            postList = user.getPosts();
            user = new User(id,firstName,lastName,postList,region,checkRole(roleStr));
//            user = User.builder().id(id)
//                    .name(firstName)
//                    .lastName(lastName)
//                    .posts(postList)
//                    .region(region)
//                    .role(checkRole(roleStr))
//                    .build();
            userService.update(user);
        } catch (IOException | ParseException | SQLException | ClassNotFoundException e) {
            System.out.println("Something went wrong...");
        }
    }

    public void deleteById(Long id) {
        try {
            user = userService.getById(id);
            userService.deleteById(id);
        } catch (IOException | SQLException | ClassNotFoundException | ParseException e) {
            System.out.println("Wrong id.");
        }
    }

    public User getById(Long id) {
        try {
            return userService.getById(id);
        } catch (IOException | ParseException | SQLException | ClassNotFoundException e) {
            System.out.println("Wrong id.");
        }
        return null;
    }

    private Role checkRole(String roleStr) {
        if (roleStr.equals("USER") || roleStr.equals("1")) {
            return Role.USER;
        } else if (roleStr.equals("ADMIN") || roleStr.equals("2")) {
            return Role.ADMIN;
        } else if (roleStr.equals("MODERATOR") || roleStr.equals("3")) {
            return Role.MODERATOR;
        }
        return null;
    }
}
