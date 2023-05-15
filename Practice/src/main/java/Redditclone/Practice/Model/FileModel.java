package Redditclone.Practice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @Column(unique = true)
    private String fileName;

    private String fileType;

    @Lob
    private byte[] file;

//    @OneToOne(targetEntity = User.class)
//    private User user;



}
