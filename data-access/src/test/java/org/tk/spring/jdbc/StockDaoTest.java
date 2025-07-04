package org.tk.spring.jdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DBJavaConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockDaoTest {

    @Autowired
    @Qualifier("jdbc")
    private StockDao stockDao;

    @Test
    void getAllStock() {
        List<Stock> stockList = stockDao.getAllStocks();
        stockList.forEach(System.out::println);
    }

    @Test
    void saveStock() {
        Stock stock = new Stock();
        stock.setId("NSE:ICICI");
        stock.setName("ICICI BANK");
        stock.setPrice(300);
        stockDao.save(stock);
        stockDao.getAllStocks().forEach(System.out::println);
    }

}
