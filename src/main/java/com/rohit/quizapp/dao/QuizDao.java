package com.rohit.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
