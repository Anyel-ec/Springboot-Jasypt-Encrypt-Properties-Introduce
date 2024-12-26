package top.anyel.jasypt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 26/12/2024
 */
@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Value("${secretparam}")
    private String secretParam;

    @GetMapping("/test")
    public String test() {
        return secretParam;
    }
}
