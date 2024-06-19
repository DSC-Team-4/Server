package dsc.server.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new TimestampToLocalDateTimeConverter());
//    }
//
//    public static class TimestampToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
//
//        @Override
//        public LocalDateTime convert(String s) {
//            long timestamp = Long.parseLong(s);
//            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
//        }
//    }
}
