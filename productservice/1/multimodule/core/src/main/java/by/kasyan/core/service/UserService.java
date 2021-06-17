package by.kasyan.core.service;

import by.kasyan.core.bean.User;
import by.kasyan.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save (String name){
        User user = new User();
        user.setName(name);
        userRepository.save(user);
    }

    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
