package ru.geekbrains.springboothomework2;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Component
public class StudentRepository {
    private final List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
        IntStream.range(1, 11).forEach(i -> students.add(new Student("Student#" + i, "Group#" + i)));
    }

    public List<Student> getStudents() {
        return List.copyOf(students);
    }

    public Student getById(long id) {
        return students.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().orElse(null);
    }

    public Student getByName(String name) {
        return students.stream().filter(it -> Objects.equals(it.getName(), name)).findFirst().orElse(null);
    }

    public Student getByGroupName(String groupName) {
        return students.stream().filter(it -> Objects.equals(it.getGroupName(), groupName)).findFirst().orElse(null);
    }

    public void insertStudent(String name, String groupName) {
        if (name != null && groupName != null) {
            students.add(new Student(name, groupName));
        } else throw new NullPointerException();

    }

    public void deleteById(long id) {
        Student target = getById(id);
        students.remove(target);
    }
}
