package org.servlets.project.service;

import org.servlets.project.model.Users;
import org.servlets.project.repository.UserRepository;
import org.servlets.project.repository.aipRepository.UserRepositoryImpl;


import java.util.List;


public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepositoryImpl();
    }

    public List<Users> getAllUsers() {
        return userRepository.getAll();
    }

    public Users getById(Long id) {
        return userRepository.getId(id);
    }

    public Users save(Users users) {
        return userRepository.save(users);
    }

    public boolean deleteById(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    public Users updateUserById( Users users,Long id) {
        return userRepository.update(users, id);
    }
}
