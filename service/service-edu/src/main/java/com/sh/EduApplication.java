package com.sh;


import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

/**
 * @author sunhu
 * @date 2021/4/20 11:12
 */
@SpringBootApplication
@ServletComponentScan
public class EduApplication {




    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }


    @Bean
    MeterRegistryCustomizer meterRegistryCustomizer(MeterRegistry meterRegistry) {
        return meterRegistry1 -> meterRegistry.config()
                .commonTags("application", "edu");
    }


    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(){
        return registry -> registry.config().commonTags("application","edu");
    }

}
