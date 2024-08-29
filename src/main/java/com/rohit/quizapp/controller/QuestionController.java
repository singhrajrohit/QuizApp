package com.rohit.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rohit.quizapp.model.Question;
import com.rohit.quizapp.service.QuestionService;


@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    //All Questions Controller
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    
    //Get Questions By Category
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCattegory(@PathVariable String category){
        return questionService.getQuestionsByCattegory(category);
    }

    //Add Question
    @PostMapping("add")
    public ResponseEntity<String> addQuestions(@RequestBody Question question){
        return questionService.addQuestions(question);
    }

    //Delete Question
    @DeleteMapping("delete")
    public ResponseEntity<String> deleteQuestion(@RequestParam Integer Id){
        return questionService.deleteQuestion(Id);
    }

    //Update Question
    @PutMapping("update")
    public ResponseEntity<String>updateQuestion(@RequestParam Integer Id, @RequestBody Question question){
        return questionService.updateQuestion(Id, question);
    }
}
