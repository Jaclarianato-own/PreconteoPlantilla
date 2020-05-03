# Servicios de XXX #

Servicios REST para la XXX

### Requerimientos ###

* OpenJDK 11.x LTS
* Apacha Maven 3.3.x o superior

### Introducción ###

Los servicios son desarrollados utilizando Spring Boot + Maven. Las pruebas unitarias deben ser realizadas utilizando JUnit5 y mockito. Por favor referirse a las páginas de ayuda y demás documentación del proyecto Spring Boot y del proyecto Apache Maven.

### Configuraciones de entorno de desarrollo ###

Para realizar las configuraciones de conexión a bases de datos y demás configuraciones para los entornos de desarrollo ubicarse en la carpeta /src/main/resources/ y editar el archivo application-dev.properties y realizar los ajustes correspondientes. En caso de que sea necesario agregar una nueva propiedad de configuración se debe adicionar a los archivos application-dev.properties y application-prod.properties. 

**Por ningún motivo** suba las configuraciones a los respositorios de código a no ser que sea necesario adicionar una nueva llave de configuración.

### Instrucciones de compilación ###

Antes de subir el codigo fuente a los repositorios ejecutar las siguientes tareas maven:

* **mvn clean compile:** para verificar que el código compila correctamente y pasa las validaciones de código estático.
* **mvn clean compile test:** para ejecutar las pruebas unitarias y verificar que todas pasan satisfactoriamente.
* **mvn clean compile verify:** para verificar que las pruebas unitarias cumplen la cobertura correspondiente.

### Verificación de código estático ###

Al ejecutar la tarea maven **mvn clean compile** en la carpeta /target se puede visualizar el archivo checkstyle-result.xml para ver las clases y los errores de código estatico para realizar los correspondientes ajustes. 

### Verificación de cobertura ###

Al ejecutar la tarea maven **mvn clean compile verify** en la carpeta /target/site/jacoco se puede visualizar el archivo index.html para revisar los caminos de los algoritmos que se estan validando correctamente en las pruebas unitarias.

### Ejecución de entorno en modo de desarrollo ###

Por defecto el proyecto se encuentra configurado para ejecutarse con el entorno de desarrollo. Para ejecutarlo se utiliza la siguiente tarea maven:

**mvn spring-boot:run**

Si se quiere ejecutar utilizando un perfil especifico, se debe utilizar el parametro -P y el entorno. Para ejecutar utilizando el entorno de desarrollo:

**mvn -Pdev spring-boot:run**

Si se desea ejecutar a partir del Jar generado utilizar el siguiente comando:

**java -jar -Dspring.profiles.active=dev archivo.jar**

### Generación del "Fat Jar"###

Para generar el archivo Jar con todo lo necesario para ejecutar el proyecto sin requerir de un servidor de aplicaciones, ejecutar la siguiente tarea maven:

**mvn clean compile package**

El archivo Jar generado quedara ubicado en la carpeta /target.

 