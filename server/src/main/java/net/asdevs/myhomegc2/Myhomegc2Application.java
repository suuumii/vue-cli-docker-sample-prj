package net.asdevs.myhomegc2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Myhomegc2Application {

	public static void main(String[] args) {
		SpringApplication.run(Myhomegc2Application.class, args);
	}

}
