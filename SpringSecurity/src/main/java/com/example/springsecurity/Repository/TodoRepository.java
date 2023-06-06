package com.example.springsecurity.Repository;

import com.example.springsecurity.Model.MyUser;
import com.example.springsecurity.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {

    List<Todo> findTodoByUser(MyUser user);

    Todo findTodoById(Integer id);
}
