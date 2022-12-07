package com.example.demo.controllers.users;

import com.example.demo.repositories.user.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminRepository adminRepository;


}
