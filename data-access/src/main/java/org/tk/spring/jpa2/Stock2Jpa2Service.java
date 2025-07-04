package org.tk.spring.jpa2;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Stock2Jpa2Service {

    private final Stock2Jpa2Repository repository;

    public Stock2Jpa2Service(Stock2Jpa2Repository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Stock2Entity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Stock2Entity save(Stock2Entity entity) {
        return repository.save(entity);
    }
}
