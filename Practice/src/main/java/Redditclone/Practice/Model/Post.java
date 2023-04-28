package Redditclone.Practice.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotBlank(message = "post name should not be NULL")
    private String postName;

    @Nullable
    private String url;

    @Nullable
    @Lob
    private String description;

    private Integer voteCount;

    private Integer commentCount;

    @ManyToOne
    @JoinColumn(name = "username",referencedColumnName = "username")
    private User user;

    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "name",referencedColumnName = "name")
    private Subreddit subreddit;



}
