package ru.bojark.springauthservice.repository;

import org.springframework.stereotype.Repository;
import ru.bojark.springauthservice.domain.User;
import ru.bojark.springauthservice.misc.Authorities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<User, List<Authorities>> users = new ConcurrentHashMap<>();

    {
        users.put(new User("user", "password"), List.of(Authorities.WRITE, Authorities.READ));
        users.put(new User("admin", "admin"), List.of(Authorities.values()));
        users.put(new User("user1", "qwerty"), List.of(Authorities.READ));
    }

    public List<Authorities> getUserAuthorities(String name, String password) {
        for (User user : users.keySet()) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                return users.get(user);
            }
        }
        return new ArrayList<>();
    }

    public Set<User> getUsers() {
        return users.keySet();
    }
}
