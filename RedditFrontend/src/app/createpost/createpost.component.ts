import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CreatePostPayload } from '../createpostpayload';
import { SubredditModel } from '../subredditmodel';
import { Router } from '@angular/router';
import { PostService } from '../shared/post.service';
import { SubredditService } from '../subreddit.service';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-createpost',
  templateUrl: './createpost.component.html',
  styleUrls: ['./createpost.component.css']
})
export class CreatepostComponent implements OnInit{
  createPostForm: FormGroup;
  postPayload!: CreatePostPayload;
  subreddits!: Array<SubredditModel>;
  postName=new FormControl('');
  subredditName=new FormControl('');
  url=new FormControl('');
  description=new FormControl('');

  constructor(private router: Router, private postService: PostService,
    private subredditService: SubredditService) {
      this.createPostForm = new FormGroup({
        postName: new FormControl('', Validators.required),
        subredditName: new FormControl('', Validators.required),
        url: new FormControl('', Validators.required),
        description: new FormControl('', Validators.required),
      });
    this.postPayload = {
      postName: '',
      url: '',
      description: '',
      subredditName: ''
    }
  }

  ngOnInit() {

    this.subredditService.getAllSubreddits().subscribe((data) => {
      this.subreddits = data;
    }, error => {
      throwError(error);
    });

  }

  createPost() {
    this.postPayload.postName = this.createPostForm.get('postName')?.value;
    this.postPayload.subredditName = this.createPostForm.get('subredditName')?.value;
    this.postPayload.url = this.createPostForm.get('url')?.value;
    this.postPayload.description = this.createPostForm.get('description')?.value;
    console.log(this.postPayload);



    this.postService.createPost(this.postPayload).subscribe((data) => {
      this.router.navigateByUrl('/');
      console.log(data);
    }, error => {
      throwError(error);
    })
  }

  discardPost() {
    this.router.navigateByUrl('/');
  }

}
