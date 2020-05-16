package pt.jp.todo.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pt.jp.todo.entities.Todo;
import pt.jp.todo.services.TodoService;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/todos")
    public String todos(Model model) {
        model.addAttribute("todo", new Todo());
        model.addAttribute("todos", todoService.findAll());
        return "todos";
    }

    @PostMapping("/todoNew")
    public String add(@Valid Todo todo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            model.addAttribute("todo", todo);
            model.addAttribute("todos", todoService.findAll());
            model.addAttribute("hasErrors", true);
            return "todos";
        }

        todoService.save(todo);
        model.addAttribute("todos", todoService.findAll());
        logger.info("Form submitted successfully.");
        return "redirect:/todos";
    }

    @PostMapping("/todoDelete/{id}")
    public String delete(@PathVariable long id, Model model) {
        todoService.deleteById(id);
        model.addAttribute("todos", todoService.findAll());
        return "redirect:/todos";
    }

    @PostMapping("/todoUpdate/{id}")
    public String update(@PathVariable long id, Model model) {
        Todo todo = todoService.findById(id).get();
        if ("Yes".equals(todo.getCompleted())) {
            todo.setCompleted("No");
        } else {
            todo.setCompleted("Yes");
        }
        todoService.save(todo);
        model.addAttribute("todos", todoService.findAll());
        return "redirect:/todos";
    }
}