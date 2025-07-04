package org.tk.spring.jpa3;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Stock3Jpa3Service {

    private final Stock3Jpa3Repository repository;

    public Stock3Jpa3Service(Stock3Jpa3Repository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Stock3Entity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Stock3Entity save(Stock3Entity entity) {
        return repository.save(entity);
    }
}
