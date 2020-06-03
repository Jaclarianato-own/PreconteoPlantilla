/*
* Archivo: SpringFoxConfig
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
package co.com.grupoasd.dominio.proyecto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import co.com.grupoasd.dominio.proyecto.util.GlobalConstants;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuraciones para Spring Fox Swagger.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com>
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    
    /**
     * Perfil de entorno de la aplicacion.
     */
    @Value("${spring.profiles.active}")
    private String activeProfile;

    /**
     * Se habilita Spring Fox.
     * @return Docket
     */
    @Bean
    public Docket api() { 
        if (GlobalConstants.PROFILE_DEV.equals(activeProfile)) {
            // En modo desarrollo se expone el swagger
            return new Docket(DocumentationType.SWAGGER_2)  
              .select()                                  
              .apis(RequestHandlerSelectors.any())              
              .paths(PathSelectors.any())                          
              .build();                                           
        } else {
            // En modo produccion se oculta el swagger
            return new Docket(DocumentationType.SWAGGER_2)  
                .select()                                  
                .apis(RequestHandlerSelectors.none())              
                .paths(PathSelectors.none())                          
                .build();    
        }
    }
}
