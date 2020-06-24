/*
* Archivo: RestResponseEntityExceptionHandler
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
package co.com.grupoasd.dominio.proyecto.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.grupoasd.dominio.proyecto.message.ErrorResponse;
import co.com.grupoasd.dominio.proyecto.util.GlobalConstants;
import co.com.grupoasd.dominio.proyecto.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Interceptor de excepcion para convertirlas en mensajes de error JSON personalizados.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com>
 */
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    /**
     * Indica si se deben mostrar las excepciones en las respuesta de error.
     */
    @Value("${app.api.showexceptions}")
    boolean showExceptions;
    
    /**
     * Permite interceptar cualquier excepcion y generar un JSON con un codigo de error generado
     * el cual se puede buscar en el log para facilitar el soporte y un mensaje generico por seguridad.
     * @param e Exception
     * @return ResponseEntity<ErrorResponse>
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorResponse> generalException(Exception e) {
        String errorCode = IdUtil.generate();
        String mensajeLog = String.format("%s - %s", errorCode, e.getMessage());
        log.error(mensajeLog, e);
        ErrorResponse error = new ErrorResponse();
        error.setErrorcode(errorCode);
        error.setTimestamp(new Date().toString());
        if (showExceptions) {
            error.setMessage(e.getMessage());
        } else {
            error.setMessage(GlobalConstants.EXCEPTION_MESSAGE);
        }
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
