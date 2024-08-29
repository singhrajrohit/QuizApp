package com.rohit.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.rohit.quizapp.model.Users;

//@Repository
public interface UsersDao extends JpaRepository<Users, Integer> {

}
