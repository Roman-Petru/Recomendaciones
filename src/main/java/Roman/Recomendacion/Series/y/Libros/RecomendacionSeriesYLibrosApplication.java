package Roman.Recomendacion.Series.y.Libros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RecomendacionSeriesYLibrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecomendacionSeriesYLibrosApplication.class, args);
	}

}
