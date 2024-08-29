package com.rohit.quizapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("view")
public class ViewController {
    //view all questions
    @GetMapping("allQuestions")
    public String allQuestionsPage(){
        return "getAllQuestions";
    }

    //View categories
    @GetMapping("categories")
    public String categoriesPage(){
        return "categories"; 
    }

    //view question by categories
    @GetMapping("questions/{category}")
    public String questionsByCategory(@PathVariable String category){
        return "questionsByCategory";
    }
}

