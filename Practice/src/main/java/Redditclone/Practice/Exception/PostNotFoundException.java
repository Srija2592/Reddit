package Redditclone.Practice.Exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String s) {
        super(s);
    }
}
