package com.maxhayday.service;

import com.maxhayday.Connection;
import com.maxhayday.data.UserTestData;
import com.maxhayday.model.User;
import com.maxhayday.repository.UserRepository;
import com.maxhayday.repository.hbn.HBUserRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.maxhayday.data.UserTestData.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;
    private RegionService regionService;
    private Transaction transaction = null;
    private UserTestData userTestData;

    public UserServiceTest() throws SQLException, IOException, ClassNotFoundException, ParseException {
        userService = new UserService();
        regionService = new RegionService();
        userTestData = new UserTestData();
    }

    @BeforeAll
    static void setUp(){
        Connection.buildSession();
    }

    @Test
    void getById() throws ClassNotFoundException, SQLException, ParseException, IOException {
        User user = userService.getById(1L);
        assertEquals(expectedDataOfUserGetById(), user);
    }

    @Test
    void save() throws ClassNotFoundException, SQLException, ParseException, IOException {
        regionService.save(testDataOfUserForCreating().getRegion());
        User createdUser = userService.save(testDataOfUserForCreating());
        assertEquals(testDataOfUserForCreating(), createdUser);
    }

    @Test
    void update() throws ClassNotFoundException, SQLException, ParseException, IOException {
        User updatedUser = userService.update(testDataOfUserForUpdating());
        assertEquals(testDataOfUserForUpdating(), updatedUser);
    }

    @Test
    void getAll() throws ClassNotFoundException, SQLException, ParseException, IOException {
        List<User> users = userService.getAll();
        assertEquals(userTestData.expectedDataOfAllUsers(), new ArrayList<>(users));
    }

    @Test
    void deleteById() throws SQLException, IOException, ClassNotFoundException, ParseException {
        userService.deleteById(1l);
        assertNull(userService.getById(1L));
    }

    @AfterAll
    public static void tearDown() {
        if (Connection.sessionFactory != null) Connection.sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }
}