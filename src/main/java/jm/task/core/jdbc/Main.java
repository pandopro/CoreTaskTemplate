package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Volodya", "Pupkin", (byte) 70);
        userService.saveUser("Anatoliy", "Kapustin", (byte) 10);
        userService.saveUser("Ilya", "Varlamov", (byte) 25);
        userService.saveUser("Maksim", "Kats", (byte) 40);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.getAllUsers();
        userService.dropUsersTable();

    }
}
