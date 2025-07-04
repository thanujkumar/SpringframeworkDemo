package org.tk.spring.jpa3;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Stock3CrudService {

    private final Stock3CrudRepository repository;

    public Stock3CrudService(Stock3CrudRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Stock3Entity> getAll() {
        return Streamable.of(repository.findAll()).stream().collect(Collectors.toList());
    }

    @Transactional
    public Stock3Entity save(Stock3Entity entity) {
        return repository.save(entity);
    }
}
