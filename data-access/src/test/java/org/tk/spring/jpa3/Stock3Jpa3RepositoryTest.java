package org.tk.spring.jpa3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.tk.spring.jpa2.Jpa2JavaConfig;
import org.tk.spring.jpa2.Stock2Entity;
import org.tk.spring.jpa2.Stock2Jpa2Service;

import java.util.List;

@SpringJUnitConfig( {Jpa3JavaConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Stock3Jpa3RepositoryTest {


    @Autowired
    private Stock3Jpa3Service service;

    @Test
    void getAllStock() {
        List<Stock3Entity> stockList = service.getAll();
        stockList.forEach(System.out::println);
    }

    @Test
    void saveWithTx() {
        Stock3Entity entity = new Stock3Entity();
        entity.setId("NSE:MINE");
        entity.setName("MY COMPANY");
        entity.setPrice(20.5);
        service.save(entity);

        List<Stock3Entity> stockList = service.getAll();
        stockList.forEach(System.out::println);
    }
}
