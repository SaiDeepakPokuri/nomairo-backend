package com.nomairo.nomairobackend.Service;

import com.nomairo.nomairobackend.Domain.MyUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    MyUser registerUser(MyUser user);

    List<MyUser> getAllUsers();

    MyUser updateUser(String id, MyUser user);

    void deleteUser(String id);

    MyUser loginUser(String email, String password);

    Optional<MyUser> getUserByEmail(String email);
}
