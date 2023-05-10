package Redditclone.Practice.Controller;

import Redditclone.Practice.Dto.UserDto;
import Redditclone.Practice.Model.User;
import Redditclone.Practice.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("updateuser")
    public ResponseEntity<User> updateuser(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateuser(userDto));
    }

    @GetMapping("getuser/{username}")
    public ResponseEntity<User> getuser(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(username));
    }
}
