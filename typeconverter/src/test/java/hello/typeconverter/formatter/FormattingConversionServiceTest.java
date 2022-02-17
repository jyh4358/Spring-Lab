package hello.typeconverter.formatter;

import hello.typeconverter.converter.StringToIntegerConverter;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService(){

        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        // 컨버터 등록
        conversionService.addConverter(new StringToIntegerConverter());

        // 포맷터 등록
        conversionService.addFormatter((new MyNumberOfFormatter()));

        //포맷터 사용
        String convert = conversionService.convert(1000, String.class);
        Assertions.assertThat(convert).isEqualTo("1,000");

        Long convert1 = conversionService.convert("1,000", Long.class);
        Assertions.assertThat(convert1).isEqualTo(1000L);

    }
}
