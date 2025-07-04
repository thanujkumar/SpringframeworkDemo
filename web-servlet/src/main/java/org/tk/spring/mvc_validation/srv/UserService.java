package org.tk.spring.mvc_validation.srv;

import org.springframework.validation.annotation.Validated;
import org.tk.spring.mvc_validation.mdl.NotAuthorizedException;
import org.tk.spring.mvc_validation.mdl.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

@Validated
public interface UserService {

    // Only apply the Default.class group
    void validateDefault(@NotNull @Valid User user) throws NotAuthorizedException;

    // Apply both
    @Validated(value = { Default.class, User.Adult.class })
    void validateAll(@NotNull @Valid User user) throws NotAuthorizedException;
}
