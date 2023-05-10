import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserDto } from './updateprofile/UserDto';
import { User } from './updateprofile/User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  updateuser(userdto:UserDto):Observable<User>{
    return this.http.post<UserDto>('backend/api/user/updateuser',userdto);
  }

  getuser(username:string):Observable<User>{
    return this.http.get<User>('backend/api/user/getuser/'+username);
  }
}
