package com.example.springsecurity.Service;

import com.example.springsecurity.ApiException.ApiException;
import com.example.springsecurity.Model.MyUser;
import com.example.springsecurity.Model.Todo;
import com.example.springsecurity.Repository.AuthRepository;
import com.example.springsecurity.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthRepository authRepository;
    public List<Todo> getTodos(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        return todoRepository.findTodoByUser(user);
    }

    public void addTodo(Integer userId, Todo todo) {
        MyUser user = authRepository.findMyUserById(userId);
        todo.setUser(user);
        todoRepository.save(todo);
    }

    public void updateTodo(Integer userId, Todo todo, Integer id){
        Todo oldTodo = todoRepository.findTodoById(id);
        MyUser user = authRepository.findMyUserById(userId);
        if (oldTodo == null || user == null || userId != oldTodo.getUser().getId() )
            throw new ApiException("Invalid");
        oldTodo.setMessage(todo.getMessage());
        todoRepository.save(oldTodo);
    }

    public void deleteTodo(Integer userId, Integer id){
        Todo todo = todoRepository.findTodoById(id);
        MyUser user = authRepository.findMyUserById(userId);
        if (todo == null || user == null || userId != todo.getUser().getId() )
            throw new ApiException("Invalid");
        todoRepository.delete(todo);
    }
}
