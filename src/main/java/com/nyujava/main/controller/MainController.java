package com.nyujava.main.controller;

import java.util.List;

import com.nyujava.main.model.QuestionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nyujava.main.model.Result;
import com.nyujava.main.service.QuizService;
import com.nyujava.main.service.QuizTimerService;

@Controller
public class MainController {

	@Autowired
	private QuizService quizService;

	@Autowired
	private QuizTimerService quizTimerService;

	private Result result;
	private Boolean hasSubmitted;

	@ModelAttribute("result")
	public Result provideResultInstance() {
		return result;
	}

	@GetMapping("/")
	public String displayHomePage() {
		return "index.html";
	}

	@PostMapping("/quiz")
	public String initiateQuiz(@RequestParam(name = "username") String username,
							   @RequestParam(name = "duration", defaultValue = "60") int duration,
							   Model model, RedirectAttributes redirectAttributes) {
		if (username.isBlank()) {
			redirectAttributes.addFlashAttribute("warning", "You must enter your name");
			return "redirect:/";
		}

		hasSubmitted = false;
		result = new Result();
		result.setUsername(username);

		// Start the timer for the user
		String userId = username;
		quizTimerService.startTimer(userId, duration, () -> {
			System.out.println("Time's up for " + username + "'s quiz!");
		});

		QuestionForm questionForm = quizService.getQuestions();
		model.addAttribute("qForm", questionForm);
		model.addAttribute("duration", duration); // Pass duration to frontend
		System.out.println(model);

		return "quiz.html";
	}



	@PostMapping("/submit")
	public String processSubmission(@ModelAttribute QuestionForm questionForm, Model model) {
		if (!Boolean.TRUE.equals(hasSubmitted)) {
			int correctAnswers = quizService.getResult(questionForm);
			result.setTotalCorrect(correctAnswers);
			quizService.saveScore(result);
			hasSubmitted = true;
		}

		return "result.html";
	}

	@GetMapping("/score")
	public String displayScoreboard(Model model) {
		List<Result> topScores = quizService.getTopScore();
		model.addAttribute("sList", topScores);

		return "scoreboard.html";
	}
}
