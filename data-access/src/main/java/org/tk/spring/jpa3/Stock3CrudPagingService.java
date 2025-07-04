package org.tk.spring.jpa3;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Stock3CrudPagingService {

    private final Stock3CrudPagingRepository repository;

    public Stock3CrudPagingService(Stock3CrudPagingRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Stock3Entity> getAll() {
        return Streamable.of(repository.findAll(Sort.unsorted())).stream().collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Stock3Entity> getAllSorted(Sort sort) {
        return Streamable.of(repository.findAll(sort)).stream().collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<Stock3Entity> getAllPaginated(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return repository.findAll(page);
    }

    @Transactional
    public Stock3Entity save(Stock3Entity entity) {
        return repository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<String> getAllStockId() {
        return Arrays.stream(repository.findAllStockId())
                .collect(Collectors.mapping(Object::toString, Collectors.toList()));
    }
}
