/*
* Archivo: AuthorizationFilter
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

import java.io.IOException;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.grupoasd.dominio.proyecto.exception.GeneralRuntimeException;
import co.com.grupoasd.dominio.proyecto.util.GlobalConstants;
import co.com.grupoasd.dominio.proyecto.util.LogTrace;
import lombok.extern.slf4j.Slf4j;

/**
 * Filtro para manejar la auteticacion.
 * 
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {
    
    /**
     * Perfil de entorno de la aplicacion.
     */
    private String activeProfile;
    
    /**
     * Usuario anonimo.
     */
    private static final String ANONYMOUS_USER = "anonymous";
    /**
     * Rol anonimo.
     */
    private static final String ANONYMOUS_ROL = "anonymous";
    /**
     * Clave por defecto.
     */
    private static final String DEFAULT_PASS = "password";
    
    /**
     * Constructor.
     * @param activeProfile Perfil activo en el entorno.
     */
    public AuthorizationFilter(String activeProfile) {
        this.activeProfile = activeProfile;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        LogTrace.trace(log);
        Claims claims = getClaims(request);
        if (!ANONYMOUS_USER.equals(claims.getUser())) {
            setUpSpringAuthentication(claims);
        } else {
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }

    /**
     * Obtiene el usuario partir de la cabecera teniendo enc uenta el perfil del entorno.
     * 
     * @param request HttpServletRequest
     * @return Claims
     */
    private Claims getClaims(HttpServletRequest request) {
        LogTrace.trace(log);
        log.debug("Perfil {}", activeProfile);
        if (GlobalConstants.PROFILE_DEV.equals(activeProfile)) {
            return getClaimsDev(request);
        } else if (GlobalConstants.PROFILE_PROD.equals(activeProfile)) {
            return getClaimsProd(request);
        } else {
            throw new GeneralRuntimeException(String.format("Perfil: %s no valido para el servicio", 
                    activeProfile));
        }
    }
    
    /**
     * Obtiene el usuario partir de la cabecera. Cuando el perfil esta en modo desarrollo.
     * 
     * @param request HttpServletRequest
     * @return Claims
     */
    private Claims getClaimsDev(HttpServletRequest request) {
        LogTrace.trace(log);
        Claims claims = new Claims();
        if (request.getHeader(GlobalConstants.HEADER_QOS_DEV) != null) {
            String header = request.getHeader(GlobalConstants.HEADER_QOS_DEV);
            header = header.replace(GlobalConstants.BEARER_KEY, "").trim();
            String json = new String(Base64.getDecoder().decode(header));
            ObjectMapper mapper = new ObjectMapper();
            try {
                claims = mapper.readValue(json, Claims.class); 
            } catch (JsonProcessingException e) {
                log.info(e.getMessage());
                claims.setUser(ANONYMOUS_USER);
                claims.getRoles().add(ANONYMOUS_ROL);
            }            
        } else {
            claims.setUser(ANONYMOUS_USER);
            claims.getRoles().add(ANONYMOUS_ROL);
        }
        return claims;
    }
    
    /**
     * Obtiene el usuario partir de la cabecera. Cuando el perfil esta en modo produccion.
     * 
     * @param request HttpServletRequest
     * @return Claims
     */
    private Claims getClaimsProd(HttpServletRequest request) {
        LogTrace.trace(log);
        Claims claims = new Claims();
        if (request.getHeader(GlobalConstants.HEADER_QOS_PROD) != null) {
            String json = new String(Base64.getDecoder().decode(request.getHeader(GlobalConstants.HEADER_QOS_PROD)));
            ObjectMapper mapper = new ObjectMapper();
            try {
                claims = mapper.readValue(json, Claims.class); 
            } catch (JsonProcessingException e) {
                log.info(e.getMessage());
                claims.setUser(ANONYMOUS_USER);
                claims.getRoles().add(ANONYMOUS_ROL);
            }
        } else {
            claims.setUser(ANONYMOUS_USER);
            claims.getRoles().add(ANONYMOUS_ROL);
        }
        return claims;
    }

    /**
     * Coloca el usuario en el contexto de Spring.
     * 
     * @param claims Claims
     */
    private void setUpSpringAuthentication(Claims claims) {
        LogTrace.trace(log);
        UserDetails us = User.withUsername(claims.getUser()).password(DEFAULT_PASS)
                .authorities(claims.getRoles().toArray(new String[0])).accountExpired(false).accountLocked(false)
                .credentialsExpired(false).disabled(false).build();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(us, "", us.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
