package org.tk.spring.jpa;

import lombok.Data;
import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
@Data
@ToString
@NamedQuery(name = StockEntity.QUERY_ALL, query = "select e from StockEntity e") //Using JPA-QL
public class StockEntity {
    public static final String QUERY_ALL = "StockEntity.findAll";

    @Id
    private String id;
    private String name;
    private double price;
}
