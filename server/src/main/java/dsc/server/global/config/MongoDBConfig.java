package dsc.server.global.config;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions.MongoConverterConfigurationAdapter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.stereotype.Component;

@Configuration
public class MongoDBConfig {
    @Bean
    public MappingMongoConverter mappingMongoConverter(
            MongoDatabaseFactory mongoDatabaseFactory,
            MongoMappingContext mongoMappingContext
    ) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

//    @Bean
//    public MongoCustomConversions customConversions() {
//        return MongoCustomConversions.create(
//                adapter -> {
//                    adapter.registerConverter( new DateToLocalDateTimeKstConverter() );
//                }
//        );
//    }

    @Component
    @ReadingConverter
    public static class DateToLocalDateTimeKstConverter implements Converter<Date, LocalDateTime> {
        @Override
        public LocalDateTime convert(Date source) {
            return source.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusHours(9);
        }
    }
}
