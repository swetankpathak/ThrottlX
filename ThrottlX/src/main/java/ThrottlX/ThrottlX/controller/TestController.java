package ThrottlX.ThrottlX.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/data")
    public String getData(@RequestHeader("X-User-Id") String userId) {
        return "Request allowed for user: " + userId;
    }
}
