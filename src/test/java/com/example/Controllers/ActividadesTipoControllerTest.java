package com.example.Controllers;
import com.example.Controllers.ActividadesTipoController;
import com.example.Entities.ActividadesTipo;
import com.example.Services.ActividadesTipoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureMockMvc
@Transactional
class ActividadesTipoControllerTest {

    @Mock
    ActividadesTipoService actividadesTipoService;

    @InjectMocks
    ActividadesTipoController actividadesTipoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void readAll() {
        // Arrange
        List<ActividadesTipo> actividadesTipoList = new ArrayList<>();
        when(actividadesTipoService.readAllActividadTipo()).thenReturn(actividadesTipoList);

        // Act
        ResponseEntity<Object> response = actividadesTipoController.readAll();

        // Assert
        assertEquals(actividadesTipoList, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void readActividadTipoById() {
        // Arrange
        int id = 1;
        ActividadesTipo actividadesTipo = new ActividadesTipo();
        when(actividadesTipoService.readActividadTipoById(id)).thenReturn(actividadesTipo);

        // Act
        ResponseEntity<ActividadesTipo> response = actividadesTipoController.readActividadTipoById(id);

        // Assert
        assertEquals(actividadesTipo, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createActividadTipo() {
        // Arrange
        ActividadesTipo actividadesTipo = new ActividadesTipo();
        when(actividadesTipoService.createActividadesTipo(any())).thenReturn(actividadesTipo);

        // Act
        ResponseEntity<Object> response = actividadesTipoController.createActividadTipo(actividadesTipo);

        // Assert
        assertEquals(actividadesTipo, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteActividadTipo() {
        // Arrange
        int id = 1;

        // Act
        ResponseEntity<Void> response = actividadesTipoController.deleteActividadTipo(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(actividadesTipoService, times(1)).deleteActividadesTipo(id);
    }

    @Test
    void updateActividadesTipo() {
        // Arrange
        int id = 1;
        ActividadesTipo updatedActividadesTipo = new ActividadesTipo();
        updatedActividadesTipo.setNombre("TipoActividadPrueba");
        when(actividadesTipoService.updateActividadesTipo(id, updatedActividadesTipo)).thenReturn(updatedActividadesTipo);

        // Act
        ResponseEntity<ActividadesTipo> response = actividadesTipoController.updateActividadesTipo(id, updatedActividadesTipo);

        // Assert
        assertEquals(updatedActividadesTipo, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createActividadTipo_Error() {
        // Arrange
        ActividadesTipo actividadesTipo = new ActividadesTipo();
        when(actividadesTipoService.createActividadesTipo(any())).thenThrow(RuntimeException.class);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            actividadesTipoController.createActividadTipo(actividadesTipo);
        });
    }

    @Test
    void updateActividadesTipo_Error() {
        // Arrange
        int id = 1;
        ActividadesTipo updatedActividadesTipo = new ActividadesTipo();
        when(actividadesTipoService.updateActividadesTipo(id, updatedActividadesTipo)).thenThrow(RuntimeException.class);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            actividadesTipoController.updateActividadesTipo(id, updatedActividadesTipo);
        });
    }

}
