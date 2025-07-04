package org.tk.spring.mvc_validation.srv;

import org.springframework.stereotype.Service;
import org.tk.spring.mvc_validation.mdl.NotAuthorizedException;
import org.tk.spring.mvc_validation.mdl.User;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void validateDefault(User user) throws NotAuthorizedException {
    }

    @Override
    public void validateAll(User user) throws NotAuthorizedException {
    }

}
