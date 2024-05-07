package com.clodi.proxy;

import com.clodi.config.SecurityConfig;
import com.clodi.dto.SimpleUserDTO;
import com.clodi.model.SimpleUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "${name.registry.url}", configuration = SecurityConfig.class) public interface UserProxy {

    @PostMapping("/user/registration") boolean registerUserAccount(@RequestBody SimpleUserDTO user);

    @GetMapping("/user/confirmRegistration") String confirmRegistration(@RequestParam("token") String token);

    @PostMapping("/user/logout") String logout();

    @GetMapping("/user/user") SimpleUser getUser(@RequestParam("username") String username);
}
