package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/admin")
public class CRUDcontroller {
    @Autowired
    UserService userService;

    CRUDcontroller(){
    }
    @GetMapping(value = "/adminPage")
    public ModelAndView listUsers(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/adminPage");
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
