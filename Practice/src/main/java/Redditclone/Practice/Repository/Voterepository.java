package Redditclone.Practice.Repository;

import Redditclone.Practice.Model.Post;
import Redditclone.Practice.Model.User;
import Redditclone.Practice.Model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Voterepository extends JpaRepository<Vote,Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
