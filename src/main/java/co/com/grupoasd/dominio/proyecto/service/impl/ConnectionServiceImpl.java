/*
* Archivo: ConnectionServiceImpl
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

package co.com.grupoasd.dominio.proyecto.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import ch.qos.logback.core.pattern.Converter;
import ch.qos.logback.core.util.FileUtil;
import co.com.grupoasd.dominio.proyecto.service.ConnectionService;
import co.com.grupoasd.dominio.proyecto.util.LogTrace;
import lombok.extern.slf4j.Slf4j;

/**
 * @inheritDoc
 * @author Jhonatan Clariana <jclariana@grupoasd.com.co>
 */
@Service
@Slf4j
public class ConnectionServiceImpl implements ConnectionService {

	/**
	 * @inheritDoc
	 * @author Jhonatan Clariana <jclariana@grupoasd.com.co>
	 */
	@Override
	public boolean createBucketAws(String nameBucket) {
		LogTrace.trace(log);

		try {
			AWSCredentials credentials = new BasicAWSCredentials("AKIATEY56CFXZTDYYCHI",
					"wbLwsUJV7gzcB2IRga6k/IllfxG1zIHvjxlO9wK1");

			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2)
					.build();

			s3Client.createBucket(nameBucket);
			return true;
		} catch (AmazonServiceException ae) {
			return false;
		}
	}

	/**
	 * @inheritDoc
	 * @author Jhonatan Clariana <jclariana@grupoasd.com.co>
	 */
	@Override
	public void UploadinObjectsAws(String bucketName) {
		LogTrace.trace(log);

		try {

			String rutaJsons = "C:\\Users\\jclariana\\Desktop\\boletines\\json";
			String rutaPdfs = "C:\\Users\\jclariana\\Desktop\\boletines\\pdf";

			String[] archivos = getListFiles(rutaJsons, rutaPdfs);

			if (null == archivos) {
				throw new Exception("No hay archivos en la ruta");
			}

			AWSCredentials credentials = new BasicAWSCredentials("AKIATEY56CFXZTDYYCHI",
					"wbLwsUJV7gzcB2IRga6k/IllfxG1zIHvjxlO9wK1");

			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2)
					.build();

			for (String archivo : archivos) {
				try {

					boolean esValido = false;

					esValido = validarArchivo(archivo);

					if (!esValido) {
						continue;
					}

					esValido = validarFormato(archivo);

					if (!esValido) {
						continue;
					}

					int codigo = getCodigo(archivo);
					String ext = getExtension(archivo);
					
					File file = null;

					if (ext.equals("pdf")) {
					
						file = new File(rutaPdfs + File.separator + archivo);
						s3Client.putObject(bucketName, codigo+"/" + archivo, file);
					}

					if (ext.equals("json")) {
						file = new File(rutaJsons + File.separator + archivo);
						s3Client.putObject(bucketName, codigo+"/" + archivo, file);
					}

				} catch (Exception e) {
					log.error(e.getMessage());

				}
			}

		} catch (AmazonServiceException ae) {
			log.error(ae.getMessage());

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private int getCodigo(String archivo) {
		LogTrace.trace(log);

		String[] nombreDesglosado = archivo.split("_");

		if (nombreDesglosado[0].equals("BOLETIN")) {
			if(nombreDesglosado.length == 4) {
				String[] aux = nombreDesglosado[3].split(".pdf");
				return Integer.parseInt(aux[0]);
			}else {
				return Integer.parseInt(nombreDesglosado[3]);
			}
			
		}

		if (nombreDesglosado[0].equals("SEN") || nombreDesglosado[0].equals("CAM")) {
			String[] aux = nombreDesglosado[2].split(".json");
			return Integer.parseInt(aux[0]);
		}

		if (nombreDesglosado[0].equals("boletines")) {
			String[] aux = nombreDesglosado[1].split(".json");
			return Integer.parseInt(aux[0]);
		}

		return 0;

	}

	private boolean validarFormato(String archivo) {
		LogTrace.trace(log);

		try {
			String[] nombreDesglosado = archivo.split("_");

			boolean esFormato = false;

			switch (nombreDesglosado[0]) {
			case "BOLETIN":
				esFormato = true;
				break;
			case "boletines":
				esFormato = true;
				break;
			case "CAM":
				esFormato = true;
				break;
			case "SEN":
				esFormato = true;
				break;
			}

			if (!esFormato) {

				throw new Exception("El archivo (" + archivo + ") no contempla un formato válido.");
			}

			if (nombreDesglosado[0].equals("BOLETIN")) {
				if (nombreDesglosado.length < 3) {
					throw new Exception("El archivo (" + archivo + ") no contempla un formato válido.");
				}
				
				if(nombreDesglosado.length == 4) {
					String[] aux = nombreDesglosado[3].split(".pdf");
					if (!isNumeric(aux[0])) {
						throw new Exception("El archivo (" + archivo + ") no contempla un formato válido.");
					}
				}else {
					if (!isNumeric(nombreDesglosado[3])) {
						throw new Exception("El archivo (" + archivo + ") no contempla un formato válido.");
					}
				}
								
			}

			if (nombreDesglosado[0].equals("SEN") || nombreDesglosado[0].equals("CAM")) {
				if (nombreDesglosado.length < 2) {
					throw new Exception("El archivo (" + archivo + ") no contempla un formato válido.");
				}
				
				String[] aux = nombreDesglosado[2].split(".json");

				if (!isNumeric(aux[0])) {
					throw new Exception("El archivo (" + archivo + ") no contempla un formato válido.");
				}
			}

			if (nombreDesglosado[0].equals("boletines")) {
				if (nombreDesglosado.length < 1) {
					throw new Exception("El archivo (" + archivo + ") no contempla un formato válido.");
				}
				
				String[] aux = nombreDesglosado[1].split(".json");
				
				
				if (!isNumeric(aux[0])) {
					throw new Exception("El archivo (" + archivo + ") no contempla un formato válido.");
				}
			}

			return true;

		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}

	}

	private boolean isNumeric(String cadena) {
		LogTrace.trace(log);

		boolean resultado;

		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}

	private boolean validarArchivo(String archivo) {
		LogTrace.trace(log);

		String extension = getExtension(archivo);

		try {
			// Se valida que el archivo contenga una extensión
			if (null == extension) {
				throw new Exception("Archivo " + archivo + " sin extensión");
			}

			return Pattern.matches("^.*\\.(pdf|txt|json)$", archivo);

		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}

	}

	private String getExtension(String archivo) {
		LogTrace.trace(log);

		String extension = null;

		int i = archivo.lastIndexOf('.');
		if (i > 0) {
			extension = archivo.substring(i + 1);
		}

		return extension;
	}

	/**
	 * @inheritDoc
	 * @author Jhonatan Clariana <jclariana@grupoasd.com.co>
	 */
	private String[] getListFiles(String rutaJsons, String rutaPdfs) {
		LogTrace.trace(log);

		try {

			File carpetaJson = new File(rutaJsons);
			File carpetaPdf = new File(rutaPdfs);

			String[] listadoJson = carpetaJson.list();
			String[] listadoPdf = carpetaPdf.list();

			String[] result = Stream.of(listadoJson, listadoPdf).flatMap(Stream::of).toArray(String[]::new);

			return result;

		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

	}

	/**
	 * @inheritDoc
	 * @author Jhonatan Clariana <jclariana@grupoasd.com.co>
	 */
	@Override
	public boolean getBucketsAws() {
		LogTrace.trace(log);

		try {
			AWSCredentials credentials = new BasicAWSCredentials("AKIATEY56CFXZTDYYCHI",
					"wbLwsUJV7gzcB2IRga6k/IllfxG1zIHvjxlO9wK1");

			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2)
					.build();

			List<Bucket> buckets = s3Client.listBuckets();

			for (Bucket bucket : buckets) {
				try {
					System.out.println(bucket.getName());
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}

			return true;
		} catch (AmazonServiceException ae) {

			return false;
		} catch (Exception e) {
			return false;
		}
	}

}
