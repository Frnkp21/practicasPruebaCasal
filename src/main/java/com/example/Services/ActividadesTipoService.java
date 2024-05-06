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
            return actividadesTipoDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al usuario con el ID: " + id));

        } catch (Exception ex) {
            String errorMessage = "EL usuario: " + id + " no existe";
            logger.error(errorMessage, ex);
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
            String errorMessage = "Error al intentar eliminar la actividad con ID: " + id;
            logger.error(errorMessage, ex);
            throw new RuntimeException(errorMessage, ex);
        }
    }
}
