package org.tk.spring.wiring.ex1.javabased;

import org.springframework.stereotype.Service;

@Service //only implementation will be in bean definition
public interface User {

    String getUser();
}
