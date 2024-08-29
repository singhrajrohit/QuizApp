package com.rohit.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rohit.quizapp.dao.UsersDao;
import com.rohit.quizapp.model.Users;

@Service
public class UsersService {
    @Autowired
    UsersDao usersDao;
    public ResponseEntity<String> registerUser(Users user) {
        try{
            usersDao.save(user);
            return new ResponseEntity<>("User registered succesfully" ,HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("User was not added successfully: "+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    
}
