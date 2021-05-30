package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

    @Override
    public void update(User user){
        User userFromBd = null;
        if(userRepository.findById(user.getId()).isPresent()) {
            userFromBd = userRepository.findById(user.getId()).get();
        }
        if(userFromBd == null){
            return;
        }
        if (!userFromBd.getRoles().containsAll(user.getRoles())){
            user.getRoles().addAll(userFromBd.getRoles());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
    }

    @Override
    public List<User> userList(){
        return userRepository.test();
    }

    @Override
    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUserName(String name){
        return userRepository.findByUsername(name);
    }
}
