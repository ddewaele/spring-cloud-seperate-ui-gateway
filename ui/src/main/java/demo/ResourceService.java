package demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceService {

    @RequestMapping("/sayHello")
    public String sayHello() {
        return "hello";
    }
}
