package com.nomairo.nomairobackend.Service;

import com.nomairo.nomairobackend.Domain.MyUser;
import com.nomairo.nomairobackend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public MyUser registerUser(MyUser user) {
        if(userRepo.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("Email already registered");
        }
        return userRepo.save(user);
    }

    @Override
    public List<MyUser> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public MyUser updateUser(String id, MyUser updatedUser) {
        MyUser existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(String id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepo.deleteById(id);
    }

    @Override
    public MyUser loginUser(String email, String password) {
        MyUser myUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!myUser.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        return myUser;
    }

    @Override
    public Optional<MyUser> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
