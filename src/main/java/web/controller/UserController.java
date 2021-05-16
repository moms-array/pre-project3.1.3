package web.controller;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.MyUserDetailService;


@RestController
@RequestMapping("/user")
@Log
public class UserController {

    @Autowired
    private MyUserDetailService userService;

    @GetMapping(value = "/userPage")
    public ModelAndView getUserPage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user =  userService.findByUsername(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userPage");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
