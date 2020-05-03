/*
* Archivo: GeneralRuntimeException
* Fecha: 02/05/2020
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
package co.com.grupoasd.dominio.proyecto.exception;

/**
 * Excepcion general de tipo Runtime.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
public class GeneralRuntimeException extends RuntimeException {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -8113643211312220852L;
    
    /**
     * Constructor.
     * @param message Mensaje de la excepcion.
     */
    public GeneralRuntimeException(String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param message Mensaje de la excepcion.
     * @param cause Excepcion raiz.
     */
    public GeneralRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
