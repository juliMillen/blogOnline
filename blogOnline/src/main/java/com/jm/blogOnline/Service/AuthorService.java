package com.jm.blogOnline.Service;

import com.jm.blogOnline.Entity.Author;
import com.jm.blogOnline.Repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private IAuthorRepository authorRepository;

    public List<Author> findAllAuthors(){
        return authorRepository.findAll();
    }

    public Author findAuthorById(Long id){
        verifyId(id);
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public Author createAuthor(Author author){
        if(author == null){
            throw new RuntimeException("Author is null");
        }
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author author){
        verifyId(id);
        Author toUpdate = authorRepository.findById(id).orElseThrow(()-> new RuntimeException("author not found"));
        toUpdate.setName(author.getName());
        toUpdate.setSurname(author.getSurname());
        toUpdate.setDescription(author.getDescription());
       return authorRepository.save(toUpdate);
    }

    public void deleteAuthorById(Long id){
        verifyId(id);
        authorRepository.deleteById(id);
    }

    public void verifyId(Long id){
        if(id == null || id <= 0) {
            throw new RuntimeException("id invalid");
        }
    }
}
