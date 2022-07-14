package kg.cbk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CbkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbkApplication.class, args);
	}

}
