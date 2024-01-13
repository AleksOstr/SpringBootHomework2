package ru.geekbrains.springboothomework2;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class StudentRepository {
    private final List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
        IntStream.range(1, 6).forEach(i -> students.add(new Student("Student" + i, "Group1")));
        IntStream.range(6, 11).forEach(i -> students.add(new Student("Student" + i, "Group2")));

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

    public List<Student> getByGroupName(String groupName) {
        return students.stream().filter(it -> Objects.equals(it.getGroupName(), groupName)).collect(Collectors.toList());
    }


    public boolean insertStudent(String name, String groupName) {
        if (name != null && groupName != null) {
            students.add(new Student(name, groupName));
            return true;
        } else return false;

    }

    public boolean deleteById(long id) {
        boolean result = false;
        try {
            Student target = getById(id);
            students.remove(target);
            result = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
