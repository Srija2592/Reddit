package Redditclone.Practice.Controller;


import Redditclone.Practice.Model.FileModel;
import Redditclone.Practice.Service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/api/file/")
@AllArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("upload/{username}")
    public ResponseEntity<FileModel> upload(@RequestParam("imageFile") MultipartFile fileDto,@PathVariable String username) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.upload(fileDto,username));
    }

    @GetMapping("{fileName}")
    public ResponseEntity<byte[]> getfile(@PathVariable("fileName") String fileName) throws DataFormatException {
        byte[] img=fileService.getFile(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(img);
    }
}

