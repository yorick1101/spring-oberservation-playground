package yorick.poc.spring.observation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yorick.poc.spring.observation.service.UserService;


@RestController
@RequestMapping("/wallet")
@Slf4j
public class UserWalletController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public boolean getByName(@RequestParam("userId")String userId) throws Exception {
        log.info("get user wallet by id:{}", userId);
        return userService.isUserWalletExist(userId);
    }

}
