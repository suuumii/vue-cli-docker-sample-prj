package net.asdevs.myhomegc2.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


// @RequestMapping(value = "/crypt", method = RequestMethod.POST)
@ResponseBody
@Controller
public class LoginController {
    @GetMapping("/api/test")
    public String test(){
        return "認証が成功しています";
    }
    @RequestMapping(value = "/api/crypt", method = RequestMethod.POST)
    public String crypt(@RequestBody String body) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(body);
    }
}