package com.rohit.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rohit.quizapp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question , Integer>{
    @Query(value = "SELECT * FROM question q WHERE LOWER(q.category) = LOWER(:category)", nativeQuery = true)
    List<Question >findByCategory(@Param("category")String category);
    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
    
}
