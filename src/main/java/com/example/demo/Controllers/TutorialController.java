package com.example.demo.Controllers;


import com.example.demo.Models.Tutorial;
import com.example.demo.Services.TutorialServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TutorialController {


    @Autowired
    private TutorialServices tutorialServices;
    @PostMapping("/add")
    public Tutorial createTutorial(@RequestBody Tutorial newTutorial) {
        return tutorialServices.createTutorial(newTutorial);
    }
    @GetMapping("/tutorials")
    public ArrayList<Tutorial> getAllTutorials() {
        return (ArrayList<Tutorial>) tutorialServices.getAllTutorials();
    }
    @GetMapping("/greetings")
    public String greetings() {

        return "hola desde la peticion get de tutorial project";
    }

    @PutMapping("/update/{id}")
    public Tutorial updateTutorial(@PathVariable("id")int id, @RequestBody Tutorial updatedTutorial){
        Optional<Tutorial> existingTutorialOptional = tutorialServices.getTutorialById(id);

        if (existingTutorialOptional.isPresent()) {
            Tutorial existingTutorial = existingTutorialOptional.get();

            // Update the fields of existingTutorial with the values from updatedTutorial
            existingTutorial.setTitle(updatedTutorial.getTitle());
            existingTutorial.setDescription(updatedTutorial.getDescription());

            // Save the updated tutorial back to the database
            return tutorialServices.updateTutorial(existingTutorial);
        } else {
            throw new RuntimeException("Tutorial not found with id: " + id);
        }
    }


    @DeleteMapping("/delete")
    public void deleteTutorial(@RequestBody Tutorial newTutorial){
        tutorialServices.deleteTutorial(newTutorial);
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteTutorialById(@PathVariable("id")int id){
        tutorialServices.deleteTutorialById(id);
    }
    @DeleteMapping("/deleteAll")
    public void deleteAllTutorials(@RequestBody List<Tutorial> tutorials){
        tutorialServices.deleteAllTutorials(tutorials);
    }
}
