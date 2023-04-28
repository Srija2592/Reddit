package Redditclone.Practice.Mapper;

import Redditclone.Practice.Dto.Subredditdto;
import Redditclone.Practice.Model.Post;
import Redditclone.Practice.Model.Subreddit;
import Redditclone.Practice.Model.User;
import Redditclone.Practice.Repository.Userrepository;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {



    @Mapping(target = "numberOfPosts",expression = "java(mapPosts(subreddit.getPosts()))")
    Subredditdto mapSubredditToDto(Subreddit subreddit);

   default Integer mapPosts(List<Post> numberOfPosts){return numberOfPosts.size();}

    @InheritInverseConfiguration
    @Mapping(target = "posts",ignore = true)
    @Mapping(target = "createdDate",expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "user",source = "user")
    Subreddit mapDtoToSubreddit(Subredditdto subredditdto, User user);
}