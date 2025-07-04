package org.tk.spring.jpa3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Stock3CrudRepository extends CrudRepository<Stock3Entity, String> {
}
