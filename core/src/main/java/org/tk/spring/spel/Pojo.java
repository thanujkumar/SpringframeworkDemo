package org.tk.spring.spel;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Pojo {

    @Value("#{systemEnvironment['windir']}")
    private String windowDir;
}
