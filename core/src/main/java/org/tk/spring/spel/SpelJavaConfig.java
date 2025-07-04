package org.tk.spring.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.Socket;

@Configuration
public class SpelJavaConfig {

    @Autowired
    private Environment environment;

    private File tempDir;

    @Value("#{ T(Math).random() }")
    private double randomValue;

    @Value("#{systemProperties['user.home']}")
    private String userHome;

    @Value("#{systemEnvironment['Path']}")
    private String pathInfo;

    @Value("#{systemProperties['java.io.tmpdir']}")
    public void setTempDir(String tmpDir) {
        tempDir = new File(tmpDir);
    }

    @PostConstruct
    public void printOutDetails() {
        System.out.println("Random Value = " + randomValue);
        System.out.println("User Home = " + userHome);
        System.out.println("System PATH = " + pathInfo);
        System.out.println("Java IO TempDir = " + tempDir.getAbsolutePath());
        ((StandardEnvironment) environment).getSystemEnvironment().forEach((s, o) -> {
            System.out.println(s + "=" + o);
        });
        System.out.println("Windows Dir ===== "+ windir);
    }

    @Bean
    public Pojo pojo() {
        return  new Pojo();
    }

    @Value("#{pojo.windowDir}")
    private String windir;

}
