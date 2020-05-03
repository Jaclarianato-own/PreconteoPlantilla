/*
* Archivo: RadicacionApiControllerTest
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
package co.com.grupoasd.operation.aquarius.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.com.grupoasd.dominio.proyecto.api.RadicacionApiController;
import co.com.grupoasd.dominio.proyecto.message.RadicacionEjemploResponse;
import co.com.grupoasd.dominio.proyecto.service.impl.RadicacionServiceImpl;

/**
 * Prueba unitaria RadicacionApiController.
 * @author Nombres Apellidos <correo@grupoasd.com.co>
 */
@ExtendWith(MockitoExtension.class)
public class RadicacionApiControllerTest {

    @InjectMocks
    RadicacionApiController RadicacionApiController;
    
    @Mock
    RadicacionServiceImpl radicacionService;
    
    @Test
    @DisplayName("Operacion de ejemplo con id valido.")
    public void ejemplo1() {
        String id = "123456";
        String mensajeRes = "Mensaje";
        RadicacionEjemploResponse mockRes = new RadicacionEjemploResponse();
        mockRes.setMensaje("Mensaje");
        when(radicacionService.ejemplo(id)).thenReturn(Optional.of(mockRes));
        ResponseEntity<?> res = RadicacionApiController.ejemplo(id);
        assertEquals(HttpStatus.OK, res.getStatusCode());
        RadicacionEjemploResponse objRes = (RadicacionEjemploResponse) res.getBody();
        assertEquals(mensajeRes, objRes.getMensaje()); 
    }
    
    @Test
    @DisplayName("Operacion de ejemplo con id no valido.")
    public void ejemplo2() {
        String id = "error";
        when(radicacionService.ejemplo(id)).thenReturn(Optional.empty());
        ResponseEntity<?> res = RadicacionApiController.ejemplo(id);
        assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode()); 
    }
}
