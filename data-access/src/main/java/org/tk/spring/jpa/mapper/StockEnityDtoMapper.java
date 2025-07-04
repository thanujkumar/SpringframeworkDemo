package org.tk.spring.jpa.mapper;

import org.mapstruct.Mapper;
import org.tk.spring.jpa.StockDto;
import org.tk.spring.jpa.StockEntity;

//https://www.baeldung.com/mapstruct
@Mapper
public interface StockEnityDtoMapper {

    //@Mapping(target = "id")
    StockDto toDto(StockEntity entity);

    //@Mapping(target = "id")
    StockEntity toEntity(StockDto dto);
}
