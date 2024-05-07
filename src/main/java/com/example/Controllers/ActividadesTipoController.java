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
        logger.info("Obteniendo todas las actividades");
        logger.debug("Todas las activities cargadas correctamente");
        return ResponseEntity.ok(actividadesTipos);
    }

    @GetMapping("/actividadestipo/{id}")
    public ResponseEntity<ActividadesTipo> readActividadTipoById(@PathVariable Integer id) {
        ActividadesTipo actividadesTipo = actividadesTipoService.readActividadTipoById(id);
        if (actividadesTipo != null) {
            logger.info("Obteniendo actividades por el id: " + id );
            logger.debug("Recuperando activities by id " + id);
            return ResponseEntity.ok(actividadesTipo);
        } else {

            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/actividadestipo")
    public ResponseEntity<Object> createActividadTipo(@RequestBody ActividadesTipo actividadesTipo) {
        try {
            ActividadesTipo createdUser = actividadesTipoService.createActividadesTipo(actividadesTipo);
            logger.info("CREATE ACTIVIDAD DONE...");
            logger.debug("Nueva activity creada");
            return ResponseEntity.ok(createdUser);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la actividad: " + ex.getMessage());
        }
    }
    @DeleteMapping("/actividadestipo/{id}")
    public ResponseEntity<Void> deleteActividadTipo(@PathVariable Integer id) {
        actividadesTipoService.deleteActividadesTipo(id);
        logger.info("Borrando usuarios por el id" + id);
        logger.debug("Activity "+ id + " borrada correctamente");
        return ResponseEntity.noContent().build();
    }
}
