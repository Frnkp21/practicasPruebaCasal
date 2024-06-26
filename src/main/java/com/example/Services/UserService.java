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
        try {
            if (!userDAO.existsById(id)) {
                throw new ResourceNotFoundException("No se puede borrar, porque no existe ningún usuario con esa ID: " + id);
            }
            userDAO.deleteById(id);
        } catch (Exception ex) {
            String errorMessage = "Error al intentar eliminar el usuario con ID: " + id;
            logger.error(errorMessage, ex);
            logger.warn("Utiliza un id existente para poder borrar el usuario",ex);
            throw new RuntimeException(errorMessage, ex);
        }
    }

    public User updateUser(Integer id, User updatedUser) {
        try {
            User existingUser = userDAO.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al usuario con el id: " + id));

            existingUser.setContraseña(updatedUser.getContraseña());
            existingUser.setNombre(updatedUser.getNombre());
            existingUser.setApellidos(updatedUser.getApellidos());
            existingUser.setFecha_nacimiento(updatedUser.getFecha_nacimiento());
            existingUser.setSexo(updatedUser.getSexo());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setNum_tarjeta_sanitaria(updatedUser.getNum_tarjeta_sanitaria());
            existingUser.setComentarios(updatedUser.getComentarios());
            existingUser.setAlergias(updatedUser.getAlergias());
            existingUser.setContacto1_nombre_completo(updatedUser.getContacto1_nombre_completo());
            existingUser.setContacto1_telefono(updatedUser.getContacto1_telefono());
            existingUser.setContacto1_email(updatedUser.getContacto1_email());
            existingUser.setContacto1_relacion(updatedUser.getContacto1_relacion());
            existingUser.setContacto1_comentarios(updatedUser.getContacto1_comentarios());
            existingUser.setContacto2_nombre_completo(updatedUser.getContacto2_nombre_completo());
            existingUser.setContacto2_telefono(updatedUser.getContacto2_telefono());
            existingUser.setContacto2_email(updatedUser.getContacto2_email());
            existingUser.setContacto2_relacion(updatedUser.getContacto2_relacion());
            existingUser.setContacto2_comentarios(updatedUser.getContacto2_comentarios());

            return userDAO.save(existingUser);
        } catch (Exception ex) {
            String errorMessage = "Error al intentar actualizar el usuario con ID: " + id;
            logger.error(errorMessage, ex);
            logger.warn("Fallo en modificar el usuario, verifica que todos los campos esten correctos", ex);
            throw new RuntimeException(errorMessage, ex);
        }
    }

    public User readUserById(Integer id) {
        try {
            return userDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al usuario con el ID: " + id));

        } catch (Exception ex) {
            String errorMessage = "EL usuario: " + id + " no existe";
            logger.error(errorMessage, ex);
            logger.warn("Fallo en el id proporcionado, se tiene que poner uno existente", ex);
            throw new RuntimeException(errorMessage, ex);
        }
    }
}

