package com.blog.blog.Service;

import com.blog.blog.Entity.Blog;
import com.blog.blog.Entity.User;
import com.blog.blog.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserService userService;

    public String createBlog(Long Id, String title, String body){
        if(blogRepository.findByTitle(title).isPresent()){
            return "Blog title already exists.";
        }
        Optional<User> optionalUser = userService.findUserById(Id);
        User user = optionalUser.get();

        Blog blog = new Blog(title, user, LocalDateTime.now(), body);
        blogRepository.save(blog);
        return "Blog created.";
    }

    public String deleteBlog(Long Id){
        if(blogRepository.findById(Id).isEmpty()){
            return "Blog not found.";
        }
        blogRepository.deleteById(Id);
        return "Blog successfully deleted.";
    }

    public String updateBlog(Long Id, String title, String body){
     if(blogRepository.findById(Id).isEmpty()) {
         return "Blog not found.";
     }
     Optional<Blog> optionalBlog = blogRepository.findById(Id);
     Blog blog = optionalBlog.get();
     blog.setTitle(title);
     blog.setBody(body);
     blogRepository.save(blog);
     return "Blog updated.";
    }

    public Optional<Blog> getBlogByTitle(String title){
        return blogRepository.findByTitle(title);
    }

    public List<Blog> getAllBlogsByUserId(Long userId){
        return blogRepository.findByAuthorId(userId);
    }

    public Optional<Blog> getByBlogId(Long Id){
        return blogRepository.findById(Id);
    }

    public List<Blog> getAllBlogsByUsername(String username){
        return blogRepository.findByUsername(username);
    }

    public List<Blog> findBlogByKeyword(String keyword){
        return blogRepository.findByKeyword(keyword);
    }




}
