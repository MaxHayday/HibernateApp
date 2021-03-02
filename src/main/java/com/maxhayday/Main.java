package com.maxhayday;

import com.maxhayday.view.observer.MenuObserved;
import com.maxhayday.view.observer.PostViewObserver;
import com.maxhayday.view.observer.RegionViewObserver;
import com.maxhayday.view.observer.UserViewObserver;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException {
        MenuObserved menu = new MenuObserved();
        menu.addObserver(new PostViewObserver());
        menu.addObserver(new UserViewObserver());
        menu.addObserver(new RegionViewObserver());
        menu.showMenu();
    }
}
