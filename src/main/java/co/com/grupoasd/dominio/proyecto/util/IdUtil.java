/*
* Archivo: IdUtil
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

import java.util.UUID;

/**
 * Clase utilitaria para generacion de identificadores aleatorios.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
public final class IdUtil {
    
    /**
     * Constructor privado. Esta clase no puede ser instanceada.
     */
    private IdUtil() {
        
    }

    /**
     * Genera un identificador aleatorio de 32 caracteres.
     * @return Cadena con el identificador.
     */
    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
