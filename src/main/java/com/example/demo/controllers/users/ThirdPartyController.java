package com.example.demo.controllers.users;

import com.example.demo.repositories.user.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;


}
