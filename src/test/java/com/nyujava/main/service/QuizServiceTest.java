package com.nyujava.main.service;

import com.nyujava.main.model.Question;
import com.nyujava.main.model.QuestionForm;
import com.nyujava.main.repository.QuestionRepo;
import com.nyujava.main.repository.ResultRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    private QuestionRepo questionRepo;

    @Mock
    private ResultRepo resultRepo;

    @InjectMocks
    private QuizService quizService;

    @Test
    void testGetQuestions() {
        // Arrange: Create a mock list of questions
        List<Question> mockQuestions = Arrays.asList(
                new Question(1, "Question 1", "Option A", "Option B", "Option C", 1, -1),
                new Question(2, "Question 2", "Option A", "Option B", "Option C", 2, -1),
                new Question(3, "Question 3", "Option A", "Option B", "Option C", 3, -1),
                new Question(4, "Question 4", "Option A", "Option B", "Option C", 1, -1),
                new Question(5, "Question 5", "Option A", "Option B", "Option C", 2, -1),
                new Question(6, "Question 6", "Option A", "Option B", "Option C", 3, -1)
        );

        // Mock the repository to return the list
        when(questionRepo.findAll()).thenReturn(mockQuestions);

        // Act: Call the getQuestions method
        QuestionForm questionForm = quizService.getQuestions();

        // Assert: Verify that 5 questions are returned
        assertEquals(5, questionForm.getQuestions().size(), "Should return 5 random questions");
    }


    @Test
    void testGetResult() {
        // Arrange
        Question question1 = new Question(1, "Title 1", "A", "B", "C", 1, 1);
        Question question2 = new Question(2, "Title 2", "A", "B", "C", 2, 3);

        QuestionForm questionForm = new QuestionForm();
        questionForm.setQuestions(Arrays.asList(question1, question2));

        // Act
        int correctAnswers = quizService.getResult(questionForm);

        // Assert
        assertEquals(1, correctAnswers, "One question should be correct");
    }

    @Test
    void testGetQuestionsWithEmptyRepo() {
        when(questionRepo.findAll()).thenReturn(new ArrayList<>());

        QuestionForm questionForm = quizService.getQuestions();

        assertNotNull(questionForm, "QuestionForm should not be null");
        assertEquals(0, questionForm.getQuestions().size(), "Should return 0 questions for empty repository");
    }

    @Test
    void testGetQuestionsWithFewerQuestions() {
        List<Question> mockQuestions = Arrays.asList(
                new Question(1, "Question 1", "Option A", "Option B", "Option C", 1, -1),
                new Question(2, "Question 2", "Option A", "Option B", "Option C", 2, -1)
        );

        when(questionRepo.findAll()).thenReturn(mockQuestions);

        QuestionForm questionForm = quizService.getQuestions();

        assertNotNull(questionForm, "QuestionForm should not be null");
        assertEquals(2, questionForm.getQuestions().size(), "Should return all available questions if fewer than 5");
    }


}
