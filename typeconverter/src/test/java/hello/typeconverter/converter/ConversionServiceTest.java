package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionServiceTest {

    @Test
    void conversionService(){

        // 컨버전 등록
        DefaultConversionService conversion = new DefaultConversionService();
        conversion.addConverter(new StringToIntegerConverter());
        conversion.addConverter(new StringToIpPortConverter());
        conversion.addConverter(new StringToIpPortConverter());
        conversion.addConverter(new IpPortToStringConverter());

        // 컨버전 사용
        Assertions.assertThat(conversion.convert("10", Integer.class)).isEqualTo(10);
        Assertions.assertThat(conversion.convert(10, String.class)).isEqualTo("10");

        IpPort ipPort = conversion.convert("127.1.2.3:8080", IpPort.class);
        Assertions.assertThat(ipPort).isEqualTo(new IpPort("127.1.2.3", 8080));

        String ipPortString = conversion.convert(new IpPort("127.0.0.1", 8080), String.class);
        Assertions.assertThat(ipPortString).isEqualTo("127.0.0.1:8080");
    }

}
