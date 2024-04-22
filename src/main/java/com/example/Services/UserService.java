package com.example.Services;

import com.example.Entities.User;
import com.example.Dao.UserDAO;
import com.example.Errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    UserDAO userDAO;
    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public List<User> readAllUsers(){
        return userDAO.findAll();
    }
    public User createUser(User user) {
        return userDAO.save(user);
    }

    public void deleteUser(Integer id) {
        userDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se puede borrar, porque no existe ningun usuario con esa id."));
        userDAO.deleteById(id);
    }

    public User updateUser(Integer id, User updatedUser) {
        User existingUser = userDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al usuario con el id: " + id));

        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setNom(updatedUser.getNom());
        existingUser.setApellido(updatedUser.getApellido());
        existingUser.setTelefono(updatedUser.getTelefono());

        return userDAO.save(existingUser);
    }
    public User readUserById(Integer id) {
        return userDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al usuario con el id: " + id));
    }

}
