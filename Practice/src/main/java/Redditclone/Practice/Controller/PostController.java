package Redditclone.Practice.Controller;

import Redditclone.Practice.Dto.PostRequest;
import Redditclone.Practice.Dto.PostResponse;
import Redditclone.Practice.Service.PostService;
import lombok.AllArgsConstructor;
import org.hibernate.transform.ResultTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/posts/")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest){
        postService.save(postRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public  ResponseEntity<PostResponse> getPost(@PathVariable Long id){
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@PathVariable Long id){
        return status(HttpStatus.OK).body(postService.getPostsBySubreddit(id));
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String username){
        return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
    }
}
