import { Component, OnInit } from '@angular/core';
import { SubredditModel } from '../subredditmodel';
import { SubredditService } from '../subreddit.service';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-listsubreddit',
  templateUrl: './listsubreddit.component.html',
  styleUrls: ['./listsubreddit.component.css']
})
export class ListsubredditComponent implements OnInit {

  subreddits!: Array<SubredditModel>;
  constructor(private subredditService: SubredditService) { }

  ngOnInit() {
    this.subredditService.getAllSubreddits().subscribe(data => {
      this.subreddits = data;
    }, error => {
      throwError(error);
    })
  }

}
