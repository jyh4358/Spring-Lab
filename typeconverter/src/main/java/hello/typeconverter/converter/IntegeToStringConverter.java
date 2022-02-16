package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;


@Slf4j
public class IntegeToStringConverter implements Converter<Integer, String> {


    @Override
    public String convert(Integer source) {
        log.info("IntegerToStringConverter Source = {}", source);
        return String.valueOf(source);
    }
}
