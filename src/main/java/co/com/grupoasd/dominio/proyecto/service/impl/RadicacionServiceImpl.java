/*
* Archivo: RadicacionServiceImpl
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
package co.com.grupoasd.dominio.proyecto.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;

import co.com.grupoasd.dominio.proyecto.exception.GeneralRuntimeException;
import co.com.grupoasd.dominio.proyecto.message.RadicacionEjemploResponse;
import co.com.grupoasd.dominio.proyecto.security.AuthenticationHandler;
import co.com.grupoasd.dominio.proyecto.security.Claims;
import co.com.grupoasd.dominio.proyecto.service.RadicacionService;
import co.com.grupoasd.dominio.proyecto.util.LogTrace;
import lombok.extern.slf4j.Slf4j;

/**
 * @inheritDoc
 * @author Nombres apellidos <correo@grupoasd.com.co>
 */
@Service
@Slf4j
public class RadicacionServiceImpl implements RadicacionService {
    
    /**
     * AuthenticationHandler.
     */
    private final AuthenticationHandler authenticationHandler;

    /**
     * Mensaje de error para forzar camino. Eliminar.
     */
    private static final String ID_ERROR = "error";
    /**
     * Mensaje de excepcion para forzar camino. Eliminar.
     */
    private static final String ID_EXCEPTION = "exception";
    
    /**
     * Constructor.
     * @param authenticationHandler AuthenticationHandler
     */
    public RadicacionServiceImpl(AuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }
    
    /**
     * @inheritDoc
     * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
     */
    @Override
    public Optional<RadicacionEjemploResponse> ejemplo(String id) {
        LogTrace.trace(log);
        if (id.equalsIgnoreCase(ID_ERROR)) {
            return Optional.empty();
        } else if (id.equalsIgnoreCase(ID_EXCEPTION)) {
            throw new GeneralRuntimeException("Exception forzada.");
        } else {
            RadicacionEjemploResponse res = new RadicacionEjemploResponse();
            res.setMensaje("Hola mundo de ejemplo.");
            // Ejemplo de obtencion del usuario de la peticion.
            Claims claims  = authenticationHandler.getAuthentication();
            res.setUsuario(claims.getUser());
            return Optional.of(res);
        }
    }

}
