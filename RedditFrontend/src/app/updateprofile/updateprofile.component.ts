import { Component, OnInit,Inject, Output, EventEmitter } from '@angular/core';
import { UserService } from '../user.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { UserDto } from './UserDto';

import { AuthService } from '../auth.service';
import { ImageDto } from './ImageDto';
import { FileService } from '../file.service';



@Component({
  selector: 'app-updateprofile',
  templateUrl: './updateprofile.component.html',
  styleUrls: ['./updateprofile.component.css']
})
export class UpdateprofileComponent implements OnInit{

  updateuserform!:FormGroup;
  updatedata:UserDto;
  submitted:boolean=false;
  selectedFile!:File;


  res!:ImageDto;
  username:any='';


  fileName:string='';

  constructor(private userservice:UserService, private fileservice:FileService,@Inject(MAT_DIALOG_DATA) public data: UserDto,private authservice:AuthService,public dialogref:MatDialogRef<UpdateprofileComponent>){
    this.updatedata={
      username:data.username,
      password:data.password,
      image:data.image,
      fullname:data.fullname,
      fileName:data.fileName


    };

  }
  ngOnInit(): void {
    this.updateuserform=new FormGroup({
      password:new FormControl('',Validators.required),
      image:new FormControl('',Validators.required),
      username:new FormControl(this.authservice.getUserName(),Validators.required),
      fullname:new FormControl('',Validators.required)

    });
  }

    updateuserdata(){
      if(this.submitted==false){
        const formData=new FormData();
        formData.append('imageFile',this.selectedFile,this.selectedFile.name);
        console.log(formData);


        this.updatedata.password=this.updateuserform.get('password')?.value;
        this.updatedata.image=this.updateuserform.get('image')?.value;
        this.updatedata.username=this.updateuserform.get('username')?.value;
        this.username=this.updatedata.username;
        this.updatedata.fullname=this.updateuserform.get('fullname')?.value;

        this.updatedata.fileName=this.selectedFile.name;


        this.fileservice.upload(formData,this.username).subscribe((ele)=>{this.res=ele,console.log(this.res)});
        this.userservice.updateuser(this.updatedata,this.selectedFile.name).subscribe((data)=>{
          console.log(data),

          this.updatedata=data,

          this.submitted=true,

          this.dialogref.close();
           console.log(this.updatedata);

        }
        )
      }
    }


    public onFileChanged(event:any){
      this.selectedFile=event.target.files[0];
      console.log("Heloo");
    }


  }
