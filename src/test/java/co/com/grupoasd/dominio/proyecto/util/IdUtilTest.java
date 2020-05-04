/*
* Archivo: IdUtilTest
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import co.com.grupoasd.dominio.proyecto.util.IdUtil;

/**
 * Prueba unitaria IdUtil.
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 */
public class IdUtilTest {

    @Test
    @DisplayName("Generacion de un identificador.")
    public void generate1() {
        String id = IdUtil.generate();
        assertEquals(32, id.length());
    }
    
    @Test
    @DisplayName("Generacion de un identificador. Deben ser aleatorios")
    public void generate2() {
        String id1 = IdUtil.generate();
        String id2 = IdUtil.generate();
        assertNotEquals(id1, id2);
    }
}
