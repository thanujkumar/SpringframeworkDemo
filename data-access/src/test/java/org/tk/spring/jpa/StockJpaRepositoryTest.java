package org.tk.spring.jpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig( {JpaJavaConfig.class}) //Below two annotations not required
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {JpaJavaConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockJpaRepositoryTest {

    @Autowired
    private StockJpaRepository stockJpaRepository;

    @Test
    void getAllStock() {
        List<StockEntity> stockList = stockJpaRepository.findAll();
        stockList.forEach(System.out::println);
    }

}
