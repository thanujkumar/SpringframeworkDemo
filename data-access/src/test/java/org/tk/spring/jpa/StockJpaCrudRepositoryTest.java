package org.tk.spring.jpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaJavaConfig.class}) //scans org.tk.spring.jpa and org.tk.spring.jpa.mapper
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockJpaCrudRepositoryTest {

    @Autowired
    private StockJpaCrudRepository jpaCrudRepository;

    @Test
    void getAllStock() {
        List<StockEntity> stockList = Streamable.of(jpaCrudRepository.findAll()).toList();
        stockList.forEach(System.out::println);
    }

}
