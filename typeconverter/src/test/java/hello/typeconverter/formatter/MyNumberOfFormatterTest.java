package hello.typeconverter.formatter;

import org.apache.tomcat.jni.Local;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MyNumberOfFormatterTest {


    @Test
    void parse() throws ParseException {
        MyNumberOfFormatter formatter = new MyNumberOfFormatter();

        Number result = formatter.parse("1,000", Locale.KOREA);
        Assertions.assertThat(result).isEqualTo(1000L);
    }

    @Test
    void print() {
        MyNumberOfFormatter formatter = new MyNumberOfFormatter();

        String result = formatter.print(1000, Locale.KOREA);
        Assertions.assertThat(result).isEqualTo("1,000");
    }
}