package hello.typeconverter.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverterTest {

    @Test
    void StringToInteger(){
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    void IntegerToString(){
        IntegeToStringConverter converter = new IntegeToStringConverter();
        String result = converter.convert(10);
        Assertions.assertThat(result).isEqualTo("10");
    }
}
