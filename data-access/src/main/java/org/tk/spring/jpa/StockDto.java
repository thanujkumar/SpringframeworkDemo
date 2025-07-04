package org.tk.spring.jpa;

import lombok.Data;

@Data
public class StockDto {
    private String id;
    private String name;
    private double price;
}
