package com.venkatesh.QuizProject.Controller;

import com.venkatesh.QuizProject.Model.QuestionWrapper;
import com.venkatesh.QuizProject.Model.Responses;
import com.venkatesh.QuizProject.Service.QuizServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizServices quizServices;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numOfQuestions,@RequestParam String quizTitle){

        return quizServices.createQuiz(category,numOfQuestions,quizTitle);
    }

    @GetMapping("getQuiz/{quizid}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int quizid){
        return quizServices.getQuiz(quizid);
    }

    @PostMapping("submit/{quizid}")
    public ResponseEntity<String> getMarks(@RequestBody List<Responses> responses,@PathVariable int quizid){

        return quizServices.getMarks(quizid,responses);
    }

}
