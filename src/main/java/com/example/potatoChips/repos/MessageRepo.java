package com.example.potatoChips.repos;

import com.example.potatoChips.domain.Message;
import com.example.potatoChips.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {

    Page<Message> findByTag(String tag, Pageable pageable); //генерится в JPQL запрос, pageable - постраничное отображение

    Page<Message> findAll(Pageable pageable);

    Page<Message> findByAuthor(User user, Pageable pageable);
}