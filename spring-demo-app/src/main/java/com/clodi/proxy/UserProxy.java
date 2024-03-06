package com.clodi.proxy;

import com.clodi.config.Config;
import com.clodi.dto.SimpleUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Claudia Vidican
 */
@FeignClient(name = "user", url = "${name.registry.url}", configuration = Config.class) public interface UserProxy {

    @PostMapping("/user/registration") boolean registerUserAccount(@RequestBody SimpleUserDTO user);

    @GetMapping("/user/confirmRegistration") String confirmRegistration(@RequestParam("token") String token);

    @PostMapping("/user/logout") String logout();
}
