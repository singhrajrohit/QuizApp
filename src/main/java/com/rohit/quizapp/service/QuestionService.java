package com.rohit.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rohit.quizapp.dao.QuestionDao;
import com.rohit.quizapp.model.Question;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Question>> getQuestionsByCattegory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> addQuestions(Question question) {
        try{
            questionDao.save(question);
            return new ResponseEntity<>("question added successfully",HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not able to add question",HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> deleteQuestion(Integer Id) {
        try{
            Optional<Question> question=questionDao.findById(Id);
            if(question.isPresent()){
            questionDao.deleteById(Id);
                return new ResponseEntity<>("deleted Successfully",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("question not found",HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("An error occured while deleting the question",HttpStatus.INTERNAL_SERVER_ERROR); 
    }
    public ResponseEntity<String> updateQuestion(Integer Id, Question updatedQuestion) {
        try{
            Optional<Question> question=questionDao.findById(Id);
            if(question.isPresent()){
                Question existingQuestion=question.get();
                existingQuestion.setCategory(updatedQuestion.getCategory());
                existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
                existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
                existingQuestion.setOption1(updatedQuestion.getOption1());
                existingQuestion.setOption2(updatedQuestion.getOption2());
                existingQuestion.setOption3(updatedQuestion.getOption3());
                existingQuestion.setOption4(updatedQuestion.getOption4());
                existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
                questionDao.save(existingQuestion);
                
                return new ResponseEntity<>("Updated Successfully",HttpStatus.ACCEPTED);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Questio not found");
            }
        }catch(Exception e){
            return new ResponseEntity<>("Something went wrong while updating question",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
