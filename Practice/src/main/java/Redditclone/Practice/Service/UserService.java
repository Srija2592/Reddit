package Redditclone.Practice.Service;

import Redditclone.Practice.Dto.UserDto;
import Redditclone.Practice.Model.FileModel;
import Redditclone.Practice.Model.User;
import Redditclone.Practice.Repository.FileRepository;
import Redditclone.Practice.Repository.Userrepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.Deflater;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class UserService {

    private final Userrepository userrepository;


    private final FileRepository fileRepository;

    private final PasswordEncoder passwordEncoder;

    public User updateuser(UserDto userDto,String fileName){
        User user=userrepository.findByUsername(userDto.getUsername()).orElseThrow(()->new UsernameNotFoundException(userDto.getUsername()));
        user.setImage(userDto.getImage());
        user.setFullname(userDto.getFullname());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFileName(fileName);
        return user;

    }

    public String getfileName(String username){
        User user=userrepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        String fil=user.getFileName();
        return fil;
    }
    public User getUser(String username){
        return userrepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }

    public static  byte[] compressbytes(byte[] data){
        Deflater deflater=new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
        byte[] buffer=new byte[1024];
        while (!deflater.finished()){
            int count=deflater.deflate(buffer);
            outputStream.write(buffer,0,count);

        }
        try{
            outputStream.close();
        } catch (IOException e) {
        }
        return outputStream.toByteArray();
    }
}
