package web.controller;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.DTO.UserDTO;
import web.service.UserService;
import web.utils.MappingUtils;


@Controller
@RequestMapping("/user")
@Log
public class UserController {

    private UserService userService;
    private MappingUtils mappingUtils;

    @Autowired
    public UserController(UserService userService, MappingUtils mappingUtils) {
        this.userService = userService;
        this.mappingUtils = mappingUtils;
    }

    @GetMapping(value = "/userPage")
    public ModelAndView getUserPage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO =  mappingUtils.userToUserDto(userService.findByUserName(auth.getName()));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userPage");
        modelAndView.addObject("userDTO", userDTO);
        return modelAndView;
    }
}
