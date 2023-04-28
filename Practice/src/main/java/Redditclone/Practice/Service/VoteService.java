package Redditclone.Practice.Service;

import Redditclone.Practice.Dto.VoteDto;
import Redditclone.Practice.Exception.PostNotFoundException;
import Redditclone.Practice.Exception.SpringRedditException;
import Redditclone.Practice.Model.Post;
import Redditclone.Practice.Model.Vote;
import Redditclone.Practice.Repository.PostRepository;
import Redditclone.Practice.Repository.Voterepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static Redditclone.Practice.Model.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {
    private final Voterepository voterepository;

    private final PostRepository postRepository;

    private final AuthService authService;

    public void vote(VoteDto voteDto) {
        Post post=postRepository.findById(voteDto.getPostId()).orElseThrow(()->new PostNotFoundException("post not found "+voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser=voterepository.findTopByPostAndUserOrderByVoteIdDesc(post,authService.getCurrentUser());
        if(voteByPostAndUser.isPresent()&&voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())){
            throw new SpringRedditException("you have already "+voteDto.getVoteType()+"'d for the post");
        }
        if(UPVOTE.equals(voteDto.getVoteType())){
            post.setVoteCount(post.getVoteCount()+1);
        }else {
            post.setVoteCount(post.getVoteCount()-1);
        }
        voterepository.save(mapToVote(voteDto,post));

        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder().voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
