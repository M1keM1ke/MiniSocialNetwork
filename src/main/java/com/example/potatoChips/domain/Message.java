package com.example.potatoChips.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity // This tells Hibernate to make a table out of this class
public class Message {
    @Id //primary key
    @GeneratedValue(strategy=GenerationType.AUTO) //порядок генерирования идентификаций
    private Long id;

    @NotBlank(message = "Please fill the message") //если пустое сообщение
    @Length(max = 2048, message = "Message too long (more than 2kB)")
    private String text;
    @Length(max = 255, message = "Message too long (more than 255)")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER) //одному юзеру соответствует множество сообщений
    @JoinColumn(name = "user_id") //аннотацие вешает название колонки в бд user_id
    private User author;

    private String filename;

    public Message() { //пустой конструктор обязателен, иначе спринг не смогут создать этот класс в бд
    }

    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}