package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DTO.UserDTO;
import web.model.User;
import web.repository.UserRepository;
import web.utils.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MappingUtils mappingUtils;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MappingUtils mappingUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mappingUtils = mappingUtils;
    }

    @Transactional
    public UserDTO findUserById(Long id){
        return mappingUtils.userToUserDto(userRepository.findById(id).get());
    }

    @Override
    public void update(UserDTO userDTO){
        User user = mappingUtils.userDtoToUser(userDTO);
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
    public List<UserDTO> userList(){
        return userRepository.findAll().stream().map(mappingUtils::userToUserDto).collect(Collectors.toList());
    }

    @Override
    public void addUser(UserDTO userDTO){
        User user = mappingUtils.userDtoToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUserName(String name){
        return userRepository.findByUsername(name);
    }
}
