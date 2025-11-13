package com.auto.carsales.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
    @GetMapping("/info")
    public String aboutMachine(){
      return  System.getProperty("os.name") + " "+ System.getProperty("os.arch")+ "\n"+System.getProperty("java.version") +"\n"+System.getProperty("user.name");
    }
}
