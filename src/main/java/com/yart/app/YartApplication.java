package com.yart.app;

import java.io.PrintStream;
import java.util.Arrays;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.yart.util.SpringUtil;

//@SpringBootApplication
/**
 *
 * @author Omid Pourhadi
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties
@PropertySource({"classpath:database.properties"})
@Import(ContainerConfig.class)
public class YartApplication
{

    public static void main(String[] args)
    {
        SpringApplication sp = new SpringApplication(YartApplication.class);
        sp.setBanner(new Banner() {

            @Override
            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out)
            {
                out.println("Yet Another Reporting Tool");
            }
        });
        sp.run(args);

    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx)
    {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames)
            {
                System.out.println(beanName);
            }

        };
    }
    
    @Bean
    public SpringUtil springUtil()
    {
        return new SpringUtil();
    }

}
