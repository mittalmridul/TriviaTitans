package com.nyujava.main.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nyujava.main.model.QuestionForm;
import com.nyujava.main.model.Result;
import com.nyujava.main.repository.QuestionRepo;
import com.nyujava.main.repository.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.nyujava.main.model.Question;

@Service
public class QuizService {

	@Autowired
	Question question;
	@Autowired
    QuestionForm qForm;
	@Autowired
    QuestionRepo qRepo;
	@Autowired
    Result result;
	@Autowired
    ResultRepo rRepo;

	public QuestionForm getQuestions() {
		if (qForm == null) {
			qForm = new QuestionForm(); // Initialize if null
		}

		List<Question> allQues = new ArrayList<>(qRepo.findAll());
		List<Question> qList = new ArrayList<>();

		Random random = new Random();

		for (int i = 0; i < 5; i++) {
			if (allQues.isEmpty()) {
				break; // Avoid IndexOutOfBoundsException if the list becomes empty
			}
			int rand = random.nextInt(allQues.size());
			qList.add(allQues.get(rand));
			allQues.remove(rand);
		}

		qForm.setQuestions(qList);
		return qForm;
	}



	public int getResult(QuestionForm qForm) {
		int correct = 0;

		for(Question q: qForm.getQuestions())
			if(q.getAns() == q.getChose())
				correct++;

		return correct;
	}

	public void saveScore(Result result) {
		Result saveResult = new Result();
		saveResult.setUsername(result.getUsername());
		saveResult.setTotalCorrect(result.getTotalCorrect());
		rRepo.save(saveResult);
	}

	public List<Result> getTopScore() {
		List<Result> sList = rRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));

		return sList;
	}
}
