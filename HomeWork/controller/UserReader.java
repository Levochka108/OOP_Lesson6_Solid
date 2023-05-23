package controller;

import model.User;

public interface UserReader {
    User readUser(Long userId) throws Exception;
}
