package com.cy.jt.auth.web.controller;

import com.cy.jt.common.domain.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class AuthController {

     //@Autowired
     //private RemoteUserService remoteUserService;
     @PostMapping("login")
     public JsonResult doLogin(String username, String password){
         //...
         System.out.println(username+"/"+password);
         return new JsonResult("login ok");
     }
}
