package org.tk.spring.jpa3;

import lombok.Data;
import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "stocks")
@Data
@ToString
public class Stock3Entity {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private double price;
}
