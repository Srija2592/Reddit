import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SignupComponent } from './auth/signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './auth/login/login.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { ToastrModule } from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import {FontAwesomeModule,FaIconLibrary} from '@fortawesome/angular-fontawesome';
import { TokenInterceptor } from './token-interceptor';
// import { ShareButtonsModule } from '@ngx-share/buttons';

import { SidebarComponent } from './shared/sidebar/sidebar.component';
import { PosttileComponent } from './shared/posttile/posttile.component';
import { VotebuttonComponent } from './shared/votebutton/votebutton.component';
import { ViewpostComponent } from './viewpost/viewpost.component';
import { SubredditsidebarComponent } from './shared/subredditsidebar/subredditsidebar.component';
import { CreatepostComponent } from './createpost/createpost.component';
import { CreatesubredditComponent } from './createsubreddit/createsubreddit.component';
import { ListsubredditComponent } from './listsubreddit/listsubreddit.component';
import { EditorModule } from '@tinymce/tinymce-angular';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ViewsubredditComponent } from './viewsubreddit/viewsubreddit.component';
import {MatMenuModule} from '@angular/material/menu';
import { UpdateprofileComponent } from './updateprofile/updateprofile.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    PosttileComponent,
    VotebuttonComponent,
    SidebarComponent,
    ViewpostComponent,
    SubredditsidebarComponent,
    CreatepostComponent,
    CreatesubredditComponent,
    ListsubredditComponent,
    UserprofileComponent,
    ViewsubredditComponent,
    UpdateprofileComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    FontAwesomeModule,
    EditorModule,
    NgbModule,
    MatMenuModule,
    MatDialogModule
  ],
  providers: [
  {provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
