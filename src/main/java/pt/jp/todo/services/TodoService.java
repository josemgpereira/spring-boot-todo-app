package pt.jp.todo.services;

import pt.jp.todo.entities.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> findAll();
    Optional<Todo> findById(Long id);
    void save(Todo todo);
    void deleteById(Long id);
}