import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PostModel } from './post-model';
import { HttpClient } from '@angular/common/http';
import { CreatePostPayload } from '../createpostpayload';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http:HttpClient){ }
  getAllPosts():Observable<Array<PostModel>> {
    return this.http.get<Array<PostModel>>('backend/api/posts/');
  }

  createPost(postPayload: CreatePostPayload): Observable<any> {
    return this.http.post('backend/api/posts/', postPayload);
  }

  getPost(id: number): Observable<PostModel> {
    return this.http.get<PostModel>('backend/api/posts/' + id);
  }

  getAllPostsByUser(name: string): Observable<PostModel[]> {
    return this.http.get<PostModel[]>('backend/api/posts/by-user/' + name);
  }

}
