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
