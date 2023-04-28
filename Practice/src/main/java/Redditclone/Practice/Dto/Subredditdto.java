package Redditclone.Practice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subredditdto {

    private String name;

    private String description;

    private Long id;

    private Integer numberOfPosts;

    private String username;
}
