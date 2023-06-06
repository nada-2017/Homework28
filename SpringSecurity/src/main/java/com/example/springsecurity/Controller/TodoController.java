package com.example.springsecurity.Controller;


import com.example.springsecurity.Model.MyUser;
import com.example.springsecurity.Model.Todo;
import com.example.springsecurity.Service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/get")
    public ResponseEntity getTodos(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(todoService.getTodos(myUser.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addTodo(@AuthenticationPrincipal MyUser myUser, @RequestBody Todo todo){
        todoService.addTodo(myUser.getId(),todo);
        return ResponseEntity.status(200).body("Todo added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal MyUser myUser, @RequestBody Todo todo, @PathVariable Integer id){
        todoService.updateTodo(myUser.getId(), todo, id);
        return ResponseEntity.status(200).body("Todo updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTodo(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer id){
        todoService.deleteTodo(myUser.getId(), id);
        return ResponseEntity.status(200).body("Todo deleted");
    }
}
