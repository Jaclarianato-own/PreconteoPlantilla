/*
* Archivo: Claims
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
package co.com.grupoasd.dominio.proyecto.security;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Usuario.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 *
 */
@Data
public class Claims {
    /**
     * Usuario autenticado.
     */
    private String user;
    /**
     * Roles del usuario.
     */
    private List<String> roles = new ArrayList<>();
    /**
     * IP del usuario.
     */
    private String ip;
    /**
     * Identificador de la transaccion.
     */
    private String trx;
    
}
