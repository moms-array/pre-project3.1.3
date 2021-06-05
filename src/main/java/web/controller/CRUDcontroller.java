package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.DTO.UserDTO;
import web.model.User;
import web.service.UserService;
import web.utils.MappingUtils;

@Controller
@RequestMapping("/admin")
public class CRUDcontroller {

    UserService userService;
    MappingUtils mappingUtils;

    @Autowired
    CRUDcontroller(MappingUtils mappingUtils, UserService userService){
        this.mappingUtils = mappingUtils;
        this.userService = userService;
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
        UserDTO userDTO =  mappingUtils.userToUserDto(userService.findByUserName(auth.getName()));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPageInfo");
        modelAndView.addObject("userDTO", userDTO);
        return modelAndView;
    }
}
