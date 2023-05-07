import { Component, OnInit } from '@angular/core';
import { PostModel } from '../shared/post-model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { PostService } from '../shared/post.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentPayload } from '../commentpayload';
import { throwError } from 'rxjs';
import {CommentService} from '../comment.service';

@Component({
  selector: 'app-viewpost',
  templateUrl: './viewpost.component.html',
  styleUrls: ['./viewpost.component.css']
})
export class ViewpostComponent implements OnInit {
  postId: number;
  post!: PostModel;
  commentForm: FormGroup;
  commentPayload: CommentPayload;
  comments!: CommentPayload[];

  constructor(private postService: PostService, private activateRoute: ActivatedRoute,
    private commentService: CommentService, private router: Router) {
    this.postId = this.activateRoute.snapshot.params['id'];

    this.commentForm = new FormGroup({
      text: new FormControl('', Validators.required)
    });
    this.commentPayload = {
      text: '',
      postId: this.postId
    };
  }

  ngOnInit(): void {
    this.getPostById();
    this.getCommentsForPost();
  }

  postComment() {
    this.commentPayload.text = this.commentForm.get('text')?.value;
    this.commentService.postComment(this.commentPayload).subscribe(data => {
      this.commentForm.get('text')?.setValue('');
      this.getCommentsForPost();
    }, error => {
      throwError(error);
    })
  }

  private getPostById() {
    this.postService.getPost(this.postId).subscribe(data => {
      this.post = data;
    }, error => {
      throwError(error);
    });
  }

  private getCommentsForPost() {
    this.commentService.getAllCommentsForPost(this.postId).subscribe(data => {
      this.comments = data;
      console.log(this.comments);
    }, error => {
      throwError(error);
    });
  }

}
