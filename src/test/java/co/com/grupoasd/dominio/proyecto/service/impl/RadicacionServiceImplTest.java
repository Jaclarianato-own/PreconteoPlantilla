/*
* Archivo: RadicacionServiceImplTest
* Fecha: 01/05/2020
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva del GRUPO ASD S.A.S.
* Su uso, alteracion, reproduccion o modificacion sin el debido
* consentimiento por escrito de GRUPO ASD S.A.S. quedan totalmente prohibidos.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
* previstas en la Ley.
*/
package co.com.grupoasd.dominio.proyecto.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.grupoasd.dominio.proyecto.message.RadicacionEjemploResponse;
import co.com.grupoasd.dominio.proyecto.security.AuthenticationHandler;
import co.com.grupoasd.dominio.proyecto.service.impl.RadicacionServiceImpl;
import co.com.grupoasd.dominio.proyecto.util.MockAuthentication;

/**
 * Prueba unitaria RadicacionServiceImpl.
 * @author Nombres Apellidos <correo@grupoasd.com.co>
 */
@ExtendWith(MockitoExtension.class)
public class RadicacionServiceImplTest {
    
    @InjectMocks
    RadicacionServiceImpl radicacionService;
    
    @Mock
    AuthenticationHandler authenticationHandler;
    
    @Test
    @DisplayName("Operacion de ejemplo con id valido.")
    public void ejemplo1() {
        String mensajeRespuesta = "Hola mundo de ejemplo.";
        MockAuthentication.mock(authenticationHandler, "grupoasd", "user");
        Optional<RadicacionEjemploResponse>  res= radicacionService.ejemplo("123456");
        assertTrue(res.isPresent());
        assertEquals(mensajeRespuesta, res.get().getMensaje());
    }
    
    @Test
    @DisplayName("Operacion de ejemplo con id no valido.")
    public void ejemplo2() {
        Optional<RadicacionEjemploResponse>  res= radicacionService.ejemplo("error");
        assertTrue(res.isEmpty());
    }
    
    @Test
    @DisplayName("Operacion de ejemplo con excepcion.")
    public void ejemplo3() {
        String mensajeError = "Exception forzada.";
        Exception exception = assertThrows(
                RuntimeException.class,
                () -> radicacionService.ejemplo("exception"));
        assertEquals(mensajeError, exception.getMessage());
    }

}
