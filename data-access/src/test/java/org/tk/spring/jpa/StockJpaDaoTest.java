package org.tk.spring.jpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaJavaConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockJpaDaoTest {

    @Autowired
    private StockJpaDaoEM stockJpaDao;

    @Test
    void getAllStock() {
        List<StockEntity> stockList = stockJpaDao.getAllStocks();
        stockList.forEach(System.out::println);
    }

}
