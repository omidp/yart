package com.yart.app;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

public class ContainerConfig
{

    
    @Bean
    public EmbeddedServletContainerFactory containerFactory()
    {
        UndertowEmbeddedServletContainerFactory em = new UndertowEmbeddedServletContainerFactory(8888);
        em.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
        return em;
    }

    
}
