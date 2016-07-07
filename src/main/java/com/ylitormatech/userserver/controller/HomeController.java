package com.ylitormatech.userserver.controller;

import com.ylitormatech.userserver.domain.service.UserService;
import com.ylitormatech.userserver.web.UserSingUp;
import com.ylitormatech.userserver.web.WwwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Perttu Vanharanta on 6.7.2016.
 */
@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/singup", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpStatus singUp(@RequestBody @Valid UserSingUp userSingUp, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return HttpStatus.BAD_REQUEST;
        }
        userService.singup(new WwwUser(null, userSingUp.getUsername(), userSingUp.getPassword(), userSingUp.getEmail(), "ROLE_USER"));
        return HttpStatus.OK;
    }


    @RequestMapping("/user/message")
    @ResponseBody
    public String getTrustedUserMessage(Principal principal) {
        WwwUser wwwUser = userService.getUser(principal.getName());
        return "Hello, Trusted User" + (principal != null ? " " + principal.getName() + " id:" +wwwUser.getId(): "");
    }

}
