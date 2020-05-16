package pt.jp.todo.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.jp.todo.entities.Todo;
import pt.jp.todo.repositories.TodoRepository;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final TodoRepository todoRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Todo> todos = new LinkedList<>();
        todos.add(new Todo("Learn Spring", "Yes"));
        todos.add(new Todo("Learn Driving", "No"));
        todos.add(new Todo("Go for a Walk", "No"));
        todos.add(new Todo("Cook Dinner", "Yes"));
        todoRepository.saveAll(todos);
    }
}