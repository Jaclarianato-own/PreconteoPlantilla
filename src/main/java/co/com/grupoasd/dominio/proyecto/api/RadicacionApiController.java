/*
* Archivo: RadicacionApiController
* Fecha: 30/04/2020
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
package co.com.grupoasd.dominio.proyecto.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.grupoasd.dominio.proyecto.message.RadicacionEjemploResponse;
import co.com.grupoasd.dominio.proyecto.service.RadicacionService;
import co.com.grupoasd.dominio.proyecto.util.LogTrace;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;

/**
 * API para radicacion de documentos.
 * @author Nombres Apellidos <correo@grupoasd.com.co>
 */
@Api(value = "radicaciones", description = "API para radicacion de documentos.", tags = { "radicaciones" })
@RequestMapping("${app.context-api}/radicaciones")
@RestController
@Slf4j
public class RadicacionApiController {
    
    /**
     * RadicacionService.
     */
    private final RadicacionService radicacionService;
    
    /**
     * Constructor.
     * @param radicacionService RadicacionService.
     */
    public RadicacionApiController(RadicacionService radicacionService) {
        this.radicacionService = radicacionService;
    }

    /**
     * Operacion de ejemplo. Por favor eliminar.
     * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
     * @param id Identificador.
     * @return ResponseEntity<?>
     */
    @ApiOperation(value = "Operacion de ejemplo.", 
            nickname = "ejemplo",
            notes = "Operacion de ejemplo que debe ser eliminada.",
                    tags = { "radicaciones" })
    @ApiResponses(value = {
        @ApiResponse(
                code = 200, message = "Cadena de ejemplo hola mundo.", 
                response = RadicacionEjemploResponse.class),
        @ApiResponse(
                code = 400, message = "Identificador no valido.")
        }
    )
    @GetMapping
    public ResponseEntity<?> ejemplo(@ApiParam(required = true, value = "Identificador de ejemplo") 
        @RequestParam String id) {
        LogTrace.trace(log);
        return radicacionService.ejemplo(id)
                .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
