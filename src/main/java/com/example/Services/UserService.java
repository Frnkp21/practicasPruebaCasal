package com.example.Services;

import com.example.Entities.User;
import com.example.Dao.UserDAO;
import com.example.Errors.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static Logger logger = Logger.getLogger(UserService.class);
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> readAllUsers() {
        return userDAO.findAll();
    }

    public User createUser(User user) {
        return userDAO.save(user);
    }

    public void deleteUser(Integer id) {
        if (!userDAO.existsById(id)) {
            throw new ResourceNotFoundException("No se puede borrar, porque no existe ningún usuario con esa ID: " + id);
        }
        userDAO.deleteById(id);
    }

    public User updateUser(Integer id, User updatedUser) {
        User existingUser = userDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al usuario con el id: " + id));

        existingUser.setContraseña(updatedUser.getContraseña());
        existingUser.setNombre(updatedUser.getNombre());
        existingUser.setApellidos(updatedUser.getApellidos());


        return userDAO.save(existingUser);
    }


    public User readUserById(Integer id) {
        return userDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al usuario con el ID: " + id));
    }
}
