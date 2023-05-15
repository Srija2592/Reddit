import { Component, OnInit } from '@angular/core';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { UpdateprofileComponent } from '../updateprofile/updateprofile.component';
import { MatDialog } from '@angular/material/dialog';
import { CreatepostComponent } from '../createpost/createpost.component';
import { UserDto } from '../updateprofile/UserDto';
import { UserService } from '../user.service';
import { User } from '../updateprofile/User';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  faUser = faUser;
  isLoggedIn!: boolean;
  username!: string;
  user:User={
    'image':'',
    'username':'',
    'password':'',
    'created':new Date(),
    'email':'',
    'enabled':false
  };

  constructor(private authService: AuthService, private router: Router,public dialog: MatDialog,private userservice:UserService) { }

  ngOnInit() {
    this.authService.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
    this.authService.username.subscribe((data: string) => this.username = data);
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUserName();
    this.userservice.getUserDetails(this.username).subscribe((data)=>{this.user=data});
  }

  goToUserProfile() {
    this.router.navigateByUrl('/user-profile/' + this.username);
  }
  updateprofile(){
    const data = this.user;
    this.dialog.open(UpdateprofileComponent, {
      data: data,
      height: '400px',
      width: '800px',
    });
  }

  logout() {
    this.authService.logout();
    this.isLoggedIn = false;
    this.router.navigateByUrl('/login');
  }

}
