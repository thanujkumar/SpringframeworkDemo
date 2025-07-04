package org.tk.spring.jpa.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.tk.spring.jpa.StockDto;
import org.tk.spring.jpa.StockJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StockJpaMapperDao {

    private final StockJpaRepository jpaRepository;

    @Autowired
    @Qualifier("stockEnityDtoMapper")
    private StockEnityDtoMapper mapper;

    public StockJpaMapperDao(StockJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public List<StockDto> getAllStockDto() {
        return jpaRepository.findAll()
                .stream()
                .map(stockEntity -> mapper.toDto(stockEntity))
                .collect(Collectors.toList());
    }
}
