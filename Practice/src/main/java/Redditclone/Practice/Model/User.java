package Redditclone.Practice.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;

    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    private LocalDate created;

    private boolean enabled;

    private String image;

    private String fullname;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Subreddit> subreddits;


}
