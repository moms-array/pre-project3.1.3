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
//    @ResponseBody - зачем?+
//    userList() не использует ResponseEntity+
//    разделить контроллеры на Rest и обычный+
//    GenerationType.AUTO заменить на Identity+
//    UserRepository userRepository; - использовать модификатор
//
//
//    сделать вот так:
//    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();+
//
//    зачем вообще нужны true/false при апдейте пользователя? (в saveUser методе сервиса и метод
//    лучше переименовать на update, а то по названию не совсем понятно, что там происходит+

}
