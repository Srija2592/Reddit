package Redditclone.Practice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String password;

    private String image;

    private String username;

    private String fullname;

    private String fileName;
}
