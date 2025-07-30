package com.example.userDB.repository;



import com.example.userDB.model.User;
import com.example.userDB.util.JsonDatabaseUtil;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final String DB_FILE = "users.json";

    public List<User> findAll() {
        return JsonDatabaseUtil.readUsers(DB_FILE);
    }

    public Optional<User> findByEmail(String email) {
        return findAll().stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public Optional<User> findByUsername(String username) {
        return findAll().stream().filter(u -> u.getUsername().equalsIgnoreCase(username)).findFirst();
    }

    public User save(User user) {
        List<User> users = findAll();
        user.setId((long) (users.size() + 1));
        users.add(user);
        JsonDatabaseUtil.writeUsers(DB_FILE, users);
        return user;
    }
}
