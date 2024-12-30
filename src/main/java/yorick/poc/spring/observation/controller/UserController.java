package yorick.poc.spring.observation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yorick.poc.spring.observation.service.UserService;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("")
    public UserService.UserVO getByName(@RequestParam("name")String name) throws Exception {
        log.info("get user by name:{}", name);
        return userService.getUserByName(name);
    }

}
