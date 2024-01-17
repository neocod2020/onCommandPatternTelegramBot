package com.mycompany.onCommandPatternTelegramBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class OnCommandPatternTelegramBotApplication {

	public static void main(String[] args) {            
            
		SpringApplication.run(OnCommandPatternTelegramBotApplication.class, args);
	}

}
