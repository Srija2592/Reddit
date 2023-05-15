import { Component, Input, OnInit } from '@angular/core';
import { PostModel } from '../shared/post-model';
import { ActivatedRoute } from '@angular/router';
import { CommentPayload } from '../commentpayload';
import { CommentService } from '../comment.service';
import { PostService } from '../shared/post.service';
import { User } from '../updateprofile/User';
import { UserService } from '../user.service';
import { FileService } from '../file.service';
import { LocalStorageService } from 'ngx-webstorage';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {
  name: string;
  posts!: PostModel[];
  comments!: CommentPayload[];
  postLength!: number;
  commentLength!: number;
  user:any;
  username:any='';
  filegroup!:FormGroup;



  retrievedImage:any;
  base64Data:any;
  retrieveResponse:any;
  fileName!: string;

  image:any;


  constructor(private activatedRoute: ActivatedRoute, private postService: PostService,private http:HttpClient,
    private commentService: CommentService,private sanitizer: DomSanitizer,private userservice:UserService,private fileservice:FileService) {
    this.name = this.activatedRoute.snapshot.params['name'];

    this.postService.getAllPostsByUser(this.name).subscribe(data => {
      this.posts = data;
      this.postLength = data.length;
    });
    this.commentService.getAllCommentsByUser(this.name).subscribe(data => {
      this.comments = data;
      this.commentLength = data.length;
    });
    this.userservice.getUserDetails(this.name).subscribe(data=>{this.user=data});




  }

  ngOnInit(): void {
    this.filegroup=new FormGroup({
      fileName:new FormControl(this.user.fileName,Validators.required),
      username:new FormControl(this.user.username,Validators.required)
    })

  }

  check(){
    this.fileName=this.user.fileName;
    console.log(this.fileName);
    this.fileservice.getFile(this.fileName).subscribe(res=>{this.retrieveResponse=res,
      this.base64Data=this.retrieveResponse.file,
      this.retrievedImage='data:image/jpeg;base64,'+this.retrieveResponse;

      var url=URL.createObjectURL(this.retrieveResponse);
      this.image=this.sanitizer.bypassSecurityTrustResourceUrl(url);
      console.log(this.retrieveResponse);

      // var reader=new FileReader();
      // reader.addEventListener("load",()=>{
      //   this.image=reader.result;
      //   console.log(this.image)
      // },false);
      // if(this.retrieveResponse){
      //   reader.readAsDataURL(this.retrieveResponse);
      // }
      // else{
      //   console.log("empty")
      // }

  }
    )

  }



}


