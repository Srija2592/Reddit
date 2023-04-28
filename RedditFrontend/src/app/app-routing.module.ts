import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';
import { HomeComponent } from './home/home.component';
import { ViewpostComponent } from './viewpost/viewpost.component';
import { CreatepostComponent } from './createpost/createpost.component';
import { CreatesubredditComponent } from './createsubreddit/createsubreddit.component';
import { ListsubredditComponent } from './listsubreddit/listsubreddit.component';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { AuthGuard } from './auth/auth.guard';
import { ViewsubredditComponent } from './viewsubreddit/viewsubreddit.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  { path: 'view-post/:id', component: ViewpostComponent },
  {path:'sign-up',component:SignupComponent},
  {path:'login',component:LoginComponent},
  { path: 'create-subreddit', component: CreatesubredditComponent,canActivate:[AuthGuard]},
  { path: 'create-post', component: CreatepostComponent,canActivate:[AuthGuard]},
  { path: 'list-subreddits', component: ListsubredditComponent },
  {path:'user-profile/:name',component:UserprofileComponent,canActivate:[AuthGuard]},
   {path:'view-subreddit/:id',component:ViewsubredditComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
