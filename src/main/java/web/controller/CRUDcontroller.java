package web.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

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
        modelAndView.addObject("users", userService.userList());
        return modelAndView;
    }

    @GetMapping("/findOne")
    @ResponseBody
    public User finOne(Long id){
        return userService.findUserById(id);
    }


    @GetMapping(value = "/edit/{id}")
    public User editPage(@PathVariable("id") Long id){
        return userService.findUserById(id);
    }

    @PatchMapping(value = "/edit")
    public ModelAndView editUser(@ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/adminPage");
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
        modelAndView.setViewName("redirect:/admin/adminPage");
        userService.addUser(user);
        return modelAndView;
    }

    @DeleteMapping(value = "/delete")
    public ModelAndView deleteUser(@RequestParam("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/adminPage");
        userService.deleteUser(id);
        return modelAndView;
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
}
