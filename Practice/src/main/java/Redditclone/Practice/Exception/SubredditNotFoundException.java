package Redditclone.Practice.Exception;

public class SubredditNotFoundException extends RuntimeException {
    public SubredditNotFoundException(String subredditName) {
        super(subredditName);
    }
}
