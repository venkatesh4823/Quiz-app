package com.venkatesh.QuizProject.Service;

import com.venkatesh.QuizProject.DAO.QuestionsDAO;
import com.venkatesh.QuizProject.DAO.QuizDAO;
import com.venkatesh.QuizProject.Model.QuestionWrapper;
import com.venkatesh.QuizProject.Model.Questions;
import com.venkatesh.QuizProject.Model.Quiz;
import com.venkatesh.QuizProject.Model.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServices {

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuestionsDAO questionsDAO;

    public ResponseEntity<String> createQuiz(String category, int numOfQuestions, String quizTitle) {

       try {
           List<Questions> questions1=questionsDAO.findQuestionsByCategory(category, numOfQuestions);
           Quiz quiz = new Quiz();
           quiz.setQuizTitle(quizTitle);
           quiz.setQuestions(questions1);

           quizDAO.save(quiz);
           return new ResponseEntity<>("Succefully created" , HttpStatus.CREATED);
       }
       catch(Exception e){
           e.printStackTrace();
       }

        return new ResponseEntity<>("Failed" , HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int quizId) {
        try{
            Optional<Quiz> quiz=quizDAO.findById(quizId);
            List<Questions> questions= quiz.get().getQuestions();
            List<QuestionWrapper> questionForUsers=new ArrayList<>();
            for(Questions q:questions){
                    QuestionWrapper questionWrapper=new QuestionWrapper(q.getNo(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
                    questionForUsers.add(questionWrapper);
            }

            return new ResponseEntity(questionForUsers,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> getMarks(int quizid, List<Responses> responses) {
        try{
            Optional<Quiz> quiz=quizDAO.findById(quizid);
            List<Questions> questions=quiz.get().getQuestions();

            int correctAnswer=0;
            int i=0;
            for(Questions q:questions){
                if(q.getCorrectOption().equals(responses.get(i++).getOption())) {
                    correctAnswer++;
                }
            }

            return new ResponseEntity<>("Marks obtained:"+correctAnswer ,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
    }
}
