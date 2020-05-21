/*
* Archivo: GlobalConstants
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
package co.com.grupoasd.dominio.proyecto.util;

/**
 * Constantes globales.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
public final class GlobalConstants {
    
    /**
     * Esta clase no puede ser instanciada.
     */
    private GlobalConstants() {
        
    }
    /**
     * Identificador del perfil en modo produccion.
     */
    public static final String PROFILE_PROD = "prod";
    /**
     * Identificador del perfil en modo desarrollo.
     */
    public static final String PROFILE_DEV = "dev";
    /**
     * Cabecera de QOS para perfil de produccion.
     */
    public static final String HEADER_QOS_PROD = "qos";
    /**
     * Cabecera de QOS para perfil de desarrollo.
     */
    public static final String HEADER_QOS_DEV = "authorization";
    /**
     * Nombre de la llave para la autenticacion bearer.
     */
    public static final String BEARER_KEY = "Bearer";
    /**
     * Mensaje de error por defecto para las excepciones.
     */
    public static final String EXCEPTION_MESSAGE = "Se ha presentado un error interno. "
            + "Por favor intente mas tarde. Si el error persiste, por favor informe del error "
            + "utilizando el valor de errorCode";
}
