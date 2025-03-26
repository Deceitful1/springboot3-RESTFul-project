package br.com.deceitful1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig
{

    @Bean
    public ObjectMapper objectMapper()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().addFilter("PersonFilter"
                , SimpleBeanPropertyFilter.serializeAllExcept("sensitiveData"));

        mapper.setFilterProvider(simpleFilterProvider);

        return mapper;

    }

}
