import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { SubredditService } from '../subreddit.service';
import { SubredditModel } from '../subredditmodel';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-viewsubreddit',
  templateUrl: './viewsubreddit.component.html',
  styleUrls: ['./viewsubreddit.component.css']
})
export class ViewsubredditComponent implements OnInit {

  subreddit!:SubredditModel;
  id!:number;
  constructor(private subredditservice:SubredditService,private activatedroute:ActivatedRoute){
    this.id=this.activatedroute.snapshot.params['id'];

    this.subredditservice.getsubredditbyid(this.id).subscribe(data=>this.subreddit=data);
  }
  ngOnInit(): void {

  }

}
