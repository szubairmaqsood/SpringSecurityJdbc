package com.example.springsecurityjdbc.Controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeResource {
    /*
     This url is for all users even not logged in once
     */
    @GetMapping("/")
    fun home():String{
        return "Home";
    }

    /*
     This url is for users and admin as well
     */
    @GetMapping("/user")
    fun user():String{
        return "user";
    }
    /*
     This url is for admin user only
     */
    @GetMapping("/admin")
    fun admin():String{
        return "admin";
    }
}