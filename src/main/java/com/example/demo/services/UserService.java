package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private MongoTemplate mongoTemplate;

    public User registerUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPasswordHash(hashedPassword); 
        user.setPassword(null); 
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, User.class);
    }

    public User authenticateUser(String email, String password) {
        User user = findUserByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPasswordHash())) {
            return user;
        }
        return null;
    }
}
