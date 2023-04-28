package Redditclone.Practice.Mapper;

import Redditclone.Practice.Dto.CommentsDto;
import Redditclone.Practice.Model.Comment;
import Redditclone.Practice.Model.Post;
import Redditclone.Practice.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target="id",ignore = true)
    @Mapping(target="text",source = "commentsDto.text")
    @Mapping(target = "createdDate",expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "post",source = "post")
    @Mapping(target = "user",source = "user")
    Comment map(CommentsDto commentsDto, Post post, User user);

    @Mapping(target = "postId",expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName",expression = "java(comment.getUser().getUsername())")
    @Mapping(target = "text",source = "text")
    CommentsDto mapToDto(Comment comment);
}
