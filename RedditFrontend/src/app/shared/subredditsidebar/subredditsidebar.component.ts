import { Component, OnInit } from '@angular/core';
import { SubredditService } from 'src/app/subreddit.service';
import { SubredditModel } from 'src/app/subredditmodel';

@Component({
  selector: 'app-subredditsidebar',
  templateUrl: './subredditsidebar.component.html',
  styleUrls: ['./subredditsidebar.component.css']
})
export class SubredditsidebarComponent implements OnInit{
  subreddits: Array<SubredditModel> = [];
  displayViewAll!: boolean;

  constructor(private subredditService: SubredditService) {
    this.subredditService.getAllSubreddits().subscribe(data => {
      if (data.length > 3) {
        this.subreddits = data.splice(0, 3);
        this.displayViewAll = true;
      } else {
        this.subreddits = data;
      }
    });
  }

  ngOnInit(): void { }

}
