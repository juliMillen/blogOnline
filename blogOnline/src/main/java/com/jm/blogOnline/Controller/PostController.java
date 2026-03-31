package com.jm.blogOnline.Controller;

import com.jm.blogOnline.Entity.Post;
import com.jm.blogOnline.Service.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/post")
@PreAuthorize("isAuthenticated()")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN','USER','AUTHOR')")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> postList = postService.findAllPosts();
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN','USER','AUTHOR')")
    public ResponseEntity<Post> getPostById(@PathVariable Long id){
        Post toSearch = postService.findPostById(id);
        return new ResponseEntity<>(toSearch, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN','AUTHOR')")
    public ResponseEntity<Post> createPost(@PathVariable Long idAuthor, @RequestBody Post post){
        Post toCreate = postService.createPost(idAuthor,post);
        return new ResponseEntity<>(toCreate, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN','AUTHOR')")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post){
        Post toUpdate = postService.updatePost(id,post);
        return new ResponseEntity<>(toUpdate,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN','AUTHOR')")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted correctly", HttpStatus.NOT_FOUND);
    }
}
