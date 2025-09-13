package com.nomairo.nomairobackend.Repository;

import com.nomairo.nomairobackend.Domain.MyUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<MyUser, String> {
    Optional<MyUser> findByEmail(String email);
}
