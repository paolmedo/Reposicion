package com.reposicion.ventas;

import com.reposicion.ventas.client.SucursalClient;
import com.reposicion.ventas.dto.SucursalDTO;
import com.reposicion.ventas.dto.VentasDTO;
import com.reposicion.ventas.model.Ventas;
import com.reposicion.ventas.repository.VentasRepository;
import com.reposicion.ventas.service.VentasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VentasServiceTest {

    @Mock
    private VentasRepository ventasRepository;

    @Mock
    private SucursalClient sucursalClient;

    @InjectMocks
    private VentasService ventasService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarVenta_Exito() {
        // Given (Prepara datos)
        VentasDTO requestDTO = new VentasDTO();
        requestDTO.setSucursalId(1L);
        requestDTO.setProducto("Monitor");
        requestDTO.setCantidad(2);
        requestDTO.setTotal(250000.0);

        Ventas ventaGuardada = new Ventas();
        ventaGuardada.setId(10L);
        ventaGuardada.setSucursalId(1L);
        ventaGuardada.setProducto("Monitor");
        ventaGuardada.setCantidad(2);
        ventaGuardada.setTotal(250000.0);
        ventaGuardada.setEstado("ACTIVA");
        ventaGuardada.setFechaVenta(LocalDateTime.now());

        SucursalDTO sucursalMock = new SucursalDTO();
        when(sucursalClient.obtenerSucursal(1L)).thenReturn(sucursalMock);
        when(ventasRepository.save(any(Ventas.class))).thenReturn(ventaGuardada);

        // When (Ejecuta el método)
        VentasDTO resultado = ventasService.registrarVenta(requestDTO);

        // Then (Verifica los resultados)
        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());
        assertEquals("ACTIVA", resultado.getEstado());
        verify(sucursalClient, times(1)).obtenerSucursal(1L);
        verify(ventasRepository, times(1)).save(any(Ventas.class));
    }
    void eliminarVenta_Exito() {
        // Given (Venta existente activa)
        Ventas ventaActiva = new Ventas();
        ventaActiva.setId(1L);
        ventaActiva.setEstado("ACTIVA");

        // Simulamos que la BD encuentra la venta
        when(ventasRepository.findById(1L)).thenReturn(java.util.Optional.of(ventaActiva));
        when(ventasRepository.save(any(Ventas.class))).thenReturn(ventaActiva);

        // When (Intentar eliminar)
        boolean resultado = ventasService.eliminarVenta(1L);

        // Then (Verificar anulación lógica)
        assertTrue(resultado);
        assertEquals("ANULADA", ventaActiva.getEstado());
        verify(ventasRepository, times(1)).findById(1L);
        verify(ventasRepository, times(1)).save(ventaActiva);
    }
}
