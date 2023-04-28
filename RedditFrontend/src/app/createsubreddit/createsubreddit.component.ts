import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, OnSameUrlNavigation, Router } from '@angular/router';
import { SubredditService } from '../subreddit.service';
import { throwError } from 'rxjs';
import { SubredditModel } from '../subredditmodel';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-createsubreddit',
  templateUrl: './createsubreddit.component.html',
  styleUrls: ['./createsubreddit.component.css']
})
export class CreatesubredditComponent implements OnInit {
  createSubredditForm: FormGroup;
  subredditModel!: SubredditModel;
  title = new FormControl('');
  description = new FormControl('');

  constructor(private router: Router, private subredditService: SubredditService,private activatedroute:ActivatedRoute,private authservice:AuthService) {
    this.createSubredditForm = new FormGroup({
      title: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });
    this.subredditModel = {
      name: '',
      description: '',
      username:0
    }
  }

  ngOnInit() {
  }

  discard() {
    this.router.navigateByUrl('/');
  }

  createSubreddit() {
    this.subredditModel.name = this.createSubredditForm.get('title')?.value;
    this.subredditModel.description = this.createSubredditForm.get('description')?.value;
    this.subredditModel.username=this.authservice.getUserName();

    console.log(this.subredditModel.username);
    this.subredditService.createSubreddit(this.subredditModel).subscribe(data => {
      this.router.navigateByUrl('/list-subreddits');

    }, error => {
      throwError(error);
    })
  }

}
