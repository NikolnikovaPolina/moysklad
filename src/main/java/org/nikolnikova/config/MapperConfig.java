package org.nikolnikova.config;

import org.nikolnikova.service.util.EntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapper();
    }
}