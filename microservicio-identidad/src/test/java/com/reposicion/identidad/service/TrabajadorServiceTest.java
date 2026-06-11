package com.reposicion.identidad.service;

import com.reposicion.identidad.dto.TrabajadorDTO;
import com.reposicion.identidad.model.Trabajador;
import com.reposicion.identidad.model.TurnoTrabajador;
import com.reposicion.identidad.repository.TrabajadorRepository;
import com.reposicion.identidad.repository.TurnoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TrabajadorServiceTest {

//--Crear repositorio falso del trabajador.
    @Mock
    private TrabajadorRepository trabajadorRepository;
//--Crear repositorio falso del turno.
    @Mock
    private TurnoRepository turnoRepository;
//--Crear service falso del trabajador.
    @InjectMocks
    private TrabajadorService trabajadorService;

    //--Test crear un trabajador
    @Test
    @DisplayName("Debe registrar un trabajador si los datos son correctos y validos.")
    void cuandoGuardarTrabajador_entoncesRetornarTrabajador() {
//--GIVEN(Dado que: Se preparan los datos de entrada)
//--Instancia de TurnoTrabajador
        TurnoTrabajador turnoSimulado = new TurnoTrabajador();
        turnoSimulado.setId(1L);
//--Instancia de TrabajadorDTO de entrada (con datos validos)
        TrabajadorDTO trabajadorEntradaDTO = new TrabajadorDTO();
        trabajadorEntradaDTO.setRut("20.123.456-7");
        trabajadorEntradaDTO.setNombre("Pavel");
        trabajadorEntradaDTO.setCorreo("correoPavel@gmail.com");
        trabajadorEntradaDTO.setRol("Reponedor");
        trabajadorEntradaDTO.setEdad(30);
        trabajadorEntradaDTO.setTurnoId(1L);
//--Creacion de entidad simulada Trabajador. Se espera que el repositorio falso (trabajadorRepository) guarde y devuelva.
//--Simulacion de base de datos registra a Trabajador y puso ID 1.
        Trabajador trabajadorGuardado = new Trabajador();
        trabajadorGuardado.setId(1L);
        trabajadorGuardado.setRut(trabajadorEntradaDTO.getRut());
        trabajadorGuardado.setNombre(trabajadorEntradaDTO.getNombre());
        trabajadorGuardado.setCorreo(trabajadorEntradaDTO.getCorreo());
        trabajadorGuardado.setRol(trabajadorEntradaDTO.getRol());
        trabajadorGuardado.setEdad(trabajadorEntradaDTO.getEdad());
        trabajadorGuardado.setTurno(turnoSimulado);
//--Configuracion del Mock
        Mockito.when(turnoRepository.findById(1L)).thenReturn(Optional.of(turnoSimulado));
//--Indicamos al repositorio falso (trabajadorService) que cuando reciba cualquier objeto de Trabajador en metodo .save(), devuelva 'trabajadorGuardado'.
        Mockito.when(trabajadorRepository.save(Mockito.any(Trabajador.class))).thenReturn(trabajadorGuardado);
//--WHEN(Cuando: Se ejecuta el metodo real en el service)
        Trabajador resultado = trabajadorService.guardarTrabajador(trabajadorEntradaDTO);
//--THEN(Entonces: se valida que el resultado cumpla cumpla con la estructura de la entidad Trabajador)
        assertNotNull(resultado, "El TRABAJADOR retornado no deberia ser nulo.");
        assertNotNull(resultado.getId(), "El ID generado no deberia ser nulo.");
        assertEquals(1L, resultado.getId(),"El ID generado deberia ser 1L.");
        assertEquals("20.123.456-7",resultado.getRut(), "El RUT almacenado no coincide con el ingresado.");
        assertEquals("Pavel", resultado.getNombre(), "El NOMBRE almacenado no coicide con el ingresado.");
        assertEquals("correoPavel@gmail.com", resultado.getCorreo(),"El CORREO almacenado no coicide con el ingresado.");
        assertEquals("Reponedor", resultado.getRol(), "El ROL almacenado no coicide con el ingresado.");
        assertEquals(30, resultado.getEdad(), "La EDAD almacenada no coicide con la ingresada.");


    }

    //--Test listar todos los trabajadores registrados
    @Test
    @DisplayName("Debe listar todos los trabajadores si los datos son correctos.")
    void cuandoListarTodosTrabajadores_entoncesRetornarTodosLosTrabajadores() {

    }

    //--Test obtener un trabajador en especifico
    @Test
    @DisplayName("Debe obtener un solo trabajador si los datos son correctos.")
    void cuandoObtenerUnSoloTrabajador_entoncesRetornarUnSoloTrabajador() {

    }

    //--Test actualizar trabajador
    @Test
    @DisplayName("Debe actualizar un trabajador si los datos son correctos.")
    void cuandoActualizarTrabajador_entoncesRetornarActualizarTrabajador() {

    }
    //--Test eliminar trabajador
    @Test
    @DisplayName("Debe eliminar un trabajador si los datos son correctos.")
    void cuandoEliminarTrabajador_entoncesEliminarTrabajador(){

    }
    //--Test asignar turno a trabajador
    @Test
    @DisplayName("Debe asignar un turno a un trabajador si los datos son correctos.")
    void cuandoAsignarTurno_entoncesTurnoAsignado(){

    }



}


