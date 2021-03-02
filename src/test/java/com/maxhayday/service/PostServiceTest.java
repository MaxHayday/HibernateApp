package com.maxhayday.service;

import com.maxhayday.Connection;
import com.maxhayday.model.Post;
import com.maxhayday.repository.PostRepository;
import com.maxhayday.repository.hbn.HBPostRepositoryImpl;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.maxhayday.data.PostTestData.*;
import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {
    private PostRepository postRepository;
    private PostService postService;
    private Transaction transaction = null;

    public PostServiceTest() throws SQLException, IOException, ClassNotFoundException {
        postRepository = new HBPostRepositoryImpl();
        postService = new PostService();
    }

    @BeforeAll
    static void setUp()  {
        Connection.buildSession();
    }

    @Test
    void getById() throws ClassNotFoundException, SQLException, ParseException, IOException {
            Post post = postService.getById(1l);
            assertEquals(expectedDataOfPostGetById(), post);
    }

    @Test
    void save() throws SQLException, IOException, ClassNotFoundException {
            Post post = postService.save(testDataOfPostForCreating());
            assertEquals(expectedCreatedDataOfPost(), post);
    }

    @Test
    void getAll() throws ClassNotFoundException, SQLException, ParseException, IOException {
            List<Post> postList = postService.getAll();
            assertEquals(expectedDataOfAllPosts(), postList);
    }

    @Test
    void deleteById() throws SQLException, IOException, ClassNotFoundException, ParseException {
            postService.deleteById(1l);
            assertNull(postService.getById(1l));
    }

    @Test
    void update() throws SQLException, ClassNotFoundException {
            Post post = postService.update(testDataOfPostForUpdating());
            assertEquals(testDataOfPostForUpdating(), post);
    }
    @AfterAll
    public static void tearDown() {
        if (Connection.sessionFactory != null) Connection.sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }
}