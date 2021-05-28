package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user){
        userRepository.save(user);
    }

    @Override
    public User findByUserName(String name){
        return userRepository.findByUsername(name);
    }
}
