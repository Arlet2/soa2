package su.arlet.soa2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Soa2Application {

	public static void main(String[] args) {
		SpringApplication.run(Soa2Application.class, args);
	}

}
