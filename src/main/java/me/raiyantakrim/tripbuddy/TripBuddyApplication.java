package me.raiyantakrim.tripbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TripBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripBuddyApplication.class, args);
	}

}
