package org.tk.spring.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Qualifier("jdbc")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StockDao {


    private final JdbcTemplate jdbcTemplate;

    public List<Stock> getAllStocks() {
        return jdbcTemplate.query("select id, name, price from stocks",
                new BeanPropertyRowMapper<>(Stock.class));
    }

    @Transactional
    public int save(Stock stock) {
        var sqlQuery = "merge into stocks key(id) values (?, ?, ?)";
        return jdbcTemplate.update(sqlQuery, stock.getId(), stock.getName(), stock.getPrice());
    }
}
