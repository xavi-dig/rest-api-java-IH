package com.example.demo.controllers.users;

import com.example.demo.models.DTOS.TransferThirdPartyDTO;
import com.example.demo.services.users.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyService thirdPartyService;

    @PatchMapping("/transfer-third-party")
    public void transferThirdParty(@RequestBody TransferThirdPartyDTO transferThirdPartyDTO, @RequestHeader String hashedKey) {
        thirdPartyService.transferThirdParty(transferThirdPartyDTO, hashedKey);
    }

}
