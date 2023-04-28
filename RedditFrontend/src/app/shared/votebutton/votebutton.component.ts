import { Component, Input, OnInit } from '@angular/core';
import { PostModel } from '../post-model';
import { faArrowDown, faArrowUp } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from 'src/app/auth.service';
import { PostService } from '../post.service';
import { ToastrService } from 'ngx-toastr';
import { VoteType } from './votetype';
import { VotePayload } from './votepayload';
import { throwError } from 'rxjs';
import { VoteService } from 'src/app/vote.service';

@Component({
  selector: 'app-votebutton',
  templateUrl: './votebutton.component.html',
  styleUrls: ['./votebutton.component.css']
})
export class VotebuttonComponent implements OnInit{
  @Input() post:PostModel={
    id:0,
  postName:'',
  url:'',
  description:'',
  voteCount:0,
  userName:'',
  subredditName:'',
  commentCount:0
  };
  votePayload: VotePayload;
  faArrowUp = faArrowUp;
  faArrowDown = faArrowDown;
  upvoteColor!: string;
  downvoteColor!: string;
  isLoggedIn!: boolean;

  constructor(private voteService: VoteService,
    private authService: AuthService,
    private postService: PostService, private toastr: ToastrService) {

    this.votePayload = {
      voteType: VoteType.DOWNVOTE,
      postId: 0
    }
    this.authService.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
  }

  ngOnInit(): void {
    this.updateVoteDetails();
  }

  upvotePost() {
    this.votePayload.voteType = VoteType.UPVOTE;
    this.vote();
    this.downvoteColor = '';
    console.log("upvote");
  }

  downvotePost() {
    this.votePayload.voteType = VoteType.DOWNVOTE;
    this.vote();
    this.upvoteColor = '';
    console.log("downvote");
  }

  private vote() {
    this.votePayload.postId = this.post.id;
    this.voteService.vote(this.votePayload).subscribe(() => {
      this.updateVoteDetails();
    }, error => {
      this.toastr.error(error.error.message);
      throwError(error);
    });
  }

  private updateVoteDetails() {
    this.postService.getPost(this.post.id).subscribe(post => {
      this.post = post;
    });
  }
}
