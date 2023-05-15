package Redditclone.Practice.Service;

import Redditclone.Practice.Model.FileModel;
import Redditclone.Practice.Repository.FileRepository;
import Redditclone.Practice.Repository.Userrepository;
import Redditclone.Practice.Util.FileUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;


@Service
@AllArgsConstructor
@Slf4j
public class FileService {
    private final FileRepository fileRepository;

    private final Userrepository userrepository;

    public FileModel upload(MultipartFile fileDto,String username) throws IOException {
        FileModel f=new FileModel();
        f.setFileName(fileDto.getOriginalFilename());

        f.setFileType(fileDto.getContentType());
        f.setFile(fileDto.getBytes());
        return fileRepository.save(f);

    }



    @Transactional
    public byte[] getFile(String fileName) throws DataFormatException {
        Optional<FileModel> file=fileRepository.findByfileName(fileName);
        System.out.println(file.get().getFile());
//        return FileUtil.decompressBytes(file.get().getFile());
        return file.get().getFile();



    }


}
