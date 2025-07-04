package org.tk.spring.jpa2;

import lombok.Data;
import lombok.ToString;
import org.tk.spring.jpa.StockEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "stocks")
@Data
@ToString
public class Stock2Entity {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private double price;
}
