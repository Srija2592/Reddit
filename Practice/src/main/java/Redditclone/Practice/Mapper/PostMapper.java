package Redditclone.Practice.Mapper;

import Redditclone.Practice.Dto.PostRequest;
import Redditclone.Practice.Dto.PostResponse;
import Redditclone.Practice.Model.*;
import Redditclone.Practice.Repository.CommentRepository;
import Redditclone.Practice.Repository.Voterepository;
import Redditclone.Practice.Service.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

import static Redditclone.Practice.Model.VoteType.DOWNVOTE;
import static Redditclone.Practice.Model.VoteType.UPVOTE;

@Mapper(componentModel = "spring")

public abstract class PostMapper {

    @Autowired
    private  CommentRepository commentRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private Voterepository voterepository;
    @Mapping(target="createdDate",expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "description",source = "postRequest.description")

    @Mapping(target = "subreddit",source = "subreddit")
    @Mapping(target="voteCount",constant = "0")
    @Mapping(target = "user",source = "user")
    public abstract Post Map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id",source = "postId")
    @Mapping(target = "subredditName",source = "subreddit.name")
    @Mapping(target = "userName",source = "user.username")
    @Mapping(target = "commentCount",expression = "java(commentCount(post))")
    @Mapping(target = "upVote",expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote",expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post){return commentRepository.findByPost(post).size();}

    boolean isPostUpVoted(Post post){return checkVoteType(post,UPVOTE);}

    boolean isPostDownVoted(Post post){
        return checkVoteType(post,DOWNVOTE);
    }

    private boolean checkVoteType(Post post, VoteType voteType) {
        if(authService.isLoggedIn()){
            Optional<Vote> voteforpostbyuser=voterepository.findTopByPostAndUserOrderByVoteIdDesc(post,authService.getCurrentUser());
            return voteforpostbyuser.filter(vote -> vote.getVoteType().equals(voteType)).isPresent();
        }
        return false;
    }

//    String getDuration(Post post){return TimeAgo.using(post.getCreatedDate().toEpochMillis());}


}
