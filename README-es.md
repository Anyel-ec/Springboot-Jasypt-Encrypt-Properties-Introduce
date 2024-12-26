# Introduce-Jasypt

Introduce-Jasypt es un proyecto de ejemplo que demuestra el uso de la biblioteca Jasypt (Java Simplified Encryption) para encriptar y desencriptar propiedades sensibles en aplicaciones Spring Boot.

## Características
- **Encriptación de propiedades sensibles** en archivos de configuración.
- **Integración con Jasypt** usando la dependencia `jasypt-spring-boot-starter`.
- **Controlador REST** que expone un endpoint para probar la desencriptación de las propiedades.

## Tecnologías utilizadas
- Java 17
- Spring Boot 3.4.1
- Jasypt Spring Boot Starter 3.0.3
- Maven

---

## Archivos clave

### **Controlador**
El controlador REST expone un endpoint `GET /api/v1/test` que devuelve el valor desencriptado de la propiedad `secretparam`:

```java
package top.anyel.jasypt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 26/12/2024
 */
@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Value("${secretparam}")
    private String secretParam;

    @GetMapping("/test")
    public String test() {
        return secretParam;
    }
}
```

### **Archivo de configuración (`application.properties`)**
El archivo contiene las propiedades encriptadas:

```properties
spring.application.name=Introduce-Jasypt
secretparam=ENC(Pw+GALmpKwBa64TijJYOsEZzmpOhEziwGpBkLEem18aenJrIwtRZyFP8TwDrpN40PViz5TLl8T2e86eWlyHraA==)
server.port=8081
```

### **Clase principal (`IntroduceJasyptApplication`)**
La clase principal habilita la funcionalidad de Jasypt con la anotación `@EnableEncryptableProperties`:

```java
package top.anyel.jasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
public class IntroduceJasyptApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntroduceJasyptApplication.class, args);
    }
}
```

### **POM (`pom.xml`)**
El archivo POM incluye las dependencias necesarias para Spring Boot y Jasypt:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>com.github.ulisesbocchio</groupId>
    <artifactId>jasypt-spring-boot-starter</artifactId>
    <version>3.0.3</version>
</dependency>
```

---

## Configuración

### Clave de encriptación
Se utiliza una clave secreta para encriptar y desencriptar las propiedades. Debes configurar esta clave como una variable de entorno o como un argumento de VM al iniciar la aplicación:

```bash
-Djasypt.encryptor.password=superkey
```

### Encriptación de propiedades
Para encriptar una propiedad, utiliza el siguiente comando Maven:

```bash
mvn jasypt:encrypt-value -Djasypt.encryptor.password=superkey -Djasypt.plugin.value="MySensitiveData"
```

El resultado será una cadena encriptada en formato `ENC(...)`, que puedes agregar al archivo de propiedades.

> **Nota:** Usa la misma clave secreta para encriptar y desencriptar las propiedades.

---

## Ejecución
1. Clona este repositorio:
   ```bash
   git clone https://github.com/Anyel-ec/Springboot-Jasypt-Encrypt-Properties-Introduce
   ```
2. Navega al directorio del proyecto:
   ```bash
   cd Springboot-Jasypt-Encrypt-Properties-Introduce
   ```
3. Asegúrate de configurar la clave de encriptación:
   ```bash
   export JASYPT_ENCRYPTOR_PASSWORD=superkey
   ```
4. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```
5. Accede al endpoint de prueba:
   ```bash
   curl http://localhost:8081/api/v1/test
   ```

---

## Autor
**Anyel EC**
- Github: [Anyel-ec](https://github.com/Anyel-ec)

---

## Licencia
Este proyecto se distribuye bajo la licencia MIT. Puedes ver los detalles completos en el archivo `LICENSE`.

