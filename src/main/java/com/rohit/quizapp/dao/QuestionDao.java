package com.rohit.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rohit.quizapp.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question , Integer>{

    List<Question >findByCategory(String category);
    
}
