package com.nyujava.main;

import com.nyujava.main.service.QuizTimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class SpringBootQuizAppApplication {

	@Autowired
	private QuizTimerService quizTimerService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootQuizAppApplication.class, args);
	}

	@PreDestroy
	public void onShutdown() {
		quizTimerService.shutdown();
	}
}
