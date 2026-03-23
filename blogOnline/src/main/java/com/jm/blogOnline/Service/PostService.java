package com.jm.blogOnline.Service;

import com.jm.blogOnline.Entity.Author;
import com.jm.blogOnline.Entity.Post;
import com.jm.blogOnline.Repository.IAuthorRepository;
import com.jm.blogOnline.Repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IAuthorRepository authorRepository;

    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }

    public Post findPostById(Long id){
        verifyID(id);
        return postRepository.findById(id).orElseThrow(()-> new RuntimeException("Post not found"));
    }

    public Post createPost(Long idAuthor, Post post){
        Author author = authorRepository.findById(idAuthor).orElseThrow(()-> new RuntimeException("author not found"));
        post.setAuthor(author);
        author.getPostList().add(post);
        return postRepository.save(post);
    }

    public Post updatePost(Long idPost, Post post){
        verifyID(idPost);
        Post toUpdate = postRepository.findById(idPost).orElseThrow(()-> new RuntimeException("Post not found"));
        toUpdate.setTitle(post.getTitle());
        toUpdate.setDescription(post.getDescription());
        toUpdate.setPublicationDate(post.getPublicationDate());

        if(post.getAuthor() != null){
            Author author = authorRepository.findById(post.getAuthor().getIdAuthor()).orElseThrow(()-> new RuntimeException("author not found"));
            toUpdate.setAuthor(author);
        }
        return postRepository.save(toUpdate);
    }

    public void deletePostById(Long id){
        verifyID(id);
        postRepository.deleteById(id);
    }

    public void verifyID(Long id){
        if(id == null || id <= 0){
            throw new RuntimeException("ID is invalid");
        }
    }
}
