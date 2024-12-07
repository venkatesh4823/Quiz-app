package com.venkatesh.QuizProject.Service;

import com.venkatesh.QuizProject.Model.Questions;
import com.venkatesh.QuizProject.DAO.QuestionsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServices {

    @Autowired
    QuestionsDAO questionsdao;

    public ResponseEntity<List<Questions>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questionsdao.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionsdao.findAll(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getByCategory(String category){
        try {
            return new ResponseEntity<>(questionsdao.getByCategory(category), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionsdao.getByCategory(category), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Questions question){

         questionsdao.save(question);

         return new ResponseEntity<>("Questions Added Successfully",HttpStatus.CREATED);
    }


    public ResponseEntity<String> deleteByNo(int no) {
        questionsdao.deleteById(no);
        return new ResponseEntity<>("Question deleted Successfully",HttpStatus.OK);
    }
}
