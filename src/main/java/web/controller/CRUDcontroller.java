package web.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/admin")
@Log
public class CRUDcontroller {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    CRUDcontroller(){
    }
    @GetMapping(value = "/users")
    public ModelAndView listUsers(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users");
        modelAndView.addObject("users", userService.userList());
        return modelAndView;
    }

    @GetMapping("/findOne")
    @ResponseBody
    public User finOne(Long id){
        return userService.findUserById(id);
    }


    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") Long id){
        User user = userService.findUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage.html");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @PatchMapping(value = "/edit")
    public ModelAndView editUser(@ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/users");
        user.setRoles(userService.findUserById(user.getId()).getRoles());
        userService.saveUser(user);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addUser(@RequestParam("addname") String username,
                                @RequestParam("addlastname") String lastname,
                                @RequestParam("addemail") String email,
                                @RequestParam("addpassword") String password){
        User user = new User();
        user.setUsername(username);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/users");
        userService.addUser(user);
        return modelAndView;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/users");
        userService.deleteUser(id);
        return modelAndView;
    }


}
