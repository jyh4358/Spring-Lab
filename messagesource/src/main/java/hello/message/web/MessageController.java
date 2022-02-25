package hello.message.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Slf4j
@Controller
public class MessageController {

    @Autowired
    MessageSource ms;

    @GetMapping("/")
    public String message(Model model, Locale locale){

        String message1 = ms.getMessage("message", null, locale);
        String message2 = ms.getMessage("message.param", new Object[]{"param test"}, locale);
        log.info("message1={}, message2={}", message1, message2);

        model.addAttribute("paramStr", "param test");

        return "message";
    }
}
