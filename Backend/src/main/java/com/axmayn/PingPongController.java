package com.axmayn;
import org.springframework.web.bind.annotation.*;

@RestController
public class PingPongController{

    record PingPong(String result){};

    @GetMapping("/ping")
    PingPong getPingPong(){
        return new PingPong("Pong");
    }

}