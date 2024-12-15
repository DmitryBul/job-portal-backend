package com.example.demo.repositories;

import com.example.demo.models.Data;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface DataRepository extends MongoRepository<Data, String> {
}

