package org.tk.spring.jpa2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig( {Jpa2JavaConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Stock2Jpa2RepositoryTest {


    @Autowired
    private Stock2Jpa2Service service;

    @Test
    void getAllStock() {
        List<Stock2Entity> stockList = service.getAll();
        stockList.forEach(System.out::println);
    }

    @Test
    void saveWithTx() {
        Stock2Entity entity = new Stock2Entity();
        entity.setId("NSE:MINE");
        entity.setName("MY COMPANY");
        entity.setPrice(20.5);
        service.save(entity);

        List<Stock2Entity> stockList = service.getAll();
        stockList.forEach(System.out::println);
    }
}
