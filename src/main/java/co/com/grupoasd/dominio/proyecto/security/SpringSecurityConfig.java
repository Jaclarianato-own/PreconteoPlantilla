/*
* Archivo: SpringSecurityConfig
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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuracion para autenticacion por cabecera.
 * @author Juan Carlos Castellanos jccastellanos@grupoasd.com.co
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
    /**
     * Perfil de entorno de la aplicacion.
     */
    @Value("${spring.profiles.active}")
    private String activeProfile;
    /**
     * Endpoints que no requieren autenticacion.
     */
    private static final String[] ENDPOINTS_NO_AUTH = new String[] {
            "/v2/api-docs", "/v2/api-docs/",
            "/swagger-resources/**/", "/swagger-resources/**",
            "/swagger-ui.html"
        };

    //private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        // INICIO NUEVO
        http.addFilterAfter(new AuthorizationFilter(activeProfile), UsernamePasswordAuthenticationFilter.class);
        // FIN NUEVO
        
        
        // Entry points
        http.authorizeRequests().antMatchers(ENDPOINTS_NO_AUTH).permitAll()
        .anyRequest().authenticated();
        //http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        // Optional, if you want to test the API from a browser
        // http.httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
