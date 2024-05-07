package com.clodi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration @EnableAspectJAutoProxy public class Config {

    @Bean public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials("AKIA4W5IWOZ73ELBSI3A", "tgcqE/uD2bdNaqUDj5wcRjesZKjxEZtzRr1WlZai");
        return AmazonS3ClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                        .build();
    }

}
