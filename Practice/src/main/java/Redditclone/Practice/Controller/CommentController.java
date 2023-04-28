package Redditclone.Practice.Controller;

import Redditclone.Practice.Dto.CommentsDto;
import Redditclone.Practice.Service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto){
        commentService.save(commentsDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("by-post/{postId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId){
        return ResponseEntity.status(OK).body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("byuser/{username}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@PathVariable String username){
        return ResponseEntity.status(OK).body(commentService.getAllCommentsForUser(username));
    }
}
