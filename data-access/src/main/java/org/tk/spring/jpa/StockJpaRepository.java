package org.tk.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//requires spring-data-jpa library
@Repository
public interface StockJpaRepository extends JpaRepository<StockEntity, String> {
}
