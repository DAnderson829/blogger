package com.blog.blog.Repository;

import com.blog.blog.Entity.Blog;
import com.blog.blog.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> findByTitle(String title);

    Optional<Blog> findByAuthor(User user);

    List<Blog> findByAuthorId(Long Id);

    List<Blog> findByUsername(String username);

    List<Blog> findByKeyword(String keyword);
}
