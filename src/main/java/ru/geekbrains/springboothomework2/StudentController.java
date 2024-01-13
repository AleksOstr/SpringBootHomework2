package ru.geekbrains.springboothomework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository repository;

    @Autowired
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

    @GetMapping
    public Student getByGroupName(@RequestParam String groupName) {
        return repository.getByGroupName(groupName);
    }

    @DeleteMapping("/del/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable long id) {
        repository.deleteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertStudent(@RequestBody StudentRecord request) {
        repository.insertStudent(request.name(), request.groupName());
    }
}
