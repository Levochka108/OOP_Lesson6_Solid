package controller;

import model.User;
import repository.GBRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserController implements UserSaver, UserReader, UserUpdate, UserShowInfo, UserClean {
    private final GBRepository<User, Long> repository;

    public UserController(GBRepository<User, Long> repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        user.setCreationDateTime(new Date());
        repository.create(user);
    }

    @Override
    public void updateUser(User user) {
        Optional<User> optionalUser = repository.update(user.getId(), user);
        if (optionalUser.isPresent()) {
            System.out.println("User updated: " + optionalUser.get());
        } else {
            System.out.println("Failed to update user.");
        }
    }
    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }
        throw new RuntimeException("User not found");
    }
    public void deleteUser(Long userId) {
        boolean deleted = repository.delete(userId);
        if (deleted) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user.");
        }
    }
}
