package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class authController {

    authController(){
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "/auth/login";
    }


}
