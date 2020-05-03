/*
* Archivo: LogTrace
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
package co.com.grupoasd.dominio.proyecto.util;

import org.slf4j.Logger;

/**
 * Clase utilitaria para manejo de logs del sistema.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
public final class LogTrace {
    
    /**
     * Esta clase es utilitaria y no puede ser instanceada.
     */
    private LogTrace() {
        
    }
    /**
     * Deja una traza del metodo desde donde se invoca para hacer debug de codigo.
     * @param logger Loggger para realizar la traza.
     */
    public static void trace(Logger logger) {
        logger.debug(Thread.currentThread().getStackTrace()[2].getMethodName());
    }
}
