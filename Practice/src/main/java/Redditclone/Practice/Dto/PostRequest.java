package Redditclone.Practice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long postId;

    private String subredditName;

    private String postName;

    private String url;

    private String description;
}
