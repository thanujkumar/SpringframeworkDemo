package org.tk.spring.profiles;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({LocalDSConfig.class, ProductionDSConfig.class})
public class RuntimeWhichDS {

    private DSBasedOnProfile profileDS;

    public RuntimeWhichDS(DSBasedOnProfile dataSource) {
        this.profileDS = dataSource;
    }

    public void printProfileDS() {
        System.out.println("Profile DS Loaded = " + profileDS.getProfileTypeDS());
    }
}
