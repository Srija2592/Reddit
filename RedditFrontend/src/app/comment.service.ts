import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import {CommentPayload} from './commentpayload';

@Injectable({
  providedIn: 'root'
})

export class CommentService {

  constructor(private httpClient: HttpClient) { }

  getAllCommentsForPost(postId: number): Observable<CommentPayload[]> {
    return this.httpClient.get<CommentPayload[]>('backend/api/comments/by-post/' + postId);
  }

  postComment(commentPayload: CommentPayload): Observable<any> {
    return this.httpClient.post<any>('backend/api/comments/', commentPayload);
  }

  getAllCommentsByUser(name: string) {
    return this.httpClient.get<CommentPayload[]>('backend/api/comments/byuser/' + name);
  }
}
