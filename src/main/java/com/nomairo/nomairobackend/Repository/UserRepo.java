package com.nomairo.nomairobackend.Repository;

import com.nomairo.nomairobackend.Domain.MyUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<MyUser, String> {
    Optional<MyUser> findByEmail(String email);
}
