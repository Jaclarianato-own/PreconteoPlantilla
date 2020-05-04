/*
* Archivo: MockAuthentication
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

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import co.com.grupoasd.dominio.proyecto.security.AuthenticationHandler;
import co.com.grupoasd.dominio.proyecto.security.Claims;

/**
 * Clase utilitaria para pruebas unitarias para hacer mock de la autenticacion.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
public final class MockAuthentication {
    
    /**
     * Esta clase no se puede instancear.
     */
    private MockAuthentication() {
        
    }

    /**
     * Inyect el mock de autenticacion a la prueba unitaria.
     * @param authenticationHandler AuthenticationHandler
     * @param usuario Usuario a inyectar.
     * @param roles Roles del usuario a inyectar.
     */
    public static void mock(AuthenticationHandler authenticationHandler, String usuario, String... roles) {
        Claims claims = new Claims();
        claims.setUser(usuario);
        claims.setRoles(Stream.of(roles)
                .collect(Collectors.toCollection(ArrayList::new)));
        when(authenticationHandler.getAuthentication()).thenReturn(claims);
    }
}
