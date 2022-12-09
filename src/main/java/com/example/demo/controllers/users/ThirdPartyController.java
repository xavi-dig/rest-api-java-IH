package com.example.demo.controllers.users;

import com.example.demo.services.users.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyService thirdPartyService;


}
