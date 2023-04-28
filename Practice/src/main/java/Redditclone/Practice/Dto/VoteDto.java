package Redditclone.Practice.Dto;

import Redditclone.Practice.Model.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoteDto {
    private VoteType voteType;

    private Long postId;
}
