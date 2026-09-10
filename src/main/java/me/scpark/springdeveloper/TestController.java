package me.scpark.springdeveloper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hi")
    public String hi() {
        return "你好?是'http://localhost:8080/hi'这里";
    }

    @GetMapping("/test")
    public String test() {
        return "你好?现在是是'http://localhost:8080/test'这里";
    }

    @PostMapping("/lhi")
    public String fhi() {
        return "你好?是打屁股'http://localhost:8080/hi'这里";
    }

    @PutMapping("/ltest")
    public String puttest() {
        return "你好?是'http://localhost:8080/puttest'哪里";
    }

    @GetMapping("/member")
    public String member() {

    }
}