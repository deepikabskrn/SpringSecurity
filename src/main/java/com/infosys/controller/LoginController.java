package com.infosys.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.entity.User;

@RestController
public class LoginController {

    @RequestMapping(method=RequestMethod.GET)
    public String loginRequest(@RequestBody User user) {
    	
    	return null;
    }
}
