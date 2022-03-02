package hello.converter.controller;

import hello.converter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TypeConverterController {

    // 스프링이 자동으로 타입 변환 해준다. String -> Integer
    // 사용자 정의 컨버터를 등록하면 사용자 정의 컨버터가 우선순위를 갖는다
    @GetMapping("/converter-v1")
    public String converterV1(@RequestParam Integer data) {
        log.info("dataType={}", data.getClass());
        return "ok";
    }

    // 사용자 정의 컨버터 동작
    @GetMapping("/converter-v2")
    public String converterV2(@RequestParam IpPort ipPort) {
        log.info("dataType={}", ipPort.getClass());
        log.info("ipPort ip={}, port={}", ipPort.getIp(), ipPort.getPort());
        return "ok";
    }
}
