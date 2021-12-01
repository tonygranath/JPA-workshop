package se.lexicon.tonygranath.jpaworkshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.lexicon.tonygranath.jpaworkshop.data.AppUserDAORepository;
import se.lexicon.tonygranath.jpaworkshop.data.DetailsDAORepository;
import se.lexicon.tonygranath.jpaworkshop.model.Details;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

@SpringBootApplication
public class JpaWorkshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaWorkshopApplication.class, args);
	}

}
