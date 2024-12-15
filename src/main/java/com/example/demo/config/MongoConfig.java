package com.example.demo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "docker-projekt"; 
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        System.out.println("DB OK");
        return MongoClients.create("mongodb://localhost:27017"); 
    }
}
