package com.reposicion.identidad.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TurnoServiceTest {

    @InjectMocks
    private TurnoService serivice;

    //--Test crear turno
    @Test
    @DisplayName("Debe registrar un turno si los datos son correctos.")
    void cuandoCrearTurno_entoncesRetornarTurnoTrabajador(){

    }
    //--Test listar todos los turno
    @Test
    @DisplayName("Debe listar todos los turno si los datos son correctos.")
        void cuandoListarTodosTurnos_entoncesTurnosListados(){

        }
    //--Test obtener un turno en especifico
    @Test
    @DisplayName("Debe obtener un turno si los datos son correctos.")
    void cuandoObtenerUnSoloTurno_entoncesTurnoObtenido(){

    }
    //--Test actualizar turno
    @Test
    @DisplayName("Debe actualizar un turno si los datos son correctos.")
    void cuandoActualizarTurnos_entoncesTurnoActualizado(){

    }
    //--Test eliminar turno
    @Test
    @DisplayName("Debe eliminar un turno si los datos son correctos.")
    void cuandoEliminarTurno_entoncesTurnoEliminado(){

    }
}
