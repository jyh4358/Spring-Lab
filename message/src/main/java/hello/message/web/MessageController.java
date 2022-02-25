package hello.message.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MessageController {

    @GetMapping("/")
    public String message(){

        return "message";
    }
}
