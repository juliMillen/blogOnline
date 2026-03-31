package com.jm.blogOnline.Controller;

import com.jm.blogOnline.Entity.Author;
import com.jm.blogOnline.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/author")
@PreAuthorize("isAuthenticated()")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN','USER','AUTHOR')")
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author> authorList = authorService.findAllAuthors();
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN','USER','AUTHOR')")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        Author toSearch = authorService.findAuthorById(id);
        return new ResponseEntity<>(toSearch,HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        Author toCreate = authorService.createAuthor(author);
        return new ResponseEntity<>(toCreate,HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author){
        Author toUpdate = authorService.updateAuthor(id,author);
        return new ResponseEntity<>(toUpdate,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN'")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthorById(id);
        return new ResponseEntity<>("Author deleted correctly",HttpStatus.NOT_FOUND);
    }
}
