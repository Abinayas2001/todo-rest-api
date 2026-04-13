package com.abinaya.todoapp.service;

import com.abinaya.todoapp.exception.ResourceNotFoundException;
import com.abinaya.todoapp.model.Todo;
import com.abinaya.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Todo not found with id: " + id));
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo existing = getTodoById(id);
        existing.setTitle(updatedTodo.getTitle());
        existing.setDescription(updatedTodo.getDescription());
        existing.setCompleted(updatedTodo.isCompleted());
        return todoRepository.save(existing);
    }

    public void deleteTodo(Long id) {
        Todo existing = getTodoById(id);
        todoRepository.delete(existing);
    }
}