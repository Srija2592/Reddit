package Redditclone.Practice.Service;

import Redditclone.Practice.Dto.Subredditdto;
import Redditclone.Practice.Exception.SpringRedditException;
import Redditclone.Practice.Mapper.SubredditMapper;
import Redditclone.Practice.Model.Subreddit;
import Redditclone.Practice.Model.User;
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
public class SubredditService {
    private final Userrepository userrepository;

    private final Subredditrepository subredditrepository;

    private final SubredditMapper subredditMapper;

    @Transactional
    public Subredditdto save(Subredditdto subredditdto){
        User user=userrepository.findByUsername(subredditdto.getUsername()).orElseThrow(()->new UsernameNotFoundException("user not found"));
        Subreddit save=subredditrepository.save(subredditMapper.mapDtoToSubreddit(subredditdto,user));
        subredditdto.setId(save.getId());
        return subredditdto;

    }



    @Transactional(readOnly = true)
    public List<Subredditdto> getAll() {
        return subredditrepository.findAll().stream().map(subredditMapper::mapSubredditToDto).collect(Collectors.toList());
    }


    public Subredditdto getSubreddit(Long id) {
        Subreddit subreddit=subredditrepository.findById(id).orElseThrow(()->new SpringRedditException("no subreddit found with id "+id));
        return subredditMapper.mapSubredditToDto(subreddit);

    }
}
