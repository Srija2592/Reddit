import { Component, OnInit,Inject } from '@angular/core';
import { UserService } from '../user.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { UserDto } from './UserDto';
import {throwError} from 'rxjs';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-updateprofile',
  templateUrl: './updateprofile.component.html',
  styleUrls: ['./updateprofile.component.css']
})
export class UpdateprofileComponent implements OnInit{

  updateuserform!:FormGroup;
  updatedata!:UserDto;
  submitted:boolean=false;

  constructor(private userservice:UserService, @Inject(MAT_DIALOG_DATA) public data: UserDto,private authservice:AuthService,public dialogref:MatDialogRef<UpdateprofileComponent>){
    this.updatedata={
      username:data.username,
      password:data.password,
      image:data.image,
      fullname:data.fullname
    };
  }
  ngOnInit(): void {
    this.updateuserform=new FormGroup({
      password:new FormControl('',Validators.required),
      image:new FormControl('',Validators.required),
      username:new FormControl(this.authservice.getUserName(),Validators.required),
      fullname:new FormControl('',Validators.required)


    });}

    updateuserdata(){
      if(this.submitted==false){
        this.updatedata.password=this.updateuserform.get('password')?.value;
        this.updatedata.image=this.updateuserform.get('image')?.value;
        this.updatedata.username=this.updateuserform.get('username')?.value;
        this.updatedata.fullname=this.updateuserform.get('fullname')?.value;

        this.userservice.updateuser(this.updatedata).subscribe((data)=>{
          this.updatedata=data,
          this.submitted=true,
          this.dialogref.close();
        }
        )
      }


    }}
