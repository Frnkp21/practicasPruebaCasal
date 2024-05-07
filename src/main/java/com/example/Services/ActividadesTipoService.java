package com.example.Services;

import com.example.Dao.ActividadesTipoDAO;
import com.example.Dao.UserDAO;
import com.example.Entities.ActividadesTipo;
import com.example.Entities.User;
import com.example.Errors.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadesTipoService {

    private static Logger logger = Logger.getLogger(ActividadesTipoService.class);
    private final ActividadesTipoDAO actividadesTipoDAO;

    @Autowired
    public ActividadesTipoService(ActividadesTipoDAO actividadesTipoDAO) {
        this.actividadesTipoDAO = actividadesTipoDAO;
    }

    public List<ActividadesTipo> readAllActividadTipo() {
        return actividadesTipoDAO.findAll();
    }
    public ActividadesTipo readActividadTipoById(Integer id) {
        try {
            return actividadesTipoDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al tipo de actividad con el ID: " + id));

        } catch (Exception ex) {
            String errorMessage = "EL tipo de actividad: " + id + " no existe";
            logger.error(errorMessage, ex);
            logger.warn("Fallo en el id proporcionado, se tiene que poner uno existente", ex);
            throw new RuntimeException(errorMessage, ex);
        }
    }
    public ActividadesTipo createActividadesTipo(ActividadesTipo actividadesTipo) {
        return actividadesTipoDAO.save(actividadesTipo);
    }
    public void deleteActividadesTipo(Integer id) {
        try {
            if (!actividadesTipoDAO.existsById(id)) {
                throw new ResourceNotFoundException("No se puede borrar, porque no existe ningúna actividad con esa ID: " + id);
            }
            actividadesTipoDAO.deleteById(id);
        } catch (Exception ex) {
            String errorMessage = "Error al intentar eliminar el tipo actividad con ID: " + id;
            logger.error(errorMessage, ex);
            logger.warn("Utiliza un id existente para poder borrar el tipo de actividad",ex);
            throw new RuntimeException(errorMessage, ex);
        }
    }
    public ActividadesTipo updateActividadesTipo(Integer id, ActividadesTipo updatedActividadesTipo) {
        try {
            ActividadesTipo existingActividadesTipo = actividadesTipoDAO.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado el tipo de actividad con el id: " + id));

            existingActividadesTipo.setNombre(updatedActividadesTipo.getNombre());

            return actividadesTipoDAO.save(existingActividadesTipo);
        } catch (Exception ex) {
            String errorMessage = "Error al intentar actualizar el tipo de actividad con ID: " + id;
            logger.error(errorMessage, ex);
            logger.warn("Fallo en modificar el tipo de actividad, verifica que todos los campos esten correctos", ex);
            throw new RuntimeException(errorMessage, ex);
        }
    }
}
