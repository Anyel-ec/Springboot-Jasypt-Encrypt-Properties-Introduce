# Introduce-Jasypt

Introduce-Jasypt is a sample project that demonstrates the use of the Jasypt (Java Simplified Encryption) library to encrypt and decrypt sensitive properties in Spring Boot applications.

## Features
- **Encryption of sensitive properties** in configuration files.
- **Integration with Jasypt** using the `jasypt-spring-boot-starter` dependency.
- **REST Controller** exposing an endpoint to test the decryption of properties.

## Technologies Used
- Java 17
- Spring Boot 3.4.1
- Jasypt Spring Boot Starter 3.0.3
- Maven

---

## Key Files

### **Controller**
The REST controller exposes a `GET /api/v1/test` endpoint that returns the decrypted value of the `secretparam` property:

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

### **Configuration File (`application.properties`)**
The file contains the encrypted properties:

```properties
spring.application.name=Introduce-Jasypt
secretparam=ENC(Pw+GALmpKwBa64TijJYOsEZzmpOhEziwGpBkLEem18aenJrIwtRZyFP8TwDrpN40PViz5TLl8T2e86eWlyHraA==)
server.port=8081
```

### **Main Class (`IntroduceJasyptApplication`)**
The main class enables Jasypt functionality with the `@EnableEncryptableProperties` annotation:

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
The POM file includes the necessary dependencies for Spring Boot and Jasypt:

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

## Configuration

### Encryption Key
A secret key is used to encrypt and decrypt properties. You need to configure this key as an environment variable or as a VM argument when starting the application:

```bash
-Djasypt.encryptor.password=superkey
```

### Encrypting Properties
To encrypt a property, use the following Maven command:

```bash
mvn jasypt:encrypt-value -Djasypt.encryptor.password=superkey -Djasypt.plugin.value="MySensitiveData"
```

The result will be an encrypted string in the format `ENC(...)`, which you can add to the properties file.

> **Note:** Use the same secret key to encrypt and decrypt properties.

---

## Execution
1. Clone this repository:
   ```bash
   git clone https://github.com/Anyel-ec/Springboot-Jasypt-Encrypt-Properties-Introduce
   ```
2. Navigate to the project directory:
   ```bash
   cd Springboot-Jasypt-Encrypt-Properties-Introduce
   ```
3. Ensure the encryption key is configured:
   ```bash
   export JASYPT_ENCRYPTOR_PASSWORD=superkey
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
5. Access the test endpoint:
   ```bash
   curl http://localhost:8081/api/v1/test
   ```

---

## Author
**Anyel EC**
- Github: [Anyel-ec](https://github.com/Anyel-ec)

---

## License
This project is distributed under the MIT License. You can find the full details in the `LICENSE` file.

