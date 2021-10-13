/*
* Archivo: ConnectionService
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

package co.com.grupoasd.dominio.proyecto.service;

/**
 * Servicio para radicacion de documentos.
 * @author Jhonatan Clariana <jclariana@grupoasd.com.co>
 */
public interface ConnectionService {
	
	 /**
     * Operación para crear un bucket.
     * @param nameBuecket Nombre del bucket a crear.
     * @return retornará verdadero si la ejecución fue exitosa.
     */
	public boolean createBucketAws(String nameBucket);
	
	/**
     * Operacion para obtener los buckets.
     * @return retornará verdadero si la ejecución fue exitosa.
     */
	public boolean getBucketsAws();
	
	/**
     * Operacion para cargar los objetos o archivos al S3 de aws.
     * @param nameBuecket Nombre del bucket a cargar con objetos o archivos.
     * @return retornará verdadero si la ejecución fue exitosa.
     */
	public void UploadinObjectsAws(String bucketName);
	
}
