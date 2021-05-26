package web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;

@Controller
@RequestMapping("/admin")
public class CRUDcontroller {

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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPageInfo");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
