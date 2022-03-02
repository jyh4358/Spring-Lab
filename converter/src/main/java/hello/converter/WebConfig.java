package hello.converter;

import hello.converter.TypeConverter.IntegerToStringConverter;
import hello.converter.TypeConverter.IpPortToStringConverter;
import hello.converter.TypeConverter.StringToIntegerConverter;
import hello.converter.TypeConverter.StringToIpPortConverter;
import hello.converter.TypeFormatter.NumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

//        registry.addFormatter(new NumberFormatter());
    }
}
