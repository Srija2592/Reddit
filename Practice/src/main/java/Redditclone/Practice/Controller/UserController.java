package Redditclone.Practice.Controller;

import Redditclone.Practice.Dto.UserDto;
import Redditclone.Practice.Model.User;
import Redditclone.Practice.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/user/")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("updateuser/{fileName}")
    public ResponseEntity<User> updateuser(@RequestBody UserDto userDto,@PathVariable String fileName){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateuser(userDto,fileName));
    }

    @GetMapping("getuser/{username}")
    public ResponseEntity<User> getuser(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(username));
    }

    @GetMapping("getFilename/{username}")
    public ResponseEntity<String> getfileName(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getfileName(username));
    }
}
