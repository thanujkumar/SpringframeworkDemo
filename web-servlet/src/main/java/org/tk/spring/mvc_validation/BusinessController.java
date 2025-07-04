package org.tk.spring.mvc_validation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc")
public class BusinessController {

    @RequestMapping("/illegalState")
    public String throwIllegalStateException() {
        throw new IllegalStateException("illegal state exception thrown, does it work?");
    }

    @RequestMapping("/runtime")
    public String throwRuntimeException() {
        throw new RuntimeException("runtime exception thrown, does it work?");
    }


}
