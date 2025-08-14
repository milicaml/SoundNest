package com.soundnest.repository;


import com.soundnest.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private final Map<Long, User> store = new HashMap<>();
    private long nextId = 1;

    public void setNextId(long id) {
        this.nextId = id;
    }

    public void save(User user) {
        if (user.getId() == null) {
            user.setId(nextId++);
        }
        store.put(user.getId(), user);
    }

    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(store.get(userId));
    }

    public Optional<User> findByUsername(String username) {
        return store.values().stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }


    public boolean deleteById(Long userId) {
        return store.remove(userId) != null;
    }
}