package com.venkatesh.QuizProject.DAO;

import com.venkatesh.QuizProject.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsDAO extends JpaRepository<Questions,Integer> {
        List<Questions> getByCategory(String category);

    @Query(value = "SELECT * FROM questions q where q.category= :category ORDER BY RAND() LIMIT :numOfQuestions",nativeQuery = true)
    List<Questions> findQuestionsByCategory(String category, int numOfQuestions);
}
