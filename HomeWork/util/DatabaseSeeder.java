package util;

import model.User;
import repository.GBRepository;

import java.util.Date;
import java.util.Random;

public class DatabaseSeeder {
    private final GBRepository<User, Long> repository;

    public DatabaseSeeder(GBRepository<User, Long> repository) {
        this.repository = repository;
    }

    public void seedData(int numberOfUsers) {
        for (int i = 0; i < numberOfUsers; i++) {
            User user = generateRandomUser();
            repository.create(user);
        }
        System.out.println("Database seeded successfully with " + numberOfUsers + " users.");
    }

    private User generateRandomUser() {
        Random random = new Random();
        String firstName = generateRandomString(5);
        String lastName = generateRandomString(8);
        String phone = generateRandomPhoneNumber();
        return new User(firstName, lastName, phone);
    }

    private String generateRandomString(int length) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    private String generateRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
