package OldFashionPound;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OldFashionPoundApplication implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory.getLogger(OldFashionPoundApplication.class);

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(OldFashionPoundApplication.class, args);
		LOG.info("APPLICATION FINISHED");
	}
	
	@Override
    public void run(String... args) {
    }

}
