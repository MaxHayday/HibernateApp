package com.maxhayday.controller;

import com.maxhayday.model.Post;
import com.maxhayday.service.PostService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

public class PostController {
    private PostService postService;
    private Post post;

    public PostController() {
        try {
            postService = new PostService();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save(Long id, String content) {
        post = new Post(id, content, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        //post = Post.builder().id(id).content(content).created(Timestamp.valueOf(LocalDateTime.now())).updated(Timestamp.valueOf(LocalDateTime.now())).build();
        try {
            postService.save(post);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("You wrote wrong information.");
        }
    }

    public List<Post> getAll() {
        try {
            return postService.getAll();
        } catch (IOException | ParseException | SQLException | ClassNotFoundException e) {
            System.out.println("You haven`t posts.");
        }
        return null;
    }

    public void update(Long id, String content, Post post) {
        post = new Post(id, content, post.getCreated(), Timestamp.valueOf(LocalDateTime.now()));

        //post = Post.builder().id(id).content(content).created(post.getCreated()).updated(Timestamp.valueOf(LocalDateTime.now())).build();
        try {
            postService.update(post);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Long id) {
        try {
            postService.deleteById(id);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("Wrong id.");
        }
    }

    public Post getById(Long id) {
        try {
            return postService.getById(id);
        } catch (IOException | ParseException | SQLException | ClassNotFoundException e) {
            System.out.println("Wrong id.");
        }
        return null;
    }

    public void deleteByUserID(Long id) {
        try {
            postService.deleteById(id);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("Wrong id.");
        }
    }
}
