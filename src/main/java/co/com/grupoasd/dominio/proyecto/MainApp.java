/*
* Archivo: MainApp
* Fecha: 30/04/2020
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
package co.com.grupoasd.dominio.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase de entrada para ejecutar el proyecto Spring Boot.
 * 
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
@SpringBootApplication
public class MainApp {

    /**
     * Punto de entrada.
     * 
     * @param args Argumentos.
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

}
