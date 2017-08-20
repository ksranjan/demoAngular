package com.example.demoapp.repositories;

import com.example.demoapp.models.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends MongoRepository<Demo, String> {

}