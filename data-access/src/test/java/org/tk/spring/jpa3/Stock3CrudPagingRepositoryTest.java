package org.tk.spring.jpa3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig({Jpa3JavaConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Stock3CrudPagingRepositoryTest {


    @Autowired
    private Stock3CrudPagingService service;

    @Test
    void getAllStock() {
        List<Stock3Entity> stockList = service.getAll();
        stockList.forEach(System.out::println);
    }

    @Test
    void getAllStockSorted() {
        List<Stock3Entity> stockList = service.getAllSorted(Sort.by(Sort.Order.desc("id")));
        stockList.forEach(System.out::println);
    }

    @Test
    void getAllStockPagination() {
        Page<Stock3Entity> page = service.getAllPaginated(1, 2, "id");
        if (page.hasContent()) {
            System.out.println("Records exists in the response");
        } else {
            System.out.println("No Records exists in the response");
        }
        System.out.println("Total ELements : " + page.getTotalElements());
        System.out.println("Total Pages : " + page.getTotalPages());
        System.out.println("Count :" + page.stream().count());
        System.out.println("===PAGE RECORDS===");
        page.get().forEach(System.out::println);
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

    @Test
    void customQuery() {
       List<String> ids = service.getAllStockId();
       ids.forEach(System.out::println);
    }
}
