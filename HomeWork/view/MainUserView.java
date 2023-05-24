package view;

import controller.UserController;
import doa.impl.FileOperation;
import model.User;
import repository.GBRepository;
import repository.impl.UserRepository;
import util.Commands;
import util.DatabaseSeeder;


import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static util.DBController.clearDataFile;

public class MainUserView implements Commander, ActionPrompt {

    private final UserController userController;

    public MainUserView(UserController userController) {
        this.userController = userController;
    }

    @Override
    public void run() {

        Commands commands;
        System.out.println();
        while (true) {
            String command = prompt("Введите команду: ");
            try {
                commands = Commands.valueOf(command);
                if (commands == Commands.EXIT) return;
                switch (commands) {
                    case CREATE -> {
                        String firstName = prompt("Имя: ");
                        String lastName = prompt("Фамилия: ");
                        String phone = prompt("Номер телефона: ");
                        Date date = new Date();
                        User user = new User(firstName, lastName, phone);
                        userController.saveUser(user);
                        System.out.println("Пользователь успешно создан.");
                    }
                    case READ -> {
                        String id = prompt("Идентификатор пользователя: ");
                        try {
                            User user = userController.readUser(Long.parseLong(id));
                            System.out.println(user);
                            System.out.println();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case UPDATE -> {
                        String id = prompt("Идентификатор пользователя: ");
                        try {
                            User user = userController.readUser(Long.parseLong(id));
                            if (user != null) {
                                String newFirstName = prompt("Новое имя: ");
                                String newLastName = prompt("Новая фамилия: ");
                                String newPhone = prompt("Новый номер телефона: ");
                                user.setFirstName(newFirstName);
                                user.setLastName(newLastName);
                                user.setPhone(newPhone);
                                userController.updateUser(user);
                                System.out.println("Пользователь обновлен: " + user);
                                System.out.println();
                            } else {
                                System.out.println("Пользователь с указанным идентификатором не найден.");
                                System.out.println();
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case DELETE -> {
                        String id = prompt("Идентификатор пользователя: ");
                        try {
                            User user = userController.readUser(Long.parseLong(id));
                            if (user != null) {
                                userController.deleteUser(user.getId());
                                System.out.println("Пользователь удален: " + user);
                                System.out.println();
                            } else {
                                System.out.println("Пользователь с указанным идентификатором не найден.");
                                System.out.println();
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case DELETE_THIS_FAIL -> {
                        Scanner flag = new Scanner(System.in);
                        System.out.println("Действительно хотите осистить фаил? нажмите -> 0");
                        if (flag.nextInt() == 0) {
                            clearDataFile();

                        }
                    }
                    case READ_ALL -> {
                        List<User> users = userController.getAllUsers();
                        for (User user : users) {
                            System.out.println(user);
                            System.out.println();
                        }
                    }
                    case CREATE_RANDOM -> {
                        final String DB_PATH = "db.txt";
                        FileOperation fileOperation = new FileOperation(DB_PATH);
                        GBRepository<User, Long> userRepository = new UserRepository(fileOperation);
                        DatabaseSeeder databaseSeeder = new DatabaseSeeder(userRepository);
                        databaseSeeder.seedData(100);
                    }
                    default -> {
                        System.out.println("Неверная команда. Пожалуйста, введите корректную команду.");
                        showConsoleListMenu();
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Неверная команда. Пожалуйста, введите корректную команду.");
                showConsoleListMenu();
            }
        }
    }

    @Override
    public void showConsoleListMenu() {
        String create = "CREATE";
        String read = "READ";
        String readAll = "READ_ALL";
        String upDate = "UPDATE";
        String delete = "DELETE";
        String clearFile = "DELETE_THIS_FAIL";
        String exit = "EXIT";
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                create,
                read,
                readAll,
                upDate,
                delete,
                clearFile,
                exit);
    }

    @Override
    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}


