package org.example.game_tournement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.game_tournement.dao.IUserRepository;
import org.example.game_tournement.Entity.User;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserRepository iUserRepository;

    public List<User> read() {
        return iUserRepository.findAll();
    }

    public User getUserByName(String username) { // To be deprecated
        return null;
    }

    public User createStudent(User user) {
        return iUserRepository.save(user);
    }

}