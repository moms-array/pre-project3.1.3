package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.DTO.UserDTO;
import web.model.User;
import web.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<UserDTO> finOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<UserDTO> editUser(@RequestBody UserDTO userDTO){
        userService.update(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/userList")
    public ResponseEntity<List<UserDTO>> userList(){
        return new ResponseEntity<>(userService.userList(), HttpStatus.OK);
    }
}
