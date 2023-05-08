package Redditclone.Practice.Service;

import Redditclone.Practice.Dto.PostRequest;
import Redditclone.Practice.Dto.PostResponse;
import Redditclone.Practice.Exception.PostNotFoundException;
import Redditclone.Practice.Exception.SubredditNotFoundException;
import Redditclone.Practice.Mapper.PostMapper;
import Redditclone.Practice.Model.Post;
import Redditclone.Practice.Model.Subreddit;
import Redditclone.Practice.Model.User;
import Redditclone.Practice.Repository.PostRepository;
import Redditclone.Practice.Repository.Subredditrepository;
import Redditclone.Practice.Repository.Userrepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {
    private final Subredditrepository subredditrepository;

    private final AuthService authService;

    private final PostMapper postMapper;

    private final PostRepository postRepository;

    private final Userrepository userrepository;

    public void save(PostRequest postRequest) {
        Subreddit subreddit=subredditrepository.findByName(postRequest.getSubredditName());
        User user=authService.getCurrentUser();
        Post post= postMapper.Map(postRequest,subreddit,user);
        postRepository.save(post);

    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream().map(postMapper::mapToDto).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId){
        Subreddit subreddit=subredditrepository.findById(subredditId).orElseThrow(()->new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts=postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username){
        User user=userrepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        return postRepository.findByUser(user).stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }
}
