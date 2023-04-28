package Redditclone.Practice.Repository;

import Redditclone.Practice.Model.Comment;
import Redditclone.Practice.Model.Post;
import Redditclone.Practice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
