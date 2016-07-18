package com.ylitormatech.userserver.controller;

import com.ylitormatech.userserver.domain.service.UserService;
import com.ylitormatech.userserver.web.UserSignUp;
import com.ylitormatech.userserver.web.WwwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> userSignUp(@RequestBody @Valid UserSignUp userSignUp, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            if(bindingResult.getFieldError("email")!=null) {
                if (bindingResult.getFieldError("email").getCode().equals("Email")) {
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                            .body("{\"error\":\"auth.singup.error.invalid.email\"}");
                }
            }
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("{\"error\":\"auth.singup.error.missing.value\"}");
        }
        if(userService.getIsUserExist(userSignUp.getUsername())) {
            userService.singup(new WwwUser(null, userSignUp.getUsername(), userSignUp.getPassword(), userSignUp.getEmail(), "ROLE_USER"));
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body("{\"error\":\"auth.singup.error.username.exist\"}");
    }


    @RequestMapping("/user/id")
    @ResponseBody
    public ResponseEntity<String> getTrustedUserMessage(Principal principal) {
        WwwUser wwwUser = userService.getUser(principal.getName());
        return ResponseEntity.ok("{\"id\":\"" +wwwUser.getId()+"\"}");
    }

}
