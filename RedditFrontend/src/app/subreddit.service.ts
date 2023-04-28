import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SubredditModel } from './subredditmodel';

@Injectable({
  providedIn: 'root'
})
export class SubredditService {

  constructor(private http:HttpClient) { }

  getAllSubreddits(): Observable<Array<SubredditModel>> {
    return this.http.get<Array<SubredditModel>>('backend/api/subreddit');
  }

  createSubreddit(subredditModel: SubredditModel): Observable<SubredditModel> {
    return this.http.post<SubredditModel>('backend/api/subreddit',
      subredditModel);
  }

  getsubredditbyid(id:number):Observable<SubredditModel>{
    return this.http.get<SubredditModel>('backend/api/subreddit/'+id);

  }
}
