package Redditclone.Practice.Repository;

import Redditclone.Practice.Model.FileModel;
import Redditclone.Practice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileModel,Long> {

    Optional<FileModel> findByfileName(String fileName);
}
