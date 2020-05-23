/*
* Archivo: AuthenticationHandler
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
package co.com.grupoasd.dominio.proyecto.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Logica de negocio para obtencion del usuario autenticado.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
@Component
public class AuthenticationHandler {

    /**
     * Obtiene el usuario autenticado. 
     * @return Usuario autenticado, si no ese encuentra autenticado retorna null.
     */
    public Claims getAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Claims claims = new Claims();
        claims.setUser(auth.getName());
        if (auth.getAuthorities() != null) {
            for (GrantedAuthority authority: auth.getAuthorities()) {
                claims.getRoles().add(authority.getAuthority());
            }
        }
        return claims;
    }
}
