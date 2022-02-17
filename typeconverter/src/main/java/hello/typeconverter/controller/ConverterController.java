package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Slf4j
//converterV1, converterV2 호출 시 RestController로 실행
//@RestController
@Controller
public class ConverterController {

    @GetMapping("/converter")
    public String converterV1(@RequestParam Integer data){
        log.info("data = {}", data);
        return "ok";
    }
    @GetMapping("/converter2")
    public String converterV2(@RequestParam IpPort ipport){
        System.out.println("ipport = " + ipport);
        return "ok";
    }

    @GetMapping("/converter-view")
    public String converterView(Model model) {
        model.addAttribute("number", 100000);
        model.addAttribute("ipPort", new IpPort("127.1.2.3", 8080));
        return "view1";
    }

    @GetMapping("/converter/edit")
    public String converterForm(Model model) {
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        Form form = new Form(ipPort);
        model.addAttribute("form", form);
        return "view2";
    }

    @PostMapping("/converter/edit")
    public String converterEdit(@ModelAttribute Form form, Model model) {
        IpPort ipPort = form.getIpPort();
        model.addAttribute("ipPort", ipPort);
        return "view1";
    }



    @Data
    static class Form {
        private IpPort ipPort;
        public Form(IpPort ipPort) {
            this.ipPort = ipPort;
        }
    }

}
