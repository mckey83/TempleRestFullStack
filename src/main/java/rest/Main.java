package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = { "rest" })
public class Main {

	public static void main(String[] args) {
		System.out.println("MAIN");
		SpringApplication.run(Main.class, args);
	}

}
