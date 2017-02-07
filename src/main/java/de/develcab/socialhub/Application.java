package de.develcab.socialhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jbruester on 13.10.16.
 */
@SpringBootApplication
@EnableIntegration
@Configuration
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        ctx.registerShutdownHook();
        SocialHub hubService = ctx.getBean(SocialHub.class);
        hubService.collectAndSpreadNews();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public ConversionService conversionService() {
        return new DefaultConversionService();
    }
}
