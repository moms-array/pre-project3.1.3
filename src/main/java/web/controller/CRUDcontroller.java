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

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") Long id){
        User user = userService.findUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage.html");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    @Transactional
    public ModelAndView editUser(@ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/users");
        user.setRoles(userService.findUserById(user.getId()).getRoles());
        userService.saveUser(user);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.setViewName("addUser");
        modelAndView.addObject(user);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addUser(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/users");
        userService.addUser(user);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/users");
        User user = userService.findUserById(id);
        userService.deleteUser(user.getId());
        return modelAndView;
    }


}
