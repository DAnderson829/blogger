package com.blog.blog.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blog_Id;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    private String body;


    public Blog(){}

    public Blog(String title, User author, LocalDateTime creationDate, String body){
        this.title = title;
        this.author = author;
        this.creationDate = creationDate;
        this.body = body;
    }

    public Long getBlogId(){
        return blog_Id;
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(User author){
        this.author = author;
    }
    public String getAuthor(){
        return author.getUsername();
    }

    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }

    public String getCreationDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return creationDate.format(formatter);
    }

    public String getBody(){
        return body;
    }

    public void setBody(String body){
        this.body = body;
    }

}
