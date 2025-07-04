package org.tk.spring.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//https://www.baeldung.com/jackson-deserialize-immutable-objects
//https://www.baeldung.com/jackson-jsonformat
//https://medium.com/consulner/lombok-tricks-and-common-mistakes-fbf0ed044c3c

@Value
@ToString
public final class Stock {

    @NotNull
    @Size(max = 15)
    private final String company;

    @NotNull
    @DecimalMin("0.01")
    private final Double price;


// Remove @AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES))) and use below that works or create lombok.config at /src/java
//
//    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
//    public Stock(@NotNull @JsonProperty("company") @Size(max = 15) String company, @NotNull @JsonProperty("price")
//    @DecimalMin("0.01") Double price) {
//        this.company = company;
//        this.price = price;
//    }

    //OR

//    @ConstructorProperties({"company", "price"})
//    public Stock(@NotNull @Size(max = 15) String company, @NotNull @DecimalMin("0.01") Double price) {
//        this.company = company;
//        this.price = price;
//    }
}
