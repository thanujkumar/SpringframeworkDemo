package org.tk.spring.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockJpaCrudRepository extends CrudRepository<StockEntity, String> {
}
