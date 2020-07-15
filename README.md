# Servicios de XXX #

Servicios REST para la XXX

### Requerimientos ###

* OpenJDK 11.x LTS
* Apacha Maven 3.3.x o superior

### Introducción ###

Los servicios son desarrollados utilizando Spring Boot + Maven. Las pruebas unitarias deben ser realizadas utilizando JUnit5 y mockito. Por favor referirse a las páginas de ayuda y demás documentación del proyecto Spring Boot y del proyecto Apache Maven.

### Estructura de paquetes ###

La estructura de paquetes del proyecto sigue la nomenclatura establecida en el documento “Instructivo servicios REST con tecnología Spring Boot” en la sección “1.5. Estructura de capas, paquetes y nomenclatura”

### Configuraciones de ambientes y entorno de desarrollo ###

El proyecto viene configurado con dos tipos de ambientes un ambiente de desarrollo y un ambiente productivo, ambos poseen configuraciones separadas en los siguientes archivos de propiedades los cuales se encuentran en la carpeta /src/resources:

-	application-dev.properties:
-	application.prod.properties:

Para desarrollo se debe editar el archivo application-dev.properties y configurar las URL de los entornos de desarrollo como por ejemplo la conexión a la base de datos.

En caso de que sea necesario agregar una nueva propiedad de configuración se debe adicionar a los archivos application-dev.properties y application-prod.properties. Para el caso del application-prod es necesario tener en cuenta que las configuraciones se realizar como variables de entorno para que estas sean configuradas en el servidor.

**Por ningún motivo** suba las configuraciones a los respositorios de código a no ser que sea necesario adicionar una nueva llave de configuración.

### Instrucciones de compilación ###

Antes de subir el codigo fuente a los repositorios ejecutar las siguientes tareas maven:

* **mvn clean install:** esta tarea ejecuta todas las tareas necesarias de compilación, ejecución de pruebas unitarias y verificación de cobertura.

También se pueden ejecutar las siguientes tareas de manera separada:

* **mvn clean compile:** para verificar que el código compila correctamente y pasa las validaciones de código estático.
* **mvn clean compile test:** para ejecutar las pruebas unitarias y verificar que todas pasan satisfactoriamente.
* **mvn clean compile verify:** para verificar que las pruebas unitarias cumplen la cobertura correspondiente.

### Pruebas unitarias ###

Las pruebas unitarias se encuentran en /src/test y deben ser realizadas con JUnit 5 y Mockito, los paquetes de cada prueba se deben llamar igual que el paquete donde se encuentra la clase a probar, el nombre de cada prueba debe finalizar con “Test” para que pueda ser ejecutada por JUnit. Es **importante** tener en cuenta que la inyección de dependencias de Spring Boot debe realizarse a través de constructores y no a través de la anotación @Autowired para que se puedan inyectar mocks en las pruebas unitarias, si estoy no se realiza, las objectos de tipo Mock no serán inyectadas y se generaran errores al ejecutar las pruebas unitarias.

### Verificación de código estático ###

Al ejecutar la tarea maven **mvn clean compile** en la carpeta /target se puede visualizar el archivo checkstyle-result.xml para ver las clases y los errores de código estatico para realizar los correspondientes ajustes. 

### Verificación de cobertura ###

Al ejecutar la tarea maven **mvn clean compile test verify** en la carpeta /target/site/jacoco se puede visualizar el archivo index.html para revisar los caminos de los algoritmos que se estan validando correctamente en las pruebas unitarias.

### Ejecución de entorno en modo de desarrollo ###

Por defecto el proyecto se encuentra configurado para ejecutarse con el entorno de desarrollo. Para ejecutarlo se utiliza la siguiente tarea maven:

**mvn spring-boot:run**

Si se quiere ejecutar utilizando un perfil especifico, se debe utilizar el parametro -P y el entorno. Para ejecutar utilizando el entorno de desarrollo:

**mvn -Pdev spring-boot:run**

Si se desea ejecutar a partir del Jar generado utilizar el siguiente comando:

**java -jar -Dspring.profiles.active=dev archivo.jar**

### Generación de "Fat Jar"###

Para generar el archivo Jar con todo lo necesario para ejecutar el proyecto sin requerir de un servidor de aplicaciones, ejecutar la siguiente tarea maven:

**mvn clean compile package**

El archivo Jar generado quedara ubicado en la carpeta /target.

### Manejo de autenticación ###

La autenticación se realiza a través de un token JWT a través de la cabecera HTTP “Authentication” como se establece en el documento “Lineamientos servicios REST” sección 2.4 Autenticación”. 

Por defecto todos los servicios REST expuestos por el proyecto requieren de autenticación, al ser servicios de backend se debe enviar una cabecera HTTP Authentication de la siguiente forma: 

Authentication: Bearer <Token>

El token es una cadena de texto que contiene la siguiente estructura con formato JSON codificada con base64:

{
    "user":"usuario1@grupoasd.com.co", 
    "roles": ["rol1", "rol2"]
}

Como ejemplo para la estructura JSON anterior, se debería enviar la siguiente cabecera:

Authentication: Bearer eyJ1c2VyIjoidXN1YXJpbzFAZ3J1cG9hc2QuY29tLmNvIiwgInJvbGVzIjogWyJyb2wxIiwgInJvbDIiXX0K

### Manejo de excepciones ###

El proyecto tiene implementado un manejador dentro del paquete config llamado RestResponseEntityExceptionHandler el cual se encarga de capturar todas las excepciones no controladas que se generen, almacenarlas en log del sistema con el cotenido de la excepción junto con un codigo unico de identificación del error, este mensaje junto con el código de error es retornado como objeto JSON como error 500, con este código unico de error el clinte puede reportar el error y asi poder ubicar el error en logs junto con toda la traza. 

Dentro de las propiedades de configuración del proyecto (archivo .properties) se encuentra una llave llamada "app.api.showexceptions" que permite que sea o no visible el contenido del error dentro del JSON de respuesta. En producción se recomienda colocar esta propiedad en false para que se muestre un error generico y asi no exponer mensajes de error técnicos los cuales pueden generar vulnerabilidades de seguridad.

Teniendo en cuenta que ese manejador existe en el proyecto, las excepciones que no puedan ser controladas deben ser disparadas hacia arriba de la pila de llamados sea utilizando la clausula throws en la firma del método o capturando la excepción y disparando una excepción de tipo Runtime para que sea capturada por el manejador.

### Manejo de logs del sistema ###

Todos los logs de sistema es decir logs que manejen excepciones o información de debug de la aplicación deben ser manejados utilizando slf4j, para esto en la clase donde se requiera el log se debe colocar la anotación de lombook @Slf4j a nivel de clase y dentro de la clase utilizar el objeto log para generar los logs.

Adicionalmente para facilitar el debug de la aplicación en la primera línea de cada método se debe agregar la siguiente línea:

LogTrace.trace(log);