package com.coding.challenge.userproj.controller;

import com.coding.challenge.userproj.model.UserDetails;
import com.coding.challenge.userproj.service.UserApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApplicationController {

    @Autowired
    private UserApplicationService userApplicationService;

    @GetMapping("/getUserDetails/{id}")
    public UserDetails getUserDetails(@PathVariable("id") int id) {
        return userApplicationService.fetchUserDetails(id);
    }

    @PutMapping("/updateUserDetails/{id}")
    public UserDetails updateUserDetails(@PathVariable("id") int id, @RequestBody UserDetails userDetails) {
        return userApplicationService.updateUserDetails(id, userDetails);
    }

}
