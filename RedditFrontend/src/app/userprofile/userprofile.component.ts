import { Component, OnInit } from '@angular/core';
import { PostModel } from '../shared/post-model';
import { ActivatedRoute } from '@angular/router';
import { CommentPayload } from '../commentpayload';
import { CommentService } from '../comment.service';
import { PostService } from '../shared/post.service';
import { User } from '../updateprofile/User';
import { UserService } from '../user.service';

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
  user!:User;
  constructor(private activatedRoute: ActivatedRoute, private postService: PostService,
    private commentService: CommentService,private userservice:UserService) {
    this.name = this.activatedRoute.snapshot.params['name'];

    this.postService.getAllPostsByUser(this.name).subscribe(data => {
      this.posts = data;
      this.postLength = data.length;
    });
    this.commentService.getAllCommentsByUser(this.name).subscribe(data => {
      this.comments = data;
      this.commentLength = data.length;
    });
    this.userservice.getuser(this.name).subscribe(data=>{this.user=data});
    console.log(this.user);
  }

  ngOnInit(): void {
  }

}
