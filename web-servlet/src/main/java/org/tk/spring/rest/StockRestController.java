package org.tk.spring.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(value = "/rest", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class StockRestController {

    private static final Map<String, Stock> STOCK_LIVE = new ConcurrentHashMap<>() {
        {
            put("NSE:TATAMOTORS", new Stock("NSE:TATAMOTORS", 303.10));
            put("NSE:INFY", new Stock("NSE:INFY", 1588.95));
            put("NSE:ITC", new Stock("NSE:ITC", 207.00));
            put("NSE:ICICIBANK", new Stock("NSE:ICICIBANK", 655.75));
        }
    };

    @GetMapping("/stock/{company}")
    @ResponseBody
    public Stock getStockPrice(@PathVariable("company") String company) {
        Stock stock;
        if (STOCK_LIVE.containsKey(company)) {
            stock = STOCK_LIVE.get(company);
        } else {
            throw new RuntimeException("we don't have stock information for given company");
        }
        return stock;
    }

    @PutMapping("/stock/{company}")
    public ResponseEntity storeStockPrice(@PathVariable("company") String company, @Valid @RequestBody Stock stock) {
        STOCK_LIVE.put(company, stock);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/stock/{company}")
    public void updateStockPrice(@PathVariable("company") String company, @Validated/*spring*/ @RequestBody Stock stock) {
        STOCK_LIVE.put(company, stock);
    }

    @GetMapping(value = "/stocks")
    public List<Stock> getAllStockPrice() {
        return STOCK_LIVE.values().stream().toList();
    }
}
