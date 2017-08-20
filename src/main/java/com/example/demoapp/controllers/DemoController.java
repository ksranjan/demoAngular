package com.example.demoapp.controllers;

import javax.validation.Valid;
import com.example.demoapp.models.Demo;
import com.example.demoapp.repositories.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DemoController {

    @Autowired
    DemoRepository demoRepository;

    @GetMapping("/demos")
    public List<Demo> getAllDemos() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return demoRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/demos")
    public Demo createDemo(@Valid @RequestBody Demo demo) {
        demo.setCompleted(false);
        return demoRepository.save(demo);
    }

    @GetMapping(value="/demos/{id}")
    public ResponseEntity<Demo> getDemoById(@PathVariable("id") String id) {
        Demo demo = demoRepository.findOne(id);
        if(demo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(demo, HttpStatus.OK);
        }
    }

    @PutMapping(value="/demos/{id}")
    public ResponseEntity<Demo> updateDemo(@PathVariable("id") String id,
                                           @Valid @RequestBody Demo demo) {
        Demo demoData = demoRepository.findOne(id);
        if(demoData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        demoData.setTitle(demo.getTitle());
        demoData.setCompleted(demo.getCompleted());
        Demo updatedDemo = demoRepository.save(demoData);
        return new ResponseEntity<>(updatedDemo, HttpStatus.OK);
    }

    @DeleteMapping(value="/demos/{id}")
    public void deleteDemo(@PathVariable("id") String id) {
        demoRepository.delete(id);
    }
}