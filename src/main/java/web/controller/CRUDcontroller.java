package web.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.List;

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
    @GetMapping(value = "/adminPage")
    public ModelAndView listUsers(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/adminPage");
        return modelAndView;
    }

    @GetMapping("/findOne/{id}")
    @ResponseBody
    public ResponseEntity<User> finOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/edit")
    @ResponseBody
    public ResponseEntity<User> editUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity<User> addUser(@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @GetMapping(value = "/adminPageInfo")
    public ModelAndView getUserPage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user =  userService.findByUserName(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPageInfo");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/userList")
    @ResponseBody
    public List<User> userList(){
        return userService.userList();
    }
}
