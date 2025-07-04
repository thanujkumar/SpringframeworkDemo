package org.tk.spring.jpa3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//requires spring-data-jpa library
@Repository
public interface Stock3Jpa3Repository extends JpaRepository<Stock3Entity, String> {
}
