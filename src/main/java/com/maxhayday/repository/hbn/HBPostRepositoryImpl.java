package com.maxhayday.repository.hbn;

import com.maxhayday.Connection;
import com.maxhayday.model.Post;
import com.maxhayday.repository.PostRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HBPostRepositoryImpl implements PostRepository {
    private Post postTmp = null;
    private List<Post> postList = null;
    private Transaction transaction = null;

    @Override
    public Post getById(Long id) throws IOException, ParseException, ClassNotFoundException, SQLException {
        try (Session session = Connection.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            postTmp = session.get(Post.class, id);
            transaction.commit();
        }
        return postTmp;
    }

    @Override
    public Post save(Post post) throws IOException, SQLException, ClassNotFoundException {
        try (Session session = Connection.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long idOfPost = (Long) session.save(post);
            transaction.commit();
            postTmp = session.get(Post.class, idOfPost);
        }
        return postTmp;
    }

    @Override
    public List<Post> getAll() throws IOException, ParseException, ClassNotFoundException, SQLException {
        try (Session session = Connection.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            postList = new ArrayList<>();
            postList = (List<Post>) session.createQuery("From Post").list();

        }
        return postList;
    }

    @Override
    public List<Post> getPostListOfUserId(Long id) throws ClassNotFoundException, SQLException, IOException, ParseException {
        postList = new ArrayList<>();
        postList = getAll();
        List<Post> postListOfUserId = new ArrayList<>();
        for (Post p :
                postList) {
            if (p.getUser().getId() == id) {
                postListOfUserId.add(p);
            }
        }
        return postList;
    }

    @Override
    public Post update(Post post) throws ClassNotFoundException, SQLException {
        try (Session session = Connection.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            postTmp = session.get(Post.class, post.getId());
            postTmp.setContent(post.getContent());
            postTmp.setCreated(post.getCreated());
            postTmp.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
            session.update(postTmp);
            post = session.get(Post.class, post.getId());
            transaction.commit();
        }
        return post;
    }

    @Override
    public void deleteById(Long id) throws IOException, ClassNotFoundException, SQLException {
        try (Session session = Connection.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            postTmp = session.get(Post.class, id);
            session.delete(postTmp);
            transaction.commit();
        }
    }
}
