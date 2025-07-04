package org.tk.spring.jpa.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructMapperConfiguration {

    @Bean
    public StockEnityDtoMapper stockEnityDtoMapper() {
        return Mappers.getMapper(StockEnityDtoMapper.class);
    }
}
