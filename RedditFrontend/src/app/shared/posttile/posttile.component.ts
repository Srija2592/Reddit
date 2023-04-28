import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { faComments } from '@fortawesome/free-solid-svg-icons';
import { PostModel } from '../post-model';

@Component({
  selector: 'app-posttile',
  templateUrl: './posttile.component.html',
  styleUrls: ['./posttile.component.css']

})
export class PosttileComponent implements OnInit {
  faComments = faComments;
  @Input() posts!: PostModel[];

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToPost(id: number): void {
    this.router.navigateByUrl('/view-post/' + id);
  }

}
