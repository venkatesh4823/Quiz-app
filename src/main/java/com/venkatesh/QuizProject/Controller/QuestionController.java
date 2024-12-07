package com.venkatesh.QuizProject.Controller;

import com.venkatesh.QuizProject.Model.Questions;
import com.venkatesh.QuizProject.Service.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionServices questionServices;

    @GetMapping("/allquestions")
    public ResponseEntity<List<Questions>> getAllQuestions(){

        return questionServices.getAllQuestions();
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Questions>> getByCategory(@PathVariable String category){

        return questionServices.getByCategory(category);

    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question){
        return questionServices.addQuestion(question);
    }

    @DeleteMapping("/deleteQuestion/{no}")
    public ResponseEntity<String> deleteByNo(@PathVariable int no){
        return questionServices.deleteByNo(no);
    }


}
