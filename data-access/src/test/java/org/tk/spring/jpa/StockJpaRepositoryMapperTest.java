package org.tk.spring.jpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tk.spring.jpa.mapper.StockJpaMapperDao;

import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaJavaConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockJpaRepositoryMapperTest {

    @Autowired
    private StockJpaMapperDao mapperDao;

    @Test
    void getAllStock() {
        List<StockDto> stockList = mapperDao.getAllStockDto();
        stockList.forEach(System.out::println);
    }

}
