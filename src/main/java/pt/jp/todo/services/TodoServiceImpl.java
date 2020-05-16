package pt.jp.todo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.jp.todo.entities.Todo;
import pt.jp.todo.repositories.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> findById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (!todo.isPresent()) {
            return Optional.empty();
        }
        return todo;
    }

    @Override
    public void save(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }
}