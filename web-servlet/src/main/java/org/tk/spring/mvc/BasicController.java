package org.tk.spring.mvc;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("/mvc")
public class BasicController {

    @RequestMapping(value = "/ping")
    @ResponseBody
    public String ping() {
        return "pong";
    }

    @RequestMapping("/args")
    @ResponseBody
    public Callable<ResponseEntity<String>> showHeader(Locale locale, @RequestHeader("User-Agent") String userAgent) {
        String originThread = Thread.currentThread().getName();
        return () -> ResponseEntity.ok(String.format("Request info locale: %s, user-agent: %s, %s -> %s", locale, userAgent, originThread, Thread.currentThread().getName()));
    }
}
