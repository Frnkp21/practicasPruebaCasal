package com.example.Controllers;

import com.example.Entities.ActividadesTipo;
import com.example.Entities.User;
import com.example.Services.ActividadesTipoService;
import com.example.Services.UserService;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ActividadesTipoController {
    ActividadesTipoService actividadesTipoService;
    private static Logger logger = Logger.getLogger(ActividadesTipoController.class);


    @Autowired
    public ActividadesTipoController(ActividadesTipoService actividadesTipoService) {
        this.actividadesTipoService = actividadesTipoService;
    }

    @GetMapping("/actividadestipo")
    public ResponseEntity<Object> readAll() {
        List<ActividadesTipo> actividadesTipos = actividadesTipoService.readAllActividadTipo();
        logger.info("Obteniendo todos los tipos de actividades");
        logger.debug("Todos los tipos de actividad cargadas correctamente");
        return ResponseEntity.ok(actividadesTipos);
    }

    @GetMapping("/actividadestipo/{id}")
    public ResponseEntity<ActividadesTipo> readActividadTipoById(@PathVariable Integer id) {
        ActividadesTipo actividadesTipo = actividadesTipoService.readActividadTipoById(id);
        if (actividadesTipo != null) {
            logger.info("Obteniendo Tipo de actividad por el id: " + id );
            logger.debug("Recuperando tipo de actividad by id " + id);
            return ResponseEntity.ok(actividadesTipo);
        } else {

            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/actividadestipo")
    public ResponseEntity<Object> createActividadTipo(@RequestBody ActividadesTipo actividadesTipo) {
        try {
            ActividadesTipo createdUser = actividadesTipoService.createActividadesTipo(actividadesTipo);
            logger.info("CREATE tipo de actividad DONE...");
            logger.debug("Nuevo tipo de actividad creada");
            return ResponseEntity.ok(createdUser);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el tipo de actividad: " + ex.getMessage());
        }
    }
    @DeleteMapping("/actividadestipo/{id}")
    public ResponseEntity<Void> deleteActividadTipo(@PathVariable Integer id) {
        actividadesTipoService.deleteActividadesTipo(id);
        logger.info("Borrando el tipo de actividad por el id" + id);
        logger.debug("tipo de actividad con el id: "+ id + " borrada correctamente");
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/actividadestipo/{id}")
    public ResponseEntity<ActividadesTipo> updateActividadesTipo(@PathVariable Integer id, @RequestBody ActividadesTipo updatedActividadesTipo) {
        ActividadesTipo actividadesTipo = actividadesTipoService.updateActividadesTipo(id, updatedActividadesTipo);
        logger.debug("Actualizando el tipo de actividad by id " + id);
        logger.info("Actualizando el tipo de actividad por el id "+id);

        return ResponseEntity.ok(actividadesTipo);
    }
}
