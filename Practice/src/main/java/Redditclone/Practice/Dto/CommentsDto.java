package Redditclone.Practice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private Long id;

    private Long postId;

    private LocalDate createdDate;

    private String text;

    private String username;
}
