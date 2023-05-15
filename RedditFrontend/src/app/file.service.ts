import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { ImageDto } from './updateprofile/ImageDto';
import { UserDto } from './updateprofile/UserDto';

@Injectable({
  providedIn: 'root'
})
export class FileService {



  constructor(private http:HttpClient) { }

  upload(file:FormData,username:string):Observable<ImageDto>{

    return this.http.post<ImageDto>('backend/api/file/upload/'+username,file);
  }

  getFile(filename:string){
    return this.http.get('backend/api/file/'+filename,{responseType:'blob'});
  }




}
