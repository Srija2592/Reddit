package Redditclone.Practice.Service;

import Redditclone.Practice.Dto.CommentsDto;
import Redditclone.Practice.Exception.PostNotFoundException;
import Redditclone.Practice.Mapper.CommentMapper;
import Redditclone.Practice.Model.Comment;
import Redditclone.Practice.Model.NotificationEmail;
import Redditclone.Practice.Model.Post;
import Redditclone.Practice.Model.User;
import Redditclone.Practice.Repository.CommentRepository;
import Redditclone.Practice.Repository.PostRepository;
import Redditclone.Practice.Repository.Userrepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final Userrepository userrepository;

    private final AuthService authService;
    private final CommentMapper commentMapper;

    private final MailService mailService;

    private final MailContentBuilder mailContentBuilder;

    public void save(CommentsDto commentsDto){
        Post post=postRepository.findById(commentsDto.getPostId()).orElseThrow(()->new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment=commentMapper.map(commentsDto,post,authService.getCurrentUser());
        commentRepository.save(comment);

        String message=mailContentBuilder.build(post.getUser().getUsername()+" posted a comment on your post."+post.getUrl());
        sendCommentNotification(message,post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername()+"commented on your post", user.getEmail(), message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream().
                map(commentMapper::mapToDto).collect(toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName){
        User user=userrepository.findByUsername(userName)
                .orElseThrow(()->new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

}
