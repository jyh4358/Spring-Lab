package hello.converter.TypeFormatter;

import hello.converter.TypeConverter.StringToIpPortConverter;
import hello.converter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService(){
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        // 컨버터도 등록이 가능하다
        conversionService.addConverter(new StringToIpPortConverter());

        // 포맷터 등록
        conversionService.addFormatter(new NumberFormatter());

        // 컨버터 사용
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        Assertions.assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        // 포맷터 사용
        Assertions.assertThat(conversionService.convert(1000, String.class)).isEqualTo("1,000");
        Assertions.assertThat(conversionService.convert("1,000", Long.class)).isEqualTo(1000);

    }




}
