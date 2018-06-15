import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { MessagesComponent } from './messages/messages.component';
import { UsersComponent } from './users/users.component';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { UsersService } from './users.service';
import { MessagesService } from './messages.service';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FollowingComponent } from './following/following.component';
import { FollowersComponent } from './followers/followers.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { TimelineComponent } from './timeline/timeline.component';
import {FormsModule} from '@angular/forms';
import {SessionService} from './session.service';
import { PostMessageComponent } from './post-message/post-message.component';
import { WebsockettestComponent } from './websockettest/websockettest.component';


@NgModule({
  declarations: [
    AppComponent,
    MessagesComponent,
    UsersComponent,
    ProfileComponent,
    LoginComponent,
    UserDetailsComponent,
    FollowingComponent,
    FollowersComponent,
    UserDetailsComponent,
    TimelineComponent,
    PostMessageComponent,
    WebsockettestComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [HttpClientModule, UsersService, MessagesService, SessionService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
