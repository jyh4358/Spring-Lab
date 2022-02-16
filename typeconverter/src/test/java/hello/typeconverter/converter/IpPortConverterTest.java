package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class IpPortConverterTest {

    @Test
    void StringToIpPort(){
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.1.2.3:8080";
        IpPort result = converter.convert(source);
        Assertions.assertThat(result).isEqualTo(new IpPort("127.1.2.3", 8080));
    }

    @Test
    void IpPortToString(){
        IpPortToStringConverter converter =new IpPortToStringConverter();
        IpPort source = new IpPort("127.1.2.3", 8080);
        String result = converter.convert(source);
        Assertions.assertThat(result).isEqualTo("127.1.2.3:8080");
    }

}
