package com.maxhayday.repository.hbn;

import com.maxhayday.Connection;
import com.maxhayday.model.Post;
import com.maxhayday.model.Region;
import com.maxhayday.model.Role;
import com.maxhayday.model.User;
import com.maxhayday.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HBUserRepositoryImpl implements UserRepository {
    private User userTmp = null;
    private List<User> userList = null;
    private Transaction transaction = null;
    private HBRegionRepositoryImpl regionRepository = new HBRegionRepositoryImpl();
    private HBPostRepositoryImpl postRepository = new HBPostRepositoryImpl();


    @Override
    public User getById(Long id) throws IOException, ParseException, ClassNotFoundException, SQLException {
        try (Session session = Connection.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            userTmp = session.get(User.class, id);
            transaction.commit();
        }
        return userTmp;
    }

    @Override
    public User save(User user) throws IOException, ParseException, ClassNotFoundException, SQLException {
        try (Session session = Connection.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long idOfUser = (Long) session.save(user);
            transaction.commit();
            userTmp = session.get(User.class, idOfUser);
        }
        return userTmp;
    }

    @Override
    public User update(User user) throws IOException, ParseException, ClassNotFoundException, SQLException {
        try (Session session = Connection.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            userTmp = session.get(User.class, user.getId());
            userTmp.setName(user.getName());
            userTmp.setLastName(user.getLastName());
            userTmp.setRole(user.getRole());
            userTmp.setRegion(user.getRegion());
            session.update(userTmp);
            userTmp = session.get(User.class, user.getId());
            transaction.commit();
        }
        return userTmp;
    }

    @Override
    public List<User> getAll() throws IOException, ParseException, ClassNotFoundException, SQLException {
        try (Session session = Connection.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            userList = new ArrayList<>();
            userList = (List) session.createQuery("FROM User u LEFT JOIN FETCH u.posts left join fetch u.region order by u.id ASC").list();
            transaction.commit();
        }
        return userList.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) throws IOException, ClassNotFoundException, SQLException {
        try (Session session = Connection.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            userTmp = session.get(User.class, id);
            session.delete(userTmp);
            transaction.commit();
        }
    }

    public void insertBasicDataInToTables() throws ClassNotFoundException, SQLException, ParseException, IOException {
        List users = new ArrayList<>();
        List postList = new ArrayList();
        Region region = new Region(1l, "UKR");
        regionRepository.save(region);
        User user = new User(1l, "Max", "Hayday", null, region, Role.USER);
        save(user);
        Post post1 = new Post(1l, "Post 1 of Max", Timestamp.valueOf("2021-01-03 8:43:10"), Timestamp.valueOf("2021-01-03 8:43:10"), user);
        postRepository.save(post1);
        postList.add(post1);
        users.add(user);

        postList = new ArrayList<>();
        region = new Region(2l, "IRL");
        regionRepository.save(region);
        user = new User(2l, "Ola", "Hayday", null, region, Role.USER);
        save(user);

        post1 = new Post(2l, "Post 1 of Ola", Timestamp.valueOf("2021-01-03 8:43:10"), Timestamp.valueOf("2021-01-03 8:43:10"), user);
        postRepository.save(post1);
        postList.add(post1);
        users.add(user);

    }
}
