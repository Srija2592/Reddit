package Redditclone.Practice.Service;

import Redditclone.Practice.Dto.UserDto;
import Redditclone.Practice.Model.User;
import Redditclone.Practice.Repository.Userrepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class UserService {

    private final Userrepository userrepository;

    public User updateuser(UserDto userDto){
        User user=userrepository.findByUsername(userDto.getUsername()).orElseThrow(()->new UsernameNotFoundException(userDto.getUsername()));
        user.setImage(userDto.getImage());
        user.setFullname(userDto.getFullname());
        user.setPassword(userDto.getPassword());
        return user;

    }

    public User getUser(String username){
        return userrepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }
}
