package yorick.poc.spring.observation.service;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import yorick.poc.spring.observation.repository.UserMapper;
import yorick.poc.spring.observation.repository.UserWalletMapper;

import java.net.URI;

@Service
@Slf4j
public class UserService {

    public record UserVO (Long id, String name , int age){
        UserVO(UserMapper.UserPO userPO){
            this(userPO.id(), userPO.name(), userPO.age());
        }
    }

    private final UserMapper userMapper;
    private final UserWalletMapper userWalletMapper;
    private final RestTemplate restTemplate;

    public UserService(UserMapper userMapper, UserWalletMapper userWalletMapper, RestTemplate restTemplate){
        this.userMapper = userMapper;
        this.userWalletMapper = userWalletMapper;
        this.restTemplate = restTemplate;
    }


    public UserVO getUserByName(@NotNull String name) throws Exception {

        UserMapper.UserPO user = userMapper.selectUserByName(name);

        if(user == null){
            throw new Exception("user not found");
        }
        URI uri = URI.create("http://localhost:8080/wallet");
        URI target = UriComponentsBuilder.newInstance().uri(uri).queryParam("userId", user.id()).build().toUri();

        ResponseEntity<Boolean> result = restTemplate.getForEntity(target, Boolean.class);
        log.info("getUserByName {}", result.getStatusCode());

        return new UserVO(user);

    }

    public boolean isUserWalletExist(String userId){
        UserWalletMapper.UserWalletPO userWallet = userWalletMapper.selectWalletById(userId);
        return userWallet != null;
    }
}
