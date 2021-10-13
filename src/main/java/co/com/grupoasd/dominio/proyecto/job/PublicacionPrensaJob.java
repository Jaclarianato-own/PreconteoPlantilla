/*
* Archivo: PublicacionPrensaJob
* Fecha: 13/10/2021
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

package co.com.grupoasd.dominio.proyecto.job;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import co.com.grupoasd.dominio.proyecto.service.ConnectionService;
import co.com.grupoasd.dominio.proyecto.service.impl.ConnectionServiceImpl;
import co.com.grupoasd.dominio.proyecto.util.LogTrace;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jclariana
 *
 */
@Component
@Slf4j
public class PublicacionPrensaJob {
	
	/**
     * ConnectionService.
     */
	private final ConnectionService connectionService;
	
	public PublicacionPrensaJob(ConnectionService connectionService) {
		this.connectionService = connectionService;
	}
	
	@Scheduled(fixedDelay = 1000)
	public void UploadinObjectsAws() {
		LogTrace.trace(log);
		connectionService.UploadinObjectsAws("boletinespoc");
	}

}
