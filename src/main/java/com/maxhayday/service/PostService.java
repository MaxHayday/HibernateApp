package com.maxhayday.service;

import com.maxhayday.model.Post;
import com.maxhayday.repository.PostRepository;
import com.maxhayday.repository.hbn.HBPostRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class PostService {
    private final PostRepository postRepository;
    private Post post;

    public PostService() throws SQLException, IOException, ClassNotFoundException {
        this.postRepository = new HBPostRepositoryImpl();
    }

    public Post getById(Long id) throws IOException, ParseException, SQLException, ClassNotFoundException {
        post = postRepository.getById(id);
        return post;
    }

    public Post save(Post post) throws IOException, SQLException, ClassNotFoundException {
        post = postRepository.save(post);
        return post;
    }

    public List<Post> getAll() throws IOException, ParseException, SQLException, ClassNotFoundException {
        List<Post> postList = postRepository.getAll();
        return postList;
    }

    public void deleteById(Long id) throws IOException, SQLException, ClassNotFoundException {
        postRepository.deleteById(id);
    }

    public Post update(Post post) throws SQLException, ClassNotFoundException {
        post = postRepository.update(post);
        return post;
    }
}
