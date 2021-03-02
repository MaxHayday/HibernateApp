package com.maxhayday;

import com.maxhayday.model.Post;
import com.maxhayday.model.Region;
import com.maxhayday.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Connection {
    public static SessionFactory sessionFactory;
    public static Configuration configuration;
    private static ServiceRegistry serviceRegistry;


    public static void buildSession() {
        configuration = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Post.class)
                .addAnnotatedClass(Region.class);
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void closeSession() {
        sessionFactory.close();
    }
}
