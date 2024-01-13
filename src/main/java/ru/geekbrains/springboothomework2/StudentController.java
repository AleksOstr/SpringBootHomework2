package ru.geekbrains.springboothomework2;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository repository;


    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<Student> getStudents() {
        return repository.getStudents();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable long id) {
        return repository.getById(id);
    }

    @GetMapping
    public Student getByName(@RequestParam String name) {
        return repository.getByName(name);
    }

    @GetMapping("/group/{groupName}/student")
    public List<Student> getByGroupName(@PathVariable String groupName) {
        return repository.getByGroupName(groupName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        boolean result = repository.deleteById(id);
        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping
    public ResponseEntity<String> insertStudent(@RequestBody StudentRecord request) {
        boolean result = repository.insertStudent(request.name(), request.groupName());
        return result ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
