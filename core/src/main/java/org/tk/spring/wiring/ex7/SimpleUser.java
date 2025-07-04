package org.tk.spring.wiring.ex7;

import org.springframework.stereotype.Component;
import org.tk.spring.wiring.ex1.javabased.User;

//example to show how component scan works (MainWiring)
@Component
public class SimpleUser implements User {
    @Override
    public String getUser() {
        return "Simple User";
    }
}
