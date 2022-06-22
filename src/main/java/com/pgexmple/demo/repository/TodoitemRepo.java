package com.pgexmple.demo.repository;

import com.pgexmple.demo.model.todoItem;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TodoitemRepo  extends JpaRepository<todoItem,Long> {
    List<todoItem> findByUserIdAndDoneFalse(Long userId);
}
