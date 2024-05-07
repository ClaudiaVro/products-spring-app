package com.clodi.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

/**
 * @author Claudia Vidican
 */
@Configuration public class FileConfig {

    @Bean public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);
        return messageSource;
    }

    @Bean public Encoder multipartFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(new ObjectFactory<HttpMessageConverters>() {
            @Override public HttpMessageConverters getObject() throws BeansException {
                return new HttpMessageConverters(new RestTemplate().getMessageConverters());
            }
        }));
    }
}
