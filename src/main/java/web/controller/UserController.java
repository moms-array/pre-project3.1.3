package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    UserController(){
    }

    public UserService getUsers() {
        return userService;
    }

    @Autowired
    public void setUsers(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/")
    public ModelAndView getUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        modelAndView.addObject("users", userService.getListUser());
        return modelAndView;
    }


    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id){
        User user = userService.getById(id).get();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @PostMapping(value = "/edit/{id}")
    public ModelAndView editUser(@ModelAttribute("user") User user, @PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        user.setId(id);
        userService.edit(user);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setId(1L);
        modelAndView.setViewName("add");
        modelAndView.addObject(user);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addUser(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.add(user);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        User user = userService.getById(id).get();
        userService.delete(user);
        return modelAndView;
    }


}
