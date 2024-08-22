package com.rohit.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rohit.quizapp.dao.QuestionDao;
import com.rohit.quizapp.dao.QuizDao;
import com.rohit.quizapp.model.Question;
import com.rohit.quizapp.model.QuestionWrapper;
import com.rohit.quizapp.model.Quiz;
import com.rohit.quizapp.model.Response;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try{
            List<Question> questions =questionDao.findRandomQuestionsByCategory(category, numQ);

            Quiz quiz=new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);
            return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something Went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        try{
            Optional<Quiz> quiz=quizDao.findById(id);
             if(!quiz.isPresent()){
                return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
            }

            List<Question> questionFromDB =quiz.get().getQuestions();
            List<QuestionWrapper> questionForUser = new ArrayList<>();

            for(Question q : questionFromDB){
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());

                questionForUser.add(qw);
            }

            return new ResponseEntity<>(questionForUser,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
        try{
            Optional<Quiz> quiz=quizDao.findById(id);
            //Quiz quiz=quizDao.findById(id).get();
            //if (quiz.isPresent()){
            //     List<Question> questions = quiz.getQuestions();
            // }
            List<Question> questions = quiz.get().getQuestions();

            int right=0;
            int i=0;
            for(Response response:responses){
                if(/*i <questions.size() &&*/response.getResponse().equals(questions.get(i).getRightAnswer())){
                    right++;
                }
                i++;
            }
            return new ResponseEntity<>(right,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(-1,HttpStatus.NOT_FOUND);
        }

    }


}
