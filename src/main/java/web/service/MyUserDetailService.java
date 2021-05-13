package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;
import web.repository.RoleRepository;
import web.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class MyUserDetailService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("not found ");
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getAuthorities());

        return userDetails;
    }

    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

    public boolean saveUser(User user){
        User userFromBd = null;
        if(userRepository.findById(user.getId()).isPresent()) {
            userFromBd = userRepository.findById(user.getId()).get();
        }
        if(userFromBd != null){
            return false;
        }
        user.setRoles(Collections.singleton(new Role(2L,"USER")));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> userList(){
        return em.createQuery("SELECT u from User u ",User.class).getResultList();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public User findByUsername(String name){
        return userRepository.findByUsername(name);
    }
}
