package Redditclone.Practice.Repository;

import Redditclone.Practice.Model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Subredditrepository extends JpaRepository<Subreddit,Long> {
    Subreddit findByName(String subredditName);
}
