package org.tk.spring.mvc_validation.mdl;


import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class User {

    public interface Adult {
    };

    @NotNull(groups = { Adult.class })
    private String isAdult;

    @NotNull
    private String fistName;

    @NotNull
    private String lastName;

}