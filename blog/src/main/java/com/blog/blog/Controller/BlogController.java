package com.blog.blog.Controller;

import com.blog.blog.Entity.Blog;
import com.blog.blog.Entity.User;
import com.blog.blog.Service.BlogService;
import com.blog.blog.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<String> createBlog(@RequestParam Long Id, @RequestParam String title, @RequestParam String body){
        Optional<User> optionalUser = userService.findUserById(Id);

        if(optionalUser.isEmpty()){
            return ResponseEntity.status(404).body("User not found.");
        }

        if(blogService.findBlogByTitle(title).isPresent()){
            return ResponseEntity.status(409).body("Blog with title already exists.");
        }

        User user = optionalUser.get();

        String response = blogService.createBlog(user.getUserId(), title, body);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long Id){
        Optional<Blog>  optionalBlog = blogService.getByBlogId(Id);

        if(optionalBlog.isEmpty()){
            return ResponseEntity.status(404).body("Blog not found");
        }

        Blog blog = optionalBlog.get();

       String response = blogService.deleteBlog(blog.getBlogId());
       return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<String> updateBlog(@PathVariable Long Id, @RequestParam String title, @RequestParam String body){
        Optional<Blog>  optionalBlog = blogService.getByBlogId(Id);

        if(optionalBlog.isEmpty()){
            return ResponseEntity.status(404).body("Blog not found");
        }

        Blog blog = optionalBlog.get();

        String response = blogService.updateBlog(blog.getBlogId(), title, body);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<String> getBlogById(@PathVariable Long Id){
        Optional<Blog>  optionalBlog = blogService.getByBlogId(Id);

        if(optionalBlog.isEmpty()){
            return ResponseEntity.status(404).body("Blog not found");
        }

        Blog blog = optionalBlog.get();

        return ResponseEntity.ok(blog.toString());

    }
}
