package com.pgexmple.demo.service;
import com.pgexmple.demo.model.todoItem;
import com.pgexmple.demo.model.User;
import com.pgexmple.demo.repository.TodoitemRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoitemRepo todoitemRepo;
    @Autowired
    private UserService userService;
    @Override
    public todoItem save(todoItem todoItem, String username) {
        User user=userService.findByUsername(username);
        todoItem.setUserId(user.getId());
       if(todoItem.getCreateDate().equals(null)) todoItem.setCreateDate(LocalDateTime.now());
        todoItem.setDone(false);
        todoItem.setCreateDate(LocalDateTime.now());
        return todoitemRepo.save(todoItem);
    }
}
