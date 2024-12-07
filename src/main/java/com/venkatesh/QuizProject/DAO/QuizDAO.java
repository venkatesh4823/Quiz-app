package com.venkatesh.QuizProject.DAO;

import com.venkatesh.QuizProject.Model.Questions;
import com.venkatesh.QuizProject.Model.Quiz;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizDAO extends JpaRepository<Quiz,Integer> {


}
