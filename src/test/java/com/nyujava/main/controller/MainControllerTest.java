package com.nyujava.main.controller;

import com.nyujava.main.model.QuestionForm;
import com.nyujava.main.service.QuizService;
import com.nyujava.main.service.QuizTimerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MainControllerTest {

    @Mock
    private QuizService quizService;

    @Mock
    private QuizTimerService quizTimerService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private MainController mainController;

    @Test
    void testDisplayHomePage() {
        String view = mainController.displayHomePage();
        assertEquals("index.html", view, "The view name should be index.html");
    }

    @Test
    void testInitiateQuiz_withValidUsername() {
        String username = "testUser";
        int duration = 60;
        QuestionForm questionForm = new QuestionForm();

        when(quizService.getQuestions()).thenReturn(questionForm);

        String view = mainController.initiateQuiz(username, duration, model, redirectAttributes);

        verify(quizTimerService).startTimer(eq(username), eq(duration), any(Runnable.class));
        verify(model).addAttribute(eq("qForm"), eq(questionForm));
        verify(model).addAttribute(eq("duration"), eq(duration));
        assertEquals("quiz.html", view, "The view name should be quiz.html");
    }

    @Test
    void testInitiateQuiz_withBlankUsername() {
        String username = "";
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        String view = mainController.initiateQuiz(username, 60, model, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("warning", "You must enter your name");
        assertEquals("redirect:/", view, "The view name should redirect to /");
    }
}
