package org.tk.spring.jpa2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tk.spring.jpa.StockEntity;

//requires spring-data-jpa library
@Repository
public interface Stock2Jpa2Repository extends JpaRepository<Stock2Entity, String> {
}
