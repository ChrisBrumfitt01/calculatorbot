package com.topcoder.calculatorbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"me.ramswaroop.jbot",
		"com.topcoder.calculatorbot"
})
public class CalculatorbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorbotApplication.class, args);
	}

}
